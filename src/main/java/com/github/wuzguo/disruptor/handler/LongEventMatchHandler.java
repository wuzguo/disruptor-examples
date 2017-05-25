
package com.github.wuzguo.disruptor.handler;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.TimeoutHandler;

/**
 * TODO？
 *
 * @author wuzguo at 2017/5/24 17:30
 */
public class LongEventMatchHandler implements EventHandler<LongEvent>, TimeoutHandler {
    public void onTimeout(long l) throws Exception {
        System.out.println("[LongEventMatchHandler] onTimeout...");
    }

    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        if (longEvent == null) {
            throw new Exception("[LongEventMatchHandler] longEvent is null");
        }

        System.out.println("[LongEventMatchHandler] longEvent: " + longEvent);

    }
}
