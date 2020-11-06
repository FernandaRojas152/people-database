package avlTree;

import java.io.Serializable;

import binarySearchTree.BinarySearchTree;
import binarySearchTree.Node;

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K,V> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private AVLNode<K, V> node;
	
	public AVLTree() {
	}
	
	@Override
	public void addNode(K key, V value) {
		setRoot(add((AVLNode<K, V>) getRoot(), new AVLNode<K, V>(key, value)));
	}
	
	private AVLNode<K, V> add(AVLNode<K, V> current, AVLNode<K, V> node) {	
		
        if (current == null)  
            return node;  
        if (node.getK().compareTo(current.getK()) <= 0)  
            current.setLeft(add((AVLNode<K, V>) current.getLeft(), node));  
        else 
        	current.setRight(add((AVLNode<K, V>) current.getRight(), node));
  
        current.setHeight(1 + Math.max(height((AVLNode<K, V>) current.getLeft()), height((AVLNode<K, V>) current.getRight())));  
 
        int balance = getBalance(current);  
        
        if (balance > 1 && node.getK().compareTo(current.getLeft().getK()) < 0)  
            return (AVLNode<K, V>) rightRotate(current);  
  
        if (balance < -1 && node.getK().compareTo(current.getLeft().getK()) > 0)  
            return (AVLNode<K, V>) leftRotate(current);  
    
        if (balance > 1 && node.getK().compareTo(current.getLeft().getK()) > 0)  {
            current.setLeft(leftRotate((AVLNode<K, V>) current.getLeft()));  
            return (AVLNode<K, V>) rightRotate(current);  
        }    
        if (balance < -1 && node.getK().compareTo(current.getLeft().getK()) < 0) {  
            current.setRight(rightRotate((AVLNode<K, V>) current.getRight()));  
            return (AVLNode<K, V>) leftRotate(current);  
        }  
        return current;  
	}
	
	@Override
	public void deleteNode(K k) {
		setRoot(delete((AVLNode<K, V>) getRoot(), k));
	}
	
	private AVLNode<K, V> delete(AVLNode<K, V> current, K k) {	
		
        if (current == null) return current;  
	        if (k.compareTo(current.getK()) < 0)
	        	current.setLeft(delete((AVLNode<K, V>) current.getLeft(), k)); 
	  
	        else if (k.compareTo(current.getK()) > 0)  
	        	current.setRight(delete((AVLNode<K, V>) current.getRight(), k)); 
        else {  
            if (current.getLeft() == null || current.getRight() == null) {  
            	AVLNode<K, V> temp = null;  
                if (temp == current.getLeft())  
                    temp = (AVLNode<K, V>) current.getRight();  
                else
                    temp = (AVLNode<K, V>) current.getLeft();  
                
                if (temp == null) {  
                    temp = current;  
                    current = null;  
                }else 
                    current = temp;   
            }else {  
            	AVLNode<K, V> temp = minValueNode(current.getRight());  
                current.setK(temp.getK()); 
                current.setRight(delete((AVLNode<K, V>) current.getRight(), temp.getK()));  
            }  
        }    
        if (current == null)  
            return current;  
        
        current.setHeight(Math.max(height((AVLNode<K, V>) current.getLeft()), height((AVLNode<K, V>) current.getRight())) + 1);  
        int balance = getBalance(current);  

        if (balance > 1 && getBalance((AVLNode<K, V>) current.getLeft()) >= 0)  
            return (AVLNode<K, V>) rightRotate(current);  
   
        if (balance > 1 && getBalance((AVLNode<K, V>) current.getLeft()) < 0) {  
            current.setLeft(leftRotate((AVLNode<K, V>) current.getLeft()));  
            return (AVLNode<K, V>) rightRotate(current);  
        }
        if (balance < -1 && getBalance((AVLNode<K, V>) current.getRight()) <= 0)  
            return leftRotate(current);  

        if (balance < -1 && getBalance((AVLNode<K, V>) current.getRight()) > 0) {  
            current.setRight(rightRotate((AVLNode<K, V>) current.getRight()));  
            return leftRotate(current);  
        }  
        return current;  
	}
	
	private AVLNode<K, V> minValueNode(Node<K, V> node) {
		AVLNode<K, V> current = (AVLNode<K, V>) node;  
		 
		 while (current.getLeft() != null)  
			 current = (AVLNode<K, V>) current.getLeft();
		 return current;  
	}
	
	private Node<K, V> rightRotate(AVLNode<K, V> y) {  
		AVLNode<K, V> x = (AVLNode<K, V>) y.getLeft();  
		AVLNode<K, V> T2 = (AVLNode<K, V>) x.getRight();  
        x.setRight(y);  
        y.setLeft(T2);  
        y.setHeight(Math.max(height((AVLNode<K, V>) y.getLeft()), height((AVLNode<K, V>) y.getRight())) + 1);  
        x.setHeight(Math.max(height((AVLNode<K, V>) x.getLeft()), height((AVLNode<K, V>) x.getRight())) + 1);   
        return x;  
    }  
	
	private AVLNode<K, V> leftRotate(AVLNode<K, V> x) {  
		AVLNode<K, V> y = (AVLNode<K, V>) x.getRight();  
		AVLNode<K, V> T2 = (AVLNode<K, V>) y.getLeft();  
	    y.setLeft(x);  
	    x.setRight(T2);  
	    x.setHeight(Math.max(height((AVLNode<K, V>) x.getLeft()), height((AVLNode<K, V>) x.getRight())) + 1);  
	    y.setHeight(Math.max(height((AVLNode<K, V>) y.getLeft()), height((AVLNode<K, V>) y.getRight())) + 1);   
	    return y;  
    }  
	
	private int height(AVLNode<K, V> node) {  
        if (node == null) return 0;  
        return node.getHeight();  
    }  
	
	private int getBalance(AVLNode<K, V> node) {  
        if (node == null) return 0;  
        return height((AVLNode<K, V>) node.getLeft()) - height((AVLNode<K, V>) node.getRight());  
    }

	public AVLNode<K, V> getNode() {
		return node;
	}

	public void setNode(AVLNode<K, V> node) {
		this.node = node;
	}
}