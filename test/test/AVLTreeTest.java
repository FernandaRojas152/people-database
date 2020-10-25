package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import avlTree.AVLTree;

class AVLTreeTest {
	
	AVLTree<Integer, Integer> aVLTree;
	
	void setUpScene() {
		aVLTree = new AVLTree<Integer, Integer>();
	}
	
	void setUpScene2() {
		aVLTree = new AVLTree<Integer, Integer>();
		aVLTree.addNode(9, 1);
		aVLTree.addNode(5, 2);
		aVLTree.addNode(10, 3);
		aVLTree.addNode(0, 4);
		aVLTree.addNode(6, 5);
		aVLTree.addNode(11, 6);
		aVLTree.addNode(-1, 7);
		aVLTree.addNode(1, 8);
		aVLTree.addNode(2, 9);
	}
	
	@Test
	void testAddNode() {
		setUpScene();
		aVLTree.addNode(1, 1);
		assertEquals(1, aVLTree.searchNode(1).getK());
	}
	
	@Test
	void testAddNode2() {	
		setUpScene2();
		aVLTree.addNode(7, 10);
		assertEquals(7, aVLTree.searchNode(7).getK());
	}
	
	@Test
	void testAddNode3() {	
		setUpScene2();
		try {
			aVLTree.addNode(3, 10);
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	void testDeleteNode() {
		setUpScene2();
		aVLTree.deleteNode(3);
		assertNull(aVLTree.searchNode(3));
	}
	
	@Test
	void testDeleteNode2() {
		setUpScene2();
		aVLTree.deleteNode(10);
		assertEquals(1, aVLTree.getRoot().getK());
	}
	
	@Test
	void testDeleteNode3() {
		setUpScene();
		try {
			aVLTree.deleteNode(4);
		}catch(NullPointerException e) {
		}
	}
}
