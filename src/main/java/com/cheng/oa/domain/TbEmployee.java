package com.cheng.oa.domain;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TbEmployee {
    private Integer id;

    private String  address;

    @DateTimeFormat(pattern = ("yyyy-MM-dd"))
    private Date    birthday;

    private String  cardid;

    @DateTimeFormat(pattern = ("yyyy-MM-dd"))
    private Date    createdate;

    private String  education;

    private String  email;

    private Integer gender;

    private String  hobby;

    private String  name;

    private String  party;

    private String  phone;

    private String  postcode;

    private String  qqnum;

    private String  race;

    private String  remark;

    private String  speciality;

    private Integer did;

    private Integer jid;

    private Integer uid;

    private TbJob   job;

    private TbDept  dept;

}
