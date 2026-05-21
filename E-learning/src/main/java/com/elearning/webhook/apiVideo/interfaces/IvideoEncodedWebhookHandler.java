package com.elearning.webhook.apiVideo.interfaces;

import com.elearning.webhook.apiVideo.models.VideoEncodedRequest;
import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;

public interface IvideoEncodedWebhookHandler {

    void handle(VideoHookQualityEvent event);
}
