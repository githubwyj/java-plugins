package cn.mnjblog.redisson.queue.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 队列消息
 *
 * @author wuyajun
 * @since 1.0.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage<T> {

    /**
     * 队列
     */
    private String queue;

    /**
     * 消息
     */
    private T message;


}
