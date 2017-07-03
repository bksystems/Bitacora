package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Oficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.SysDbVersionEntity;

import java.net.ProtocolException;
import java.util.List;

/**
 * Created by Carlos Rizo on 25/06/2017.
 */

public class SyncManager extends AsyncTask<Void, String, Void> {

    private String TAG = SyncManager.class.getSimpleName();
    private Context context;
    private ProgressDialog progressDialog;

    private static String hostname = ConfigServerConnection.getHostname();
    private static String port = ConfigServerConnection.getPort();
    private static String pathSyncFiles = ConfigServerConnection.getPathSyncFiles();

    private DBHelper dbHelper;

    public SyncManager(Context context){
        this.context = context;
    }


    @Override
    protected Void doInBackground(Void... params) {
        progressDialog.setCancelable(true);
        if(validateInternetConnection()) {

            HttpHandler httpHandler = new HttpHandler();
            SysDbVersionEntity dbVersionEntityData = null;
            //dbHelper = new DBHelper(context);
            DAO_Oficinas daoOficinas = new DAO_Oficinas(context);
            try {
                publishProgress("Buscando actualización de información");
                String strUrl = hostname + port + pathSyncFiles + "getVersionDataBase.php";
                String jsonStrVersionDB = httpHandler.makeServicesCall(strUrl);

                if (jsonStrVersionDB != null) {
                    dbVersionEntityData = SyncAuxHandlerParse.DBVersionParse(jsonStrVersionDB);
                    //SQLiteDatabase db = dbHelper.getWritableDatabase();
                    //if (db.getVersion() < dbVersionEntityData.getId()) {

                    //}
                }
            } catch (ProtocolException ex) {
                ex.printStackTrace();
            }

            if (dbVersionEntityData != null) {
                publishProgress("Preparando la base de datos.......");
                if (dbVersionEntityData.getupdateOficinas() == 1) {
                    try {
                        List<EntityOficina> listOficinas = null;
                        publishProgress("Conectando para obtener oficinas...");
                        String strUrl = hostname + port + pathSyncFiles + "getOficinas.php";
                        String jsonStrOficinas = httpHandler.makeServicesCall(strUrl);
                        if (jsonStrOficinas != null) {
                            listOficinas = SyncAuxHandlerParse.OficinasJSONParse(jsonStrOficinas);
                            if (listOficinas != null && listOficinas.size() > 0) {
                                publishProgress("Actualizando estructura de oficinas....");
                                daoOficinas.updateOficinas(listOficinas);
                                publishProgress("Se actualizo la estructura de oficinas....");
                            }
                        }
                    } catch (ProtocolException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }else{


        }
        return null;
    }

    private boolean validateInternetConnection() {
        publishProgress("Validando conexión...");
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return  connected;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Actualizando Bitacora");
        progressDialog.setMessage("Conectando con el servidor");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.cancel();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }
}
