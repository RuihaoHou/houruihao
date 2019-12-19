package com.cheng.oa.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TbAdvice {
    private Integer id;
    private Integer uid;
    private Date    createdate;

    private String  title;

    private String  content;

    private TbUser  user;

    @Override
    public String toString() {
        return "TbAdvice [id=" + id + ", createdate=" + createdate + ", title=" + title + ", content=" + content
                + ", user=" + user + "]";
    }

}
