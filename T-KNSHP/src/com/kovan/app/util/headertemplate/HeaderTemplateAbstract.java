package com.kovan.app.util.headertemplate;

import com.kovan.lib.codec.HeaderTemplate;

public abstract class HeaderTemplateAbstract {
    public HeaderTemplate headerTemplate;
    public int orderOfLengthField;

    public HeaderTemplateAbstract(){
        headerTemplate = new HeaderTemplate();
    }
}
