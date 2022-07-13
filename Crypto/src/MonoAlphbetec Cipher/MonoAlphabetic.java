package MonoAlphbetec;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MonoAlphabetic {

    /**
     * The array size should be 26 and the alphabetic letters should be distributed between all the
     * elements. key[0] should be the cipher letter of encrypting the letter 'a' and k[1] should be
     * the cipher letter of encrypting the letter 'b' and so on ...
     */
    private final char[] key;

    /**
     * Hint: create another char array with 26 elements let say keyRevert. Configure keyRevert[0] the
     * letter of decrypting the letter 'a' and keyRevert[1] the letter of decrypting the letter 'b'
     * and so on ...
     */

    private char[] keyRevert;

    public MonoAlphabetic(char[] key) {
        this.key = key;
        initKeyRevert(key);
    }

    private void initKeyRevert(char[] key) {

        keyRevert = new char[26];

        for (int i = 0; i < 26; i++) {
            keyRevert[i] = (char) ('a' + i);
        }
    }

    public String encrypt(String plainText) {
        String res = "";
        for (int i = 0; i < plainText.length(); i++) {
            int index = plainText.charAt(i) - 'a';
            if (index >= 0)
                res += key[index];
            else
                res += plainText.charAt(i);
        }

        return res;
    }

    public String decrypt(String cipherText) {
        String res = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int index = cipherText.charAt(i) - 'a';
            if (index >= 0)
                res += keyRevert[index];
            else
                res += cipherText.charAt(i);
        }

        return res;
    }

    public File encrypt(File plainTextFile) throws IOException {
        Scanner scanner = new Scanner(plainTextFile);

        File output = new File("encrypt.txt");
        PrintWriter pw = new PrintWriter(output);

        while (scanner.hasNext()) {
            pw.println(encrypt(scanner.nextLine()));
        }

        pw.flush();
        pw.close();

        return output;
    }

    public File decrypt(File cipherTextFile) throws IOException {
        Scanner scanner = new Scanner(cipherTextFile);

        File output = new File("encrypt.txt");
        PrintWriter pw = new PrintWriter(output);

        while (scanner.hasNext()) {
            pw.println(decrypt(scanner.nextLine()));
        }

        pw.flush();
        pw.close();

        return output;
    }
}
