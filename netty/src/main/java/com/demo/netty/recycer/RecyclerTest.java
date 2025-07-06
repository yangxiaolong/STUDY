package com.demo.netty.recycer;

import io.netty.util.Recycler;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.TimeUnit;

public class RecyclerTest {

    private static final Recycler<User> RECYCLER = new Recycler<>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    private record User(Recycler.Handle<User> handle) {
        public void recycle() {
            handle.recycle(this);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //同线程回收
        /*User user = RECYCLER.get();
        user.recycle();
        User user1 = RECYCLER.get();
        System.out.println(user == user1);*/

        //异线程回收
        User user = RECYCLER.get();
        new FastThreadLocalThread(() -> {
            user.recycle();
            System.out.println(11111111111L);
        }).start();
        TimeUnit.SECONDS.sleep(1);
        User user1 = RECYCLER.get();
        System.out.println(user == user1);
    }

}