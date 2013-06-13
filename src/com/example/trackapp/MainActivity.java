package com.example.trackapp;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends SherlockFragmentActivity {

	
	SharedPreferences mPrefs;
	final String welcomeScreenShownPref = "welcomeScreenShown";
	
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//ImageButton im = (ImageButton) findViewById(R.id.sosButton);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);
		
		/*im.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this,PanicWindow.class);
				MainActivity.this.startActivity(myIntent);
			}
		});*/
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		// Second argument is the default to use if the preference can't be found
		Boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);
		
		if (!welcomeScreenShown) {
			
			String whatsNewTitle = getResources().getString(R.string.whatsNewTitle);
		        String whatsNewText = getResources().getString(R.string.whatsNewText);
		        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(whatsNewTitle).setMessage(whatsNewText).setPositiveButton(
		                R.string.ok, new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                        dialog.dismiss();
		                    }
		                }).show();
		        SharedPreferences.Editor editor = mPrefs.edit();
		        editor.putBoolean(welcomeScreenShownPref, true);
		        editor.commit(); // Very important to save the preference
		} 
			 
		final ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(bar.newTab().setText("Manual"), Fragment1.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Alerta"), Fragment2.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("Datos"), FragmentLayout.class, null);
	    
	
	
	}
	
}
