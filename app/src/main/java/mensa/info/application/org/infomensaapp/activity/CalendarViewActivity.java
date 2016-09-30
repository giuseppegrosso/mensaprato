package mensa.info.application.org.infomensaapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.service.PresenzeMensiliService;
import mensa.info.application.org.infomensaapp.service.interfaces.DownloadAbstractService;
import mensa.info.application.org.infomensaapp.service.interfaces.DownloadResultReceiver;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Login;
import mensa.info.application.org.infomensaapp.sql.model.Menu;
import mensa.info.application.org.infomensaapp.sql.model.Presenza;

public class CalendarViewActivity extends AbstractActivity implements DownloadResultReceiver.Receiver
{

    private static final String URL_SERVER = "http://10.2.2.10:8080/mensa/"; //Giuseppe.

    private Date menu_date = Calendar.getInstance().getTime();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sdfHuman = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdfDbase = new SimpleDateFormat("yyyy-MM-dd");

    private DownloadResultReceiver mReceiver = null;

    private DatabaseHelper db = null;


    @Bind(R.id.calendarView)
    MaterialCalendarView widget;

    int currentTileSize;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        setSupportedToolBar();

        // se ci sono prendo i dati dalla banca dati
        this.db = new DatabaseHelper(this.getApplicationContext());

        ButterKnife.bind(this);

        /* Starting Download Service */
        mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        currentTileSize = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;

        int color = Color.parseColor("#CE93D8");
        widget.setSelectionColor(color);
        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        widget.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large);
        widget.setDateTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        widget.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        widget.clearSelection();

        startIntent(Calendar.getInstance());

        widget.setOnMonthChangedListener(new OnMonthChangedListener()
        {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date)
            {
                startIntent(date.getCalendar());
            }
        });

    }


    public void startIntent(Calendar date)
    {
        widget.clearSelection();

        setProgressBarIndeterminateVisibility(true);
        // recupero il codice fiscale dalla login.
        String cf = Menu.PASTO_NORMALE;
        Login lg = db.getLoginDefault();
        if (lg != null && lg.getCf().length() > 0)
            cf = lg.getCf();
        /* Starting Download Service */
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, PresenzeMensiliService.class);
        /* Send optional extras to Download IntentService */
        intent.putExtra("data", date.getTimeInMillis());
        intent.putExtra("cf", cf);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 112);
        String url = URL_SERVER + "mensa?step=getPresenzeMensili&bean__data=" + date.getTimeInMillis() + "&bean__cf=" + intent.getStringExtra("cf");
        intent.putExtra("url", url);
        startService(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData)
    {
        final TextView mTextView = (TextView) findViewById(R.id.title);

        switch (resultCode)
        {
            case DownloadAbstractService.STATUS_RUNNING:
                addProgressBar(true);
                break;
            case DownloadAbstractService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                addProgressBar(false);

                Calendar calendar = null;
                List<Presenza> dates = new ArrayList<>();

                Object objallPresenze = null;
                try
                {
                    objallPresenze = bytes2Object((byte[]) resultData.get("result"));
                } catch (IOException e)
                {
                    e.printStackTrace();
                } catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                dates = (List<Presenza>) objallPresenze;

                for (int i = 0; i < dates.size(); i++)
                {
                    widget.setDateSelected(dates.get(i).getData(), true);
                }

                break;
            case DownloadAbstractService.STATUS_ERROR:
                addProgressBar(false);
                String error = resultData.getString(Intent.EXTRA_TEXT);
                makeToast(error);
                break;
        }
    }

}
