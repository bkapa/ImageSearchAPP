package com.walmartlabs.bkapa.imagesearchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ImageDisplayAcitivity extends AppCompatActivity {

    private TextView imageTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display_acitivity);
        this.getSupportActionBar().hide();
        ImageResult image = (ImageResult) getIntent().getSerializableExtra("image");
        ImageView imageView = (ImageView) findViewById(R.id.ivImageResult);
        imageTitle = (TextView)findViewById(R.id.tvText);
        imageTitle.setText(image.getTitle());
        Picasso.with(this).load(image.getFullUrl()).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_display_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
