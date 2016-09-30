package mensa.info.application.org.infomensaapp.sql.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Creato da Giuseppe Grosso in data 27/11/15.
 */
public class Presenza extends ModelBean
{

    private Calendar data;
    private String cf;
    private int presenza;

    public Presenza()
    {
        this(0);
    }

    public Presenza(int id)
    {
        this(id, null);
    }

    public Presenza(int id, Calendar data)
    {
        this(id, data, null);
    }

    public Presenza(int id, Calendar data, String cf)
    {
        this(id, data, cf, 0);
    }

    public Presenza(int id, Calendar data, String cf, int presenza)
    {
        this.id = id;
        this.data = data;
        this.cf = cf;
        this.presenza = presenza;
    }

    public Presenza(Calendar data, String cf, int presenza)
    {
        this(0, data, cf, presenza);
    }
    public Presenza(Calendar data, String cf)
    {
        this(0, data, cf, 1);
    }


    public Calendar getData()
    {
        return data;
    }

    public String getDataAsString()
    {
        return new SimpleDateFormat("yyyyMMdd").format(data.getTime());
    }

    public void setData(Calendar data)
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

    public int getPresenza()
    {
        return presenza;
    }

    public void setPresenza(int presenza)
    {
        this.presenza = presenza;
    }

}
