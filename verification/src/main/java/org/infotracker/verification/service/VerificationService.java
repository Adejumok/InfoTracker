package org.infotracker.verification.service;

import org.infotracker.verification.dto.VerificationResponse;
import org.infotracker.verification.entity.Information;

public interface VerificationService {
    VerificationResponse verifyInformation(Information information);
}
