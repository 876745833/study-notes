/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.controller;


import com.crc.elasticsearch.pojo.TTJJ;
import com.crc.elasticsearch.service.ElasticsearchService;
import com.crc.elasticsearch.utils.ElasticsearchIndexParam;
import com.crc.elasticsearch.utils.TTJJParseUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author crc
 * @version : JdController.java, v 0.1 2021年04月09日 11:27 crc Exp $
 */
@RestController
@RequestMapping("ttjj")
public class TtjjController {

    @Resource
    private ElasticsearchService elasticsearchService;

    @RequestMapping("/save")
    public String save() {
        String[] arrType = {"gp","hh","zq","zs","pg"};
        for (String type : arrType) {
            List<TTJJ> list = TTJJParseUtil.getList(type);
            boolean b = elasticsearchService.bulkRequest(list,ElasticsearchIndexParam.TTJJ_INDEX);
            if (!b) {
                return "false";
            }
        }
        return "true";
    }


    @RequestMapping("/query/{keyword}/{from}/{size}")
    public List<Map<String, Object>> query(@PathVariable("keyword") String keyword, @PathVariable("from")int from, @PathVariable("size")int size) {
        return elasticsearchService.searchRequest(keyword, ElasticsearchIndexParam.TTJJ_INDEX, from, size);
    }



}