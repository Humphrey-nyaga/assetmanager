package com.assetmanager.app.view.html;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HtmlComponent<T> implements Serializable {
    public String table(List<T> models) {

        if (models == null || models.isEmpty()) {
            return StringUtils.EMPTY;
        }
        List<Field> fields = List.of(models.get(0).getClass().getDeclaredFields());
        StringBuilder stringBuilder = new StringBuilder()
                .append("<div class=\"col-md-8 mr-0\">")
                .append("<div class=\"table-responsive-sm\">")
                .append("<div style=\"max-height: 60vh; overflow: auto;\">")
                .append("<table class=\"table table-bordered border-4\">")
                .append("<thead class=\"table-success\">")
                .append("<tr>\n");

        for (Field field : fields) {
            String fieldName = field.getName();
            if (!field.isAnnotationPresent(TableColumnHeader.class))
                continue;
            TableColumnHeader columnHeader = field.getAnnotation(TableColumnHeader.class);
            field.setAccessible(true);
            stringBuilder.append("<th scope=\"col\">")
                    .append(StringUtils.isBlank(columnHeader.header())?fieldName : columnHeader.header())
                    .append("</th>");

        }

        stringBuilder.append("</tr></thead>");

        for(T model: models){
            stringBuilder.append("<tr>");
            for(Field field: fields){
                field.setAccessible(true);
                try {
                    stringBuilder.append("<td>")
                            .append(field.get(model))
                            .append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            stringBuilder.append("</tr>");
        }

        stringBuilder.append(
        """
        <tbody></table>
        </div></div></div></div>
        """);

       return stringBuilder.toString();
    }


    public String form(T t) {
        String className = t.getClass().getSimpleName();

        HtmlForm htmlForm = t.getClass().getAnnotation(HtmlForm.class);

        StringBuilder htmlFormBuilder = new StringBuilder().append(
                " <div class=\"row no-gutters\">\n" +
                " <div class=\"col-md-4 p-2 ml-2\">\n" +
                " <div class=\"asset-container mx-auto\" style=\"\">\n" +
                " <form method=" + htmlForm.httpMethod()+
                " action= "+ htmlForm.url() + " ");
                htmlFormBuilder.append("class=\"border border-4\">\n" +
                " <h4 class=\"text-center mb-0 mt-0\">" +
                "Create New " + htmlForm.label()+"</h4>");


        List<Field> fields = List.of(t.getClass().getDeclaredFields());
        for (Field field : fields) {

            String fieldName = field.getName();
            if (!field.isAnnotationPresent(HtmlFormField.class))
                continue;
            HtmlFormField htmlFormField = field.getAnnotation(HtmlFormField.class);

            String fieldType = "text";
            if (field.getType().isAssignableFrom(LocalDate.class)) {
                fieldType = "date";
            } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
                fieldType = "number";
            } else if (field.getType().isEnum()) {
                htmlFormBuilder.append(generateEnumField(StringUtils.isBlank(htmlFormField.label())?fieldName: htmlFormField.label(), field.getType()));
                continue;
            }

            htmlFormBuilder.append(generateInputField(StringUtils.isBlank(htmlFormField.label())?fieldName: htmlFormField.label(), fieldType));
        }
        htmlFormBuilder.append("""
                        <div class="d-grid gap-2 p-2">
                                <button class="btn btn-primary" type="submit">Create\s""").append(htmlForm.label())
                .append("""
                            </button>
                            </div>
                            </form>
                            </div>
                            </div>
                        """);
        return htmlFormBuilder.toString();
    }

    private String generateInputField(String label, String type) {
        return "<div class=\"mb-1 mt-0 p-2\">\n" +
                " <label for=\"" + label + "\" class=\"form-label\">" + label + "</label>\n" +
                " <input type=\"" + type + "\" class=\"form-control form-control-sm\" id=\"" + label + "\" name=\"" + label + "\">\n" +
                "</div>";
    }

    private String generateEnumField(String label, Class<?> enumClass) {
        StringBuilder htmlForm = new StringBuilder();
        htmlForm.append("<div class=\"mb-1 mt-0 p-2\">\n")
                .append(" <label for=\" " + label + "\" class=\"form-label\">" + label + "</label>\n")
                .append("<select class=\"form-select form-select-sm\" id=\"" + label + "\" name=\"" + label + "\">\n");

        for (Object enumConstant : enumClass.getEnumConstants()) {
            htmlForm.append("<option value=\"")
                    .append(enumConstant.toString())
                    .append("\">")
                    .append(enumConstant)
                    .append("</option>\n");
        }

        htmlForm.append(" </select></div>");
        return htmlForm.toString();
    }



/*String htmlForm = """
                <div class="row">
                <div class="col-md-4 p-2">
                <div class="asset-container mx-auto center" style="">
                <form method="POST" action="./asset" class="border border-4">
                <h4 class=\"text-center mb-0 mt-0\">Create New Asset</h4>
                """;

        for (Field field: fields) {
            String fieldName = field.getName();
            String type = "text";
            if(field.getType().isAssignableFrom(LocalDate.class)){
                type = "date";
            }
            if(field.getType().isAssignableFrom(BigDecimal.class)){
                type = "number";
            }
            if(field.getType().isEnum()){
                htmlForm += "<div class=\"mb-1 mt-0 p-2\">\n" +
                        " <label for=\" "+fieldName+"\" class=\"form-label\">"+fieldName+ "</label>\n" +
                        "<select class=\"form-select form-select-sm\" id=\""+fieldName+ "\" name=\"category\">\n";

                Class<?> enumClass = field.getType();
                for (Object enumConstant : enumClass.getEnumConstants()) {
                    htmlForm += "   <option value=\"" + enumConstant.toString() + "\">" + enumConstant + "</option>\n";
                }
                htmlForm += " </select></div>";
                continue;
            }

            htmlForm += "<div class=\"mb-1 mt-0 p-2\">\n" +
                    " <label for=\" "+fieldName+"\" class=\"form-label\">"+fieldName+ "</label>\n" +
                    " <input type=\""+type+"\" class=\"form-control form-control-sm\" id=\""+fieldName+"\" name=\""+fieldName+"\">\n" +
                    "</div>";
        }
        htmlForm += """
                <div class="d-grid gap-2 p-2">
                        <button class="btn btn-primary" type="submit">Create Asset</button>
                    </div>
                   </form>
                  </div>
                  </div>
                """;

         return htmlForm; */


}
