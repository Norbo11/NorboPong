package com.github.norbo11.norbopong.util;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.github.norbo11.norbopong.main.Game;

public class Config
{
    private static TreeMap<String, String> values = new TreeMap<>();

    public static void reload()
    {
        ArrayList<String> contents = FileHelper.readFile(Game.FILE_CONFIG);
        
        if (contents == null)
        {
            save();
            reload();
            return;
        }
        
        for (String line : contents)
        {
            String[] split = line.split(":");
            values.put(split[0], split.length == 2 ? split[1] : "");
        }
    }
    
    public static String getString(String key)
    {
        return values.get(key) != null ? values.get(key) : "";
    }
    
    public static int getInteger(String key)
    {
        return Integer.valueOf(values.get(key) != null ? values.get(key) : "0");
    }
    
    public static float getFloat(String key)
    {
        return Float.valueOf(values.get(key) != null  ? values.get(key) : "0");
    }
    
    public static double getDouble(String key)
    {
        return Double.valueOf(values.get(key) != null  ? values.get(key) : "0");
    }
    
    public static void save()
    {
        String contents = "";
        
        for (Entry<String, String> entry : values.entrySet())
        {
            contents += entry.getKey() + ":" + entry.getValue() + "\n";
        }
        
        if (contents.length() >= 2) contents = contents.substring(0, contents.length() - 1);
        
        FileHelper.setFile(Game.FILE_CONFIG, contents);
    }
    
    public static void put(String key, String value)
    {
        values.put(key, value);
    }
    
    public static void put(String key, int value)
    {
        values.put(key, Integer.toString(value));
    }
    
    public static void put(String key, double value)
    {
        values.put(key, Double.toString(value));
    }
    
    public static void put(String key, float value)
    {
        values.put(key, Float.toString(value));
    }
}
