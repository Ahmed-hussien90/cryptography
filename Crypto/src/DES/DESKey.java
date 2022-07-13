package DES;

import java.math.BigInteger;
import java.util.Scanner;

public class DESKey {

    private static final int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    private static final int[] PC2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };

    private static final int[] rotations = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };


    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);

        String key = s.nextLine();

        //convert hex to binary
        String key1 = hexToBin(key);
        System.out.println(key1);

        String[] Reskeys = getSubKeys(key1);


        for (int i = 0; i < Reskeys.length; i++) {
            System.out.println(Reskeys[i]);
        }


    }

    private static String[] getSubKeys(String key) {
        String subkeys[] = new String[16];

        key = permute(PC1, key);
        System.out.println(key);
        // split into 28 bit left and right
        String c = key.substring(0, 28);
        System.out.println(c);
        String d = key.substring(28);
        System.out.println(d);

        for (int i = 0; i < 16; i++) {
            // rotate the 28-bit values
            if (rotations[i] == 1) {
                StringBuilder s = new StringBuilder(c);
                char firstc = s.charAt(0);
                s.deleteCharAt(0);
                s.append(firstc);
                c = s.toString();
                StringBuilder s2 = new StringBuilder(d);
                char firstd = s2.charAt(0);
                s2.deleteCharAt(0);
                s2.append(firstd);
                d = s2.toString();

            } else {

                StringBuilder s = new StringBuilder(c);
                String firstc = s.substring(0, 2);
                s.deleteCharAt(0);
                s.deleteCharAt(1);
                s.append(firstc);
                c = s.toString();
                StringBuilder s2 = new StringBuilder(d);
                String firstd = s2.substring(0, 2);
                s2.deleteCharAt(0);
                s2.deleteCharAt(1);
                s2.append(firstd);
                d = s2.toString();

            }

            String cd = c.concat(d);
            String subkey = permute(PC2, cd);
            subkeys[i] = new BigInteger(subkey, 2).toString(16);
        }

        return subkeys;
    }


    private static String permute(int[] PC, String key) {
        char[] arr = new char[PC.length];
        for (int i = 0; i < PC.length; i++) {
            arr[i] = key.charAt(PC[i] - 1);
        }
        return String.valueOf(arr);

    }

    private static String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        hex = hex.replaceAll("a", "1010");
        hex = hex.replaceAll("b", "1011");
        hex = hex.replaceAll("c", "1100");
        hex = hex.replaceAll("d", "1101");
        hex = hex.replaceAll("e", "1110");
        hex = hex.replaceAll("f", "1111");
        return hex;
    }
}
//test
//0f1571c947d9e859