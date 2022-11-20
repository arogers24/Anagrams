/*
  This program finds all the anagrams in a specified txt file.
  The run time to find anagrams is printed.
  The words with the most anagrams are printed.

  Assignment11
  Adam Rogers
 */

import java.util.*;

public class Anagrams {

    public static void main(String[] args) {
	System.out.println("Welcome to the anagram finder!\n");
			   
	if (args.length != 1) {
	    System.out.println("  usage: java Anagram some-text.txt\n");
	    System.exit(0);
	}

	System.out.println("  reading file " + args[0] + "...");

	WordReader wr = new WordReader(args[0]);
	RunTimer rt = new RunTimer();
	HashMap<String, ArrayList<String>> wordMap = new HashMap<>();
	HashMap<String, ArrayList<String>> anagrams = new HashMap<>();

	System.out.println("Found anagrams:");
	rt.start();
	// read txt word by word
	String next = wr.nextWord();
	while (next != null) {
	    // sort word by character
	    char[] ch = next.toCharArray();
	    Arrays.sort(ch);
	    // get list at that key
	    ArrayList<String> wordList = wordMap.get(String.copyValueOf(ch));
	    // if list exists and doesn't contain word, alter list
	    if (wordList != null && !wordList.contains(next)) {
		wordList.add(next);
		// add to anagram map
		anagrams.put(String.copyValueOf(ch), wordList);
	    }
	    // otherwise, create new list and add word
	    else {		
		wordList = new ArrayList<>();
		wordList.add(next);
	    }
	    // add to word map
	    wordMap.put(String.copyValueOf(ch), wordList);
	    next = wr.nextWord();
	}
        rt.stop();
	
	ArrayList<String> maxAnagrams = new ArrayList<>();
	int maxCount = 0;
	// iterate through keys
	for (String key : anagrams.keySet()) {
	    int counter = anagrams.get(key).size();
	    // print anagrams
	    System.out.println(anagrams.get(key));
	    // if number of anagrams is the same as max, add to max list
	    if (counter == maxCount) maxAnagrams.addAll(anagrams.get(key));
	    // if number of anagrams exceeds max, set new max
	    if (counter > maxCount) {
		maxCount = counter;
	        maxAnagrams = anagrams.get(key);
	    }
	}
	System.out.println("There are " + anagrams.keySet().size() + " sets of anagrams.");
	System.out.println("That took " + rt.getElapsedMillis() + "ms");
	System.out.println("Words with the most anagrams are: " + maxAnagrams);	
    }    
}
