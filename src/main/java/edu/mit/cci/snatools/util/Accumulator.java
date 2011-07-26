package edu.mit.cci.snatools.util;


/**
 * Pass this over a collection via CollectionUtils to accumulate stats about a collection.  Useful for counting various instances of things in a collection.
 * @see edu.mit.cci.snatools.util.CollectionUtils#process(java.util.Collection, Accumulator)
 * 
 * @author Joshua Introne
 *
 * @param <F>
 * @param <T>
 */
public interface Accumulator<F, T> {

	public void accumulate(F obj);
	public T result();
}
