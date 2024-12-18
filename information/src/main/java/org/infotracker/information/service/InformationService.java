package org.infotracker.information.service;

import org.infotracker.information.dto.InformationRequest;
import org.infotracker.information.dto.InformationResponse;

public interface InformationService {
    InformationResponse addInformation(InformationRequest request);
    InformationResponse getInformation(Long informationId);
}
