package cn.mnjblog.redisson.queue.handle;


import cn.mnjblog.redisson.queue.message.DelayQueueMessage;
import cn.mnjblog.redisson.queue.message.QueueMessage;

/**
 * 队列处理接口
 *
 * @author wuyajun
 * @since 1.0.0
 */
public interface QueueHandler {

    /**
     * 获取队列消息
     *
     * @param queueName 队列名称
     * @param <T>       消息
     * @return 需要执行的队列消息
     */
    <T> T getQueueMessage(String queueName);

    /**
     * 添加消息到队列
     *
     * @param queueMessage 队列消息
     * @param <T>          消息
     */
    <T> void addQueueMessage(QueueMessage<T> queueMessage);

    /**
     * 添加消息到队列
     *
     * @param delayQueueMessage 延迟队列消息
     * @param <T>               消息
     */
    <T> void addDelayQueueMessage(DelayQueueMessage<T> delayQueueMessage);

}
