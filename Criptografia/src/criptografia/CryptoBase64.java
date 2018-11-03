package criptografia;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Marcos Junior
 */
public class CryptoBase64 {

    public static String encrypt(String strClearText) {
        return Base64.encodeBase64String(strClearText.getBytes());
    }

    public static String decrypt(String strEncrypted) {
        byte[] byt = Base64.decodeBase64(strEncrypted);
        return new String(byt);
    }
}
