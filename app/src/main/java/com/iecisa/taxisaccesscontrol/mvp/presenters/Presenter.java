package com.iecisa.taxisaccesscontrol.mvp.presenters;

import android.os.Bundle;

import com.iecisa.taxisaccesscontrol.mvp.views.MVPView;

/**
 * @author darevalo
 */
public interface Presenter {

    void onStart ();

    void onStop ();

    void attachView (MVPView v);

    void attachBundle(Bundle bundle);

    void initializePresenter();

}
