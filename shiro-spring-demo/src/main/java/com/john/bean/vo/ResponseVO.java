package com.john.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回到前端Ajax请求的统一格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
    private Integer code;
    private String msg;
    private Object data;
}
