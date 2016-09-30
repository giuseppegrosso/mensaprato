package mensa.info.application.org.infomensaapp.service;

import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mensa.info.application.org.infomensaapp.service.interfaces.DownloadAbstractService;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Menu;

/**
 * Creato da Giuseppe Grosso in data 13/11/15.
 */
public class MenuDelGiornoService extends DownloadAbstractService
{

    private static final String TAG = "MenuDelGiornoService";

    public MenuDelGiornoService()
    {
        super(MenuDelGiornoService.class.getName());
    }

    /**
     * eseguo il parsing dei risultati e li restituisco nel bundle.
     *
     * @param result
     * @return
     */
    protected Object parseResult(String result)
    {

        List<Menu> menuList = new ArrayList<>();

        try
        {
            JSONObject response = new JSONObject(result);

            JSONArray dieta = response.optJSONArray("menu");

            for (int i = 0; i < dieta.length() - 1; i++)
            {
                JSONObject sdieta = dieta.optJSONObject(i);

                menuList.add(new Menu(sdieta.getString("codice_fiscale"), sdieta.getString("descrizione")));

            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    protected Object retriveDataFromDbase(Intent intent)
    {
        String data = intent.getStringExtra("data");
        String cf = intent.getStringExtra("cf");

        // prendo i dati del menu del giorno.
        MenuDelGiornoManager manager = new MenuDelGiornoManager();
        List<mensa.info.application.org.infomensaapp.sql.model.Menu> allMenu = manager.getMenuDelGiorno(this.getApplicationContext(), data, cf);

        // non so come fare!!!
        if (allMenu != null && allMenu.size() > 0)
        {
            return allMenu;
        }

        // se non ho trovato i dati di menu personali prendo la dieta normale.
        allMenu = manager.getMenuDelGiorno(this.getApplicationContext(), data, Menu.PASTO_NORMALE);
        if (allMenu != null && allMenu.size() > 0)
        {
            return allMenu;
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
        List<Menu> lmenu = (List<Menu>) data;
        Log.w(this.getClass().getName(), "SCRIVO IL MENU su database: " + lmenu.size());

        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        Menu mn = null;
        for (int i = 0; i < lmenu.size(); i++)
        {
            mn = lmenu.get(i);
            Log.w(this.getClass().getName(), "SCRIVO IL MENU su database: " + mn.getDescrizione() + " " + mn.getData());
            db.createMenu(mn);
        }
    }
}