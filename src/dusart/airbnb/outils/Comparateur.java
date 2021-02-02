package dusart.airbnb.outils;

public class Comparateur<T extends Comparatif<T>> {
    private final T obj1;
    private final T obj2;

    public Comparateur(T pObj1, T pObj2) {
        obj1 = pObj1;
        obj2 = pObj2;
    }

    public T getHigher() {
        return obj1.getHigher(obj2);
    }

    public T getLower() {
        return obj1.getLower(obj2);
    }
}
