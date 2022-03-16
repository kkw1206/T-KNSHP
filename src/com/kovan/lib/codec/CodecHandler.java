package com.kovan.lib.codec;


import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CodecHandler {
    public CodecHandler() {
    }

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        System.out.println("================================TEST START================================");
        System.out.println("================================인코딩 시작================================");
        HeaderTemplate sendHt = new HeaderTemplate();
        sendHt.setHeaderTemplateAttribute(1, "MSG_VERSION", 4, "AN", false);
        sendHt.setHeaderTemplateAttribute(2, "MSG_CODE", 4, "N", false);
        sendHt.setHeaderTemplateAttribute(3, "MSG_LENGTH", 4, "N", true);
        sendHt.setHeaderTemplateAttribute(4, "CONTENT_TYPE", 8, "AN", false);
        sendHt.setHeaderTemplateAttribute(5, "MSG_CHARSET", 8, "AN", false);
        sendHt.setHeaderTemplateAttribute(6, "MSG_HMAC", 12, "AN", false);
        CodecFixedLenNJson payPacketEncoder = new CodecFixedLenNJson("EUC-KR");
        payPacketEncoder.setHeaderTemplate(sendHt);
        payPacketEncoder.setAttributeHeader("MSG_VERSION", "1.0");
        payPacketEncoder.setAttributeHeader("MSG_CODE", "1000");
        payPacketEncoder.setAttributeHeader("CONTENT_TYPE", "JSON");
        payPacketEncoder.setAttributeHeader("MSG_CHARSET", "EUC-KR");
        payPacketEncoder.setAttributeHeader("MSG_HMAC", "");
        payPacketEncoder.setAttributeData("MID", "M123456789");
        payPacketEncoder.setAttributeData("CID", "C123456789");
        payPacketEncoder.setAttributeData("PAY_METHOD", "CC");
        payPacketEncoder.setAttributeData("TID", "123456789");
        payPacketEncoder.setAttributeData("ORDERNO", "6789");
        String encodedData = payPacketEncoder.encodeData();
        String encodeHeader = "";
        String encodePacket = "";
        encodeHeader = payPacketEncoder.encodeHeader();
        encodePacket = payPacketEncoder.encode();
        System.out.println("[encodedData\t]" + encodedData);
        System.out.println("[encodeHeader\t]" + encodeHeader);
        System.out.println("[encodePacket\t]" + encodePacket);
        System.out.println("================================인코딩 끝================================");
        System.out.println("================================디코딩 시작================================");
        String recvPacket = "1.0 10000092JSON    EUC-KR              {\"TID\":\"123456789\",\"ORDERNO\":\"6789\",\"MID\":\"M123456789\",\"PAY_METHOD\":\"CC\",\"CID\":\"C123456789\"}";
        System.out.println("[recvPacket\t]" + recvPacket);
        CodecFixedLenNJson recvPacketDecoder = new CodecFixedLenNJson();
        recvPacketDecoder.setHeaderTemplate(sendHt);
        recvPacketDecoder.decodeHeader(recvPacket);
        recvPacketDecoder.decodeData(recvPacket.substring(recvPacketDecoder.getHeaderLength()));
        recvPacketDecoder.decode(recvPacket);
        List<String> printList = new ArrayList();
        printList.add(recvPacketDecoder.getAttributeHeader("MSG_VERSION"));
        printList.add(recvPacketDecoder.getAttributeHeader("MSG_CODE"));
        printList.add(recvPacketDecoder.getAttributeHeader("MSG_LENGTH"));
        printList.add(recvPacketDecoder.getAttributeHeader("CONTENT_TYPE"));
        printList.add(recvPacketDecoder.getAttributeHeader("MSG_CHARSET"));
        printList.add(recvPacketDecoder.getAttributeHeader("MSG_HMAC"));
        printList.add(recvPacketDecoder.getAttributeData("MID"));
        printList.add(recvPacketDecoder.getAttributeData("CID"));
        printList.add(recvPacketDecoder.getAttributeData("PAY_METHOD"));
        printList.add(recvPacketDecoder.getAttributeData("TID"));
        printList.add(recvPacketDecoder.getAttributeData("ORDERNO"));

        for(int i = 0; i < printList.size(); ++i) {
            System.out.println("[" + Integer.toString(i + 1) + "번째 element\t]" + (String)printList.get(i));
        }

        System.out.println("================================디코딩 끝================================");
        System.out.println("================================TEST END================================");
    }
}
