package cn.no7player.utils.signature;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.DigestUtils;

public class Test {

    static final String TARGET = "changeme";

    public static void main(String[] args) {
        Md5();
        Base64();


    }

    /*
     * 不可逆算法  MD5
     */
    public static void Md5() {
        String str = DigestUtils.md5DigestAsHex(TARGET.getBytes());
        System.out.println("md5Hex:     "+str);
    }

    /*
    * 不可逆算法  SHA1
    */
    public void Sha1()
    {
//        String str = DigestUtils.shaHex(TARGET);
//        print("shaHex:     "+str);
//        str = DigestUtils.sha256Hex(TARGET);
//        print("sha256Hex:  "+str);
//        str = DigestUtils.sha384Hex(TARGET);
//        print("sha384Hex:  "+str);
//        str = DigestUtils.sha512Hex(TARGET);
//        print("sha512Hex:  "+str);
    }

     /*
      * 可逆算法  BASE64
      */

    public static void Base64()
    {
        //加密
        byte[] b = Base64.encodeBase64(TARGET.getBytes(), true);
        String str = new String(b);
        print("BASE64:     "+str);

        //解密
        byte[] b1 = Base64.decodeBase64(str);
        print("解密之后内容为：  "+new String(b1));
    }
    public static void print(Object obj)
    {
        System.out.println(obj);
    }
}
