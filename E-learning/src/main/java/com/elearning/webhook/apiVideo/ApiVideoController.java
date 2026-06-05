package com.elearning.webhook.apiVideo;

import com.elearning.webhook.apiVideo.interfaces.IvideoEncodedWebhookHandler;
import com.elearning.webhook.apiVideo.interfaces.IvideoHookConsumer;
import com.elearning.webhook.apiVideo.interfaces.IvideoWebhookValidator;
import com.elearning.webhook.apiVideo.models.VideoEncodedRequest;
import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;
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

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/webhooks/")
@AllArgsConstructor
public class ApiVideoController {

    private final ObjectMapper objectMapper;
    private final IvideoWebhookValidator videoWebhookValidator;
    private final IvideoHookConsumer ivideoHookConsumer;

    @PostMapping("/video-encoded")
    ResponseEntity<Void> videoEncoded(@RequestBody  String rawBody , HttpServletRequest originalReq) throws JsonProcessingException {

        if(!videoWebhookValidator.validateRequest(rawBody,originalReq)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var reqDto = objectMapper.readValue(rawBody, VideoEncodedRequest.class);
        VideoHookQualityEvent event = VideoHookQualityEvent.builder()
                .webhookId(originalReq.getHeader("X-Api-Video-WebhookID"))
                .type(reqDto.type())
                .emittedAt(reqDto.emittedAt())
                .videoId(reqDto.videoId())
                .encoding(reqDto.encoding())
                .quality(reqDto.quality()).build();

        try{
            ivideoHookConsumer.submit(event);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return ResponseEntity.accepted().build();
    }
}
