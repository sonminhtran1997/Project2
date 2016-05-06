/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumble;

import java.util.ArrayList;

/**
 *
 * @author Son Tran
 */
public class PermutationGenerator {
    String word;
    public PermutationGenerator(String inputString){
        this.word = inputString;
    }
    public ArrayList<String> getPermutation()
    {
        ArrayList<String> output = new ArrayList<String>();
        if (word.length() == 0) {
            output.add(word);
        }
        else
        {
            for (int i = 0; i < word.length(); i++) {
                String shorter = word.substring(0,i) + word.substring(i+1);
                ArrayList<String> shorterPermutations = new PermutationGenerator(shorter).getPermutation();
                for(String s :shorterPermutations)
                {
                    output.add(word.charAt(i) + s);
                }
            }
        }
        return output;
    }
}
