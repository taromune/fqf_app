package afmaga.es.frikiquefriki;

import afmaga.es.frikiquefriki.R;
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

public class Pestana2 extends Activity {
    /** Called when the activity is first created. */
	private ProgressBar progressBar2;
	private WebView webView2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pestana2);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar2);                     
        
        webView2 = (WebView)findViewById(R.id.webview2);       
        
        webView2.setWebViewClient(new Callback());
        
        WebSettings webSettings = webView2.getSettings();
        webSettings.setBuiltInZoomControls(true);
//        webSettings.setJavaScriptEnabled(true);                      	              
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        
        webView2.setWebViewClient(new WebViewClient());

        webView2.setWebChromeClient(new WebChromeClient()
        	        {
        	            public void onProgressChanged(WebView view, int progress)
        	            {              
        	            	progressBar2.setProgress(0);
        	            	FrameLayout progressBarLayout = (FrameLayout) findViewById(R.id.progressBarLayout2);
        	            	progressBarLayout.setVisibility(View.VISIBLE);
        	            	Pestana2.this.setProgress(progress * 1000);

        	            	TextView progressStatus = (TextView) findViewById(R.id.progressStatus2);
        	            	progressStatus.setText(progress + " %");
        	            	progressBar2.incrementProgressBy(progress);

        	            	if (progress == 100)
        	            	{
        	            	progressBarLayout.setVisibility(View.GONE);
        	            	}
        	            }
        	        });
        webView2.loadUrl("http://frikiquefriki.es/categorias");
      
    }
    @Override
    public void onBackPressed()
    {
    if (webView2.canGoBack())
    {
    	webView2.goBack();
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
    	webView2 = (WebView) findViewById(R.id.webview2);
        switch (item.getItemId()) {
            case R.id.recargar: 
            	webView2.reload();
            	return true;
            case R.id.config: 
            	Intent i = new Intent(this, config.class );
                startActivity(i);
            	return true;
            case R.id.exit: 
            	System.exit(0);
            	return true;
            default :
            	webView2.reload();
            	return super.onOptionsItemSelected(item);
           
        }        
    }
}