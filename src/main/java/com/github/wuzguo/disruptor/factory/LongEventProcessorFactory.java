
package com.github.wuzguo.disruptor.factory;

import com.github.wuzguo.disruptor.event.LongEvent;
import com.github.wuzguo.disruptor.handler.LongEventUpdateHandler;
import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.EventProcessorFactory;

/**
 * TODO？
 *
 * @author wuzguo at 2017/5/24 18:00
 */
public class LongEventProcessorFactory implements EventProcessorFactory<LongEvent> {

    private Disruptor<LongEvent> disruptor;

    private EventHandlerGroup<LongEvent> handlerGroup;

    /**
     * @param disruptor
     * @param handlerGroup
     */
    public LongEventProcessorFactory(final Disruptor<LongEvent> disruptor, final EventHandlerGroup<LongEvent> handlerGroup) {
        this.disruptor = disruptor;
        this.handlerGroup = handlerGroup;
    }

    /**
     * @param ringBuffer
     * @param sequences
     * @return
     */
    public EventProcessor createEventProcessor(final RingBuffer<LongEvent> ringBuffer, final Sequence[] sequences) {

        BatchEventProcessor<LongEvent> eventProcessor = new BatchEventProcessor<LongEvent>(
                disruptor.getRingBuffer(),
                handlerGroup.asSequenceBarrier(),
                new LongEventUpdateHandler());

        disruptor.getRingBuffer().addGatingSequences(eventProcessor.getSequence());

        return eventProcessor;
    }
}
