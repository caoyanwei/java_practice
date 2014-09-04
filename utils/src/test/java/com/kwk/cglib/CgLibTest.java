package com.kwk.cglib;


import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;


public class CgLibTest {
    @Test
    public void haveAuth() {
        TableDAO tDao = TableDAOFactory.getAuthInstance(new AuthProxy("张三"));
        doMethod(tDao);
    }

    @Test
    public void haveNoAuth() {
        TableDAO tDao = TableDAOFactory.getAuthInstance(new AuthProxy("李四"));
        doMethod(tDao);
    }

    public static void doMethod(TableDAO dao) {
        dao.create();
        dao.query();
        dao.update();
        dao.delete();
    }
}


class TableDAO {
    public void create() {
        System.out.println("create() is running !");
    }

    public void query() {
        System.out.println("query() is running !");
    }

    public void update() {
        System.out.println("update() is running !");
    }

    public void delete() {
        System.out.println("delete() is running !");
    }
}

class AuthProxy implements MethodInterceptor {
    private String name;

    //传入用户名称
    public AuthProxy(String name) {
        this.name = name;
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //用户进行判断
        if (!"张三".equals(name)) {
            System.out.println("你没有权限！");
            return null;
        }
        return proxy.invokeSuper(obj, args);
    }
}

class TableDAOFactory {
    private static TableDAO tDao = new TableDAO();

    public static TableDAO getAuthInstance(AuthProxy authProxy) {
        Enhancer en = new Enhancer();
        en.setSuperclass(TableDAO.class);
        en.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});
        en.setCallbackFilter(new AuthProxyFilter());
        return (TableDAO)en.create();
    }
}

class AuthProxyFilter implements CallbackFilter {
    public int accept(Method arg0) {
        if (!"query".equalsIgnoreCase(arg0.getName()))
            return 0;
        return 1;
    }

}