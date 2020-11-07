package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import redBlackBST.RedBlackBST;

class RedBlackBSTTest {
	
	RedBlackBST<Integer, String> redBlackBST;

	void setUpScene() {
		redBlackBST = new RedBlackBST<Integer, String>();
	}
	
	void setUpScene2() {
		redBlackBST = new RedBlackBST<Integer, String>();
		redBlackBST.insertRB(2, "a");
		redBlackBST.insertRB(3, "b");
		redBlackBST.insertRB(1, "c");
		redBlackBST.insertRB(5, "d");
		redBlackBST.insertRB(4, "e");
		redBlackBST.insertRB(6, "f");
	}
	
	@Test
	void testInsertRB() {
		setUpScene();
		redBlackBST.insertRB(1, "3");
		assertEquals(1, redBlackBST.search(1).getK());
	}
	
	@Test
	void testInsertRB2() {	
		setUpScene2();
		redBlackBST.insertRB(7, "g");
		assertEquals(7, redBlackBST.search(7).getK());
	}
	
	@Test
	void testInsertRB3() {	
		setUpScene2();
		try {
			redBlackBST.insertRB(3, "d");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	void testDeleteRB() {
		setUpScene2();
		redBlackBST.deleteRB(3);
		assertNull(redBlackBST.search(3));
	}
	
	@Test
	void testDeleteRB2() {
		setUpScene();
		try {
			redBlackBST.deleteRB(4);
		}catch(NullPointerException e) {
		}
	}
	
	@Test
	void testSearch() {
		setUpScene2();
		assertEquals(1, redBlackBST.search(1).getK());
	}

	@Test
	void testSearch2() {
		setUpScene();
		assertNull(redBlackBST.search(1));
	}
	
	@Test
	void testUpdateNode() {
		setUpScene2();
		redBlackBST.updateNode(1, "a");
		assertEquals("a", redBlackBST.search(1).getV());
	}
	
	@Test
	void testUpdateNode2() {
		setUpScene();
		try {
			redBlackBST.updateNode(1, "a");
		}catch(NullPointerException e) {
		}
	}
	
	@Test
	void testSize() {
		setUpScene2();
		assertEquals(6, redBlackBST.getSize());
	}
	
	@Test
	void testSize2() {
		setUpScene();
		assertEquals(1, redBlackBST.getSize());
	}
	
	@Test
	void testInOrder() {
		setUpScene2();
		ArrayList<String> list = new ArrayList<>();
		String[] expected = {"c", "a", "b", "e", "d", "f"};
		redBlackBST.inOrder(list);
		for (int i = 0; i < list.size(); i++) 
			assertEquals(expected[i], list.get(i));
	}
}
