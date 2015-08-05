package com.iecisa.taxisaccesscontrol;

import android.app.Application;

import com.iecisa.taxisaccesscontrol.model.Repository;

/**
 * @author darevalo
 */
public class TaxiAccessControlApp extends Application {

    private Repository mRepository=null;

    public Repository getRepository() {
        return this.mRepository;
    }

    public void setRepository(Repository repository) {
        this.mRepository = repository;
    }
}
