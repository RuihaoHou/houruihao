package com.cheng.oa.domain;

import lombok.Data;

@Data
public class TbDept {
    private Integer id;
    private String  name;
    private String  remark;
    @Override
    public String toString() {
        return "TbDept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
    }

}
