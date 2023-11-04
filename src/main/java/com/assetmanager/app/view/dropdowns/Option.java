package com.assetmanager.app.view.dropdowns;
import java.io.Serializable;

public class Option<T> implements Serializable {
    private T optionName;
    private String label;

    public Option(T optionName, String label) {
        this.optionName = optionName;
        this.label = label;
    }

    public T getOptionName() {
        return optionName;
    }

    public void setOptionName(T optionName) {
        this.optionName = optionName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
