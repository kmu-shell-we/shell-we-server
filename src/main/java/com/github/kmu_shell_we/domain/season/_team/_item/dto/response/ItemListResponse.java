package com.github.kmu_shell_we.domain.season._team._item.dto.response;

import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(name = "아이템 목록 응답 DTO")
public class ItemListResponse {

    List<ItemResponse> items;

    public static ItemListResponse from(List<Item> items) {

        return ItemListResponse.of(items.stream().map(ItemResponse::from.toList());
    }
}
