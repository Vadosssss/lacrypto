package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
public class Encrypt {
    public static void crypt(JFXTextArea inEncrypt, JFXTextArea outEncrypt, JFXTextField keyField) {
        String msg = inEncrypt.getText();
        String encmsg = "";
        String decmsg = "";
        String key = keyField.getText();
        int keylen = key.length();
        int msglen = msg.length();
        int j;
        j = 0;
        for (int i = 0; i < msglen; i++) {
            encmsg = encmsg + (char) (msg.charAt(i) ^ key.charAt(j));
            j++;
            if (j == keylen) {
                j = 0;
            }
        }

        outEncrypt.setText(encmsg);
    }
}


