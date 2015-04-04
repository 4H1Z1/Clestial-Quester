package me.johngreen.clestia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Created by johngreen on 30/03/2015.
 */
public class Math {
    private int secconds;
    private int minnuts;
    private int hours;

    private String time;

    private Long startMili;

    public Math() {
        this.startMili = System.currentTimeMillis();
        this.secconds = 0;
        this.minnuts = 0;
        this.hours = 0;
        this.time = "0:0:0";
    }

    public String getTime() {
        if (System.currentTimeMillis() - startMili > 1000) {
            String temp = hours+":";
            this.secconds++;
            if (this.secconds > 59) {
                this.minnuts++;
                this.secconds = 0;
            }
            if (this.minnuts > 59) {
                this.hours++;
                this.minnuts = 0;
            }
            if(this.minnuts<10){
                temp+="0"+this.minnuts+":";
            }else {
                temp += this.minnuts + ":";
            }
            if(this.secconds<10){
                temp+="0"+this.secconds;
            }else{
                temp+=this.secconds;
            }
            this.time = temp;
            this.startMili = System.currentTimeMillis();
        }
        return this.time;
    }

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private int getPrice(int id) throws IOException {
        URL url = new URL("http://open.tip.it/json/ge_single_item?item=" + id);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String line = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            line += inputLine;
        }
        in.close();
        line = line.substring(line.indexOf("mark_price\":\"")
                + "mark_price\":\"".length());
        line = line.substring(0, line.indexOf("\""));
        return Integer.parseInt(line.replaceAll(",", ""));
    }

}