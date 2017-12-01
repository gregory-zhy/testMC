package cn.no7player.utils.poi;

import cn.no7player.model.ExcelOrderModel;
import cn.no7player.model.User;
import cn.no7player.utils.csv.CsvFormat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestExport {

    public static void main(String args[]) throws Exception {
//        testUser();

        testModel();

    }

    public static void testModel() throws Exception {
        String path = "d:\\csv\\outputfile-2017-11-07-12-15.csv";
        String[] fieldArr = {"corpPhone","merchantName","isSend","transformTime"};
//        String[] fieldArr = {"dDate"};
        CsvFormat<ExcelOrderModel> csvFormat = new CsvFormat<>();
        List<ExcelOrderModel> excelOrderModelList = csvFormat.csvRecode2List(path,new ExcelOrderModel(),fieldArr);
        for(ExcelOrderModel model : excelOrderModelList){
            System.out.println(model.toString());
            System.out.println("------------------------------------------");
        }
        System.out.println("数据源生成完毕！");

        String title = "测试POI工具类";
        String[] headers = {"申请时间","申请单号","申请人姓名","电话","申请金额","面签地址","身份证号"};
        String version = "2003";
        FileOutputStream fos = new FileOutputStream("d:\\POI\\exportExcelModel.xls");

        ExportExcelUtil<ExcelOrderModel> exportExcelUtil = new ExportExcelUtil<>();
        System.out.println("正在导出！");
        exportExcelUtil.exportExcel(title,headers,excelOrderModelList,fieldArr,fos,version);
        System.out.println("导出完毕！");
    }

    public static void testUser() throws FileNotFoundException {
        List<User> userList = new ArrayList<>();
        for(int i=0;i<100;i++){
            User user = new User();
            user.setId(""+i);
            user.setAge(10+i);
            user.setName("user"+i);
            user.setPassword("password"+i);
            user.setCreateTime(new Date());
            user.setMoney(new BigDecimal(Math.round(Math.random()*10000)));
            userList.add(user);
        }
        System.out.println("数据源生成完毕！");

        String title = "测试POI工具类";
        String[] headers = {"ID","姓名","年龄","密码","日期"};
        String[] fieldArr = {"money"};
        String version = "2003";
        FileOutputStream fos = new FileOutputStream("d:\\POI\\exportUtils.xls");

        ExportExcelUtil<User>  exportExcelUtil = new ExportExcelUtil<>();
        System.out.println("正在导出！");
        exportExcelUtil.exportExcel(title,headers,userList,fieldArr,fos,version);
        System.out.println("导出完毕！");
    }
}
