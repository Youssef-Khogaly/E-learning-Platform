package com.elearning.webhook.apiVideo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vids/webhooks/")
@AllArgsConstructor
public class ApiVideoController {

    private final ObjectMapper objectMapper;
    private final IvideoWebhookValidator videoWebhookValidator;
    private final IvideoEncodedWebhookHandler videoEncodedWebhookHandler;

    @PostMapping("/video-encoded")
    ResponseEntity<Void> videoEncoded(@RequestBody  String rawBody , HttpServletRequest originalReq) throws JsonProcessingException {

        if(!videoWebhookValidator.validateRequest(rawBody,originalReq)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var reqDto = objectMapper.readValue(rawBody,VideoEncodedRequest.class);
        videoEncodedWebhookHandler.handle(reqDto);
        return ResponseEntity.accepted().build();
    }
}
