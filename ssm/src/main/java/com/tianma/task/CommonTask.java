package com.tianma.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zuowenxia on 2017/4/27.
 */
@Component
@Scope("prototype")
public class CommonTask implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        System.out.println("common task running!!");
    }
}