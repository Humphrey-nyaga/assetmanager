package com.assetmanager.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.util.logger.FileLogger;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang3.StringUtils;

public class BaseAction extends HttpServlet {
    private static final Logger LOGGER = FileLogger.getLogger();

    public void serializeForm(Object bean, Map<String, ?> requestMap) {
        try {
            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
                @Override
                public Object convert(String value, Class clazz) {
                    if (clazz.isEnum()) {
                        return Enum.valueOf(clazz, value);
                    } else if (clazz == LocalDate.class) {
                        // web forms return the date in the form
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
            LOGGER.warning("Serialization Error" + e);
        }
    }
    public <T> void renderPage(HttpServletRequest request, HttpServletResponse response,
                               String activeUrl, Class<T> entity,List<T> entityDataList) throws IOException, ServletException {

        request.setAttribute("activeUrl", activeUrl);
        if (StringUtils.trimToEmpty(request.getParameter("action")).equals("add"))
            request.setAttribute("content", HtmlComponent.form(entity));
        else
            request.setAttribute("content", HtmlComponent.table(entityDataList,entity));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./app/index.jsp");
        requestDispatcher.forward(request, response);

    }

    public void renderPageWithoutTables(HttpServletRequest request, HttpServletResponse response, String content, String activeUrl) throws ServletException, IOException {
        request.setAttribute("activeUrl", activeUrl);
        request.setAttribute("content",content);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./app/index.jsp");
        requestDispatcher.forward(request, response);

    }


}
