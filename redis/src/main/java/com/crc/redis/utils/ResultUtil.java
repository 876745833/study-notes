package com.crc.redis.utils;

import com.crc.redis.common.object.PageResult;
import com.crc.redis.common.object.ResponseVO;
import com.crc.redis.enums.ResponseStatus;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ResultUtil {

    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    public static ModelAndView forward(String view) {
        return new ModelAndView("forward:" + view);
    }

    public static ResponseVO error(int code, String message) {
        return vo(code, message, null);
    }

    public static ResponseVO error(ResponseStatus status) {
        return vo(status.getCode(), status.getMessage(), null);
    }

    public static ResponseVO success(Object data) {
        return vo(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), data);
    }
    
    public static ResponseVO success(ResponseStatus status) {
        return vo(status.getCode(), status.getMessage(), null);
    }

    public static ResponseVO vo(int code, String message, Object data) {
        return new ResponseVO<>(code, message, data);
    }

    public static PageResult tablePage(Long total, List<?> list) {
        return new PageResult(total, list);
    }

    public static PageResult tablePage(PageInfo info) {
        if (info == null) {
            return new PageResult(0L, new ArrayList());
        }
        return tablePage(info.getTotal(), info.getList());
    }

}
