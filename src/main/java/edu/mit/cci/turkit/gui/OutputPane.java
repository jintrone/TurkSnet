package edu.mit.cci.turkit.gui;

import com.sun.tools.example.debug.gui.OutputSink;
import edu.mit.cci.turkit.util.TurkitOutputSink;
import edu.mit.cci.turkit.util.WireTap;


import javax.swing.*;
import java.awt.*;

public class OutputPane extends JPanel implements SimpleEventListener, TurkitOutputSink {
	SimpleEventManager sem;
	JTextArea text;
	WireTap wireTap;

	public OutputPane(SimpleEventManager _sem) throws Exception {
		this.sem = _sem;
		sem.addListener(this);

		text = new JTextArea();
		text.setEditable(false);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		text.setFont(font);

		setLayout(new BorderLayout());
		add(new JScrollPane(text));
	}
	
	public void startCapture() {
		wireTap = new WireTap();
	}
	
	public void stopCapture() {
		text.setText(wireTap.close());
		wireTap = null;
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}

	public void onEvent(SimpleEvent e) throws Exception {
	}
}
