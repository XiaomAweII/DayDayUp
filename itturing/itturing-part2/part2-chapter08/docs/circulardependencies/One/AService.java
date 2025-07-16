package com.xushu.circulardependencies.One;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xsls
 *
 */
@Component
public class AService implements IAService, InitializingBean {

	@Autowired
    private IBService bService;




	public AService() {
		System.out.println("创建A");
	}

	@Override
	public void say() {
		System.out.println("I'm A， My B is"+bService.toString());
	}



    @Override
    public String toString() {
        return "InstanceA{" +
                "instanceB=" + bService +
                '}';
    }


	@Override
	public void afterPropertiesSet() throws Exception {
		// todo 初始化

	}
}
