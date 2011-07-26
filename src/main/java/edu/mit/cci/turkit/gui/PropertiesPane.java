package edu.mit.cci.turkit.gui;

public class PropertiesPane extends CodePane {

	public PropertiesPane(SimpleEventManager _sem) throws Exception {
		super(_sem);
	}

	public void setMode(String mode) throws Exception {
		if (file == null)
			return;

		reload();
		String s = text.getText();
		s = s.replaceFirst("(?m)^mode\\s+=(.*)$", "mode = " + mode);
		text.setText(s);
		save();
	}
}
