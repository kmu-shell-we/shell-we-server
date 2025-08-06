package com.github.kmu_shell_we.domain.user._schedule.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "스케줄 등록 요청")
public class UpsertScheduleRequest {

    @NotBlank
    String url;
}
