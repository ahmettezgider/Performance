package de.performance.tdexpressions;

import java.util.Hashtable;
import java.util.Map;

public class Properties {


    final static ThreadLocal<java.util.Properties> runtimeProperties = new ThreadLocal<java.util.Properties>() {
        @Override public java.util.Properties initialValue() { return new java.util.Properties(); }
    };

    final static ThreadLocal<Hashtable<String,Object>> runtimeObjects = new ThreadLocal<Hashtable<String,Object>>() {
        @Override public Hashtable<String,Object> initialValue() { return new Hashtable<String,Object>(); }
    };

    public static void init() {
        runtimeProperties.set(new java.util.Properties());
        runtimeObjects.set(new Hashtable<>());
    }

    public static String getProperty(String name) {
        return getProperty(name, 0);
    }

    public static String getProperty(String name, int n) {

        java.util.Properties p = runtimeProperties.get();
        if(p.containsKey(name)) {
            String val = p.getProperty(name);
            if (n>0) {
                n = Math.min(Math.max(0, n), val.length());
                val = val.substring(0, n);
            }
            return val;
        } else {
            return null;
        }
    }

    public static void setProperty(String n, String value) {
        java.util.Properties p = runtimeProperties.get();
        p.setProperty(n, value);
    }

    public static void setProperties(Map<String,String> properties) {
        if(properties==null) return;
        java.util.Properties p = runtimeProperties.get();
        p.putAll(properties);
    }

    public void removeProperty(String n) {
        runtimeProperties.get().remove(n);
    }

    public static boolean isPropertySet(String n) {
        return runtimeProperties.get().containsKey(n);
    }

    public static Object getObject(String n) {
        Hashtable<String,Object> p = runtimeObjects.get();
        if(p.containsKey(n)) {
            return p.get(n);
        } else {
            return null;
        }
    }

    public static Object getObjectOrDefault(String n, Object value) {
        Hashtable<String,Object> p = runtimeObjects.get();
        if(p.containsKey(n)) {
            return p.get(n);
        } else {
            p.put(n, value);
            return value;
        }
    }

    public static void setObject(String n, Object value) {
        Hashtable<String,Object> p = runtimeObjects.get();
        p.put(n, value);
    }

    public static void removeObject(String n) {
        runtimeObjects.get().remove(n);
    }

    public static boolean isObjectSet(String n) {
        return runtimeObjects.get().containsKey(n);
    }

}
