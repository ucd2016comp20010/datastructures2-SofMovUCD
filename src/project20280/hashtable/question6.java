package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class question6 {

    public int hashCode(String s){
        int hash = 0;
        int skip = Math.max(1, s.length()/8);
        for(int i = 0; i < s.length(); i+= skip){
            hash = hash*37 + s.charAt(i);
        }
        return hash;
    }

    public int hash_poly(String s, int a){
        int h = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            char s_i = (char) s.charAt(i);
            int v = s_i*((int) Math.pow(a, n-i-1));
            h += v;
        }
        return h;
    }

    public int hash_cyclic(String s, int shift){
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

        Scanner scanner = new Scanner(f);

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();
            //System.out.println("word: "+word);

            //if word not in hashmap add it with count 1
            if(counter.get(word) == null){
                counter.put( word, 1); //add number
            }
            else{ //if word in hashmap, increment count by 1
                counter.put(word, counter.get(word)+1);
            }
        }

        ArrayList<Entry<String, Integer>> topTen = new ArrayList<>();
        for(Entry<String,Integer> e : counter.entrySet()){
            topTen.add(e);
            if(topTen.size() > 10){
                topTen.sort((a,b) -> b.getValue().compareTo(a.getValue()));
                topTen.removeLast();
            }
        }

        for(Entry<String,Integer> e : topTen){
            System.out.println("Word: "+ e.getKey()+", Frequency: "+ e.getValue());
        }
    }
}
