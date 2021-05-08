/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.service;

import java.util.List;
import java.util.Map;

/**
 * @author crc
 * @version : ElstacsearchService.java, v 0.1 2021年04月09日 11:14 crc Exp $
 */
public interface ElasticsearchService {

    void createIndex();

    void existIndex();

    void deleteIndex();

    void addDocument();

    void getIndex();

    /**
     * 批量导入
     */
    <T> boolean bulkRequest(List<T> list, String index);

    /**
     * 分页查询
     */
    List<Map<String, Object>> searchRequest(String keyword, String index, int from, int size);
}