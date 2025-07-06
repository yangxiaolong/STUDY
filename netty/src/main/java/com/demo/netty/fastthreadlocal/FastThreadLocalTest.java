package com.demo.netty.fastthreadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

public class FastThreadLocalTest {

    static FastThreadLocal<Object> threadLocal = new FastThreadLocal<>() {
        @Override
        protected Object initialValue() throws Exception {
            return new Object();
        }
    };

    public static void main(String[] args) {
        new Thread(() -> {
            Object object = threadLocal.get();
            System.out.println(object);
        }).start();
        new FastThreadLocalThread(() -> {
            Object object = threadLocal.get();
            System.out.println(object);
        }).start();
    }

}
