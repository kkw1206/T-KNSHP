package com.kovan.lib.cipher.ca.csr;

import java.math.BigInteger;

public interface Signer {
    SignerWithSerial setSerialNumber(BigInteger var1);

    SignerWithSerial setRandomSerialNumber();
}
