package com.roy.redis;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author： roy
 * Description：
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicClientDemo {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test()
    {
        stringRedisTemplate.opsForValue().set("test3","楼兰");
        System.out.println(stringRedisTemplate.opsForValue().get("test3"));

        System.out.println(stringRedisTemplate.opsForValue().get("test2"));
    }

    @Test
    public void test2()
    {
        redisTemplate.opsForValue().set("test3","楼兰");
        System.out.println(redisTemplate.opsForValue().get("test3"));

        System.out.println(redisTemplate.opsForValue().get("test2"));
    }
}
