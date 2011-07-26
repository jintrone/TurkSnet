package edu.mit.cci.turkit.util;

/**
 * User: jintrone
 * Date: 7/20/11
 * Time: 11:32 AM
 */
public interface TurkitOutputSink {

    public void startCapture();
    public void stopCapture();
    public void setText(String text);

}
