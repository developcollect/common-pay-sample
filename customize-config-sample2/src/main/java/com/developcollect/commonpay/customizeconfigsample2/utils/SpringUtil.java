package com.developcollect.commonpay.customizeconfigsample2.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author zak
 * @version 1.0
 * @date 2019/10/21 10:51
 */
@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext.getParent() == SpringUtil.applicationContext
                || SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取当前运行项目的路径
     *
     * @return java.lang.String
     * @author zak
     * @date 2019/11/18 9:20
     * @deprecated 用 {@link #appHome()}
     */
    @Deprecated
    public static String currentAppPath() {
        return appHome();
    }


    /**
     * 获取当前运行项目的路径
     *
     * @return java.lang.String
     * @author zak
     * @date 2019/11/18 9:20
     */
    public static String appHome() {
        ApplicationHome home = new ApplicationHome();
        return home.getDir().getAbsolutePath() + File.separator;
    }


    public static void publishEvent(ApplicationEvent event) {
        getApplicationContext().publishEvent(event);
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return getApplicationContext().getBean(requiredType);
    }

    public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException {
        return getApplicationContext().getBeansOfType(type);
    }

    public static Object getBean(String name) throws BeansException {
        return getApplicationContext().getBean(name);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        Map<String, Object> beansWithAnnotation = getApplicationContext().getBeansWithAnnotation(annotationType);
        return beansWithAnnotation;
    }
}
