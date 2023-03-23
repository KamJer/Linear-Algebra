package test;

import static org.junit.Assert.assertTrue;

import matrix.Matrix;
import matrix.MatrixF;
import vector.Vector;
import vector.VectorD;
import vector.VectorF;
import vector.VectorI;

class Test {

	@org.junit.jupiter.api.Test
	void faddTest() {
		Vector one = new VectorF(2);
		one.set(0, 4);
		one.set(1, 6);
		
		Vector sec = new VectorF(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		one.add(sec);
		
		assertTrue(one.get(0).floatValue() == 10 && one.get(1).floatValue() == 10);
	}
	
	@org.junit.jupiter.api.Test
	void fsubTest() {
		Vector one = new VectorF(2);
		one.set(0, 4);
		one.set(1, 6);
		
		Vector sec = new VectorF(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		one.sub(sec);
		
		assertTrue(one.get(0).floatValue() == -2 && one.get(1).floatValue() == 2);
	}
	
	@org.junit.jupiter.api.Test
	void fmulTest() {
		Vector one = new VectorF(2);
		one.set(0, 4);
		one.set(1, 6);
		
		one.mul(2);
		
		assertTrue(one.get(0).floatValue() == 8 && one.get(1).floatValue() == 12);
	}
	
	@org.junit.jupiter.api.Test
	void fnormalizeTest() {
		Vector one = new VectorF(2);
		one.set(0, 4);
		one.set(1, 6);
		
		one.normalize();
		assertTrue(Math.round(one.module()) == 1);
	}
	
	@org.junit.jupiter.api.Test
	void fmoduleTest() {
		Vector one = new VectorF(2);
		one.set(0, 4);
		one.set(1, 3);
		
		assertTrue(Math.round(one.module()) == 5);
	}
	
	@org.junit.jupiter.api.Test
	void fangleTest() {
		Vector one = new VectorF(2);
		one.set(0, 0);
		one.set(1, 5);
		
		Vector sec = new VectorF(2);
		sec.set(0, 0);
		sec.set(1, -5);
		
		assertTrue(one.angle(sec) == Math.PI);
	}
	
	@org.junit.jupiter.api.Test
	void fscalarTest() {
		Vector one = new VectorF(2);
		one.set(0, 4);
		one.set(1, 3);
		
		Vector sec = new VectorF(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		assertTrue(Math.round(one.scalar(sec).doubleValue()) == 36.0);
	}
	
	@org.junit.jupiter.api.Test
	void iaddTest() {
		Vector one = new VectorI(2);
		one.set(0, 4);
		one.set(1, 6);
		
		Vector sec = new VectorI(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		one.add(sec);
		
		assertTrue(one.get(0).intValue() == 10 && one.get(1).intValue() == 10);
	}
	
	@org.junit.jupiter.api.Test
	void isubTest() {
		Vector one = new VectorI(2);
		one.set(0, 4);
		one.set(1, 6);
		
		Vector sec = new VectorI(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		one.sub(sec);
		
		assertTrue(one.get(0).intValue() == -2 && one.get(1).intValue() == 2);
	}
	
	@org.junit.jupiter.api.Test
	void imulTest() {
		Vector one = new VectorI(2);
		one.set(0, 4);
		one.set(1, 6);
		
		one.mul(2);
		
		assertTrue(one.get(0).intValue() == 8 && one.get(1).intValue() == 12);
	}
	
	@org.junit.jupiter.api.Test
	void inormalizeTest() {
		Vector one = new VectorI(2);
		one.set(0, 4);
		one.set(1, 6);
		
		one.normalize();
		assertTrue(Math.round(one.module()) == 1);
	}
	
	@org.junit.jupiter.api.Test
	void imoduleTest() {
		Vector one = new VectorI(2);
		one.set(0, 4);
		one.set(1, 3);
		
		assertTrue(Math.round(one.module()) == 5);
	}
	
	@org.junit.jupiter.api.Test
	void iangleTest() {
		Vector one = new VectorI(2);
		one.set(0, 0);
		one.set(1, 5);
		
		Vector sec = new VectorI(2);
		sec.set(0, 0);
		sec.set(1, -5);
		
		assertTrue(one.angle(sec) == Math.PI);
	}
	
	@org.junit.jupiter.api.Test
	void iscalarTest() {
		Vector one = new VectorI(2);
		one.set(0, 4);
		one.set(1, 3);
		
		Vector sec = new VectorI(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		assertTrue(Math.round(one.scalar(sec).doubleValue()) == 36.0);
	}
	
	@org.junit.jupiter.api.Test
	void addTest() {
		Vector one = new VectorD(2);
		one.set(0, 4);
		one.set(1, 6);
		
		Vector sec = new VectorD(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		one.add(sec);
		
		assertTrue(one.get(0).intValue() == 10 && one.get(1).intValue() == 10);
	}
	
	@org.junit.jupiter.api.Test
	void subTest() {
		Vector one = new VectorD(2);
		one.set(0, 4);
		one.set(1, 6);
		
		Vector sec = new VectorD(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		one.sub(sec);
		
		assertTrue(one.get(0).intValue() == -2 && one.get(1).intValue() == 2);
	}
	
	@org.junit.jupiter.api.Test
	void mulTest() {
		Vector one = new VectorD(2);
		one.set(0, 4);
		one.set(1, 6);
		
		one.mul(2);
		
		assertTrue(one.get(0).intValue() == 8 && one.get(1).intValue() == 12);
	}
	
	@org.junit.jupiter.api.Test
	void normalizeTest() {
		Vector one = new VectorD(2);
		one.set(0, 4);
		one.set(1, 6);
		
		one.normalize();
		assertTrue(Math.round(one.module()) == 1);
	}
	
	@org.junit.jupiter.api.Test
	void moduleTest() {
		Vector one = new VectorD(2);
		one.set(0, 4);
		one.set(1, 3);
		
		assertTrue(Math.round(one.module()) == 5);
	}
	
	@org.junit.jupiter.api.Test
	void angleTest() {
		Vector one = new VectorD(2);
		one.set(0, 0);
		one.set(1, 5);
		
		Vector sec = new VectorD(2);
		sec.set(0, 0);
		sec.set(1, -5);
		
		assertTrue(one.angle(sec) == Math.PI);
	}
	
	@org.junit.jupiter.api.Test
	void scalarTest() {
		Vector one = new VectorD(2);
		one.set(0, 4);
		one.set(1, 3);
		
		Vector sec = new VectorD(2);
		sec.set(0, 6);
		sec.set(1, 4);
		
		assertTrue(Math.round(one.scalar(sec).doubleValue()) == 36.0);
	}
	
	@org.junit.jupiter.api.Test
	void matrixTest() {
		Matrix one = new MatrixF(3, 3);
		one.set(0, 0, 2);
		one.set(0, 1, 4);
		one.set(0, 2, 5);
		one.set(1, 0, 2);
		one.set(1, 1, 5);
		one.set(1, 2, 5);
		one.set(2, 0, 6);
		one.set(2, 1, 3);
		one.set(2, 2, 4);
		
		assertTrue(true);
	}
	
	@org.junit.jupiter.api.Test
	void matrixIdTest() {
		Matrix one = new MatrixF(3, 3);
		one.identity();
		
		assertTrue(one.isIdentity());
	}
	
	@org.junit.jupiter.api.Test
	void matrixaddTest() {
		Matrix one = new MatrixF(3, 3);
		one.set(0, 0, 5);
		one.set(0, 1, 5);
		one.set(0, 2, 5);
		one.set(1, 0, 5);
		one.set(1, 1, 5);
		one.set(1, 2, 5);
		one.set(2, 0, 5);
		one.set(2, 1, 5);
		one.set(2, 2, 5);
		
		Matrix sec = new MatrixF(3, 3);
		sec.set(0, 0, 2);
		sec.set(0, 1, 2);
		sec.set(0, 2, 3);
		sec.set(1, 0, 4);
		sec.set(1, 1, 2);
		sec.set(1, 2, 3);
		sec.set(2, 0, 5);
		sec.set(2, 1, 4);
		sec.set(2, 2, 2);
		
		one.add(sec);
		
		assertTrue(one.get(0, 0).equals(7f) && one.get(2, 1).equals(9f) && one.get(1, 2).equals(8f));
	}
	
	@org.junit.jupiter.api.Test
	void matrixDetTest() {
		Matrix one = new MatrixF(3,3);
		one.set(0, 0, 1);
		one.set(0, 1, 52);
		one.set(0, 2, 3);
		
		one.set(1, 0, 15);
		one.set(1, 1, 15);
		one.set(1, 2, 6);
		
		one.set(2, 0, 81);
		one.set(2, 1, 56);
		one.set(2, 2, 81);
		assertTrue(one.det().equals(-38154f));
	}
	
	@org.junit.jupiter.api.Test
	void matrixRoundTest() {
		Matrix one = new MatrixF(3,3);
		one.set(0, 0, 1.008);
		one.set(0, 1, 2.256);
		one.set(0, 2, 3.384);
		
		one.set(1, 0, 3);
		one.set(1, 1, 6);
		one.set(1, 2, 6);
		
		one.set(2, 0, 81);
		one.set(2, 1, 81);
		one.set(2, 2, 81);
		one.roundElements(2);
		assertTrue(one.get(0, 0).equals(1.01f));
	}
	
	@org.junit.jupiter.api.Test
	void matrixDecompositionTest() {
		Matrix one = new MatrixF(3,3);
		one.set(0, 0, 1);
		one.set(0, 1, 2);
		one.set(0, 2, 3);
		
		one.set(1, 0, 3);
		one.set(1, 1, 6);
		one.set(1, 2, 6);
		
		one.set(2, 0, 5);
		one.set(2, 1, 15);
		one.set(2, 2, 8);
		
		assertTrue(true);
	}
	
	@org.junit.jupiter.api.Test
	void matrixTranslateTest() {
		Matrix one = new MatrixF(4,4);
		one.identity();
		Vector sec = new VectorF(3);
		sec.set(0, 5f);
		sec.set(1, 5f);
		sec.set(2, 5f);
		
		one.translate(sec);
		assertTrue(one.get(2, 3).equals(5f));
	}
	
	@org.junit.jupiter.api.Test
	void matrixScaleTest() {
		Matrix one = new MatrixF(4,4);
		one.identity();
		one.set(0, 2, 5f);
		
		Vector sec = new VectorF(3);
		sec.set(0, 2f);
		sec.set(1, 2f);
		sec.set(2, 2f);
		
		one.scale(sec);
		assertTrue(one.get(1, 1).equals(2f));
	}
	
	
}
