package mensa.info.application.org.infomensaapp.activity;

import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;

public class MensaMainTabActivity extends AbstractActivity
{

    private DrawerLayout drawer = null;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private int[] tabIcons = {
            R.drawable.ic_action_menu,
//            R.drawable.ic_action_cal,
            R.drawable.ic_action_pay
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mensa_tab_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!isLoginActivated())
            startActivity(new Intent(this, LoginActivity.class));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startEmailIntent();
            }
        });
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

    }


    private boolean isLoginActivated()
    {
        // dalle banca dati vado a prendere i dati se esistono
        // non propongo la login altrimenti si.
        if (getLogin() == null)
        {
            DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
            return db.isLogin();
        } else
            return (getLogin() != null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mensa_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
//            return true;
            startActivity(new Intent(this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item)
//    {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_menugiorno)
//        {
////            startMenuManager();/**/
//            startActivity(new Intent(this, MenudelgiornoActivity.class));
//        } else if (id == R.id.nav_login)
//        {
//            startActivity(new Intent(this, LoginActivity.class));
//        } else if (id == R.id.nav_presenze)
//        {
//            startActivity(new Intent(this, CalendarViewActivity.class));
//        } else if (id == R.id.nav_conto)
//        {
////            startNotifiche();
//
//            startActivity(new Intent(this, EstrattoContoActivity.class));
//        } else if (id == R.id.nav_settings)
//        {
////            startActivity(new Intent(this, SettingsActivity.class));
//        } else if (id == R.id.nav_email)
//        {
////            copyDatabase();
//            startEmailIntent();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


//    public void copyDatabase()
//    {
//        makeToast("connessione: " + isConnectionActivate());
//        try
//        {
//            File sd = Environment.getExternalStorageDirectory();
//            File data = Environment.getDataDirectory();
//
//            if (sd.canWrite())
//            {
//                String currentDBPath = "data/mensa.info.application.org.infomensaapp/databases/mensaApp";
//
//                String backupDBPath = "Download/copiaMensaApp";
//                File currentDB = new File(data, currentDBPath);
//                File backupDB = new File(sd, backupDBPath);
//
//                if (currentDB.exists())
//                {
//                    FileChannel src = new FileInputStream(currentDB).getChannel();
//                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                    dst.transferFrom(src, 0, src.size());
//                    src.close();
//                    dst.close();
//                }
//                makeToast("Backup Complete");
//            }
//        } catch (Exception e)
//        {
//            Log.w("Settings Backup", e);
//        } finally
//        {
//            Log.w("Fine copia", "fine copia ");
//        }
//    }

//    private boolean isConnectionActivate()
//    {
//        ConnectivityManager cm =
//                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//
//        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
//
//        if (isWiFi)
//            makeToast("connessione wifi: " + isWiFi);
//
//        return isConnected;
//    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed()
    {
        if (doubleBackToExitPressedOnce)
        {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        makeToast("Premi ancora Esc per uscire...");
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    /**
     * metodi di servizio
     */


    // notifiche
//    public void startNotifiche()
//    {
//        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Intent notificationIntent = new Intent(MensaMainTabActivity.this, DownloadResultReceiver.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(MensaMainTabActivity.this, 0, notificationIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//
//        NotificationManager nm = (NotificationManager) this
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Resources res = this.getResources();
//        Notification.Builder builder = new Notification.Builder(this);
//
//        builder.setContentIntent(contentIntent)
//                .setSmallIcon(R.drawable.typeb_calendar_today)
//                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.typeb_calendar_today))
//                .setTicker(res.getString(R.string.app_name))
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true)
//                .setSound(soundUri)
//                .setContentTitle("Aggiornamento xxxx")
//                .setContentText("ci sono nuovi aggiornamenti per te!");
//
//        Notification n = builder.build();
//
//        nm.notify(11223, n);
//
//    }

    public void startEmailIntent()
    {
        MailTo mt = MailTo.parse("mailto:giuseppe.ing.grosso@gmail.com");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
        sendIntent.putExtra(Intent.EXTRA_TEXT, "");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "oggetto: ");
        sendIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(sendIntent, "Invia un messaggio:"));
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0)
            {
                return MenudelgiornoFragment.newInstance(position + 1);
            }
//            else if (position == 1)
//            {
//                return MenudelgiornoFragment.newInstance(position + 1);
//            }
            else if (position == 1)
            {
                return EstrattoContoFragment.newInstance(position + 1);
            }

            return null;
        }

        @Override
        public int getCount()
        {
            // Show 3 total pages.
            return 2;
        }


    }

}