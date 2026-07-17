package com.example.aistudymentor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aistudymentor.R;
import com.example.aistudymentor.adapters.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    Menu menu;
    MenuItem itemLogout;
    SharedPreferences sharedPrf;
    Intent dataIntent;
    Bundle dataBundle;
    private String userAccount = "";
    private int userId = 0;

    @Override
    protected void onStart() {
        super.onStart();
        if (userId <= 0 || TextUtils.isEmpty(userAccount)){
            // chua su dung chuc nang dang nhap
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        activeMenu(dataIntent, dataBundle);
    }
    private void activeMenu(Intent intent, Bundle bundle){
        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            String menuTab = bundle.getString("MENU_TAB", "").toLowerCase();
            if (menuTab.equals("category")){
                viewPager.setCurrentItem(1);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        navigationView = findViewById(R.id.navigationView);
        menu = navigationView.getMenu();
        itemLogout = menu.findItem(R.id.logout_menu);
        dataIntent = getIntent();
        dataBundle = dataIntent.getExtras();
        sharedPrf = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        if (sharedPrf != null) {
            userAccount = sharedPrf.getString("USERNAME_USER", "");
            userId = sharedPrf.getInt("ID_USER", 0);
        }
        // xu ly de dong/mo drawer navigation
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // click drawer navigation
        navigationView.setNavigationItemSelectedListener(this);
        // view pager
        setupViewPager();
        // click bottom navigation
        clickTabBottomNavigation();
        // logout
        logoutApp();
        // hien thi thong tin nguoi dang nhap
        MenuItem itemUser = menu.findItem(R.id.account_menu);
        if (userAccount != null) {
            itemUser.setTitle(userAccount);
        }
    }
    private void logoutApp(){
        itemLogout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                // xoa du lieu da luu trong sharePreference
                SharedPreferences.Editor editor = sharedPrf.edit();
                editor.clear();
                editor.apply();

                drawerLayout.closeDrawer(GravityCompat.START);
                Intent login = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
                return false;
            }
        });
    }
    private void clickTabBottomNavigation(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home_menu) {
                    viewPager.setCurrentItem(0); // Home - position : 0
                } else if (menuItem.getItemId() == R.id.category_menu) {
                    viewPager.setCurrentItem(1); // Category
                } else if (menuItem.getItemId() == R.id.quiz_menu) {
                    viewPager.setCurrentItem(2); // Quiz
                } else if (menuItem.getItemId() == R.id.settings_menu) {
                    viewPager.setCurrentItem(3); // Settings
                }
                return true;
            }
        });
    }
    private void setupViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0){
                    bottomNavigationView.getMenu().findItem(R.id.home_menu).setChecked(true);
                } else if (position == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.category_menu).setChecked(true);
                } else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.quiz_menu).setChecked(true);
                } else if (position == 3) {
                    bottomNavigationView.getMenu().findItem(R.id.settings_menu).setChecked(true);
                } else {
                    bottomNavigationView.getMenu().findItem(R.id.home_menu).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.home_menu) {
            viewPager.setCurrentItem(0); // Home - position : 0
        } else if (menuItem.getItemId() == R.id.category_menu) {
            viewPager.setCurrentItem(1); // Category
        } else if (menuItem.getItemId() == R.id.quiz_menu) {
            viewPager.setCurrentItem(2); // Quiz
        } else if (menuItem.getItemId() == R.id.settings_menu) {
            viewPager.setCurrentItem(3); // Settings
        }
        drawerLayout.closeDrawer(GravityCompat.START); // close menu
        return true;
    }
}
