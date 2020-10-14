package com.dw.ss.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program:
 * @description: User 实体
 * @author: dingwen
 * @create: 2020/10/13 15:39
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -711300302051382692L;
    private Integer id;
    private String name;
    private String password;
}
