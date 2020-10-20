package redBlackBST;

public class RedBlackNode<K extends Comparable<K>, V> {

    public static final int BLACK = 0;
    public static final int RED = 1;
    
	protected K k;
	protected V v; 
	protected RedBlackNode<K, V> p;
	protected RedBlackNode<K, V> left;
	protected RedBlackNode<K, V> right;
	protected int color;

    public RedBlackNode() {
        color = BLACK;
        p = null;
        left = null;
        right = null;
    }

	public RedBlackNode(K k, V v){
        this();
        this.k = k;
        this.v = v;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}
}
