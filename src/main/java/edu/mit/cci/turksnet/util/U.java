package edu.mit.cci.turksnet.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: jintrone
 * Date: 2/24/11
 * Time: 10:38 AM
 */
public class U {


    private static String VAL_SEP = ";";
    private static String VAR_SEP = "&";
    private static String VAR_VAL_SEP = "=";



    public static String escape(Object... vals) {
        StringBuffer buffer = new StringBuffer();
        for (Object val : vals) {
            try {
                buffer.append(URLEncoder.encode(val.toString(), "UTF-8"));
                buffer.append(VAL_SEP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public static String[] unescape(String vals) {
        List<String> result = new ArrayList<String>();
        for (String val : vals.split(VAL_SEP)) {
            try {
                result.add(URLDecoder.decode(val, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toArray(new String[]{});
    }

    public static String stringify(Map<Object,Object> input) {
        StringBuilder builder= new StringBuilder();
        String sep = "";
        for (Map.Entry<Object,Object> ent:input.entrySet()){
            builder.append(sep);
            builder.append(ent.getKey()).append(VAR_VAL_SEP).append(ent.getValue());
            sep = VAR_SEP;
        }
        return builder.toString();
    }

    public static Map<String,String[]> mapify(String str) {
        return Collections.emptyMap();
    }

    public static String update(String map,String key, String val) {
        String[] props = map.split(VAR_SEP);
        String nprop = key+VAR_VAL_SEP+val;
        String toreplace = null;
        for (String prop:props) {
            String[] kvp = prop.split(VAR_VAL_SEP);
            if (key.equals(kvp[0])) {
                toreplace = prop;
                break;
            }
        }

        if (toreplace!=null) {
            map = map.replaceFirst(toreplace,nprop);
        } else {
            map = map.trim().length()>0?map+VAR_SEP+nprop:nprop;
        }
        return map;
    }

    public static String join(String[] array, String sep) {
        StringBuffer buff = new StringBuffer();
        for (int i=0;i<array.length;i++) {
            if (i>0) buff.append(sep);
            buff.append(array[i]);
        }
        return buff.toString();
    }

    public static Set<Integer> randIntSet(int maxval,int count) {
        Set<Integer> result = new HashSet<Integer>();
        for (int i=0;i<count;i++) {
            int candidate = (int)(maxval*Math.random());
            while (result.contains(candidate)) {
                candidate = (int)(maxval*Math.random());
            }
            result.add(candidate);
        }
        return result;
    }

}
