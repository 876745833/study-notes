/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author crc
 * @version : User.java, v 0.1 2021年04月08日 17:19 crc Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private String name;
    private String interests;
    private int age;
    private Date birth;
}