package com.elearning.webhook.apiVideo.interfaces;


public interface WebhookSecretProvider {

    byte[] getSec(String webhookId);
}
