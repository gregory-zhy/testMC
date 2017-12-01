package cn.no7player.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FTPUtil01 {

    //ftp对象
    private FTPClient ftp;
    //需要连接到的ftp端的ip
    private String ip;
    //连接端口，默认21
    private int port;
    //要连接到的ftp端的名字
    private String name;
    //要连接到的ftp端的对应得密码
    private String pwd;

    //调用此方法，输入对应得ip，端口，要连接到的ftp端的名字，要连接到的ftp端的对应得密码。连接到ftp对象，并验证登录进入fto
    public FTPUtil01(String ip, int port, String name, String pwd) {
        ftp = new FTPClient();
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.pwd = pwd;

        //验证登录
        try {
            ftp.connect(ip, port);
//            ftp.enterLocalActiveMode();    //主动模式
            ftp.enterLocalPassiveMode(); //被动模式
            System.out.println(ftp.login(name, pwd));
            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

//  //验证登录
//  public void login() {
//      try {
//          ftp.connect(ip, port);
//          System.out.println(ftp.login(name, pwd));
//          ftp.setCharset(Charset.forName("UTF-8"));
//          ftp.setControlEncoding("UTF-8");
//
//      } catch (IOException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }
//  }

    //获取ftp某一文件（路径）下的文件名字,用于查看文件列表
    public void getFilesName() {
        try {
            //获取ftp里面，“Windows”文件夹里面的文件名字，存入数组中
            FTPFile[] files = ftp.listFiles("/Windows");
            //打印出ftp里面，“Windows”文件夹里面的文件名字
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //上传文件
    public void putFile() {
        try {
            //将本地的"D:/1.zip"文件上传到ftp的根目录文件夹下面，并重命名为"aaa.zip"
            System.out.println(ftp.storeFile("aaa.zip", new FileInputStream(new File("D:/1.zip"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //上传文件的第二种方法，优化了传输速度
    public void putFile2() {
        try {
            OutputStream os = ftp.storeFileStream("aaa.zip");
            FileInputStream fis = new FileInputStream(new File("D:/1.zip"));

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                os.write(b,0,len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //下载文件
    public void getFile() {
        try {
            //将ftp根目录下的"jsoup-1.10.2.jar"文件下载到本地目录文件夹下面，并重命名为"1.jar"
            ftp.retrieveFile("jsoup-1.10.2.jar", new FileOutputStream(new File("D:/1.jar")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //下载文件的第二种方法，优化了传输速度
    public void getFile2() {
        try {
            InputStream is = ftp.retrieveFileStream("03.png");

            FileOutputStream fos = new FileOutputStream(new File("D:/2.png"));

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                fos.write(b,0,len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception{
//        FTPUtil01 m = new FTPUtil01("192.168.37.21",21,"root","root");
        // m.putFile();
        // m.putFile2();
        //m.getFile();
//        m.getFile2();
        String[] strs = null;
        List<String> filePathList = new ArrayList<>();
        File file = new File("D:\\scheduel\\");
        if (file.isDirectory()){
            File[] fileArray = file.listFiles();
//            strs = new String[fileArray.length];
            for(int i=0;i<fileArray.length;i++){
                String temp = fileArray[i].getPath();
                String fileName = fileArray[i].getName();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if(fileName.endsWith(".csv")){ //D:\scheduel\2017-10-24-11-42.csv
//                    String fileDate = temp.substring(temp.length()-20,temp.length()-10);
//                    String fileDate = fileName.substring(0,10);
//                    if(sdf.format(new Date()).equals(fileDate)){
                        filePathList.add(temp);
//                        System.out.println(fileDate);
//                    }
                }
            }
        }else {
            System.out.println("不是目录");
        }
        Object[] obj = filePathList.toArray();
        strs = new String[obj.length];
        for(int i = 0;i<obj.length;i++){
            strs[i] = (String) obj[i];
            System.out.println(strs[i]);
        }
        System.out.println("--------------------------------------");
        String srcPath = "D:\\scheduel\\";
        String destPath = "D:\\scheduel\\temp\\";

        File[] sorcFiles = new File(srcPath).listFiles();
        //移动文件
//        for(int i=0;i<sorcFiles.length;i++){
//            //判断文件夹是否创建，没有创建则创建新文件夹
//            File destPathFile = new File(destPath);
//            if(!destPathFile.exists()){
//                destPathFile.mkdirs();
//            }
//            File sorcFile = sorcFiles[i];
//            if(!sorcFile.isDirectory()){
//                File destFile = new File(destPath+File.separator+sorcFile.getName());
//                System.out.println("-------------------sorcFile:"+sorcFile.getPath());
//                System.out.println("-------------------destFile:"+destFile.getPath());
//                if (sorcFile.renameTo(destFile)) {
//                    System.out.println("------------------------------File is moved successful!"+sorcFile.getName());
//                } else {
//                    System.out.println("------------------------------File is failed to move!"+sorcFile.getName());
//                }
//            }

//        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1 = sdf.parse("2017-10-25 14:10:11");
//        int days = (int) (new Date().getTime()-d1.getTime()) / (1000*3600*24);
//        System.out.println("-------"+days);
//        System.out.println("-------"+d1.getTime());
//        System.out.println("-------"+new Date().getTime());

        //删除过期文件
//        File[] overTimeFile = new File(destPath).listFiles();
//        for(int i=0;i<overTimeFile.length;i++){
//                Long lon = overTimeFile[i].lastModified();
//                System.out.println("-------lon:"+lon);
//                System.out.println("-------new Date:"+new Date().getTime());
//                int days = (int) (new Date().getTime()-lon) / (1000*3600*24);
//                System.out.println("-------"+days);
//
//            }

        new File("D:\\ftp\\test.txt").delete();
        }




//        for(int i=0;i<strs.length;i++){
//            try {
//                File sorcFile = new File(strs[i]);
//                File destFile = new File(destPath+File.separator+sorcFile.getName());
//
//                if (sorcFile.renameTo(destFile)) {
//                    System.out.println("File is moved successful!");
//                } else {
//                    System.out.println("File is failed to move!");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }







//        String zipName = "test001";
//        String zipPath = "D:\\scheduel\\";
//        writeZip(strs,"test001",zipPath);
//        FileInputStream fio = new FileInputStream(new File(zipPath+zipName+".zip"));
//        uploadFile("192.168.37.130", 21, "ftptest", "ftptest",zipName+".zip",fio);
//        System.out.println("uploadFile Sucess");
//    }

    private static void writeZip(String[] strs, String zipname,String path) throws IOException {
        String[] files = strs;
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

    public static boolean uploadFile(String host, int port, String username, String password,String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
//			ftp.connect(host);
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            System.out.println("--------------------------------------------------loginReply:"+reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            System.out.println("------------------------------------------WorkingDirectory:"+ftp.printWorkingDirectory());
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setBufferSize(1024);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    private void moveTotherFolders(String srcFilePath,String destFileName,String destPath){
//        String sorcFilePath = srcPath + File.separator + sorcFileName;
        String destFilePath = destPath + File.separator + destFileName;
        try {
            File sorcFile = new File(srcFilePath);
            File destFile = new File(destPath);//获取文件夹路径
            if(!destFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
                destFile.mkdirs();
            }
            if (sorcFile.renameTo(new File(destFilePath))) {
                System.out.println("File is moved successful!:"+destFilePath);
            } else {
                System.out.println("File is failed to move!:"+destFilePath);
            }
        } catch (Exception e) {

        }
    }
}
























