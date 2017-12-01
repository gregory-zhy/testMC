package cn.no7player.chart.pie;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;

public class PieChart {
    public static void main( String[ ] args ) throws Exception {

    }

    public static void pieChart() throws Exception{
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue("IPhone 5s", new Double( 20 ) );
        dataset.setValue("SamSung Grand", new Double( 20 ) );
        dataset.setValue("MotoG", new Double( 40 ) );
        dataset.setValue("Nokia Lumia", new Double( 10 ) );

        JFreeChart chart = ChartFactory.createPieChart("Mobile Sales",dataset,
                true,true,true);

        int width = 400; /* Width of the image */
        int height = 350; /* Height of the image */
        File pieChart = new File( "D:\\resource\\chart\\PieChart.jpeg");
        ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
        System.out.println("生成完毕！！");
    }
}
