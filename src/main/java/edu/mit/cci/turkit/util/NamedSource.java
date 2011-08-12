package edu.mit.cci.turkit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * User: jintrone
 * Date: 7/19/11
 * Time: 11:12 PM
 */
public interface NamedSource {

    public Reader getReader();
    public String getName();



    public static class FileSource implements NamedSource {

        Reader source;
        String name;

        public FileSource(File f) throws FileNotFoundException {
            this.source = new InputStreamReader(
						new FileInputStream(f), Charset.forName("UTF-8"));
            this.name = f.getAbsolutePath();
        }

        @Override
        public Reader getReader() {
           return source;
        }

        @Override
        public String getName() {
            return name;
        }
    }


    public static class URLSource implements NamedSource {

         Reader source;
        String name;

        public URLSource(URL u) throws IOException {
            source = new InputStreamReader(u.openStream(), Charset.forName("UTF-8"));
             name = u.toString();
        }

        @Override
        public Reader getReader() {
           return source;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static class StringSource implements NamedSource {

        String source;
        String name;

        public StringSource(String source, String name) {
            this.name = name;
            this.source = source;
        }

        public String getName() {
            return name;
        }
        public Reader getReader() {
            return new StringReader(source);
        }
    }

}


