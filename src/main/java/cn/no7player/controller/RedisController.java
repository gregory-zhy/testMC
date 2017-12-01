package cn.no7player.controller;

import cn.no7player.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/redis")
public class RedisController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }
    @RequestMapping(value = "/set",method = RequestMethod.GET)
    public String set(@RequestParam String key, @RequestParam String value) throws Exception{
        redisClient.set(key,value);
        return "success!!";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(@RequestParam String key) throws Exception{
        return redisClient.get(key);
    }






}
