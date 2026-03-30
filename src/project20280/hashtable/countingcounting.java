package project20280.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class countingcounting {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("sample_text.txt");

        ChainHashMap<String, Integer> counter = new ChainHashMap<>();

        Scanner scanner = new Scanner(f);

        while(scanner.hasNext()){
            String word = scanner.next();
            System.out.println("word: "+word);

            //if word not in hashmap add it with count 1

            //if word in hashmap, increment count by 1
        }
    }
}
