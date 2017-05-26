
package com.github.wuzguo.disruptor.handler;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * TODO 清理操作 Handler
 *
 * @author wuzguo at 2017/5/24 15:52
 */
public class ClearingEventHandler implements EventHandler<LongEvent> {
    /**
     * @param longEvent
     * @param l
     * @param b
     * @throws Exception
     */
    public void onEvent(final LongEvent longEvent, final long l, final boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName() + ": [ClearingEventHandler] longEvent: " + longEvent);
        longEvent.clear();
    }
}
