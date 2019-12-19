package com.cheng.oa.domain;

import lombok.Data;

@Data
public class Message {
    private String name;
    private String gender;
    private String phone;
    private String email;
    private TbJob  job;
    private String education;
    private String cardid;
    private TbDept dept;
    private String address;
    private String createDate;

    @Override
    public String toString() {
        return "Message [name=" + name + ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", job=" + job
                + ", education=" + education + ", cardid=" + cardid + ", dept=" + dept + ", address=" + address
                + ", createDate=" + createDate + "]";
    }

}
