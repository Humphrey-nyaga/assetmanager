package com.assetmanager.app.view.html;

import com.assetmanager.database.helper.DbTable;
import org.reflections.Reflections;

import java.util.List;

public class AssetCardRender {
    public static String renderAssetCards() {

        StringBuilder cardHtml = new StringBuilder()
                .append(
                        "<div class=\"d-grid gap-2 d-md-block\">\n");

        Reflections reflections = new Reflections("com.assetmanager.app.model");
        List<Class<?>> entities = reflections.getTypesAnnotatedWith(AssetCreationCard.class)
                .stream().toList();

        for (Class<?> clazz : entities) {
            AssetCreationCard assetCreationCard = clazz.getAnnotation(AssetCreationCard.class);
            String url = assetCreationCard.addUrl();
            String label = assetCreationCard.label();

            cardHtml.append(
                    "    <a href=\"" + url + "\" class=\"btn btn-primary rounded-2\">Add " + label + "</a>"
                    );
        }
        cardHtml.append("</div>\n");

        return cardHtml.toString();
    }
}
