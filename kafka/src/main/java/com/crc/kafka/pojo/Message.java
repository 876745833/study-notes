package com.crc.kafka.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/5/22 15:09
 */
@Data
public class Message {

    private String id;

    private String msg;

    private Date sendTime;

}
