package afmaga.es.frikiquefriki;

import android.os.Bundle;
import android.app.Activity;
import com.google.android.gcm.GCMRegistrar;

public class GcmActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
                
        		
    }
    
    public void registrar(){
    	GCMRegistrar.checkDevice(GcmActivity.this);
        GCMRegistrar.checkManifest(GcmActivity.this);
        final String regId = GCMRegistrar.getRegistrationId(GcmActivity.this);
		if (regId.equals("")) {
		    	GCMRegistrar.register(GcmActivity.this, "224338875065"); //Sender ID
		} 
    }
 
}
