package cn.no7player;

import cn.no7player.model.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test {

    public static void main(String[] args) throws Exception{

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
//        String str = "2003-01-21";
//        String str1 = "2006-12-31";

//        Date date = sdf.parse(str1);
//        System.out.println(date.getTime());

//        String str = test();
//        System.out.println(sdf.format(new Date()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
//        Date date = new Date();
//        StringBuffer s = new StringBuffer(sdf.format(date));
//        System.out.println(s);

//        Random r = new Random();
//        s.append("-").append(r.nextInt(10000) + "");
//        System.out.println(s);

//        String str = new DecimalFormat("0000").format(9);
//        System.out.println(str);
//        String dataStr = "Thu Nov 02 16:42:52 CST 2017";
//        Date date = parseDate(dataStr);
//        System.out.println(date);

//        System.out.println(sdf.parse(dataStr));


//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
////        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//        Date date = new Date();
//        System.out.println(date);
//        System.out.println(sdf.format(date));

        Map<String,Object> map = new HashMap<>();
        map.put("id","001");
        map.put("name","jack");
        map.put("age",20);
        map.put("password","123456");
        map.put("createTime",new Date());
        map.put("money",new BigDecimal("0.123"));
        User user = (User) convertMap01(User.class,map);
        System.out.println("----------"+user.toString());
    }



    public static Object convertMap01(Class type, Map map) {
        Object obj = null;
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
            obj = type.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);

                    Object[] args = new Object[1];
                    args[0] = value;

                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }

        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
//    @SuppressWarnings("rawtypes")
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }




    /**
     * 格式化string为Date
     * @param datestr
     * @return date
     */
    public static Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0) {
//                fmtstr = "yyyy-MM-dd HH:mm:ss";
                fmtstr = "EEE MMM dd HH:mm:ss zzz yyyy";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        } catch (Exception e) {
            return null;
        }
    }

    public static String test(){
        if(1 == 0){
            return "1";
        }
        if(1 == 1){
            if(1 == 2){
                return "s";
            }
            if(1 == 1){
                return "b";
            }
        }
        if(1 == 1){
            return "3";
        }
        return "4";
    }

    private static void writeZip(String[] strs, String zipname) throws IOException {
        String[] files = strs;
        String path = "";

        OutputStream os = new BufferedOutputStream(new FileOutputStream(path + zipname + ".zip"));
        ZipOutputStream zos = new ZipOutputStream(os);
        byte[] buf = new byte[8192];
        int len;
        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i]);
            if (!file.isFile())
                continue;
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            while ((len = bis.read(buf)) > 0) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
        }
        zos.closeEntry();
        zos.close();

        for (int i = 0; i < files.length; i++) {
            System.out.println("------------" + files);
            File file = new File(files[i]);
            file.delete();
        }
    }
}
