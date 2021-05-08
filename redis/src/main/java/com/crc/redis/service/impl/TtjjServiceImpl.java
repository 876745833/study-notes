package com.crc.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.redis.entity.Ttjj;
import com.crc.redis.mapper.TtjjMapper;
import com.crc.redis.service.TtjjService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author crc
 * @since 2021-05-07
 */
@Service
public class TtjjServiceImpl extends ServiceImpl<TtjjMapper, Ttjj> implements TtjjService {

    @Resource
    private TtjjMapper ttjjMapper;

    @Override
    public int insertValues(List<Ttjj> list) {
        return ttjjMapper.insertValues(list);
    }

    @Override
    public IPage<Ttjj> queryByReq(int pageNo, int pageSize) {
        Page<Ttjj> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Ttjj> ew = new QueryWrapper<>();
        ew.orderByDesc("weekUp","monthUp","monthUp3","monthUp6");
        return ttjjMapper.selectPage(page, ew);
    }
}
