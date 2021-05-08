package com.crc.redis.pase;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import com.crc.redis.entity.Ttjj;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author CRC
 * @Data 2021/5/7
 */
@Slf4j
public class TTJJParseUtil {
    public static void main(String[] args) {
        String[] arrType = {"gp", "hh", "zq", "zs", "pg"};

        String url = "https://fundapi.eastmoney.com/fundtradenew.aspx?ft=hh&sc=6y&st=desc&pi=1&pn=100&cp=&ct=&cd=&ms=&fr=&plevel=&fst=&ftype=&fr1=&fl=0&isab=1";

        getList(url);
    }

    public static List<Ttjj> getList(String type) {
        String url = "https://fundapi.eastmoney.com/fundtradenew.aspx?ft=" + type + "&sc=6y&st=desc&pi=1&pn=100&cp=&ct=&cd=&ms=&fr=&plevel=&fst=&ftype=&fr1=&fl=0&isab=1";

        ArrayList<Ttjj> list = new ArrayList<>();
        String get = HttpUtil.get(url);
        int i1 = get.indexOf("[");
        int i2 = get.indexOf("]");
        String substring = get.substring(i1, i2 + 1);
        JSONArray jsonArray = new JSONArray(substring);
        Object[] objects = jsonArray.toArray();
        for (Object object : objects) {
            Ttjj data = getData(object.toString());
            list.add(data);
        }
        log.info("爬虫获=={}==类型取数据=={}==条", type, list.size());
        return list;
    }

    static Ttjj getData(String str) {
        String[] split = str.split("\\|");
        Ttjj ttjj = null;
        for (int i = 0; i < split.length; i++) {
            ttjj = Ttjj.builder().id(split[0])
                    .name(split[1])
                    .type(split[2])
                    .date(split[3])
                    .unitWorth(split[4])
                    .dayUp(split[5])
                    .weekUp(split[6])
                    .monthUp(split[7])
                    .monthUp3(split[8])
                    .monthUp6(split[9])
                    .yearUp(split[10])
                    .yearUp2(split[11])
                    .yearUp3(split[12])
                    .nowYear(split[13])
                    .createFromUp(split[14]).build();
        }
        return ttjj;
    }
}
