package redBlackBST;

import java.io.Serializable;
import java.util.Collection;

public class RedBlackBST<K extends Comparable<K>, V> implements RedBlackBSTOperations<K, V>, Serializable {

	private static final long serialVersionUID = 1L;
	private RedBlackNode<K, V> nil;
	private RedBlackNode<K, V> root;

    public RedBlackBST() {
    	nil = new RedBlackNode<>();
    	root = nil;
        root.left = nil;
        root.right = nil;
        root.p = nil;
    }

	private void leftRotate(RedBlackNode<K, V> x){
		RedBlackNode<K, V> y;
		y = x.right;
		x.right = y.left;
		if (!isNil(y.left)) y.left.p = x;
		y.p = x.p;
		if (isNil(x.p)) root = y;
		else if (x.p.left == x) x.p.left = y;
		else x.p.right = y;
		y.left = x;
		x.p = y;
	}

	private void rightRotate(RedBlackNode<K, V> y){
        RedBlackNode<K, V> x = y.left;
        y.left = x.right;
        if (!isNil(x.right)) x.right.p = y;
        x.p = y.p;
        if (isNil(y.p)) root = x;
        else if (y.p.right == y) y.p.right = x;
        else y.p.left = x;
        x.right = y;
        y.p = x;
	}

    public void insertRB(K k, V v) {
        insertRB(new RedBlackNode<K, V>(k, v));
    }

	private void insertRB(RedBlackNode<K, V> z) {
		RedBlackNode<K, V> y = nil;
		RedBlackNode<K, V> x = root;
		while (!isNil(x)){
			y = x;	
			if (z.k.compareTo(x.k) <= 0)
				x = x.left;
			else 
				x = x.right;
		}
		z.p = y;
		if (isNil(y)) root = z;
		else if (z.k.compareTo(y.k) < 0) y.left = z;
		else y.right = z;
		z.left = nil;
		z.right = nil;
		z.color = RedBlackNode.RED;
		insertFixup(z);	
	}
	
	private void insertFixup(RedBlackNode<K, V> z){
		RedBlackNode<K, V> y = nil;
		while (z.p.color == RedBlackNode.RED){
			if (z.p == z.p.p.left){
				y = z.p.p.right;
				if (y.color == RedBlackNode.RED){
					z.p.color = RedBlackNode.BLACK;
					y.color = RedBlackNode.BLACK;
					z.p.p.color = RedBlackNode.RED;
					z = z.p.p;
				}else if (z == z.p.right){
					z = z.p;
					leftRotate(z);
				}else {
					z.p.color = RedBlackNode.BLACK;
					z.p.p.color = RedBlackNode.RED;
					rightRotate(z.p.p);
				}
			}else {
				y = z.p.p.left;
				if (y.color == RedBlackNode.RED){
					z.p.color = RedBlackNode.BLACK;
					y.color = RedBlackNode.BLACK;
					z.p.p.color = RedBlackNode.RED;
					z = z.p.p;
				}else if (z == z.p.left){
					z = z.p;
					rightRotate(z);
				}else {
					z.p.color = RedBlackNode.BLACK;
					z.p.p.color = RedBlackNode.RED;
					leftRotate(z.p.p);
				}
			}
		}
	root.color = RedBlackNode.BLACK;
	}
	
	private RedBlackNode<K, V> treeMinimum(RedBlackNode<K, V> node){
		while (!isNil(node.left))
			node = node.left;
		return node;
	}

	private RedBlackNode<K, V> treeSuccessor(RedBlackNode<K, V> x){
		if (!isNil(x.left) )
			return treeMinimum(x.right);
		RedBlackNode<K, V> y = x.p;
		while (!isNil(y) && x == y.right){
			x = y;
			y = y.p;
		}
		return y;
	}

	public void deleteRB(K k) {
		RedBlackNode<K, V> z = search(k);
		RedBlackNode<K, V> x = nil;
		RedBlackNode<K, V> y = nil;
		if (isNil(z.left) || isNil(z.right)) y = z;
		else y = treeSuccessor(z);
		if (!isNil(y.left))	x = y.left;
		else x = y.right;
		x.p = y.p;
		if (isNil(y.p)) root = x;
		else if (!isNil(y.p.left) && y.p.left == y) y.p.left = x;
		else if (!isNil(y.p.right) && y.p.right == y) y.p.right = x;
		if (y != z) z.k = y.k;
		if (y.color == RedBlackNode.BLACK) removeFixup(x);
	}

	private void removeFixup(RedBlackNode<K, V> x){
		RedBlackNode<K, V> w;
		while (x != root && x.color == RedBlackNode.BLACK){
			if (x == x.p.left){
				w = x.p.right;
				if (w.color == RedBlackNode.RED){
					w.color = RedBlackNode.BLACK;
					x.p.color = RedBlackNode.RED;
					leftRotate(x.p);
					w = x.p.right;
				}
				if (w.left.color == RedBlackNode.BLACK &&
							w.right.color == RedBlackNode.BLACK){
					w.color = RedBlackNode.RED;
					x = x.p;
				}else {
					if (w.right.color == RedBlackNode.BLACK){
						w.left.color = RedBlackNode.BLACK;
						w.color = RedBlackNode.RED;
						rightRotate(w);
						w = x.p.right;
					}
					w.color = x.p.color;
					x.p.color = RedBlackNode.BLACK;
					w.right.color = RedBlackNode.BLACK;
					leftRotate(x.p);
					x = root;
				}
			}else {
				w = x.p.left;
				if (w.color == RedBlackNode.RED){
					w.color = RedBlackNode.BLACK;
					x.p.color = RedBlackNode.RED;
					rightRotate(x.p);
					w = x.p.left;
				}
				if (w.right.color == RedBlackNode.BLACK &&
							w.left.color == RedBlackNode.BLACK){
					w.color = RedBlackNode.RED;
					x = x.p;
				}else {
					 if (w.left.color == RedBlackNode.BLACK){
						w.right.color = RedBlackNode.BLACK;
						w.color = RedBlackNode.RED;
						leftRotate(w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = RedBlackNode.BLACK;
					w.left.color = RedBlackNode.BLACK;
					rightRotate(x.p);
					x = root;
				}
			}
		}
		x.color = RedBlackNode.BLACK;
	}
	
	private boolean isNil(RedBlackNode<K, V> node){
		return node == nil;
	}

	public RedBlackNode<K, V> search(K k){
		RedBlackNode<K, V> current = root;
		while (!isNil(current)){
			if (current.k.equals(k))
				return current;
			else if (current.k.compareTo(k) < 0)
				current = current.right;
			else
				current = current.left;
		}
		return null;
	}
	
	public void updateNode(K k, V v) {
		RedBlackNode<K, V> node = search(k);
		node.setV(v);
	}

	public int getSize() {
		return getSize(root);
	}
	
	private int getSize(RedBlackNode<K, V> root) {
		int lw = root.left==nil ? 0 : getSize(root.left);
		int rw = root.right==nil ? 0 : getSize(root.right);
		return 1+lw+rw;
	}

	public void inOrder(Collection<V> collection) {
		inOrder(root, collection);
	}
	
	private void inOrder(RedBlackNode<K, V> root, Collection<V> collection) {
		if(root.left!=nil)
			inOrder(root.left, collection);
		collection.add(root.v);
		if(root.right!=nil)
			inOrder(root.right, collection);
	}
}
