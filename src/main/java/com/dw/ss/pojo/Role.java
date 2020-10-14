package com.dw.ss.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program:
 * @description: Role 实体
 * @author: dingwen
 * @create: 2020/10/13 15:42
 **/
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 8630375519810373981L;
    private Integer id;
    private String name;
}
