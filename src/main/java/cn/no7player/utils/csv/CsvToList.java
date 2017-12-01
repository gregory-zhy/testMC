package cn.no7player.utils.csv;

import cn.no7player.model.ExcelOrderModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class CsvToList {
    public static void main(String args[]) throws Exception {

//        String[] heads = {"dDate","oddNumbers", "applyName","phone","dMoney",
//                "detailAdd","identityCard","corpPhone","merchantName"};
//        String[] heads = getFields(ExcelOrderModel.class);
        String path = "d:\\csv\\outputfile-2017-11-07-12-15.csv";

        CsvFormat<ExcelOrderModel> csvFormat = new CsvFormat<>();
        List<ExcelOrderModel> excelOrderModelList = csvFormat.csvRecode2List(path,new ExcelOrderModel(),new String[]{});
        for(ExcelOrderModel model : excelOrderModelList){
            System.out.println(model.toString());
            System.out.println("------------------------------------------");

        }

//        Field[] fields = ExcelOrderModel.class.getDeclaredFields();
//        for(Field field : fields){
//            System.out.println("------------------------------"+field.getType());
//        }

//        String dateString  = "Tue Apr 15 11:23:55 CST 2014";
//        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH) ;
//        System.out.println(sfEnd.format(sfStart.parse(dateString)));
    }


    /**读取csv文件
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

    //获取指定对象的属性明
    public static String[] getFields (Class clazz){
//        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        String[] fieldArr = new String[fields.length];
        for(int i=0;i<fieldArr.length;i++){
            fieldArr[i] = fields[i].getName();
        }
        return fieldArr;
    }

    public static void reflectGetSet() throws Exception{
        Class clazz = Class.forName("cn.no7player.model.User"); //这里的类名是全名。。有包的话要加上包名
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        //写数据
        for (Field f : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
            Method wM = pd.getWriteMethod();//获得写方法
            wM.invoke(obj, 2);//因为知道是int类型的属性，所以传个int过去就是了。。实际情况中需要判断下他的参数类型
        }
        //读数据
//        for (Field f : fields) {
//            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
//            Method rM = pd.getReadMethod();//获得读方法
//            Integer num = (Integer) rM.invoke(obj);//因为知道是int类型的属性,所以转换成integer就是了。。也可以不转换直接打印
//            System.out.println(num);
//        }

//        Class clazz01 = Class.forName("com.itcast.day26.Person");
//        //Field field = clazz.getDeclaredField("name");
//        Object obj01 = clazz.newInstance();
//
//        PropertyDescriptor pd = new PropertyDescriptor("name", clazz01);
//        Method get = pd.getReadMethod();
//        String name = (String)get.invoke(obj01);
//        System.out.println(name);
    }
}
