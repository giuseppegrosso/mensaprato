package mensa.info.application.org.infomensaapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Login;

/**
 * Creato da Giuseppe Grosso in data 10/12/15.
 */

public class AbstractActivity extends AppCompatActivity
{
    private Login lg = null;
    private ProgressBar bar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.lg = getLoginDefault();
        this.bar = (ProgressBar) findViewById(R.id.progress);
    }

    protected void addProgressBar(boolean visible)
    {
        if (this.bar==null) this.bar = (ProgressBar) findViewById(R.id.progress);
        if (visible)
            this.bar.setVisibility(View.VISIBLE);
        else
            this.bar.setVisibility(View.GONE);
    }

    protected void setSupportedToolBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Login getLoginDefault()
    {
        // dalle banca dati vado a prendere i dati se esistono
        // non propongo la login altrimenti si.
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());

        return db.getLoginDefault();

    }

    public Login getLogin()
    {
        return this.lg;
    }


    public void makeToast(String text, boolean b)
    {
        Toast.makeText(getApplicationContext(), text, (b ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG)).show();
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
