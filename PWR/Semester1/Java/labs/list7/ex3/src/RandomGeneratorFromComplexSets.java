import java.util.Random;

public class RandomGeneratorFromComplexSets extends RandomNumberFromSets{
    TComplexSets complexSets = new TComplexSets();


    void setComplexSets(TPair... pairs){
        complexSets.length = 0;
        for(TPair pair: pairs){
            complexSets.complexSet[complexSets.length] = pair;
            complexSets.length ++;
        }
    }

    private int chooseRandomSubset(){
        if(complexSets.complexSet.length == 0) throw new IllegalStateException("There is no subsets to choose from");
        return super.nextInt(complexSets.length);
    }


}
