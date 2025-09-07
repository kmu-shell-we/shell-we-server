package com.github.kmu_shell_we.domain.season._team._item.service;

import com.github.kmu_shell_we.domain.mission._season_mission.exception.SeasonMissionExceptions;
import com.github.kmu_shell_we.domain.mission._season_mission.repository.SeasonMissionRepository;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemListResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.BoomResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ExperienceDoubleResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.MissionChangeResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ScoreDeductionResponse;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import com.github.kmu_shell_we.domain.season._team._item.exception.ItemExceptions;
import com.github.kmu_shell_we.domain.season._team._item.repository.ItemRepository;
import com.github.kmu_shell_we.domain.season._team._team_mission.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._team_mission.exceptions.TeamMissionExceptions;
import com.github.kmu_shell_we.domain.season._team._team_mission.repository.TeamMissionRepository;
import com.github.kmu_shell_we.domain.season._team._user_team.repository.UserTeamRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.exception.TeamExceptions;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;
    private final ItemRepository itemRepository;
    private final TeamMissionRepository teamMissionRepository;
    private final SeasonMissionRepository seasonMissionRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("@itemService.canAccessItem(#user, #season, #team)")
    public ItemListResponse getItems(User user, Season season, Team team) {

        return ItemListResponse.from(itemRepository.findAllByTeam(team));
    }

    @Transactional
    @PreAuthorize("@itemService.canAccessItem(#user, #season, #team)")
    public ItemResponse drawItem(User user, Season season, Team team) {

        // 0. 포인트 조회 및 차감
        if (team.getPoint() < 100) {

            throw ItemExceptions.NOT_ENOUGH_POINT.toException();
        }

        team.setPoint(team.getPoint() - 100);

        // 1. 아이템 랜덤 뽑기
        ItemType itemType = drawRandomItem();

        // 2. 뽑은 아이템을 DB에 저장
        if (itemType == ItemType.EXPERIENCE_DOUBLE) {

            itemRepository.save(Item.builder()
                    .team(team)
                    .type(itemType)
                    .isUnUsed(true)
                    .build());
        } else {

            itemRepository.save(Item.builder()
                    .team(team)
                    .type(itemType)
                    .build());
        }

        // 3. 뽑은 아이템에 대한 응답 DTO 반환
        return switch (itemType) {
            case BOOM -> BoomResponse.of();
            case EXPERIENCE_DOUBLE -> ExperienceDoubleResponse.of();
            case MISSION_CHANGE -> {

                TeamMission current = teamMissionRepository
                        .findCurrentMission(team, LocalDateTime.now())
                        .orElseThrow(TeamMissionExceptions.NOT_FOUND_TEAM_MISSION::toException);

                Mission after = seasonMissionRepository.findRandomDailyMissionBySeason(season)
                        .orElseThrow(SeasonMissionExceptions.NOT_FOUND_SEASON_MISSION::toException)
                        .getMission();

                Mission previous = current.getMission();

                current.setMission(after);

                yield MissionChangeResponse.from(previous, after);
            }
            case SCORE_DEDUCTION -> {

                Team otherTeam = teamRepository.findRandomTeamExcept(team)
                                .orElseThrow(TeamExceptions.NOT_FOUND_TEAM::toException);

                otherTeam.setExperience(Math.max(0, otherTeam.getExperience() - 50));

                yield ScoreDeductionResponse.from(otherTeam, 50);
            }
        };
    }

    private ItemType drawRandomItem() {

        double r = ThreadLocalRandom.current().nextDouble();

        if (r < 0.50) return ItemType.BOOM;
        if (r < 0.70) return ItemType.EXPERIENCE_DOUBLE;
        if (r < 0.90) return ItemType.MISSION_CHANGE;

        return ItemType.SCORE_DEDUCTION;
    }

    public boolean canAccessItem(User user, Season season, Team team) {

        return userTeamRepository.findByUserAndTeam(user, team).isPresent()
                && season.isCurrentSeason()
                && season.equals(team.getSeason());
    }
}
