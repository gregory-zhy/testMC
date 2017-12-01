package cn.no7player.utils.fastjson;

import cn.no7player.model.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastJsonTest {

    public static void main(String[] args){
        String userJson = user2Json();
        System.out.println(userJson);

        JSONObject ob = JSON.parseObject(userJson);
        long lon = ob.getLong("createTime");
        System.out.println(lon);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }

    public static String user2Json(){
        User user = new User();
        user.setId("001");
        user.setName("Jack");
        user.setAge(20);
        user.setMoney(new BigDecimal("20.36"));
        user.setCreateTime(new Date());
        user.setPassword("123456");

//        String json = JSONObject.toJSONString(user);
        String json = JSON.toJSONString(user);
        return json;
    }





}
