
package com.github.wuzguo.disruptor.producer;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * TODO  Using version 3 Translators
 *
 * @author wuzguo at 2017/5/24 15:36
 */
public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
                    event.setValue(bb.getLong(0));
                }
            };

    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
