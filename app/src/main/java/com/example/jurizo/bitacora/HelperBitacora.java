package com.example.jurizo.bitacora;

import android.app.Application;

import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Employee;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.User;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Visit;

/**
 * Created by jurizo on 15/08/17.
 */

public class HelperBitacora extends Application {
    private User usr;
    private Employee emp;
    private Visit vst;
    private Office ofc;

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Visit getVst() {
        return vst;
    }

    public void setVst(Visit vst) {
        this.vst = vst;
    }

    public Office getOfc() {
        return ofc;
    }

    public void setOfc(Office ofc) {
        this.ofc = ofc;
    }
}
