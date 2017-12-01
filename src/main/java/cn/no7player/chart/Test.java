package cn.no7player.chart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;

public class Test {

    public static void main( String[] args )throws Exception {
//        createPieChart3D();

//        createLineChart();

        createCategoryDataset();

    }

    //3D饼图：
    public static void createPieChart3D(){
        String[] keys = { "王晓博", "李冰", "牛根生", "李开复", "马云", "其它" };
        double[] data = { 60, 60, 55, 77, 80, 50 };
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < keys.length; i++) {
            dataset.setValue(keys[i], data[i]);
        }
        ChartUtil.createPieChart3D("PieChart3D",dataset,"D:\\resource\\chart");
        System.out.println("--------createPieChart3D!!");
    }
    //折线图：
    public static void createLineChart(){
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(90, "温度", "2011-01-01");
        ds.addValue(100, "温度", "2011-01-02");
        ds.addValue(80, "温度", "2011-01-03");
        ds.addValue(120, "温度", "2011-01-04");

        ds.addValue(130, "湿度", "2011-01-01");
        ds.addValue(100, "湿度", "2011-01-02");
        ds.addValue(150, "湿度", "2011-01-03");
        ds.addValue(140, "湿度", "2011-01-04");
        ChartUtil.createLineChart("LineChart","日期","空气质量",
                ds,"D:\\resource\\chart");
        System.out.println("--------createLineChart!!");
    }
    //柱状图：
    public static void createCategoryDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "struts1.2", "张三");
        dataset.addValue(200, "struts2.x", "张三");
        dataset.addValue(300, "webwork", "张三");
        dataset.addValue(400, "spring", "张三");

        dataset.addValue(100, "struts1.2", "李四");
        dataset.addValue(200, "struts2.x", "李四");
        dataset.addValue(300, "webwork", "李四");
        dataset.addValue(400, "spring", "李四");  //dataset.addValue(-20,　"Ext　JS",　"Jan")负向坐标
        ChartUtil.createCategoryDataset("CategoryDataset","姓名","技能",
                dataset,"D:\\resource\\chart");
    }
    // 条形图：
    public static void createBarChart(){
        double[][] data = new double[][] {
                { 1310, 1220, 1110, 1000, 666 },
                { 720, 700, 680, 640, 777 },
                { 1130, 1020, 980, 800, 888 },
                { 440, 400, 360, 300, 999 },
                { 400, 400, 400, 400, 555 }
        };
        String[] rowKeys = { "猪肉", "牛肉", "鸡肉", "鱼肉", "羊肉" };
        String[] columnKeys = { "襄城", "樊城", "襄州", "东津", "鱼梁州" };
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
                rowKeys, columnKeys, data);
    }
    // 散点图：：
    public static void createPointChart(){
        double[][] data1 = new double[2][5];
        DefaultXYDataset xydataset = new DefaultXYDataset();
        data1[0][0] = 80;
        data1[1][0] = 88;
        data1[0][1] = 82;
        data1[1][1] = 90;
        data1[0][2] = 98;
        data1[1][2] = 67;
        data1[0][3] = 110;
        data1[1][3] = 120;
        data1[0][4] = 122;
        data1[1][4] = 132;
        xydataset.addSeries("温度", data1);
    }
}
