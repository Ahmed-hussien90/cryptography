package CeasarCipher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Caesar {

  private final int key;

  public Caesar(int key) {
    this.key = key;
  }

  public String encrypt(String plainText) {
    String res = "";
    for (int i = 0; i < plainText.length(); i++) {
      if (plainText.charAt(i) == ' ') {
        res += " ";
      } else {
        res += (char) ('a' + (plainText.charAt(i) - 'a' + key) % 26);
      }
    }
    return res;
  }

  public String decrypt(String cipherText) {
    String res = "";
    for (int i = 0; i < cipherText.length(); i++) {
      if (cipherText.charAt(i) == ' ') {
        res += " ";
      } else {
        res += (char) ('a' + (cipherText.charAt(i) - 'a' - key + 26) % 26);
      }
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
