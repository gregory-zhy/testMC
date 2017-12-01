package cn.no7player.chart.bar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BarChart {

    public static void main( String[ ] args )throws Exception {
//        final String fiat = "FIAT";
//        final String audi = "AUDI";
//        final String ford = "FORD";
//
//        final String speed = "Speed";
//        final String millage = "Millage";
//        final String userrating = "User Rating";
//        final String safety = "Safety";
//
//        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
//
//        dataset.addValue( 1.0 , fiat , speed );
//        dataset.addValue( 3.0 , fiat , userrating );
//        dataset.addValue( 5.0 , fiat , millage );
//        dataset.addValue( 5.0 , fiat , safety );
//
//        dataset.addValue( 5.0 , audi , speed );
//        dataset.addValue( 6.0 , audi , userrating );
//        dataset.addValue( 10.0 , audi , millage );
//        dataset.addValue( 4.0 , audi , safety );
//
//        dataset.addValue( 4.0 , ford , speed );
//        dataset.addValue( 2.0 , ford , userrating );
//        dataset.addValue( 3.0 , ford , millage );
//        dataset.addValue( 6.0 , ford , safety );
//
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "CAR USAGE STATIStICS",
//                "Category",
//                "Score",
//                 dataset,
//                 PlotOrientation.VERTICAL,
//                false,  //图例
//                false,
//                false);
//
//        int width = 540; /* Width of the image */
//        int height = 380; /* Height of the image */
//        File BarChart = new File( "D:\\resource\\chart\\BarChart.jpeg" );
//        ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
//        System.out.println("生成完毕！！");
         jfBarChart();
    }

    public static void jfBarChart(){
        String[] rowKey = {"Speed","Millage","User Rating","Safety"};
        String[] columKey = {"FIAT","AUDI","FORD"};
        String title = "汽车性能";
        String xLabel = "指标";
        String yLabel = "得分";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Random random = new Random(10);
        for(String row : rowKey){
            for(String col : columKey){
                dataset.addValue(random.nextDouble(),row,col);
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(title,xLabel,yLabel,dataset,PlotOrientation.VERTICAL,
                true,true,true);

        //导出jpeg
        int width = 540;
        int height = 380;
        File charFile = new File("D:\\resource\\chart\\BarChart.jpeg");
        try {
            ChartUtilities.saveChartAsPNG(charFile,barChart,width,height);
            System.out.println("生成完毕！！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
