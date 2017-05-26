
package com.github.wuzguo.disruptor;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.github.wuzguo.disruptor.factory.LongEventFactory;
import com.github.wuzguo.disruptor.factory.LongEventProcessorFactory;
import com.github.wuzguo.disruptor.handler.*;
import com.github.wuzguo.disruptor.producer.LongEventProducerWithTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TODO 加强版启动类
 *
 * @author wuzguo at 2017/5/24 17:15
 */
public class LongEventWorkHandlerMain {

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
        disruptor.setDefaultExceptionHandler(new LongEventExceptionHandler());

        // 模拟消费者的三个状态，并行->串行->并行
        EventHandlerGroup<LongEvent> handlerGroup = disruptor.
                handleEventsWithWorkerPool(new LongEventWorkHandler(),
                        new LongEventWorkHandler(),
                        new LongEventWorkHandler()).
                then(new LongEventMatchHandler()).
                thenHandleEventsWithWorkerPool(new LongEventThenWorkHandler(),
                        new LongEventThenWorkHandler(),
                        new LongEventThenWorkHandler());

        // Set up custom event processors to handle events from the ring buffer.
        handlerGroup.then(new LongEventProcessorFactory(disruptor, handlerGroup))
                // Clearing Objects From the Ring Buffer
                .then(new ClearingEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // LongEventProducer producer = new LongEventProducer(ringBuffer);
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

        // 生产数据
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            buffer.putLong(0, l);
            producer.onData(buffer);
            Thread.sleep(1000);
        }
    }
}
