package org.infotracker.information.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationResponse {
    private Long id;
    private boolean isVerified;
    private String informationTitle;
    private LocalDateTime verifiedAt;

}
