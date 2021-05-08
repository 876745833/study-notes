package com.crc.redis.common.request;

import lombok.Data;

/**
 * @Author CRC
 * @Data 2021/4/25
 */
@Data
public class PageReqObject {
    int pageNo;
    int pageSize;
}
