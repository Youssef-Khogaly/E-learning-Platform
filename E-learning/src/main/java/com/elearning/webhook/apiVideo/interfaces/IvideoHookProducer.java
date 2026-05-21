package com.elearning.webhook.apiVideo.interfaces;

import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;

public interface IvideoHookProducer {


    public void publish(VideoHookQualityEvent videoHookQualityEvent);
}
