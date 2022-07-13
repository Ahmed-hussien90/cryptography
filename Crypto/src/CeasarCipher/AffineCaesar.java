package CeasarCipher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AffineCaesar {

    private final int a;
    private final int b;

    public AffineCaesar(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public String encrypt(String plainText) {
        String s = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) == ' ') {
                s += " ";
            } else {
                s += (char) ('a' + ((plainText.charAt(i) - 'a') * a + b) % 26);
            }
        }
        return s;
    }

    public String decrypt(String cipherText) {

        String s = "";
        int inverse = 0;

        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                inverse = i;
            }
        }

        for (int i = 0; i < cipherText.length(); i++) {

            if (cipherText.charAt(i) == ' ') {
                s += " ";
            } else {
                s = s + Character.toLowerCase((char) (((inverse * ((Character.toUpperCase(cipherText.charAt(i)) + 'A' - b)) % 26)) + 'A'));
            }
        }
        return s;
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
        Scanner input = new Scanner(cipherTextFile);

        File output = new File("decrypt.txt");
        PrintWriter pw = new PrintWriter(output);

        while (input.hasNext()) {
            pw.println(decrypt(input.nextLine()));
        }

        pw.flush();
        pw.close();

        return output;
    }
}

