package mensa.info.application.org.infomensaapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.sql.model.Login;

public class EstrattoContoFragment extends AbstractFragment
{

    WebView myWebView = null;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public static EstrattoContoFragment newInstance(int sectionNumber)
    {
        EstrattoContoFragment fragment = new EstrattoContoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.fragment_ec, container, false);

        Context context = getActivity().getBaseContext();

        myWebView = (WebView) rootView.findViewById(R.id.webview);
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

        String url = "";


        Login lg = getLogin();
        if (lg != null && lg.getCf() != null)
        {
            url = "https://pagamenti.comune.prato.it/pagamentibinj/servlet/CercaDebitiSct?cfGenitore=" + getLogin().getCf() + "&cartaIdentita=" + getLogin().getCi() + "&&codEnte=001&cfBambino=&numeroBadge=";
            myWebView.loadUrl(url);
        } else
            makeToast("Non hai inserito i dati di login");

        return rootView;
    }

}
