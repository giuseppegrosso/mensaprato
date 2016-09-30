package mensa.info.application.org.infomensaapp.sql.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

import mensa.info.application.org.infomensaapp.service.MenuDelGiornoManager;

/**
 * Creato da Giuseppe Grosso in data 16/11/15.
 */
public class Menu extends ModelBean implements Parcelable
{
    public static final String PASTO_NORMALE = "PASTO_NORMALE";

    private String data;
    private String cf;
    private String descrizione;
    private int consistente;

    public Menu()
    {
        this(0);
    }

    public Menu(int id)
    {
        this(id, null);
    }

    public Menu(int id, String data)
    {
        this(id, data, null);
    }

    public Menu(int id, String data, String cf)
    {
        this(id, data, cf, null);
    }

    public Menu(int id, String data, String cf, String descrizione)
    {
        this(id, data, cf, descrizione, 0);
    }

    public Menu(int id, String data, String cf, String descrizione, int consistente)
    {
        this.id = id;
        this.data = data;
        this.cf = cf;
        this.descrizione = descrizione;
        this.consistente = consistente;
    }

    public Menu(String descrizione)
    {
        this(PASTO_NORMALE, descrizione);
    }

    public Menu(String cf, String descrizione)
    {
        this((Calendar) null, cf, descrizione);
    }

    public Menu(Calendar data, String descrizione)
    {
        this(data, Menu.PASTO_NORMALE, descrizione);
    }

    public Menu(Calendar data, String cf, String descrizione)
    {
        this(MenuDelGiornoManager.getDateTime(data, "yyyy-MM-dd"), cf, descrizione);
    }

    public Menu(String data, String cf, String descrizione)
    {
        this.data = data;
        this.cf = cf;
        this.descrizione = descrizione;
        this.consistente = 0;
    }


    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getCf()
    {
        return cf;
    }

    public void setCf(String cf)
    {
        this.cf = cf;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public boolean isConsistente()
    {
        return (consistente == 1) ? true : false;
    }

    public void setConsistente(int consistente)
    {
        this.consistente = consistente;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringArray(new String[] {this.createdat,
                this.data, this.cf, this.descrizione});
    }
}
