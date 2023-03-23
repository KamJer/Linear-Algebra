package matrix;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import vector.Vector;

public class MatrixI extends Matrix{

	public MatrixI(Matrix matrix) {
		super(matrix);
	}
	
	public MatrixI(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		elements = new Integer[rowCount][columnCount];
	}
	
	@Override
	public Matrix add(Matrix matrix) {
		if (matrix.columnCount() == this.columnCount() && matrix.rowCount() == this.rowCount()) {
			for (int i = 0; i < elements.length; i++) {
				for (int j = 0; j < elements[i].length; j++) {
					this.elements[i][j] = this.elements[i][j].intValue() + matrix.elements[i][j].intValue();
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
					this.elements[i][j] = this.elements[i][j].intValue() - matrix.elements[i][j].intValue();
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
						temp.set(j, i, elements[i][k].intValue() * elements[k][j].intValue());
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
				this.elements[i][j] = this.elements[i][j].intValue() * a.intValue();
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
					temp += l.elements[i][k].intValue() * u.elements[k][j].intValue();
				}
				u.elements[i][j] = this.elements[i][j].intValue() - temp;
			}

			for (int j = 0; j < this.elements[i].length; j++) {
				if (i == j) {
					l.elements[i][i] = 1;
				} else {
					int temp = 0;
					for (int k = 0; k < i; k++) {
						temp += (l.elements[j][k].intValue() * u.elements[k][i].intValue());
					}
					l.elements[j][i] = (this.elements[j][i].intValue() - temp) / u.elements[i][i].intValue();
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
//						temp += l.elements[i][k].intValue() * u.elements[k][j].intValue();
//					}
//					u.elements[i][j] = this.elements[i][j].intValue() - temp;
//				}
//
//				if (i > j) {
//					float temp = 0;
//					for (int k = 0; k < this.elements[i].length; k++) {
//						temp += (l.elements[i][k].intValue() * u.elements[k][j].intValue());
//					}
//					l.elements[i][j] = this.elements[i][j].intValue() - temp;
//					l.elements[i][j] = l.elements[i][j].intValue() * (1 / u.elements[j][j].intValue());
//					System.out.println(l.elements[i][j].intValue() * (1 / u.elements[j][j].intValue()));
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
			det *= matrix.elements[i][i].intValue();
		}
		return det;
	}

	@Override
	public Matrix roundElements(int pos) {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				float num = elements[i][j].intValue() * (float) Math.pow(10, pos);
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
					sum += l.elements[j][k].intValue() * z.elements[k][i].intValue();
				}
				z.elements[j][i] = (identity.elements[j][i].intValue() - sum) / l.elements[j][j].intValue();
			}
//			kolumna invers
			for (int j = elements[i].length - 1; j >= 0; j--) {
				float sum = 0;
				for (int k = j + 1; k < elements.length; k++) {
					sum += u.elements[j][k].intValue() * invers.elements[k][i].intValue();
				}
				invers.elements[j][i] = (z.elements[j][i].intValue() - sum) / u.elements[j][j].intValue();
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
				bufferF.put(elements[j][i].intValue());
			}
		}
		buffer.flip();
		return bufferF;
	}

	@Override
	public Matrix translate(Vector vector) {
		if (this.rowCount() == 4 && this.columnCount() == 4) {
			this.elements[0][3] = 	Math.fma(this.elements[0][0].intValue(), vector.get(0).intValue(), 
									Math.fma(this.elements[0][1].intValue(), vector.get(1).intValue(), 
									Math.fma(this.elements[0][2].intValue(), vector.get(2).intValue(), 
											 this.elements[0][3].intValue())));
			
			this.elements[1][3] =	Math.fma(this.elements[1][0].intValue(), vector.get(0).intValue(), 
									Math.fma(this.elements[1][1].intValue(), vector.get(1).intValue(), 
									Math.fma(this.elements[1][2].intValue(), vector.get(2).intValue(), 
											 this.elements[1][3].intValue())));
			
			this.elements[2][3] =	Math.fma(this.elements[2][0].intValue(), vector.get(0).intValue(), 
									Math.fma(this.elements[2][1].intValue(), vector.get(1).intValue(), 
									Math.fma(this.elements[2][2].intValue(), vector.get(2).intValue(), 
											 this.elements[2][3].intValue())));
			
			this.elements[3][3] = 	Math.fma(this.elements[3][0].intValue(), vector.get(0).intValue(), 
									Math.fma(this.elements[3][1].intValue(), vector.get(1).intValue(), 
									Math.fma(this.elements[3][2].intValue(), vector.get(2).intValue(), 
											 this.elements[3][3].intValue())));
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
			float c = (float) Math.cos(angle.intValue());
			float s = (float) Math.sin(angle.intValue());
			float oneminusc = 1.0f - c;
			float xy = axis.get(0).intValue() * axis.get(1).intValue();
			float yz = axis.get(1).intValue() * axis.get(2).intValue();
			float xz = axis.get(0).intValue() * axis.get(1).intValue();
			float xs = axis.get(0).intValue() * s;
			float ys = axis.get(1).intValue() * s;
			float zs = axis.get(2).intValue() * s;

			float[] e = new float[3];
			for (int i = 0; i < e.length; i++) {
				e[i] = ((float) Math.pow(axis.get(i).intValue(), 2)) * oneminusc + c;
			}

			float e10 = xy * oneminusc - zs;
			float e20 = xz * oneminusc + ys;

			float e01 = xy * oneminusc + zs;
			float e21 = yz * oneminusc + xs;

			float e02 = xz * oneminusc + ys;
			float e12 = yz * oneminusc - xs;

			float t00 = this.elements[0][0].intValue() * e[0] + this.elements[0][1].intValue() * e10 + this.elements[0][2].intValue() * e20;
			float t10 = this.elements[1][0].intValue() * e[0] + this.elements[1][1].intValue() * e10 + this.elements[1][2].intValue() * e20; 
			float t20 = this.elements[2][0].intValue() * e[0] + this.elements[2][1].intValue() * e10 + this.elements[2][2].intValue() * e20;
			float t30 = this.elements[2][0].intValue() * e[0] + this.elements[2][1].intValue() * e10 + this.elements[2][2].intValue() * e20;

			float t01 = this.elements[0][0].intValue() * e01 + this.elements[0][1].intValue() * e[1] + this.elements[0][2].intValue() * e21;
			float t11 = this.elements[1][0].intValue() * e01 + this.elements[1][1].intValue() * e[1] + this.elements[1][2].intValue() * e21;
			float t21 = this.elements[2][0].intValue() * e01 + this.elements[2][1].intValue() * e[1] + this.elements[2][2].intValue() * e21;
			float t31 = this.elements[3][0].intValue() * e01 + this.elements[3][1].intValue() * e[1] + this.elements[3][2].intValue() * e21;

			this.elements[0][2] = this.elements[0][0].intValue() * e02 + this.elements[0][1].intValue() * e12 + this.elements[0][2].intValue() * e[2];
			this.elements[1][2] = this.elements[1][0].intValue() * e02 + this.elements[1][1].intValue() * e12 + this.elements[1][2].intValue() * e[2];
			this.elements[2][2] = this.elements[2][0].intValue() * e02 + this.elements[2][1].intValue() * e12 + this.elements[2][2].intValue() * e[2];
			this.elements[3][2] = this.elements[3][0].intValue() * e02 + this.elements[3][1].intValue() * e12 + this.elements[3][2].intValue() * e[2];

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
					this.elements[i][j] = this.elements[i][j].intValue() * scale.get(j).intValue();
				}
			}
		}
		return this;
	}
}
