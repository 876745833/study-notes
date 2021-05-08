package com.crc.mongodb.dao.impl;

/**
 * @Author CRC
 * @Date 2020/6/4
 * @Describe
 */

import com.crc.mongodb.dao.DemoDao;
import com.crc.mongodb.entity.DemoEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DemoDaoImpl implements DemoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(DemoEntity demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void saveObj(Object json) {
        mongoTemplate.save(json);
    }

    @Override
    public void removeDemo(Long id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateDemo(DemoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, DemoEntity.class);
    }

    @Override
    public DemoEntity findDemoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        DemoEntity demoEntity = mongoTemplate.findOne(query, DemoEntity.class);
        return demoEntity;
    }

}

