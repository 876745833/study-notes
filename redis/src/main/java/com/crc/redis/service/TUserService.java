package com.crc.redis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.redis.common.request.TUserRequest;
import com.crc.redis.entity.TUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crc
 * @since 2021-04-25
 */
public interface TUserService extends IService<TUser> {

    IPage<TUser> queryByReq(TUserRequest request);

    int insertValues(List<TUser> list);
}
