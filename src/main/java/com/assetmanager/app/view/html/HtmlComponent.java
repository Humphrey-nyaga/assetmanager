package com.assetmanager.app.view.html;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.util.SelectBoxStore;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.inject.spi.CDI;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.assetmanager.app.view.html.AssetCardRender.renderAssetCards;


public class HtmlComponent implements Serializable {
    public static <T> String table(List<T> data, Class<T> dataClass) {

        if (!dataClass.isAnnotationPresent(HtmlTable.class))
            return StringUtils.EMPTY;

        HtmlTable htmlTableLabel = dataClass.getAnnotation(HtmlTable.class);

        List<Field> fields = new ArrayList<>(Arrays.asList(dataClass.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(dataClass.getDeclaredFields()));

        StringBuilder stringBuilder = new StringBuilder()
                .append("<div class=\"row justify-content-center\">\n")
                .append("<div class=\"col-md-11 mr-0\">\n");


        if (Asset.class.equals(dataClass)) {
            stringBuilder.append(renderAssetCards());
        } else {
            stringBuilder.append("<div class=\"btn-toolbar\"><a href=\"" + htmlTableLabel.addUrl() + "\"><button class=\"btn btn-primary rounded-2\">Add " + htmlTableLabel.label() + "</button></a></div>\n");
        }

        stringBuilder.append("<div class=\"\">\n")
                .append("<div style=\"max-height: 60vh; overflow: auto;\">\n")
                .append("<table class=\" table table-bordered border-4 table-striped table-responsive-sm \">\n")
                .append("<thead class=\"table-success\">\n")
                .append("<tr>\n");


        for (Field field : fields) {
            String fieldName = field.getName();

            if (!field.isAnnotationPresent(TableColumnHeader.class))
                continue;

            TableColumnHeader columnHeader = field.getAnnotation(TableColumnHeader.class);
            field.setAccessible(true);
            stringBuilder.append("<th scope=\"col\">")
                    .append(StringUtils.isBlank(columnHeader.header()) ? fieldName : columnHeader.header())
                    .append("</th>");

        }
        stringBuilder.append(" <th scope=\"col\">Actions</th>\n");
        stringBuilder.append("</tr></thead>");

        for (T model : data) {
            stringBuilder.append("<tr>");
            for (Field field : fields) {
                if (!field.isAnnotationPresent(TableColumnHeader.class))
                    continue;
                field.setAccessible(true);
                try {
                    if (field.getType().isEnum()) {
                        Object enumValue = field.get(model);
                        Method method = field.getType().getMethod("getName");
                        stringBuilder.append("<td>")
                                .append(method.invoke(enumValue))
                                .append("</td>");
                    } else {

                        stringBuilder.append("<td>")
                                .append(field.get(model))
                                .append("</td>");
                    }
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                Object id = dataClass.getMethod("getId").invoke(model);


                stringBuilder.append("<td>")
                        .append("""
                                <button type="button" class="btn btn-sm btn-success" onclick="window.location.href='""").append(htmlTableLabel.updateUrl())
                        .append("?id=").append(id).append("""
                                '">  Update</button>
                                """)

                        .append("""
                                <button type="button" class="btn btn-sm btn-danger" onclick="confirmDelete('""").append(htmlTableLabel.url())
                        .append("/").append(id).append("""
                                        ')">
                                        Delete
                                    </button>
                                </td>""");
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            stringBuilder.append("</tr>");
        }
        stringBuilder.append("""
                <tbody></table>
                </div></div></div></div>
                """);

        return stringBuilder.toString();
    }


    public static <T> String form(Class<T> t) {
        @SuppressWarnings("ignore")
        String className = t.getSimpleName();

        HtmlForm htmlForm = t.getAnnotation(HtmlForm.class);

        StringBuilder htmlFormBuilder = new StringBuilder();
        htmlFormBuilder.append(" <div class=\"row justify-content-center\">\n" +
                " <div class=\"col-md-10 p-2 ml-2\">\n" +
                " <div class=\" bg-white asset-container mx-auto\" style=\"\">\n" +
                " <form method=" + htmlForm.httpMethod() +
                " action= " + htmlForm.url() + " ");

        // I need the form to have the inputs horizontal. The form is becoming too long
        htmlFormBuilder.append("<div class=\" data-form border border-1 p-3 rounded\">\n" +
                " <h4 class=\"text-center mb-0 mt-0\">" +
                "Create New " + htmlForm.label() + "</h4>");

        int tabCounter = 0;
        int fieldsInCurrentTab = 0;
        int maxFieldsPerTab = 4;

        List<Field> fields = new ArrayList<>(Arrays.asList(t.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(t.getDeclaredFields()));

        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlFormField.class))
                continue;

            if (fieldsInCurrentTab == 0) {
                tabCounter++;
                htmlFormBuilder.append("<div class=\"row\">");
            }

            fieldsInCurrentTab++;

            HtmlFormField htmlFormField = field.getAnnotation(HtmlFormField.class);

            String fieldName = field.getName();
            String label = StringUtils.isBlank(htmlFormField.label()) ? fieldName : htmlFormField.label();
            String type = "text";
            if (field.getType().isAssignableFrom(LocalDate.class)) {
                type = "date";
            }
            if (field.getType().isAssignableFrom(BigDecimal.class) || field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(Double.class)) {
                type = "number";
            }
            if (field.getType().isEnum()) {
                htmlFormBuilder.append("<div class=\"col-md-4\">\n" +
                                " <label for=\" " + fieldName + "\" class=\"form-label\">" + label + "</label>\n")
                        .append("<select class=\"form-select form-select-sm\" id=\"" + fieldName + "\" name=\"" + fieldName + "\">\n");

                Class<?> enumClass = field.getType();
                for (Object enumConstant : enumClass.getEnumConstants()) {
                    Method method = null;
                    try {
                        method = field.getType().getMethod("getName");

                        htmlFormBuilder.append("<option value=\"" + enumConstant.toString() + "\">" + method.invoke(enumConstant) + "</option>\n");
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
                htmlFormBuilder.append(" </select></div>");
                continue;
            }
            if (StringUtils.isNotBlank(htmlFormField.selectList())
                    && StringUtils.isNotBlank(htmlFormField.selectValue())
                    && StringUtils.isNotBlank(htmlFormField.selectDisplay())){
                    try {

                        htmlFormBuilder.append("<div class=\"col-md-4\">\n" +
                                        " <label for=\" " + fieldName + "\" class=\"form-label\">" + label + "</label>\n")
                                .append("<select class=\"form-select form-select-sm\"")
                                .append(" id=\"").append(fieldName)
                                .append("\" name=\"").append(fieldName).append("\" ")
                                .append(htmlFormField.isRequired() ? "required" : "")
                                .append(">");

                        SelectBoxStore genericCombo = CDI.current().select(SelectBoxStore.class).get();

                        Method selectListMethod = SelectBoxStore.class.getDeclaredMethod(htmlFormField.selectList());

                        List<?> options = (List<?>) selectListMethod.invoke(genericCombo);

                        System.out.println("Assignees>>>>>>>>>>>>" + options.toString());

                        for (Object option : options) {
                            Field valueField = htmlFormField.selectValueInSuper() ?
                                    option.getClass().getSuperclass().getDeclaredField(htmlFormField.selectValue()) :
                                    option.getClass().getDeclaredField(htmlFormField.selectValue());
                            valueField.setAccessible(true);
                            System.out.println("Field Name: " + valueField.getName());
                            System.out.println("Field Value: " + valueField.get(option));

                            Field displayField = htmlFormField.selectDisplayInSuper() ?
                                    option.getClass().getSuperclass().getDeclaredField(htmlFormField.selectDisplay()) :
                                    option.getClass().getDeclaredField(htmlFormField.selectDisplay());
                            displayField.setAccessible(true);
                            System.out.println("Field Name: " + valueField.getName());
                            System.out.println("Field Value: " + valueField.get(option));
                            System.out.println(">>>>>>>>>Display Field "+ displayField.get(option));

                            htmlFormBuilder.append("<option value=\"")
                                    .append(valueField.get(option)).append("\">")
                                    .append(displayField.get(option)).append("</option>)");
                        }

                        htmlFormBuilder.append("</select></div>");
                        continue;
                    } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException |
                             InvocationTargetException ex) {
                        System.out.println(ex.getMessage());
                    }

            }

            else {
                htmlFormBuilder.append("<div class=\" col-md-4\">\n" +
                                " <label for=\" " + fieldName + "\" class=\"form-label\">" + label + "</label>\n")
                        .append(htmlFormField.isRequired() ? "<span style=\"color:black;\">*</span> " : "");
                if (htmlFormField.isTextArea()) {
                    htmlFormBuilder.append("<textarea rows=\"3\"");
                } else {
                    htmlFormBuilder.append(" <input type=\"" + type + "\"").append(htmlFormField.isRequired() ? "required" : "");
                }
                htmlFormBuilder.append(" class=\"form-control form-control-sm\" id=\"" + fieldName + "\" name=\"" + fieldName + "\">\n");
                if (htmlFormField.isTextArea()) {
                    htmlFormBuilder.append("</textarea>");
                }
                htmlFormBuilder.append("</div>");
            }


            if (fieldsInCurrentTab >= maxFieldsPerTab) {
                htmlFormBuilder.append("</div>");
                fieldsInCurrentTab = 0;
            }
        }

        if (fieldsInCurrentTab > 0) {
            htmlFormBuilder.append("</div>");
        }

        htmlFormBuilder.append("""
                        <div class="gap-2 p-2 d-flex justify-content-center">
                            <button class="btn btn-lg btn-primary" type="submit">Create """)
                .append(htmlForm.label()).append("""
                                        </button>
                                    </div>
                                </form>
                                </div>
                                </div>
                            </div>
                        </div>
                        """);

        return htmlFormBuilder.toString();
    }

    private static String ifBlank(String target, String alternative) {
        return StringUtils.isBlank(target) ? alternative : StringUtils.trimToEmpty(target);
    }

}
