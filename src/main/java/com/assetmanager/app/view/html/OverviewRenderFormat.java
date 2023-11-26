package com.assetmanager.app.view.html;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class  OverviewRenderFormat implements Serializable {
    public static String generateHtml(Class<?> clazz, List<?> list) {
        StringBuilder html = new StringBuilder();
//                .append("<div class=\"container\">\n" +
//                        "            <div class=\"row\">");

        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();


            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(SummaryHtmlCard.class))
                    continue;
                method.setAccessible(true);
                SummaryHtmlCard summaryHtml = method.getAnnotation(SummaryHtmlCard.class);
                String label = summaryHtml.name();

                Object result = method.invoke(instance, list);

                html.append("<div class=\"col-sm-4\">");
                if (result instanceof Map<?, ?> resultMap) {
                    html.append(generateMapHtml(resultMap, label));

                } else if (result instanceof BigDecimal) {
                    DecimalFormat format = new DecimalFormat("#,###.##");
                    String formattedValue = "$ <br> " + format.format((BigDecimal) result);
                    html.append(generateSingleValueHtml(formattedValue, label));
                } else {
                    html.append(generateSingleValueHtml(result, label));
                }
                html.append("</div>");

            }

//            html.append("</div>\n" +
//                    "        </div>");
            return html.toString();

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static  <K, V> String generateMapHtml(Map<K, V> map, String methodLabel) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("<div class=\"card hover-zoom mb-3\">")
                .append("    <h5 class=\"card-header bg-secondary text-center\">")
                .append(methodLabel)
                .append("    <h5>\n")
                .append("    <ul class=\"list-group list-group-flush\">");

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            stringBuilder.append("<li class=\"list-group-item\">")
                    .append("<div class=\"row\">\n")
                    .append("    <div class=\"col-6\">").append(entry.getKey()).append(":</div>\n")
                    .append("    <div class=\"col-6\">").append(entry.getValue()).append("</div>\n")
                    .append("</div>\n").append("</li>");
        }

        stringBuilder.append("</ul>").append("</div>");
        return stringBuilder.toString();
    }

    private static String generateSingleValueHtml(Object value, String methodLabel) {
        return "<div class=\"card mb-3\">\n" +
                "    <h5 class=\"card-header bg-secondary text-center\">" + methodLabel + "</h5>\n" +
                "    <div class=\"d-flex align-items-center flex-column\">\n" +
                "        <div class=\"display-4 text-center mt-0\">\n" +
                "            " + value.toString() + "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";
    }
}
