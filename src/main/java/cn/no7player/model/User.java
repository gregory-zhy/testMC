package cn.no7player.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zl on 2015/8/27.
 */
public class User {

//    @JSONField(ordinal = 1)
    private String id;

//    @JSONField(serialize = false)
    private String name;

//    @JSONField(ordinal = 2)
    private Integer age;

//    @JSONField(name = "pwd",ordinal = 3)
    private String password;

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

//    @JSONField(serializeUsing = UserMoneySerializer.class,ordinal = 4)
    private BigDecimal money;

    public String getId() {
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

    public void setId(String id) {
        this.id = id;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", money=" + money +
                '}';
    }
}
