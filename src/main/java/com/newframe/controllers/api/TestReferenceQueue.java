package com.newframe.controllers.api;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class TestReferenceQueue {

    public static void main(String[] args) {

        String s = new String();
        StringBuffer stringBuffer = new StringBuffer();
        Object counter = new Object();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        counter = 888;
        PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);

        System.gc();
        try {
            // Remove 是一个阻塞方法，可以指定 timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                // do something
            }
        } catch (InterruptedException e) {
            // Handle it
        }

        Integer.valueOf(231);


    }

}
