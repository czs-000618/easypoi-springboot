package com.hncj.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @Excel(name="编号")
    private String id;
    @Excel(name="姓名")
    private String name;
    @Excel(name="生日",format = "yyyy年MM月dd日",width = 15.0)
    private Date bir;
    @Excel(name="爱好",width = 15.0)
    private String habbys;
    @Excel(name="头像信息",width = 22,type = 2,savePath = "/Users/Admin/Desktop/easypoi-springboot/src/main/resources/static/images")
    private String photo;
    @Excel(name="身份证号",width = 18.0)
    private String no;
    @Excel(name="家庭住址",width = 20.0)
    private String address;

}
