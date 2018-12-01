package sample;


import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class Decrypt {
    public static void decrypt(JFXTextArea inEncrypt, JFXTextArea outEncrypt, JFXTextField keyField) {
        String decmsg = inEncrypt.getText();
        String hakala = "";
        String key = keyField.getText();
        int keylen = key.length();
        int msglen = decmsg.length();
        int j;
        j = 0;
        j = 0;
        for (int i = 0; i < msglen; i++) {
            hakala = hakala + (char) (decmsg.charAt(i) ^ key.charAt(j));
            j++;
            if (j == keylen) {
                j = 0;
            }
        }
//        System.out.println("Decoded message: " + decmsg);
        outEncrypt.setText(hakala);
    }
}

