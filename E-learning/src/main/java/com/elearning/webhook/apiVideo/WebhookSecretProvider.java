package com.elearning.webhook.apiVideo;


interface WebhookSecretProvider {

    byte[] getSec(String webhookId);
}
