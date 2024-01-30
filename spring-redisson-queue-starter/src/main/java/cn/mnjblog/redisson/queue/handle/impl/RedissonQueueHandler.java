package cn.mnjblog.redisson.queue.handle.impl;

import cn.mnjblog.redisson.queue.message.QueueMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonShutdownException;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;

/**
 * 队列执行器
 *
 * @author wuyajun
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class RedissonQueueHandler extends AbstractQueueHandler {

    private final RedissonClient redissonClient;

    @Override
    public <T> T getQueueMessage(String queueName) {
        try {
            RBlockingDeque<T> blockingDeque = redissonClient.getBlockingDeque(queueName);
            return blockingDeque.take();
        } catch (RedissonShutdownException e) {
            log.debug(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Override
    public <T> void addQueueMessage(QueueMessage<T> queueMessage) {
        try {
            RBlockingDeque<T> blockingDeque = redissonClient.getBlockingDeque(queueMessage.getQueue());
            if (blockingDeque.contains(queueMessage.getMessage())) {
                log.info("queue record exist,queue：{},uuid：{}", queueMessage.getQueue(), queueMessage.getMessage());
                return;
            }
            blockingDeque.add(queueMessage.getMessage());
            log.debug("添加消息到队列，队列键：{}", queueMessage.getQueue());
        } catch (Exception e) {
            log.error("添加消息到队列失败", e);
        }
    }
}
