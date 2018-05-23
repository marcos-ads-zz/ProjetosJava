package modulo.metodos;

import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Marcos Junior
 */
public class CryptoBase64 {

    public static String encrypt(String strClearText) {
        String strData;
        strData = Base64.encodeBase64String(strClearText.getBytes());
        return strData;
    }

    public static String decrypt(String strEncrypted) {
        String strData;
        byte[] byt = Base64.decodeBase64(strEncrypted);
        strData = new String(byt);
        return strData;
    }
}
