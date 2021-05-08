package com.crc.redis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.redis.annotation.BussinessLog;
import com.crc.redis.common.object.PageResult;
import com.crc.redis.common.object.ResponseVO;
import com.crc.redis.entity.Ttjj;
import com.crc.redis.mapper.TtjjMapper;
import com.crc.redis.pase.TTJJParseUtil;
import com.crc.redis.service.TtjjService;
import com.crc.redis.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author CRC
 * @Data 2021/4/27
 */
@RestController
@RequestMapping("ttjj")
@Slf4j
public class TtjjController {

    @Resource
    private TtjjService ttjjService;
    @Resource
    private TtjjMapper ttjjMapper;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取", notes = "获取")
    @BussinessLog("调用获取")
    public ResponseVO getData(@PathVariable("id") String id) {
        Ttjj user = ttjjService.getById(id);
        return ResultUtil.success(user);
    }

    @PostMapping("/query/{no}/{size}")
    @ApiOperation(value = "查询", notes = "查询")
    @BussinessLog("调用查询")
    public PageResult queryData(@PathVariable("no") int no, @PathVariable("size") int size) {
        IPage<Ttjj> tUserIPage = ttjjService.queryByReq(no, size);
        return ResultUtil.tablePage(tUserIPage.getTotal(), tUserIPage.getRecords());
    }

    @GetMapping("/adds")
    public String getData() {
        ttjjService.remove(new QueryWrapper<>());
        long start = System.currentTimeMillis();
        String[] arrType = {"gp", "hh", "zq", "zs", "pg"};
        for (String type : arrType) {
            List<Ttjj> list = TTJJParseUtil.getList(type);
            ttjjService.insertValues(list);
            long end = System.currentTimeMillis();
            long l = end - start;
            log.info("导入数据 {} 条，耗时 {}ms", l);
        }
        return "导入数据成功";
    }

    @GetMapping("/add")
    public String getData2() {
        ttjjService.remove(new QueryWrapper<>());
        long start = System.currentTimeMillis();
        String[] arrType = {"gp", "hh", "zq", "zs", "pg"};
        for (String type : arrType) {
            List<Ttjj> list = TTJJParseUtil.getList(type);
            for (Ttjj ttjj : list) {
                try {
                    ttjjMapper.insert(ttjj);
                } catch (Exception e) {
                }
            }
            long end = System.currentTimeMillis();
            long l = end - start;
            log.info("导入数据 {} 条，耗时 {}ms", list.size(), l);
        }
        return "导入数据成功";
    }

}
