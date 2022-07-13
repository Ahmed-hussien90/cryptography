package RowTransposition;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RowTransposition {
    int n;
    ArrayList<Integer> keys;

    public RowTransposition(File keyFile) throws Exception {
        Scanner s = new Scanner(keyFile);
        n = s.nextInt();
        keys = new ArrayList<>(n);
        s.nextLine();
        for (int i = 0; i < n; i++) {
            keys.add(s.nextInt());
        }
        s.close();
    }

    public File encrypt(File plainTextFile) throws Exception {
        Scanner s = new Scanner(plainTextFile);
        String plain = s.nextLine().toLowerCase();
        int k = plain.length();
        int x = (int) Math.ceil((double) k / n);
        System.out.println(x);
        char Mat[][] = new char[x][n];
        File output = new File("output5.txt");
        PrintWriter pw = new PrintWriter(output);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < n; j++) {
                Mat[i][j] = '*';
            }
        }

        int m = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < n; j++) {

                if (m < k) {
                    Mat[i][j] = plain.charAt(m++);
                }
                System.out.print(Mat[i][j] + " ");
            }
            System.out.println();
        }


        for (int j = 0; j < n; j++) {
            for (int i = 0; i < x; i++) {
                if (Mat[i][keys.indexOf(j)] != '*') {
                    pw.append(Mat[i][keys.indexOf(j)]);
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
        int x = (int) Math.ceil((double) k / n);
        System.out.println(x);
        char Mat[][] = new char[x][n];
        File output = new File("output6.txt");
        PrintWriter pw = new PrintWriter(output);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < n; j++) {
                Mat[i][j] = '*';
            }
        }

        int m = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < x; i++) {
                if (m < k) {
                    Mat[i][j] = cipher.charAt(m++);
                }
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Mat[i][j] + " ");
            }
            System.out.println();

        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < x; i++) {
                if (Mat[i][keys.indexOf(j)] != '\u0000') {
                    pw.append(Mat[i][keys.indexOf(j)]);
                }
            }
        }
        pw.close();
        s.close();

        return output;
    }
}
