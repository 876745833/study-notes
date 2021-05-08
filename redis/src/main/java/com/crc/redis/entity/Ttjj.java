package com.crc.redis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author crc
 * @since 2021-05-07
 */
@TableName("ttjj")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ttjj extends Model<Ttjj> {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("type")
    private String type;

    @TableField("date")
    private String date;

    @TableField("unitWorth")
    private String unitWorth;

    @TableField("dayUp")
    private String dayUp;

    @TableField("weekUp")
    private String weekUp;

    @TableField("monthUp")
    private String monthUp;

    @TableField("monthUp3")
    private String monthUp3;

    @TableField("monthUp6")
    private String monthUp6;

    @TableField("yearUp")
    private String yearUp;

    @TableField("yearUp2")
    private String yearUp2;

    @TableField("yearUp3")
    private String yearUp3;

    @TableField("nowYear")
    private String nowYear;

    @TableField("createFromUp")
    private String createFromUp;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
