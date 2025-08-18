package com.github.kmu_shell_we.domain.season._team._item.service;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemListResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.BoomResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ExperienceDoubleResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ScoreDeductionResponse;
import com.github.kmu_shell_we.domain.season._team._item.exception.ItemExceptions;
import com.github.kmu_shell_we.domain.season._team._item.repository.ItemRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season._team.repository.TeamRepository;
import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final TeamRepository teamRepository;

    private final MissionChangeService  missionChangeService;

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season")
    public ItemListResponse getItems(Season season, Team team) {

        return ItemListResponse.from(itemRepository.findAllByTeam(team));
    }

    public ItemResponse drawItem(Season season, Team team) {

        ItemType item = drawRandomItem();

        switch (item) {
            case BOOM -> {
                return BoomResponse.of();
            }
            case EXPERIENCE_DOUBLE -> {
                return ExperienceDoubleResponse.of();
            }
            case MISSION_CHANGE -> {
                return missionChangeService.changeToNewMission(season, team);
            }
            case SCORE_DEDUCTION -> {
                Team otherTeam = teamRepository.findRandomTeamExcept(team);
                return ScoreDeductionResponse.of(otherTeam, 50);
            }
            default -> {
                throw ItemExceptions.FAILED_TO_DRAW.toException();
            }
        }
    }

    private ItemType drawRandomItem() {

        double r = ThreadLocalRandom.current().nextDouble();

        if (r < 0.50) return ItemType.BOOM;
        if (r < 0.70) return ItemType.EXPERIENCE_DOUBLE;
        if (r < 0.90) return ItemType.MISSION_CHANGE;

        return ItemType.SCORE_DEDUCTION;
    }
}
