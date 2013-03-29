package com.example.trackapp;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;



import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class MainActivity extends SherlockFragmentActivity {

	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);
		
		final ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(bar.newTab().setText("Fragment 1"), Fragment1.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Fragment 2"), Fragment2.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Fragment 3"), Fragment3.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Fragment 4"), Fragment4.class, null);
	}
}
