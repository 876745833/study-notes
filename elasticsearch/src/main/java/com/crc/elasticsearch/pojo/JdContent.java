/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author crc
 * @version : JdContent.java, v 0.1 2021年04月09日 11:01 crc Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JdContent {
    private String shop;
    private String name;
    private String price;
    private String img;
}