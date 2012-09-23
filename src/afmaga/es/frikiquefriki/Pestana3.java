package afmaga.es.frikiquefriki;

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

public class Pestana3 extends Activity {
    /** Called when the activity is first created. */
	private ProgressBar progressBar3;
	private WebView webView3;
    @SuppressLint("SetJavaScriptEnabled")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pestana3);
        progressBar3 = (ProgressBar) findViewById(R.id.progressbar3);                     
        
        webView3 = (WebView)findViewById(R.id.webview3);       
        
        webView3.setWebViewClient(new Callback());
        
        WebSettings webSettings = webView3.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);                      	              
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        
        webView3.setWebViewClient(new WebViewClient());

        webView3.setWebChromeClient(new WebChromeClient()
        	        {
        	            public void onProgressChanged(WebView view, int progress)
        	            {              
        	            	progressBar3.setProgress(0);
        	            	FrameLayout progressBarLayout = (FrameLayout) findViewById(R.id.progressBarLayout3);
        	            	progressBarLayout.setVisibility(View.VISIBLE);
        	            	Pestana3.this.setProgress(progress * 1000);

        	            	TextView progressStatus = (TextView) findViewById(R.id.progressStatus3);
        	            	progressStatus.setText(progress + " %");
        	            	progressBar3.incrementProgressBy(progress);

        	            	if (progress == 100)
        	            	{
        	            	progressBarLayout.setVisibility(View.GONE);
        	            	}
        	            }
        	        });
        webView3.loadUrl("http://frikiquefriki.es/random");
      
    }
    @Override
    public void onBackPressed()
    {
    if (webView3.canGoBack())
    {
    	webView3.goBack();
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
    	menu.clear(); 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_random, menu);
        return true;
    }
       
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	webView3 = (WebView) findViewById(R.id.webview3);
        switch (item.getItemId()) {
            case R.id.random: 
            	webView3.loadUrl("http://frikiquefriki.es/random");
            	return true;
            case R.id.config: 
            	Intent i = new Intent(this, config.class );
                startActivity(i);
            	return true;
            case R.id.exit: 
            	System.exit(0);
            	return true;
            default :
            	webView3.reload();
            	return super.onOptionsItemSelected(item);
           
        }        
    }
}