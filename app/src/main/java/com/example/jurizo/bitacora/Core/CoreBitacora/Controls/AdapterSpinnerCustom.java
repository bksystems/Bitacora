package com.example.jurizo.bitacora.Core.CoreBitacora.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.R;

import java.util.List;

/**
 * Created by Carlos Rizo on 08/07/2017.
 */

public class AdapterSpinnerCustom extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<EntityUser> users;

    public AdapterSpinnerCustom(Context context, List<EntityUser> users){
        inflater = LayoutInflater.from(context);
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public EntityUser getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_spinner, null);
        TextView userName = (TextView)convertView.findViewById(R.id.custom_spinner_nombre);
        TextView userId = (TextView)convertView.findViewById(R.id.custom_spinner_id);
        TextView userPuesto = (TextView) convertView.findViewById(R.id.custom_spinner_puesto);
        //TextView userArea = (TextView) convertView.findViewById(R.id.custom_spinner_area);
        String nomina = "";
        String puesto = "";
        //String area = "";
        if(users.get(position).getId() > 0 || users.get(position).getNomina() > 0) {
            nomina = String.valueOf(users.get(position).getNomina()) + " - " ;
            puesto = users.get(position).getPuesto().getPuesto();
            //area = users.get(position).getArea().getArea();
        }
        userName.setText(nomina + users.get(position).getApellido_paterno() + " " + users.get(position).getApellido_materno() + " " + users.get(position).getNombres());
        userPuesto.setText(puesto);
       // userArea.setText(area);

        return convertView;
    }
}
