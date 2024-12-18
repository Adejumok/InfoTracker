package org.infotracker.verification.controller;

import lombok.AllArgsConstructor;
import org.infotracker.verification.dto.InformationResponse;
import org.infotracker.verification.dto.VerificationResponse;
import org.infotracker.verification.entity.Information;
import org.infotracker.verification.service.VerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/infoTracker/verification/")
@AllArgsConstructor
public class VerificationController {
    private VerificationService verificationService;

    @PostMapping("verifyInformation")
    public ResponseEntity<VerificationResponse> verifyInformation(@RequestBody Information information){
        VerificationResponse verificationResponse = verificationService.verifyInformation(information);
        return new ResponseEntity<>(verificationResponse, HttpStatus.OK);
    }
}
