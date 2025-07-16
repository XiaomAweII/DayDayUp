package com.xushu.circulardependencies.One;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/***
 * @Author 徐庶
 * @Slogan 致敬大师，致敬未来的你
 */
@ComponentScan
@Configuration  // 配置类   spring.xml
public class RunMainSpring {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

		// spring容器加载
		 AnnotationConfigApplicationContext ioc =
                new AnnotationConfigApplicationContext(RunMainSpring.class);



		//  获取/创建   bean
		IBService bService =  (IBService) ioc.getBean("BService");

		bService.say();




	}


}
