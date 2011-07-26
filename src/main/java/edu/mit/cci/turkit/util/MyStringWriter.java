package edu.mit.cci.turkit.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyStringWriter extends PrintWriter {
	public StringWriter stringWriter = null;

	public MyStringWriter() {
		super(new StringWriter());
		stringWriter = (StringWriter) out;
	}

	public String toString() {
		return stringWriter.toString();
	}
}
