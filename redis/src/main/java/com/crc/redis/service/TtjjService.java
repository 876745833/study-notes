package com.crc.redis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.redis.entity.Ttjj;

import java.util.List;

/**
 * @Author CRC
 * @Data 2021/5/7
 */
public interface TtjjService extends IService<Ttjj> {
    int insertValues(List<Ttjj> list);

    IPage<Ttjj> queryByReq(int pageNo,int pageSize);
}
