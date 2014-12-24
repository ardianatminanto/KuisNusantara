package com.example.kuisnusantara;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements ViewFactory{
	private int index = 0;
	private int hitungUkuranSample(Options options, int lebarDiperlukan, int tinggiDiperlukan){
		final int lebar = options.outWidth;
		final int tinggi = options.outHeight;
		int sampleResize = 1;
		
		if(tinggi > tinggiDiperlukan || lebar > lebarDiperlukan){
			sampleResize = Math.round((float) tinggi / (float) tinggiDiperlukan);
		} else {
			sampleResize = Math.round((float) lebar / (float) lebarDiperlukan);
		}
		return sampleResize;
	}
	
	private Bitmap decodeContohBitmapDariAlamatPath(InputStream is, int lebar, int tinggi){
		Bitmap bm = null;
		final BitmapFactory.Options options = new BitmapFactory.Options();
		
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(is, null, options);
		options.inSampleSize =  hitungUkuranSample(options, lebar, tinggi);
		options.inJustDecodeBounds = false;
		
		bm = BitmapFactory.decodeStream(is, null, options);
		
		return bm;
	}
	
	private ImageView insertPhoto(InputStream is) {
		Bitmap bm = decodeContohBitmapDariAlamatPath(is, 500, 500);
		
		final ImageView imageView = new ImageView(getApplicationContext());
		imageView.setLayoutParams(new LayoutParams(500, 500));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(5, 5, 5, 5);
		imageView.setLayoutParams(lp);
		
		imageView.setImageBitmap(bm);
		return imageView;
	}
	
	//-------------------------------------------VIEW--------------------------------------------------------------
	
	private void tampilanProv(){
		viewProv.setText(Integer.toString(prov+1)+ " / " +Integer.toString(listProv.length));
	}
	
	private void namaProv(){
		DatabaseConnector dc = new DatabaseConnector(this);
		namaProv.setText(dc.namaProv(prov+1));
		dc.close();
	}
	
	private void namaIbukota(){
		DatabaseConnector dc = new DatabaseConnector(this);
		ibuKota.setText(dc.namaIbukota(prov+1));
		dc.close();
	}
	
	private void tampilanGambar(){
		DatabaseConnector dc = new DatabaseConnector(this);
		int totalGambar =dc.jumlahGambarProv(prov+1), totalPoin = dc.maxPoin(prov+1);
		jumlahGambar.setText("100");
		maksPoin.setText(Integer.toString(totalPoin));
		dc.close();
	}
	
	private void initView(){
		tampilanProv();
		namaProv();
		namaIbukota();
		tampilanGambar();
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	private static String[] listProv = null;
	private int prov = 0;
	LinearLayout gambarProvinsi;
	private ImageSwitcher imageSwitcher;
	private TextView jumlahGambar;
	private TextView maksPoin;
	private TextView ibuKota;
	private TextView namaProv;
	private TextView viewProv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseConnector dc = new DatabaseConnector(this);
		
		jumlahGambar = (TextView) findViewById(R.id.textJumlahGambar);
		maksPoin = (TextView) findViewById(R.id.textMaksPoin);
		ibuKota = (TextView) findViewById(R.id.textIbukota);
		namaProv = (TextView) findViewById(R.id.namaProv);
		viewProv = (TextView) findViewById(R.id.viewProv);
		
		MenuAwal.mPlayer.start();
		
		Bundle extras = getIntent().getExtras();
		index = extras.getInt("index");
		
		if(index>0)
			prov = index - 1;
		else
			prov = 0;
		Cursor c = dc.getProv();
		listProv = new String[c.getCount()];
		int numProv = 0;
		if(c.moveToFirst()){
			do{
				listProv[numProv] = c.getString(c.getColumnIndex("gambar"));
				numProv++;
			}while(c.moveToNext());
		}
		imageSwitcher=(ImageSwitcher) findViewById(R.id.imageSwitcher1);
		imageSwitcher.setFactory(this);
		try {
			imageSwitcher.setImageDrawable(insertPhoto(getAssets().open("provinsi/" +listProv[prov]+ ".jpg")).getDrawable());
		} catch (IOException e) {
			e.printStackTrace();
		}
		initView();
		dc.close();
	}
	
	//---------------------------------------BUTTON-----------------------------------------------------------------------------------
	
	public void sebelumnya(View v){
		Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
		Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);
		if(prov != 0)
			prov--;
		else prov = listProv.length - 1;
		try {
			imageSwitcher.setImageDrawable(insertPhoto(getAssets().open("provinsi/" +listProv[prov]+ ".jpg")).getDrawable());
			initView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void selanjutnya(View v){
		Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
		Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);
		if(prov != listProv.length - 1)
			prov++;
		else prov = 0;
		try {
			imageSwitcher.setImageDrawable(insertPhoto(getAssets().open("provinsi/" +listProv[prov]+ ".jpg")).getDrawable());
			initView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void masuk(View v){
		//Toast.makeText(getApplicationContext(), listNamaProv[0], Toast.LENGTH_SHORT).show();
		Intent i = new Intent(MainActivity.this, KuisActivity.class);
		i.putExtra("nama_prov", namaProv.getText().toString());
		i.putExtra("region", listProv[prov]);
		i.putExtra("index_prov", prov+1);
		MenuAwal.mPlayer.pause();
		startActivity(i);

	}

	public void reset(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset Skor"); 
        builder.setMessage("Apakah Anda yakin akan mengulang skor Anda?");

        builder.setCancelable(false); 
        builder.setPositiveButton("Ya",
           new DialogInterface.OnClickListener()                
           {                                                       
              public void onClick(DialogInterface dialog, int id) 
              {
                 //resetQuiz();
              	DatabaseConnector dc = new DatabaseConnector(MainActivity.this);
              	dc.updatePoin(0, prov+1);
              	int totalPoin = dc.maxPoin(prov+1);
        		maksPoin.setText(Integer.toString(totalPoin));
              	dc.close();
              }                              
           }
        );
        builder.setNegativeButton("Tidak", null);
        AlertDialog resetDialog = builder.create();
        resetDialog.show();
	}

	//---------------------------------------BUTTON-----------------------------------------------------------------------------------

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new
				ImageSwitcher.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LayoutParams.MATCH_PARENT));
		return imageView;
	}
	
	@Override
	public void onBackPressed(){
		Intent i = new Intent(MainActivity.this, MenuHome.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("EXIT", true);
		i.putExtra("status", false);
		startActivity(i);
	}
}
