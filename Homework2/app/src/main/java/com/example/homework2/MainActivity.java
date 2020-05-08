package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private AppBarLayout appbarlayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout=(TabLayout)findViewById(R.id.tabLayout);
        appbarlayout=(AppBarLayout)findViewById(R.id.appBar);
        viewpager=(ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        //Adding Fragments
        adapter.AddFragment(new Fragment_Food(),"Food List");
        adapter.AddFragment(new Fragment_Announcement(),"Announcement");
        adapter.AddFragment(new Fragment_News(),"News");
        //Adapter Setup
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);


        /*
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.activity_main,menu,"Menu");
        transaction.commit();
        Fragment_Food food=new Fragment_Food();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.activity_main,food,"Food");
        transaction.commit();*/

    }
}
