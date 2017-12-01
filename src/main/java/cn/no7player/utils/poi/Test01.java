package cn.no7player.utils.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Test01 {

    public static void main(String[] args) throws Exception {
//        writePOI();
//            Workbook wb = item();
//        FileOutputStream fos = new FileOutputStream("d:\\POI\\orderItem.xls");
//        wb.write(fos);
//        fos.close();
//        System.out.println("导出完毕！");
    }

    public static Workbook item() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("订单信息表");

        String title[] = {"申请时间","申请单号","贷款产品","产品编码","申请人","电话","授信额度","申请金额"};

        //设置大标题
        Row headRow = sheet.createRow(0);
        Cell headCell = headRow.createCell(0);
        headCell.setCellValue("订单信息汇总表");
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,title.length-1));
        headCell.setCellStyle(headStyle(wb,headRow));

        //设置表头
        Row titleRow = sheet.createRow(1);
        for (int i = 0;i<title.length;i++){
            titleRow.setHeightInPoints(20);
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(titleStyle(wb,titleRow));
        }

//        FileOutputStream fos = new FileOutputStream("d:\\POI\\exportItem.xls");
//        wb.write(fos);
        System.out.println("生成完毕！");
//        fos.close();
        return wb;
    }

    public static CellStyle headStyle(Workbook wb, org.apache.poi.ss.usermodel.Row row){
        CellStyle curStyle = wb.createCellStyle();

        //设置行高
        row.setHeightInPoints(22);

        //设置字体
        Font curFont = wb.createFont();
        curFont.setFontName("宋体");
        curFont.setFontHeightInPoints((short)16);
        curFont.setBoldweight(Font.BOLDWEIGHT_BOLD);	//字体加粗

        curStyle.setFont(curFont);	//绑定字体

        curStyle.setAlignment(CellStyle.ALIGN_CENTER);		//横向居中
        curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);	//纵向居中

        return curStyle;
    }

    public static CellStyle titleStyle(Workbook wb,Row row){
        CellStyle curStyle = wb.createCellStyle();

        //设置行高
        row.setHeightInPoints(18);

        //设置字体
        Font curFont = wb.createFont();
        curFont.setFontName("宋体");
        curFont.setFontHeightInPoints((short)13);
//        curFont.setBoldweight(Font.BOLDWEIGHT_BOLD);	//字体加粗

        curStyle.setFont(curFont);	//绑定字体

        curStyle.setAlignment(CellStyle.ALIGN_CENTER);		//横向居中
        curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);	//纵向居中

        return curStyle;
    }





    //导出Excel
    public static void writePOI() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet1");
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());

        row.createCell(1).setCellValue(123);
        row.createCell(2).setCellValue(false);
        row.createCell(3).setCellValue(12.34);
        row.createCell(4).setCellValue(12.34f);
        row.createCell(5).setCellValue(12.34d);
        row.createCell(6).setCellValue(HSSFCell.CELL_TYPE_NUMERIC);

        FileOutputStream fos = new FileOutputStream("d:\\POI\\testPOI.xls");
        wb.write(fos);

        fos.close();
    }

    //读取Excel
    public static void readExcel() throws Exception{
        FileInputStream fis = new FileInputStream("d:\\POI\\testPOI.xls");
        POIFSFileSystem pfis = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(pfis);

        Sheet sh = wb.getSheetAt(0);
        if(sh==null){
            return;
        }

        for(int r=0;r<sh.getLastRowNum();r++){
            Row row = sh.getRow(r);
            if(row==null){
                continue;
            }

            for(int c=0;c<row.getLastCellNum();c++){
                Cell cell = row.getCell(c);
                if(cell==null){
                    continue;
                }
                System.out.print(getValue(cell)+"  ");
            }
            System.out.println();
        }
    }

    private static String getValue(Cell cell) {
        int type = cell.getCellType();
        if(type==HSSFCell.CELL_TYPE_BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(type==HSSFCell.CELL_TYPE_NUMERIC){
            return String.valueOf(cell.getNumericCellValue());
        }else{
            return String.valueOf(cell.getStringCellValue());
        }
    }

}
