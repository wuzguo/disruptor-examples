
package com.github.wuzguo.disruptor.factory;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * TODO 事件的工厂
 *
 * @author wuzguo at 2017/5/24 15:04
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    /**
     * @return
     */
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
