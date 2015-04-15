package edu.uic.cs478.Vedula;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import edu.uic.cs478.Vedula.TitleFragment.ListSelectionListener;

public class MainActivity extends Activity implements ListSelectionListener{
	/*Main activity class containing methods for Mainactivity lifecycle*/
	//Class fields which are used in main activity
	public static String[] mTitleArray;
	public Boolean animal=false;
	public Boolean car=false;
	public Boolean flower=false;
	public Boolean fromTable=false;
	public Boolean fromTableToast=false;
	public String statusString="idle";
	private FrameLayout mTitleFrameLayout, mImagesFrameLayout,mOneImageFrameLayout;
	private FragmentManager fragmentManager;
	
	//ArrayList of animalURLs to be used to fetch images that are to be displayed
	private ArrayList<String> animalURLs=new ArrayList<String>(Arrays.asList(
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/animal-snow-monkey-with-her-baby-high-resolution-wallpaper-download-snow-monkey-images-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/beautiful-great-dane-background-pictrure-hd-new-wide-wallpapers-for-desktop.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/02/amazing-wooly-mammoth-wide-hd-wallpaper-download-wooly-mammoth-images-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/02/amazing-cute-kitten-widescreen-high-definition-wallpaper.jpg",
			 "http://www.hdwallpaperscool.com/wp-content/uploads/2015/01/beautiful-snow-leopard-hd-wallpaper-download-snow-leopard-images-free.jpg",
			 "http://www.hdwallpaperscool.com/wp-content/uploads/2015/01/american-bighorn-sheep-desktop-background-hd-wallpaper-download-bighorn-sheep-images-free.jpg"));
	//ArrayList of carURLs to be used to fetch images that are to be displayed
	private ArrayList<String> carURLs=new ArrayList<String>(Arrays.asList(
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/light-green-porsche-cayman-car-high-resolution-wallpaper-download-porsche-cayman-images-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/amazing-lancia-full-high-quality-wallpaper-download-lancia-images-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/abstract-kia-racer-car-high-resolution-wallpaper-for-desktop-background-download-kia-images-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/beautiful-old-car-widescreen-high-definition-wallpaper-for-desktop-background-download-old-car-images-free.jpg",
			 "http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/acura-nsx-2015-red-colour-high-definition-wallpaper.jpg",
			 "http://www.hdwallpaperscool.com/wp-content/uploads/2015/02/2013-techart-porsche-turbo-wide-high-definition-wallpaper-for-desktop-background-download-techart-porsche-images-free.jpg"));
	//ArrayList of flowerURLs to be used to fetch images that are to be displayed
	private ArrayList<String> flowerURLs=new ArrayList<String>(Arrays.asList(
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/blue-flower-desktop-background-high-definition-wallpaper-download-blue-flower-images-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/amazing-orange-flower-high-definition-wallpaper.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/daffodil-high-resolution-wallpaper-download-daffodils-imges-free.jpg",
			"http://www.hdwallpaperscool.com/wp-content/uploads/2015/03/black-white-daisies-flower-catalog-grey-widescreen-high-definition-wallpaper-download-free.jpg",
			 "http://www.hdwallpaperscool.com/wp-content/uploads/2015/02/beautiful-lilac-flowers-widescreen-high-definition-wallpaper-for-desktop-background-download-lilac-images-free.jpg",
			 "http://www.hdwallpaperscool.com/wp-content/uploads/2015/02/amazing-white-hydrangea-flower-widescreen-high-definition-wallpaper.jpg"));

	private  ImageFragment mImageFragment;
	private  OneImageFragment mOneImageFragment=new OneImageFragment();
	private  TitleFragment mTitleFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Getting the titles of list
		mTitleArray=getResources().getStringArray(R.array.Titles);
		
		setContentView(R.layout.activity_main);
		//Obtaining the containers to frames
		mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_frame);
		mImagesFrameLayout = (FrameLayout) findViewById(R.id.table_frame);
		
		//Getting fragment manager
		fragmentManager = getFragmentManager();
		
		//Beginning a transaction
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		//Null check of bundle to link Mainactivity with previous state of fragments
		if (savedInstanceState != null) {
		
		//getting the fragments from bundle and putting them in fragment variables so that they need not be created newly
		mImageFragment = (ImageFragment) fragmentManager.getFragment(savedInstanceState, "tableImage");
		mTitleFragment =    (TitleFragment) fragmentManager.getFragment(savedInstanceState, "titleState");
		
		}
		
		//Null check of bundle
		if (savedInstanceState == null) {	
			
			//For the first time activity is starting we need to just create new fragments and adding it to container 
			     mTitleFragment=new TitleFragment();
				fragmentTransaction.add(R.id.title_frame, mTitleFragment);
				
				mImageFragment = new ImageFragment();
				fragmentTransaction.add(R.id.table_frame, mImageFragment);
				
			
			
		}
		//Committing the transaction
		fragmentTransaction.commit();
		
		//"If loops" to transfer toast message between orientation changes or activity recreation
		if (savedInstanceState != null) {
			String tempString=savedInstanceState.getString("ToastMessage");
		if (tempString.equalsIgnoreCase("showing selected picture")) {
			statusString="showing selected picture";
		}
		else if (tempString.equalsIgnoreCase("showing downloaded thumbnails")) {
			statusString="showing downloaded thumbnails";
		}
		
		else if (tempString.equalsIgnoreCase("downloading pictures")&&!savedInstanceState.getBoolean("fromTableToastkey")) {
			statusString="showing selected picture";
		}
		else if (tempString.equalsIgnoreCase("downloading pictures")&&savedInstanceState.getBoolean("fromTableToastkey")) {
			statusString="showing downloaded thumbnails";
		}
		else if (tempString.equalsIgnoreCase("I am still here and showing already downloaded thumbnails")) {
			statusString="I am still here and showing already downloaded thumbnails";
		}
		}
		
	}
	
	
	//Transferring toast message on back press 
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (statusString.equalsIgnoreCase("showing selected picture")) {
			statusString="I am still here and showing already downloaded thumbnails";
		}
		
	}
	
	//Method called when first image in called from table layout
	public void firstImageMethod(View v)
	{
		//Beginning a transaction to add one image fragment to container and storing in back stack 
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		//Adding fragment to container in frame layout
		fragmentTransaction.add(R.id.table_frame, mOneImageFragment);
		//Adding to back stack
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		//Executing the pending transactions
		fragmentManager.executePendingTransactions();
		//Flag setting to indicate the Asynch task which method to call
	    this.fromTable=false;
	    //Based upon  flag we call Asynch task for starting background thread by passing url ArrayList with one URL corresponding to category  
		if(this.animal==true ){
			//Setting URL into local variable
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(animalURLs.get(0)));
			//Calling Asynch task with single URL
			new AReadPageTask().execute(urlList) ;
		}
		else if (this.car==true) {
			//Setting URL into local variable
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(carURLs.get(0)));
			//Calling Asynch task with single URL
			new AReadPageTask().execute(urlList) ;
		}
		else if(this.flower==true) {
			//Setting URL into local variable
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(flowerURLs.get(0)));
			//Calling Asynch task with single URL
			new AReadPageTask().execute(urlList) ;

		}
	}
	
	//Method called when second image in called from table layout
	public void secondImageMethod(View v)
	{
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.table_frame, mOneImageFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();
	    this.fromTable=false;
		if(this.animal==true )
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(animalURLs.get(1)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if (this.car==true) {
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(carURLs.get(1)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if(this.flower==true)
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(flowerURLs.get(1)));
			
			new AReadPageTask().execute(urlList) ;

		}
	}
	//Method called when third image in called from table layout
	public void thirdImageMethod(View v)
	{
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.table_frame, mOneImageFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();
	    this.fromTable=false;
		if(this.animal==true )
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(animalURLs.get(2)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if (this.car==true) {
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(carURLs.get(2)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if(this.flower==true)
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(flowerURLs.get(2)));
			
			new AReadPageTask().execute(urlList) ;

		}
	}
	
	//Method called when fourth image in called from table layout
	public void fourthImageMethod(View v)
	{
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.table_frame, mOneImageFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();
	    this.fromTable=false;
		if(this.animal==true )
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(animalURLs.get(3)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if (this.car==true) {
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(carURLs.get(3)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if(this.flower==true)
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(flowerURLs.get(3)));
			
			new AReadPageTask().execute(urlList) ;

		}
	}
	
	//Method called when fifth image in called from table layout
	public void fifthImageMethod(View v)
	{
	
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.table_frame, mOneImageFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();
	    this.fromTable=false;
		if(this.animal==true )
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(animalURLs.get(4)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if (this.car==true) {
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(carURLs.get(4)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if(this.flower==true)
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(flowerURLs.get(4)));
			
			new AReadPageTask().execute(urlList) ;

		}
	}
	
	//Method called when sixth image in called from table layout
	public void sixthImageMethod(View v)
	{
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.table_frame, mOneImageFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();
	    this.fromTable=false;
		if(this.animal==true )
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(animalURLs.get(5)));
			
			new AReadPageTask().execute(urlList) ;
		}
		
		else if (this.car==true) {
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(carURLs.get(5)));
			
			new AReadPageTask().execute(urlList) ;
		}
		else if(this.flower==true)
		{
			ArrayList<String> urlList= new ArrayList<String>(Arrays.asList(flowerURLs.get(5)));
			
			new AReadPageTask().execute(urlList) ;

		}
	}
	
	//onSaveInstanceState method which is overridden  
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//If loop to check if fragment is currently added so that an already fragment transcends through activity recreation by adding it to bundle
		if(mOneImageFragment.isAdded())
		{
		//Adding to bundle fragment manager	
		fragmentManager.putFragment(outState,"oneImage",mOneImageFragment);
		}
		if(mImageFragment.isAdded())
		{
			fragmentManager.putFragment(outState,"tableImage",mImageFragment);
		}
		if(mTitleFragment.isAdded())
		{
		fragmentManager.putFragment(outState,"titleState",mTitleFragment);
		}
		
		//Storing statusString in bundle
		outState.putString("ToastMessage", statusString);
		//Storing flag in bundle to help transcend the toast message
		if (fromTableToast==true) {
			outState.putBoolean("fromTableToastkey", true);
		}
	}
	
	//Method to display toast message when button in list fragment is clicked 
	public void statusToastDisplay(View v)
	{
		//toast display method
		Toast.makeText(getApplicationContext(), statusString, Toast.LENGTH_SHORT).show();
	}
	
	//Based upon the index of list item clicked we set flags to be used in flag checks
	public void flagSet(int pos)
	{
		if (pos==0) {
			this.animal=true;
			this.car=false;
			this.flower=false;
		}
	
	else if (pos==1) {
		this.car=true;
		this.animal=false;
		this.flower=false;
	}
	else if (pos==2) {
		this.flower=true;
		this.animal=false;
		this.car=false;
	}
	}
	
	//Overridden method from ListFragment class to be used to display table of images
	@Override
	public void onListSelection(int index) {
		this.fromTable=true;
		//null check on image fragment
		if(mImageFragment != null)
		{
			//Check if fragment is already added or not
		if (!mImageFragment.isAdded()) {
			//Adding to fragment manager and back stack
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.table_frame, mImageFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();
		}}
		//After fragment is added to container we call asynch task for starting background thread
		if (index==0) {
			
			 new AReadPageTask().execute(animalURLs) ;	
		}
		else if (index==1) {
			
			new AReadPageTask().execute(carURLs);
		}
		else if (index==2) {
			
			new AReadPageTask().execute(flowerURLs);
		}
		 
	}

	//Asynch task inner class for background thread operations
	public class AReadPageTask extends AsyncTask<ArrayList<String>,Integer,ArrayList<Bitmap>> {	
   //onPreExecute method setting initial value of toast string
		protected void onPreExecute() {		
			statusString="idle";
		}
		//Logic of background thread is in this method
		@Override
		protected ArrayList<Bitmap> doInBackground(ArrayList<String>... strings) {	
			statusString="downloading pictures";
			URL aUrl = null ; 
			Bitmap result = null ;
			 ArrayList<Bitmap> bitmapList=new ArrayList<Bitmap>(6);
			 //Iterating through the URL ArrayList to get bitmaps and store in Bitmap ArrayList
			 for (int i = 0; i < strings[0].size(); i++) {
				 try {
					aUrl = new URL(strings[0].get(i)) ;
						InputStream s=(InputStream) aUrl.getContent();
						result = BitmapFactory.decodeStream(s) ;
						//Calling onProgressUpdate so that we can set toast message
						onProgressUpdate(10);
						bitmapList.add(result);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
			//Returning Bitmap ArrayList to onPostExecute
			return bitmapList;
		}

		// This method is executed in the UI thread to update the toast message in the display 
		@Override
	    protected void onProgressUpdate(Integer... values) {
			statusString="downloading pictures";
			if (fromTable==true) {
				fromTableToast=true;
			}
		}	
		
		// This method is executed in the UI thread after doInBackground() has returned
		protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
			//different fragment methods are called based fromTable flag and toast messages are set accordingly using if else loop
			if(fromTable==true)
			{
				statusString="showing downloaded thumbnails";	
			mImageFragment.addToUI(bitmaps);
			}
			else
			{
				statusString="showing selected picture";
				mOneImageFragment.addToUIOneImage(bitmaps);
			}
		}
	}
	
}
