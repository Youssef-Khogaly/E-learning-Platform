package com.elearning.webhook.apiVideo;

import com.elearning.webhook.apiVideo.interfaces.IvideoEncodedWebhookHandler;
import com.elearning.webhook.apiVideo.interfaces.IvideoHookConsumer;
import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class VideoHookConsumerImpl implements IvideoHookConsumer {

    private final ExecutorService executorService;
    private final IvideoEncodedWebhookHandler ivideoEncodedWebhookHandler;

    private static final int coreSize = 1;
    private static final int maxPoolSize = 2;
    private static final int keepAliveMilli = 10000;
    private static final int queueSize = 256;
    public VideoHookConsumerImpl(@Qualifier("qualityHookHandlerWithRetries") IvideoEncodedWebhookHandler ivideoEncodedWebhookHandler) {
        this.ivideoEncodedWebhookHandler = ivideoEncodedWebhookHandler;
        ThreadFactory threadFactory = (t) ->{
            var thread = new Thread(t);
            thread.setName("api.video quality hook worker");
            return thread;
        };
        this.executorService = new ThreadPoolExecutor(coreSize,maxPoolSize,keepAliveMilli
                , TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize) , threadFactory);
    }
    private Runnable getRunnable(VideoHookQualityEvent event)
    {
        return () -> ivideoEncodedWebhookHandler.handle(event);
    }
    @Override
    public void submit(VideoHookQualityEvent event) {
        var runnable = getRunnable(event);
        executorService.execute(runnable);
    }
}
