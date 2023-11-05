package com.assetmanager.app.view.dropdowns;

import com.assetmanager.app.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class AssetCategoryDropdown implements OptionsMenu {

    List<Option<Category>> assetCategory = new ArrayList<>();
    {
        assetCategory.add(new Option<>(Category.HARDWARE, "Hardware"));
        assetCategory.add(new Option<>(Category.SOFTWARE, "Software"));
        assetCategory.add(new Option<>(Category.DIGITAL, "Digital Asset"));
        assetCategory.add(new Option<>(Category.ART, "Art"));
    }
    @Override
    public String menu() {
        StringBuilder stringBuilder = new StringBuilder()
                .append(
                        "<label for=\"category\" class=\"form-label\">Category</label>\n" +
                        "<select class=\"form-select form-select-sm\" id=\"category\" name=\"category\">\n");

        for (Option<Category> category: assetCategory) {
            stringBuilder.append("<option value=").append(category.getOptionName()).append("> ")
                    .append(category.getLabel())
                    .append("</option>");
        }
        stringBuilder.append("</select>");
        return stringBuilder.toString();
    }
}
