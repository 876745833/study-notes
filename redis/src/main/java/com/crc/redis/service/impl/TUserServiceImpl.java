package com.crc.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.redis.annotation.RedisCache;
import com.crc.redis.common.request.TUserRequest;
import com.crc.redis.entity.TUser;
import com.crc.redis.mapper.TUserMapper;
import com.crc.redis.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crc
 * @since 2021-04-25
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    @RedisCache
    public IPage<TUser> queryByReq(TUserRequest request) {
        Page<TUser> page = new Page<>(request.getPageNo(), request.getPageSize());
        QueryWrapper<TUser> ew = new QueryWrapper<>();
        ew.eq("address",request.getAddress());
        return tUserMapper.selectPage(page, ew);
    }

    @Override
    public int insertValues(List<TUser> list) {
        return tUserMapper.insertValues(list);
    }

    @Override
    @RedisCache
    public TUser getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @RedisCache(flush = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
//    @RedisCache(flush = true)
    public boolean save(TUser entity) {
        return super.save(entity);
    }
}
