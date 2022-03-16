package com.kovan.lib.codec;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeaderTemplate {
    private List<HeaderAttribute> headerTemplateAttributeList;
    private boolean isByteBasedCropping = true;

    public HeaderTemplate() {
        this.headerTemplateAttributeList = new ArrayList();
    }

    public HeaderTemplate(List<HeaderAttribute> ftl, boolean isByteBasedCropping) {
        this.headerTemplateAttributeList = ftl;
        this.isByteBasedCropping = isByteBasedCropping;
    }

    public List<HeaderAttribute> getHeaderTemplateAttributeList() {
        return this.headerTemplateAttributeList;
    }

    public void setHeaderTemplateAttributeList(List<HeaderAttribute> headerTemplateAttributeList) {
        this.headerTemplateAttributeList = headerTemplateAttributeList;
    }

    public boolean isByteBasedCropping() {
        return this.isByteBasedCropping;
    }

    public void setByteBasedCropping(boolean isByteBasedCropping) {
        this.isByteBasedCropping = isByteBasedCropping;
    }

    public void setHeaderTemplateAttribute(int order, String name, int length, String type, boolean isLengthField) {
        HeaderAttribute fa = new HeaderAttribute();
        fa.setName(name);
        fa.setOrder(order);
        fa.setLength(length);
        fa.setType(type);
        fa.setLengthField(isLengthField);
        this.headerTemplateAttributeList.add(order - 1, fa);
    }

    public int getHeaderTemplateLength() {
        int headerLength = 0;

        HeaderAttribute ha;
        for(Iterator var3 = this.headerTemplateAttributeList.iterator(); var3.hasNext(); headerLength += ha.getLength()) {
            ha = (HeaderAttribute)var3.next();
        }

        return headerLength;
    }
}
