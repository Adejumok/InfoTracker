package org.infotracker.information.service;

import lombok.extern.slf4j.Slf4j;
import org.infotracker.information.Information;
import org.infotracker.information.dto.InformationRequest;
import org.infotracker.information.dto.InformationResponse;
import org.infotracker.information.dto.VerificationResponse;
import org.infotracker.information.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class InformationServiceImpl implements InformationService{
    private final InformationRepository repository;

    @Value("${verification-service.base-url}")
    private String verificationUrl;

    public InformationServiceImpl(InformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public InformationResponse addInformation(InformationRequest request) {
        log.info("incoming info ====> {}", request);

        Information information = Information.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
//        repository.save(information);
        log.info("checking info....... {}", information);
        checkInformationVerificationStatus(information);

        return InformationResponse.builder()
                .id(information.getId())
                .title(information.getTitle())
                .description(information.getDescription())
                .isVerified(information.isVerified())
                .build();
    }
    private void checkInformationVerificationStatus(Information information) {
        String url = verificationUrl + "/verification/verifyInformation";
        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);

//        HttpEntity<Information> entity = new HttpEntity<>(information, headers);
//        ResponseEntity<VerificationResponse> informationResponse = restTemplate.exchange(
//                url, HttpMethod.GET, entity, VerificationResponse.class);

        ResponseEntity<VerificationResponse> informationResponse = restTemplate.postForEntity(url, information, VerificationResponse.class);

        VerificationResponse result = informationResponse.getBody();

        boolean isVerified = informationResponse.getStatusCode() == HttpStatus.OK && result != null && result.isVerified();
        information.setVerified(isVerified);
        repository.save(information);
    }

    @Override
    public InformationResponse getInformation(Long informationId) {
        Optional<Information> optionalInformation = repository.findById(informationId);
        Information information = optionalInformation.get();
        return InformationResponse.builder()
                .id(information.getId())
                .title(information.getTitle())
                .description(information.getDescription())
                .build();
    }
}
