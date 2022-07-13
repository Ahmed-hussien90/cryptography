package OneTimePad;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class OneTimePad {
    int n;
    String key;

    // the keyFile should contain a word with the length of the maximum length of any plaintext need to be encrypted
    public OneTimePad(File keyFile) throws Exception {
        Scanner s = new Scanner(keyFile);
        key = s.nextLine();
        n = key.length();
        s.close();
    }

    public File encrypt(File plainTextFile) throws Exception {
        Scanner s = new Scanner(plainTextFile);
        String plain = s.nextLine();
        int k = plain.length();
        File output = new File("output.txt");
        PrintWriter pw = new PrintWriter(output);

        for (int i = 0; i < k; i++) {
            pw.append((char) ('a' + ((plain.charAt(i) - 'a') + (key.charAt(i) - 'a')) % 26));

        }
        pw.close();
        s.close();

        return output;
    }

    public File decrypt(File cipherTextFile) throws Exception {
        Scanner s = new Scanner(cipherTextFile);
        String cipher = s.nextLine();
        int k = cipher.length();
        File output = new File("output2.txt");
        PrintWriter pw = new PrintWriter(output);

        for (int i = 0; i < k; i++) {
            int ch = ((cipher.charAt(i) - 'a') - (key.charAt(i) - 'a'));
            if (ch < 0) {
                ch += 26;
            }

            pw.append((char) ('a' + ch % 26));
        }
        pw.close();
        s.close();

        return output;
    }
}
