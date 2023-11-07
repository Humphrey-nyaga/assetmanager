package com.assetmanager.servlet;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

public class BaseAction extends HttpServlet {

    public void serializeForm(Object bean, Map<String, ?> requestMap) {
        try {
            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
                @Override
                public Object convert(String value, Class clazz) {
                    if (clazz.isEnum()) {
                        return Enum.valueOf(clazz, value);
                    } else if (clazz == LocalDate.class) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        return LocalDate.parse(value, formatter);
                    } else {
                        return super.convert(value, clazz);
                    }
                }
            });
            ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);
            beanUtilsBean.populate(bean, requestMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
