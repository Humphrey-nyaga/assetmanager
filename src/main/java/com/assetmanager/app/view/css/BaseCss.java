package com.assetmanager.app.view.css;

import java.io.Serializable;

public class BaseCss implements Serializable {
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    private String style = """
                <style>
                  .bd-placeholder-img {
                    font-size: 1.125rem;
                    text-anchor: middle;
                    -webkit-user-select: none;
                    -moz-user-select: none;
                    user-select: none;
                  }

                  @media (min-width: 768px) {
                    .bd-placeholder-img-lg {
                      font-size: 3.5rem;
                    }
                  }
             .asset-container {
                margin: 0;
                padding: 2px;
                max-height: calc(100vh - 4px);\s
            }
              .logo {
                        text-align: left;
                        background-color: #343a40; */
                        color: white;\s
                        padding: 2px;
                    }

                    .tm {
                        font-size: 8px;
                        vertical-align: left;
                    }
                       
                    .card {
                           border-radius: 10px;
                           height: 200px;
              }
              .card:hover {
                transform: scale(1.1);
                transition: transform 0.3s ease;
                background-color: #f2f2f2;
                color: #000;
              }
              .card:hover ul {
                background-color: #f2f2f2;
                color: #000;
              }
              
              
               body{
               background-color: #f5f5f5;
               }
               .profile-dropdown-menu {
                       max-height: 100px;
                       overflow-y: auto;
                   }
                   
               .profile-dropdown-menu .dropdown-item {
                               font-size: 14px;
                               padding: 4px 10px;
         
               </style>
            """;

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
