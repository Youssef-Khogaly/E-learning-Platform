package com.elearning.webhook.apiVideo;

import com.elearning.webhook.apiVideo.interfaces.VideoInitializationLock;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VideoInitializationLockImpl implements VideoInitializationLock {

    private final ConcurrentHashMap<String,Long> locksMap = new ConcurrentHashMap<>();
    @Override
    public boolean tryAcquire(String vidId) {
        return locksMap.putIfAbsent(vidId,Thread.currentThread().getId()) == null;
    }

    @Override
    public boolean tryAcquire(String vidId, Duration ttl) {
        throw new RuntimeException("ttl acquire is not supported");
    }

    @Override
    public void release(String vidId) {
        locksMap.remove(vidId,Thread.currentThread().getId());
    }
}
