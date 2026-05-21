package com.elearning.webhook.apiVideo.interfaces;

import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;

import java.util.concurrent.RejectedExecutionException;

public interface IvideoHookConsumer {


    public void submit(VideoHookQualityEvent event) throws RejectedExecutionException;
}
