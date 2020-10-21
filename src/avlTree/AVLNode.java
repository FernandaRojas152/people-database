package avlTree;

public class AVLNode<K extends Comparable<K>, T> {
	private int balanceFactor;
	
	public AVLNode() {
		
	}

	public int getBalanceFactor() {
		
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

}
