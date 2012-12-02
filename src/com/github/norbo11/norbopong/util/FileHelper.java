package com.github.norbo11.norbopong.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHelper
{
    public static void setFile(File file, String contents)
    {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            
            bf.write(contents);
            bf.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<String> readFile(File file)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> contents = new ArrayList<>();
            String line = "";
            
            while ((line = br.readLine()) != null)
                contents.add(line);
            
            br.close();
            
            return contents;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
