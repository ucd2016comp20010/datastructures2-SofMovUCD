package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class question6 {

    public static void partA(File f, ChainHashMap<String, Integer> counter) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        int[] collisions = new int[11];
        int hashGotten;

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();
            hashGotten = Math.abs(hash_poly(word, 41) % 11);


            //if word not in hashmap add it with count 1
            if(counter.bucketGet(hashGotten, word) == null){
                collisions[hashGotten]++; //increase only when a new item is added
                counter.bucketPut(hashGotten, word, 1); //add number
            }
            else{ //if word in hashmap, increment count by 1
                counter.bucketPut(hashGotten, word, counter.bucketGet(hashGotten, word)+1);
            }
        }
        System.out.println("Number of collisions is "+ (IntStream.of(collisions).sum()-11));
    }

    public static int hashCode(String s){
        int hash = 0;
        int skip = Math.max(1, s.length()/8);
        for(int i = 0; i < s.length(); i+= skip){
            hash = hash*37 + s.charAt(i);
        }
        return hash;
    }

    public static int hash_poly(String s, int a){
        int h = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            char s_i = (char) s.charAt(i);
            int v = s_i*((int) Math.pow(a, n-i-1));
            h += v;
        }
        return h;
    }

    public static int hash_cyclic(String s, int shift){
        int h = 0;
        for(int i = 0; i < s.length(); i++){
            h = (h << shift) | (h >> (32 - shift));
            h += (int) s.charAt(i);
        }
        return h;
    }

    static void main() throws FileNotFoundException {
        File f = new File("C:\\Users\\mysof\\IdeaProjects\\datastructures2-SofMovUCD\\sample_text.txt");

        ChainHashMap<String, Integer> counter = new ChainHashMap<>();

        partA(f, counter);
    }
}
