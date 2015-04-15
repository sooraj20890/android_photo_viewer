package edu.uic.cs478.Vedula;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class OneImageFragment extends Fragment {
	/*Fragment class which holds methods of fragment life cycle*/
	
	//Private Class field
	private ImageView anImageView99 = null;
	
	
	//onCreate overridden method with sets retain Instance set to true 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	//Create view which is overridden to set view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
      //obtaining view that is inflated
		View theView = inflater.inflate(R.layout.image, container, false);
		//Bundle null check
		if (savedInstanceState != null) {
			//Setting image bitmap into the view so that it can be displayed on screen
		if (savedInstanceState.getParcelable("nintynine") !=null) {
			anImageView99= (ImageView) theView.findViewById(R.id.imageView99);
			anImageView99.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("nintynine"), 300, 300, false));
		}
		}
		
		//Returning view after making applicable necessary changes
		return theView;
	}
	//During normal life cycle this is called after activity is ready and it gets the fragment ready with getting hold of ImageViews
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//getting the image views from Activity this fragment is currently associated with and putting them in local ImageViews
		anImageView99= (ImageView) getActivity().findViewById(R.id.imageView99);
		
		
	}
	
	//User defined method which gets ArrayList of bitmaps from Asynchtask class onPostExecute method when a image from bottom gird is selected from top fragment
	public void addToUIOneImage(ArrayList<Bitmap> aBitmap)
	{  
		//Sets each element from bitmaps ArrayList into ImageViews that were captured earlier
		anImageView99.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(0), 1000, 1000, false));
		
	}
	//onSaveInstanceState method which holds the fragment state and enables the fragment to survive recreation of main activity (eg:- orientation change)
   @Override
public void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
	if ((BitmapDrawable) anImageView99.getDrawable() !=null) {
		BitmapDrawable a=(BitmapDrawable) anImageView99.getDrawable();
		Bitmap bitmap99 = a.getBitmap();
		outState.putParcelable("nintynine", bitmap99);
	}
	//Setting the retain instance to true
	setRetainInstance(true);
}
}
