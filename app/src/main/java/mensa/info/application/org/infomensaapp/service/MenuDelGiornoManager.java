package mensa.info.application.org.infomensaapp.service;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Menu;

/**
 * Creato da Giuseppe Grosso in data 16/11/15.
 */
public class MenuDelGiornoManager
{
    // Logcat tag
    private static final String LOG = MenuDelGiornoManager.class.getName();

    public MenuDelGiornoManager()
    {

    }

    public List<Menu> getMenuDelGiorno(Context context, String cf)
    {
        return getMenuDelGiorno(context, Calendar.getInstance(), cf);
    }

    public List<Menu> getMenuDelGiornoStandard(Context context, Calendar data)
    {
        return getMenuDelGiorno(context, data, Menu.PASTO_NORMALE);
    }

    public List<Menu> getMenuDelGiorno(Context context, Calendar data, String cf)
    {
        DatabaseHelper db = new DatabaseHelper(context);
        String sData = MenuDelGiornoManager.getDateTime(data, "yyyy-MM-dd");
        List<Menu> allMenus = db.getPastoPersonaleByDate(sData, cf);
        return allMenus;
    }

    public List<Menu> getMenuDelGiornoNonPrevisto(Context context)
    {
        DatabaseHelper db = new DatabaseHelper(context);
        List<Menu> allMenus = db.getMenuNonPrevisto();
        return allMenus;
    }

    public List<Menu> getMenuDelGiorno(Context context, String data, String cf)
    {
        Log.d(LOG, "Prendo il menu dal database");
        DatabaseHelper db = new DatabaseHelper(context);
        List<Menu> allMenus = db.getPastoPersonaleByDate(data, cf);
        return allMenus;
    }

    public void setMenuDelGiorno(Context context, Calendar data, String cf, String[] results)
    {
        Log.d(LOG, "scrivo il menu su database");
        DatabaseHelper db = new DatabaseHelper(context);
        Menu menu = null;
        for (int i = 0; i < results.length; i++)
        {
            menu = new Menu(data, cf, results[i]);
            db.createMenu(menu);
        }
    }

    public void setMenuDelGiorno(Context context, List<Menu> results)
    {
        Log.d(LOG, "scrivo il menu");
        DatabaseHelper db = new DatabaseHelper(context);
        for (int i = 0; i < results.size(); i++)
        {
            db.createMenu(results.get(i));
        }
    }


    /**
     * ritorno la stringa nel formato date time.
     */
    public static String getDateTime(Calendar mydate, String pattern)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        if (mydate == null)
            mydate = Calendar.getInstance();
        return dateFormat.format(mydate.getTime());
    }

}