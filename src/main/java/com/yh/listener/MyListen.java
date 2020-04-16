package com.yh.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListen implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听开始");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听结束");
    }
}
