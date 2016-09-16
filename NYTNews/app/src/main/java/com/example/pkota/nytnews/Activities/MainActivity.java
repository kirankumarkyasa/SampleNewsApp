package com.example.pkota.nytnews.Activities;

/**
 * Created by pkota on 13-09-2016.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pkota.nytnews.R;
import com.example.pkota.nytnews.retrofit.NewsAPI;
import com.example.pkota.nytnews.retrofit.NewsApiInterface;
import com.example.pkota.nytnews.utils.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    private static final String TAG = "MainActivity";
   // MemoryCache memoryCache;

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.recycler_fragment);
        drawerFragment.setUp(R.id.recycler_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        verifyStoragePermissions(this);
        // display the first navigation drawer view on app launch

        displayView(0);

    }


    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    public void setRecycler(Call<News> call) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setItemViewCacheSize(0);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                List<News> news = response.body().getResults();
                recyclerView.setAdapter(new CustomList(news,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "Number of news received: " + t.toString());
            }
        });

        recyclerView.addOnItemTouchListener(
                new CustomList(getApplicationContext(), new CustomList.OnItemClickListener() {
                    @Override public void onItemClick(View view,List<News> news ,int position) {
                        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                        intent.putExtra("url", news.get(position).getUrl());
                        startActivity(intent);
                    }

                })
        );
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
     //   memoryCache = new MemoryCache();
        NewsApiInterface apiService =
                NewsAPI.getClient().create(NewsApiInterface.class);
        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
              //  memoryCache.clear();
                setRecycler(apiService.getAllNews());
                title = getString(R.string.title_home);
                break;
            case 1:
             //   memoryCache.clear();
                setRecycler(apiService.getSportsNews());
                title = getString(R.string.title_sports);
                break;
            case 2:
              //  memoryCache.clear();
                setRecycler(apiService.getHealthNews());
                title = getString(R.string.title_health);
                break;
            case 3:
             //   memoryCache.clear();
                setRecycler(apiService.getBusinessNews());
                title = getString(R.string.title_business);
                break;
            case 4:
               // memoryCache.clear();
                setRecycler(apiService.getArtNews());
                title = getString(R.string.title_art);
                break;
            default:
                break;
        }
        // set the toolbar title
        getSupportActionBar().setTitle(title);

    }
}
