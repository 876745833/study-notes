package com.crc.mongodb.controller;

import com.alibaba.fastjson.JSON;
import com.crc.mongodb.dao.DemoDao;
import com.crc.mongodb.entity.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author CRC
 * @Date 2020/6/4
 * @Describe
 */
@RestController
public class MongoController {
    
    @Autowired
    private DemoDao demoDao;
    
    @RequestMapping("/save")
    public String insertDemo(){
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("测试mongodb");
        demoEntity.setBy("crc");
        demoEntity.setUrl("http://www.baidu.com");

        demoDao.saveDemo(demoEntity);

        demoEntity = new DemoEntity();
        demoEntity.setId(2L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("测试mongodb");
        demoEntity.setBy("crc");
        demoEntity.setUrl("http://www.baidu.com");

        demoDao.saveDemo(demoEntity);

        demoEntity.setId(3L);
        demoDao.saveObj(demoEntity);

        return "sucess";
    }
    
    @RequestMapping("/remove")
    public String removeDemoTest() {
        demoDao.removeDemo(2L);
        return "sucess";
    }

    @RequestMapping("/uodate")
    public String updateDemoTest() {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB 更新数据");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("crc");
        demoEntity.setUrl("http://www.souyunku.com");
        demoDao.updateDemo(demoEntity);
        return "sucess";
    }

    @RequestMapping("/find")
    public String findDemoByIdTest() {
        DemoEntity demoEntity = demoDao.findDemoById(1L);
        String result = JSON.toJSONString(demoEntity);
        System.out.println(result);
        return result;
    }
}
