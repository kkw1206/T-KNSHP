package com.kovan.lib.cipher.ca.certificate;

public interface DnBuilder {
    DnBuilder setCn(String var1);

    DnBuilder setCommonName(String var1);

    DnBuilder setL(String var1);

    DnBuilder setLocalityName(String var1);

    DnBuilder setSt(String var1);

    DnBuilder setStateOrProvinceName(String var1);

    DnBuilder setO(String var1);

    DnBuilder setOrganizationName(String var1);

    DnBuilder setOu(String var1);

    DnBuilder setOrganizationalUnitName(String var1);

    DnBuilder setC(String var1);

    DnBuilder setCountryName(String var1);

    DnBuilder setStreet(String var1);

    DistinguishedName build();
}
