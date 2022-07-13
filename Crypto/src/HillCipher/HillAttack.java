package HillCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class HillAttack {
    static int plainMat[][];
    static int cipherMat[][];
    static int keyMat[][];
    static int plainInverseMat[][];
    static int n;

    /*
       should return the key
       plain text sample file and cipher text sample file format:
       first line contains integer n identity the matrix size
       followed by n line and each line contains n number
      example
      plain text file:
      2
      7 8
      11 11
      cipher text file
      2
      7 2
      17 25
       */
    public File attack(File plainTextSampleFile, File cipherTextSampleFile) throws Exception {

        File output = new File("output6.txt");
        PrintWriter pw = new PrintWriter(output);

        fillMat(plainTextSampleFile, cipherTextSampleFile);
        plainInverseMat = matrixInverse(n);

        keyMat = new int[n][n];

        pw.println(n + "");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMat[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    keyMat[i][j] += cipherMat[k][i] * plainInverseMat[j][k];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pw.append(keyMat[j][i] % 26 + " ");  //printing matrix element
            }
            pw.println();
        }

        pw.close();

        return output;
    }

    private static void fillMat(File plainTextSampleFile, File cipherTextSampleFile) throws FileNotFoundException {

        Scanner plainFile = new Scanner(plainTextSampleFile);
        Scanner cipherFile = new Scanner(cipherTextSampleFile);

        n = plainFile.nextInt();
        cipherFile.nextInt();
        plainMat = new int[n][n];
        cipherMat = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                plainMat[i][j] = plainFile.nextInt();
                cipherMat[i][j] = cipherFile.nextInt();
            }
        }
        plainFile.close();
        cipherFile.close();
    }


    private static int[][] matrixInverse(int n) {

        int MatInverse[][] = new int[n][n];
        int revMatrix[][] = new int[n][n];

        if (n == 2) {
            revMatrix[0][0] = plainMat[1][1];
            revMatrix[1][1] = plainMat[0][0];
            revMatrix[0][1] = -plainMat[0][1];
            revMatrix[1][0] = -plainMat[1][0];
        } else {
            revMatrix[0][0] = ((plainMat[1][1] * plainMat[2][2]) - (plainMat[1][2] * plainMat[2][1]));
            revMatrix[0][1] = -((plainMat[0][1] * plainMat[2][2]) - (plainMat[0][2] * plainMat[2][1]));
            revMatrix[0][2] = ((plainMat[0][1] * plainMat[1][2]) - (plainMat[0][2] * plainMat[1][1]));

            revMatrix[1][0] = -((plainMat[1][0] * plainMat[2][2]) - (plainMat[1][2] * plainMat[2][0]));
            revMatrix[1][1] = ((plainMat[0][0] * plainMat[2][2]) - (plainMat[0][2] * plainMat[2][0]));
            revMatrix[1][2] = -((plainMat[0][0] * plainMat[1][2]) - (plainMat[0][2] * plainMat[1][0]));

            revMatrix[2][0] = ((plainMat[1][0] * plainMat[2][1]) - (plainMat[1][1] * plainMat[2][0]));
            revMatrix[2][1] = -((plainMat[0][0] * plainMat[2][1]) - (plainMat[0][1] * plainMat[2][0]));
            revMatrix[2][2] = ((plainMat[0][0] * plainMat[1][1]) - (plainMat[0][1] * plainMat[1][0]));
        }

        int det = new BigInteger(String.valueOf(MatDeterminant(n))).mod(new BigInteger("26")).intValue();
        int detInverse = new BigInteger(String.valueOf(det)).modInverse(new BigInteger("26")).intValue();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MatInverse[i][j] = new BigInteger(String.valueOf(revMatrix[i][j] * detInverse)).mod(new BigInteger("26")).intValue();
            }
        }

        return MatInverse;
    }

    private static int MatDeterminant(int n) {
        int x, y, z, determinant = 0;

        if (n == 2)
            determinant = (plainMat[0][0] * plainMat[1][1]) - (plainMat[0][1] * plainMat[1][0]);
        else {
            x = (plainMat[1][1] * plainMat[2][2]) - (plainMat[2][1] * plainMat[1][2]);
            y = (plainMat[1][0] * plainMat[2][2]) - (plainMat[2][0] * plainMat[1][2]);
            z = (plainMat[1][0] * plainMat[2][1]) - (plainMat[2][0] * plainMat[1][1]);

            determinant = (plainMat[0][0] * x) - (plainMat[0][1] * y) + (plainMat[0][2] * z);

        }
        return determinant;

    }


}
