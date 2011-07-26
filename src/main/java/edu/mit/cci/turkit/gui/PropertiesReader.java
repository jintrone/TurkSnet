package edu.mit.cci.turkit.gui;

import edu.mit.cci.turkit.RhinoUtil;
import edu.mit.cci.turkit.TurKitPlaceholder;
import edu.mit.cci.turkit.turkitBridge.TurKit;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;


import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PropertiesReader {
	public static Map<String, Object> read(String input, boolean showMessages)
			throws Exception {
		Map<String, Object> map = new HashMap();

		Context cx = Context.enter();
		cx.setLanguageVersion(170);
		Scriptable scope = cx.initStandardObjects();

		RhinoUtil.evaluateURL(cx, scope, PropertiesReader.class
                .getResource(TurKit.js_libs_path + "/util.js"));
		scope.put("input", scope, input);

		Set<String> old = new HashSet();
		for (Object o : scope.getIds()) {
			old.add(o.toString());
		}
		try {
            String preader = TurKitPlaceholder.js_utils_path+"/"+"propertiesReader.js";

            URL url = Class.class.getResource(preader);

            RhinoUtil.evaluateURL(cx, scope,url );
		} catch (Exception e) {
			if (e instanceof JavaScriptException) {
				JavaScriptException je = (JavaScriptException) e;
				if (showMessages) {
					JOptionPane.showMessageDialog(null, je.details());
				}
				return null;
			} else {
				throw e;
			}
		}
		for (Object o : scope.getIds()) {
			String key = o.toString();
			if (!old.contains(key)) {
				map.put(key, scope.get(key, null));
			}
		}
		return map;
	}
}
