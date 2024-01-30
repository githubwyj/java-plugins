package cn.mnjblog.redisson.queue.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 延迟队列消息
 *
 * @author wuyajun
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DelayQueueMessage<T> extends QueueMessage<T> {


    /**
     * 消息消费时间 单位秒
     */
    private Long delay;

}
