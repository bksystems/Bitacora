package com.example.jurizo.bitacora.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Employee;
import com.example.jurizo.bitacora.R;

import java.util.List;

/**
 * Created by Carlos Rizo on 08/07/2017.
 */

public class AdapterSpinnerCustom extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<Employee> employees;

    public AdapterSpinnerCustom(Context context, List<Employee> employees){
        inflater = LayoutInflater.from(context);
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Employee getItem(int position) {
        return employees.get(position);
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
        TextView userArea = (TextView) convertView.findViewById(R.id.custom_spinner_area);
        String roster = "";
        String puesto = "";
        String area = "";
        if(employees.get(position).getId() > 0 || employees.get(position).getRoster() > 0) {
            roster = String.valueOf(employees.get(position).getRoster()) + " - " ;
            //puesto = users.get(position).getPuesto().getPuesto();
            //area = users.get(position).getArea().getArea();
        }
        userName.setText(roster
                + employees.get(position).getFirst_lastname() + " "
                + employees.get(position).getSecond_lastname() + " "
                + employees.get(position).getNames());
        userPuesto.setText(puesto);
        userArea.setText(area);

        return convertView;
    }
}
