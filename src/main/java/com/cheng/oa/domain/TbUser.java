package com.cheng.oa.domain;

import java.util.Date;

public class TbUser {
    private Integer id;

    private Date createdate;

    private String loginname;

    private String password;

    private Integer status;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

	@Override
	public String toString() {
		return "TbUser [id=" + id + ", createdate=" + createdate + ", loginname=" + loginname + ", password=" + password
				+ ", status=" + status + ", username=" + username + "]";
	}
    
}