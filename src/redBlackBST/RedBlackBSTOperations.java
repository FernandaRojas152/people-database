package redBlackBST;

import java.util.Collection;

public interface RedBlackBSTOperations <K extends Comparable<K>, V> {
	
	 public void insertRB(K k, V v);
	 public void deleteRB(K k);
	 public RedBlackNode<K, V> search(K k);
	 public void updateNode(K k, V v) ;
	 public int size();	
	 public void inOrder(Collection<V> collection);	
}
