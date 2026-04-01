package project20280.hashtable;

import project20280.interfaces.Entry;
import project20280.interfaces.PriorityQueue;
import project20280.priorityqueue.HeapPriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("(A): Number of collisions is "+ (IntStream.of(collisions).sum()-11));
    }

    public static void partB(File f, ChainHashMap<String, Integer> counter) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        int[] collisions = new int[11];
        int hashGotten;

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();
            hashGotten = Math.abs(hash_poly(word, 17) % 11);


            //if word not in hashmap add it with count 1
            if(counter.bucketGet(hashGotten, word) == null){
                collisions[hashGotten]++; //increase only when a new item is added
                counter.bucketPut(hashGotten, word, 1); //add number
            }
            else{ //if word in hashmap, increment count by 1
                counter.bucketPut(hashGotten, word, counter.bucketGet(hashGotten, word)+1);
            }
        }
        System.out.println("(B): Number of collisions is "+ (IntStream.of(collisions).sum()-11));
    }

    public static void partC(File f, ChainHashMap<String, Integer> counter) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        int[] collisions = new int[11];
        int hashGotten;

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();
            hashGotten = Math.abs(hash_cyclic(word, 7) % 11);


            //if word not in hashmap add it with count 1
            if(counter.bucketGet(hashGotten, word) == null){
                collisions[hashGotten]++; //increase only when a new item is added
                counter.bucketPut(hashGotten, word, 1); //add number
            }
            else{ //if word in hashmap, increment count by 1
                counter.bucketPut(hashGotten, word, counter.bucketGet(hashGotten, word)+1);
            }
        }
        System.out.println("(C): Number of collisions is "+ (IntStream.of(collisions).sum()-11));
    }

    public static void partD(File f, ChainHashMap<String, Integer> counter) throws FileNotFoundException {

        int[][] collisions = new int[32][11];
        PriorityQueue<Integer, Integer> collisionsTotal = new HeapPriorityQueue<>();
        int hashGotten;

        for(int i = 0; i < 32; i++) {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                hashGotten = Math.abs(hash_cyclic(word, i) % 11);


                //if word not in hashmap add it with count 1
                if (counter.bucketGet(hashGotten, word) == null) {
                    collisions[i][hashGotten]++; //increase only when a new item is added
                    counter.bucketPut(hashGotten, word, 1); //add number
                } else { //if word in hashmap, increment count by 1
                    counter.bucketPut(hashGotten, word, counter.bucketGet(hashGotten, word) + 1);
                }
            }
            collisionsTotal.insert(i, IntStream.of(collisions[i]).sum()-11);
            System.out.println("did "+ i);
        }
        System.out.println("(D):");
        Entry<Integer, Integer> current;
        while (!collisionsTotal.isEmpty()){
            current = collisionsTotal.removeMin();
            System.out.println("\tShift:"+current.getKey()+" Collisions: "+current.getValue());
        }

    }

    public static void partE(File f, ChainHashMap<String, Integer> counter) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        int[] collisions = new int[11];
        int hashGotten;

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();
            hashGotten = Math.abs(hashCode(word) % 11);


            //if word not in hashmap add it with count 1
            if(counter.bucketGet(hashGotten, word) == null){
                collisions[hashGotten]++; //increase only when a new item is added
                counter.bucketPut(hashGotten, word, 1); //add number
            }
            else{ //if word in hashmap, increment count by 1
                counter.bucketPut(hashGotten, word, counter.bucketGet(hashGotten, word)+1);
            }
        }
        System.out.println("(E): Number of collisions is "+ (IntStream.of(collisions).sum()-11));
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
        File f = new File("C:\\Users\\mysof\\IdeaProjects\\datastructures2-SofMovUCD\\words.txt");

        ChainHashMap<String, Integer> counter = new ChainHashMap<>();

        //partA(f, counter);
        //partB(f, counter);
        //partC(f, counter);
        //partD(f, counter);
        /* Output of partD since it takes a long time to run and always returns the same values
        Collisions:26684 Shift: 31
        Collisions:30217 Shift: 30
        Collisions:32050 Shift: 29
        Collisions:34921 Shift: 28
        Collisions:38410 Shift: 27
        Collisions:41884 Shift: 26
        Collisions:42396 Shift: 24
        Collisions:42836 Shift: 25
        Collisions:54644 Shift: 23
        Collisions:58874 Shift: 21
        Collisions:60861 Shift: 22
        Collisions:70539 Shift: 20
        Collisions:80096 Shift: 19
        Collisions:87827 Shift: 18
        Collisions:95316 Shift: 17
        Collisions:98645 Shift: 15
        Collisions:106984 Shift: 16
        Collisions:108664 Shift: 14
        Collisions:127829 Shift: 13
        Collisions:143112 Shift: 12
        Collisions:155876 Shift: 11
        Collisions:170535 Shift: 10
        Collisions:198600 Shift: 9
        Collisions:218698 Shift: 8
        Collisions:238385 Shift: 7
        Collisions:264550 Shift: 6
        Collisions:288697 Shift: 5
        Collisions:318964 Shift: 4
        Collisions:350599 Shift: 3
        Collisions:385585 Shift: 2
        Collisions:424294 Shift: 1
        Collisions:466536 Shift: 0
        */
        partE(f, counter);
    }
}
