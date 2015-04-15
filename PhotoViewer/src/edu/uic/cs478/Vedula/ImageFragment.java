package edu.uic.cs478.Vedula;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {
	/* Image fragment class for bottom fragment containing list 
	  of images of each category selected*/
	
	//Private class fields of type ImageView - 6 for 6 images that are holding images
	private ImageView anImageView1 = null;
	private ImageView anImageView2 = null;
	private ImageView anImageView3 = null;
	private ImageView anImageView4 = null;
	private ImageView anImageView5 = null;
	private ImageView anImageView6 = null;
	
	
	//onSaveInstanceState method which holds the fragment state and enables the fragment to survive recreation of main activity (eg:- orientation change)
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
		if ((BitmapDrawable) anImageView1.getDrawable() !=null) {
			BitmapDrawable a=(BitmapDrawable) anImageView1.getDrawable();
			Bitmap bitmap1 = a.getBitmap();
			outState.putParcelable("one", bitmap1);
		}
		//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
		if ((BitmapDrawable) anImageView2.getDrawable() !=null) {
		BitmapDrawable b=(BitmapDrawable) anImageView2.getDrawable();
		Bitmap bitmap2 = b.getBitmap();
		outState.putParcelable("two", bitmap2);
		}
		//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
		if ((BitmapDrawable) anImageView3.getDrawable() !=null) {
		BitmapDrawable c=(BitmapDrawable) anImageView3.getDrawable();
		Bitmap bitmap3 = c.getBitmap();
		outState.putParcelable("three", bitmap3);
		}
		//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
		if ((BitmapDrawable) anImageView4.getDrawable() !=null) {
		BitmapDrawable d=(BitmapDrawable) anImageView4.getDrawable();
		Bitmap bitmap4 = d.getBitmap();
		outState.putParcelable("four", bitmap4);
		}
		
		//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
		if ((BitmapDrawable) anImageView5.getDrawable() !=null) {
		BitmapDrawable e=(BitmapDrawable) anImageView5.getDrawable();
		Bitmap bitmap5 = e.getBitmap();
		outState.putParcelable("five", bitmap5);
		}
		
		//if condition which checks if ImageView has bitmap and sets the bitmap to Bundle as Parcelable object with a key
		if ((BitmapDrawable) anImageView6.getDrawable() !=null) {
		BitmapDrawable f=(BitmapDrawable) anImageView6.getDrawable();
		Bitmap bitmap6 = f.getBitmap();
		outState.putParcelable("six", bitmap6);
		}
		
		//Setting the retain instance to true
	setRetainInstance(true);	
	}
	
	//onCreate method which calls super as well as sets retain instance
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Setting the retain instance to true so that it takes notice of fragment lifecycle to be retained
		setRetainInstance(true);
		
		
	}
	
	//Create view which is overridden to set view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		  //Instead of directly returning inflated layout view we are modifying the view based on the bundle
				View aview =inflater.inflate(R.layout.table_item, container, false);
				
				//null check for bundle
				if (savedInstanceState != null) {
					//null check for bundle parcelable object
					if (savedInstanceState.getParcelable("one") != null) {
						
					//getting the image views from inflated layout view and putting them in local image views
					anImageView1= (ImageView) aview.findViewById(R.id.imageView1);
					anImageView2= (ImageView) aview.findViewById(R.id.imageView2);
					anImageView3= (ImageView) aview.findViewById(R.id.imageView3);
					anImageView4= (ImageView) aview.findViewById(R.id.imageView4);
					anImageView5= (ImageView) aview.findViewById(R.id.imageView5);
					anImageView6= (ImageView) aview.findViewById(R.id.imageView6);
					
					//setting bitmaps into the image views which inturn are place in inflated layout view
					anImageView1.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("one"), 300, 300, false));
					anImageView2.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("two"), 300, 300, false));
					anImageView3.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("three"), 300, 300, false));
					anImageView4.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("four"), 300, 300, false));
					anImageView5.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("five"), 300, 300, false));
					anImageView6.setImageBitmap(Bitmap.createScaledBitmap((Bitmap)savedInstanceState.getParcelable("six"), 300, 300, false));
					}
					
				}
				//returning the inflated layout view after all its contents are set based upon bundle content
				return aview;
	}
	//During normal life cycle this is called after activity is ready and it gets the fragment ready with getting hold of ImageViews
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//getting the image views from Activity this fragment is currently associated with and putting them in local ImageViews
		anImageView1= (ImageView) getActivity().findViewById(R.id.imageView1);
		anImageView2= (ImageView) getActivity().findViewById(R.id.imageView2);
		anImageView3= (ImageView) getActivity().findViewById(R.id.imageView3);
		anImageView4= (ImageView) getActivity().findViewById(R.id.imageView4);
		anImageView5= (ImageView) getActivity().findViewById(R.id.imageView5);
		anImageView6= (ImageView) getActivity().findViewById(R.id.imageView6);
		
	}
	//User defined method which gets ArrayList of bitmaps from Asynchtask class onPostExecute method when a list item is selected from top fragment
	public void addToUI(ArrayList<Bitmap> aBitmap)
	{  
		//Sets each element from bitmaps ArrayList into ImageViews that were captured earlier
		anImageView1.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(0), 300, 300, false));
		anImageView2.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(1), 300, 300, false));
		anImageView3.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(2), 300, 300, false));
		anImageView4.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(3), 300, 300, false));
		anImageView5.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(4), 300, 300, false));
		anImageView6.setImageBitmap(Bitmap.createScaledBitmap(aBitmap.get(5), 300, 300, false));
	}
	// onDestroy method just calling super class onDestroy - not overridden
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		
	}
	// onDetach method just calling super class onDetach - not overridden
	@Override
	public void onDetach() {
		
		super.onDetach();
		
	}
	// onAttach method just calling super class onAttach - not overridden
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		
	}
	// onDestroyView method just calling super class onDestroyView - not overridden
	@Override
	public void onDestroyView() {
		
		super.onDestroyView();
		
	}
	// onStop method just calling super class onStop - not overridden
	@Override
	public void onStop() {
		
		super.onStop();
		
	}
	// onStart method just calling super class onStart - not overridden
	@Override
	public void onStart() {
		
		super.onStart();
		
	}
	// onResume method just calling super class onResume - not overridden
	@Override
	public void onResume() {
		
		super.onResume();
		
	}
	// onPause method just calling super class onPause - not overridden
	@Override
	public void onPause() {
		
		super.onPause();
		
	}

	
}
