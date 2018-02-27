package com.practice.utils;


import com.practice.dto.TokenParentDTO;
import com.practice.dto.TokenTeacherManageDTO;
import com.practice.dto.TokenUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.net.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Jwt token util
 * @author Xushd  2017/12/21 23:36
 */
public class JwtTokenUtil {


    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    public static final int JWT_TTL_WEB = 30*60*1000;
    public static final int JWT_TTL_APP = 7*24*60*60*1000;
    public static final String PROFILES = "Xushd";

    /**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        String stringKey = PROFILES+JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     * @param subject
     * @return
     * @throws Exception
     */
    public static String createJWT(String subject) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
        long nowMillis = System. currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts. builder()
                .setId(JWT_ID)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        long expMillis = nowMillis + JWT_TTL_WEB;
        Date exp = new Date( expMillis);
        builder.setExpiration( exp);
        return builder.compact();
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 验证 jwt 是否过期
     * @param jwt
     * @return
     */
    public static boolean isJwtTimeOut(String jwt){
        try {
            Claims claims = parseJWT(jwt);

            return true;

        }catch (Exception e){

        }
        return false;
    }

    /**
     * 获取 jwt token 中的用户信息
     * @param token
     * @return
     */
    public static TokenUserDTO getTokeUser(String token) {
        try {
            Claims claims = parseJWT(token);

            if(claims!=null){
                String subject = claims.getSubject();

                return JsonUtils.jsonToPojo(subject,TokenUserDTO.class);

            }

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Get parentDTO by token
     * @param token
     * @return
     */
    public static TokenParentDTO getTokenParent(String token){
        try {
            Claims claims = parseJWT(token);

            if(claims!=null){
                String subject = claims.getSubject();

                return JsonUtils.jsonToPojo(subject,TokenParentDTO.class);

            }

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Get teacherManageDTO by token
     * @param token
     * @return
     */
    public static TokenTeacherManageDTO getTokenTeacherManage(String token) {

        try {
            Claims claims = parseJWT(token);

            if(claims!=null){
                String subject = claims.getSubject();

                return JsonUtils.jsonToPojo(subject,TokenTeacherManageDTO.class);

            }

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Create APP token
     * @param subject
     * @return
     */
    public static String createAPPJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
        long nowMillis = System. currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts. builder()
                .setId(JWT_ID)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        long expMillis = nowMillis + JWT_TTL_APP;
        Date exp = new Date( expMillis);
        builder.setExpiration( exp);
        return builder.compact();

    }


}
