package com.github.kmu_shell_we.domain.season._team._item.service;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import com.github.kmu_shell_we.domain.season._team._item.repository.ItemRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemGachaService {

    private final ItemRepository itemRepository;

    public ItemType drawRandomItem() {

        double r = ThreadLocalRandom.current().nextDouble();

        if (r < 0.50) return null;
        if (r < 0.70) return ItemType.NEXT_MISSION_EXP_2X;
        if (r < 0.90) return ItemType.MISSION_RESET;

        return ItemType.DECREASE_OTHER_TEAM_EXP;
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
}
