package com.crc.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.redis.entity.TUser;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author crc
 * @since 2021-04-25
 */
public interface TUserMapper extends BaseMapper<TUser> {
    /**
     * 百万数据快速插入
     * @param list
     * @return
     */
    int insertValues(List<TUser> list);
}
