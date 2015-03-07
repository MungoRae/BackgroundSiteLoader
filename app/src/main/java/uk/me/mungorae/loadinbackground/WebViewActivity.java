package uk.me.mungorae.loadinbackground;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class WebViewActivity extends ActionBarActivity {
    @InjectView(R.id.mr_web_view)
    WebView webView;

    private Uri address;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_web_view);

        ButterKnife.inject(this);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setSupportProgressBarIndeterminateVisibility(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setSupportProgressBarIndeterminateVisibility(false);
                setTitle(view.getTitle());
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        address = intent.getData();
        if(address != null) {
            webView.loadUrl(address.toString());
        }
        else {
            Toast.makeText(this, "Intent has no data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        webView.reload();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_open:
                openUrlInAnotherBrowser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openUrlInAnotherBrowser() {
        Intent urlIntent = new Intent(Intent.ACTION_VIEW);
        urlIntent.setData(address);

        Intent chooserIntent = Intent.createChooser(urlIntent, "Choose Browser:");
        startActivity(chooserIntent);
    }
}
