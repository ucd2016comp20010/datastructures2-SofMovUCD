package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class countingcounting {
    static void main() throws FileNotFoundException {
        File f = new File("C:\\Users\\mysof\\IdeaProjects\\datastructures2-SofMovUCD\\sample_text.txt");

        ChainHashMap<String, Integer> counter = new ChainHashMap<>();

        Scanner scanner = new Scanner(f);

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();
            //System.out.println("word: "+word);

            //if word not in hashmap add it with count 1
            if(counter.get(word) == null){
                counter.put(word, 1); //add number
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
