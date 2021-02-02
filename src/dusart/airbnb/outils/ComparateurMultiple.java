package dusart.airbnb.outils;

import java.util.ArrayList;

public class ComparateurMultiple<T extends Comparatif<T>> {
    private ArrayList<T> objArray;

    public ComparateurMultiple(ArrayList<T> pObjArray) {
        objArray = pObjArray;
    }

    public void add(T obj) {
        objArray.add(obj);
    }

    public T getHigher() {
        int resultIndex = 0;
        for(int i = 1; i < objArray.size(); i++){
            if(objArray.get(resultIndex).getHigher(objArray.get(i)) == objArray.get(i)) {
                resultIndex = i;
            }
        }
        return objArray.get(resultIndex);
    }
}
