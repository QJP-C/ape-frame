package com.qjp.redis.exception;

/**
 * 自定义的锁异常
 */
public class ShareLockException extends RuntimeException{

    public ShareLockException(String message){
        super(message);
    }
}
