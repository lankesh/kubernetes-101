package com.example.aicallsimulator.controller;

import org.springframework.web.bind.annotation.*;
import com.example.aicallsimulator.model.CallRequest;
import com.example.aicallsimulator.model.CallResponse;
import com.example.aicallsimulator.service.CallSimulationService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class CallSimulationController {

    @Autowired
    private CallSimulationService callSimulationService;

    @PostMapping("/simulateCall")
    public CallResponse simulateCall(@RequestBody CallRequest callRequest) {
        return callSimulationService.generateSimulatedResponse(callRequest);
    }
}
