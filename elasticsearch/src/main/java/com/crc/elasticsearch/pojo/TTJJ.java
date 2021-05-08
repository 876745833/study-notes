package com.crc.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author CRC
 * @Data 2021/5/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TTJJ {
    private String id;
    private String name;
    private String type;
    private String date;
    private String unitWorth;
    private String dayUp;
    private String weekUp;
    private String monthUp;
    private String monthUp3;
    private String monthUp6;
    private String yearUp;
    private String yearUp2;
    private String yearUp3;
    private String nowYear;
    private String createFromUp;
}
