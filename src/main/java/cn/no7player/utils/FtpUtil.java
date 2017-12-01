package cn.no7player.utils;

import org.apache.commons.net.ftp.*;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

/**
 * ftp上传下载工具类
 * @author	Gregory
 */
public class FtpUtil {

	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile01(String host, int port, String username, String password, String basePath,
			String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
//			ftp.connect(host);
			boolean login = ftp.login(username, password);// 登录
			System.out.println("-----------------------login:"+login);
			reply = ftp.getReplyCode();
			System.out.println("-----------------------reply:"+reply);
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftp.disconnect();
//				return result;
//			}
			//切换到上传目录
			boolean cwd = ftp.changeWorkingDirectory(basePath);
			System.out.println("-----------------------切换到上传目录:"+cwd);
			if (!ftp.changeWorkingDirectory(basePath+filePath)) {
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							System.out.println("-----------------------创建目录失败");
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			System.out.println("-----------------------当前目录:"+ftp.printWorkingDirectory());
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//            ftpClient.enterLocalActiveMode();    //主动模式
			ftp.enterLocalPassiveMode(); //被动模式
			//上传文件
			if (!ftp.storeFile(filename, input)) {
				System.out.println("-----------------------storeFile:false");
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
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

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
//			if (!ftp.changeWorkingDirectory(basePath+filePath)) {
//				//如果目录不存在创建目录
//				String[] dirs = filePath.split("/");
//				String tempPath = basePath;
//				for (String dir : dirs) {
//					if (null == dir || "".equals(dir)) continue;
//					tempPath += "/" + dir;
//					if (!ftp.changeWorkingDirectory(tempPath)) {
//						if (!ftp.makeDirectory(tempPath)) {
//							return result;
//						} else {
//							ftp.changeWorkingDirectory(tempPath);
//						}
//					}
//				}
//			}
			System.out.println("------------------------------------------WorkingDirectory:"+ftp.printWorkingDirectory());
//			 ftp.enterLocalActiveMode();    //主动模式
//			ftp.enterLocalPassiveMode(); //被动模式
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setBufferSize(1024);
			//上传文件
			boolean b = ftp.storeFile(filename, input);
			System.out.println("-------------------------ftp.getReplyString:"+ftp.getReplyString());
			if (!b) {
				System.out.println("--------------------------storeFile失败");
				return result;
			}
//            OutputStream os = ftp.storeFileStream("aaa.csv");
//            byte[] b = new byte[1024];
//            int len = 0;
//            while ((len = input.read(b)) != -1) {
//                os.write(b,0,len);
//            }
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
	public static void main(String[] args){
		try {
	        FileInputStream in=new FileInputStream(new File("D:\\ftp\\test.txt"));
//	        boolean flag = uploadFile("192.168.37.130", 8021, "ftptest", "ftptest", "test02.txt", in);
	        boolean flag = uploadFile("192.168.10.47", 21, "ftpuser", "ftpuser", "test01.txt", in);
	        System.out.println(flag);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	}
}
