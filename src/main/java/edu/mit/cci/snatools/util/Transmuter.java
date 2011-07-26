package edu.mit.cci.snatools.util;

public interface Transmuter<F, T> {
	
	public T transmute(F obj);

}
