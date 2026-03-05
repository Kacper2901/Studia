import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class LanguageDetector {
    ArrayList<FileDistance> enFiles;
    ArrayList<FileDistance> plFiles;
    ArrayList<FileDistance> esFiles;
    ArrayList<FileDistance> allFiles;

    String inputText;
    int enVotes;
    int plVotes;
    int esVotes;

    public LanguageDetector() throws IOException{
        collectAllLanguageFiles();
        storeAllFiles();
    }

    private void collectAllLanguageFiles() throws IOException{
        esFiles = CollectLanguageFiles.findLangFiles(Paths.get("../trainingFiles"), Languages.SPANISH);
        plFiles = CollectLanguageFiles.findLangFiles(Paths.get("../trainingFiles"), Languages.POLISH);
        enFiles = CollectLanguageFiles.findLangFiles(Paths.get("../trainingFiles"), Languages.ENGLISH);
    }

    private void storeAllFiles(){
        allFiles = new ArrayList<>();
        allFiles.addAll(enFiles);
        allFiles.addAll(esFiles);
        allFiles.addAll(plFiles);
    }

    public Languages getLanguageOfInputText(int k) throws IOException{
        setInputText();

        for(FileDistance p: allFiles){
            String fileText = Files.readString(p.filePath);
            p.setDistanceFromInputText(calculeteSquareDistance(inputText,fileText));
        }

        this.enVotes = 0;
        this.plVotes = 0;
        this.esVotes = 0;
        allFiles.sort(Comparator.comparingDouble((FileDistance d) -> d.distanceFromInputText));

        for(int i = 0; (i < k) && i < allFiles.size(); i++){
            switch (allFiles.get(i).language){
                case SPANISH:
                    esVotes++;
                    break;
                case POLISH:
                    plVotes++;
                    break;
                case ENGLISH:
                    enVotes++;
                    break;
                default:
                    break;
            }
        }
        if(enVotes >= plVotes && enVotes >= esVotes) return Languages.ENGLISH;
        else if(plVotes >= enVotes && plVotes >= esVotes) return Languages.POLISH;
        else return Languages.SPANISH;
    }

    private void setInputText(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a text in Polish, English or Spanish \nand I will try to guess the language! \n(The longer text, the better, so make sure there are at least 100 letters) \n\n");
        this.inputText = scanner.nextLine().toLowerCase();
        while (this.inputText.length() < 100){
            System.out.println("Text is too short for me to guess, write sth longer! \n(at least 100 characters)\n\n");
            this.inputText = scanner.nextLine().toLowerCase();
        }
    }

    private double[] storeFreqOfLatinLetters(String inputString){
        int sumOfLatinLetters = 0;
        double[] freq = new double[26];
        for(char c: inputString.toCharArray()){
            if(c >= 'a' && c <= 'z'){
                freq[getCharIdx(c)] ++; // f.e. 'a'=97 and b=98 so b-a = 1, a-a =0, so letters are in alphabetical order
                sumOfLatinLetters++;
            }
        }

        if(sumOfLatinLetters == 0) return freq;

        for(int i = 0; i < 26; i++){
            freq[i] /= sumOfLatinLetters;
        }
        return freq;
    }

    private double calculeteSquareDistance(String inputText, String trainingText){
        double sum = 0;
        double[] inputTextFreq = storeFreqOfLatinLetters(inputText);
        double[] trainingTextFreq = storeFreqOfLatinLetters(trainingText);
        for(int i = 0; i < 26; i++){
            sum += (trainingTextFreq[i] - inputTextFreq[i])*(trainingTextFreq[i] - inputTextFreq[i]); //we dont need a root becaise it cancels out while comparing
        }

        return sum;
    }

    private int getCharIdx(char c){
        return c - 'a';
    }
}
