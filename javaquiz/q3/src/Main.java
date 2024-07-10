import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String fileName = args[0];
            File file = new File(fileName);
            if(args.length > 1){
                throw new ArrayIndexOutOfBoundsException("There should be only 1 paremeter");
            }
            try {
                if (!file.exists()) {
                    throw new NoSuchFileException("There should be an input file in the specified path");
                }
                String[] lineBoard = FileInput.readFile(fileName, true, true);
                List<String> inputs = new ArrayList<>();
                for (String line : lineBoard) {
                    inputs.add(line);
                }
                if (inputs.size() == 0) {
                    throw new NullPointerException("The input file should not be empty");
                } else {
                    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghıjklmnopqrstuvwxyz ";
                    for (String line : inputs) {
                        for (int i = 0; i < line.length(); i++) {
                            if (!alphabet.contains(line.substring(i, i + 1))) {
                                throw new Exception("The input file should not contains unexpected characters");
                            }
                        }
                    }
                    for (String line : inputs) {
                        FileOutput.writeToFile("output.txt",line,true,true);
                    }
                }
            } catch (NoSuchFileException e) {
                FileOutput.writeToFile("output.txt","There should be an input file in the specified path",true,true);
            } catch (NullPointerException e) {
                FileOutput.writeToFile("output.txt","The input file should not be empty",true,true);
            } catch (Exception e) {
                FileOutput.writeToFile("output.txt","The input file should not contains unexpected characters",true,true);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            FileOutput.writeToFile("output.txt","There should be only 1 parameter",true,true);
        }
    }
}