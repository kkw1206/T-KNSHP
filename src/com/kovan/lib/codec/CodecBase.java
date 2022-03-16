package com.kovan.lib.codec;

import org.json.simple.parser.ParseException;

public interface CodecBase {
    String encodeData();

    void decodeData(String var1) throws ParseException;
}
