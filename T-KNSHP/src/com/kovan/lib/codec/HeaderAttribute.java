package com.kovan.lib.codec;

public class HeaderAttribute {
    private String name;
    private int order;
    private int length;
    private String type;
    private String align;
    private String padding;
    private boolean isLengthField;

    public HeaderAttribute() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
        if (type != null) {
            if (type.equals("AN")) {
                this.align = "left";
                this.padding = "";
            } else if (type.equals("N")) {
                this.align = "right";
                this.padding = "0";
            }
        }

    }

    public String getName() {
        return this.name;
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

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isLengthField() {
        return this.isLengthField;
    }

    public void setLengthField(boolean isLengthField) {
        this.isLengthField = isLengthField;
    }
}
