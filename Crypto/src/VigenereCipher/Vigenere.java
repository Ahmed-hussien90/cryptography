package VigenereCipher;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Vigenere {
    String key;
    int m;

    public Vigenere(File keyFile) throws Exception {
        Scanner s = new Scanner(keyFile);
        key = s.nextLine();
        m = key.length();
        s.close();
    }

    public File encrypt(File plainTextFile) throws Exception {

        Scanner scanner = new Scanner(plainTextFile);
        String plain = scanner.nextLine();
        File output = new File("output3.txt");
        PrintWriter pw = new PrintWriter(output);

        for (int i = 0; i < plain.length(); i++) {
            pw.append((char) ('a' + ((plain.charAt(i) - 'a') + (key.charAt(i % m) - 'a')) % 26));

        }
        pw.close();
        scanner.close();
        return output;
    }

    public File decrypt(File cipherTextFile) throws Exception {
        Scanner scanner = new Scanner(cipherTextFile);
        String cipher = scanner.nextLine();
        File output = new File("output4.txt");
        PrintWriter pw = new PrintWriter(output);

        for (int i = 0; i < cipher.length(); i++) {

            int c = ((cipher.charAt(i) - 'a') - (key.charAt(i % m) - 'a'));

            if (c < 0) {
                c += 26;
            }

            pw.append((char) ('a' + c % 26));

        }
        pw.close();
        scanner.close();
        return output;
    }
}
