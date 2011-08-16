package edu.mit.cci.turkit.turkitBridge;

import edu.mit.cci.turkit.MTurkSOAP;
import edu.mit.cci.turkit.RhinoUtil;
import edu.mit.cci.turkit.TurKitPlaceholder;
import edu.mit.cci.turkit.gui.JavaScriptDatabase;
import edu.mit.cci.turkit.util.NamedSource;
import edu.mit.cci.turkit.util.U;
import edu.mit.cci.turksnet.Session_;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurKit extends TurKitPlaceholder {

    /**
     * The version number for this release of TurKit.
     */
    public String version = null;

    /**
     * Your Amazon AWS Access Key ID
     */
    public String awsAccessKeyID;

    /**
     * Your Amazon AWS Secret Access Key
     */
    public String awsSecretAccessKey;

    /**
     * A reference to the current JavaScript file being executed.
     */
    public NamedSource jsFile;

    /**
     * A reference to the {@link JavaScriptDatabase} associated with this TurKit
     * program.
     */
    public JavaScriptDatabase database;

    /**
     * This is an HTML template used for created HITs hosted on your S3 account.
     */
    public String taskTemplate;

    /**
     * Should be one of the following values: "offline", "sandbox", "real"
     */
    public String mode;

    /**
     * True iff we want to see verbose output from the TurKit program.
     */
    public boolean verbose = true;

    /**
     * True iff the safety values {@link TurKit#maxMoney} and
     * {@link TurKit#maxHITs} should be used.
     */
    public boolean safety = true;

    /**
     * TurKit will try to ensure that no more money than this is spend over
     * <i>all</i> runs of the program.
     */
    public double maxMoney = 10;

    /**
     * TurKit will try to ensure that no more than this many HITs will be
     * created over <i>all</i> runs of the program.
     */
    public int maxHITs = 100;

    /**
     * True if the TurKit program threw the "stop" exception by calling
     * <code>stop()</code> on the most recent run of the program.
     */
    public boolean stopped = false;


    public Session_ currentSession = null;

    /**
     * This is the main entry point for creating a TurKit program.
     *
     * @param source      a NamedSource with Reader that provideds the TurKit program, written in JavaScript,
     *                    using the TurKit JavaScript API.
     * @param accessKeyId your Amazon AWS access key id.
     * @param secretKey   your Amazon AWS secret key.
     * @param mode        set to "offline", "sandbox" or "real". ("offline" will not let
     *                    you access MTurk or S3. "sandbox" will let you access the
     *                    MTurk sandbox, and the real S3. "real" mode accesses the real
     *                    MTurk and the real S3.)
     * @throws Exception
     */
    public TurKit(NamedSource source, String accessKeyId, String secretKey, String mode)
            throws Exception {
        reinit(source, accessKeyId, secretKey, mode);
    }

    public void setActiveSession(Session_ session) {
        this.currentSession = session;

    }

    public Session_ getCurrentSession() {
        return currentSession;
    }

    /**
     * Use this function if you want to re-initialize certain parameters of the
     * TurKit object. This function will only have an effect if these values are
     * different from their current values.
     *
     * @throws Exception
     */
    public void reinit(NamedSource source , String accessKeyId, String secretKey,
                       String mode) throws Exception {
        mode = mode.toLowerCase();

        if (version == null) {
            version = U.slurp(this.getClass().getResource(
                    "/version.properties"));
            {
                Matcher m = Pattern.compile("=(.*)").matcher(version);
                if (m.find()) {
                    version = m.group(1).trim();
                }
            }
        }
        if (taskTemplate == null) {
            taskTemplate = U.slurp(this.getClass().getResource(
                    "/task-template.html"));
        }

        this.awsAccessKeyID = accessKeyId;
        this.awsSecretAccessKey = secretKey;
        this.mode = mode;
        if (!mode.equals("sandbox") && !mode.equals("real")
                && !mode.equals("offline")) {
            throw new IllegalArgumentException(
                    "mode must be either sandbox, real or offline.");
        }

        NamedSource oldSource = this.jsFile;
        this.jsFile = source;
        if (!jsFile.equals(oldSource)) {
            if (database != null) {
                database.close();
            }
            database = null;
            loadDatabase();
        }
    }

    /**
     * Changes the mode. Possible values include "offline", "sandbox" and
     * "real".
     * <ul>
     * <li>"offline" will not let you access MTurk or S3.</li>
     * <li>"sandbox" will let you access the MTurk sandbox, and the real S3.</li>
     * <li>"real" mode accesses the real MTurk and the real S3.</li>
     * </ul>
     */
    public void setMode(String mode) throws Exception {
        reinit(jsFile, awsAccessKeyID, awsSecretAccessKey, mode);
    }

    /**
     * Performs a SOAP request on MTurk. The <code>paramsList</code> must be a
     * sequence of strings of the form a1, b1, a2, b2, a3, b3 ... Where aN is a
     * parameter name, and bN is the value for that parameter. Most common
     * parameters have suitable default values, namely: Version, Timestamp,
     * Query, and Signature.
     * <p/>
     * This is a replacement for the deprecated
     * {@link edu.mit.cci.turkit.MTurk#restRequest(String, String, boolean, String, String...)}
     */
    public String soapRequest(String operation, String XMLstring)
            throws Exception {
        if (mode.equals("offline"))
            throw new Exception(
                    "You may not make a REST request to MTurk in offline mode.");

        MTurkSOAP m = new MTurkSOAP(awsAccessKeyID, awsSecretAccessKey,
                mode.equals("sandbox"));
        return m.soapRequest(operation, XMLstring);
    }

    /**
     * Deletes the database file(s) and creates a new one.
     *
     * @param saveBackup set to true if you want to keep a copy of the database file
     */
    public void resetDatabase(boolean saveBackup) throws Exception {
        if (database != null) {
            runOnce(0, 0, new NamedSource.URLSource(this.getClass().getResource(TurKitPlaceholder.js_utils_path + "/" + "/resetDatabase.js")));

            database.delete(saveBackup);
            database = null;
        }
        loadDatabase();
    }

    private void loadDatabase() throws Exception {

        System.err.println("Loading database at "+new File(".").getAbsolutePath());
        database = new JavaScriptDatabase(new File(jsFile.getName()
                + ".database"), new File(jsFile.getName()
                + ".database.tmp"));
    }

    /**
     * Call this when you are done using this TurKit object. It will release any
     * resources it is using, including a {@link JavaScriptDatabase}.
     */

    public void close() {
        database.close();
    }

    /**
     * Runs the JavaScript program in the <code>source</code> once. The
     * <code>source</code> must be a File or URL.
     *
     * @param maxMoney if non-zero, sets how much money the JavaScript program may
     *                 spend (over all it's runs) before throwing a safety exception.
     * @param maxHITs  if non-zero, sets how many HITs the JavaScript program may
     *                 create (over all it's runs) before throwing a safety
     *                 exception.
     * @return true iff no "stop" exceptions were thrown using
     *         <code>stop()</code> in the JavaScript program.
     * @throws Exception
     */
    public boolean runOnce(double maxMoney, int maxHITs, NamedSource source)
            throws Exception {
        this.maxMoney = maxMoney;
        this.maxHITs = maxHITs;
        if (maxMoney < 0 && maxHITs < 0) {
            safety = false;
        } else if (maxMoney < 0 || maxHITs < 0) {
            throw new Exception(
                    "if maxMoney or maxHITs is 0, then they must both be 0.");
        }
        try {

            Context cx = Context.enter();
            cx.setLanguageVersion(170);
            Scriptable scope = cx.initStandardObjects();

            scope.put("javaTurKit", scope, this);

            for (String s : U.getResourceListing(this.getClass(), js_libs_path)) {

                RhinoUtil.evaluateURL(cx, scope,
                        this.getClass().getResource(js_libs_path + "/" + s));
            }

            stopped = false;
//            if (source instanceof URL) {
//                RhinoUtil.evaluateURL(cx, scope, (URL) source);
//            } else if (source instanceof File) {
//                RhinoUtil.evaluateFile(cx, scope, (File) source);
//            } else if (source instanceof NamedString) {
//                RhinoUtil.evaluateString(cx, scope, (NamedString) source)
//            }
            RhinoUtil.evaluateNamedSource(cx,scope,source);
        } catch (Exception e) {
            if (e instanceof JavaScriptException) {
                JavaScriptException je = (JavaScriptException) e;
                if (je.details().equals("stop")) {
                    if (verbose) {
                        System.out.println("stopped");
                    }
                    return false;
                }
            }
            if (verbose) {
                e.printStackTrace();
            }
            throw e;
        }
        return !stopped;
    }

    /**
     * Runs the JavaScript program once.
     *
     * @param maxMoney if non-zero, sets how much money the JavaScript program may
     *                 spend (over all it's runs) before throwing a safety exception.
     * @param maxHITs  if non-zero, sets how many HITs the JavaScript program may
     *                 create (over all it's runs) before throwing a safety
     *                 exception.
     * @return true iff no "stop" exceptions were thrown using
     *         <code>stop()</code> in the JavaScript program.
     * @throws Exception
     */
    public boolean runOnce(double maxMoney, int maxHITs) throws Exception {
        return runOnce(maxMoney, maxHITs, jsFile);
    }

    /**
     * Delete all your HITs.
     */
    public void deleteAllHITs() throws Exception {
        URL url = this.getClass().getResource(TurKitPlaceholder.js_utils_path + "/" + "/deleteAllHITs.js");
        runOnce(maxMoney, maxHITs, new NamedSource.URLSource(url));
    }

    /**
     * Calls {@link TurKit#runOnce(double, int)} repeatedly, with a delay of
     * <code>retryAfterSeconds</code> between runs.
     *
     * @param retryAfterSeconds number of seconds delay between calling
     *                          {@link TurKit#runOnce(double, int)}.
     * @param maxMoney          see {@link TurKit#runOnce(double, int)}.
     * @param maxHITs           see {@link TurKit#runOnce(double, int)}.
     * @throws Exception
     */
    public void run(int retryAfterSeconds, double maxMoney, int maxHITs)
            throws Exception {
        while (true) {
            if (runOnce(maxMoney, maxHITs)) {
                break;
            }
            Thread.sleep(retryAfterSeconds * 1000);
        }
    }
}
