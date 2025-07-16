package com.xushu.circulardependencies.singletonDemo;

// 1.但是synchronized的粒度太大了，即锁的临界区太大了，有点影响效率
// 2.虽然锁的粒度变小了，但线程不安全了
// 3. 第10行又加了一层if判断，也就是所谓的Double Check Lock
// 饿汉    懒汉
public class Singleton {

	// 大
	private static volatile Singleton singleton;


	public static  Singleton getSingleton() {

		// todo ...
		// todo ...

		// 锁粒度太大
		// 锁=多线程竞争资源   临界资源

		if(singleton==null){	// 1

			synchronized (singleton) {

				if(singleton==null){  // 2

					singleton = new Singleton();
					// singleton
					// new Singleton();
					// 赋值
				}

			}
		}

		return singleton;
	}

















	public static void main(String[] args) {
		for (int i=0;i<1000;i++){
			new Thread(()-> {
			/*	try {
					System.out.println(Singleton.getInstance().hashCode());
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}*/
			}).start();
		}
	}
}
