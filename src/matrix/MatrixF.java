package matrix;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import vector.Vector;

public class MatrixF extends Matrix {

	public MatrixF(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public MatrixF(Matrix matrix) {
		super(matrix);
	}

	@Override
	public Matrix add(Matrix matrix) {
		if (matrix.columnCount() == this.columnCount() && matrix.rowCount() == this.rowCount()) {
			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < elements[i].length; j++) {
					this.elements[i][j] = this.elements[i][j].floatValue() + matrix.elements[i][j].floatValue();
				}
			}
			return this;
		}
		return null;
	}

	@Override
	public Matrix sub(Matrix matrix) {
		if (matrix.columnCount() == this.columnCount() && matrix.rowCount() == this.rowCount()) {
			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < elements[i].length; j++) {
					this.elements[i][j] = this.elements[i][j].floatValue() - matrix.elements[i][j].floatValue();
				}
			}
			return this;
		}
		return null;
	}

	@Override
	public Matrix mul(Matrix matrix) {
//	Checking if the amount of columns and the amount of row in the matrixes is the same
		if (this.columnCount() == matrix.rowCount()) {
//			creating temp matrix to save temporery data 
			Matrix temp = new MatrixF(columnCount(), rowCount());

			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < elements[i].length; j++) {
					for (int k = 0; k < elements.length; k++) {
//						performing multiplication for all elements
						temp.set(j, i, elements[i][k].floatValue() * elements[k][j].floatValue());
					}
				}
			}
//			giving temporary values to this matrix
			this.elements = temp.elements;
//			returning this matrix
			return this;
		}
		return null;
	}

	@Override
	public Matrix mul(Number a) {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				this.elements[i][j] = this.elements[i][j].floatValue() * a.floatValue();
			}
		}
		return this;
	}

	@Override
	public Matrix[] luDecomposition() throws IllegalArgumentException {
//		TODO: algorithm can return -infinity if there is 0 in it's diagonal solve it
//		!!//////algorytm z internetu https://www.geeksforgeeks.org/doolittle-algorithm-lu-decomposition/ //////!!
		Matrix[] set = new MatrixF[2];
		Matrix u = new MatrixF(this.columnCount(), this.rowCount());
		Matrix l = new MatrixF(this.columnCount(), this.rowCount());
		set[0] = u;
		set[1] = l;
		for (int i = 0; i < this.elements.length; i++) {
			for (int j = i; j < this.elements[i].length; j++) {
				float temp = 0;
				for (int k = 0; k < i; k++) {
					temp += l.elements[i][k].floatValue() * u.elements[k][j].floatValue();
				}
				u.elements[i][j] = this.elements[i][j].floatValue() - temp;
			}

			for (int j = 0; j < this.elements[i].length; j++) {
				if (i == j) {
					l.elements[i][i] = 1;
				} else {
					int temp = 0;
					for (int k = 0; k < i; k++) {
						temp += (l.elements[j][k].floatValue() * u.elements[k][i].floatValue());
					}
					l.elements[j][i] = (this.elements[j][i].floatValue() - temp) / u.elements[i][i].floatValue();
				}
			}
		}
//		!!//////Mój algorytm//////!! dzia³a ale czy jest wystarczaj¹co wydajny (raczej nie)?

//		Matrix[] set = new MatrixF[2];
//		Matrix u = new MatrixF(this.sizeX(), this.sizeY());
//		Matrix l = new MatrixF(this.sizeX(), this.sizeY());
//		l.identity();
//		set[0] = u;
//		set[1] = l;
//		for (int i = 0; i < this.elements.length; i++) {
//			for (int j = 0; j < this.elements[i].length; j++) {
//
//				if (i <= j) {
//					float temp = 0;
//					for (int k = 0; k < this.elements[i].length; k++) {
//						temp += l.elements[i][k].floatValue() * u.elements[k][j].floatValue();
//					}
//					u.elements[i][j] = this.elements[i][j].floatValue() - temp;
//				}
//
//				if (i > j) {
//					float temp = 0;
//					for (int k = 0; k < this.elements[i].length; k++) {
//						temp += (l.elements[i][k].floatValue() * u.elements[k][j].floatValue());
//					}
//					l.elements[i][j] = this.elements[i][j].floatValue() - temp;
//					l.elements[i][j] = l.elements[i][j].floatValue() * (1 / u.elements[j][j].floatValue());
//					System.out.println(l.elements[i][j].floatValue() * (1 / u.elements[j][j].floatValue()));
//				}
//			}
//		}
		for (int i = 0; i < u.elements.length; i++) {
			for (int j = 0; j < u.elements[i].length; j++) {
				if (u.elements[i][j].equals(Float.NEGATIVE_INFINITY)) {
					throw new IllegalArgumentException("Error: There is 0 in diagonal of the upper matrix");
				}
			}
		}

		return set;
	}

	@Override
	public Number det() {
		Matrix matrix = this.luDecomposition()[0];
		float det = 1;
		for (int i = 0; i < this.elements.length; i++) {
			det *= matrix.elements[i][i].floatValue();
		}
		return det;
	}

	@Override
	public Matrix roundElements(int pos) {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				float num = elements[i][j].floatValue() * (float) Math.pow(10, pos);
				num = Math.round(num);
				num /= Math.pow(10, pos);
				this.elements[i][j] = num;
			}
		}
		return this;
	}

	@Override
	public Matrix invers() {
		Matrix[] lu = this.luDecomposition();
		Matrix u = lu[0];
		Matrix l = lu[1];
		Matrix identity = new MatrixF(this.columnCount(), this.rowCount());
		identity.identity();
		Matrix z = new MatrixF(this.columnCount(), this.rowCount());
		Matrix invers = new MatrixF(this.columnCount(), this.rowCount());

//		kolumna z
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				float sum = 0;
				for (int k = 0; k < j; k++) {
					sum += l.elements[j][k].floatValue() * z.elements[k][i].floatValue();
				}
				z.elements[j][i] = (identity.elements[j][i].floatValue() - sum) / l.elements[j][j].floatValue();
			}
//			kolumna invers
			for (int j = elements[i].length - 1; j >= 0; j--) {
				float sum = 0;
				for (int k = j + 1; k < elements.length; k++) {
					sum += u.elements[j][k].floatValue() * invers.elements[k][i].floatValue();
				}
				invers.elements[j][i] = (z.elements[j][i].floatValue() - sum) / u.elements[j][j].floatValue();
			}
		}
		this.elements = invers.elements;
		return this;
	}

	@Override
	public FloatBuffer putTo(Buffer buffer) {
		FloatBuffer bufferF = (FloatBuffer) buffer;
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				bufferF.put(elements[j][i].floatValue());
			}
		}
		buffer.flip();
		return bufferF;
	}

	@Override
	public Matrix translate(Vector vector) {
		if (this.rowCount() == 4 && this.columnCount() == 4) {
			this.elements[0][3] = 	Math.fma(this.elements[0][0].floatValue(), vector.get(0).floatValue(), 
									Math.fma(this.elements[0][1].floatValue(), vector.get(1).floatValue(), 
									Math.fma(this.elements[0][2].floatValue(), vector.get(2).floatValue(), 
											 this.elements[0][3].floatValue())));
			
			this.elements[1][3] =	Math.fma(this.elements[1][0].floatValue(), vector.get(0).floatValue(), 
									Math.fma(this.elements[1][1].floatValue(), vector.get(1).floatValue(), 
									Math.fma(this.elements[1][2].floatValue(), vector.get(2).floatValue(), 
											 this.elements[1][3].floatValue())));
			
			this.elements[2][3] =	Math.fma(this.elements[2][0].floatValue(), vector.get(0).floatValue(), 
									Math.fma(this.elements[2][1].floatValue(), vector.get(1).floatValue(), 
									Math.fma(this.elements[2][2].floatValue(), vector.get(2).floatValue(), 
											 this.elements[2][3].floatValue())));
			
			this.elements[3][3] = 	Math.fma(this.elements[3][0].floatValue(), vector.get(0).floatValue(), 
									Math.fma(this.elements[3][1].floatValue(), vector.get(1).floatValue(), 
									Math.fma(this.elements[3][2].floatValue(), vector.get(2).floatValue(), 
											 this.elements[3][3].floatValue())));
		} else {
			throw new IllegalStateException("Error: incomapatible size of the matrix");
		}
		return this;
	}

	@Override
	public Matrix rotate(Number angle, Vector axis) {
		if (this.rowCount() == 3 && this.columnCount() == 3) {
//			TODO: set method for it
		} else if (this.rowCount() == 4 && this.columnCount() == 4) {
			float c = (float) Math.cos(angle.floatValue());
			float s = (float) Math.sin(angle.floatValue());
			float oneminusc = 1.0f - c;
			float xy = axis.get(0).floatValue() * axis.get(1).floatValue();
			float yz = axis.get(1).floatValue() * axis.get(2).floatValue();
			float xz = axis.get(0).floatValue() * axis.get(1).floatValue();
			float xs = axis.get(0).floatValue() * s;
			float ys = axis.get(1).floatValue() * s;
			float zs = axis.get(2).floatValue() * s;

			float[] e = new float[3];
			for (int i = 0; i < e.length; i++) {
				e[i] = ((float) Math.pow(axis.get(i).floatValue(), 2)) * oneminusc + c;
			}

			float e10 = xy * oneminusc - zs;
			float e20 = xz * oneminusc + ys;

			float e01 = xy * oneminusc + zs;
			float e21 = yz * oneminusc + xs;

			float e02 = xz * oneminusc + ys;
			float e12 = yz * oneminusc - xs;

			float t00 = this.elements[0][0].floatValue() * e[0] + this.elements[0][1].floatValue() * e10 + this.elements[0][2].floatValue() * e20;
			float t10 = this.elements[1][0].floatValue() * e[0] + this.elements[1][1].floatValue() * e10 + this.elements[1][2].floatValue() * e20; 
			float t20 = this.elements[2][0].floatValue() * e[0] + this.elements[2][1].floatValue() * e10 + this.elements[2][2].floatValue() * e20;
			float t30 = this.elements[2][0].floatValue() * e[0] + this.elements[2][1].floatValue() * e10 + this.elements[2][2].floatValue() * e20;

			float t01 = this.elements[0][0].floatValue() * e01 + this.elements[0][1].floatValue() * e[1] + this.elements[0][2].floatValue() * e21;
			float t11 = this.elements[1][0].floatValue() * e01 + this.elements[1][1].floatValue() * e[1] + this.elements[1][2].floatValue() * e21;
			float t21 = this.elements[2][0].floatValue() * e01 + this.elements[2][1].floatValue() * e[1] + this.elements[2][2].floatValue() * e21;
			float t31 = this.elements[3][0].floatValue() * e01 + this.elements[3][1].floatValue() * e[1] + this.elements[3][2].floatValue() * e21;

			this.elements[0][2] = this.elements[0][0].floatValue() * e02 + this.elements[0][1].floatValue() * e12 + this.elements[0][2].floatValue() * e[2];
			this.elements[1][2] = this.elements[1][0].floatValue() * e02 + this.elements[1][1].floatValue() * e12 + this.elements[1][2].floatValue() * e[2];
			this.elements[2][2] = this.elements[2][0].floatValue() * e02 + this.elements[2][1].floatValue() * e12 + this.elements[2][2].floatValue() * e[2];
			this.elements[3][2] = this.elements[3][0].floatValue() * e02 + this.elements[3][1].floatValue() * e12 + this.elements[3][2].floatValue() * e[2];

			this.elements[0][0] = t00;
			this.elements[1][0] = t10;
			this.elements[2][0] = t20;
			this.elements[3][0] = t30;

			this.elements[0][1] = t01;
			this.elements[1][1] = t11;
			this.elements[2][1] = t21;
			this.elements[3][1] = t31;
		} else {
			throw new IllegalStateException();
		}
		return this;
	}

	@Override
	public Matrix scale(Vector scale) {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length - 1; j++) {
				if (j != columnCount() - 1) {
					this.elements[i][j] = this.elements[i][j].floatValue() * scale.get(j).floatValue();
				}
			}
		}
		return this;
	}
}
