package matrix;

import java.nio.Buffer;

import vector.Vector;

public abstract class Matrix {

	protected Number[][] elements;
	
	/**
	 * Creates the matrix with given size
	 * @param columnCount - the amount of columns
	 * @param rowCount - the amount of rows
	 */
	public Matrix(int rowCount, int columnCount) {
		this.elements = new Number[rowCount][columnCount];
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				this.elements[i][j] = 0;
			}
		}
	}

	public Matrix(Matrix matrix) {
		this.elements = new Number[matrix.rowCount()][matrix.columnCount()];
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				this.elements[i][j] = matrix.elements[i][j];
			}
		}
	}

	public int columnCount() {
		return elements[0].length;
	}

	public int rowCount() {
		return elements.length;
	}
	
	/**
	 * Returns the value that is being hold in the element of the given coordinate
	 * @param row - column of the element
	 * @param column - row of the element
	 * @return
	 */
	public Number get(int row, int column) {
		return elements[row][column];
	}

	public void set(int row, int column, Number value) {
		this.elements[row][column] = value;
	}

	public void identity() {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				if (j == i) {
					elements[i][j] = 1f;
				} else {
					elements[i][j] = 0f;
				}
			}
		}
	}

	public Matrix traspose() {
		Number[][] matrixtemp = new Number[columnCount()][rowCount()];
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				matrixtemp[i][j] = this.elements[j][i];
			}
		}
		this.elements = matrixtemp;
		return this;
	}

	public boolean isIdentity() {
		int correct = 0;
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				if (i == j) {
					if (elements[i][j].equals(1)) {
						correct ++;
					}
				} else {
					if (elements[i][j].equals(0)) {
						correct ++;
					}
				}
			}
		}
		return correct == columnCount() * rowCount();
	}
	
	public abstract Matrix add(Matrix matrix);
	public abstract Matrix sub(Matrix matrix);
	public abstract Matrix mul(Matrix matrix);
	public abstract Matrix mul(Number a);
	public abstract Matrix[] luDecomposition();
	public abstract Number det();
	public abstract Matrix roundElements(int pos);
	public abstract Matrix invers();
	public abstract Buffer putTo(Buffer buffer);
	public abstract Matrix translate(Vector vector);
	public abstract Matrix rotate(Number angle, Vector axis);
	public abstract Matrix scale(Vector scale);
	
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < elements.length; i++) {
			string += "[";
			for (int j = 0; j < elements[i].length; j++) {
				if (j == columnCount() - 1) {
					string += elements[i][j];
				} else {
					string += elements[i][j] + ", ";
				}
			}
			string += "]" + "\n";
		}
		return string;
	}
}
