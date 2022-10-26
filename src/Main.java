import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
public class Main {
    public static class Node{      // defining the Node
        Node childs[] = new Node[30];
        String str;
    }

    public static void insert(Node curr, String s){    //function for inserting the words in the string
        for(int i = 0; i<s.length();i++){
            char ch = s.charAt(i);
            if(curr.childs[ch-'a'] == null){
                curr.childs[ch-'a'] = new Node();
            }
            curr = curr.childs[ch-'a'];
        }
        curr.str = s;
    }
    public static void search(Node curr, Node nword){     //function for searching the nodes if the concatenated words exist in the array

        if(curr.str != null && nword.str != null){
            ans.add(curr.str); //if the both current and new word in the string becomes null then the current node of the string is added to the ans Hash set
        }
        if(nword.str != null){  //if only new word becomes null then the current and root is searched in the array
            search(curr, root);
        }
        for(int i = 0; i<30 ; i++){
            if(curr.childs[i] != null && nword.childs[i] != null){
                search(curr.childs[i], nword.childs[i]);
            }
        }

    }
    public static void search1(Node curr){   // function for searching the current node in the string

        if(curr.str != null){
            search(curr, root);
        }

        for(Node child: curr.childs){
            if(child != null){
                search1(child);
            }
        }
    }
    static Node root;  // root node definition
    static HashSet<String> ans = new HashSet<>();  //hash set is used to deal with duplecacy of the strings
    public static List<String> findAllConcatenatedWordsInADict(String[] words){     //main function for searching the concatenated function
        root = new Node();
        for(String s: words){
            insert(root,s);
        }
        search1(root);
        List<String > al = new ArrayList<>(ans); //converting the result to array list

        int maxLength = 0; // for printing the longest string
        String longestString = null;
        for (String s : al) {
            if (s.length() > maxLength){
                maxLength = s.length();
                longestString = s;
            }
        }
        System.out.println(longestString);



        int maxLength2 = 0;  //for printing the secong longest string
        String secondlongestString = null;
        for (String s : al) {
            if (s.length() > maxLength2 && s.length() < longestString.length()) {
                maxLength2 = s.length();
                secondlongestString = s;
            }
        }
        System.out.println(secondlongestString);


        return al;
    }
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis(); //time when statring the program
        File file = new File("/Users/shubhamkashyap/IdeaProjects/wordConcat/src/Input_02.txt"); //taking input from file
        BufferedReader reader = new BufferedReader(new FileReader(file));  //buffer reader is used to read the file
        ArrayList<String> list = new ArrayList<>(); //creating new array list

        String line = null; //new string line
        while ((line = reader.readLine()) != null) list.add(line); //adding each line of the input file in the string

        String[] array = list.toArray(new String[0]); // converting array list to string array

        findAllConcatenatedWordsInADict(array); // calling the concatenate function
        long end = System.currentTimeMillis(); //time at the end of the program after processing the input file
        long time = end - start; // subtracting the end time from start time to get the running time of program
        System.out.println("time taken to process input file: "+ time +"ms");
    }
}