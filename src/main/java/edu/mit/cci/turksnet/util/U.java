package edu.mit.cci.turksnet.util;

import flexjson.JSON;
import flexjson.JSONSerializer;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

    public static Map<String,String> mapifyJSON(String s) throws JSONException {

        String results = s.trim();
        if (results.startsWith("(")) {
            results = results.substring(1,results.length()-1);
        }
        System.err.println("Processing results: "+results);

        JSONObject obj = new JSONObject(results);
        Map<String,String> map = new HashMap<String, String>();
        for (Iterator<String> i = obj.keys();i.hasNext();) {
            String key = i.next();
           map.put(key,obj.get(key).toString());

        }

        return map;
    }

//    public static Map<String,Object> mapifyJSON2Obj(String s) throws JSONException {
//
//        String results = s.trim();
//        if (results.startsWith("(")) {
//            results = results.substring(1,results.length()-1);
//        }
//        System.err.println("Processing results: "+results);
//
//        JSONObject obj = new JSONObject(results);
//        Map<String,String> map = new HashMap<String, String>();
//        for (Iterator<String> i = obj.keys();i.hasNext();) {
//            String key = i.next();
//            Object v = obj.get(key);
//            if (v instanceof JSONArray) {
//                JSONArray a = (JSONArray)v;
//                List<String> result = new ArrayList<String>();
//                for (int j=0;j<a.length();j++) {
//                    result.add(a.getString(j));
////                }
//            }
//
//           map.put(key,obj.get(key).toString());
//
//        }
//
//        return map;
//    }
//

    public static String jsonify(Map<String, Object> vals) {


        StringBuilder buffer = new StringBuilder();
        String sep = "";
        buffer.append("{");
        for (Map.Entry<String, Object> ent : vals.entrySet()) {

            Object rep = ent.getValue();
            String reps = rep.toString();
            buffer.append(sep).append("\"").append(ent.getKey()).append("\":");
            if (rep instanceof Map) {
               buffer.append(jsonify(((Map<String,Object>)ent)));
            } else if (reps.startsWith("{") && reps.endsWith("}")) {
                buffer.append(rep);
            }
            else if (!reps.matches("[\\d\\.]+")) {
                buffer.append('"').append(ent.getValue()).append('"');
            } else {
                buffer.append(ent.getValue());
            }
            sep = ",";
        }
        buffer.append("}");
        return buffer.toString();
    }

    public static String safejson(Object o) {
        String result = "";
        try {
            result = JSONObject.valueToString(o);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (result.startsWith("\"") && result.endsWith("\"")) {
            result =  result.substring(1,result.length()-1);
        }
        return result;
    }

    public static void main(String[] arg) throws JSONException {
        Map<String,String> result = mapifyJSON("{\"foo\":[1,3,4,5],\"bar\":\"whatever\"}");
        System.err.println(result.get("foo"));

    }
}
