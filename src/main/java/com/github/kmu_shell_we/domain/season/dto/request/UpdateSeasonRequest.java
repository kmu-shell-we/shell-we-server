package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.UpdateStartBeforeEndDate;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@UpdateStartBeforeEndDate
public class UpdateSeasonRequest {

    @NotBlank
    LocalDateTime startedAt;

    @NotBlank
    LocalDateTime endedAt;

    @AssertTrue(message = "시작일은 종료일보다 앞서야 한다.")
    public boolean isValidDateTime() {

        return this.startedAt.isBefore(this.endedAt);
    }
}
