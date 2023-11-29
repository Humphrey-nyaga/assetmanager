package com.assetmanager.app.view.html;

import com.assetmanager.database.helper.DbTable;
import org.reflections.Reflections;

import java.util.List;

public class AssetCardRender {
    public String renderAssetCards() {

        StringBuilder cardHtml = new StringBuilder()
                .append("<div class=\"container\">\n" +
                        "<div class=\"row\">");

        Reflections reflections = new Reflections("com.assetmanager.app.model");
        List<Class<?>> entities = reflections.getTypesAnnotatedWith(AssetCreationCard.class)
                .stream().toList();

        for (Class<?> clazz : entities) {
            AssetCreationCard assetCreationCard = clazz.getAnnotation(AssetCreationCard.class);
            String url = assetCreationCard.addUrl();
            String label = assetCreationCard.label();

            cardHtml.append("<div class=\"col-md-4\">\n" +
                    " <div class=\"card text-center\">\n" +
                    " <div class=\"card-body\">\n" +
                    "<h5 class=\"card-title\">Add ").append(label).append(" </h5>\n" +
                    "<p class=\"card-text\">").append(url).append("</p>\n" +
                    " <a href=\"#\" class=\"btn btn-primary\">View Report</a>\n" +
                    " </div>\n" +
                    " </div>\n" +
                    " </div>");
        }
        cardHtml.append("</div>\n" +
                "\n" +
                "</div>");

        return cardHtml.toString();
    }
}
