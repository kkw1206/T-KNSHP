package com.kovan.lib.util.formatter;

public class FormatterAttribute {
    private String name;
    private String order;
    private String length;
    private String type;
    private String align;
    private String padding;
    private String masking;

    public FormatterAttribute() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getName() {
        return this.name;
    }

    public String getOrder() {
        return this.order;
    }

    public String getLength() {
        return this.length;
    }

    public String getType() {
        return this.type;
    }

    public String getAlign() {
        return this.align;
    }

    public String getPadding() {
        return this.padding;
    }

    public String getMasking() {
        return this.masking;
    }

    public void setMasking(String masking) {
        this.masking = masking;
    }
}