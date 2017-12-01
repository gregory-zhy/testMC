package cn.no7player.utils;

import java.io.*;


import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;


public class FtpUtil02 {

    private static Logger logger=Logger.getLogger(FtpUtil02.class);

    private static FTPClient ftp;

    /**
     * 获取ftp连接
     * @param f
     * @return
     * @throws Exception
     */
    public static boolean connectFtp(Ftp f) throws Exception{
        ftp=new FTPClient();
        boolean flag=false;
        int reply;
        if (f.getPort()==null) {
            ftp.connect(f.getIpAddr(),21);
        }else{
            ftp.connect(f.getIpAddr(),f.getPort());
        }
        ftp.login(f.getUserName(), f.getPwd());
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return flag;
        }
        ftp.changeWorkingDirectory(f.getPath());
        flag = true;
        return flag;
    }

    /**
     * 关闭ftp连接
     */
    public static void closeFtp(){
        if (ftp!=null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp上传文件
     * @param f
     * @throws Exception
     */
    public static void upload(File f) throws Exception{
        if (f.isDirectory()) {
            ftp.makeDirectory(f.getName());
            ftp.changeWorkingDirectory(f.getName());
            String[] files=f.list();
            for(String fstr : files){
                File file1=new File(f.getPath()+"/"+fstr);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftp.changeToParentDirectory();
                }else{
                    File file2=new File(f.getPath()+"/"+fstr);
                    FileInputStream input=new FileInputStream(file2);
                    ftp.storeFile(file2.getName(),input);
                    input.close();
                }
            }
        }else{
            File file2=new File(f.getPath());
            FileInputStream input=new FileInputStream(file2);
            ftp.storeFile(file2.getName(),input);
            input.close();
        }
    }

    /**
     * 下载链接配置
     * @param f
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @throws Exception
     */
    public static void startDown(Ftp f,String localBaseDir,String remoteBaseDir ) throws Exception{
        if (FtpUtil02.connectFtp(f)) {

            try {
                FTPFile[] files = null;
                boolean changedir = ftp.changeWorkingDirectory(remoteBaseDir);
                if (changedir) {
                    ftp.setControlEncoding("GBK");
                    files = ftp.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        try{
                            downloadFile(files[i], localBaseDir, remoteBaseDir);
                        }catch(Exception e){
                            logger.error(e);
                            logger.error("<"+files[i].getName()+">下载失败");
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                logger.error("下载过程中出现异常");
            }
        }else{
            logger.error("链接失败！");
        }

    }


    /**
     *
     * 下载FTP文件
     * 当你需要下载FTP文件的时候，调用此方法
     * 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
     *
     * @param ftpFile
     * @param relativeLocalPath
     * @param relativeRemotePath
     */
    private  static void downloadFile(FTPFile ftpFile, String relativeLocalPath,String relativeRemotePath) {
        if (ftpFile.isFile()) {
            if (ftpFile.getName().indexOf("?") == -1) {
                OutputStream outputStream = null;
                try {
                    File locaFile= new File(relativeLocalPath+ ftpFile.getName());
                    //判断文件是否存在，存在则返回
                    if(locaFile.exists()){
                        return;
                    }else{
                        outputStream = new FileOutputStream(relativeLocalPath+ ftpFile.getName());
                        ftp.retrieveFile(ftpFile.getName(), outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (Exception e) {
                    logger.error(e);
                } finally {
                    try {
                        if (outputStream != null){
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        logger.error("输出文件流异常");
                    }
                }
            }
        } else {
            String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
            String newRemote = new String(relativeRemotePath+ ftpFile.getName().toString());
            File fl = new File(newlocalRelatePath);
            if (!fl.exists()) {
                fl.mkdirs();
            }
            try {
                newlocalRelatePath = newlocalRelatePath + '/';
                newRemote = newRemote + "/";
                String currentWorkDir = ftpFile.getName().toString();
                boolean changedir = ftp.changeWorkingDirectory(currentWorkDir);
                if (changedir) {
                    FTPFile[] files = null;
                    files = ftp.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        downloadFile(files[i], newlocalRelatePath, newRemote);
                    }
                }
                if (changedir){
                    ftp.changeToParentDirectory();
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }



    /**
     * Description: 向FTP服务器上传文件
     * @Version1.0
     * @param url FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path FTP服务器保存目录
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 返回上传状态 StatusCodes 成功与否
     */
    public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {
        boolean error_age=false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
//            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.connect(url);
            boolean login = ftp.login(username, password);//登录
            logger.info("login:"+login);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftp.enterLocalPassiveMode();
//            ftp.enterLocalActiveMode();
            ftp.setFileTransferMode( FTPClient.STREAM_TRANSFER_MODE);
            logger.info("当前目录:"+ftp.printWorkingDirectory());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.info("disconnect:"+reply);
                return error_age;
            }
            if(!ftp.changeWorkingDirectory(path))
            {
                File newFile = new File(path +"/"+ filename);
                String dir = newFile.getParentFile().getPath();
                if (!ftp.changeWorkingDirectory(dir)) {
                    if (!ftp.makeDirectory(newFile.getParentFile().getPath())) {
                        logger.debug("创建文件目录【" + dir + "】 失败！");
                    }
                }
            }
            logger.info("当前目录:"+ftp.printWorkingDirectory());
            boolean storeFile = ftp.storeFile(path+"/"+filename, input);
            logger.info("storeFile:"+storeFile);
            logger.info("-------ftp.getReplyCode:"+ftp.getReplyCode());
            logger.info("-------ftp.getReplyString:"+ftp.getReplyString());
            input.close();
            ftp.logout();
            error_age=true;
        } catch (IOException e) {
            error_age=false;
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    error_age=false;
                }
            }
        }

        return error_age;

    }



    public static void main(String[] args) throws Exception{
        String url = "192.168.10.47";
        int port = 21;
        String username = "ftpuser";
        String password="ftpuser";
        String path = "/home/vsftpd";
        String filename="test.txt";
        InputStream input = new FileInputStream(new File("D:\\ftp\\test.txt"));
        uploadFile(url,port,username,password,path,filename,input);

//        FTPClient ftpClient = new FTPClient();
//        FileInputStream fis = null;
//
//        try {
//            ftpClient.setControlEncoding("GBK");// 设置登陆编码格式
//            ftpClient.setConnectTimeout(20000);// 超时60秒55372
//            ftpClient.connect("192.168.37.21",21);
//            boolean b = ftpClient.login("ftptest", "ftptest");
//            System.out.println("连接成功:"+b);
//            File srcFile = new File("D:\\ftp\\test.txt");
//            fis = new FileInputStream(srcFile);
//            //设置上传目录
//            boolean cwd = ftpClient.changeWorkingDirectory("/home");
//            System.out.println("设置上传目录："+cwd);
//            System.out.println("当前目录:"+ftpClient.printWorkingDirectory());
//            ftpClient.setBufferSize(1024);
//            //设置文件类型（二进制）
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
////            ftpClient.enterLocalActiveMode();    //主动模式
//            ftpClient.enterLocalPassiveMode(); //被动模式
//            String fileName = new String("work".getBytes("GBK"),"iso-8859-1");
//            boolean b1 = ftpClient.storeFile(fileName, fis);
//            System.out.println("上传结果:"+b1);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("FTP客户端出错！", e);
//        } finally {
//            IOUtils.closeQuietly(fis);
//            try {
//                ftpClient.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("关闭FTP连接发生异常！", e);
//            }
//        }
    }

}
