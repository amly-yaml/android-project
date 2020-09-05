package com.example.nangshwekyi.celeappdemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private GridView gridView;
    private ArrayAdapter<String> names;
    private ImageView imageView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) ViewfindViewById(R.id.gridView);
        imageView = (ImageView) findViewById (R.id.imageView);
        Random rand = new Random();

        int rndInt = rand.nextInt(4) + 1; // n = the number of images, that start at idx 1
        String imageName = "image" + rndInt; // random image display

        int imageID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(imageID);

    }
}
