package com.orchid.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.orchid.tools.BaseDto;
import com.orchid.tools.annotation.Log;

import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.util.Date;

/**
 * Created by ljg on 2018/4/26.
 */
public class UserInfo extends BaseDto implements Serializable{
    private static final long serialVersionUID = 1L;
    @Log(title = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @Log(title = "用户名")
    private String userName;
    @Log(title = "登录名")
    private String accountName;
    @Log(title = "密码")
    private String password;
    @Log(title = "电话")
    private String telephone;
    @Log(title = "email")
    private String email;
    @Log(title = "qq")
    private String qq;
    @Log(title = "部门ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    @Log(title = "职位")
    private String userPosition;
    @Log(title = "工号")
    private String staffNo;
    @Log(title = "备注")
    private String remark;
    @Log(title = "状态")
    private Short status;
    @Log(title = "创建者")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;
    @Log(title = "创建时间")
    private Date createTime;
    @Log(title = "密码状态")
    private Short pwdStatus;
    @Log(title = "数据权限范围")
    private String dataAreaCode;
    @Log(title = "登陆失败次数")
    private Integer failCount;
    @Log(title = "最后一次登陆时间")
    private Date lastTime;
    @Log(title = "账号是否锁定")
    private Integer isLock = 0;

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getPwdStatus() {
        return pwdStatus;
    }

    public void setPwdStatus(Short pwdStatus) {
        this.pwdStatus = pwdStatus;
    }

    public String getDataAreaCode() {
        return dataAreaCode;
    }

    public void setDataAreaCode(String dataAreaCode) {
        this.dataAreaCode = dataAreaCode;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
