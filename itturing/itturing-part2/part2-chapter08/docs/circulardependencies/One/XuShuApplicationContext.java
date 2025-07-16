package com.xushu.circulardependencies.One;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
public class XuShuApplicationContext  {


	//
    private Map<String, BeanDefinition> beanDefinitionMap = new LinkedHashMap<>(256);

	// 一级缓存 单例
	private   final  Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

	// 二级缓存  创建时不完整的bean   提升性能
	private   final  Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(256);


	// 三级缓存   提升了扩展性   保证bean规范
	private   final  Map<String, ObjectFactory> factoriesEarlySingletonObjects = new ConcurrentHashMap<>(256);



	public XuShuApplicationContext() throws Exception {

        // 加载ioc容器
        refresh();
    }

	// ioc容器加载
    private void refresh() throws Exception {

        // todo 解析配置

		// 注册BeanDefinition
        loadBeanDefinitions();

		// 创建所有的单例bean
		finishBeanFactoryInitialization();
    }

	private void finishBeanFactoryInitialization() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// todo.  bean加载
		for (String beanName : beanDefinitionMap.keySet()) {
			getBean(beanName);
		}

	}

	// 创建一个个的bean
	public   Object  getBean(String beanName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

		// 获取bean   加锁   影响性能  已经创建好的bean也阻塞
		Object bean=getSingleton(beanName);

		if(bean!=null){
			return bean;
		}


		return createBean(beanName);

	}

	private Object createBean(String beanName) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {


		synchronized (singletonObjects) {

			// 第二遍检测
			if (singletonObjects.containsKey(beanName)) {
				return singletonObjects.get(beanName);
			}

			// 1.实例化
			RootBeanDefinition rootBeanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
			Class<?> beanClass = rootBeanDefinition.getBeanClass();
			Object bean = beanClass.getConstructor().newInstance();


			Object singleton=bean;
			factoriesEarlySingletonObjects.put(beanName,() -> {
				return new JdkProxyBeanPostProcessor().getEarlyBeanReference(singleton, beanName);
			});
			// aop 动态代理   判断只有循环依赖才创建aop     不是循环
			// earlySingletonObjects.put(beanName, aopBean);

			// 2.属性注入
			for (Field declaredField : beanClass.getDeclaredFields()) {
				// 说明属性有@Autowired
				if (declaredField.getAnnotation(Autowired.class) != null) {

					// byname
					String dependName = declaredField.getName();
					Object dependBean = getBean(dependName);

					declaredField.setAccessible(true);
					declaredField.set(bean, dependBean);

				}
			}

			// 初始化...


			// 为了防止 在循环依赖中改变了bean
			// new A = 二级缓存proxy

			Object beanAop = getSingleton(beanName);
			if(beanAop !=null){
				bean=beanAop;
			}

			// 一级
			singletonObjects.put(beanName, bean);
			earlySingletonObjects.remove(beanName);
			factoriesEarlySingletonObjects.remove(beanName);
			return bean;
		}

	}

	// 获取bean
	private Object getSingleton(String beanName) {

		if (singletonObjects.containsKey(beanName)) {
			return singletonObjects.get(beanName);
		}

		synchronized (singletonObjects) {
			// 二级缓存
			if (earlySingletonObjects.containsKey(beanName)) {
				return earlySingletonObjects.get(beanName);
			}

			// 出口
			if (factoriesEarlySingletonObjects.containsKey(beanName)) {
				ObjectFactory objectFactory = factoriesEarlySingletonObjects.get(beanName);
				Object aopbean = objectFactory.getObject();

				earlySingletonObjects.put(beanName, aopbean);
				return  aopbean;

			}
			return null;
		}
	}
		/**
		 *
		 *  根据配置信息创建BeanDefinition  底层是通过解析配置类注册beandefinition
		 */
	private void loadBeanDefinitions() {
		// 创建A    BeanDefinition
		RootBeanDefinition aBeanDefinition = new RootBeanDefinition(AService.class);
		// 创建B    BeanDefinition
		RootBeanDefinition bBeanDefinition = new RootBeanDefinition(BService.class);
		beanDefinitionMap.put("aService", aBeanDefinition);
		beanDefinitionMap.put("bService", bBeanDefinition);
	}


}
