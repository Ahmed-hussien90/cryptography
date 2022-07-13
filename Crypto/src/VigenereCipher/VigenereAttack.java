package VigenereCipher;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class VigenereAttack {

    // should return a file with the key
    public File attack(File plainTextSampleFile, File cipherTextSampleFile) throws Exception {

        File output = new File("output5.txt");
        PrintWriter pw = new PrintWriter(output);
        Scanner plainFile = new Scanner(plainTextSampleFile);
        Scanner cipherFile = new Scanner(cipherTextSampleFile);

        String plain = plainFile.nextLine();
        String cipher = cipherFile.nextLine();
        int n = plain.length();
        String s = "";

        for (int i = 0; i < n; i++) {

            int c = ((cipher.charAt(i) - 'a') - (plain.charAt(i) - 'a'));

            if (c < 0) {
                c += 26;
            }

            char ch = (char) ('a' + c);

            if (s.length() > 3 && ch == s.charAt(0))
                break;

            s += ch;
        }

        pw.append(s);
        pw.close();
        cipherFile.close();
        plainFile.close();
        return output;
    }
}
