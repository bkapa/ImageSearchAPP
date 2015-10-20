package com.walmartlabs.bkapa.imagesearchapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bkapa on 10/19/15.
 */
public class ImageResult implements Serializable {
    public String fullUrl;
    public String thumbUrl;
    public String title;

    public ImageResult(JSONObject json){
        try{
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");

        }catch(JSONException e){
            e.printStackTrace();
        }

    }
    public static ArrayList<ImageResult> fromJsonArray(JSONArray jsonArray){

        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for(int i=0;i<jsonArray.length();i++){
           try{
               results.add(new ImageResult(jsonArray.getJSONObject(i)));
           }catch (JSONException e){
               e.printStackTrace();
           }
        }
        return results;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
