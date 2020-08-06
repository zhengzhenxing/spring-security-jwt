package com.example.springsecurityjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtTokenUtil {
    // keytool -genkey -alias jwt -keyalg  RSA -keysize 1024 -validity 365 -keystore jwt.jks
    // jwt.jks放resources目录下
    private static final String keystore = "jwt.jks";
    private static final String password = "zzx123123";
    private static final String alias = "jwt"; // jwt 为 命令生成整数文件时的别名

    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keystore); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, password.toCharArray());
            privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
            publicKey = keyStore.getCertificate(alias).getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用私钥加密 token
     *
     * @param:
     * @return:
     * @auther: Tangzhiqiang
     * @date: 2019/1/13 20:43
     */
    public static String generateToken(String subject, int expirationSeconds) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 不使用公钥私钥 加密token
     *
     * @param:
     * @return:
     * @auther: Tangzhiqiang
     * @date: 2019/1/13 20:41
     */
    public static String generateToken(String subject, int expirationSeconds, String salt) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS256, salt) // 不使用公钥私钥
                .compact();
    }

    /**
     * 通过 公钥解密token
     *
     * @param:
     * @return:
     * @auther: Tangzhiqiang
     * @date: 2019/1/13 20:40
     */
    public static String parseToken(String token) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

    /**
     * 不嘛通过 公钥解密token
     *
     * @param:
     * @return:
     * @auther: Tangzhiqiang
     * @date: 2019/1/13 20:40
     */
    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(salt) // 不使用公钥私钥
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

}

