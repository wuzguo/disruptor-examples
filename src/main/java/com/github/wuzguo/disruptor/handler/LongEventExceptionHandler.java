
package com.github.wuzguo.disruptor.handler;

import com.lmax.disruptor.ExceptionHandler;

/**
 * TODO 异常处理 Handler
 *
 * @author wuzguo at 2017/5/24 16:46
 */
public class LongEventExceptionHandler implements ExceptionHandler {

    public void handleEventException(Throwable throwable, long l, Object o) {
        System.out.println("handleEventException...");
    }

    public void handleOnStartException(Throwable throwable) {
        System.out.println("handleOnStartException...");
    }

    public void handleOnShutdownException(Throwable throwable) {
        System.out.println("handleOnShutdownException...");
    }
}
