package com.assetmanager.app.view.html;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class HtmlComponent implements Serializable {
    public static <T> String table(List<T> data, Class<T> dataClass) {

        if (!dataClass.isAnnotationPresent(HtmlTable.class))
            return StringUtils.EMPTY;

        HtmlTable htmlTableLabel = dataClass.getAnnotation(HtmlTable.class);
        List<Field> fields = List.of(dataClass.getDeclaredFields());
        StringBuilder stringBuilder = new StringBuilder()
                .append("<div class=\"row justify-content-center\">\n")
                .append("<div class=\"col-md-10 mr-0\">\n")
                .append("<div class=\"btn-toolbar\"><a href=\"" + htmlTableLabel.addUrl() + "\"><button class=\"btn btn-primary rounded-2\">Add " + htmlTableLabel.label() + "</button></a></div>\n")
                .append("<div class=\"\">\n")
                .append("<div style=\"max-height: 60vh; overflow: auto;\">\n")
                .append("<table class=\"table table-bordered border-4 table-striped table-responsive-sm \">\n")
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
                field.setAccessible(true);
                try {
                    stringBuilder.append("<td>")
                            .append(field.get(model))
                            .append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            stringBuilder.append("""
                    <td>
                    <button type="button" class="btn btn-sm btn-success">Update</button>
                    <button type = "button" class="btn btn-sm btn-danger" onclick = "confirmDelete(this)"> Delete </button>
                                        </td>""");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("""
        <tbody></table>
        </div></div></div></div>
        """);

        return stringBuilder.toString();
    }



    public static<T> String form(Class<T> t) {

        @SuppressWarnings("ignore")
        String className = t.getSimpleName();

        HtmlForm htmlForm = t.getAnnotation(HtmlForm.class);

        StringBuilder htmlFormBuilder = new StringBuilder()
                .append(" <div class=\"row justify-content-center\">\n" +
                        " <div class=\"col-md-4 p-2 ml-2\">\n" +
                        " <div class=\"asset-container mx-auto\" style=\"\">\n" +
                        " <form method=" + htmlForm.httpMethod() +
                        " action= " + htmlForm.url() + " ");
        htmlFormBuilder.append("class=\"border border-4\">\n" +
                " <h4 class=\"text-center mb-0 mt-0\">" +
                "Create New " + htmlForm.label() + "</h4>");


        List<Field> fields = List.of(t.getDeclaredFields());
        for (Field field : fields) {

            if (!field.isAnnotationPresent(HtmlFormField.class))
                continue;
            HtmlFormField htmlFormField = field.getAnnotation(HtmlFormField.class);

            String fieldName = field.getName();
            String id = StringUtils.isBlank(htmlFormField.id()) ? field.getName() : htmlFormField.id();
            String label = StringUtils.isBlank(htmlFormField.label()) ? fieldName : htmlFormField.label();

            String fieldType = "text";
            if (field.getType().isAssignableFrom(LocalDate.class)) {
                fieldType = "date";
                htmlFormBuilder.append("<c:if test='${empty " + fieldName + " or " + fieldName + " le today}'>");
            } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
                fieldType = "number";
            } else if (field.getType().isEnum()) {
                htmlFormBuilder.append(generateEnumField(label, fieldName, id, field.getType()));
                continue;
            }

            htmlFormBuilder.append(generateInputField(label, fieldName, id, fieldType));
            if (field.getType().isAssignableFrom(LocalDate.class)) {
                htmlFormBuilder.append("</c:if>");
            }
        }
        htmlFormBuilder.append("""
        <div class="d-grid gap-2 p-2">
            <button class="btn btn-primary" type="submit">Create""")
                .append(htmlForm.label()).append("""
            </button>
        </div>
    </form>
</div> </div>
</div>
""");

        return htmlFormBuilder.toString();
    }

    private static String generateInputField(String label, String fieldName, String id, String type) {
        return "<div class=\"mb-1 mt-0 p-2\">\n" +
                " <label for=\"" + label + "\" class=\"form-label\">" + label + "</label>\n" +
                " <input type=\"" + type + "\" class=\"form-control form-control-sm\" id=\"" + id + "\" name=\"" + fieldName + "\">\n" +
                "</div>";
    }

    private static String generateEnumField(String label, String fieldName, String id, Class<?> enumClass) {
        StringBuilder htmlForm = new StringBuilder();
        htmlForm.append("<div class=\"mb-1 mt-0 p-2\">\n")
                .append(" <label for=\" " + label + "\" class=\"form-label\">" + label + "</label>\n")
                .append("<select class=\"form-select form-select-sm\" id=\"" + id + "\" name=\"" + fieldName + "\">\n");

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
                < div class="row" >
                <div class="col-md-4 p-2" >
                <div class="asset-container mx-auto center" style = "" >
                <form method = "POST" action = "./asset" class="border border-4" >
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
                < div class="d-grid gap-2 p-2" >
                        <button class="btn btn-primary" type = "submit" > Create Asset </button >
                    </div >
                   </form >
                  </div >
                  </div >
                """;

         return htmlForm; */


}
