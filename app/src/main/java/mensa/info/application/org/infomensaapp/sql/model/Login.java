package mensa.info.application.org.infomensaapp.sql.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Creato da Giuseppe Grosso in data 18/11/15.
 */
public class Login extends ModelBean implements Parcelable
{
    private String data;
    private String cf;
    private String ci;

    public Login()
    {
        this(0);
    }

    public Login(int id)
    {
        this(id, null);
    }

    public Login(int id, String data)
    {
        this(id, data, null);
    }

    public Login(int id, String data, String cf)
    {
        this(id, data, cf, null);
    }

    public Login(int id, String data, String cf, String ci)
    {
        this.id = id;
        this.data = data;
        this.cf = cf;
        this.ci = ci;
    }

    public Login(String cf, String ci)
    {
        this(0, null, cf, ci);
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

    public String getCi()
    {
        return this.ci;
    }

    public void setCi(String ci)
    {
        this.ci = ci;
    }


    protected Login(Parcel in)
    {
        data = in.readString();
        cf = in.readString();
        ci = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(data);
        dest.writeString(cf);
        dest.writeString(ci);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>()
    {
        @Override
        public Login createFromParcel(Parcel in)
        {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size)
        {
            return new Login[size];
        }
    };
}