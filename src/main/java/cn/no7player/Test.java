package cn.no7player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws Exception{

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String str = "2003-01-21";
//        String str1 = "2006-12-31";
//
//        Date date = sdf.parse(str1);
//        System.out.println(date.getTime());

        String str = test();
        System.out.println(str);
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
}
