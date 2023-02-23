package com.zhangys.photo.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: generator
 * @DESCRIPTION: JWT配置类 1.通过用户信息获取token
 *                        2.从token中获取用户信息
 *                        3.判断token是否失效
 *                        4.刷新token
 * @USER: 13905
 * @DATE: 2023/1/8 14:43
 */
@Component
public class JwtTokenUtil {
    //荷载claim的名称
    private static final String CLAIM_KEY_USERNAME="sub";
    //荷载claim的创建时间
    private static final String CLAIM_KEY_CREATED ="created";
    //jwt令牌的秘钥
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * 从token获取用户信息
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username=null;
        Claims claims=getClaimsFromToken(token);
        try {
            username = claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;

    }

    /**
     * 判断token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        //判断 token中的username是否与登录的username相同 并且token并未过期
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }
    /**
     * 判断token是否可以被刷新
     */
    public Boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }
    /**
     * 刷新token
     */
    public String refreshToken(String token)
    {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expirated=getExpiredDateFromToken(token);
        //用到期时间与现在的时间比较 判断是否过期
        return expirated.before(new Date());
    }

    /**
     * 获取token的到期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        //通过调用从token中获取负载方法 获取到期时间
        Claims claims = getClaimsFromToken(token);
        return  claims.getExpiration();
    }

    /**
     * 根据token获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims= Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成JWTToken
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 生成token实效时间
     *
     * @return
     */
    private Date generateExpiration() {
        //当前时间+配置时间
        return new Date(System.currentTimeMillis()+expiration*1000);
    }




}
