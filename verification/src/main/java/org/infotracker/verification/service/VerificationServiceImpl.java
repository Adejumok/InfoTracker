package org.infotracker.verification.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.infotracker.verification.entity.Information;
import org.infotracker.verification.entity.Verification;
import org.infotracker.verification.dto.InformationResponse;
import org.infotracker.verification.dto.VerificationResponse;
import org.infotracker.verification.repository.VerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class VerificationServiceImpl implements VerificationService{
    private VerificationRepository repository;
    @Override
    public VerificationResponse verifyInformation(Information information) {
        log.info("Incoming Information ==> {}", information);

        if (information == null || information.getTitle() == null) {
            return buildVerificationResponse(false, null);
        }

        if (containsSpecialCharacters(information.getTitle())) {
            return buildVerificationResponse(false, information.getTitle());
        }

        return buildVerificationResponse(true, information.getTitle());
    }

    private boolean containsSpecialCharacters(String input) {
        return input.matches(".*[^a-zA-Z0-9 ].*");
    }

    private VerificationResponse buildVerificationResponse(boolean isVerified, String informationTitle) {
        Verification verification = Verification.builder()
                .informationTitle(informationTitle)
                .isVerified(isVerified)
                .verifiedAt(LocalDateTime.now())
                .build();
        log.info("Incoming Verification ==> {}", verification);

        repository.save(verification);
        return VerificationResponse.builder()
                .id(verification.getId())
                .informationTitle(verification.getInformationTitle())
                .isVerified(isVerified)
                .verifiedAt(LocalDateTime.now())
                .build();
    }
}
