package com.github.kmu_shell_we.domain.season._team._item.service;

import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemListResponse;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import com.github.kmu_shell_we.domain.season._team._item.repository.ItemRepository;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("#season.isCurrentSeason() and #season == #team.season")
    public ItemListResponse getItems(Season season, Team team) {

        List<Item> items = itemRepository.findAllByTeam(team);

        return ItemListResponse.from(items);
    }
}
