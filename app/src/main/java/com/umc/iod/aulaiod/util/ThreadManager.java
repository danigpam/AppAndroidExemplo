package com.umc.iod.aulaiod.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadManager {

    private static Executor executor;
    public static final int MAX_NUMBER_OF_THREADS = 4;

    public static Executor getExecutor() {
        if (executor == null) {
            executor = Executors.newFixedThreadPool(MAX_NUMBER_OF_THREADS);
        }
        return executor;
    }
}
