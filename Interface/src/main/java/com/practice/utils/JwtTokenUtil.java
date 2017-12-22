package com.practice.utils;


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
    public static final int JWT_TTL = 60*60*1000;
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
        long expMillis = nowMillis + JWT_TTL;
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
     * 获取 jwt token 中的用户信息
     * @param jwt
     * @return
     */
    public static TokenUserDTO getTokeUser(String jwt) {
        try {
            Claims claims = parseJWT(jwt);

            if(claims!=null){
                String subject = claims.getSubject();

                return JsonUtils.jsonToPojo(subject,TokenUserDTO.class);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
