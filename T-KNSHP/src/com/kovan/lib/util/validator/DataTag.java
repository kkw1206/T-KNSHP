package com.kovan.lib.util.validator;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "data-list"
)
public class DataTag {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String required;
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String fixLength;
    @XmlAttribute
    private String minLength;
    @XmlAttribute
    private String maxLength;
    @XmlAttribute
    private String pattern;
    @XmlAttribute
    private String value;
    @XmlAttribute
    private String split;
    @XmlElement(
            name = "invalid-data"
    )
    private List<InvalidDataTag> invalidDataList;

    public DataTag() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequired() {
        return this.required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFixLength() {
        return this.fixLength;
    }

    public void setFixLength(String fixLength) {
        this.fixLength = fixLength;
    }

    public String getMinLength() {
        return this.minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSplit() {
        return this.split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public List<InvalidDataTag> getInvalidDataList() {
        return this.invalidDataList;
    }

    public void setInvalidDataList(List<InvalidDataTag> invalidDataList) {
        this.invalidDataList = invalidDataList;
    }
}