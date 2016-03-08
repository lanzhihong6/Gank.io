package com.lanzhihong.gankio.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.lanzhihong.gankio.R;
import com.lanzhihong.gankio.fragment.AndroidFragment;
import com.lanzhihong.gankio.fragment.ItemFragment;
import com.lanzhihong.gankio.fragment.MeizhiFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.iconics.typeface.IIcon;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Snackbar snackbar;
    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Nullable
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Nullable
    @Bind(R.id.container)
    FrameLayout container;
    Fragment currentFragment;

    private final IIcon[] iIcon = new IIcon[]{
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android,
            FontAwesome.Icon.faw_android
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate())); //  Android-Iconics

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("全部");
        switchFragment(new ItemFragment());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        setMenuImage();

    }

    //设置menu栏的icon
    private void setMenuImage() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem item = menu.getItem(i);
            setIconImage(item, iIcon[i]);
        }
    }

    //设置 Android-Iconics
    private void setIconImage(MenuItem item, IIcon icon) {
        item.setIcon(new IconicsDrawable(this)
                .icon(icon)
                .color(Color.BLACK)
                .iconOffsetXDp(5));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }


    public void onClick(View view) {
        snackbar = Snackbar.make(view, "测试一下 Snackbar ", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        String title = null;
        if (id == R.id.nav_all) {  //全部
            fragment = new ItemFragment();
            title = "全部";

        } else if (id == R.id.nav_welfare) {  //福利
            title = "福利";
            fragment = new MeizhiFragment();

        } else if (id == R.id.nav_android) {  //安卓
            title = "Android";
            fragment = new AndroidFragment();

        } else if (id == R.id.nav_ios) {    //ios
            title = "ios";

        } else if (id == R.id.nav_front) {  //前端
            title = "前端";

        } else if (id == R.id.nav_vedio) {   //休息视频
            title = "休息视频";

        } else if (id == R.id.nav_resource) {   //扩展
            title = "扩展资源";

        }

        switchFragment(fragment);
        toolbar.setTitle(title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {

        if (fragment != null && (currentFragment == null || !fragment.getClass().getName().equals(currentFragment.getClass().getName()))) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            currentFragment = fragment;
        }
    }

}
