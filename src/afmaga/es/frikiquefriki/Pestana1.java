package afmaga.es.frikiquefriki;

import com.google.android.gcm.GCMRegistrar;

import afmaga.es.frikiquefriki.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class Pestana1 extends Activity {
    /** Called when the activity is first created. */
	private ProgressBar progressBar;
	private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pestana1);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);                     
        
        webView = (WebView)findViewById(R.id.webview); 
                
        webView.setWebViewClient(new Callback());



        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient()
        	        {
        	            public void onProgressChanged(WebView view, int progress)
        	            {              
        	            	progressBar.setProgress(0);
        	            	FrameLayout progressBarLayout = (FrameLayout) findViewById(R.id.progressBarLayout1);
        	            	progressBarLayout.setVisibility(View.VISIBLE);
        	            	Pestana1.this.setProgress(progress * 1000);

        	            	TextView progressStatus = (TextView) findViewById(R.id.progressStatus1);
        	            	progressStatus.setText(progress + " %");
        	            	progressBar.incrementProgressBy(progress);

        	            	if (progress == 100)
        	            	{
        	            	progressBarLayout.setVisibility(View.GONE);
        	            	}
        	            }
        	        });
        
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true); 
        webSettings.setPluginsEnabled(true);
        
        webView.loadUrl("http://frikiquefriki.es");
        
    //    GcmActivity GcmActividad = new GcmActivity();
     //   GcmActividad.registrar();         
      
    }
    @Override
    public void onBackPressed()
    {
    if (webView.canGoBack())
    {
    	webView.goBack();
    	}
    	else
    	{	
    	super.onBackPressed();
    	}
    }
    
    private class Callback extends WebViewClient {
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url){    		    		
    		return true;
    	}
    	    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	webView = (WebView) findViewById(R.id.webview);
        switch (item.getItemId()) {
            case R.id.recargar: 
            	webView.reload();
            	return true;
            case R.id.config: 
            	Intent i = new Intent(this, config.class );
                startActivity(i);
            	return true;            
            case R.id.exit: 
            	System.exit(0);
            	return true;
            default :
            	webView.reload();
            	return super.onOptionsItemSelected(item);
           
        }        
    }
}