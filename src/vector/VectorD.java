package vector;

import matrix.Matrix;

public class VectorD extends Vector {
	public VectorD(int size) {
		super(size);
	}
	
	public VectorD(Vector vector) {
		super(vector);
	}
	
	@Override
	public Vector add(Vector vector) {
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = elements[i].doubleValue() + vector.elements[i].doubleValue();
		}
		return this;
	}
	
	@Override
	public Vector add(Matrix matrix) {
		Vector vectorTemp = new VectorD(this.size());
		if (this.elements.length == matrix.rowCount()) {
			for (int i = 0; i < matrix.rowCount(); i++) {
				float sum = 0;
				for (int j = 0; j < matrix.columnCount(); j++) {
					sum += this.elements[i].doubleValue() * matrix.get(i, j).doubleValue();
				}
				vectorTemp.set(i, sum);
			}
			this.elements = vectorTemp.elements;
			return this;
		} else {
			throw new IllegalStateException("Error: Matrix and vector incomaptible");
		}
	}

	@Override
	public Vector sub(Vector vector) {
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = elements[i].doubleValue() - vector.elements[i].doubleValue();
		}
		return this;
	}

	@Override
	public Vector mul(Number a) {
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = this.elements[i].doubleValue() * a.doubleValue();
		}
		return this;
	}

	@Override
	public Vector normalize() {
		double module = (double) module();
		for (int i = 0; i < elements.length; i++) {
			elements[i] = elements[i].doubleValue() / module;
		}
		return this;
	}

	@Override
	public Number scalar(Vector vector) {
		float scalar = 0;
		for (int i = 0; i < elements.length; i++) {
			scalar += elements[i].doubleValue() * vector.elements[i].doubleValue();
		}
		return scalar;
	}
}
