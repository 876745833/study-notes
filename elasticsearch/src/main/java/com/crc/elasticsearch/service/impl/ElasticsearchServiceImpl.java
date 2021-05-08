/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.service.impl;

import com.crc.elasticsearch.service.ElasticsearchService;
import com.crc.elasticsearch.utils.ElasticsearchIndexParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author crc
 * @version : ElasticsearchServiceImpl.java, v 0.1 2021年04月09日 11:15 crc Exp $
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public void createIndex() {

    }

    @Override
    public void existIndex() {

    }

    @Override
    public void deleteIndex() {

    }

    @Override
    public void addDocument() {

    }

    @Override
    public void getIndex() {

    }

    @Override
    public <T> boolean bulkRequest(List<T> list, String index) {
        BulkResponse bulk = null;
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (int i = 0; i < list.size(); i++) {
                bulkRequest.add(new IndexRequest(index)
                        .id(String.valueOf(i + 1))
                        .source(new ObjectMapper().writeValueAsString(list.get(i)), XContentType.JSON));
            }
            bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !bulk.hasFailures();
    }

    @Override
    public List<Map<String, Object>> searchRequest(String keyword, String index, int from, int size) {
        //结果集
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        try {
            SearchRequest searchRequest = new SearchRequest(index)
                    //精确查询
                    // .source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", keyword))
                    //词条查询
                    .source(new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", keyword))
                            .from(from)
                            .size(size)
                            .timeout(TimeValue.timeValueSeconds(1)));
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits().getHits()) {
                list.add(hit.getSourceAsMap());
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}