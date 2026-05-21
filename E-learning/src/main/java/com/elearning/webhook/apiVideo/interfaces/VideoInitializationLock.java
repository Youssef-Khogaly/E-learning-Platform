package com.elearning.webhook.apiVideo.interfaces;

import java.time.Duration;

public interface VideoInitializationLock {


    boolean tryAcquire(String vidId);
    boolean tryAcquire(String vidId, Duration ttl);
    void release(String vidId);

}
