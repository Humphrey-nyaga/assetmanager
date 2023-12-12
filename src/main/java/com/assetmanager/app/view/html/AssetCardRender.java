package com.assetmanager.app.view.html;

import org.reflections.Reflections;

import java.util.List;

public class AssetCardRender {
    public static String renderAssetCards() {

        StringBuilder cardHtml = new StringBuilder()
                .append(
                        "<div class=\"d-grid gap-2 d-md-block mb-2\">\n");

        Reflections reflections = new Reflections("com.assetmanager.app.model");
        List<Class<?>> entities = reflections.getTypesAnnotatedWith(AssetCreationCard.class)
                .stream().toList();

        for (Class<?> clazz : entities) {
            AssetCreationCard assetCreationCard = clazz.getAnnotation(AssetCreationCard.class);
            String url = assetCreationCard.servletUrl();
            String label = assetCreationCard.label();

            cardHtml.append(
                    "    <a href=\"" + url + "\" class=\"btn btn-primary rounded-2\">View " + label + "</a>"
                    );
        }
        cardHtml.append(
                "    <a href=\"" + "./app/assignasset.jsp" + "\" class=\"btn btn-success rounded-2\">Assign Assets</a>"
        );
        cardHtml.append("</div>\n");

        return cardHtml.toString();
    }
}
