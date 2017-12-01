package cn.no7player.utils.signature;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * @author ZL
 *
 * 利用rsa生成一对公私钥
 * SHA1withRSA进行签名与验签
 * 可参考https://docs.oracle.com/javase/tutorial/security/apisign/index.html
 */
public class RSATest {
    public static void main(String[] args) throws IOException {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            //初始化密钥对生成器
            gen.initialize(2048);
            //rsa生成一对公私钥
            KeyPair pair = gen.generateKeyPair();
            PublicKey publicKey  = pair.getPublic();
            PrivateKey privateKey  = pair.getPrivate();

//            //SHA1withRSA算法进行签名
//            //实例化，传入算法
//            Signature sign = Signature.getInstance("SHA1withRSA");
//            //初始化，传入私钥
//            sign.initSign(privateKey);

//            byte[] data = "sss".getBytes();
//            sign.update(data);
//            byte[] signature = sign.sign();
//
//            //验签
//            Signature verifySign = Signature.getInstance("SHA1withRSA");
//            verifySign.initVerify(publicKey);
//            verifySign.update(data);
//            boolean flag = verifySign.verify(signature);
//            System.out.println(flag);

            byte [] key = publicKey.getEncoded();
            FileOutputStream keyfos = new FileOutputStream("suepk");
            keyfos.write(key);
            keyfos.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
