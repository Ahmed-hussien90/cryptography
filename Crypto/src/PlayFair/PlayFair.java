package PlayFair;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayFair {

    public static void main(String[] args) {

        PlayFair playFair = new PlayFair("gravity");

        String plainText = "hello";

        System.out.println(playFair.encrypt(plainText));

    }

    private final PlayFairKey playFairKey;

    public PlayFair(String key) {
        this.playFairKey = new PlayFairKey(key);
    }

    public String encrypt(String plainText) {
        String res = "";

        if (plainText.length() % 2 != 0) {
            plainText = plainText.concat("x");
        }
        System.out.println(plainText);
        for (int i = 0; i < plainText.length() - 1; i += 2) {
            res += PlayFairKey.encrypt(plainText.charAt(i), plainText.charAt(i + 1));
        }

        return res;
    }

    public String decrypt(String cipherText) {
        String res = "";

        for (int i = 0; i < cipherText.length() - 1; i += 2) {
            res += PlayFairKey.decrypt(cipherText.charAt(i), cipherText.charAt(i + 1));
        }

        if (res.charAt(res.length() - 1) == 'x')
            return new StringBuilder(res).deleteCharAt(res.length() - 1).toString();


        return res;
    }

    public File encrypt(File plainTextFile) throws IOException {
        return null;
    }

    public File decrypt(File cipherTextFile) throws IOException {
        return null;
    }

    /**
     * Playfair key class that manage the initialization of the key matrix and the
     * encryption/decryption of two letters.
     */
    static class PlayFairKey {

        private static final int MATRIX_SIZE = 5;
        Map<Character, Boolean> repeated = new HashMap<>();

        private static final char[][] keyMatrix = new char[MATRIX_SIZE][MATRIX_SIZE];

        public PlayFairKey(String key) {
            for (int i = 0; i < 26; i++) {
                repeated.put((char) ('a' + i), true);
            }

            buildKeyMatrix(key);
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    System.out.print(keyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        /**
         * Initialization the key matrix.
         */
        private void buildKeyMatrix(String key) {

            String keyString = (key + "abcdefghijklmnopqrstuvwxyz").replace('j', 'i');

            int k = 0;
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    if (k < keyString.length() && repeated.get(keyString.charAt(k))) {
                        keyMatrix[i][j] = keyString.charAt(k);
                        repeated.put(keyString.charAt(k), false);
                    } else {
                        j--;
                    }
                    k++;
                }
            }

        }

        public static String encrypt(char ch1, char ch2) {
            String res = "";

            int I1 = rowPosition(ch1);
            int J1 = columnPosition(ch1);
            int I2 = rowPosition(ch2);
            int J2 = columnPosition(ch2);

            if (I1 == I2) {
                res += keyMatrix[I1][(J1 + 1) % 5];
                res += keyMatrix[I2][(J2 + 1) % 5];

            } else if (J1 == J2) {

                res += keyMatrix[(I1 + 1) % 5][J2];
                res += keyMatrix[(I2 + 1) % 5][J1];

            } else {
                res += keyMatrix[I1][J2];
                res += keyMatrix[I2][J1];
            }

            return res;
        }

        public static String decrypt(char ch1, char ch2) {
            String res = "";

            int I1 = rowPosition(ch1);
            int J1 = columnPosition(ch1);
            int I2 = rowPosition(ch2);
            int J2 = columnPosition(ch2);

            if (I1 == I2) {

                res += keyMatrix[I1][(J1 - 1) % 5 == -1 ? 4 : (J1 - 1) % 5];
                res += keyMatrix[I2][(J2 - 1) % 5 == -1 ? 4 : (J2 - 1) % 5];

            } else if (J1 == J2) {

                res += keyMatrix[(I1 - 1) % 5 == -1 ? 4 : (I1 - 1) % 5][J1];
                res += keyMatrix[(I2 - 1) % 5 == -1 ? 4 : (I2 - 1) % 5][J2];

            } else {
                res += keyMatrix[I1][J2];
                res += keyMatrix[I2][J1];
            }

            return res;
        }

        private static int rowPosition(char ch) {
            int pos = 0;
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    if (ch == keyMatrix[i][j]) {
                        pos = i;
                        break;
                    }
                }
            }
            return pos;
        }

        private static int columnPosition(char ch) {
            int pos = 0;
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    if (ch == keyMatrix[i][j]) {
                        pos = j;
                        break;
                    }
                }
            }
            return pos;
        }
    }
}