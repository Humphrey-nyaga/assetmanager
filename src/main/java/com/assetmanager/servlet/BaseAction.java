package com.assetmanager.servlet;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

public class BaseAction extends HttpServlet {
    public void serializeForm(Object bean, Map<String,?> requestMap){
        try {
            BeanUtils.populate(bean, requestMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
