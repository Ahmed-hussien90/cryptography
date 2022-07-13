package AES;

import java.math.BigInteger;
import java.util.Scanner;

public class AESKey_1827230 {

    private static final String S_box[][] = {
            {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
            {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
            {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
            {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
            {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
            {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
            {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
            {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
            {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
            {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
            {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
            {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
            {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
            {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
            {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
            {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}

    };

    private static final String R_COL[] = {"01", "02", "04", "08", "10", "20", "40", "80", "1B", "36"};


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the key");

        String key = scanner.nextLine().toLowerCase().replaceAll(" ", "");

        String[] result = generateKeys(key);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }


    private static String[] generateKeys(String key) {
        String words[] = new String[44];
        words[0] = key.substring(0, 8);
        words[1] = key.substring(8, 16);
        words[2] = key.substring(16, 24);
        words[3] = key.substring(24, 32);

        for (int i = 4; i < 44; i++) {

            String temp = words[i - 1];

            if (i % 4 == 0) {
                temp = SubWord(RotWord(temp));
                temp = XOR(temp, R_COL[(i / 4) - 1].concat("000000"));
            }

            words[i] = XOR(words[i - 4], temp);

        }

        return words;
    }

    private static String RotWord(String word) {
        StringBuilder sb = new StringBuilder(word);
        String temp = sb.substring(0, 2);
        sb.delete(0, 2);
        sb.append(temp);
        return sb.toString();
    }


    private static String SubWord(String word) {
        StringBuilder sb = new StringBuilder(word);

        for (int i = 0; i < sb.length() - 1; i += 2) {

            String s = word.substring(i, i + 2);

            int ii = Integer.parseInt(s.charAt(0) + "", 16);
            int jj = Integer.parseInt(s.charAt(1) + "", 16);

            sb.replace(i, i + 2, S_box[ii][jj]);

        }

        return sb.toString();
    }

    private static String XOR(String s1, String s2) {

        BigInteger i1 = new BigInteger(s1, 16);
        BigInteger i2 = new BigInteger(s2, 16);
        BigInteger res = i1.xor(i2);
        String s3 = res.toString(16);

        return s3;
    }

}


