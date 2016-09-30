package mensa.info.application.org.infomensaapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mensa.info.application.org.infomensaapp.R;
import mensa.info.application.org.infomensaapp.sql.helper.DatabaseHelper;
import mensa.info.application.org.infomensaapp.sql.model.Login;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AbstractActivity
{

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private TextView mCfView;
    private TextView mCiView;

    private View mProgressView;
    private View mLoginFormView;

    private DatabaseHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the login form.
        mCfView = (TextView) findViewById(R.id.cf_genitore);
        mCiView = (TextView) findViewById(R.id.ci_genitore);

        // se ci sono prendo i dati dalla banca dati
        this.db = new DatabaseHelper(this.getApplicationContext());
        if (this.db.isLogin())
        {
            Login logins = this.db.getLoginDefault();
            if (logins != null && logins.getCf().length() > 0)
            {
                mCfView.setText(logins.getCf());
                mCiView.setText(logins.getCi());
            }
        }

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Cerco di effettuare il login se trovo errori es. campi non riempiti
     * o con dati non consistenti segnalo l'errore.
     */
    private void attemptLogin()
    {
        if (mAuthTask != null)
        {
            return;
        }

        // Reset errors.
        mCfView.setError(null);
        mCiView.setError(null);

        // Store values at the time of the login attempt.
        String cfGen = mCfView.getText().toString();
        String ciGen = mCiView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // controllo cf e ci se inseriti
        if (TextUtils.isEmpty(cfGen))
        {
            mCfView.setError("Il codice Fiscale non può essere vuoto");
            focusView = mCfView;
            cancel = true;
        }
        if (TextUtils.isEmpty(ciGen))
        {
            mCiView.setError("La carta identità non può essere vuota");
            focusView = mCiView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(cfGen) && !TextUtils.isEmpty(ciGen))
        {
            if (!isCfValid(cfGen))
            {
                mCfView.setError("Il codice Fiscale deve essere di 16 caratteri");
                focusView = mCfView;
                cancel = true;
            }
            if (!isCiValid(ciGen))
            {
                mCiView.setError("La carta di identità non è valida");
                focusView = mCfView;
                cancel = true;
            }
        }


        if (cancel)
        {
            // se c'e' un errore metto il fuoco sul campo.
            focusView.requestFocus();
        } else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(this.db, cfGen, ciGen);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isCfValid(String codiceFiscale)
    {
        return codiceFiscale.length() >= 16;
    }

    private boolean isCiValid(String cartaIdentita)
    {
        return cartaIdentita.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {

        private final DatabaseHelper mDb;
        private final String mCf;
        private final String mCi;

        UserLoginTask(DatabaseHelper db, String cf, String ci)
        {
            this.mDb = db;
            this.mCf = cf;
            this.mCi = ci;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            Login logins = mDb.getLoginDefault();
            if (logins != null && logins.getCf().length() > 0)
            {
                Login login = logins;
                login.setCi(this.mCi);
                login.setCf(this.mCf);
                this.mDb.updateLogin(login);
            } else
            {
                this.mDb.createLogin(mCf, mCi);
            }

            //

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            mAuthTask = null;
            showProgress(false);

            if (success)
            {
                finish();
            } else
            {
                // vedere cosa fare.
            }
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

