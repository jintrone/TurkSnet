package edu.mit.cci.turkit.gui;

import edu.mit.cci.turkit.util.U;


import javax.swing.*;
import java.util.Vector;

public class SimpleEventManager {
	Vector<SimpleEventListener> listeners = new Vector();

	public SimpleEventManager() {

	}

	public void addListener(SimpleEventListener s) {
		listeners.add(s);
	}

	public SimpleEvent fireEvent(String name, Object a, Object b) {
		return fireEvent(new SimpleEvent(name, a, b));
	}

	public SimpleEvent fireEvent(SimpleEvent e) {
		for (SimpleEventListener lis : listeners) {
			try {
				lis.onEvent(e);
			} catch (Exception ee) {
				U.rethrow(ee);
			}
		}
		return e;
	}

	public void pushEvent(String name, Object a, Object b) {
		final SimpleEvent e = new SimpleEvent(name, a, b);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				fireEvent(e);
			}
		});
	}
}
