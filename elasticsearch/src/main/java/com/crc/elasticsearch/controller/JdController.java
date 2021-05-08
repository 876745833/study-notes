/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.controller;


import com.crc.elasticsearch.pojo.JdContent;
import com.crc.elasticsearch.service.ElasticsearchService;
import com.crc.elasticsearch.utils.ElasticsearchIndexParam;
import com.crc.elasticsearch.utils.HttpParseUtil;
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
@RequestMapping("jd")
public class JdController {

    @Resource
    private ElasticsearchService elasticsearchService;

    @RequestMapping("/save/{keyword}")
    public String save(@PathVariable("keyword") String keyword) {
        List<JdContent> java = HttpParseUtil.parse(keyword);
        boolean b = elasticsearchService.bulkRequest(java, ElasticsearchIndexParam.JD_INDEX);
        if (b) {
            return "true";
        }
        return "false";
    }


    @RequestMapping("/query/{keyword}/{from}/{size}")
    public List<Map<String, Object>> query(@PathVariable("keyword") String keyword, @PathVariable("from") int from, @PathVariable("size") int size) {
        return elasticsearchService.searchRequest(keyword, ElasticsearchIndexParam.JD_INDEX, from, size);
    }


}