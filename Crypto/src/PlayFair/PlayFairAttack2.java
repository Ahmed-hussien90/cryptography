package PlayFair;

import java.io.*;
import java.util.*;

public class PlayFairAttack2 {

    static ArrayList freqChars = new ArrayList<>();
    static LinkedHashMap<Character, Integer> charMap = new LinkedHashMap<>();
    static ArrayList characters;
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        String orderFreqChar = "etaoinshrdlcumwfgypbvkjxqz";

        br = new BufferedReader(new FileReader("in.txt"));
        bw = new BufferedWriter(new FileWriter("plaintext.txt"));

        for (char c : orderFreqChar.toCharArray()) {
            freqChars.add(c);
        }

        StringBuilder decryptedString = new StringBuilder();
        String contentLine = br.readLine();
        while (contentLine != null) {
            decryptedString.append(contentLine);
            decryptedString.append("\n");
            contentLine = br.readLine();
        }

        OrderStr(decryptedString.toString());

        characters = new ArrayList<>(charMap.keySet());

        decrypt(decryptedString.toString());


        br.close();
        bw.close();

    }

    private static void OrderStr(String str) {

        char[] strArray = str.toLowerCase().toCharArray();

        HashMap<Character, Integer> m = new HashMap<>();

        for (char c : strArray) {
            if (m.containsKey(c)) {
                m.put(c, m.get(c) + 1);
            } else {
                m.put(c, 1);
            }
        }

        m.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> charMap.put(x.getKey(), x.getValue()));

    }


    static void decrypt(String str) throws IOException {

        StringBuilder sb = new StringBuilder();

        System.out.println(charMap.toString());
        System.out.println(characters);
        System.out.println(freqChars);


        for (int i = 0; i < str.length(); i++) {

            if (str.toLowerCase().charAt(i) >= 'a' && str.toLowerCase().charAt(i) <= 'z')
                sb.append(freqChars.get(characters.indexOf(str.toLowerCase().charAt(i))));
            else
                sb.append(str.toLowerCase().charAt(i));
        }
        bw.write(sb.toString());
    }

    static void encrypt(String str) throws IOException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.toLowerCase().charAt(i) >= 'a' && str.toLowerCase().charAt(i) <= 'z')
                sb.append(characters.get(freqChars.indexOf(str.toLowerCase().charAt(i))));
            else
                sb.append(str.toLowerCase().charAt(i));
        }
        bw.write(sb.toString());

    }

}
