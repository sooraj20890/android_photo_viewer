package edu.uic.cs478.Vedula;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitleFragment extends ListFragment {
	/*Fragment class which holds methods of fragment life cycle*/
	
	//Class fields
	private ListSelectionListener mListener = null;
	private static final String TAG = "TitleFragment";
	int mCurCheckPosition = 0;
	
	//Inner interface which deals with list selection operations with two methods
	public interface ListSelectionListener {
		public void onListSelection(int index);
		public void flagSet(int index);
	}
	
	//While attaching fragment to activity this method is called
	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);

		try {
		
			// Set the ListSelectionListener for communicating with the MainActivity
			mListener = (ListSelectionListener) activity;

		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnArticleSelectedListener");
		}
	}
	
	//onCreate method which calls super as well as sets retain instance
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	//Create view which is overridden to set view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//Bundle null check
		if (savedInstanceState != null) {
			//setting flags in Main activity based on list position on recreation
			mListener.flagSet(savedInstanceState.getInt("listPosition"));
		}
		
		//returning inflated view
		return inflater.inflate(R.layout.fulllayout,
				container, false);
		
	}
	
	//list item click method thats called when list item is clicked
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
	
		// Indicates the selected item has been checked
		getListView().setItemChecked(pos, true);
		
		//Flag setting and listener method 
		flagSettingCall(pos);
		callingOnListSelection(pos);
		//Setting position clicked in class field
		mCurCheckPosition=pos;
	}
	//During normal life cycle this is called after activity is ready and it gets the fragment ready with sets choice mode
	@Override
	public void onActivityCreated(Bundle savedState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedState);

		// Set the list choice mode to allow only one selection at a time
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		
		//setting name arrays in layout using list adapter
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.titles_item, MainActivity.mTitleArray));
		
	
		
	}
	
	//local method which calls flagSet methods in activity
	public void flagSettingCall(int aInt)
	{
		mListener.flagSet(aInt);
	}
	
	//local method which calls OnListSelection methods in activity
	public void callingOnListSelection(int anInt )
	{
		mListener.onListSelection(anInt);
	}
	
	/*onSaveInstanceState method which holds the fragment state and enables the fragment to survive recreation of main activity (eg:- orientation change) 
	and putting selected list position */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("listPosition", mCurCheckPosition);
	}
	

}
