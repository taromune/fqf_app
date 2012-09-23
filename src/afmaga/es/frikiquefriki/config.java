package afmaga.es.frikiquefriki;

import afmaga.es.frikiquefriki.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class config extends Activity {
 
    /** Called when the activity is first created. */
	private CheckBox notify_sound;
	private CheckBox notify_vibrate;
	private CheckBox notify_led;
	private SharedPreferences prefs;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        
        prefs = getSharedPreferences("FQFConfiguration",Context.MODE_PRIVATE);
      
        boolean sound = prefs.getBoolean("sound", true);       
        boolean vibrate = prefs.getBoolean("vibrate", true);
        boolean led = prefs.getBoolean("led", true);
                  
        notify_sound = (CheckBox)findViewById(R.id.checkBox_sound);  
        notify_vibrate = (CheckBox)findViewById(R.id.checkBox_vibration);
        notify_led = (CheckBox)findViewById(R.id.checkBox_led); 
        
        notify_sound.setChecked(sound);
        notify_vibrate.setChecked(vibrate);
        notify_led.setChecked(led);  

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	savepreferences(prefs);
            	
    	        finish();
            }
        });
        
              
    }
	
		public void savepreferences(SharedPreferences prefs2){
			   
			   SharedPreferences.Editor editor = prefs2.edit();
		        		         
		        notify_sound = (CheckBox)findViewById(R.id.checkBox_sound);  
		        notify_vibrate = (CheckBox)findViewById(R.id.checkBox_vibration);  
		        notify_led = (CheckBox)findViewById(R.id.checkBox_led); 
		        
		        boolean sound = notify_sound.isChecked();       
		        boolean vibrate = notify_vibrate.isChecked();
		        boolean led =notify_led.isChecked();
		        		        			    
		        editor.putBoolean("sound", sound );
		        editor.putBoolean("vibrate", vibrate );
		        editor.putBoolean("led", led );
		        editor.commit();  
		}
	   @Override
	    public void onBackPressed()
	    {
	     
		   savepreferences(prefs); 
	        
	        super.onBackPressed();
	    }	


}
