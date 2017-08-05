package com.example.jurizo.bitacora.Controls;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Visit;
import com.example.jurizo.bitacora.R;

import java.util.List;

/**
 * Created by jurizo on 30/06/17.
 */

public class AdapterListViewVisit extends ArrayAdapter<Visit> {
    int groupid;
    LayoutInflater inflater;
    List<Visit> list;

    public AdapterListViewVisit(Activity context, int groupid, int id, List<Visit> list){
        super(context, id, list);
        this.list = list;
        this.groupid = groupid;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid, parent, false);
        int id = list.get(position).getId();
        TextView idVisit = (TextView) itemView.findViewById(R.id.layout_visita_id);
        ImageView imageView = (ImageView)   itemView.findViewById(R.id.layout_visita_imagen);
        TextView txtUserExcecute = (TextView) itemView.findViewById(R.id.layout_visita_usuario);
        TextView txtFecha = (TextView) itemView.findViewById(R.id.layout_visita_fecha);
        TextView txtOficina = (TextView) itemView.findViewById(R.id.layout_visita_oficina);
        ImageView imageViewStatus = (ImageView) itemView.findViewById(R.id.layout_visita_imagen_status);

        idVisit.setText(String.valueOf(list.get(position)));

        /*String userName = list.get(position).getUser().getApellido_paterno() + " " + list.get(position).getUser().getApellido_materno() + " " + list.get(position).getUser().getNombres();
        txtUserExcecute.setText(userName);

        String oficinaVisit = String.valueOf(list.get(position).getOficina().getCc()) + " - " + list.get(position).getOficina().getOficina();
        txtOficina.setText(oficinaVisit);


        if(list.get(position).getStatus() == 0 || list.get(position).getIsUpdate() == 0){
            imageViewStatus.setImageResource(R.drawable.ic_red_point);
        }else {
            imageViewStatus.setImageResource(R.drawable.ic_green_point);
        }

        txtFecha.setText(list.get(position).getFecha());*/

        return itemView;
    }
}
