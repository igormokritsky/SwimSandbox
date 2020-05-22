package org.igormokritsky.entity;

public class Style extends Entity{

    private String styleName;

    public Style() {

    }

    public Style(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
}
