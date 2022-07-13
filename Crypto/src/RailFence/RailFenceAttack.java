package RailFence;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class RailFenceAttack {

  // the cipherTextFile should contain the cipher text needed to be decrypted.
  // the result should be all the possible combination of the plaintext:
  // that can be obtained by number of levels equals to 2 -> cipher,length
  // print each possible solution in a line starting with the level 2
  // run the unit test and see the unit test files for more information
  public File attack(File cipherTextFile) throws Exception {
    File keyF = new File("keys.txt");
    PrintWriter pw = new PrintWriter(keyF);
    pw.write(2);
    File output = new File("output.txt");
    PrintWriter pw2 = new PrintWriter(output);

    for(int i =2 ;i<10 ;i++) {

      pw.write(i);
      RailFence r = new RailFence(keyF);
      File decryptFile = r.decrypt(cipherTextFile);
      Scanner s = new Scanner(decryptFile);
      String cipher = s.nextLine();

      pw2.append(cipher);
      pw2.append("\n");
      s.close();

    }

    pw.close();
    pw2.close();

    return output;
  }
}
