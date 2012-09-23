package afmaga.es.frikiquefriki;

import afmaga.es.frikiquefriki.R;
import android.app.TabActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TabHost;
import android.util.Log;


public class FQFActivity extends TabActivity {
    /** Called when the activity is first created. */
	//private static final String TAG = "FrikiQueFriki";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        Resources res = getResources();
        
        intent = new Intent().setClass(this, Pestana1.class);
        spec = tabHost.newTabSpec("Pestana1").setIndicator("Índice",res.getDrawable(R.drawable.pestana1)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, Pestana2.class);
        spec = tabHost.newTabSpec("Pestana2").setIndicator("Categorias",res.getDrawable(R.drawable.pestana2)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, Pestana3.class);
        spec = tabHost.newTabSpec("Pestana3").setIndicator("Random!",res.getDrawable(R.drawable.pestana3)).setContent(intent);
        tabHost.addTab(spec);
       
    }
    
    

}