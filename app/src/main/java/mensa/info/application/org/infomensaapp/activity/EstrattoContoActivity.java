package mensa.info.application.org.infomensaapp.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.sql.model.Login;

public class EstrattoContoActivity extends AbstractActivity
{

    WebView myWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estratto_conto);
        setSupportedToolBar();

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);

                return false; // then it is not handled by default action
            }
        });

        String url = "https://pagamenti.comune.prato.it/pagamentibinj/servlet/CercaDebitiSct?cfGenitore=" + getLogin().getCf() + "&cartaIdentita=" + getLogin().getCi() + "&&codEnte=001&cfBambino=&numeroBadge=";

        Login lg = getLogin();
        if (lg != null && lg.getCf() != null)
            myWebView.loadUrl(url);
        else
            makeToast("Non hai inserito i dati di login");

    }

}
