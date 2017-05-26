
package com.github.wuzguo.disruptor.handler;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * TODO 消费者
 *
 * @author wuzguo at 2017/5/24 15:05
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    /**
     * @param longEvent
     * @param l
     * @param b
     * @throws Exception
     */
    public void onEvent(final LongEvent longEvent, final long l, final boolean b) throws Exception {
        // 如果为空直接抛异常
        if (longEvent == null) {
            System.out.println(Thread.currentThread().getName() + ": longEvent is null, l: " + l);
            throw new Exception("longEvent is null");
        }

        // 具体的业务逻辑
        System.out.println(Thread.currentThread().getName() + ": longEvent: " + longEvent);
    }
}
