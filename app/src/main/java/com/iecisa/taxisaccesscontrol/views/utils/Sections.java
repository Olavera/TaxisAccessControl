package com.iecisa.taxisaccesscontrol.views.utils;

/**
 * @author Olavera
 */
public enum Sections {
    BLACKLIST_FRAGMENT(0),
    VEHICLE_DETAILS_FRAGMENT(101),
    UNKNOWN(999);

    private int mPosition;

    private Sections(int position){
        this.mPosition = position;
    }

    public int getPosition(){
        return mPosition;
    }

    public static Sections parseSection(int position){
        Sections ret = UNKNOWN;
        if(BLACKLIST_FRAGMENT.getPosition() == position){
            ret = BLACKLIST_FRAGMENT;
        } else if (VEHICLE_DETAILS_FRAGMENT.getPosition() == position) {
            ret = VEHICLE_DETAILS_FRAGMENT;
        }
        return ret;
    }
}
