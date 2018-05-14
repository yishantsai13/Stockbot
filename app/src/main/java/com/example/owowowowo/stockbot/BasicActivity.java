//package com.example.owowowowo.stockbot;
//
//import android.app.FragmentManager;
//import android.content.res.Configuration;
//import android.support.design.widget.NavigationView;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.MenuItem;
//import android.widget.Toolbar;
//
//
//public class BasicActivity extends AppCompatActivity {
//
//    private static final String TAG = "BaseActivity";
//
//    private Toolbar mToolbar;
//
//    /**
//     * ActionBarDrawerToggle  是 DrawerLayout.DrawerListener 实现。和 NavigationDrawer 搭配使用，
//     * 推荐用这个方法，符合 Material Design规范。
//     */
//    private ActionBarDrawerToggle mDrawerToggle;
//
//    private DrawerLayout mDrawerLayout;
//
//    private boolean mToolbarInitialized;
//
//    private int mItemToOpenWhenDrawerCloses = -1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        Log.d(TAG, "Activity onCreate");
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!mToolbarInitialized) {
//            throw new IllegalStateException("You must run super.initializeToolbar at " +
//                    "the end of your onCreate method");
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Whenever the fragment back stack changes, we may need to update the
//        // action bar toggle: only top level screens show the hamburger-like icon, inner
//        // screens - either Activities or fragments - show the "Up" icon instead.
//        getFragmentManager().addOnBackStackChangedListener(backStackChangedListener);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        getFragmentManager().removeOnBackStackChangedListener(backStackChangedListener);
//    }
//
//    /**
//     * onPostCreate() + onConfigurationChanged() 作用
//     * 1.改变android.R.id.home返回图标
//     * 2.Drawer拉出、隐藏，带有android.R.id.home动画效果。
//     * 3.监听Drawer拉出、隐藏。
//     */
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        if (mDrawerToggle != null) {
//            mDrawerToggle.syncState();
//        }
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (mDrawerToggle != null) {
//            mDrawerToggle.onConfigurationChanged(newConfig);
//        }
//    }
//
//    /**
//     * 加上这句使左上角 Menu 键好用
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        // If not handled by drawerToggle, home needs to be handled by returning to previous
//        if (item != null && item.getItemId() == android.R.id.home) {
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onBackPressed() {
//        // If the drawer is open, back will close it
//        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START)) {
//            mDrawerLayout.closeDrawers();
//            return;
//        }
//        // Otherwise, it may return to the previous fragment stack
//        FragmentManager fragmentManager = getFragmentManager();
//        if (fragmentManager.getBackStackEntryCount() > 0) {
//            fragmentManager.popBackStack();
//        } else {
//            // Lastly, it will rely on the system behavior for back
//            super.onBackPressed();
//        }
//    }
//
//    private final FragmentManager.OnBackStackChangedListener backStackChangedListener =
//            new FragmentManager.OnBackStackChangedListener() {
//                @Override
//                public void onBackStackChanged() {
//                    updateDrawerToggle();
//                }
//            };
//
//    protected void updateDrawerToggle() {
//        if (mDrawerToggle == null) {
//            return;
//        }
//        boolean isRoot = getFragmentManager().getBackStackEntryCount() == 0;
//        mDrawerToggle.setDrawerIndicatorEnabled(isRoot);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayShowHomeEnabled(!isRoot);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(!isRoot);
//            getSupportActionBar().setHomeButtonEnabled(!isRoot);
//        }
//        if (isRoot) {
//            mDrawerToggle.syncState();
//        }
//    }
//
//    /**
//     *对外暴露的方法，自定义Toolbar 结合 ActionBarDrawerToggle ，来封装抽屉式。
//     */
//    protected void initializeToolbar() {
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (mToolbar == null) {
//            throw new IllegalStateException("Layout is required to include a Toolbar with id " +
//                    "'toolbar'");
//        }
//
////        mToolbar.inflateMenu(R.menu.main);
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (mDrawerLayout != null) {
//            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//            if (navigationView == null) {
//                throw new IllegalStateException("Layout requires a NavigationView " +
//                        "with id 'nav_view'");
//            }
//
//            // Create an ActionBarDrawerToggle that will handle opening/closing of the drawer:
//            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                    mToolbar, R.string.open_content_drawer, R.string.close_content_drawer);
//            mDrawerLayout.setDrawerListener(drawerListener);
//            populateDrawerItems(navigationView);
//            setSupportActionBar(mT);
//            updateDrawerToggle();
//        } else {
//            setSupportActionBar(mToolbar);
//        }
//
//        mToolbarInitialized = true;
//    }
//
//    private final DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
//        @Override
//        public void onDrawerSlide(View drawerView, float slideOffset) {
//            if (mDrawerToggle != null) mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
//        }
//
//        @Override
//        public void onDrawerOpened(View drawerView) {
//            if (mDrawerToggle != null) mDrawerToggle.onDrawerOpened(drawerView);
//            if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);
//        }
//
//        @Override
//        public void onDrawerClosed(View drawerView) {
//            if (mDrawerToggle != null) mDrawerToggle.onDrawerClosed(drawerView);
//            if (mItemToOpenWhenDrawerCloses >= 0) {
//                Bundle extras = ActivityOptions.makeCustomAnimation(
//                        BaseActivity.this, R.anim.fade_in, R.anim.fade_out).toBundle();
//                Class activityClass = null;
//                switch (mItemToOpenWhenDrawerCloses) {
//                    case R.id.navigation_first:
//                        activityClass = FirstActivity.class;
//                        break;
//                    case R.id.navigation_second:
//                        activityClass = SecondActivity.class;
//                        break;
//                    case R.id.navigation_third:
//                        activityClass = ThirdActivity.class;
//                        break;
//                }
//                if (activityClass != null) {
//                    startActivity(new Intent(BaseActivity.this, activityClass), extras);
//                    finish();
//                }
//            }
//        }
//
//        @Override
//        public void onDrawerStateChanged(int newState) {
//            if (mDrawerToggle != null) mDrawerToggle.onDrawerStateChanged(newState);
//        }
//    };
//
//    private void populateDrawerItems(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        menuItem.setChecked(true);
//                        mItemToOpenWhenDrawerCloses = menuItem.getItemId();
//                        mDrawerLayout.closeDrawers();
//                        return true;
//                    }
//                });
////        if (FirstActivity.class.isAssignableFrom(getClass())) {
////            navigationView.setCheckedItem(R.id.navigation_first);
////        } else if (SecondActivity.class.isAssignableFrom(getClass())) {
////            navigationView.setCheckedItem(R.id.navigation_second);
////        } else if (ThirdActivity.class.isAssignableFrom(getClass())) {
////            navigationView.setCheckedItem(R.id.navigation_third);
////        }
//    }
//
//
//}
