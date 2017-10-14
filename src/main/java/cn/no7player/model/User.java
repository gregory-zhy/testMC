package cn.no7player.model;

import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String password;
    private Date createTime;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null){
            return;
        }
        this.password = password;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
