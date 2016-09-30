package mensa.info.application.org.infomensaapp.service.interfaces;

import java.io.IOException;

/**
 * Creato da Giuseppe Grosso in data 13/11/15.
 */
public interface DownloadInterfaceService
{
    // successivamente effettuare ritorno di oggetto json.
    Object downloadData(String requestUrl) throws IOException, Exception;
}
