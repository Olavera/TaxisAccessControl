package com.iecisa.taxisaccesscontrol;

import android.app.Application;

import com.iecisa.model.rest.RestDataSource;

/**
 * @author darevalo
 */
public class App extends Application {

    private RestDataSource mRestDataSource=null;

    public RestDataSource getRestDataSource() {
        return mRestDataSource;
    }

    public void setRestDataSource(RestDataSource mRestDataSource) {
        this.mRestDataSource = mRestDataSource;
    }
}
