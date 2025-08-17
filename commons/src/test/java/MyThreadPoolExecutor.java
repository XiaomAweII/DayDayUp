import java.util.concurrent.*;


public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    private ThreadLocalContext threadLocalContext;

    public MyThreadPoolExecutor(ThreadLocalContext context, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.threadLocalContext = context;
    }

    public MyThreadPoolExecutor(ThreadLocalContext context, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                ThreadFactory threadFactory,
                                RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.threadLocalContext = context;
    }

    @Override
    public void execute(Runnable command) {
        super.execute(this.new RunnableWrap(command));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(this.new CallableWrap(task));
    }

    /**
     * 线程本地变量上下文接口，用于解决线程池中共享外部线程ThreadLocal数据的问题
     *
     * @param <T>
     */
    public interface ThreadLocalContext<T> {
        /**
         * 获取线程池中需要共享的上下文对象，将任务交给线程时会被调用
         *
         * @return
         */
        T getContext();

        /**
         * 设置上下文，线程池中的线程执行任务的时候会调用
         *
         * @param context {@link #getContext()} 返回的对象
         */
        void setContext(T context);
    }

    private class CallableWrap<V> implements Callable<V> {

        private Callable<V> target;

        private Object context;

        public CallableWrap(Callable<V> target) {
            this.target = target;
            this.context = MyThreadPoolExecutor.this.threadLocalContext.getContext();
        }

        @Override
        public V call() throws Exception {
            MyThreadPoolExecutor.this.threadLocalContext.setContext(this.context);
            return this.target.call();
        }
    }


    private class RunnableWrap implements Runnable {

        private Runnable target;

        private Object context;

        public RunnableWrap(Runnable target) {
            this.target = target;
            this.context = MyThreadPoolExecutor.this.threadLocalContext.getContext();
        }

        @Override
        public void run() {
            MyThreadPoolExecutor.this.threadLocalContext.setContext(this.context);
            this.target.run();
        }
    }
}