package com.elearning.webhook.apiVideo;

import jakarta.servlet.http.HttpServletRequest;

public interface IvideoWebhookValidator {
    public boolean validateRequest(String rawBody, HttpServletRequest request);
}
