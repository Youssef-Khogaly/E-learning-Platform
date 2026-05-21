package com.elearning.webhook.apiVideo.interfaces;

import jakarta.servlet.http.HttpServletRequest;

public interface IvideoWebhookValidator {
    public boolean validateRequest(String rawBody, HttpServletRequest request);
}
