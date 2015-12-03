package com.VocaloidRadio;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity
        implements RecentSongsFragment.OnFragmentInteractionListener,
        RadioStreamFragment.OnFragmentInteractionListener,
        // Webviews Related Fragments
        DevelopmentBlogFragment.OnFragmentInteractionListener,
        MainWebsiteFragment.OnFragmentInteractionListener,
        FeaturedPagesFragment.OnFragmentInteractionListener,
        RSSFeedFragment.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // Initial setup of the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load Navigation Drawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the Drawer
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // Update the main content by replacing fragments

        FragmentManager fragmentManager = getSupportFragmentManager();
        onSectionAttached(position + 1);
        switch (position) {

            // RadioStreamFragment
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RadioStreamFragment.newInstance("", ""))
                        .commit();
                break;

            // RecentSongsFragment
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RecentSongsFragment.newInstance("", ""))
                        .commit();
                break;

            // FeaturedPagesFragment
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, FeaturedPagesFragment.newInstance("", ""))
                        .commit();
                break;

            // MainWebsiteFragment
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MainWebsiteFragment.newInstance("", ""))
                        .commit();
                break;

            // DevelopmentBlogFragment
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, DevelopmentBlogFragment.newInstance("", ""))
                        .commit();
                break;

            // RSSFragment
            case 5:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RSSFeedFragment.newInstance("", ""))
                        .commit();
                break;

            default:
                break;
        }
    }

    // Method to change between fragments
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // Empty constructor
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Context context) {  // Called when a fragment is first attached to its context
            super.onAttach(context);
            ((MainActivity) context).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
