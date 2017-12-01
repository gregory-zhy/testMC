package cn.no7player.utils.reflect;

import cn.no7player.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect {

    public static void main(String[] args) throws Exception{
        User user = new User();
        Class  clazz = user.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();

//        for(Field field : fields){
//            String fieldSetName = parSetName(field.getName());
//            Method mt = clazz.getMethod(fieldSetName,field.getType());
//            System.out.println("---------------:"+mt.getName());
//            System.out.println("---------------:"+field.getType());
//            System.out.println("---------------:"+fieldSetName);
//
//        }

        Field nameFiled = fields[1];
//        Method mt = clazz.getMethod("setName",nameFiled.getType());
        Method mt = clazz.getMethod("getName",new Class[]{});
        System.out.println("---------------:"+mt.getName());




    }

    public static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }
}
