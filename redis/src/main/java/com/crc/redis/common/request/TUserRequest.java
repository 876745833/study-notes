package com.crc.redis.common.request;


import com.crc.redis.entity.TUser;
import lombok.Data;

/**
 * @Author CRC
 * @Data 2021/4/25
 */
@Data
public class TUserRequest extends TUser {
    int pageNo = 1;
    int pageSize = 20;
}
