package com.example.jurizo.bitacora;

import android.app.Application;

import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;

/**
 * Created by jurizo on 15/08/17.
 */

public class HelperBitacora extends Application {
    private Office office;

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
