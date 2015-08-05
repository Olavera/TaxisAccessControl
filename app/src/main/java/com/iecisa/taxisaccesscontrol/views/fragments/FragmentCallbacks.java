package com.iecisa.taxisaccesscontrol.views.fragments;

import android.os.Bundle;

import com.iecisa.taxisaccesscontrol.views.utils.Sections;

/**
 * @author Olavera
 */
public interface FragmentCallbacks {

    void onSectionAttached(String title);

    void switchFragment(Sections section, Bundle bundle);
}
