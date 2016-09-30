package mensa.info.application.org.infomensaapp.sql.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mensa.info.application.org.infomensaapp.sql.model.Login;
import mensa.info.application.org.infomensaapp.sql.model.Menu;
import mensa.info.application.org.infomensaapp.sql.model.Presenza;

/**
 * Creato da Giuseppe Grosso in data 16/11/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mensaApp";

    // Table Names
    private static final String TABLE_MENU = "menu";
    private static final String TABLE_LOGIN = "login";
    private static final String TABLE_PRESENZE = "presenze";


    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // campi della tabella Menu
    private static final String DATA_MENU = "data";
    private static final String CF_MENU = "cf";
    private static final String DESC_MENU = "desc";
    private static final String CONSISTENTE_MENU = "consistente";

    // campi della tabella login
    private static final String DATA_LOGIN = "data";
    private static final String CF_LOGIN = "cf";
    private static final String CI_LOGIN = "ci";

    // campi della tabella presenze
    private static final String DATA_PRESENZE = "data";
    private static final String CF_PRESENZE = "cf";
    private static final String PRESENZA_PRESENZE = "presenza";


    // Creazione tabella
    // Menu statement di creazione
    private static final String CREATE_TABLE_MENU = "CREATE TABLE "
            + TABLE_MENU + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DATA_MENU
            + " DATE," + CF_MENU + " TEXT," + DESC_MENU + " TEXT," + CONSISTENTE_MENU + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "
            + TABLE_LOGIN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DATA_LOGIN
            + " DATE," + CF_LOGIN + " TEXT," + CI_LOGIN + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_PRESENZE = "CREATE TABLE "
            + TABLE_PRESENZE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DATA_PRESENZE
            + " DATE," + CF_PRESENZE + " TEXT," + PRESENZA_PRESENZE + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void caricaPasti_Dati2015_2016(SQLiteDatabase db)
    {
        // SETTEMBRE
        createMenu(db, "2015-09-21", new String[]{"PASTA AL POMODORO BIO", "FRITTATA BIO", "PISELLINI ALL'OLIO BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2015-09-22", new String[]{"PASSATO DI VERDURE CON PASTA", "PASTA PROSCIUTTO COTTO", "INSALATA VERDE CON MAIS", "BANANA"});
        createMenu(db, "2015-09-23", new String[]{"TORTELLINI ROSE'", "PETTO DI POLLO AL LATTE", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2015-09-24", new String[]{"RISO ALL'OLIO", "POLPETTE DI MANZO AL POMODORO", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2015-09-25", new String[]{"PASTA AL PESTO", "PARMIGIANO REGGIANO", "INSALATA DI POMODORI", "SUCCO DI FRUTTA"});
        createMenu(db, "2015-09-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-09-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-09-28", new String[]{"PASTA AL BURRO", "TONNO SOTT'OLIO / HALIBUT GRATINATO", "POMODORI INSALATATRI", "FRUTTA FRESCA"});
        createMenu(db, "2015-09-29", new String[]{"RISO AL POMODORO", "RICOTTA FILIERA CORTA", "INSALATA MISTA", "FRUTTA FRESCA"});
        createMenu(db, "2015-09-30", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "COSCE DI POLLO ARROSTO", "CAROTE FILANGE'", "BANANA"});
        // OTTOBRE

        // NOVEMBRE
        createMenu(db, "2015-11-23", new String[]{"PASSATO DI VERDURA CON RISO", "MERLUZZO AROMATIZZATO CON MAIONESE", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2015-11-24", new String[]{"LASAGNE AL RAGU'", "PARMIGIANO REGGIANO", "INSALATA VERDE", "FRUTTA FRESCA"});
        createMenu(db, "2015-11-25", new String[]{"PASTA AL TONNO", "PROSCIUTTO COTTO", "SPINACI SALTATI", "BANANA"});
        createMenu(db, "2015-11-26", new String[]{"PASTA AL BURRO", "HAMBURGER DI MANZO CON POMODORO", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2015-11-27", new String[]{"CREMA DI PATATE E CAROTE CON RISO", "PETTO DI POLLO ALLA SALVIA", "PISELLI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2015-11-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-11-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-11-30", new String[]{"PASTA ALL'AMATRICIANA", "PECORINO DOP", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA"});

        // DICEMBRE
        createMenu(db, "2015-12-01", new String[]{"PASTINA IN BRODO/PASTA AL POMODORO**", "ROLLATA DI TACCHINO CON MORTADELLA", "BIETOLA SALTATA", "BANANA"});
        createMenu(db, "2015-12-02", new String[]{"RISOTTO DI ZUCCA", "BOCCONCINI DI POLLO FRITTO", "INSALATA VERDE CON MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2015-12-03", new String[]{"TAGLIATELLE AL RAGU'", "POLPETTE VEGETALI", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2015-12-04", new String[]{"PASS. DI VERDURA CON ORZO'", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "TORTINA"});
        createMenu(db, "2015-12-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-07", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-08", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-09", new String[]{"CAPPELLINI IN BRODO", "FRITTATA", "TRIS DI VERDURE AL VAPORE", "BANANA"});
        createMenu(db, "2015-12-10", new String[]{"PASSATO DI VERDURA CON CROSTINI/RISO**", "COSCE DI POLLO ARROSTO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA"});
        createMenu(db, "2015-12-11", new String[]{"PASTA AL RAGU' DI MANZO", "STRACCHINO", "INSALATA MISTA", "BUDINO"});
        createMenu(db, "2015-12-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-14", new String[]{"PASTA AL POMODORO", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2015-12-15", new String[]{"RISOTTO AI PORRI", "ARISTA ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2015-12-16", new String[]{"RAVIOLI BURRO E SALVIA", "SCALOPPE DI POLLO ALL'ARANCIA", "PATATINE PAI", "PANETTONE"});
        createMenu(db, "2015-12-17", new String[]{"PASS. DI CECI E PASTA BIO", "BOCCONCINI DI MOZZARELLA BIO", "BIETOLA SALTATA BIO", "BANANA BIO"});
        createMenu(db, "2015-12-18", new String[]{"PASTA AL POMODORO E RICOTTA", "POLPETTONE DI SHREK", "TRIS DI VERDURE AL VAPORE", "SUCCO DI FRUTTA"});
        createMenu(db, "2015-12-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-20", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-21", new String[]{"PASSATO DI VERDURA CON RISO", "MERLUZZO AROMATIZZATO CON MAIONESE", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2015-12-22", new String[]{"PASTA AL POMODORO", "PARMIGIANO REGGIANO", "FAGIOLINI ALL'OLIO", "SUCCO DI FRUTTA"});
        createMenu(db, "2015-12-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-30", new String[]{"NON PREVISTO"});
        createMenu(db, "2015-12-31", new String[]{"NON PREVISTO"});

        // GENNAIO 2016!!!!
        createMenu(db, "2016-01-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-03", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-07", new String[]{"PASTA AL BURRO", "MANZO ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-08", new String[]{"CREMA PATATE E CAROTE CON RISO", "PETTO DI POLLO ALLA SALVIA", "PISELLINI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-09", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-10", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-11", new String[]{"PASTA ALL'AMATRICIANA", "PECORINO DOP", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-12", new String[]{"PASTINA IN BRODO/PASTA AL POMODORO**", "ROLLATA DI TACCHINO CON MORTADELLA", "BIETOLA SALTATA", "BANANA"});
        createMenu(db, "2016-01-13", new String[]{"RISOTTO DI ZUCCA", "BOCCONCINI DI POLLO FRITTO", "INSALATA VERDE CON MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-14", new String[]{"TAGLIATELLE AL RAGU'", "POLPETTE VEGETALI", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-15", new String[]{"PASS. DI VERDURA CON ORZO'", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "TORTINA"});
        createMenu(db, "2016-01-16", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-17", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-18", new String[]{"RISO AL POMODORO", "TONNO SOTT'OLIO/MERLUZZO AL FORNO*", "CAROTE FILANGE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-19", new String[]{"PASTA ALL'OLIO", "MANZO ARROSTO AL POMODORO", "CROCCHETTE DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-20", new String[]{"CAPPELLETTI IN BRODO", "FRITTATA", "TRIS DI VERDURE AL VAPORE", "BANANA"});
        createMenu(db, "2016-01-21", new String[]{"PASSATO DI VERDURA CON CROSTINI/RISO", "COSCE DI POLLO ARROSTO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-22", new String[]{"PASTA AL RAGU' DI MANZO", "STRACCHINO", "INSALATA MISTA", "BUDINO"});
        createMenu(db, "2016-01-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-25", new String[]{"PASTA AL POMODORO", "PLATESSA FRITTA", "CAROTE ALL'OLIO", "BANANA"});
        createMenu(db, "2016-01-26", new String[]{"PASTA AL BURRO", "PETTO DI POLLO ALLA MUGNAIA", "INSALATA VERDE CON MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-27", new String[]{"LASAGNE AL RAGU'", "PARMIGIANO REGGIANO", "SPINACI SALTATI", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-28", new String[]{"RISOTTO AI CARCIOFI'", "TACCHINO AGLI AROMI", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-01-29", new String[]{"PASS. DI FAGIOLI E PASTA", "POLPETTE VEGETALI", "FINOCCHI E CAROTE CRUDITE'", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-01-30", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-01-31", new String[]{"NON PREVISTO"});

        
        // FEBBRAIO
        createMenu(db, "2016-02-01", new String[]{"RAVIOLI AL POMODORO", "ARISTA ARROSTO", "INSALATA MISTA", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-02", new String[]{"PASSATO DI VERDURA CON RISO", "PIZZA", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-03", new String[]{"PASTINA IN BRODO/CAPPELLETTI IN BRODO**", "HAMBURGER POLLO E TACCHINO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-04", new String[]{"PASTA AL RAGU' DI MANZO", "BOCCONCINI DI MOZZARELLA", "BIETOLA SALTATA", "CENCI"});
        createMenu(db, "2016-02-05", new String[]{"PASTA ALL'OLIO", "TONNO SOTT'OLIO", "FAGIOLI ALL'OLIO", "TORTINA"});
        createMenu(db, "2016-02-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-07", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-08", new String[]{"POLENTA AL RAGU'", "PROSCIUTTO COTTO", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-09", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-10", new String[]{"PASS. DI CECI E PASTA BIO", "STRACCHINO BIO", "SPINACI SALTATI BIO'", "BANANA BIO"});
        createMenu(db, "2016-02-11", new String[]{"RISOTTO ALLA PARMIGIANA", "BOCC. DI POLLO FRITTO", "CAROTE FILANGE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-12", new String[]{"TAGLIATELLE AL POMODORO", "MERLUZZO CON MAIONESE", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-14", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-15", new String[]{"RISOTTO DI ZUCCA", "PARMIGIANO REGGIANO", "BIETOLA SALTATA", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-16", new String[]{"CAPPELLETTI IN BRODO", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO'", "BANANA"});
        createMenu(db, "2016-02-17", new String[]{"PASTA AL POMODORO", "COSCE DI POLLO ARROSTO", "INSALATA MISTA", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-18", new String[]{"PASSATO DI VERDURA E CROSTINI/ORZO**", "BRASATO DI MANZO", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-19", new String[]{"PASTA ALLA PIZZAIOLA", "FRITTATA", "TRIS DI VERDURE AL VAPORE", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-20", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-21", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-22", new String[]{"PASTA AL POMODORO", "PLATESSA FRITTA", "CAROTE ALL'OLIO'", "BANANA"});
        createMenu(db, "2016-02-23", new String[]{"PASTA AL BURRO", "PETTO DI POLLO ALLA MUGNAIA", "INSALATA VERDE CON MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-24", new String[]{"LASAGNE AL RAGU'", "PARMIGIANO REGGIANO", "SPINACI SALTATI", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-25", new String[]{"RISOTTO AI CARCIOFI", "TACCHINO AGLI AROMI", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-02-26", new String[]{"PASS. DI FAGIOLI E PASTA", "POLPETTE VEGETALI", "FINOCCHI E CAROTE CRUDITE'", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-02-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-02-29", new String[]{"RAVIOLI AL POMODORO", "ARISTA ARROSTO", "INSALATA MISTA", "FRUTTA FRESCA"});

        // MARZO
        createMenu(db, "2016-03-01", new String[]{"PASSATO DI VERDURA CON RISO", "PIZZA", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-02", new String[]{"PASTINA IN BRODO/CAPPELLETTI IN BRODO**", "HAMBURGER POLLO E TACCHINO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-03", new String[]{"PASTA AL RAGU' DI MANZO", "BOCCONCINI DI MOZZARELLA", "BIETOLA SALTATA'", "BANANA"});
        createMenu(db, "2016-03-04", new String[]{"PASTA ALL'OLIO", "TONNO SOTT'OLIO", "FAGIOLI ALL'OLIO", "TORTINA"});
        createMenu(db, "2016-03-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-07", new String[]{"POLENTA AL RAGU'", "PROSCIUTTO COTTO", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-08", new String[]{"PASTA ALL'OLIO", "LONZA DI MAIALE AL LIMONE", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-09", new String[]{"PASSATO DI CECI CON PASTA BIO", "STRACCHINO BIO", "SPINACI SALTATI BIO", "BANANA BIO"});
        createMenu(db, "2016-03-10", new String[]{"RISOTTO ALLA PARMIGIANA", "BOCC. DI POLLO FRITTO", "CAROTE FILANGE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-11", new String[]{"TAGLIATELLE AL POMODORO", "MERLUZZO CON MAIONESE", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-14", new String[]{"RISOTTO DI ZUCCA", "PARMIGIANO REGGIANO", "BIETOLA SALTATA", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-15", new String[]{"CAPPELLETTI IN BRODO", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO'", "BANANA"});
        createMenu(db, "2016-03-16", new String[]{"PASTA AL POMODORO", "COSCE DI POLLO ARROSTO", "INSALATA MISTA", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-17", new String[]{"PASSATO DI VERDURA E CROSTINI/ORZO**", "BRASATO DI MANZO", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-18", new String[]{"PASTA ALLA PIZZAIOLA", "FRITTATA", "TRIS DI VERDURE AL VAPORE", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-20", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-21", new String[]{"PASTA AL POMODORO", "PLATESSA FRITTA", "CAROTE ALL'OLIO'", "CIOCCOLATINA AL LATTE BIO"});
        createMenu(db, "2016-03-22", new String[]{"PASTA AL BURRO", "PETTO DI POLLO ALLA MUGNAIA", "INSALATA VERDE CON MAIS", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-03-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-03-30", new String[]{"PASTINA IN BRODO/CAPPELLETTI IN BRODO**", "HAMBURGER POLLO E TACCHINO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA"});
        createMenu(db, "2016-03-31", new String[]{"PASTA AL RAGU' DI MANZO", "BOCCONCINI DI MOZZARELLA", "BIETOLA SALTATA'", "BANANA"});

        // APRILE
        createMenu(db, "2016-04-01", new String[]{"PASTA ALL'OLIO", "TONNO SOTT'OLIO", "FAGIOLI ALL'OLIO", "TORTINA"});
        createMenu(db, "2016-04-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-03", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-04", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-05", new String[]{"PASTA AL POMODORO", "BOCCONCINI DI POLLO FRITTO", "CAROTE FILANGE'", "BANANA"});
        createMenu(db, "2016-04-06", new String[]{"RAVIOLI BURRO E SALVIA", "MERLUZZO ALLA LIVORNESE", "FAGIOLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
        createMenu(db, "2016-04-07", new String[]{"BRUSCHETTA AL POMODORO BIO", "BOCCONCINI DI MOZZARELLA BIO", "INSALATA VERDE BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-04-08", new String[]{"RISOTTO DI CAROTE", "POLPETTE DI MANZO AL POMODORO", "ZUCCHINE TRIFOLATE", "BUDINO"});
        createMenu(db, "2016-04-09", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-10", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-11", new String[]{"GNOCCHI AL POMODORO", "EMMENTHAL E MORTADELLA", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-12", new String[]{"PASTA ALLA PIZZAIOLA", "COSCE DI POLLO ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-13", new String[]{"PASTA FREDDA AL TONNO/PASTA ALL'OLIO", "UOVA SODE CON MAIONESE", "INSALATA E POMODORI", "BANANA"});
        createMenu(db, "2016-04-14", new String[]{"CREMA DI VERDURE E RISO", "LONZA DI MAIALE AL LIMONE", "CAROTE FILANGE' E MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-15", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLI ALL'OLIO", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-04-16", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-17", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-18", new String[]{"PASTA AL POMODORO E BASILICO", "TONNO SOTT'OLIO", "FAGIOLI VERDI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-19", new String[]{"PASTA AL BURRO", "POLPETTONE DI MANZO CON POMODORO", "ZUCHINE TRIFOLATE", "BANANA"});
        createMenu(db, "2016-04-20", new String[]{"CREMA DI CAROTE E PISELLI E CROSTINI/RISO**", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-21", new String[]{"LASAGNE AL PESTO", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-22", new String[]{"RISO AL POMODORO", "SPEZZATINO DI TACCHINO AGLI AGRUMI", "INSALATA VERDE CON MAIS", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-04-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-04-26", new String[]{"PASTA AL TONNO", "FRITTATA", "PISELLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
        createMenu(db, "2016-04-27", new String[]{"PASTA AL PESTO", "PETTO DI POLLO ALLA SALVIA", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-04-28", new String[]{"INSALATA DI RISO/RISO AL POMODORO*", "BASTONCINI DI PESCE", "INSALATA E POMODORI", "BANANA"});
        createMenu(db, "2016-04-29", new String[]{"PASTA ALL'OLIO", "RICOTTA FILIERA CORTA", "RATATOUILLE DI VERDURA/FAGIOLINI ALL'OLIO*", "TORTINA"});
        createMenu(db, "2016-04-30", new String[]{"NON PREVISTO"});

        // MAGGIO
        createMenu(db, "2016-05-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-02", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-03", new String[]{"PASTA AL POMODORO", "BOCCONCINI DI POLLO FRITTO", "CAROTE FILANGE'", "BANANA"});
        createMenu(db, "2016-05-04", new String[]{"RAVIOLI BURRO E SALVIA", "MERLUZZO ALLA LIVORNESE", "FAGIOLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
        createMenu(db, "2016-05-05", new String[]{"BRUSCHETTA AL POMODORO BIO", "BOCCONCINI DI MOZZARELLA BIO", "INSALATA VERDE BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-05-06", new String[]{"RISOTTO DI CAROTE", "POLPETTE DI MANZO AL POMODORO", "ZUCCHINE TRIFOLATE", "BUDINO"});
        createMenu(db, "2016-05-07", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-08", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-09", new String[]{"GNOCCHI AL POMODORO", "EMMENTHAL E MORTADELLA", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-10", new String[]{"PASTA ALLA PIZZAIOLA", "COSCE DI POLLO ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-11", new String[]{"PASTA FREDDA AL TONNO/PASTA ALL'OLIO", "UOVA SODE CON MAIONESE", "INSALATA E POMODORI", "BANANA"});
        createMenu(db, "2016-05-12", new String[]{"CREMA DI VERDURE E RISO", "LONZA DI MAIALE AL LIMONE", "CAROTE FILANGE' E MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-13", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLI ALL'OLIO", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-05-14", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-15", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-16", new String[]{"PASTA AL POMODORO E BASILICO", "TONNO SOTT'OLIO", "FAGIOLI VERDI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-17", new String[]{"PASTA AL BURRO", "POLPETTONE DI MANZO CON POMODORO", "ZUCHINE TRIFOLATE", "BANANA"});
        createMenu(db, "2016-05-18", new String[]{"CREMA DI CAROTE E PISELLI E CROSTINI/RISO**", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-19", new String[]{"LASAGNE AL PESTO", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-20", new String[]{"RISO AL POMODORO", "SPEZZATINO DI TACCHINO AGLI AGRUMI", "INSALATA VERDE CON MAIS", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-05-21", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-22", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-23", new String[]{"TAGLIATELLE PAGLIA E FIENO AL POMODORO", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-24", new String[]{"PASTA AL TONNO", "FRITTATA", "PISELLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
        createMenu(db, "2016-05-25", new String[]{"PASTA AL PESTO", "PETTO DI POLLO ALLA SALVIA", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-26", new String[]{"INSALATA DI RISO/RISO AL POMODORO*", "BASTONCINI DI PESCE", "INSALATA E POMODORI", "BANANA"});
        createMenu(db, "2016-05-27", new String[]{"PASTA ALL'OLIO", "RICOTTA FILIERA CORTA", "RATATOUILLE DI VERDURA/FAGIOLINI ALL'OLIO*", "TORTINA"});
        createMenu(db, "2016-05-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-05-30", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-05-31", new String[]{"PASTA AL POMODORO", "BOCCONCINI DI POLLO FRITTO", "CAROTE FILANGE'", "BANANA"});

        // GIUGNO
        createMenu(db, "2016-06-01", new String[]{"RAVIOLI BURRO E SALVIA", "MERLUZZO ALLA LIVORNESE", "FAGIOLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
        createMenu(db, "2016-06-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-03", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-06", new String[]{"GNOCCHI AL POMODORO", "EMMENTHAL E MORTADELLA", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-07", new String[]{"PASTA ALLA PIZZAIOLA", "COSCE DI POLLO ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-08", new String[]{"PASTA FREDDA AL TONNO/PASTA ALL'OLIO", "UOVA SODE CON MAIONESE", "INSALATA E POMODORI", "BANANA"});
        createMenu(db, "2016-06-09", new String[]{"CREMA DI VERDURE E RISO", "LONZA DI MAIALE AL LIMONE", "CAROTE FILANGE' E MAIS", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-10", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLI ALL'OLIO", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-06-11", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-13", new String[]{"PASTA AL POMODORO E BASILICO", "TONNO SOTT'OLIO", "FAGIOLI VERDI ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-14", new String[]{"PASTA AL BURRO", "POLPETTONE DI MANZO CON POMODORO", "ZUCHINE TRIFOLATE", "BANANA"});
        createMenu(db, "2016-06-15", new String[]{"CREMA DI CAROTE E PISELLI E CROSTINI/RISO**", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-16", new String[]{"LASAGNE AL PESTO", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-17", new String[]{"RISO AL POMODORO", "SPEZZATINO DI TACCHINO AGLI AGRUMI", "INSALATA VERDE CON MAIS", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-06-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-20", new String[]{"TAGLIATELLE PAGLIA E FIENO AL POMODORO", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-21", new String[]{"PASTA AL TONNO", "FRITTATA", "PISELLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
        createMenu(db, "2016-06-22", new String[]{"PASTA AL PESTO", "PETTO DI POLLO ALLA SALVIA", "PATATE LESSE", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-23", new String[]{"INSALATA DI RISO/RISO AL POMODORO*", "BASTONCINI DI PESCE", "INSALATA E POMODORI", "BANANA"});
        createMenu(db, "2016-06-24", new String[]{"PASTA ALL'OLIO", "RICOTTA FILIERA CORTA", "RATATOUILLE DI VERDURA/FAGIOLINI ALL'OLIO*", "TORTINA"});
        createMenu(db, "2016-06-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-06-27", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA"});
        createMenu(db, "2016-06-28", new String[]{"PASTA AL POMODORO", "BOCCONCINI DI POLLO FRITTO", "CAROTE FILANGE'", "BANANA"});
        createMenu(db, "2016-06-29", new String[]{"RAVIOLI BURRO E SALVIA", "MERLUZZO ALLA LIVORNESE", "FAGIOLINI ALL'OLIO", "MACEDONIA DI FRUTTA"});
    }


    public void caricaPasti_Dati2016_2017(SQLiteDatabase db)
    {
        // SETTEMBRE
        createMenu(db, "2016-09-10", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-11", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-14", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-15", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-16", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-17", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-20", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-21", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-22", new String[]{"RISO ALL'OLIO", "FRITTATA", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-09-23", new String[]{"CREMA DI ZUCCHINE CON CROSTINI/RISO**", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-09-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-09-26", new String[]{"PASTA AL BURRO", "TONNO/HALIBUT GRATINATO*", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-09-27", new String[]{"RISO AL POMODORO", "RICOTTA FILIERA CORTA", "INSALATA MISTA", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-09-28", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "COSCE DI POLLO ARROSTO", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2016-09-29", new String[]{"PASSATO DI VERDURA CON PASTA", "MORTADELLA DI BOLOGNA IGP", "PATATE ARROSTO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-09-30", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "SPEZZATINO DI TACCHINO BIANCO", "BIETOLA SALTATA", "TORTINA DI PORRETTA"});

        // OTTOBRE
        createMenu(db, "2016-10-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-03", new String[]{"GNOCCHI AL PESTO", "ARISTA ARROSTO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-04", new String[]{"PASTA AL RAGU' DI MANZO", "UOVA SODE CON MAIONESE", "INSALATA VERDE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-05", new String[]{"PASTA AL POMODORO E RICOTTA", "POLPETTE DI LEGUMI AL FORNO", "ZUCCHINE TRIFOLATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-06", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/ORZO**", "BASTONCINI DI PESCE AL FORNO", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2016-10-07", new String[]{"RISOTTO DI CAROTE", "PETTO DI POLLO AL LIMONE", "PISELLINI ALL'OLIO", "BUDINO"});
        createMenu(db, "2016-10-08", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-09", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-10", new String[]{"PASTA ALLE MELANZANE", "TACCHINO AGLI AROMI", "SPINACI SALTATI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-11", new String[]{"TORTELLINI ROSE'", "BOCCONCINI DI MOZZARELLA", "POMODORI INSALATARI", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2016-10-12", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "FINOCCHI CRUDITE'", "BANANA BIO"});
        createMenu(db, "2016-10-13", new String[]{"INSALATA DI RISO ALL'OLIO", "BOCCONCINI DI POLLO FRITTO", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-14", new String[]{"PASTA AL POMODORO", "HAMBURGER DI PESCE", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-15", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-16", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-17", new String[]{"BRUSCHETTA AL POMODORO BIO/PASTA AL POMODORO BIO*", "FRITTATA BIO", "PISELLINI ALL'OLIO BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-18", new String[]{"PASTA AL PESTO", "HAMBURGER DI MANZO CON POMODORO", "PATATE LESSE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-19", new String[]{"TAGLIATELLE AL POMODORO", "PETTO DI POLLO AL LATTE", "CAROTE ALL'OLIO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2016-10-20", new String[]{"RISO ALL'OLIO", "POLPETTE VEGETALI AL FORNO", "INSALATA VERDE CON MAIS", "BANANA BIO"});
        createMenu(db, "2016-10-21", new String[]{"CREMA DI ZUCCHINE CON CROSTINI/RISO**", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-10-22", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-24", new String[]{"PASTA AL BURRO", "TONNO/HALIBUT GRATINATO*", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-25", new String[]{"RISO AL POMODORO", "RICOTTA FILIERA CORTA", "INSALATA MISTA", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-26", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "COSCE DI POLLO ARROSTO", "CAROTE FILANGE'", "BANANE BIO"});
        createMenu(db, "2016-10-27", new String[]{"PASSATO DI VERDURA CON PASTA", "MORTADELLA DI BOLOGNA IGP", "PATATE ARROSTO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-10-28", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "SPEZZATINO DI TACCHINO BIANCO", "BIETOLA SALTATA", "TORTINA DI PORRETTA"});
        createMenu(db, "2016-10-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-30", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-10-31", new String[]{"NON PREVISTO"});
        // NOVEMBRE
        createMenu(db, "2016-11-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-02", new String[]{"RISOTTO DI ZUCCA", "TACCHINO AGLI AROMI", "INSALATA VERDE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-03", new String[]{"TAGLIATELLE AL RAGU'", "POLPETTE VEGETALI AL FORNO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-04", new String[]{"PASSATO DI VERDURA CON ORZO", "PROSCIUTTO COTTO", "PATATE ARROSTO", "TORTINO DI PORRETTA"});
        createMenu(db, "2016-11-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-07", new String[]{"CAPPELLETTI IN BRODO", "FRITTATA", "SPINACI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-08", new String[]{"PASTA ALL'OLIO", "MANZO ARROSTO AL POMODORO", "CROCCHETTE DI PATATE AL FORNO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-09", new String[]{"RISO AL POMODORO", "TONNO/MARLUZZO OLIO E LIMONE*", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2016-11-10", new String[]{"PASSATO DI VERDURA CON CROSTINI/RISO**", "COSCE DI POLLO ARROSTO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-11", new String[]{"PASTA AL RAGU' VEGETALE", "STRACCHINO", "INSALATA MISTA", "SUCCO DI FRUTTA"});
        createMenu(db, "2016-11-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-14", new String[]{"PASTA AL POMODORO", "PLATESSA FRITTA", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-15", new String[]{"RISOTTO AI PORRI", "ARISTA ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-16", new String[]{"RAVIOLI BURRO E SALVIA", "SCALOPPE DI POLLO ALL'ARANCIA", "CAROTE FILANGE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-17", new String[]{"PASSATO DI CECI CON PASTA BIO", "BOCCONCINI DI MOZZARELLA BIO", "BIETOLA SALTATA", "BANANA BIO"});
        createMenu(db, "2016-11-18", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO", "POLPETTONE DI SHREK", "TRIS DI VERDURE CON FAGIOLINI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-20", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-21", new String[]{"PASSATO DI VERDURE CON RISO", "MERLUZZO AROMATIZZATO CON VERDURE", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-22", new String[]{"PASTA AL RAGU' DI MANZO", "PARMIGIANO REGGIANO", "FINOCCHI LESSI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-23", new String[]{"PASTA AL TONNO", "POLPETTE DI LEGUMI AL FORNO", "SPINACI SALTATI'", "BANANA BIO"});
        createMenu(db, "2016-11-24", new String[]{"PASTA AL BURRO", "HAMBURGER DI MANZO CON POMODORO", "INSALATA VERDE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-25", new String[]{"PASTA E PATATE", "PETTO DI POLLO ALLA SALVIA", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-11-28", new String[]{"PASTA AL POMODORO", "PECORINO D.O.P.", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-11-29", new String[]{"PASTINA IN BRODO VEGETALE/PASTA AL POMODORO**", "ROLLATA DI TACCHINO", "BIETOLA SALTATA", "BANANA BIO"});
        createMenu(db, "2016-11-30", new String[]{"RISOTTO DI ZUCCA", "BOCCONCINI DI POLLO FRITTO", "INSALATA VERDE", "FRUTTA FRESCA BIO"});
        // DICEMBRE
        createMenu(db, "2016-12-01", new String[]{"TAGLIATELLE AL RAGU'", "POLPETTE VEGETALI AL FORNO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-02", new String[]{"PASSATO DI VERDURE CON ORZO", "PROSCIUTTO COTTO", "PATATE ARROSTO", "TORTINA DI PORRETTA"});
        createMenu(db, "2016-12-03", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-05", new String[]{"CAPPELLETTI IN BRODO", "FRITTATA", "SPINACI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-06", new String[]{"PASTA ALL'OLIO", "MANZO ARROSTO AL POMODORO", "CROCCHETTE DI PATATE AL FORNO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-07", new String[]{"RISO AL POMODORO", "TONNO/MARLUZZO OLIO E LIMONE*", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2016-12-08", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-09", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-10", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-11", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-12", new String[]{"PASTA AL POMODORO", "PLATESSA FRITTA", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-13", new String[]{"RISOTTO AI PORRI", "ARISTA ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-14", new String[]{"RAVIOLI BURRO E SALVIA", "SCALOPPE DI POLLO ALL'ARANCIA", "PATATINE", "PANDORO"});
        createMenu(db, "2016-12-15", new String[]{"PASSATO DI CECI CON PASTA BIO", "BOCCONCINI DI MOZZARELLA BIO", "BIETOLA SALTATA BIO", "BANANA BIO"});
        createMenu(db, "2016-12-16", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO", "POLPETTONE DI SHREK", "TRIS DI VERDURE CON FAGIOLINI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-17", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-19", new String[]{"PASSATO DI VERDURE CON RISO", "MERLUZZO AROMATIZZATO CON VERDURE", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-20", new String[]{"PASTA AL RAGU' DI MANZO", "PARMIGIANO REGGIANO", "FINOCCHI LESSI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-21", new String[]{"PASTA AL TONNO", "POLPETTE DI LEGUMI AL FORNO", "SPINACI SALTATI'", "BANANA BIO"});
        createMenu(db, "2016-12-22", new String[]{"PASTA AL BURRO", "HAMBURGER DI MANZO CON POMODORO", "INSALATA VERDE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2016-12-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-30", new String[]{"NON PREVISTO"});
        createMenu(db, "2016-12-31", new String[]{"NON PREVISTO"});
        // GENNAIO 2017
        createMenu(db, "2017-01-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-03", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-07", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-08", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-09", new String[]{"PASTA AL POMODORO", "PECORINO D.O.P.", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-10", new String[]{"PASTINA IN BRODO VEGETALE/PASTA AL POMODORO**", "ROLLATA DI TACCHINO", "BIETOLA SALTATA", "BANANA BIO"});
        createMenu(db, "2017-01-11", new String[]{"RISOTTO DI ZUCCA", "BOCCONCINI DI POLLO FRITTO", "INSALATA VERDE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-12", new String[]{"TAGLIATELLE AL RAGU'", "POLPETTE VEGETALI AL FORNO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-13", new String[]{"PASSATO DI VERDURE CON ORZO", "PROSCIUTTO COTTO", "PATATE ARROSTO", "TORTINA DI PORRETTA"});
        createMenu(db, "2017-01-14", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-15", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-16", new String[]{"CAPPELLETTI IN BRODO", "FRITTATA", "SPINACI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-17", new String[]{"PASTA ALL'OLIO", "MANZO ARROSTO AL POMODORO", "CROCCHETTE DI PATATE AL FORNO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-18", new String[]{"RISO AL POMODORO", "TONNO/MARLUZZO OLIO E LIMONE*", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-01-19", new String[]{"PASSATO DI VERDURA CON CROSTINI/RISO**", "COSCE DI POLLO ARROSTO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-20", new String[]{"PASTA AL RAGU' VEGETALE", "STRACCHINO", "INSALATA MISTA", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-01-21", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-22", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-23", new String[]{"PASTA AL POMODORO E RICOTTA", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-24", new String[]{"CREMA DI CAROTE CON RISO", "PETTO DI POLLO ALLA MUGNAIA", "BIETOLA ALL'OLIO", "BANANA BIO"});
        createMenu(db, "2017-01-25", new String[]{"LASAGNE AL RAGU'", "TRIS DI VERDURE CON FAGIOLINI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-26", new String[]{"PASTA AL POMODORO", "TACCHINO AGLI AROMI", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-27", new String[]{"PASSATO DI FAGIOLI CON PASTA", "POLPETTE VEGETALI AL FORNO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-01-30", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-01-31", new String[]{"PASSATO DI VERDURA", "FOCACCIA RIPIENA'", "FRUTTA FRESCA BIO"});
        // FEBBRAIO 2017
        createMenu(db, "2017-02-01", new String[]{"PASTA AL RAGU' DI MANZO", "PARMIGIANO REGGIANO", "SPINACI SALTATI", "BANANA BIO"});
        createMenu(db, "2017-02-02", new String[]{"PASTINA IN BRODO VEGETALE/PASTA AL POMODORO**", "HAMBURGER POLLO E TACCHINO", "FINOCCHI E CAROTE CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-03", new String[]{"RISOTTO ALLA PARMIGIANA", "TONNO", "FAGIOLI CANNELLINI ALL'OLIO", "TORTINA DI PORRETTA"});
        createMenu(db, "2017-02-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-06", new String[]{"POLENTA AL RAGU'", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-07", new String[]{"RISO AL POMODORO", "LONZA DI MAIALE AL LIMONE", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-08", new String[]{"PASTA ALL'OLIO", "BOCCONCINI DI POLLO FRITTO", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-09", new String[]{"PASSATO DI CECI CON PASTA BIO", "STRACCHINO BIO", "BIETOLA SALTATA BIO", "BANANA BIO"});
        createMenu(db, "2017-02-10", new String[]{"TAGLIATELLE AL POMODORO", "HAMBURGER DI PESCE AL FORNO", "INSALATA MISTA", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-02-11", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-13", new String[]{"RISOTTO DI ZUCCA", "BOCCONCINI DI MOZZARELLA", "SPINACI SALTATI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-14", new String[]{"PASTINA IN BRODO/CAPPELLETTI IN BRODO", "MERLUZZO AROMATIZZATO CON MAIONESE", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-02-15", new String[]{"PASTA AL POMODORO", "COSCE DI POLLO ARROSTO", "INSALATA VERDE CON MAIS", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-16", new String[]{"PASSATO DI VERDURA CON CROSTINI/ORZO**", "BRASATO DI MAIALE CON OLIVE", "PATATE LESSE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-17", new String[]{"PASTA AL TONNO", "FRITTATA", "TRIS DI VERDURE CON BROCCOLI", "BUDINO"});
        createMenu(db, "2017-02-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-20", new String[]{"PASTA AL POMODORO E RICOTTA", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-21", new String[]{"CREMA DI CAROTE CON RISO", "PETTO DI POLLO ALLA MUGNAIA", "BIETOLA ALL'OLIO", "BANANA BIO"});
        createMenu(db, "2017-02-22", new String[]{"LASAGNE AL RAGU'", "TRIS DI VERDURE CON FAGIOLINI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-23", new String[]{"PASTA AL POMODORO", "TACCHINO AGLI AROMI", "PURE' DI PATATE", "CENCI"});
        createMenu(db, "2017-02-24", new String[]{"PASSATO DI FAGIOLI CON PASTA", "POLPETTE VEGETALI AL FORNO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-02-27", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-02-28", new String[]{"NON PREVISTO"});
        // MARZO 2017
        createMenu(db, "2017-03-01", new String[]{"PASTA ALL'OLIO", "PARMIGIANO REGGIANO", "SPINACI SALTATI", "BANANA BIO"});
        createMenu(db, "2017-03-02", new String[]{"PASTINA IN BRODO/PASTA AL POMODORO**", "HAMBURGER DI POLLO E TACCHINO", "FINOCCHI E CAROTE CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-03", new String[]{"RISOTTO ALLA PARMIGIANA", "TONNO", "FAGIOLI CANNELLINI ALL'OLIO", "TORTINA DI PORRETTA"});
        createMenu(db, "2017-03-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-05", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-06", new String[]{"POLENTA AL RAGU'", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-07", new String[]{"RISO AL POMODORO", "LONZA DI MAIALE AL LIMONE", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-08", new String[]{"PASTA ALL'OLIO", "BOCCONCINI DI POLLO FRITTO", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-09", new String[]{"PASSATO DI CECI CON PASTA BIO", "STRACCHINO BIO", "BIETOLA SALTATA BIO", "BANANA BIO"});
        createMenu(db, "2017-03-10", new String[]{"TAGLIATELLE AL POMODORO", "HAMBURGER DI PESCE AL FORNO", "INSALATA MISTA", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-03-11", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-13", new String[]{"RISOTTO DI ZUCCA", "BOCCONCINI DI MOZZARELLA", "SPINACI SALTATI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-14", new String[]{"PASTINA IN BRODO/CAPPELLETTI IN BRODO", "MERLUZZO AROMATIZZATO CON MAIONESE", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-03-15", new String[]{"PASTA AL POMODORO", "COSCE DI POLLO ARROSTO", "INSALATA VERDE CON MAIS", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-16", new String[]{"PASSATO DI VERDURA CON CROSTINI/ORZO**", "BRASATO DI MAIALE CON OLIVE", "PATATE LESSE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-17", new String[]{"PASTA AL TONNO", "FRITTATA", "TRIS DI VERDURE CON BROCCOLI", "BUDINO"});
        createMenu(db, "2017-03-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-19", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-20", new String[]{"PASTA AL POMODORO E RICOTTA", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-21", new String[]{"CREMA DI CAROTE CON RISO", "PETTO DI POLLO ALLA MUGNAIA", "BIETOLA ALL'OLIO", "BANANA BIO"});
        createMenu(db, "2017-03-22", new String[]{"LASAGNE AL RAGU'", "TRIS DI VERDURE CON FAGIOLINI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-23", new String[]{"PASTA AL POMODORO", "TACCHINO AGLI AROMI", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-24", new String[]{"PASSATO DI FAGIOLI CON PASTA", "POLPETTE VEGETALI AL FORNO", "FINOCCHI CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-26", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-03-27", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-28", new String[]{"PASSATO DI VERDURA", "FOCACCIA RIPIENA", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-29", new String[]{"PASTA AL RAGU'DI MANZO", "PARMIGIANO REGGIANO", "SPINACI SALTATI", "BANANA BIO"});
        createMenu(db, "2017-03-30", new String[]{"PASTINA IN BRODO/PASTA AL POMODORO**", "HAMBURGER DI POLLO E TACCHINO", "FINOCCHI E CAROTE CRUDITE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-03-31", new String[]{"RISOTTO ALLA PARMIGIANA", "TONNO", "FAGIOLI CANNELLINI ALL'OLIO", "TORTINA DI PORRETTA"});
        // APRILE 2017
        createMenu(db, "2017-04-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-03", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-04", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/ORZO**", "BOCCONCINI DI POLLO FRITTO", "RATATOUILLE DI VERDURE/CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-04-05", new String[]{"BRUSCHETTA AL POMODORO BIO", "BOCCONCINI DI MOZZARELLA BIO", "INSALATA VERDE BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-06", new String[]{"PASTA ALL'OLIO", "PESCE E PATATE AL FORNO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2017-04-07", new String[]{"RISOTTO DI CAROTE", "POLPETTE VEGETALI AL FORNO", "ZUCCHINE TRIFOLATE", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-04-08", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-09", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-10", new String[]{"GNOCCHI AL POMODORO", "EMMENTHAL E MORTADELLA DI BOLOGNA IGP", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-11", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "COSCE DI POLLO ARROSTO", "PURE' DI PATATE", "CIOCCOLATO AL LATTE BIO IN TAVOLETTA"});
        createMenu(db, "2017-04-12", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-14", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-15", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-16", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-17", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-19", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/RISO**", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-20", new String[]{"LASAGNE AL PESTO/PASTA AL PESTO*", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-21", new String[]{"RISO E BISI", "SPEZZATINO DI TACCHINO AGLI AGRUMI", "CAROTE FILANGE CON MAIS", "BUDINO"});
        createMenu(db, "2017-04-22", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-23", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-26", new String[]{"PASTA AL RAGU' VEGETALE", "FRITTATA", "PISELLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-27", new String[]{"INSALATA DI RISO/RISO AL POMODORO*", "BASTONCINI DI PESCE AL FORNO", "INSALATA E POMODORI INSALATARI", "BANANA BIO"});
        createMenu(db, "2017-04-28", new String[]{"PASTA AL POMODORO", "RICOTTA FILIERA CORTA", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-04-29", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-04-30", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-01", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-02", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/ORZO**", "BOCCONCINI DI POLLO FRITTO", "RATATOUILLE DI VERDURE/CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-05-03", new String[]{"BRUSCHETTA AL POMODORO BIO", "BOCCONCINI DI MOZZARELLA BIO", "INSALATA VERDE BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-04", new String[]{"PASTA ALL'OLIO", "PESCE E PATATE AL FORNO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2017-05-05", new String[]{"RISOTTO DI CAROTE", "POLPETTE VEGETALI AL FORNO", "ZUCCHINE TRIFOLATE", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-05-06", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-07", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-08", new String[]{"GNOCCHI AL POMODORO", "EMMENTHAL E MORTADELLA DI BOLOGNA IGP", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-09", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "COSCE DI POLLO ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-10", new String[]{"PASTA FREDDA AL TONNO/PASTA ALL'OLIO", "UOVA SODE CON MAIONESE", "INSALATA VERDE CON MAIS", "BANANA BIO"});
        createMenu(db, "2017-05-11", new String[]{"VELLUTATA DI PISELLI CON RISO", "LONZA DI MAIALE AL LIMONE", "CAROTE FILANGE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-12", new String[]{"PASTA AL PESTO", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-05-13", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-14", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-15", new String[]{"PASTA AL POMODORO E BASILICO", "TONNO", "FAGIOLINI VERDI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-16", new String[]{"PASTA AL BURRO", "POLPETTONE DI MANZO CON POMODORO", "ZUCCHINE TRIFOLATE", "BANANA BIO"});
        createMenu(db, "2017-05-17", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/RISO**", "PROSCIUTTO CRUDO/PROSCIUTTO COTTO*", "PATATE ARROSTO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-18", new String[]{"LASAGNE AL PESTO/PASTA AL PESTO*", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-19", new String[]{"RISO E BISI", "SPEZZATINO DI TACCHINO AGLI AGRUMI", "CAROTE FILANGE CON MAIS", "BUDINO"});
        createMenu(db, "2017-05-20", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-21", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-22", new String[]{"RAVIOLI BURRO E SALVIA", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-23", new String[]{"PASTA AL RAGU' VEGETALE", "FRITTATA", "PISELLINI ALL'OLIO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2017-05-24", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "PETTO DI POLLO ALLA SALVIA", "PATATE LESSE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-25", new String[]{"INSALATA DI RISO/RISO AL POMODORO*", "BASTONCINI DI PESCE AL FORNO", "INSALATA DI POMODORI INSALATARI", "BANANA BIO"});
        createMenu(db, "2017-05-26", new String[]{"PASTA AL POMODORO", "RICOTTA FILERA CORTA", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-27", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-28", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-05-29", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-05-30", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/ORZO**", "BOCCONCINI DI POLLO FRITTO", "RATATOUILLE DI VERDURE/CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-05-31", new String[]{"BRUSCHETTA AL POMODORO BIO", "BOCCONCINI DI MOZZARELLA BIO", "INSALATA VERDE BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-01", new String[]{"PASTA ALL'OLIO", "PESCE E PATATE AL FORNO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2017-06-02", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-03", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-04", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-05", new String[]{"GNOCCHI AL POMODORO", "EMMENTHAL E MORTADELLA DI BOLOGNA IGP", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-06", new String[]{"PASTA AL POMODORO E OLIVE/PASTA AL POMODORO*", "COSCE DI POLLO ARROSTO", "PURE' DI PATATE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-07", new String[]{"PASTA FREDDA AL TONNO/PASTA ALL'OLIO", "UOVA SODE CON MAIONESE", "INSALATA VERDE CON MAIS", "BANANA BIO"});
        createMenu(db, "2017-06-08", new String[]{"VELLUTATA DI PISELLI CON RISO", "LONZA DI MAIALE AL LIMONE", "CAROTE FILANGE'", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-09", new String[]{"PASTA AL PESTO", "CROCCHETTE DI MERLUZZO AL FORNO", "PISELLINI ALL'OLIO", "SUCCO DI FRUTTA"});
        createMenu(db, "2017-06-10", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-11", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-12", new String[]{"PASTA AL POMODORO E BASILICO", "TONNO", "FAGIOLINI VERDI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-13", new String[]{"PASTA AL BURRO", "POLPETTONE DI MANZO CON POMODORO", "ZUCCHINE TRIFOLATE", "BANANA BIO"});
        createMenu(db, "2017-06-14", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/RISO**", "PROSCIUTTO CRUDO", "PATATE ARROSTO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-15", new String[]{"LASAGNE AL PESTO", "PARMIGIANO REGGIANO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-16", new String[]{"RISO E BISI", "SPEZZATINO DI TACCHINO AGLI AGRUMI", "CAROTE FILANGE CON MAIS", "BUDINO"});
        createMenu(db, "2017-06-17", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-18", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-19", new String[]{"RAVIOLI BURRO E SALVIA", "ARISTA ARROSTO", "CAROTE ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-20", new String[]{"PASTA AL RAGU' VEGETALE", "FRITTATA", "PISELLINI ALL'OLIO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2017-06-21", new String[]{"FUSILLI CON ZUCCHINE E PINOLI", "PETTO DI POLLO ALLA SALVIA", "PATATE LESSE", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-22", new String[]{"RISO AL POMODORO", "BASTONCINI DI PESCE AL FORNO", "INSALATA DI POMODORI INSALATARI", "BANANA BIO"});
        createMenu(db, "2017-06-23", new String[]{"PASTA AL POMODORO", "RICOTTA FILERA CORTA", "FAGIOLINI ALL'OLIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-24", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-25", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-06-26", new String[]{"PASTA AL PESTO", "PROSCIUTTO COTTO", "POMODORI INSALATARI", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-27", new String[]{"CREMA DI VERDURE ESTIVA CON CROSTINI/ORZO**", "BOCCONCINI DI POLLO FRITTO", "CAROTE FILANGE'", "BANANA BIO"});
        createMenu(db, "2017-06-28", new String[]{"BRUSCHETTA AL POMODORO BIO", "BOCCONCINI DI MOZZARELLA BIO", "INSALATA VERDE BIO", "FRUTTA FRESCA BIO"});
        createMenu(db, "2017-06-29", new String[]{"PASTA ALL'OLIO", "PESCE E PATATE AL FORNO", "MACEDONIA DI FRUTTA BIO"});
        createMenu(db, "2017-06-30", new String[]{"NON PREVISTO"});
        createMenu(db, "2017-07-01", new String[]{"NON PREVISTO"});


    }

    @Override
    protected void finalize() throws Throwable
    {
        this.close();
        super.finalize();
        Log.d("chiudo la connessione", "chiusura connessione");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        // creazione delle tabelle
        db.execSQL(CREATE_TABLE_MENU);
        caricaPasti_Dati2016_2017(db);
        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_PRESENZE);

        // inserisci le altre tabelle qui sotto...
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESENZE);
        // inserisci le altre tabelle qui sotto...
        // creazione del database ...
        onCreate(db);
    }

    /************************************************************************************************
     * Metodi per la creazione e il funzionamento
     ************************************************************************************************/

    // ------------------------ "menu" metodi della tabella ----------------//

    /*
     * Creazione della tabella Menu
     */
    public long createMenu(SQLiteDatabase db, String data, String[] menu)
    {
        for (int i = 0; i < menu.length; i++)
        {
            createMenu(db, new Menu(data, Menu.PASTO_NORMALE, menu[i]));
        }

        return menu.length;
    }

    /*
     * Creazione della tabella Menu
     */
    public long createMenu(Menu menu)
    {
        return createMenu(null, menu);
    }

    /*
     * Creazione della tabella Menu
     */
    public long createMenu(SQLiteDatabase db, Menu menu)
    {
        if (db == null)
            db = this.getWritableDatabase();

        // insert row
        long menu_id = db.insert(TABLE_MENU, null, getContentFromMenu(menu));

        return menu_id;
    }

    private ContentValues getContentFromMenu(Menu menu)
    {
        ContentValues values = new ContentValues();
        values.put(DATA_MENU, menu.getData());
        values.put(CF_MENU, menu.getCf());
        values.put(DESC_MENU, menu.getDescrizione());
        values.put(CONSISTENTE_MENU, menu.isConsistente());
        values.put(KEY_CREATED_AT, getDateTime());

        return values;
    }

    /**
     * ottengo l'elemento di menu
     */
    public Menu getMenu(long menu_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MENU + " WHERE "
                + KEY_ID + " = " + menu_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Menu mn = getMenuFromCursor(c);

        return mn;
    }

    private Menu getMenuFromCursor(Cursor c)
    {
        Menu mn = new Menu();
        mn.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        mn.setData(c.getString(c.getColumnIndex(DATA_MENU)));
        mn.setCf((c.getString(c.getColumnIndex(CF_MENU))));
        mn.setDescrizione((c.getString(c.getColumnIndex(DESC_MENU))));
        mn.setConsistente((c.getInt(c.getColumnIndex(CONSISTENTE_MENU))));
        mn.setCreatedat(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return mn;
    }

    /**
     * ottengo tutto il menu normale del giorno voluto.
     */
    public List<Menu> getPastoNormaleByDate(String data)
    {
        return getPastoPersonaleByDate(data, Menu.PASTO_NORMALE);
    }

    /**
     * ottengo tutto il menu normale del giorno voluto.
     */
    public List<Menu> getPastoPersonaleByDate(String data, String cf)
    {
        List<Menu> menus = new ArrayList<Menu>();
        String selectQuery = "SELECT  * FROM " + TABLE_MENU + " mn WHERE"
                + " mn." + DATA_MENU + " = '" + data + "' AND mn." + CF_MENU
                + " = '" + cf + "'";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst())
        {
            do
            {
                // aggiungo il menu alla lista.
                menus.add(getMenuFromCursor(c));
            } while (c.moveToNext());
        }

        return menus;
    }

    /**
     * ottengo tutto il menu normale del giorno voluto.
     */
    public List<Menu> getMenuNonPrevisto()
    {
        List<Menu> menus = new ArrayList<Menu>();
        menus.add(new Menu("NON PREVISTO"));

        return menus;
    }


    /**
     * numero di menu
     */
    public int getMenuCount()
    {
        return getMenuPersonaleCountByDate(null, null);
    }

    /**
     * numero di menu
     */
    public int getMenuPastoNormaleCountByDate(String data)
    {
        return getMenuPersonaleCountByDate(data, Menu.PASTO_NORMALE);
    }

    /**
     * numero di menu
     */
    public int getMenuPersonaleCountByDate(String data, String cf)
    {
        String countQuery = "SELECT  * FROM " + TABLE_MENU + " mn";
        if (data != null || cf != null)
        {
            countQuery += " WHERE";

            if (data != null)
                countQuery += " mn." + DATA_MENU + " = '" + data;
            if (cf != null)
                countQuery += " AND mn." + CF_MENU + " = '" + cf;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        // return count
        return count;
    }

    /**
     * Aggiorno i menu
     */
    public int updateMenu(Menu menu)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating row
        return db.update(TABLE_MENU, getContentFromMenu(menu), KEY_ID + " = ?",
                new String[]{String.valueOf(menu.getId())});
    }

    /**
     * Cancello un menu dato l'id
     */
    public void deleteMenu(long menu_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MENU, KEY_ID + " = ?",
                new String[]{String.valueOf(menu_id)});
    }

    // cancellazione per cf e data.


    // ------------------------ "login" metodi della tabella ----------------//


    /*
     * Creazione della tabella Menu
     */
    public long createLogin(String cf, String ci)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert row
        long login_id = db.insert(TABLE_LOGIN, null, getContentFromLogin(cf, ci));

        return login_id;
    }

    private ContentValues getContentFromLogin(String cf, String ci)
    {
        ContentValues values = new ContentValues();
        values.put(DATA_LOGIN, getDate());
        values.put(CF_LOGIN, cf);
        values.put(CI_LOGIN, ci);
        values.put(KEY_CREATED_AT, getDateTime());

        return values;
    }

    /**
     * ottengo l'elemento di Login
     */
    public Login getLogin(long login_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = getLoginQuery(login_id, null, null);

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Login lg = getLoginFromCursor(c);

        return lg;
    }

    private Login getLoginFromCursor(Cursor c)
    {
        Login mn = new Login();
        mn.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        mn.setData(c.getString(c.getColumnIndex(DATA_LOGIN)));
        mn.setCf((c.getString(c.getColumnIndex(CF_LOGIN))));
        mn.setCi((c.getString(c.getColumnIndex(CI_LOGIN))));
        mn.setCreatedat(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return mn;
    }

    private String getLoginQuery(Long login_id, String cf, String ci)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN + " l";
        if (cf != null || ci != null || login_id != null)
            selectQuery += " WHERE 1=1";
        if (cf != null)
            selectQuery += " AND l." + CF_LOGIN + " "
                    + " = '" + cf + "'";
        if (ci != null)
            selectQuery += " AND l." + CI_LOGIN + " "
                    + " = '" + ci + "'";
        if (login_id != null)
            selectQuery += " AND l." + KEY_ID + " "
                    + " = " + login_id;

        return selectQuery;
    }

    /**
     * ottengo tutto l'oggetto login in base all'unica riga presente.
     */
    public Login getLoginDefault()
    {
        List<Login> llogin = null;
        if ((llogin = getLoginByCFCI(null, null)).size() > 0)
            return llogin.get(0);
        return null;
    }

    /**
     * ottengo tutto l'oggetto login
     */
    public List<Login> getLoginByCFCI(String cf, String ci)
    {
        List<Login> logins = new ArrayList<Login>();
        String selectQuery = getLoginQuery(null, cf, ci);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst())
        {
            do
            {
                // aggiungo il menu alla lista.
                logins.add(getLoginFromCursor(c));
            } while (c.moveToNext());
        }

        return logins;
    }

    /**
     * ritorna true se esiste un record di login
     */
    public boolean isLogin()
    {
        return getLoginCount() > 0;
    }

    /**
     * numero di login
     */
    public int getLoginCount()
    {
        return getLoginCountByCFCI(null, null);
    }

    /**
     * numero di login
     */
    public int getLoginCountByCFCI(String cf, String ci)
    {
        String countQuery = getLoginQuery(null, cf, ci);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        // return count
        return count;
    }

    /**
     * Deleting a Login
     */
    public int updateLogin(Login login)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CF_LOGIN, login.getCf());
        values.put(CI_LOGIN, login.getCi());
        values.put(DATA_LOGIN, getDateTime());

        // updating row
        return db.update(TABLE_LOGIN, values, KEY_ID + " = ?",
                new String[]{String.valueOf(login.getId())});
    }

    /**
     * Deleting a Login
     */
    public void deleteLogin(long login_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOGIN, KEY_ID + " = ?",
                new String[]{String.valueOf(login_id)});
    }


    // ------------------------ "presenze" metodi della tabella ----------------//

    /*
     * Creazione della tabella Menu
     */
    public long createPresenze(Presenza pp)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert row
        long presenze_id = 0;
        presenze_id = db.insert(TABLE_PRESENZE, null, getContentFromPresenze(pp.getData(), pp.getCf(), 1));

        return presenze_id;

    }

    /*
     * Creazione della tabella Menu
     */
    public long createPresenze(Calendar data, String cf, int[] presenze)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert row
        long presenze_id = 0;
        for (int i = 0; i > presenze.length; i++)
        {
            presenze_id = db.insert(TABLE_PRESENZE, null, getContentFromPresenze(data, cf, presenze[i]));
        }
        return presenze_id;
    }

    private ContentValues getContentFromPresenze(Calendar data, String cf, int presenza)
    {
        return getContentFromPresenze(getDate(data), cf, presenza);
    }

    private ContentValues getContentFromPresenze(String data, String cf, int presenza)
    {
        ContentValues values = new ContentValues();
        values.put(DATA_PRESENZE, data);
        values.put(CF_PRESENZE, cf);
        values.put(PRESENZA_PRESENZE, presenza);
        values.put(KEY_CREATED_AT, getDateTime());

        return values;
    }

    /**
     * ottengo l'elemento di Login
     */
    public Presenza getPresenza(long presenza_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = getPresenzaQuery(presenza_id, null, null);

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        return getPresenzaFromCursor(c);
    }

    /**
     * ottengo l'elemento di Login
     */
    public Presenza getPresenzeByDataCf(Calendar date, String cf)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = getPresenzaQuery(null, date, cf);

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        return getPresenzaFromCursor(c);
    }

    /**
     * ottengo l'elemento di Login
     */
    public List<Presenza> getPresenzeMensiliByDataCf(Calendar date, String cf)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = getPresenzaQuery(null, date, cf, true);

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        List<Presenza> lpresenza = new ArrayList<>();
        // looping through all rows and adding to list
        if (c.moveToFirst())
        {
            do
            {
                // aggiungo il menu alla lista.
                lpresenza.add(getPresenzaFromCursor(c));
            } while (c.moveToNext());
        }

        return lpresenza;
    }


    private Presenza getPresenzaFromCursor(Cursor c)
    {
        Presenza p = new Presenza();
        p.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        p.setData(getCalendar(c.getString(c.getColumnIndex(DATA_PRESENZE)), "yyyy-MM-dd"));
        p.setCf((c.getString(c.getColumnIndex(CF_PRESENZE))));
        p.setPresenza((c.getInt(c.getColumnIndex(PRESENZA_PRESENZE))));
        p.setCreatedat(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return p;
    }

    private String getPresenzaQuery(Long presenza_id, Calendar data, String cf)
    {
        return getPresenzaQuery(presenza_id, data, cf, false);
    }

    private String getPresenzaQuery(Long presenza_id, Calendar data, String cf, boolean mensili)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_PRESENZE + " l";
        if (data != null || cf != null || presenza_id != null)
            selectQuery += " WHERE 1=1";
        if (mensili)
        {
            if (data != null)
                selectQuery += " AND strftime('%Y%m', l." + DATA_PRESENZE + " "
                        + ") = '" + getYearMonth(data) + "'";
        }
        else
        {
            if (data != null)
                selectQuery += " AND l." + DATA_PRESENZE + " "
                        + " = '" + data + "'";
        }
        if (cf != null)
            selectQuery += " AND l." + CF_PRESENZE + " "
                    + " = '" + cf + "'";
        if (presenza_id != null)
            selectQuery += " AND l." + KEY_ID + " "
                    + " = " + presenza_id;

        return selectQuery;
    }

    /**
     * cancello le presenze
     */
    public void deletePresenza(long presenza_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENZE, KEY_ID + " = ?", new String[]{String.valueOf(presenza_id)});
    }

    public void deletePresenzaByCfDate(String cf, Calendar data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENZE, DATA_PRESENZE + " = ? AND " + CF_PRESENZE + " = ?", new String[]{getDate(data), cf});
    }

    public void deletePresenzeMeseByCfDate(String cf, Long data)
    {
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis(data);
        deletePresenzeMeseByCfDate(cf, mydate);
    }

    public void deletePresenzeMeseByCfDate(String cf, Calendar data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESENZE, "strftime('%Y%m', " + DATA_PRESENZE + ") = ? AND " + CF_PRESENZE + " = ?", new String[]{getYearMonth(data), cf});
    }

    /************************************************************************************************
     * Metodi di comodo.
     ************************************************************************************************/

    private String getDate()
    {
        return getDate(null);
    }

    private Calendar getCalendar(Long cal)
    {
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(cal);
        return calendario;
    }

    private Calendar getCalendar(String cal, String format)
    {
        Calendar calendario = Calendar.getInstance();
        java.util.Date thedate = null;
        try
        {
            thedate = new SimpleDateFormat(format).parse(cal);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        if (thedate != null)
            calendario.setTime(thedate);
        return calendario;
    }

    private String getDate(Calendar date)
    {
        return getDateTime(date, "yyyy-MM-dd");
    }

    private String getYearMonth(Calendar date)
    {
        return getDateTime(date, "yyyyMM");
    }

    /**
     * ritorno la stringa nel formato date time.
     */
    private String getDateTime()
    {
        return getDateTime(null, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * ritorno la stringa nel formato date time.
     */
    private String getDateTime(Calendar mydate, String pattern)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        if (mydate == null)
            mydate = Calendar.getInstance();
        return dateFormat.format(mydate.getTime());
    }
}
