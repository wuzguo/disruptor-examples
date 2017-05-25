
package com.github.wuzguo.disruptor.event;

/**
 * TODO 具体任务
 *
 * @author wuzguo at 2017/5/24 15:04
 */
public class LongEvent {

    private long value;

    public LongEvent() {
    }

    public void clear() {
        System.out.println("LongEvent .. clear..");
        this.value = 0L;
    }

    public LongEvent(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
