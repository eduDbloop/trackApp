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
		mTabsAdapter.addTab(bar.newTab().setText("Manual"), Fragment1.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Alerta"), Fragment2.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Datos"), FragmentLayout.class, null);
	}
}
