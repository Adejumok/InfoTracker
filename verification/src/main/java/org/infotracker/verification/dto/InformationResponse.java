package org.infotracker.verification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isVerified;

}
