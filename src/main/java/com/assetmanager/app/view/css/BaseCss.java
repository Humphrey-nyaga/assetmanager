package com.assetmanager.app.view.css;

import java.io.Serializable;

public class BaseCss implements Serializable {
    private String style = "    " +
            "<style>\n" + //
            "      .bd-placeholder-img {\n" + //
            "        font-size: 1.125rem;\n" + //
            "        text-anchor: middle;\n" + //
            "        -webkit-user-select: none;\n" + //
            "        -moz-user-select: none;\n" + //
            "        user-select: none;\n" + //
            "      }\n" + //
            "\n" + //
            "      @media (min-width: 768px) {\n" + //
            "        .bd-placeholder-img-lg {\n" + //
            "          font-size: 3.5rem;\n" + //
            "        }\n" + //
            "      }\n" +
            " .asset-container {\n" +
            "    margin: 0;\n" +
            "    padding: 2px;\n" +
            "    max-height: calc(100vh - 4px); \n" +
            "}\n" +
            "    </" +//
            "    </style>\n" ;

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
