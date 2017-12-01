package cn.no7player.utils;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDTest {

    public static void main(String[] args) throws Exception {

        String uuid01 = UUID.randomUUID().toString();
        String uuid02 = UUID.randomUUID().toString().replace("-", "");

        System.out.println(uuid01);
        System.out.println(uuid02);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        System.out.println(sdf.format(new Date()));
//        System.out.println(new Date().getTime());

//        int a = 1;
//        int b = 2;
//        System.out.println(a+""+b);
    }
}

