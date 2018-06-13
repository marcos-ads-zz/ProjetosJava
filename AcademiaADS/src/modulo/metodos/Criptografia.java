package modulo.metodos;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Marcos Junior
 */
public class Criptografia {

    //private static String key16 = "2728387d355a3dfa";//(128 / 8 = 128) 16 caracteres = 128 bits

    public static byte[] encryAES(String strTextolimpo, String strKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey key = new SecretKeySpec(strKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] mensagemEncriptada = cipher.doFinal(strTextolimpo.getBytes());
        return mensagemEncriptada;
    }

    public static byte[] decryAES(byte[] strEncryText, String strKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey key = new SecretKeySpec(strKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] mensagemDescriptada = cipher.doFinal(strEncryText);
        return mensagemDescriptada;
    }

    public static byte[] encryBlowfish(String strTexotlimpo, String strKey) throws Exception {
        byte[] encrypted;
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            encrypted = cipher.doFinal(strTexotlimpo.getBytes());

        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception(e);
        }
        return encrypted;
    }

    public static byte[] decryBlowfish(byte[] strEncryText, String strKey) throws Exception {
        byte[] decrypted;
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            decrypted = cipher.doFinal(strEncryText);

        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception(e);
        }
        return decrypted;
    }

}
