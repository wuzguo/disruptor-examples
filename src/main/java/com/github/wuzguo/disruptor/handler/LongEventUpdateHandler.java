
package com.github.wuzguo.disruptor.handler;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.TimeoutHandler;

/**
 * TODO？
 *
 * @author wuzguo at 2017/5/24 17:32
 */
public class LongEventUpdateHandler implements EventHandler<LongEvent>, TimeoutHandler {
    /**
     * @param longEvent
     * @param l
     * @param b
     * @throws Exception
     */
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        if (longEvent == null) {
            throw new Exception("[LongEventUpdateHandler] longEvent is null");
        }

        System.out.println(Thread.currentThread().getName() + ": [LongEventUpdateHandler] longEvent: " + longEvent);

    }

    public void onTimeout(long l) throws Exception {
        System.out.println(Thread.currentThread().getName() + ": [LongEventUpdateHandler] longEvent handler timeout");
    }
}
