package com.example.core;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.model.StringInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StringUppercaseHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUppercaseHandler.class);
    private final ObjectMapper objectMapper;

    public StringUppercaseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent requestEvent) {
        LOGGER.info("Gateway Request : {}", requestEvent);
        try {
            StringInput stringInput = objectMapper.readValue(requestEvent.getBody(), StringInput.class);
            StringInput result = new StringInput(stringInput.input(), stringInput.input().toUpperCase());
            String output = objectMapper.writeValueAsString(result);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(output);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
