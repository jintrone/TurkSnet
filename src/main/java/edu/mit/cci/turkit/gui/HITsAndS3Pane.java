package edu.mit.cci.turkit.gui;

import edu.mit.cci.turkit.TurKitPlaceholder;
import edu.mit.cci.turkit.turkitBridge.TurKit;
import edu.mit.cci.turkit.util.U;

import javax.swing.*;


import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;

public class HITsAndS3Pane extends JPanel implements SimpleEventListener {
	SimpleEventManager sem;
	JEditorPane html;
	TurKit turkit;
	public static String getOnlineObjectsJs = null;

	public void init(TurKit turkit) {
		this.turkit = turkit;
	}
	
	public HITsAndS3Pane(SimpleEventManager _sem)
			throws Exception {
		this.sem = _sem;
		sem.addListener(this);

		html = new JEditorPane("text/html", "");
		html.setEditable(false);
		html.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (Exception ee) {
						U.rethrow(ee);
					}
				}
			}
		});

		setLayout(new BorderLayout());
		add(new JScrollPane(html));
	}

	public void onEvent(SimpleEvent e) throws Exception {
		if (e.name == "updateDatabase") {
			turkit.database.consolidate();
			if (getOnlineObjectsJs == null) {
				getOnlineObjectsJs = U.slurp(this.getClass().getResource(TurKitPlaceholder.js_utils_path+"/"+
						"getOnlineObjects.js"));
			}
			String s = (String) turkit.database.queryRaw(getOnlineObjectsJs);
			html.setText(s);
			html.setCaretPosition(0);
		}
	}
}
