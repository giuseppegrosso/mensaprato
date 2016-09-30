package mensa.info.application.org.infomensaapp.service.interfaces;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Creato da Giuseppe Grosso in data 13/11/15.
 */
public class DownloadResultReceiver extends ResultReceiver
{

    private Receiver mReceiver;

    public DownloadResultReceiver(Handler handler)
    {
        super(handler);
    }

    public void setReceiver(Receiver receiver)
    {
        mReceiver = receiver;
    }

    public interface Receiver
    {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData)
    {
        if (mReceiver != null)
        {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}

