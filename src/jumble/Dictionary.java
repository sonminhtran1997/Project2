
package jumble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Son Tran
 */
public class Dictionary {
    File dictFile = null;
    Scanner fileScanner;
    String current;
    String scrambleWord = "";
    int length = 0;
    ArrayList<String> wordList;
    String[] wordArray;
    Random rand = new Random();
    
    final int RAND_TIMES_MAX = 25;
    public Dictionary()
    {
        wordList = new ArrayList();
    }
    public Dictionary(File file){
        this();
        setFile(file);
    }
    public void setFile(File file){
        this.dictFile = file;
    }
    public boolean checkStatusSetup(){
        if(dictFile == null)
            return false;
        try
        {
            fileScanner = new Scanner(dictFile);
        }
        catch(FileNotFoundException ex)
        {
            return false;
        }
        try
        {
            for(; fileScanner.hasNextLine(); wordList.add(current))
                current = fileScanner.nextLine().toUpperCase();
            fileScanner.close();
            length = wordList.size();
            wordArray = new String[length];
            wordArray = (String[])wordList.toArray(wordArray);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error", "Cannot read Dictionary", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public String getJumble(){
        String jumbleWord = "";
        int randNum = 0;
        int randTimes = 0;
        randNum = rand.nextInt(wordArray.length);
        randTimes =  rand.nextInt(RAND_TIMES_MAX);
        scrambleWord = wordArray[randNum];
        jumbleWord = scramble(scrambleWord, randTimes);
        return jumbleWord;
    }
    public String scramble(String inputString, int times){
        String outputString = "";
        char[] workArray = inputString.toCharArray();
        outputString = swap(workArray, rand.nextInt(workArray.length-1), rand.nextInt(workArray.length-1));
        if (times < 1) 
        {
            return outputString;
        }
        else
        {
            outputString = scramble(outputString, times - 1);
        }
        return outputString;
    }
    public String swap(char[] swapedArray, int fromIndex, int toIndex)
    {
        StringBuilder outputString = new StringBuilder("");
        char temp = '\0';
        temp = swapedArray[fromIndex];
        swapedArray[fromIndex] = swapedArray[toIndex];
        swapedArray[toIndex] = temp;
        for (int i = 0; i < swapedArray.length; i++) {
            outputString.append(swapedArray[i]);
        }
        return outputString.toString();
    }
    
    
}
