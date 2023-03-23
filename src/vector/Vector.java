package vector;

import matrix.Matrix;

public abstract class Vector {
	
	protected Number[] elements;
	
	public Vector(int size) {
		this.elements = new Number[size];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = 0;
		}
	}
	
	/**
	 * Creates vector from passed number array, constructor equates passed 
	 * array to the array in the vector instead of rewriting it's content to the vector array
	 * @param vector - array of elements
	 */
	public Vector(Number[] vector) {
		this.elements = vector;
	}
	
	public Vector(Vector vector) {
		this.elements = new Number[vector.size()];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = vector.elements[i];
		}
	}
	
	/**
	 * 
	 * @return - size of the vector/the amount of the elements of in the vector
	 */
	public int size() {
		return elements.length;
	}
	
	public Number get(int element) {
		return elements[element];
	}
	
	public void set(int element, Number value) {
		this.elements[element] = value;
	}
	
	public boolean checkCompatibilty(Vector vector) {
		return vector.size() == this.size();
	}
	
	public double module() {
		double module = 0;
		for (int i = 0; i < elements.length; i++) {
			module += elements[i].doubleValue() * elements[i].doubleValue();
		}
		module = Math.sqrt(module);
		return module;
	}
	
	public double angle(Vector vector) {
		double scalar = scalar(vector).doubleValue();
		double modules = this.module() * vector.module();
		return Math.acos(scalar / modules);
	}
	
	/**
	 * Method to add two vectors
	 * @param vector - vector to add
	 * @return - this vector
	 */
	public abstract Vector add(Vector vector);
	
	
	public abstract Vector add(Matrix matrix);
	/**
	 * Method to subtract two vectors
	 * @param vector - vector to subtract
	 * @return - this vector
	 */
	public abstract Vector sub(Vector vector);
	
	/**
	 * Method to multiply with specific value
	 * @param a - value to multiply vector with
	 * @return this vector
	 */
	public abstract Vector mul(Number a);
	
	/**
	 * Normalizes this vector
	 * @return this vector
	 */
	public abstract Vector normalize();	
	
	/**
	 * Method to calculate scalar of the vector
	 * @return - scalar of the vector
	 */
	public abstract Number scalar(Vector vector);
	
	@Override
	public String toString() {
		String vector = "(";
		for (int i = 0; i < elements.length; i++) {
			if (i == elements.length - 1) {
				vector += elements[i];
			} else {
				vector += elements[i] + ", ";
			}
		}
		vector += ")";
		return vector;
	}
}
