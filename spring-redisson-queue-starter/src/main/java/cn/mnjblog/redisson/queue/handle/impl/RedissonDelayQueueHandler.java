package cn.mnjblog.redisson.queue.handle.impl;

import cn.mnjblog.redisson.queue.message.DelayQueueMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonShutdownException;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * 延迟队列执行器
 *
 * @author wuyajun
 * @since  1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class RedissonDelayQueueHandler extends AbstractQueueHandler {

    private final RedissonClient redissonClient;

    @Override
    public <T> T getQueueMessage(String queueName) {
        try {
            RBlockingDeque<T> blockingDeque = redissonClient.getBlockingDeque(queueName);
            redissonClient.getDelayedQueue(blockingDeque);
            return blockingDeque.take();
        } catch (RedissonShutdownException e) {
            log.debug(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Override
    public <T> void addDelayQueueMessage(DelayQueueMessage<T> delayQueueMessage) {
        try {
            RBlockingDeque<T> blockingDeque = redissonClient.getBlockingDeque(delayQueueMessage.getQueue());
            RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
            if (delayedQueue.contains(delayQueueMessage.getMessage())) {
                log.info("queue record exist,queue：{},uuid：{}", delayQueueMessage.getQueue(), delayQueueMessage.getMessage());
                return;
            }
            delayedQueue.offer(delayQueueMessage.getMessage(), delayQueueMessage.getDelay(), TimeUnit.SECONDS);
            log.debug("添加消息到队列，队列键：{}，延迟时间：{}", delayQueueMessage.getQueue(), delayQueueMessage.getDelay() + "秒");
        } catch (Exception e) {
            log.error("添加消息到队列失败", e);
        }
    }

}
