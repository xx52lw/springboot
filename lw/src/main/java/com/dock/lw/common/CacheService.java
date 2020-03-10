package com.dock.lw.common;

import java.time.Duration;

public interface CacheService {

    void set(String key, Object value);

    void set(String key, Object value, long timeout);

    void set(String key, Object value, Duration timeout);

    Object get(String key);

    Boolean tryLock(String key);

    Boolean unLock(String key);

}
