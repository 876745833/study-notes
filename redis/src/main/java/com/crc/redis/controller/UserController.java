package com.crc.redis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crc.redis.annotation.BussinessLog;
import com.crc.redis.common.object.PageResult;
import com.crc.redis.common.object.ResponseVO;
import com.crc.redis.common.request.TUserRequest;
import com.crc.redis.entity.TUser;
import com.crc.redis.service.TUserService;
import com.crc.redis.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Author CRC
 * @Data 2021/4/27
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private TUserService tUserService;

    @GetMapping("/user/{id}")
    @ApiOperation(value = "获取", notes = "获取")
    @BussinessLog("调用获取")
    public ResponseVO getData(@PathVariable("id") String id){
        TUser user = (TUser)tUserService.getById(id);
        System.out.println("user = " + user);
        return ResultUtil.success(user);
    }

    @PostMapping("/user")
    @ApiOperation(value = "查询", notes = "查询")
    @BussinessLog("调用查询")
    public PageResult queryData(TUserRequest request){
        IPage<TUser> tUserIPage = tUserService.queryByReq(request);
        return ResultUtil.tablePage(tUserIPage.getTotal(),tUserIPage.getRecords());
    }

    @PutMapping("/user")
    @ApiOperation(value = "新增/更新", notes = "新增/更新")
    @BussinessLog("调用新增/更新")
    public ResponseVO queryData(@RequestBody TUser user){
        boolean save = tUserService.save(user);
        return ResultUtil.success(save);
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "删除", notes = "删除")
    @BussinessLog("调用删除")
    public ResponseVO queryData(@PathVariable("id") String id){
        TUser user = tUserService.getById(id);
        return ResultUtil.success(user);
    }

    @GetMapping("/add")
    public String addData() {
        tUserService.remove(new QueryWrapper());
        int count = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            TUser tUser = new TUser();
            tUser.setId(i);
            tUser.setUserName("user" + i);
            tUser.setAddress("address");
            tUser.setCreateTime(LocalDateTime.now());
            tUserService.save(tUser);
        }
        long end = System.currentTimeMillis();
        long l = end - start;
        log.info("导入数据 {} 条，耗时 {}ms",count,l);
        return "导入数据 "+count+" 条，耗时 "+l+"ms";
    }

    @GetMapping("/adds")
    public String getData() {
        tUserService.remove(new QueryWrapper<>());
        int count = 1000000;
        long start = System.currentTimeMillis();
        ArrayList<TUser> tUsers = new ArrayList<>();
        String address = "北京";

        for (int j = 1; j <= count; j++) {
            TUser tUser = new TUser();
            tUser.setId(j);
            tUser.setUserName("user" + j);
            if (j == 20000) address = "上海";
            if (j == 40000) address = "深圳";
            if (j == 60000) address = "广州";
            if (j == 80000) address = "杭州";
            tUser.setAddress(address);
            tUser.setCreateTime(LocalDateTime.now());
            tUsers.add(tUser);
            //1000 43912ms
            //10000 40836ms
            if (j % 10000 == 0) {
                tUserService.insertValues(tUsers);
                tUsers.clear();
            }
        }
        long end = System.currentTimeMillis();

        long l = end - start;
        log.info("导入数据 {} 条，耗时 {}ms",count,l);
        return "导入数据 "+count+" 条，耗时 "+l+"ms";
    }

}
