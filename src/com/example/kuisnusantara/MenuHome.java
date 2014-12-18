package com.example.kuisnusantara;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class MenuHome extends Activity{
	
	public static boolean shouldPlay = false;
	private ImageButton imageSound;
	private ImageButton keluar;
	public static boolean sound = true;
	//private MusicServices mServ;
	public static MediaPlayer mPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		imageSound = (ImageButton) findViewById(R.id.imageButton5);
		keluar = (ImageButton)findViewById(R.id.imageButton4);
		//imageSound.setOnClickListener(imgHandler);
		setContentView(R.layout.activity_menu_home);
		mPlayer = MediaPlayer.create(this, R.raw.indonesia_raya);
		mPlayer.setVolume(1, 1);
		mPlayer.setLooping(true);
		mPlayer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	    if (!shouldPlay) { // it won't pause music if shouldPlay is true
	        mPlayer.pause();
	        mPlayer = null;
	    }
	}
	
	public void play(View v){
		Intent i = new Intent(MenuHome.this, MainActivity.class);
		i.putExtra("index", 0);
		shouldPlay = true;
		startActivity(i);
	}
	
	public void about(View v){
		Intent i = new Intent(MenuHome.this, MenuAbout.class);
		shouldPlay = true;
		startActivity(i);
	}
	
	public void help(View v){
		Intent i = new Intent(MenuHome.this, MenuHelp.class);
		shouldPlay = true;
		startActivity(i);
	}
	
	public void quit(View v){
		if(getIntent().getBooleanExtra("EXIT", false)){
			finish();
		}
		System.exit(0);
	}
	
	public void sound(View v){
		if(sound){
			ImageButton aButton = (ImageButton)v;
			aButton.setBackgroundResource(R.drawable.mute);
			mPlayer.setVolume(0, 0);
			sound = false;
		}
		else {
			//imageSound.setBackgroundResource(R.drawable.sound);
			ImageButton bButton = (ImageButton)v;
			bButton.setBackgroundResource(R.drawable.sound);
			mPlayer.setVolume(1, 1);
			sound = true;
		}
	}
	
	private OnClickListener imgHandler = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			imageSound.setBackgroundResource(R.drawable.mute);
		}
	};
	

}
