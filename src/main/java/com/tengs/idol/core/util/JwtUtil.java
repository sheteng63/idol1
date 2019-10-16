package com.tengs.idol.core.util;

import com.tengs.idol.core.exception.BzException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

public class JwtUtil {

    final static String base64EncodedSecretKey = "jwtsecretdemo";//私钥

    final static long TOKEN_EXP = 1000 * 60 * 60 * 24;//过期时间,测试使用十分钟



    public static String getToken(String userName) {

        return Jwts.builder()

                .setSubject(userName)

                .claim("roles", "user")

                .setIssuedAt(new Date())

                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/

                .signWith(SignatureAlgorithm.HS512, base64EncodedSecretKey)

                .compact();

    }

    public static Claims checkToken(String token) throws BzException {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e1) {

            throw new BzException("000001","登录信息过期，请重新登录");

        } catch (Exception e) {

            throw new BzException("000001","用户未登录，请重新登录");

        }
        return claims;
    }

}