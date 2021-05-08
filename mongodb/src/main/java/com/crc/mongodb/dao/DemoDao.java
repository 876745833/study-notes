package com.crc.mongodb.dao;


import com.crc.mongodb.entity.DemoEntity;

/**
 * @Author CRC
 * @Date 2020/6/4
 * @Describe
 */

public interface DemoDao {

    void saveDemo(DemoEntity demoEntity);

    void saveObj(Object json);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(Long id);
}

