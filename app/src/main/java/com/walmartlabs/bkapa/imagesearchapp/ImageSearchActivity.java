package com.walmartlabs.bkapa.imagesearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ImageSearchActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 27;
    GridView gvImages;
    ArrayList<ImageResult> images;
    ImageAdapter  imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        gvImages = (GridView) findViewById(R.id.gvResults);
        images = new ArrayList<ImageResult>();
        // initialize the adapter
        imageAdapter = new ImageAdapter(this, images);
        // attach the adapter to the ListView
        gvImages.setAdapter(imageAdapter);
        // Fetch the data remotely

        //CreateListner
        setupListViewListener();

    }

    public void setupListViewListener(){



        gvImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private long id;

            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                Intent intent = new Intent(ImageSearchActivity.this, ImageDisplayAcitivity.class);
                ImageResult   image = (ImageResult) adapter.getItemAtPosition(pos);
                intent.putExtra("image", image);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void fetchImages(String query) {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8";
        client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray imageResultJson = null;
                try {
                    imageResultJson= response.getJSONObject("responseData").getJSONArray("results");
                    images.clear();
                    images.addAll(ImageResult.fromJsonArray(imageResultJson));
                    imageAdapter.addAll(ImageResult.fromJsonArray(imageResultJson));
                //    imageAdapter.notifyDataSetChanged();
                } catch (JSONException exp) {
                    exp.printStackTrace();
                }



            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                              @Override
                                              public boolean onQueryTextSubmit(String query) {
                                                  fetchImages(query);
                                                  return true;
                                              }

                                              @Override
                                              public boolean onQueryTextChange(String newText) {
                                                  return false;
                                              }
                                          }
        );
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
