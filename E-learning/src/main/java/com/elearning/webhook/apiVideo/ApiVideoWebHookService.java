package com.elearning.webhook.apiVideo;

import com.elearning.VideoExternalService.ApiVideoService;
import com.elearning.VideoExternalService.ApiVideoUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import video.api.client.ApiVideoClient;
import video.api.client.api.ApiException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Objects;

@Service
public class ApiVideoWebHookService implements WebhookSecretProvider,IvideoEncodedWebhookHandler,IvideoWebhookValidator {

    private final ApiVideoService apiVideoService;
    private byte[] secCache = null;
    private final ApiVideoUtils apiVideoUtils;
    public ApiVideoWebHookService(ApiVideoService apiVideoService, ApiVideoUtils apiVideoUtils) {
        this.apiVideoService = apiVideoService;
        this.apiVideoUtils = apiVideoUtils;
    }

    @Override
    public void handle(VideoEncodedRequest requestDto) {

    }

    private byte[] hmacSha256(String data, byte[] sec)
    {
        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey = new SecretKeySpec(sec,"HmacSHA256");
            mac.init(secKey);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hash).getBytes(StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean validateRequest(String rawBody, HttpServletRequest request) {
        final String webhookId = request.getHeader("X-Api-Video-WebhookID");
        final String expectedSignature = request.getHeader("X-Api-Video-Signature");
        if(webhookId == null || webhookId.isBlank())
            return false;
        if(expectedSignature == null || expectedSignature.isBlank())
            return false;

        byte[] actualSignature= hmacSha256(rawBody,getSec(webhookId));

        return MessageDigest.isEqual(expectedSignature.getBytes(StandardCharsets.UTF_8),actualSignature);
    }

    @Override
    public byte[] getSec(String webhookId) {
        if(secCache != null)
            return secCache;

        return secCache = Objects.requireNonNull(apiVideoUtils.rateLimitRetry(() -> apiVideoService.getWebHook(webhookId)).getSignatureSecret()).getBytes(StandardCharsets.UTF_8);
    }
}
