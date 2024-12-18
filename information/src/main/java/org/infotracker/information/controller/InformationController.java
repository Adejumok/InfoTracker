package org.infotracker.information.controller;

import lombok.AllArgsConstructor;
import org.infotracker.information.dto.InformationRequest;
import org.infotracker.information.dto.InformationResponse;
import org.infotracker.information.service.InformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/infoTracker/information/")
@AllArgsConstructor
public class InformationController {
    private InformationService informationService;

    @PostMapping("")
    public ResponseEntity<InformationResponse> addInformation(@RequestBody InformationRequest request){
        InformationResponse response = informationService.addInformation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{informationId}")
    public ResponseEntity<InformationResponse> getInformation(@PathVariable Long informationId){
        InformationResponse response = informationService.getInformation(informationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
