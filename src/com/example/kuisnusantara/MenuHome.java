package com.example.kuisnusantara;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MenuHome extends Activity {
	
	ImageButton imageSound;
	boolean sound = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageSound = (ImageButton) findViewById(R.id.imageButton5);
		setContentView(R.layout.activity_menu_home);
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
	
	public void play(View v){
		Intent i = new Intent(MenuHome.this, MainActivity.class);
		startActivity(i);
	}
	
	public void about(View v){
		Intent i = new Intent(MenuHome.this, MenuAbout.class);
		startActivity(i);
	}
	
	public void help(View v){
		Intent i = new Intent(MenuHome.this, MenuHelp.class);
		startActivity(i);
	}
	
	public void quit(View v){
		finish();
	}
	
	public void sound(View v){
		if(sound){
			//imageSound.setBackgroundResource(R.drawable.mute);
			sound = false; 
		}
		else {
			//imageSound.setBackgroundResource(R.drawable.sound);
			sound = true;
		}
	}
}
