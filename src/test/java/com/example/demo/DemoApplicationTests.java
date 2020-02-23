package com.example.demo;

import com.example.controller.MyRunAble;
import com.example.domain.Users;
import com.example.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching   //开启缓存,否则无法使用redis缓存
public class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void test1() {
        Users u = userService.selectByPrimaryKey((long) 1);
        System.out.println(1);
        u.setNickName("ff");
        Users res = userService.updateByPrimaryKey(u);
        Users u1 = userService.selectByPrimaryKey((long) 1);
        System.out.println(res);

    }

    @Test
    public void test2() {
        MyRunAble runAble=new MyRunAble();
        Thread thread = new Thread(runAble);
        thread.start();


    }

}
