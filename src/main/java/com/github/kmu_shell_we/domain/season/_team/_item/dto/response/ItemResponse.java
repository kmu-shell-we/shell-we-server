package com.github.kmu_shell_we.domain.season._team._item.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.BoomResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ExperienceDoubleResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.MissionChangeResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ScoreDeductionResponse;
import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "아이템 응답 DTO")
@JsonTypeInfo(use = JsonTypeInfo.Id.SIMPLE_NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BoomResponse.class, name = "BoomResponse"),
        @JsonSubTypes.Type(value = ExperienceDoubleResponse.class, name = "ExperienceDoubleResponse"),
        @JsonSubTypes.Type(value = MissionChangeResponse.class, name = "MissionChangeResponse"),
        @JsonSubTypes.Type(value = ScoreDeductionResponse.class, name = "ScoreDeductionResponse")
})
public abstract class ItemResponse {

    Class<? extends ItemResponse> type;

    public static ItemResponse from(Item item) {

        // TODO: 속성이 다른 아이템을 어떻게 처리해야 하나
    }
}
