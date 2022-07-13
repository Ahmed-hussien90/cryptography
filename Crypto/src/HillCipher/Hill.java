package HillCipher;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Hill {

    static int[][] keyMatrix;
    static int KeyInverse[][];

    public static void main(String[] args) throws Exception {
        Hill hill = new Hill(new File("in.txt"));

        hill.decrypt(new File("files/HillCipherTextFileScenario1.txt"));

    }

    public Hill(File keyFile) throws Exception {
        Scanner scanner = new Scanner(keyFile);
        int n = scanner.nextInt();
        keyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
    }

    public File encrypt(File plainTextFile) throws Exception {
        Scanner scanner = new Scanner(plainTextFile);
        File output = new File("output.txt");
        PrintWriter pw = new PrintWriter(output);
        String plain = scanner.nextLine();

        int n = keyMatrix.length;

        while (plain.length() % n != 0) {
                plain = plain + "x";
        }

        System.out.println(n);
        System.out.println(plain.length());
        for (int i = 0; i <= plain.length() - n; i += n) {
            pw.append(encryptBlock(plain.substring(i, i + n)));
        }

        pw.close();
        scanner.close();

        return output;
    }


    public String encryptBlock(String str) {

        String s = "";
        int resChar = 0;
        int n = keyMatrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resChar += ((str.charAt(j) - 'a') * keyMatrix[i][j]);
            }
            s += (char) (('a' + (resChar % 26)));
            resChar = 0;
        }

        return s;

    }

    public static File decrypt(File cipherTextFile) throws Exception {

        Scanner scanner = new Scanner(cipherTextFile);
        File output2 = new File("output2.txt");
        PrintWriter pw = new PrintWriter(output2);
        String cipher = scanner.nextLine();

        int n = keyMatrix.length;
        KeyInverse = matrixInverse(n);


        System.out.println(n);
        System.out.println(cipher.length());
        for (int i = 0; i <= cipher.length() - n; i += n) {
            pw.append(decryptBlock(cipher.substring(i, i + n)));
        }

        pw.close();
        scanner.close();

        return output2;

    }

    public static String decryptBlock(String str) {

        String s = "";
        int resChar = 0;
        int n = keyMatrix.length;
        KeyInverse = matrixInverse(n);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(KeyInverse[i][j]);
                resChar += ((str.charAt(j) - 'a') * KeyInverse[i][j]);
            }
            s += (char) (('a' + (resChar % 26)));
            resChar = 0;
        }

        return s;

    }

    private static int[][] matrixInverse(int n) {

        int MatInverse[][] = new int[n][n];
        int revMatrix[][] = new int[n][n];

        if (n == 2) {
            revMatrix[0][0] = keyMatrix[1][1];
            revMatrix[1][1] = keyMatrix[0][0];
            revMatrix[0][1] = -keyMatrix[0][1];
            revMatrix[1][0] = -keyMatrix[1][0];
        } else {
            revMatrix[0][0] = ((keyMatrix[1][1] * keyMatrix[2][2]) - (keyMatrix[1][2] * keyMatrix[2][1]));
            revMatrix[0][1] = -((keyMatrix[0][1] * keyMatrix[2][2]) - (keyMatrix[0][2] * keyMatrix[2][1]));
            revMatrix[0][2] = ((keyMatrix[0][1] * keyMatrix[1][2]) - (keyMatrix[0][2] * keyMatrix[1][1]));

            revMatrix[1][0] = -((keyMatrix[1][0] * keyMatrix[2][2]) - (keyMatrix[1][2] * keyMatrix[2][0]));
            revMatrix[1][1] = ((keyMatrix[0][0] * keyMatrix[2][2]) - (keyMatrix[0][2] * keyMatrix[2][0]));
            revMatrix[1][2] = -((keyMatrix[0][0] * keyMatrix[1][2]) - (keyMatrix[0][2] * keyMatrix[1][0]));

            revMatrix[2][0] = ((keyMatrix[1][0] * keyMatrix[2][1]) - (keyMatrix[1][1] * keyMatrix[2][0]));
            revMatrix[2][1] = -((keyMatrix[0][0] * keyMatrix[2][1]) - (keyMatrix[0][1] * keyMatrix[2][0]));
            revMatrix[2][2] = ((keyMatrix[0][0] * keyMatrix[1][1]) - (keyMatrix[0][1] * keyMatrix[1][0]));
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
            determinant = (keyMatrix[0][0] * keyMatrix[1][1]) - (keyMatrix[0][1] * keyMatrix[1][0]);
        else {
            x = (keyMatrix[1][1] * keyMatrix[2][2]) - (keyMatrix[2][1] * keyMatrix[1][2]);
            y = (keyMatrix[1][0] * keyMatrix[2][2]) - (keyMatrix[2][0] * keyMatrix[1][2]);
            z = (keyMatrix[1][0] * keyMatrix[2][1]) - (keyMatrix[2][0] * keyMatrix[1][1]);

            determinant = (keyMatrix[0][0] * x) - (keyMatrix[0][1] * y) + (keyMatrix[0][2] * z);

        }
        return determinant;

    }

}

