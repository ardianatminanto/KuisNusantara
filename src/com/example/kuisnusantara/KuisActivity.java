package com.example.kuisnusantara;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.example.kuisnusantara.R.color;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class KuisActivity extends Activity {
	
	private static final String TAG = "KuisNusantara Activity";  
	private List<String> fileNameList; // flag file names
	private List<String> quizCountriesList;
	private List<String> answerList;
	private List<String> choiceList;
	
	private String correctAnswer; 
	private int totalGuesses; // number of guesses made
	private int correctAnswers; // number of correct guesses
	private int guessRows;
	private int poin = 0; //poin terkumpul
	private Random random; 
	private Handler handler;
	private Animation shakeAnimation;
	private Animation blinkAnimation;
	private String region;
	private String prov = "";
	private int indexProv;
	
	private TextView poinView;
	private TextView answerTextView;
	private TextView questionNumberTextView;
	
	//gambar soal
	private ImageView flagImageView;
	
	private TableLayout buttonTableLayout;
	
	//hint
	private ImageView bantuan1;
	private ColorMatrixColorFilter filterHint1;
	private ImageView bantuan2;
	private ColorMatrixColorFilter filterHint2;
	private ImageView bantuan3;
	private ColorMatrixColorFilter filterHint3;
	private ImageView bantuan4;
	private ColorMatrixColorFilter filterHint4;
	private int nyawa = 1;
	private int bantuan = 0;
	private List<Integer> jawabanSalah;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.activity_kuis); 

	    fileNameList = new ArrayList<String>();
	    quizCountriesList = new ArrayList<String>();
	    answerList = new ArrayList<String>();
	    choiceList = new ArrayList<String>();
	    guessRows = 4; 
	    random = new Random(); 
	    handler = new Handler();
	    
	    shakeAnimation = 
	       AnimationUtils.loadAnimation(this, R.anim.incorrect_shake); 
	    shakeAnimation.setRepeatCount(3); 
	    
	    blinkAnimation = new AlphaAnimation(0,1);
	    blinkAnimation.setDuration(250); // duration - half a second
	    blinkAnimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
	    blinkAnimation.setRepeatCount(Animation.RELATIVE_TO_SELF); // Repeat animation infinitely
	    blinkAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
	    
	    Bundle extras = getIntent().getExtras();
	    region = extras.getString("region");
	    prov = extras.getString("nama_prov");
	    indexProv = extras.getInt("index_prov");
	    
	    setTitle("Propinsi "+prov);
	    //----------------------------------------------------------------------------------------------------
	    
	    questionNumberTextView = 
	       (TextView) findViewById(R.id.questionNumberTextView);
	    
	    flagImageView = (ImageView) findViewById(R.id.flagImageView);
	    
	    buttonTableLayout = 
	       (TableLayout) findViewById(R.id.buttonTableLayout);
	    
	    poinView = (TextView)findViewById(R.id.titleTextView);
	    poinView.setText("Poin Anda : "+String.valueOf(poin));
	    
	    questionNumberTextView.setText(
	       getResources().getString(R.string.question) + " 1 " + 
	       getResources().getString(R.string.of) + " 10");
	    
	    //deklarasi variabel bantuan
	    bantuan1 = (ImageView) findViewById(R.id.bantuan1);
		bantuan1.setOnClickListener(hint1Listener);
		filterHint1 = (ColorMatrixColorFilter) bantuan1.getColorFilter();
		
		jawabanSalah = new ArrayList<Integer>();
		
		bantuan2 = (ImageView)findViewById(R.id.bantuan2);
		bantuan2.setOnClickListener(hint2Listener);
		filterHint2 = (ColorMatrixColorFilter)bantuan2.getColorFilter();
		
		bantuan3 = (ImageView)findViewById(R.id.bantuan3);
		bantuan3.setOnClickListener(hint3Listener);
		filterHint3 = (ColorMatrixColorFilter)bantuan3.getColorFilter();
		
		bantuan4 = (ImageView)findViewById(R.id.bantuan4);
		filterHint4 = (ColorMatrixColorFilter)bantuan4.getColorFilter();
	    
	    resetQuiz();
	} 
	
	private void resetQuiz() 
	{      
		DatabaseConnector dc = new DatabaseConnector(this);
		fileNameList.clear();
		Cursor c = dc.getGambar(indexProv);
		
		if(c.moveToFirst()){
			do{
				fileNameList.add(c.getString(c.getColumnIndex("nama")));
			}while(c.moveToNext());
		}
		else Log.e(TAG, "Error loading image file names");
	      
	    correctAnswers = 0; 
	    totalGuesses = 0;
	    poin = 0;
	    quizCountriesList.clear();
	    
	    //reset nyawa dan bantuan
	    nyawa = 1;
	    bantuan = 0;
	    
	    poinView = (TextView)findViewById(R.id.titleTextView);
	    poinView.setText("Poin Anda : "+String.valueOf(poin));
	   	
	    //reset Hint
	    bantuan1.setColorFilter(filterHint1);
	    bantuan1.setOnClickListener(hint1Listener);
	    
	    bantuan2.setColorFilter(filterHint2);
	    bantuan2.setOnClickListener(hint2Listener);
	    
	    bantuan3.setColorFilter(filterHint3);
	    bantuan3.setOnClickListener(hint3Listener);
	    
	    bantuan4.setColorFilter(filterHint4);
	    
	    int flagCounter = 1; 
	    int numberOfFlags = fileNameList.size();
	    while (flagCounter <= 10) 
	    {
	       int randomIndex = random.nextInt(numberOfFlags);          
	       String fileName = fileNameList.get(randomIndex);
	       if (!quizCountriesList.contains(fileName)) 
	       {
	          quizCountriesList.add(fileName); 
	          ++flagCounter;
	       }
	    }
	    loadNextFlag();
	} 
	
	private void loadNextFlag() 
	{
	    String nextImageName = quizCountriesList.remove(0);
	    correctAnswer = nextImageName;

	    String[] namaFile = nextImageName.split("-");

	    //answerTextView.setText("");  
	    questionNumberTextView.setText(
	       namaFile[0]+" - "+prov);
	    
	    AssetManager assets = getAssets(); // get app's AssetManager
	    InputStream stream;
	    try
	    {
	       stream = assets.open(region + "/" + nextImageName + ".jpg");
	       Drawable flag = Drawable.createFromStream(stream, nextImageName);
	       flagImageView.setImageDrawable(flag);                       
	    }
	    catch (IOException e)  
	    {
	       Log.e(TAG, "Error loading " + nextImageName, e);
	    } 
	    
	    for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
	       ((TableRow) buttonTableLayout.getChildAt(row)).removeAllViews();

	    Collections.shuffle(fileNameList); 
	      
	    int correct = fileNameList.indexOf(correctAnswer);
	    fileNameList.add(fileNameList.remove(correct));
	    
	    DatabaseConnector dc = new DatabaseConnector(this);
	    choiceList.clear();
	    choiceList = dc.getChoice(correctAnswer);

	    LayoutInflater inflater = (LayoutInflater) getSystemService(
	       Context.LAYOUT_INFLATER_SERVICE);

	      
	    for (int row = 0; row < guessRows; row++) 
	    {
	       TableRow currentTableRow = getTableRow(row);

	       for (int column = 0; column < 1; column++) 
	       {
	          Button newGuessButton = 
	             (Button) inflater.inflate(R.layout.guess_button, null);
	          //String fileName = fileNameList.get((row * 1) + column);
	          String fileName = choiceList.get(row);
	          newGuessButton.setWidth(50);
	          newGuessButton.setHeight(50);
	          //newGuessButton.setText(getCountryName(fileName));
	          newGuessButton.setText(fileName);
	          newGuessButton.setId(row);
	          newGuessButton.setOnClickListener(guessButtonListener);
	          newGuessButton.setSingleLine(true);
	          currentTableRow.addView(newGuessButton);
	          answerList.add(newGuessButton.getText().toString());
	       } 
	    } 
	    
	    int row = random.nextInt(guessRows);
	    int column = random.nextInt(1); 
	    TableRow randomTableRow = getTableRow(row);
	    String countryName = getCountryName(correctAnswer);
	    ((Button)randomTableRow.getChildAt(column)).setText(countryName);
	} 
	
	private TableRow getTableRow(int row)
	{
	    return (TableRow) buttonTableLayout.getChildAt(row);
	} 
	
	private String getCountryName(String name)
	{
	    return name.substring(name.indexOf('-') + 1).replace('_', ' ');
	}
	
	private void submitGuess(Button guessButton) 
	{
	    String guess = guessButton.getText().toString();
	    String answer = getCountryName(correctAnswer);
	    ++totalGuesses; 
	    if (guess.equals(answer)) 
	    {
	       ++correctAnswers;
	       if(bantuan == 2){
	    	   poin+=5;
	    	   bantuan=0;
	       }   
	       else
	    	   poin+=10;
	       MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.yay);
	       if(MenuHome.sound == true)
	    	   mPlayer.setVolume(1, 1);
	       else
	    	   mPlayer.setVolume(0, 0);
		   mPlayer.setLooping(false);
		   mPlayer.start();
	       guessButton.setBackgroundColor(Color.GREEN);
	       disableButtons();
	       poinView.setText("Poin Anda : "+String.valueOf(poin));
		   guessButton.startAnimation(blinkAnimation);
		   answerList.clear();
	       if (correctAnswers == 10) 
	       {
	          AlertDialog.Builder builder = new AlertDialog.Builder(this);
	          builder.setTitle("Anda Berhasil"); 
	          builder.setMessage("Selamat, Anda berhasil menyelesaikan Kuis Nusantara Propinsi "+prov);

	          builder.setCancelable(false); 
	          builder.setPositiveButton("Kembali Ke Menu",
	             new DialogInterface.OnClickListener()                
	             {                                                       
	                public void onClick(DialogInterface dialog, int id) 
	                {
	                   //resetQuiz();
	                	DatabaseConnector dc = new DatabaseConnector(KuisActivity.this);
	                	dc.updatePoin(poin, indexProv);
	                	Intent i = new Intent(KuisActivity.this,MainActivity.class);
	                	i.putExtra("index", indexProv);
		  			    startActivity(i);
		  			    dc.close();
	                }                              
	             }
	          );
	          AlertDialog resetDialog = builder.create();
	          resetDialog.show();
	       } 
	       else
	       {  handler.postDelayed(
	             new Runnable()
	             { 
	                @Override
	                public void run()
	                {
	                   loadNextFlag();
	                }
	             }, mPlayer.getDuration()); 
	       }
	    } 
	    else  
	    {  
	       int index = 0;
	       flagImageView.startAnimation(shakeAnimation);
	       for(int i=0; i<answerList.size(); i++){
	    	   Button answers = (Button)findViewById(i);
	    	   if(!answerList.get(i).equals(answers.getText().toString())){
	    		   index=i;
	    	   }
	       }
	       guessButton.setEnabled(false);
	       guessButton.setBackgroundColor(Color.RED);
	       Button correct = (Button)findViewById(index);
	       correct.setBackgroundColor(Color.GREEN);
	       correct.startAnimation(blinkAnimation);
	       answerList.clear();
	       
	       MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.wrong);
	       if(MenuHome.sound == true)
	    	   mPlayer.setVolume(1, 1);
	       else
	    	   mPlayer.setVolume(0, 0);
		   mPlayer.setLooping(false);
		   mPlayer.start();
	       
	       if(nyawa==1 || bantuan==2){
	    	   if(bantuan!=2){
	    		   nyawa--;
				   ColorMatrix matrix = new ColorMatrix();
		   		   matrix.setSaturation(0);
		   		   ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
		   		   bantuan4.setColorFilter(cf);
	    	   }
	    	   else{
	    		   bantuan=0;
	    		   poin-=5;
	    		   poinView.setText("Poin Anda : "+String.valueOf(poin));
	    	   }
	    	   ++correctAnswers;
	    	   if (correctAnswers == 10) 
		       {
		          AlertDialog.Builder builder = new AlertDialog.Builder(this);
		          builder.setTitle("Anda Berhasil"); 
		          builder.setMessage("Selamat, Anda berhasil menyelesaikan Kuis Nusantara Propinsi "+prov);

		          builder.setCancelable(false); 
		          builder.setPositiveButton(R.string.reset_quiz,
		             new DialogInterface.OnClickListener()                
		             {                                                       
		                public void onClick(DialogInterface dialog, int id) 
		                {
		                   resetQuiz();                                      
		                }                              
		             }
		          ); 
		          AlertDialog resetDialog = builder.create();
		          resetDialog.show();
		       } 
		       else
		       {  handler.postDelayed(
		             new Runnable()
		             { 
		                @Override
		                public void run()
		                {
		                   loadNextFlag();
		                }
		             }, mPlayer.getDuration()); 
		       }
	       }
	       else{
	    	   //Message Show main lagi atau tidak
		       AlertDialog.Builder builder = new AlertDialog.Builder(this);
		       builder.setTitle(R.string.lose); 
	           
		       builder.setMessage("Maaf, Jawaban Anda Salah");

		       builder.setCancelable(false);
		       
		       builder.setPositiveButton("Main Lagi",
		             new DialogInterface.OnClickListener()                
		             {                                                       
		                public void onClick(DialogInterface dialog, int id) 
		                {
		                   resetQuiz();                                      
		                }                              
		             }
		          );
		       builder.setNegativeButton("Keluar",
		            new DialogInterface.OnClickListener()                
		             {                                                       
		                public void onClick(DialogInterface dialog, int id) 
		                {
		                	Intent i = new Intent(KuisActivity.this,MainActivity.class);
		                	i.putExtra("index", indexProv);
			  			    startActivity(i);                                      
		                }                              
		             }	  	  
		          );
		       AlertDialog resetDialog = builder.create();
		       resetDialog.show();
	       }
	       disableButtons();
	    } 
	} 

	private void disableButtons()
	{
	    for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
	    {
	       TableRow tableRow = (TableRow) buttonTableLayout.getChildAt(row);
	       for (int i = 0; i < tableRow.getChildCount(); ++i)
	          tableRow.getChildAt(i).setEnabled(false);
	    } 
	} 
	
	private void disableWrongAnswer()
	{
		for(int i=0; i<answerList.size(); i++){
    	   Button answers = (Button)findViewById(i);
    	   if(answerList.get(i).equals(answers.getText().toString())){
    		   jawabanSalah.add(i);
    	   }
       }
	   List<Integer> disableAnswers = new ArrayList<Integer>();
	   for(int i=0; i<2; i++){
		   int index = random.nextInt(jawabanSalah.size());
		   disableAnswers.add(jawabanSalah.get(index));
		   jawabanSalah.remove(index);
	   }
	   for(int i=0; i<disableAnswers.size(); i++){
		   Button answer = (Button)findViewById(disableAnswers.get(i));
		   answer.setEnabled(false);
	   }
	   jawabanSalah.clear();
	   bantuan1.setOnClickListener(null);
	}
	
	private final int CHOICES_MENU_ID = Menu.FIRST;
	private final int REGIONS_MENU_ID = Menu.FIRST + 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu)             
	{            
	   // super.onCreateOptionsMenu(menu);                        
	                                                              
	   // menu.add(Menu.NONE, CHOICES_MENU_ID, Menu.NONE, R.string.choices);             
	    //menu.add(Menu.NONE, REGIONS_MENU_ID, Menu.NONE, R.string.regions);             
	                                                              
	    return true; 
	}
	
	@Override
	public void onBackPressed(){
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	      /*switch (item.getItemId()) 
	      {
	         case CHOICES_MENU_ID:
	            final String[] possibleChoices = 
	               getResources().getStringArray(R.array.guessesList);

	            AlertDialog.Builder choicesBuilder = 
	               new AlertDialog.Builder(this);         
	            choicesBuilder.setTitle(R.string.choices);
	         
	            choicesBuilder.setItems(R.array.guessesList,                    
	               new DialogInterface.OnClickListener()                    
	               {                                                        
	                  public void onClick(DialogInterface dialog, int item) 
	                  {                                                     
	                     guessRows = Integer.parseInt(                      
	                        possibleChoices[item].toString()) / 3;          
	                     resetQuiz();                      
	                  }                               
	               } 
	            );                               
	            AlertDialog choicesDialog = choicesBuilder.create();
	            choicesDialog.show();          
	            return true; 

	         case REGIONS_MENU_ID:
	            final String[] regionNames = 
	               regionsMap.keySet().toArray(new String[regionsMap.size()]);
	         
	            boolean[] regionsEnabled = new boolean[regionsMap.size()];
	            for (int i = 0; i < regionsEnabled.length; ++i)
	               regionsEnabled[i] = regionsMap.get(regionNames[i]);
	            AlertDialog.Builder regionsBuilder =
	               new AlertDialog.Builder(this);
	            regionsBuilder.setTitle(R.string.regions);
	            
	            String[] displayNames = new String[regionNames.length];
	            for (int i = 0; i < regionNames.length; ++i)
	               displayNames[i] = regionNames[i].replace('_', ' ');
	         
	            regionsBuilder.setMultiChoiceItems( 
	               displayNames, regionsEnabled,
	               new DialogInterface.OnMultiChoiceClickListener() 
	               {
	                  @Override
	                  public void onClick(DialogInterface dialog, int which,
	                     boolean isChecked) 
	                  {
	                   regionsMap.put(
	                        regionNames[which].toString(), isChecked);
	                  }
	               } 
	            ); 
	          
	            regionsBuilder.setPositiveButton(R.string.reset_quiz,
	               new DialogInterface.OnClickListener()
	               {
	                  @Override
	                  public void onClick(DialogInterface dialog, int button)
	                  {
	                     resetQuiz(); 
	                  } 
	               } 
	            ); 
	            AlertDialog regionsDialog = regionsBuilder.create();
	            regionsDialog.show();
	            return true;
	      }*/
		   
		  
		if (item.getItemId() == android.R.id.home ){
			finish();
		}
	    return super.onOptionsItemSelected(item);
	} 
	
	//onClick Full Screen
	/*private OnClickListener fullScreenListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(isImageFitToScreen){
				isImageFitToScreen = false;
				flagImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                flagImageView.setAdjustViewBounds(true);
			}
			else{
				isImageFitToScreen = true;
				flagImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                flagImageView.setScaleType(ImageView.ScaleType.FIT_XY);
			}
		}
	};*/
	
	private OnClickListener guessButtonListener = new OnClickListener() 
	{
	    @Override
	    public void onClick(View v) 
	    {
	       submitGuess((Button) v); 
	    }
	};
	
	//onClick hint quiz
	private OnClickListener hint1Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ColorMatrix matrix = new ColorMatrix();
			matrix.setSaturation(0);
			ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
			((ImageView) v).setColorFilter(cf);
			
			//set bantuan menjadi 1
			bantuan = 1;
			
			disableWrongAnswer();
		}
	};
	
	private OnClickListener hint2Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ColorMatrix matrix = new ColorMatrix();
			matrix.setSaturation(0);
			ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
			((ImageView) v).setColorFilter(cf);
			
			//set bantuan menjadi 2
			bantuan = 2;
			bantuan2.setOnClickListener(null);
		}
	};
	
	private OnClickListener hint3Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ColorMatrix matrix = new ColorMatrix();
			matrix.setSaturation(0);
			ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
			((ImageView) v).setColorFilter(cf);
			//set bantuan menjadi 3
			bantuan = 3;
			
			//skip question
			++correctAnswers;
			if (correctAnswers == 10) 
			{
	          AlertDialog.Builder builder = new AlertDialog.Builder(KuisActivity.this);
	          builder.setTitle("Anda Berhasil"); 
	          builder.setMessage("Selamat, Anda berhasil menyelesaikan Kuis Nusantara Propinsi "+prov);

	          builder.setCancelable(false); 
	          builder.setPositiveButton(R.string.reset_quiz,
	             new DialogInterface.OnClickListener()                
	             {                                                       
	                public void onClick(DialogInterface dialog, int id) 
	                {
	                   resetQuiz();                                      
	                }                              
	             }
	          ); 
	          AlertDialog resetDialog = builder.create();
	          resetDialog.show();
			} 
			else
			{  handler.postDelayed(
	             new Runnable()
	             { 
	                @Override
	                public void run()
	                {
	                   loadNextFlag();
	                }
	             }, 100); 
			}
			answerList.clear();
			bantuan3.setOnClickListener(null);
		}
	};
}
