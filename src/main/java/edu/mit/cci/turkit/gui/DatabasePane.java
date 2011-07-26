package edu.mit.cci.turkit.gui;

import edu.mit.cci.turkit.turkitBridge.TurKit;
import edu.mit.cci.turkit.util.U;

import javax.swing.*;



import javax.swing.*;
import java.awt.*;

public class DatabasePane extends JPanel implements SimpleEventListener {
	SimpleEventManager sem;
	JTextArea text;
	TurKit turkit;

	public void init(TurKit turkit) {
		this.turkit = turkit;
	}
	
	public DatabasePane(SimpleEventManager _sem) throws Exception {
		this.sem = _sem;
		sem.addListener(this);

		text = new JTextArea();
		text.setEditable(false);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		text.setFont(font);

		setLayout(new BorderLayout());
		add(new JScrollPane(text));
	}

	public void onEvent(SimpleEvent e) throws Exception {
		if (e.name == "updateDatabase") {
			turkit.database.consolidate();
			text.setText(U.slurp(turkit.database.storageFile));
		}
	}
}
