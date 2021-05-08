package com.crc.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.redis.entity.Ttjj;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author crc
 * @since 2021-05-07
 */
public interface TtjjMapper extends BaseMapper<Ttjj> {
    /**
     * 百万数据快速插入
     * @param list
     * @return
     */
    int insertValues(List<Ttjj> list);
}
