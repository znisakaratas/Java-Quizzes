import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.io.FileWriter;
//import java.io.IOException;

public class Main {
    public static String[] readFile(String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void writeToFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }
    public static void main(String[] args) {
        String[] lines = readFile("input.txt");
        List<String> lineList = new ArrayList<>();
        for (String line : lines) {
            lineList.add(line);
        }


        for (int i = 0; i < lineList.size(); i++) {
            String elements = lineList.get(i);
            if (elements.contains("Exit")) {
                break;
            } else if (elements.contains("Armstrong")) {
                int armNum = Integer.parseInt(lineList.get(i + 1));
                String armstr = "";
                for (int a = 1; a <= armNum; a++){
                    int number = a;
                    int total = 0;
                    int digitpow = (Integer.toString(number)).length();
                    while (number > 0) {
                        int digit = number % 10;
                        total += Math.pow(digit, digitpow);
                        number /= 10;
                    }
                    if(total == a){
                        armstr += Integer.toString(a) + " ";
                    }
                }
                Main.writeToFile("output.txt","Armstrong numbers up to "+armNum+":",true,true);
                Main.writeToFile("output.txt",armstr + "\n",true,true);

            } else if (elements.contains("Emirp")) {
                int emirpNum = Integer.parseInt(lineList.get(i + 1));
                Main.writeToFile("output.txt","Emirp numbers up to "+emirpNum+":",true,true);
                String emirpStr = "";
                for (int num = 13; num <= emirpNum; num++) {
                    if (EmirpNum(num)) {
                        emirpStr += Integer.toString(num) + " ";
                    }
                }
                Main.writeToFile("output.txt",emirpStr,true,true);
            } else if (elements.contains("Abundant")) {
                int abNum = Integer.parseInt(lineList.get(i + 1));
                String abundants = "";
                for (int abnum = 1; abnum <= abNum; abnum++){
                    if (Abundants(abnum).length() > 1){
                        abundants += Abundants(abnum) + " ";
                    }
                }
                Main.writeToFile("output.txt","\nAbundant numbers up to "+abNum+":",true,true);
                Main.writeToFile("output.txt",abundants,true,true);

            } else if (elements.contains("Ascending")) {
                List<Integer> ascendList = new ArrayList<>();
                List<String> partedList = new ArrayList<>();
                partedList.addAll(lineList.subList((i + 1), lineList.size()));
                int oneindex = partedList.indexOf("-1");
                Main.writeToFile("output.txt","\nAscending order sorting:",true,true);
                for (int k = 0; k < oneindex; k++) {
                    ascendList.add(Integer.parseInt(partedList.get(k)));
                    Collections.sort(ascendList);
                    String ascSort = ascendList.toString();
                    ascSort = ascSort.substring(1, ascSort.length() - 1);
                    Main.writeToFile("output.txt",ascSort.replace(",",""),true,true);
                }
            } else if (elements.contains("Descending")) {
                List<Integer> descendList = new ArrayList<>();
                List<String> partList = new ArrayList<>();
                partList.addAll(lineList.subList((i + 1), lineList.size()));
                int oneindex = partList.indexOf("-1");
                Main.writeToFile("output.txt","\nDescending order sorting:",true,true);
                for (int k = 0; k < oneindex; k++) {
                    descendList.add(Integer.parseInt(partList.get(k)));
                    Collections.sort(descendList);
                    Collections.reverse(descendList);
                    String desSort = descendList.toString();
                    desSort = desSort.substring(1, desSort.length() - 1);
                    Main.writeToFile("output.txt",desSort.replace(",", ""),true,true);


                }
            }

        }
    }

    public static String Abundants(int num) {
        int sum = 0;
        for (int i = 1; i <= num/2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        if(sum > num){
            return Integer.toString(num);

        }
        return "";
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
            else{
                reverse(n);
            }
        }
        return true;
    }
    public static int reverse(int n) {
        int reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed;


    }
    public static boolean EmirpNum(int n) {
        if (!isPrime(n)) {
            return false;
        }
        int reversed = reverse(n);
        if (reversed == n) {
            return false;
        }
        return isPrime(reversed);
    }



}


