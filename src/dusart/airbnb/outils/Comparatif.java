package dusart.airbnb.outils;

public interface Comparatif<T> {
    public T getHigher(T obj);
    public T getLower(T obj);
}
