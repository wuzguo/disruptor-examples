
package com.github.wuzguo.disruptor.handler;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.TimeoutHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * TODO？
 *
 * @author wuzguo at 2017/5/24 17:12
 */
public class LongEventThenWorkHandler implements WorkHandler<LongEvent>, TimeoutHandler {
    /**
     * @param l
     * @throws Exception
     */
    public void onTimeout(final long l) throws Exception {
        System.out.println("[LongEventThenWorkHandler] longEvent then handler timeout");
    }

    public void onEvent(final LongEvent longEvent) throws Exception {
        if (longEvent == null) {
            throw new Exception("[LongEventThenWorkHandler] longEvent then is null");
        }

        System.out.println("[LongEventThenWorkHandler] longEvent then: " + longEvent);
    }
}
