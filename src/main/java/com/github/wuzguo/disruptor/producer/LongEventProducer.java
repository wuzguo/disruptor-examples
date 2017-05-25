
package com.github.wuzguo.disruptor.producer;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * TODO 生产者
 *
 * @author wuzguo at 2017/5/24 15:36
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor
            LongEvent event = ringBuffer.get(sequence);
            // for the sequence
            event.setValue(byteBuffer.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
