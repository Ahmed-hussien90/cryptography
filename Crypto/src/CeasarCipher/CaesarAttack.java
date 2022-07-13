package CeasarCipher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaesarAttack {

  /**
   * Return list of all possible plain texts that can be determined by a given cipher text.
   */
  public List<String> attack(String cipherText) {

    List<String> res = new ArrayList<>();

    for (int i = 1; i < 26; i++) {
      res.add(new Caesar(i).decrypt(cipherText));
    }

    return res;
  }

  /**
   * Return list of all possible plain texts in file that can be determined by a given cipher text.
   */
  public File attack(File cipherTextFile) throws IOException {

    Scanner input = new Scanner(cipherTextFile);

    File output = new File("attack_result.txt");
    PrintWriter pw = new PrintWriter(output);

    while (input.hasNext()) {
      String cipherText = input.nextLine();
      pw.println(attack(cipherText));
    }

    pw.flush();
    pw.close();

    return output;
  }
}
