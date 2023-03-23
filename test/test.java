import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import matrix.MatrixF;

class test {

	@Test
	void test() {
		MatrixF matrix = new MatrixF(3, 3);
		matrix.set(0, 0, 0);
		matrix.set(0, 1, 3);
		matrix.set(0, 2, 5);
		matrix.set(1, 0, 3);
		matrix.set(1, 1, 6);
		matrix.set(1, 2, 4);
		matrix.set(2, 0, 6);
		matrix.set(2, 1, 2);
		matrix.set(2, 2, 4);
		
		int det = matrix.det().intValue();
		System.out.println(det);
		assertTrue(det == -98);
	}

}
