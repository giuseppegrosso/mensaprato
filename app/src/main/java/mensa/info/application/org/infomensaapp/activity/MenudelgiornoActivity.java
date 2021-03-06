package mensa.info.application.org.infomensaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.service.MenuDelGiornoService;
import mensa.info.application.org.infomensaapp.service.interfaces.DownloadAbstractService;
import mensa.info.application.org.infomensaapp.service.interfaces.DownloadResultReceiver;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Login;
import mensa.info.application.org.infomensaapp.sql.model.Menu;

public class MenudelgiornoActivity extends AbstractActivity implements DownloadResultReceiver.Receiver
{

    private DownloadResultReceiver mReceiver;
    private static final String URL_SERVER = "http://10.2.2.10:8080/help/"; //Giuseppe.

    private Calendar menu_date = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sdfDbase = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfHuman = new SimpleDateFormat("EEEE dd/MM/yyyy");

    private DatabaseHelper db = null;
    static final String STATE_DATE = "state_date";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null)
        {
            // Restore value of members from saved state
            menu_date = Calendar.getInstance();
            menu_date.setTimeInMillis(savedInstanceState.getLong(STATE_DATE));
        }
        setContentView(R.layout.activity_menudelgiorno);
        setSupportedToolBar();

        this.mReceiver = new DownloadResultReceiver(new Handler());
        this.mReceiver.setReceiver(this);

        // se ci sono prendo i dati dalla banca dati
        this.db = new DatabaseHelper(this.getApplicationContext());

        startMenuManager();

        // aggancio gli handler.
        Button avanti = (Button) findViewById(R.id.next);
        Button indietro = (Button) findViewById(R.id.previous);
        Button btnDate = (Button) findViewById(R.id.title);

        avanti.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getMenu_date().add(Calendar.DATE, 1);
                startMenuManager();
            }
        });
        indietro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getMenu_date().add(Calendar.DATE, -1);
                startMenuManager();
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new SimpleCalendarDialogFragment().show(getSupportFragmentManager(), "test-simple-calendar");
            }
        });
    }

    public class SimpleCalendarDialogFragment extends DialogFragment implements OnDateSelectedListener
    {
         @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            return inflater.inflate(R.layout.dialog_basic, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState)
        {
            super.onViewCreated(view, savedInstanceState);

            MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);

            widget.setOnDateChangedListener(this);
        }

        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
        {
            setMenu_date(date.getDate());
            startMenuManager();
            this.dismiss();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // salvo il dato della data.
        savedInstanceState.putLong(STATE_DATE, menu_date.getTimeInMillis());

        // richiamo il metodo nativo.
        super.onSaveInstanceState(savedInstanceState);
    }

    private void startMenuManager()
    {
        final TextView mTextView = (TextView) findViewById(R.id.title);

        mTextView.setText(sdfHuman.format(menu_date.getTime()));
        mTextView.setVisibility(TextView.VISIBLE);

        startIntent(this.getApplicationContext());
    }

    public void startIntent(Context context)
    {
        // recupero il codice fiscale dalla login.
        String cf = Menu.PASTO_NORMALE;
        Login lLogin = db.getLoginDefault();
        if (lLogin != null && lLogin.getCf().length() > 0)
            cf = lLogin.getCf();
        /* Starting Download Service */
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, MenuDelGiornoService.class);
        /* Send optional extras to Download IntentService */
        intent.putExtra("data", sdfDbase.format(menu_date.getTime()));
        intent.putExtra("cf", cf);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 102);
        intent.putExtra("url", URL_SERVER + "mensa?step=getMenuGiorno&data=" + sdf.format(menu_date.getTime()) + "&cf=" + intent.getStringExtra("cf"));

        startService(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData)
    {
        final TextView mTextView = (TextView) findViewById(R.id.title);

        switch (resultCode)
        {
            case DownloadAbstractService.STATUS_RUNNING:
                // rendo visibile la barra indeterminata
                addProgressBar(true);
                mTextView.setVisibility(TextView.VISIBLE);
                break;
            case DownloadAbstractService.STATUS_FINISHED:
                // nascondo la progressbar
                addProgressBar(false);
                Object objallMenu = null;
                try
                {
                    objallMenu = bytes2Object((byte[]) resultData.get("result"));
                } catch (IOException e)
                {
                    e.printStackTrace();
                } catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                List<Menu> allMenu = (List<mensa.info.application.org.infomensaapp.sql.model.Menu>) objallMenu;

                // TODO da capire come fare per fare un adapter.
                final ListView mListView = (ListView) findViewById(R.id.pastoList);

                String[] results = new String[allMenu.size()];

                for (int i = 0; i < allMenu.size(); i++)
                    results[i] = allMenu.get(i).getDescrizione();


                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
                mListView.setAdapter(adapter);


                break;
            case DownloadAbstractService.STATUS_ERROR:
                // nascondo la progressbar
                addProgressBar(false);
                /* in caso di errore mostro un messaggio */
                makeToast(resultData.getString(Intent.EXTRA_TEXT));
                break;
        }
    }

    public Calendar getMenu_date()
    {
        return menu_date;
    }

    public void setMenu_calendar(Calendar menu_date)
    {
        this.menu_date = menu_date;
    }

    public void setMenu_date(java.util.Date mydate)
    {
        this.menu_date.setTime(mydate);
    }

}
