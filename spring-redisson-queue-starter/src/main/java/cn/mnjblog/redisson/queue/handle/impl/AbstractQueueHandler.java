package cn.mnjblog.redisson.queue.handle.impl;


import cn.mnjblog.redisson.queue.handle.QueueHandler;
import cn.mnjblog.redisson.queue.message.DelayQueueMessage;
import cn.mnjblog.redisson.queue.message.QueueMessage;

/**
 * 队列执行抽象类
 *
 * @author wuyajun
 * @since 1.0.0
 */
public abstract class AbstractQueueHandler implements QueueHandler {

    @Override
    public <T> T getQueueMessage(String queueName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void addQueueMessage(QueueMessage<T> queueMessage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void addDelayQueueMessage(DelayQueueMessage<T> delayQueueMessage) {
        throw new UnsupportedOperationException();
    }
}
