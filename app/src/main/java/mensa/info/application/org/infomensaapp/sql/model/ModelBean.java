package mensa.info.application.org.infomensaapp.sql.model;

import java.io.Serializable;

/**
 * Creato da Giuseppe Grosso in data 27/11/15.
 * <p/>
 * modello di template per i bean: contenitori di dati.
 */
public abstract class ModelBean implements Serializable
{
    protected int id;
    protected String createdat;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCreatedat()
    {
        return createdat;
    }

    public void setCreatedat(String createdat)
    {
        this.createdat = createdat;
    }


}
