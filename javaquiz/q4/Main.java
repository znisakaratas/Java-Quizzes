import java.util.*;
public class Main {
    public static void main(String[] args) {
        String file = args[0];
        String[] poem = FileInput.readFile(file,true,true);
        ArrayList<String> poemList = new ArrayList<>();
        HashSet<String> poemSet = new HashSet<>();
        TreeSet<String> poemTreeSet = new TreeSet<>();
        TreeSet<String> poemTreeSetByID = new TreeSet<>(new IDComparator());
        HashMap<Integer,String> hashMapPoem = new HashMap<>();
        for (String eachLine: poem){
            poemList.add(eachLine);
            poemSet.add(eachLine);
            poemTreeSet.add(eachLine.replace("\t"," "));
            poemTreeSetByID.add(eachLine);
            hashMapPoem.put(Integer.parseInt(eachLine.substring(0,eachLine.indexOf("\t"))),eachLine.substring(eachLine.indexOf("\t")+1));
        }
        for (String verses: poemList){
            FileOutput.writeToFile("poemArrayList.txt",verses,true,true);
        }
        Collections.sort(poemList, new IDComparator());
        for (String eachVerse:poemList){
            FileOutput.writeToFile("poemArrayListOrderByID.txt",eachVerse,true,true);
        }
        for (Map.Entry<Integer, String> entry : hashMapPoem.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
            FileOutput.writeToFile("poemHashMap.txt",key+"\t"+value,true,true);
        }
        for (String verseSet: poemSet){
            FileOutput.writeToFile("poemHashSet.txt",verseSet,true,true);
        }
        for (String verseTreeSet: poemTreeSet){
            FileOutput.writeToFile("poemTreeSet.txt", verseTreeSet,true,true);
        }
        for (String verseTreeById: poemTreeSetByID){
            FileOutput.writeToFile("poemTreeSetOrderByID.txt",verseTreeById,true,true);
        }
    }
}