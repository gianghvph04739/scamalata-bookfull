package com.skyreds.truyenfull.view.main;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.a21buttons.bottomnavigationview.widget.BottomNavigationView;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.ViewPagerAdapter;
import com.skyreds.truyenfull.base.BaseActivity;
import com.skyreds.truyenfull.view.fragment.category.CategoryFragment;
import com.skyreds.truyenfull.view.fragment.feature.FeatureFragment;
import com.skyreds.truyenfull.view.fragment.profile.ProfileFragment;
import com.skyreds.truyenfull.view.fragment.trending.TrendingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    MenuItem prevMenuItem;

    private CategoryFragment categoryFragment;
    private FeatureFragment featureFragment;
    private ProfileFragment profileFragment;
    private TrendingFragment trendingFragment;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(unbinder);
        ButterKnife.bind(this);

        setUp();
    }

    @Override
    protected void setUp() {
        setupViewPager(viewPager);
        setupNavigationClick();
    }

    private void setupNavigationClick() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feature:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.nav_trending:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.nav_category:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.nav_profile:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        featureFragment = new FeatureFragment();
        trendingFragment = new TrendingFragment();
        categoryFragment = new CategoryFragment();
        profileFragment = new ProfileFragment();
        adapter.addFragment(featureFragment);
        adapter.addFragment(trendingFragment);
        adapter.addFragment(categoryFragment);
        adapter.addFragment(profileFragment);

        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
