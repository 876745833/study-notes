/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.crc.elasticsearch.utils;

import com.crc.elasticsearch.pojo.JdContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author crc
 * @version : HttpParseUtil.java, v 0.1 2021年04月09日 10:11 crc Exp $
 */
public class HttpParseUtil {

    public static void main(String[] args) {
        try {
            List<JdContent> list = parse("中国&page=5");
            System.out.println("list.toString() = " + list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<JdContent> parse(String keyword){
//        String url = "https://ss.cods.org.cn/latest/searchR?q=%25E9%2598%25BF%25E9%2587%258C%25E5%25B7%25B4%25E5%25B7%25B4&currentPage=1&t=common&searchToken=aa2ba08aaaa861afda42b0c096ac573e";
//        String url = "https://search.jd.com/Search?keyword="+keyword +"&enc=utf-8";
        ArrayList<JdContent> list = null;
        try {
            String url = "https://search.jd.com/Search?keyword="+keyword;
            Document document = Jsoup.parse(new URL(url),3000);
            Element element = document.getElementById("J_goodsList");
            Elements li = element.getElementsByTag("li");
            list = new ArrayList<>();
            for (Element el : li) {
                String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String name = el.getElementsByClass("p-name").eq(0).text();
                String shop = el.getElementsByClass("p-shop").eq(0).text();
                list.add(new JdContent(shop,name,price,img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}