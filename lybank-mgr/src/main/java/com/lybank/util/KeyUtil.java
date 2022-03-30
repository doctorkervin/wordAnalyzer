package com.lybank.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyUtil {

    /**
     * JDK AES 加密
     *
     * @param plainBytes 加密明文
     * @param keyBytes   加密密钥
     * @return 加密密文
     */
    public static byte[] jdkAES(byte[] plainBytes, byte[] keyBytes) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainBytes);
            return encryptedBytes;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JDK AES 解密
     *
     * @param plainBytes
     * @param keyBytes   解密密钥
     * @return
     */
    public static byte[] jdkAES_(byte[] plainBytes, byte[] keyBytes) {
        try {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            Key key = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(plainBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JDK RSA 公钥加密
     *
     * @param plainBytes
     * @param publicKey
     * @return
     */
    public static byte[] jdkRSA(byte[] plainBytes, String publicKey) {
        System.out.println(plainBytes.length);
        try {
            // 公钥字符串转RSAPublicKey对象
            byte[] bytePublicKey = Base64DF.getDecoder().decode(publicKey.getBytes());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytePublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            // 私钥加密，公钥解密--加密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            PublicKey publickey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publickey);
            return cipher.doFinal(plainBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JDK RSA 私钥解密
     *
     * @param plainBytes
     * @param
     * @return
     */
    public static byte[] jdkRSA_(byte[] plainBytes, String stringPrivateKey) {
        try {
            // 私钥字符串转RSAPrivateKey对象
            byte[] bytePrivateKey = Base64DF.getDecoder().decode(stringPrivateKey.getBytes());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // 私钥加密，公钥解密--解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(plainBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JDK RSA 签名
     */
    public static byte[] rsaSign(byte[] plainBytes, String rsaPrivateKey) {
        try {

            byte[] bytePrivateKey = Base64DF.getDecoder().decode(rsaPrivateKey.getBytes());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey2);
            signature.update(plainBytes);
            return signature.sign();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JDK RSA 验签
     */
    public static boolean rsaSign_() {

        return true;
    }

}
