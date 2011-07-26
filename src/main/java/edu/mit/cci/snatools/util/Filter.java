/**
 * 
 */
package edu.mit.cci.snatools.util;

import java.util.Collection;

public interface Filter<T> {
	public boolean accept(T d);
	
}