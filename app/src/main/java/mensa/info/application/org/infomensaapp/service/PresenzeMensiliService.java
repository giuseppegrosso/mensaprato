package mensa.info.application.org.infomensaapp.service;

import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import mensa.info.application.org.infomensaapp.service.interfaces.DownloadAbstractService;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Menu;
import mensa.info.application.org.infomensaapp.sql.model.Presenza;

/**
 * Creato da Giuseppe Grosso in data 26/11/15.
 */
public class PresenzeMensiliService extends DownloadAbstractService
{

    private static final String TAG = "PresenzeMensiliService";


    public PresenzeMensiliService()
    {
        super(PresenzeMensiliService.class.getName());
    }


    /**
     * eseguo il parsing dei risultati e li restituisco nel bundle.
     *
     * @param result
     * @return
     */
    protected Object parseResult(String result)
    {

        List<Presenza> dayList = new ArrayList<>();
        Presenza pp = null;
        Calendar cal = Calendar.getInstance();
        try
        {
            JSONObject response = new JSONObject(result);

            JSONArray presenze = response.optJSONArray("presenze");
            Calendar day = null;
            String spresenza = "";
            String cf = "";
            for (int i = 0; i < presenze.length(); i++)
            {
                JSONObject jpresenza = presenze.optJSONObject(i);
                if (jpresenza == null) continue;

                // prendo la data che mi e' stata passata.
                cal.setTimeInMillis(Long.parseLong(jpresenza.get("data").toString()));
                spresenza = jpresenza.get("presenza").toString();
                cf = jpresenza.get("codice_fiscale").toString();
                if (jpresenza != null) break;
            }
            // in base alle presenze array --> costruisco l'oggetto presenze.
            for (int k = 0; k < spresenza.length(); k++)
            {
                if (spresenza.substring(k, k + 1).equalsIgnoreCase("1"))
                {

                    day = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), k + 1);
                    pp = new Presenza(day, cf);
                    dayList.add(pp);
                }

            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return dayList;
    }

    @Override
    protected Object retriveDataFromDbase(Intent intent)
    {
        Long data = intent.getLongExtra("data", 0);
        String cf = intent.getStringExtra("cf");
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        // prendo i dati e li memorizzo
        Calendar presenzaMensile = Calendar.getInstance();
        presenzaMensile.setTimeInMillis(data);
        List<Presenza> allPresenze = db.getPresenzeMensiliByDataCf(presenzaMensile, cf);

        // se ci sono presenze le ritorno.
        if (allPresenze != null && allPresenze.size() > 0)
        {
            return allPresenze;
        }

        return null;
    }

    /**
     * metodo per lo store dei dati.
     *
     * @param data
     * @return
     */
    @Override
    protected void storeData(Intent intent, Object data)
    {
        List<Presenza> lpresenza = (List<Presenza>) data;
        Long mydata = intent.getLongExtra("data", 0);
        String cf = intent.getStringExtra("cf");

        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        Presenza pp = null;
        // cancello preventivamente il database con le presenze.
        db.deletePresenzeMeseByCfDate(cf, mydata);
        for (int i = 0; i < lpresenza.size(); i++)
        {
            pp = lpresenza.get(i);
            Log.w(this.getClass().getName(), "SCRIVO La presenza su database: " + pp.getDataAsString() + " " + pp.getCf());
            db.createPresenze(pp);
        }
    }
}