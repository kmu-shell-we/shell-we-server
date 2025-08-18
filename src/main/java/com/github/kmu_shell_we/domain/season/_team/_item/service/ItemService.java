package com.github.kmu_shell_we.domain.season._team._item.service;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemListResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import com.github.kmu_shell_we.domain.season._team._item.repository.ItemRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
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

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season")
    public ItemListResponse getItems(Season season, Team team) {

        return ItemListResponse.from(itemRepository.findAllByTeam(team));
    }

    public ItemResponse drawItem(Team team) {

        ItemType drawnType = drawRandomItem();

        if (drawnType == null) return null;

        Item saved = itemRepository.save(
                Item.builder()
                        .team(team)
                        .type(drawnType)
                        .build()
        );

        return ItemResponse.from(saved);
    }

    private ItemType drawRandomItem() {

        double r = ThreadLocalRandom.current().nextDouble();

        if (r < 0.50) return ItemType.BOOM;
        if (r < 0.70) return ItemType.EXPERIENCE_DOUBLE;
        if (r < 0.90) return ItemType.MISSION_CHANGE;

        return ItemType.SCORE_DEDUCTION;
    }
}
