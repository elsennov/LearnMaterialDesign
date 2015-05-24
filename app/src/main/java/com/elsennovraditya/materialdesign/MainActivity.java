package com.elsennovraditya.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity
        implements MenuAdapter.ViewHolder.OnMenuClickListener {

    private final String TAG = MainActivity.this.getClass().getSimpleName();

    @InjectView(R.id.navigation_drawer_layout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.content_frame)
    FrameLayout mContentFrame;

    @InjectView(R.id.left_drawer)
    RecyclerView mLeftDrawer;

    private com.elsennovraditya.materialdesign.MenuItem[] mMenuItems;

    private MenuHeader mMenuHeader;

    private LinearLayoutManager mLayoutManager;

    private ActionBarDrawerToggle mDrawerToggle;

    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initMenu();
        initView();
        initFirstFragment();
    }

    private void initMenu() {
        mMenuItems = new com.elsennovraditya.materialdesign.MenuItem[2];
        mMenuItems[Constant.FIRST] = new com.elsennovraditya.materialdesign.MenuItem();
        mMenuItems[Constant.FIRST].setTitle(getString(R.string.home));
        mMenuItems[Constant.FIRST].setIcon(R.drawable.ic_home);

        mMenuItems[Constant.SECOND] = new com.elsennovraditya.materialdesign.MenuItem();
        mMenuItems[Constant.SECOND].setTitle(getString(R.string.profile));
        mMenuItems[Constant.SECOND].setIcon(R.drawable.ic_profile);

        mMenuHeader = new MenuHeader();
        mMenuHeader.setEmail(getString(R.string.email));
        mMenuHeader.setName(getString(R.string.name));
        mMenuHeader.setIcon(R.drawable.head);
    }

    private void initView() {
        mLeftDrawer.setHasFixedSize(true);

        mMenuAdapter = new MenuAdapter(this, mMenuHeader, mMenuItems, this);
        mLeftDrawer.setAdapter(mMenuAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mLeftDrawer.setLayoutManager(mLayoutManager);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.openDrawer,
                R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }


        };
        // Drawer Toggle Object Made
        mDrawerLayout.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
    }

    private void initFirstFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new MainFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the mMenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onHeaderIconClicked() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onHeaderIconLongClicked(View headerIcon) {
        Intent intent = new Intent(this, ImageDetailActivity.class);
        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="robot"
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, headerIcon,
                        getString(R.string.image));
        // start the new activity
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onMenuClicked(int position) {
        Toast.makeText(this, "Position " + position + " is clicked!", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void init(Bundle bundle) {
//        addFragmentSection("Material Design", new MainFragment());
//        addActivitySection("Profile", ProfileActivity.class);
//    }
//
//    private MaterialSection addFragmentSection(String title, Fragment fragment) {
//        MaterialSection materialSection = newSection(title, fragment);
//        addSection(materialSection);
//        return materialSection;
//    }
//
//    private MaterialSection addActivitySection(String title, Class activityClass) {
//        Intent intent = new Intent(this, activityClass);
//        MaterialSection materialSection = newSection(title, intent);
//        addSection(materialSection);
//        return materialSection;
//    }

}
