package vector;

import matrix.Matrix;

public class VectorF extends Vector {

	public VectorF(int size) {
		super(size);
	}
	
	public VectorF(Number[] vector) {
		super(vector);
	}
	
	public VectorF(Vector vector) {
		super(vector);
	}
	
	@Override
	public Float get(int element) {
		return (Float) super.get(element);
	}

	@Override
	public Vector add(Vector vector) {
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = elements[i].floatValue() + vector.elements[i].floatValue();
		}
		return this;
	}
	
	@Override
	public Vector add(Matrix matrix) {
		Vector vectorTemp = new VectorF(this.size());
		if (this.elements.length == matrix.rowCount()) {
			for (int i = 0; i < matrix.rowCount(); i++) {
				float sum = 0;
				for (int j = 0; j < matrix.columnCount(); j++) {
					sum += this.elements[i].floatValue() * matrix.get(i, j).floatValue();
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
			this.elements[i] = elements[i].floatValue() - vector.elements[i].floatValue();
		}
		return this;
	}

	@Override
	public Vector mul(Number a) {
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = this.elements[i].floatValue() * a.floatValue();
		}
		return this;
	}

	@Override
	public Vector normalize() {
		double module = (double) module();
		for (int i = 0; i < elements.length; i++) {
			elements[i] = elements[i].floatValue() / module;
		}
		return this;
	}

	@Override
	public Number scalar(Vector vector) {
		float scalar = 0;
		for (int i = 0; i < elements.length; i++) {
			scalar += elements[i].floatValue() * vector.elements[i].floatValue();
		}
		return scalar;
	}

	@Override
	public double module() {
		double module = 0;
		for (int i = 0; i < elements.length; i++) {
			module += elements[i].floatValue() * elements[i].floatValue();
		}
		module = Math.sqrt(module);
		return module;
	}

	@Override
	public double angle(Vector vector) {
		double scalar = scalar(vector).doubleValue();
		double modules = this.module() * vector.module();
		return Math.acos(scalar / modules);
	}
}
