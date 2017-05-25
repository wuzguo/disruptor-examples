
package com.github.wuzguo.disruptor.handler;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.TimeoutHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * TODO？
 *
 * @author wuzguo at 2017/5/24 17:05
 */
public class LongEventWorkHandler implements WorkHandler<LongEvent>, TimeoutHandler {
    /**
     * 消费者实现类
     *
     * @param longEvent
     * @throws Exception
     */
    public void onEvent(final LongEvent longEvent) throws Exception {
        if (longEvent == null) {
            throw new Exception("[LongEventWorkHandler] longEvent is null");
        }

        System.out.println("[LongEventWorkHandler] longEvent: " + longEvent);
    }

    public void onTimeout(final long l) throws Exception {
        System.out.println("[LongEventWorkHandler] longEvent handler timeout");
    }
}
