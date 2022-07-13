package CeasarCipher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AffineCaesarAttack {
    public static void main(String[] args) {

        List<String> li = attack("xzd qhib yz tzkib yqvt jdbtyvzu");

        //in a=5 , b=7 this message decrypted
        for (String s : li) {
            System.out.println(s);
        }

    }

    public static List<String> attack(String cipherText) {
        List<String> res = new ArrayList<>();
        int a[] = {1,3,5,7,9,11,15,17,19,21,23,25};

        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < 26; j++) {
                res.add("a:" + i + "  b:" + j + "  " + new AffineCaesar(i, j).decrypt(cipherText));
            }
        }
        return res;
    }

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
