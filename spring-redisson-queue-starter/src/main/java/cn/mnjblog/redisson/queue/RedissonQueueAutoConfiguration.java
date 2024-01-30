package cn.mnjblog.redisson.queue;

import cn.mnjblog.redisson.queue.handle.impl.RedissonDelayQueueHandler;
import cn.mnjblog.redisson.queue.handle.impl.RedissonQueueHandler;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列组件AutoConfiguration
 *
 * @author wuyajun
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedissonQueueAutoConfiguration {

    private final RedissonClient redissonClient;


    /**
     * 队列
     *
     * @return 普通队列执行器
     */
    @Bean
    public RedissonQueueHandler redissonQueueHandler() {
        return new RedissonQueueHandler(redissonClient);
    }

    /**
     * 延迟队列
     *
     * @return 延迟队列执行器
     */
    @Bean
    public RedissonDelayQueueHandler redissonDelayQueueHandler() {
        return new RedissonDelayQueueHandler(redissonClient);
    }

}
