package com.bryce.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.util.DigestUtils;

/**
 * @ClassName Realm
 * @Description shiro realm
 * @Author Bryce
 * @Date 2020-07-20 10:03
 */
public class MyRealm extends AuthorizingRealm {

    @Bean(name = "userRealm")
    public MyRealm userRealm(){
        return new MyRealm();
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("=>授权doGetAuthorizationInfo");
        return null;
    }

    /***
     * @Description //认证
     * @Author Bryce
     * @Date 10:11 2020/7/20
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("=>认证doAuthenticationToken");
        String username = (String) authenticationToken.getPrincipal();
        //用户名密码为admin 123 才能登录
        if(!"admin".equals(username)){
            throw new UnknownAccountException("账户不存在");
        }
        String saltPassword = DigestUtils.md5DigestAsHex("123".getBytes());
        System.out.println(saltPassword);
        return new SimpleAuthenticationInfo(username, saltPassword, getName());
    }
}
