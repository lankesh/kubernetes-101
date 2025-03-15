package com.example.aicallsimulator.service;

import org.springframework.stereotype.Service;
import com.example.aicallsimulator.model.CallRequest;
import com.example.aicallsimulator.model.CallResponse;

@Service
public class CallSimulationServiceImpl implements CallSimulationService {

    @Override
    public CallResponse generateSimulatedResponse(CallRequest callRequest) {
        // Simple implementation: return a predefined response based on caller input
        String aiResponse = "Hello, this is a simulated AI response.";
        if (callRequest.getCallerInput() != null && !callRequest.getCallerInput().isEmpty()) {
            aiResponse = "You said: " + callRequest.getCallerInput();
        }
        CallResponse response = new CallResponse();
        response.setAiResponse(aiResponse);
        return response;
    }
}
