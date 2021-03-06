package com.example.trackapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;


public class ItemsFragment extends SherlockListFragment {
	
	boolean isDualPane;
	int mCurCheckPosition = 0;
	private final String TAG = "trackApp:";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Checking the build version for the sake of UI
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			//list with static array of items
			setListAdapter(new ArrayAdapter<String>(getActivity(), 
					android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.companies_array)));
		} else {
			//list with static array of items
			setListAdapter(new ArrayAdapter<String>(getActivity(), 
					android.R.layout.simple_list_item_activated_1, getResources().getStringArray(R.array.companies_array)));
		}
		
		
		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI
		
		
		//View detailsFrame = getActivity().findViewById(R.id.details);
		//Toast.makeText(getActivity(), detailsFrame.toString(), Toast.LENGTH_SHORT).show();
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			//Log.v(TAG, "NO SOY NULL");
			isDualPane = true;
		} else {
			//Log.v(TAG, "SOY NULL");
			isDualPane = false;
		}
		//isDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
		
		//isDualPane = detailsFrame != null && detailsFrame.isVisible();
		
		if(savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice",0);
		}
		
		if(isDualPane) {
			// In dual-pane mode, the list view highlights the selected item.
	        //Log.v(TAG, "Dual Panel");
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(mCurCheckPosition);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
	}
	
	/**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void showDetails(int index) {
        mCurCheckPosition = index;
 
        if (isDualPane) {
            
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            getListView().setItemChecked(index, true);
 
            // Check what fragment is currently shown, replace if needed.
            DetailsFragment details = (DetailsFragment)
                    getActivity().getSupportFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = DetailsFragment.newInstance(index);
 
                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
 
        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}