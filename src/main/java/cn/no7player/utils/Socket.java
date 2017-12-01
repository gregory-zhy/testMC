package cn.no7player.utils;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Socket {
    static FtpClient myFtp;
    static String hostname;
    static String username;
    static String password;

    /**
     * @param args
     * @author cutelion 20051108 14:27
     */
    public static void main(String[] args) {
        //		String host = "192.168.37.21"; // 要连接的服务端IP地址
//		int port = 21; // 要连接的服务端对应的监听端口
//		// 与服务端建立连接
//		Socket client = new Socket(host, port);
//		System.out.println(client.getKeepAlive());
//		InputStream is = client.getInputStream();
//		byte[] b = new byte[8];
//		while(-1!=is.read(b)){
//			System.out.println(Arrays.toString(b));
//		}
//		client.close();
        connectFTP("192.168.37.21",21,"root","root");

    }

    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp
        FtpClient ftp = null;
        try {
            //创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接
            ftp = FtpClient.create();
            ftp.connect(addr);
            //登陆
            ftp.login(username, password.toCharArray());
            ftp.setBinaryType();
            System.out.println("连接成功");
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }
}