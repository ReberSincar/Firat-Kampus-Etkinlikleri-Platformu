package com.rebersincar.kampusetkinlik.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    TextView head;
    Bundle extras;
    Button back,forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webView);
        head = findViewById(R.id.webText);
        back = findViewById(R.id.webBack);
        forward = findViewById(R.id.webPre);
        Yukle();
        Kontrol();

    }

    public void Yukle()
    {
        extras = getIntent().getExtras();
        String link = extras.getString("link");
        String strHead = extras.getString("head");
        head.setText(strHead);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
        final ProgressDialog progress = ProgressDialog.show(this, strHead, "Yükleniyor....", true);
        progress.show();
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress.cancel();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                String url = request.getUrl().toString();
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void Kontrol()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack())
                    webView.goBack();
                else
                    Toast.makeText(WebViewActivity.this, "Geri Gidecek Sayfa Yok", Toast.LENGTH_SHORT).show();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward())
                    webView.goForward();
                else
                    Toast.makeText(WebViewActivity.this, "İler Gidecek Sayfa Yok", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
