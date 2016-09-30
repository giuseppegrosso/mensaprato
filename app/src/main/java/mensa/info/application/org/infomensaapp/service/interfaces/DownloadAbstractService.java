package mensa.info.application.org.infomensaapp.service.interfaces;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mensa.info.application.org.infomensaapp.service.MenuDelGiornoManager;

/**
 * Creato da Giuseppe Grosso in data 13/11/15.
 */
public abstract class DownloadAbstractService extends IntentService implements DownloadInterfaceService
{
    private static final String TAG = "DownloadAbstractService";

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private Intent myintent = null;


    /**
     * Creo un IntentService.
     *
     * @param name Usato solo per creare il worker thread, importante solo per debugging.
     */
    public DownloadAbstractService(String name)
    {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        myintent = intent;
        Log.w(TAG, "Service Started!");

        // prendo la classe di receiver
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        // recupero la url impostata nell'activity
        String url = intent.getStringExtra("url");

        // istanzio oggetto bundle come contenitore dei dati.
        Bundle bundle = new Bundle();

        // se la url non è vuota ... procedo
        if (!TextUtils.isEmpty(url))
        {
            /* Aggiorno la UI: il servizio di Download Service è in Running */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
            Object results = null;
            try
            {
                // cerco i dati nel database
                results = retriveDataFromDbase(intent);
                // se non li trovo li chiedo al server
//                if (results == null) results = downloadData(url);
                if (results == null)
                {
                    // prendo i dati del menu del giorno.
                    MenuDelGiornoManager manager = new MenuDelGiornoManager();
                    results = manager.getMenuDelGiornoNonPrevisto(this.getApplicationContext());
                }

                /* rimando i dati indietro all'activity */
                if (results != null)
                {
                    // i dati li trasformo in array di byte
                    bundle.putByteArray("result", object2Bytes(results));
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e)
            {

                /* invio un messaggio di errore indietro all'activity */
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);
            }
        }
        Log.d(TAG, "Fine servizio Download.");
        this.stopSelf();
    }

    /**
     * converto l'oggetto (deve implementare serializable) in byte arrays
     */
    static public byte[] object2Bytes(Object o) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        return baos.toByteArray();
    }

    /**
     * effettuo la chiamata http
     *
     * @param requestUrl
     * @return
     * @throws IOException
     * @throws DownloadException
     */
    public Object downloadData(String requestUrl) throws IOException, DownloadException
    {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        /* formo l'oggetto java java.net.URL */
        URL url = new URL(requestUrl);
        urlConnection = (HttpURLConnection) url.openConnection();

        /* opzionale request header */
        urlConnection.setRequestProperty("Content-Type", "application/json");

        /* opzionale request header */
        urlConnection.setRequestProperty("Accept", "application/json");

        /* chiamata in Get */
        urlConnection.setRequestMethod("GET");
        int statusCode = urlConnection.getResponseCode();

        /* 200 rappresenta HTTP OK */
        if (statusCode == 200)
        {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String response = convertInputStreamToString(inputStream);
            Object results = parseResult(response);

            // chiamata alla classe implementativa per lo store dei dati su database locale.
            storeData(this.myintent, results);

            return results;
        } else
        {
            throw new DownloadException("Errore nel fetch dei dati");
        }
    }


    protected String convertInputStreamToString(InputStream inputStream) throws IOException
    {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while ((line = bufferedReader.readLine()) != null)
        {
            result += line;
        }

            /* Close Stream */
        if (null != inputStream)
        {
            inputStream.close();
        }

        return result;
    }

    public class DownloadException extends Exception
    {

        public DownloadException(String message)
        {
            super(message);
        }

        public DownloadException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    // metodo per il parsing dei dati.
    protected abstract Object parseResult(String response);


    protected abstract Object retriveDataFromDbase(Intent intent);

    // metodo per lo store dei dati.
    protected abstract void storeData(Intent intent, Object data);


}
