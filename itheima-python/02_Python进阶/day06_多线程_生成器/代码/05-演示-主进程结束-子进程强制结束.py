"""
案例: 演示 主进程执行完毕后, 子进程会被同步关闭.

当 主进程结束的时候, 如果想让 子进程同步结束, 方式如下:
    方式1: 设置子进程为 守护进程.               推荐
        格式:
            子进程名.daemon = True
        特点:
            当非守护进程关闭的时候, 它的守护进程也会被同步关闭.
    方式2: "强制"销毁子进程.
        可能会出现问题, 子进程会变成 "僵尸进程", 即: 资源不会被释放, 而是交由init进程来维护管理, 在合适的时机方式资源.
            子进程 => main进程
            僵尸进程 => init进程

"""

# 需求: 创建1个子进程, 执行完大概需要2秒.  而主进程执行完需要1秒. 实现该需求, 观察结果.
# 导包
import multiprocessing, time

# 1. 定义函数work(), 表示: 子进程关联的函数.
def work():
    for i in range(10):
        print(f'work {i}...')
        time.sleep(0.2)  # 总执行时长: 0.2秒 * 10 = 2秒


# 在main方法中测试.
if __name__ == '__main__':
    # 2. 创建(子)进程, 关联: work()函数, 执行需要 2秒.
    p1 = multiprocessing.Process(target=work)
    # 方式1: 设置子进程为: 守护进程.  当非守护进程(main)结束的时候, 守护进程也会同步结束.
    # p1.daemon = True
    # 3. 启动 子进程.
    p1.start()

    # 4. 休眠1秒, 表示: 主进程执行需要 1 秒.
    time.sleep(1)

    # 方式2: 销毁子进程.
    p1.terminate()  # 强制销毁子进程, 它会变成"僵尸进程", 关闭了, 但是资源没有释放.

    # 5. 打印: 主进程执行结束.
    print('main进程(主进程)执行结束!')
