package MonoAlphbetec;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MonoAlphabeticAttack {

    /**
     * Given a plain text (plaintTextToLearn) with it's corresponding cipher text (cipherTextToLearn)
     * to learn. Attack the provided cipher text (cipherText).
     */
    public String attack(String plaintTextToLearn, String cipherTextToLearn, String cipherText) {
        String res = "";

        Map<Character, Character> learnMap = new HashMap<>();

        for (int i = 0; i < plaintTextToLearn.length(); i++) {
            char currPlainChar = plaintTextToLearn.trim().charAt(i);
            char currCipherChar = cipherTextToLearn.trim().charAt(i);

            if (!learnMap.containsKey(currCipherChar)) {
                learnMap.put(currCipherChar, currPlainChar);
            }

        }

        System.out.println(learnMap);

        for (int i = 0; i < cipherText.length(); i++) {

            res += learnMap.get(cipherText.charAt(i));

        }

        return res;
    }

    public File attack(File plaintTextToLearnFile, File cipherTextToLearnFile, File cipherTextFile)
            throws IOException {

        File output = new File("decrypt.txt");
        PrintWriter pw = new PrintWriter(output);

        Scanner plaintTextToLearn = new Scanner(plaintTextToLearnFile);
        Scanner cipherTextToLearn = new Scanner(cipherTextToLearnFile);
        Scanner cipherText = new Scanner(cipherTextFile);


        pw.println(attack(plaintTextToLearn.nextLine(), cipherTextToLearn.nextLine(), cipherText.nextLine()));


        pw.flush();
        pw.close();

        return output;
    }
}
