package mensa.info.application.org.infomensaapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Login;

/**
 * Creato da Giuseppe Grosso in data 10/12/15.
 */

public abstract class AbstractFragment extends Fragment
{

    protected View rootView;
    private Login lg = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.lg = getLoginDefault();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private Login getLoginDefault()
    {
        // dalle banca dati vado a prendere i dati se esistono
        // non propongo la login altrimenti si.
        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());

        return db.getLoginDefault();

    }

    public Login getLogin()
    {
        return this.lg;
    }

    public void makeToast(String text, boolean b)
    {
        Toast.makeText(this.getActivity().getApplicationContext(), text, (b ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG)).show();
    }

    public void makeToast(String text)
    {
        makeToast(text, true);
    }

    /**
     * Converting byte arrays to objects
     */
    static public Object bytes2Object(byte raw[])
            throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(raw);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = ois.readObject();
        return o;
    }
}
