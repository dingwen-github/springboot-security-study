package com.dw.ss.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program:
 * @description: Permission 实体
 * @author: dingwen
 * @create: 2020/10/13 15:44
 **/
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = -6369820046804725864L;
    private Integer id;
    //权限名称
    private String name;
    //权限描述
    private String description;
    //授权链接
    private String url;
    //父节点ID
    private Integer pId;
}
