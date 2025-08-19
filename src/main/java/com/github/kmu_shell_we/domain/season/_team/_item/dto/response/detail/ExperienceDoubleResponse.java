package com.github.kmu_shell_we.domain.season._team._item.dto.response.detail;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "[아이템] 경험치 두 배 응답 DTO")
@EqualsAndHashCode(callSuper = true)
public class ExperienceDoubleResponse extends ItemResponse {

    public static ExperienceDoubleResponse of() {

        ExperienceDoubleResponse response = new ExperienceDoubleResponse();

        response.setType(ItemType.EXPERIENCE_DOUBLE);

        return response;
    }
}
