package com.bryce.config;

import com.bryce.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description shiro 配置
 * @Author Bryce
 * @Date 2020-07-20 10:14
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //设置未认证(登录)时，访问需要认证的资源时跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/user/loginPage");
        //设置访问无权限的资源时跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauthorizedPage");
        //指定路径和过滤器的对应关系
        Map<String, String> filterMap = new HashMap<>();
        //设置/user/login不需要登录就能访问,anon:无需认证即可访问 authc:需要认证才可访问
        filterMap.put("/", "anon");
        filterMap.put("/user/login", "anon");
        //设置/user/list需要登录用户拥有角色user时才能访问
        filterMap.put("/user/list", "roles[user]");
        //其他路径则需要登录才能访问
        filterMap.put("/**", "authc");
        //filterMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        //使用HashedCredentialsMatcher带加密的匹配器来替换原先明文密码匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //指定加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //指定加密次数(md5(md5(pass)))
        //hashedCredentialsMatcher.setHashIterations(2);
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    /**
     *  开启权限注解1
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 开启权限注解2
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }
}
