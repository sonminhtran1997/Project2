/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumble;

import java.util.ArrayList;


public class JumbleSolver {
    String jumble = "";
    Dictionary dictionary;
    PermutationGenerator permutationTool;
    final int NOT_FOUND = -1;
    public JumbleSolver(String jumble, Dictionary dictionary){
        this.jumble = jumble;
        this.dictionary = dictionary;
    }
    public String[] getSolutions(){
        ArrayList<String> solutions = new ArrayList<String>();
        String[] solutionsArray;
        permutationTool = new PermutationGenerator(jumble);
        String[] permutation = new String[permutationTool.getPermutation().size()];
        permutation = permutationTool.getPermutation().toArray(permutation);
        for (int i = 0; i < permutation.length; i++) {
            if (binSearch(dictionary.wordArray, permutation[i], 0, dictionary.wordArray.length - 1) 
                    && !solutions.contains(permutation[i])) 
            {
                solutions.add(permutation[i]);
            }
        }
        solutionsArray = new String[solutions.size()];
        solutionsArray = solutions.toArray(solutionsArray);
        return solutionsArray;
    }
    public boolean binSearch(String[] data, String word, int fromIndex, int toIndex){
        int mid = 0;
        if (fromIndex >= toIndex) 
        {
            return false;
        }
        mid = (fromIndex + toIndex) / 2;
        if (word.equals(data[mid])) 
        {
            return true;
        }
        else if (word.compareTo(data[mid]) < 0)
        {
            return binSearch(data, word, fromIndex, mid - 1);
        }
        else
        {
            return binSearch(data, word, mid + 1, toIndex);
        }
    }
}
