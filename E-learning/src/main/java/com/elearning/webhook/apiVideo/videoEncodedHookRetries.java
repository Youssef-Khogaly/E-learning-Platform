package com.elearning.webhook.apiVideo;

import com.elearning.VideoExternalService.Exceptions.ApiVideoUnavailableException;
import com.elearning.webhook.apiVideo.interfaces.IvideoEncodedWebhookHandler;
import com.elearning.webhook.apiVideo.models.VideoHookQualityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("qualityHookHandlerWithRetries")
@Slf4j
public class videoEncodedHookRetries implements IvideoEncodedWebhookHandler {

    private final IvideoEncodedWebhookHandler ivideoEncodedWebhookHandler;
    private static final int MAX_RETRIES = 5;
    public videoEncodedHookRetries(@Qualifier("qualityHookHandlerImpl") IvideoEncodedWebhookHandler ivideoEncodedWebhookHandler) {
        this.ivideoEncodedWebhookHandler = ivideoEncodedWebhookHandler;
    }

    private void finalTryFailLog(VideoHookQualityEvent event , Exception exception)
    {
        log.error("handling event failed, {}" , event,exception);
    }
    @Override
    public void handle(VideoHookQualityEvent event) {

        Exception lastException = null;
        for(int curr = 1 ; curr <= MAX_RETRIES ; ++curr)
        {
            try {
                ivideoEncodedWebhookHandler.handle(event);
                return;
            } catch (CannotAcquireLockException e) {
                lastException = e;
                if(curr == MAX_RETRIES)
                    break;
                try{
                    Thread.sleep(200 * curr,0);
                } catch (InterruptedException ex) {
                    log.error("handle video quality webhook interrupted" , ex);
                    lastException = ex;
                    break;
                }

            } catch (ApiVideoUnavailableException  e){
                lastException = e;
                if(curr == MAX_RETRIES)
                    break;
                try{
                    Thread.sleep(2000 * curr,0);
                } catch (InterruptedException ex) {
                    log.error("handle video quality webhook interrupted" , ex);
                    lastException = ex;
                    break;
                }

            } catch (RuntimeException e) {
                lastException = e;
                if(curr == MAX_RETRIES)
                    break;
                try{
                    Thread.sleep(100 * curr,0);
                } catch (InterruptedException ex) {
                    log.error("handle video quality webhook interrupted" , ex);
                    lastException = ex;
                    break;
                }
            }
        }

        finalTryFailLog(event,lastException);

    }
}
