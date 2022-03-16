package com.kovan.lib.cipher.ca.certificate.impl;


import com.kovan.lib.cipher.ca.certificate.DistinguishedName;
import com.kovan.lib.cipher.ca.certificate.DnBuilder;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

public class DnBuilderImpl implements DnBuilder {
    private final X500NameBuilder builder = new X500NameBuilder();

    public DnBuilderImpl() {
    }

    public DnBuilder setCn(String cn) {
        this.builder.addRDN(BCStyle.CN, cn);
        return this;
    }

    public DnBuilder setCommonName(String cn) {
        return this.setCn(cn);
    }

    public DnBuilder setL(String l) {
        this.builder.addRDN(BCStyle.L, l);
        return this;
    }

    public DnBuilder setLocalityName(String l) {
        return this.setL(l);
    }

    public DnBuilder setSt(String st) {
        this.builder.addRDN(BCStyle.ST, st);
        return this;
    }

    public DnBuilder setStateOrProvinceName(String st) {
        return this.setSt(st);
    }

    public DnBuilder setO(String o) {
        this.builder.addRDN(BCStyle.O, o);
        return this;
    }

    public DnBuilder setOrganizationName(String o) {
        return this.setO(o);
    }

    public DnBuilder setOu(String ou) {
        this.builder.addRDN(BCStyle.OU, ou);
        return this;
    }

    public DnBuilder setOrganizationalUnitName(String ou) {
        return this.setOu(ou);
    }

    public DnBuilder setC(String c) {
        this.builder.addRDN(BCStyle.C, c);
        return this;
    }

    public DnBuilder setCountryName(String c) {
        return this.setC(c);
    }

    public DnBuilder setStreet(String street) {
        this.builder.addRDN(BCStyle.STREET, street);
        return this;
    }

    public DistinguishedName build() {
        X500Name name = this.builder.build();
        return new BcX500NameDnImpl(name);
    }
}
