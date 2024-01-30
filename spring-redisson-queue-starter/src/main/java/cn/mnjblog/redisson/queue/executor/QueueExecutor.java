package cn.mnjblog.redisson.queue.executor;

/**
 * 队列执行器
 *
 * @author wuyajun
 * @since  1.0.0
 */
public interface QueueExecutor<T> {

    /**
     * 执行方法
     *
     * @param t 参数
     */
    void execute(T t);

}
