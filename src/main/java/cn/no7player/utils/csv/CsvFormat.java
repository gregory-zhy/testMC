package cn.no7player.utils.csv;

import cn.no7player.model.ExcelOrderModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ArrayUtils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * csv文件读取、生成工具类
 */
public class CsvFormat<T> {

    /**读取csv文件,指定头信息
     * @param filePath 文件路径
     * @param headers csv列头
     * @return CSVRecord 列表
     * @throws IOException **/
    public static List<CSVRecord> readCSV(String filePath, String[] headers) throws IOException{

        //创建CSVFormat
        CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);

        FileReader fileReader=new FileReader(filePath);

        //创建CSVParser对象
        CSVParser parser=new CSVParser(fileReader,formator);

        List<CSVRecord> records=parser.getRecords();

        parser.close();
        fileReader.close();

        return records;
    }

    /**
     * 读取csv文件,指定Class
     * @param filePath
     * @param t
     * @return
     * @throws IOException
     */
    public List<CSVRecord> readCSV(String filePath, T t) throws IOException{
        //反射获取属性数组
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String[] headers = new String[fields.length];
        for(int i=0;i<headers.length;i++){
            headers[i] = fields[i].getName();
        }

        //创建CSVFormat
        CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);

        FileReader fileReader=new FileReader(filePath);

        //创建CSVParser对象
        CSVParser parser=new CSVParser(fileReader,formator);

        List<CSVRecord> records=parser.getRecords();

        parser.close();
        fileReader.close();

        return records;
    }

    /**
     * 将csv文件转换为List
     * @param filePath csv文件路径
     * @param t  目标文件类型
     * @return  List<T>
     * @throws Exception
     */
    public List<T> csvRecode2List(String filePath,T t,String[] fieldArr)  throws Exception{
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        //获取属性数组
        String[] headers = new String[fields.length];
        for(int i=0;i<headers.length;i++){
            headers[i] = fields[i].getName();
        }

        //创建CSVFormat
        CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);
        FileReader fileReader=new FileReader(filePath);
        //创建CSVParser对象
        CSVParser parser=new CSVParser(fileReader,formator);
        List<CSVRecord> records=parser.getRecords();

        parser.close();
        fileReader.close();

        List<T> list = new ArrayList<>();
        for(CSVRecord record : records){
            T bean =(T)clazz.newInstance();
            //排除指定field
//            excludeField(fieldArr,fields);

            for (Field field : fields) {
                try {
                    boolean b = checkField(fieldArr,field);
                    if(!b){
                        System.out.println("已忽略"+field+"属性");
                        continue;
                    }
                    String fieldSetName = parSetName(field.getName());
                    if (!checkSetMet(methods, fieldSetName)) {
                        continue;
                    }
                    Method fieldSetMet = clazz.getMethod(fieldSetName,field.getType());
//              String fieldKeyName = parKeyName(field.getName());
                    String fieldKeyName = field.getName();
                    String value = record.get(fieldKeyName);
                    if (null != value && !"".equals(value)) {
                        String fieldType = field.getType().getSimpleName();
                        if ("String".equals(fieldType)) {
                            fieldSetMet.invoke(bean, value);
                        } else if ("Date".equals(fieldType)) {
                            Date temp = parseDate(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
                            Integer intval = Integer.parseInt(value);
                            fieldSetMet.invoke(bean, intval);
                        } else if ("Long".equalsIgnoreCase(fieldType)) {
                            Long temp = Long.parseLong(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("Double".equalsIgnoreCase(fieldType)) {
                            Double temp = Double.parseDouble(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                            Boolean temp = Boolean.parseBoolean(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("BigDecimal".equalsIgnoreCase(fieldType)){
                            BigDecimal temp = new BigDecimal(value);
                            fieldSetMet.invoke(bean, temp);
                        }else {
                            System.out.println("not supper type" + fieldType);
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            list.add(bean);
        }

        return list;
    }

    public static Field[] excludeField(String[] fieldArr, Field[] fields) {
        ArrayList<Field> arrayList = new ArrayList<>();
        for(Field field : fields){
            arrayList.add(field);
        }

        return null;
    }

    /**
     * 拼接在某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
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
    /**
     * 判断是否存在某属性的 set方法
     *
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 格式化string为Date
     *
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

    public boolean checkField(String[] fieldArr,Field field){
        boolean result = true;
        for(String str : fieldArr){
            if(str.equals(field.getName())){
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        String path = "d:\\csv\\outputfile-2017-10-27-09-11.csv";
//        String[] heads = {"dDate","oddNumbers", "applyName","phone","dMoney",
//                "detailAdd","identityCard","corpPhone","merchantName"};
//        List<CSVRecord> csvRecordList = readCSV(path, ExcelOrderModel);
//        for(CSVRecord csv : csvRecordList){
//            for(int i=0;i<heads.length;i++){
//                System.out.println(heads[i]+":"+csv.get(heads[i]));
//            }
//            System.out.println("-------------------------");
//
//        }
        Field[] fields = ExcelOrderModel.class.getDeclaredFields();
        String[] str = {"isSend","transformTime"};
        Field[] fld = excludeField(str,fields);

        for(Field field : fld){
            System.out.println(field.getName());
        }
    }

}
