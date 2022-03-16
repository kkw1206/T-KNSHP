package com.kovan.app.util.headertemplate;

public class KnsHeaderTemplate extends HeaderTemplateAbstract {
    public KnsHeaderTemplate(){
        orderOfLengthField = 3; // 길이 필드 위치

        headerTemplate.setHeaderTemplateAttribute(1, "MSG_TYPE", 4, "AN", false);
        headerTemplate.setHeaderTemplateAttribute(2, "MSG_VERSION", 4, "AN", false);
        headerTemplate.setHeaderTemplateAttribute(3, "MSG_LENGTH"     ,  4,  "N", true);
        headerTemplate.setHeaderTemplateAttribute(4, "MSG_DATA_TYPE"  ,  8, "AN", false);
        headerTemplate.setHeaderTemplateAttribute(5, "MSG_CHARSET"    ,  8, "AN", false);
        headerTemplate.setHeaderTemplateAttribute(6, "MSG_DATA_CIPHER", 20, "AN", false);
    }
}
