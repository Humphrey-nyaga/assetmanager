package com.assetmanager.app.view.html;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HtmlComponent <T> implements Serializable {
    StringBuilder stringBuilder = new StringBuilder();
    public static  String table(){
        return StringUtils.EMPTY;
    }
//    private String generateInputField(String fieldName, String type) {
//        return "<div class=\"mb-1 mt-0 p-2\">\n" +
//                " <label for=\"" + fieldName + "\" class=\"form-label\">" + fieldName + "</label>\n" +
//                " <input type=\"" + type + "\" class=\"form-control form-control-sm\" id=\"" + fieldName + "\" name=\"" + fieldName + "\">\n" +
//                "</div>";
//    }
//
//    private String generateEnumField(String fieldName, Class<?> enumClass) {
//        StringBuilder htmlForm = new StringBuilder();
//        htmlForm.append("<div class=\"mb-1 mt-0 p-2\">\n")
//                .append(" <label for=\" " + fieldName + "\" class=\"form-label\">" + fieldName + "</label>\n")
//                .append("<select class=\"form-select form-select-sm\" id=\"" + fieldName + "\" name=\"" + fieldName + "\">\n");
//
//        for (Object enumConstant : enumClass.getEnumConstants()) {
//            htmlForm.append("   <option value=\"" + enumConstant.toString() + "\">" + enumConstant + "</option>\n");
//        }
//
//        htmlForm.append(" </select></div>");
//        return htmlForm.toString();
//    }


    public String form(T t){

//        StringBuilder htmlForm  = new StringBuilder().append( """
//                <div class="row no-gutters">
//                <div class="col-md-4 p-2 ml-2">
//                <div class="asset-container mx-auto" style="">
//                <form method="POST" action="./asset" class="border border-4">
//                <h4 class=\"text-center mb-0 mt-0\">Create New Asset</h4>
//                """);


        List<Field> fields = List.of(t.getClass().getDeclaredFields());
//        for (Field field : fields) {
//            String fieldName = field.getName();
//            String type = "text";
//            if (field.getType().isAssignableFrom(LocalDate.class)) {
//                type = "date";
//            } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
//                type = "number";
//            } else if (field.getType().isEnum()) {
//                htmlForm.append(generateEnumField(fieldName, field.getType()));
//                continue;
//            }
//
//            htmlForm.append(generateInputField(fieldName, type));
//        }
//        htmlForm.append("""
//                <div class="d-grid gap-2 p-2">
//                        <button class="btn btn-primary" type="submit">Create Asset</button>
//                    </div>
//                    </form>
//                    </div>
//                    </div>
//                """);
//        return htmlForm.toString();

String htmlForm = """
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

         return htmlForm;
    }

}
