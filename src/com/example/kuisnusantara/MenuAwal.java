package com.example.kuisnusantara;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MenuAwal extends Activity {

	private Handler handler =  new Handler();
	private ProgressBar progressBar;
	private int load = 0;
	protected static final int TIMER_RUNTIME = 10000;
	protected boolean mbActive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_awal);
		
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		
		final Thread timerThread = new Thread() {
	          @Override
	          public void run() {
	              mbActive = true;
	              try {
	                  int waited = 0;
	                  while(mbActive && (waited < TIMER_RUNTIME)) {
	                      sleep(200);
	                      if(mbActive) {
	                          waited += 200;
	                          updateProgress(waited);
	                      }
	                  }
	          } catch(InterruptedException e) {
	              // do nothing
	          } finally {
	              onContinue();
	          }
	        }
	     };
	     timerThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_awal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}
	
	public int doWork(){
		load+=10;
		return load;
	}
	
	public void updateProgress(final int timePassed) {
	       if(null != progressBar) {
	           // Ignore rounding error here
	           final int progress = progressBar.getMax() * timePassed / TIMER_RUNTIME;
	           if(progress==progressBar.getMax()){
	        	   Intent i = new Intent(MenuAwal.this, MenuHome.class);
					startActivity(i);
					finish();
	           }
	           else
	        	   progressBar.setProgress(progress);
	       }
	   }
	
	public void onContinue() {
	     // perform any final actions here
	   }

}
