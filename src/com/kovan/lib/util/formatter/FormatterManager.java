package com.kovan.lib.util.formatter;

import java.util.HashMap;

public class FormatterManager {
    private String defaultSeperator = "^";
    private static FormatterManager formatterManager = null;
    private HashMap<String, FormatterBase> mapFormatterBase = new HashMap();

    public FormatterManager() {
    }

    public FormatterBase getFormatterBase(String name) {
        return (FormatterBase)this.mapFormatterBase.get(name);
    }

    public static FormatterManager getFormatterManager() {
        if (formatterManager == null) {
            formatterManager = new FormatterManager();
        }

        return formatterManager;
    }

    public void load(String type, String path) throws Exception {
        if (type.equals("FORMATTER_FL")) {
            FormatterBase formatterBase = new FormatterFL();
            formatterBase.Parsing(path);
            this.mapFormatterBase.put(path, formatterBase);
        } else if (type.equals("FORMATTER_SP")) {
            this.load(type, path, this.defaultSeperator);
        }

    }

    public void load(String type, String path, String seperator) throws Exception {
        if (type.equals("FORMATTER_FL")) {
            this.load(type, path);
        } else if (type.equals("FORMATTER_SP")) {
            FormatterBase formatterBase = new FormatterSP(seperator);
            formatterBase.Parsing(path);
            this.mapFormatterBase.put(path, formatterBase);
        }

    }

    public void load(String type, String path, boolean isStringBasedCropping) throws Exception {
        if (type.equals("FORMATTER_FL")) {
            FormatterBase formatterBase = new FormatterFL(isStringBasedCropping);
            formatterBase.Parsing(path);
            this.mapFormatterBase.put(path, formatterBase);
        } else if (type.equals("FORMATTER_SP")) {
            this.load(type, path, this.defaultSeperator, isStringBasedCropping);
        }

    }

    public void load(String type, String path, String seperator, boolean isStringBasedCropping) throws Exception {
        if (type.equals("FORMATTER_FL")) {
            this.load(type, path, isStringBasedCropping);
        } else if (type.equals("FORMATTER_SP")) {
            FormatterBase formatterBase = new FormatterSP(seperator, isStringBasedCropping);
            formatterBase.Parsing(path);
            this.mapFormatterBase.put(path, formatterBase);
        }

    }
}
