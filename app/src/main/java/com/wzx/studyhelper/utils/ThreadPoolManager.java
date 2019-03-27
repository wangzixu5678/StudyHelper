package com.wzx.studyhelper.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private static ExecutorService cachedThreadPool ;

    public synchronized static ExecutorService getInstance(){
        if (cachedThreadPool==null){
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        return cachedThreadPool;
    }
}
