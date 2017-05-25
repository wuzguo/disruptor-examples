
package com.github.wuzguo.disruptor;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.github.wuzguo.disruptor.factory.LongEventFactory;
import com.github.wuzguo.disruptor.handler.ClearingEventHandler;
import com.github.wuzguo.disruptor.handler.LongEventExceptionHandler;
import com.github.wuzguo.disruptor.handler.LongEventHandler;
import com.github.wuzguo.disruptor.producer.LongEventProducerWithTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TODO 启动类
 *
 * @author wuzguo at 2017/5/24 15:39
 */
public class LongEventMain {
    public static void main(final String[] args) throws Exception {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024 * 4;

        // Construct the Disruptor
        //  Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler()).then(new ClearingEventHandler());
        disruptor.setDefaultExceptionHandler(new LongEventExceptionHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // LongEventProducer producer = new LongEventProducer(ringBuffer);
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            buffer.putLong(0, l);
            producer.onData(buffer);
            Thread.sleep(1000);
        }
    }
}
