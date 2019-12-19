package com.cheng.oa.domain;

import lombok.Data;
import java.util.Date;


@Data
public class TbDoc {
	  private Integer id;

	    private Date    date;

	    private String  filename;

	    private String  title;

	    private Integer uid;

	    private String  remark;

	    private TbUser  user;
  
}
