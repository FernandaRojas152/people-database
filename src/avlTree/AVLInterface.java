package avlTree;

public interface AVLInterface<K extends Comparable<K>, T> {
	public void insert(K key, T height);
	public void insertBalanced();
}
