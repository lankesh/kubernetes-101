package com.example.aicallsimulator.service;

import com.example.aicallsimulator.model.CallRequest;
import com.example.aicallsimulator.model.CallResponse;

public interface CallSimulationService {
    CallResponse generateSimulatedResponse(CallRequest callRequest);
}
