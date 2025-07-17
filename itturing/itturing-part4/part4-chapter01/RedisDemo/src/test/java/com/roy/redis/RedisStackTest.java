package com.roy.redis;

import jakarta.annotation.Resource;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： roy
 * Description：
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisStackTest {

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    List<String> keys = List.of("a-bf");


    @Test
    public void LuaTest(){
        String luaScript = """
                local key = KEYS[1]
                redis.call('BF.RESERVE', key, '0.01','1000','NONSCALING')
                return 'OK'
                """;

//        String luaScript = """
//                local key = KEYS[1]
//                local value = ARGV[1]
////                redis.call('SET', key, value)
//                redis.call('BF.RESERVE', key)
////                redis.log(redis.LOG_DEBUG,'11111')
//                return 'OK'
//                """;
//        RedisScript<String> script = new DefaultRedisScript<>(luaScript,String.class);
        //key必须不存在。
        String execute = redisTemplate.execute(new DefaultRedisScript<>(luaScript,String.class),
                List.of("bfKey"),
                "");
        System.out.println(execute);
    }


    @Test
    public void jsonTest() throws JSONException {
        String luaScript= """
                for i , key in ipairs(KEYS) do
                    local result = redis.call("JSON.SET", ARGV[i],"$.",key)
                    print("SET JSON RESULT:" .. result)
                end
                return 10
                """;

        RedisScript<Long> script = new DefaultRedisScript<>(luaScript,Long.class);
        List<String> keys = new ArrayList<>();
        List<String> args = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",i);
            jsonObject.put("name","product "+i);
            jsonObject.put("price",i*500);

            keys.add(jsonObject.toString());
            args.add("Product:"+i);
        }


        Long execute = redisTemplate.execute(script, keys, args);
        System.out.println(execute);
    }

    @Test
    public void createBloomFilter(){
        if(!redisTemplate.hasKey("a-bf")){
            try{
                String createFilterScriptText= """
                     return redis.call('BF.RESERVE', KEYS[1], '0.01','1000','NONSCALING')
                """;
                DefaultRedisScript<String> redisScript = new DefaultRedisScript<>(createFilterScriptText, String.class);
                String execute = redisTemplate.execute(redisScript,keys);
                System.out.println("CREATE BF:"+execute);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("COMMAND NOT SUPPORT");
            }

        }else{
            System.out.println("BF KEY is already exists");
        }
    }

    @Test
    public void addData(){
        if(!redisTemplate.hasKey("a-bf")){
            System.out.println("BF KEY is not exists");
        }else{
            try{
                String[] args = new String[]{"A","B","C","D","E","F","G"};
                String addDataScriptText= """
                for i,arg in ipairs(ARGV) do
                    local addRes = redis.call('BF.ADD',KEYS[1],arg)
                end
                return 'OK'
                """;
                DefaultRedisScript<String> redisScript = new DefaultRedisScript<>(addDataScriptText, String.class);
                System.out.println("ADDDATA BF:"+redisTemplate.execute(redisScript,  keys, args));
            }catch (Exception e){
//                e.printStackTrace();
                System.out.println("COMMAND NOT SUPPORTED");
            }
        }
    }

    @Test
    public void checkData(){
        if(!redisTemplate.hasKey("a-bf")){
            System.out.println("BF KEY is not exists");
        }else{
            String[] args = new String[]{"A","B","C","D","E","F","G"};
            String checkDataScriptText= """
                    local checkRes = redis.call('BF.EXISTS',KEYS[1],ARGV[1])
                    return checkRes
                    """;
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(checkDataScriptText, Long.class);
            try{
                Long res = redisTemplate.execute(redisScript, keys, args);
                if(1L == res){
                    System.out.println("KEY EXISTS");
                } else if (0L==res) {
                    System.out.println("KEY NOT EXISTS");
                }else{
                    System.out.println("ERROR");
                }
            }catch (Exception e){
                System.out.println("COMMAND NOT SUPPORTED");
            }


        }
    }

    @Test
    public void BloomFilterTest(){
        String createFilterScriptText= """
                     return redis.call('BF.RESERVE', KEYS[1], '0.01','1000','NONSCALING')
                """;
        String addDataScriptText= """
                for i,arg in ipairs(ARGV) do
                    local addRes = redis.call('BF.ADD',KEYS[1],arg)
                end
                return 'OK'
                """;
        String checkDataScriptText= """
                    local checkRes = redis.call('BF.EXISTS',KEYS[1],ARGV[1])
                    return checkRes
                """;

//        RedisScript createFilterScript = new DefaultRedisScript<>(createFilterScriptText);

        List<String> keys = new ArrayList<>();
        keys.add("a-bf");

//        String execute = redisTemplate.execute(new DefaultRedisScript<>(createFilterScriptText,String.class),
//                keys);
//        System.out.println("CREATE BF:"+execute);


        List<String> args = List.of("A","B","C","D","E","F","G");
//
//        System.out.println("ADDDATA BF:"+redisTemplate.execute(new DefaultRedisScript<>(addDataScriptText,String.class),  keys, args));

        RedisScript checkDataFilterScript = new DefaultRedisScript<>(checkDataScriptText,Long.class);

        args = List.of("A");

        System.out.println("CHECKED BF:"+redisTemplate.execute(checkDataFilterScript,keys,args));
    }
}
