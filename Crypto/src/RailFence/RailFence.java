package RailFence;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class RailFence {
    int key;

    public RailFence(File keyFile) throws Exception {

        Scanner s = new Scanner(keyFile);
        key = s.nextInt();
        s.close();

    }

    public File encrypt(File plainTextFile) throws Exception {
        Scanner s = new Scanner(plainTextFile);
        String plain = s.nextLine().toLowerCase();
        int k = plain.length();
        char Mat[][] = new char[key][k];
        File output = new File("output3.txt");
        PrintWriter pw = new PrintWriter(output);

        int row = 0, col = 0;
        boolean flag = true;

        for (int i = 0; i < k; i++) {

            Mat[row][col] = plain.charAt(i);

            if (row == key - 1) {
                flag = false;
            }
            if (row == 0) {
                flag = true;
            }

            if (flag) {
                row++;
            } else {
                row--;
            }
            col++;

        }
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < k; j++) {
                if (Mat[i][j] != '\u0000') {
                    pw.append(Mat[i][j]);
                }
            }
        }

        pw.close();
        s.close();

        return output;
    }

    public File decrypt(File cipherTextFile) throws Exception {
        Scanner s = new Scanner(cipherTextFile);
        String cipher = s.nextLine().toLowerCase();
        int k = cipher.length();
        char Mat[][] = new char[key][k];
        File output = new File("output4.txt");
        PrintWriter pw = new PrintWriter(output);

        boolean flag = true;

        int row = 0, col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                flag = true;
            if (row == key - 1)
                flag = false;

            Mat[row][col++] = '#';

            if (flag) row++;
            else row--;
        }

        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (Mat[i][j] == '#' && index < cipher.length())
                    Mat[i][j] = cipher.charAt(index++);

        row = 0;
        col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                flag = true;
            if (row == key - 1)
                flag = false;

            pw.append(Mat[row][col++]);

            if (flag) row++;
            else row--;
        }

        pw.close();
        s.close();

        return output;
    }

}
