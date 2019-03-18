package com.guli.ucenter.util;

import com.guli.ucenter.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author ：mei
 * @date ：Created in 2019/3/16 0016 下午 16:40
 * @description：jwt工具类
 * @modified By：
 * @version: $
 */
public class JwtUtils {

    public static final String SUBJECT = "guli";

    public static final String APPSECRET = "guli";

    public static final long EXPIRE = 1000 * 60 * 30;

    /**
     * 生成jwt token
     *
     * @param member
     * @return
     */
    public static String geneJsonWebToken(Member member) {

        if (member == null || StringUtils.isEmpty(member.getId())
                || StringUtils.isEmpty(member.getNickname())
                || StringUtils.isEmpty(member.getAvatar())) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", member.getId())
                .claim("nickname", member.getNickname())
                .claim("avatar", member.getAvatar())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;
    }

    /**
     * 校验jwt token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
        return claims;
    }

}

