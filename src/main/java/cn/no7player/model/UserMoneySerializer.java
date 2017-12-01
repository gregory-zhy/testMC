package cn.no7player.model;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

//定制User属性（money）序列化
public class UserMoneySerializer implements ObjectSerializer{
    @Override
    public void write(JSONSerializer jsonSerializer, Object obj, Object fieldName, Type fieldType, int i) throws IOException {
        BigDecimal money = (BigDecimal) obj;
        String text = money + "元";
        jsonSerializer.write(text);
    }
//    @Override
//    public void write(JSONSerializer jsonSerializer, Object obj, Object fieldName, Type fieldType) throws IOException {
//        BigDecimal money = (BigDecimal) obj;
//        String text = money + "元";
//        jsonSerializer.write(text);
//    }
}
