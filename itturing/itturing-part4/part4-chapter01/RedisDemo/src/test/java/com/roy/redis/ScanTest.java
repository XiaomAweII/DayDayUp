package com.roy.redis;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author： roy
 * Description：
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ScanTest {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Test
    public void initData(){
        RedisScript<String> redisScript = RedisScript.of("for i = 1,30,1 do redis.call('SET','k'..tostring(i),'v'..tostring(i)) end return 'OK'", String.class);
        String result = redisTemplate.execute(redisScript, null);
        System.out.println("initData res:"+result);
    }

    @Test
    public void scanTest(){
        ScanOptions scanOptions = ScanOptions.scanOptions().match("k*").count(4).build();
        try (Cursor<String> cursor = redisTemplate.scan(scanOptions)) {
            System.out.println("position ==> "+cursor.getPosition());
//            cursor.forEachRemaining(System.out::println);
            while(cursor.hasNext()){
                System.out.println("record ==>"+cursor.next());
            }
        }
    }
}
