package com.kovan.lib.util.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public abstract class FormatterBase {
    protected boolean isStringBasedCropping = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String formatterName = null;
    private HashMap<Integer, FormatterAttribute> formatterMap = new HashMap();
    private int formatLength = 0;

    public FormatterBase() {
    }

    public HashMap getFormatterMap() {
        return this.formatterMap;
    }

    public String getFormatterName() {
        return this.formatterName;
    }

    public int getFormatLength() {
        return this.formatLength;
    }

    public int Parsing(String filePath) throws Exception {
        this.logger.debug("FormatterBase:" + filePath);
        this.formatterName = filePath;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filePath);
        Element order = doc.getDocumentElement();
        NodeList items = order.getElementsByTagName("item");
        this.logger.debug(filePath);

        for(int i = 0; i < items.getLength(); ++i) {
            Node item = items.item(i);
            Node text = item.getFirstChild();
            String ItemName = text.getNodeValue();
            FormatterAttribute ft = new FormatterAttribute();
            ft.setName(ItemName);
            NamedNodeMap Attrs = item.getAttributes();

            for(int j = 0; j < Attrs.getLength(); ++j) {
                Node attr = Attrs.item(j);
                if (attr.getNodeName().equals("order")) {
                    ft.setOrder(attr.getNodeValue());
                } else if (attr.getNodeName().equals("length")) {
                    ft.setLength(attr.getNodeValue());

                    try {
                        this.formatLength += Integer.valueOf(attr.getNodeValue());
                    } catch (Exception var16) {
                    }
                } else if (attr.getNodeName().equals("type")) {
                    ft.setType(attr.getNodeValue());
                } else if (attr.getNodeName().equals("align")) {
                    ft.setAlign(attr.getNodeValue());
                } else if (attr.getNodeName().equals("padding")) {
                    ft.setPadding(attr.getNodeValue());
                } else if (attr.getNodeName().equals("position")) {
                    ft.setPadding(attr.getNodeValue());
                } else if (attr.getNodeName().equals("masking")) {
                    ft.setMasking(attr.getNodeValue());
                } else {
                    this.logger.debug("Not Found Attribute from XML...");
                }

                System.out.println(attr.getNodeName() + ":" + attr.getNodeValue());
            }

            this.formatterMap.put(Integer.parseInt(ft.getOrder()), ft);
        }

        return 0;
    }

    public void EncodingDisplay() {
        HashMap map = this.getFormatterMap();
        Set<Entry<Integer, FormatterAttribute>> set = map.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()) {
            Entry<Integer, FormatterAttribute> e = (Entry)it.next();
            this.logger.debug("key:" + e.getKey() + " " + "value[" + ((FormatterAttribute)e.getValue()).getOrder() + " " + ((FormatterAttribute)e.getValue()).getLength() + "]");
        }

    }

    public abstract String Encoding(HashMap var1);

    public abstract String Encoding(HashMap var1, StringBuffer var2);

    public abstract String EncodingENC(HashMap var1);

    public abstract HashMap Decoding(String var1);

    public abstract HashMap Decoding(String var1, StringBuffer var2);

    public abstract String DecodingENC(String var1);
}
