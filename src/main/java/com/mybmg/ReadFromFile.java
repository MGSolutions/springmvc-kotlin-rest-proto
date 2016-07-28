package com.mybmg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 */
public class ReadFromFile {
    public static void mainX(String[] args) throws Exception {

        InputStream is = new FileInputStream("/tmp/gggg/song-from-browser");
        GZIPInputStream gis = new GZIPInputStream(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "ISO-8859-1"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }

        System.err.println("READ: " + sb);
    }
}
