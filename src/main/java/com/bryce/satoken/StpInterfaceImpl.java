package com.bryce.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StpInterfaceImpl
 * @Description
 * @Author Bryce
 * @Date 2020-09-14 09:46
 */
//@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<Object> getPermissionCodeList(Object loginId, String loginKey) {
        List<Object> list = new ArrayList<Object>();    // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        list.add("101");
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        list.add("article-get");
        return list;
    }
}
