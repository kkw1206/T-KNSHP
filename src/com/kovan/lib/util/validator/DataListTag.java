package com.kovan.lib.util.validator;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "data-list"
)
public class DataListTag {
    @XmlAttribute
    private String encoding;
    @XmlElement(
            name = "data"
    )
    private List<DataTag> dataList;

    public DataListTag() {
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<DataTag> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<DataTag> dataList) {
        this.dataList = dataList;
    }
}