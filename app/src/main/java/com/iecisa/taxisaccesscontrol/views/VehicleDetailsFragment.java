package com.iecisa.taxisaccesscontrol.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iecisa.model.entities.VehicleDetails;
import com.iecisa.taxisaccesscontrol.R;
import android.support.v4.app.Fragment;

public class VehicleDetailsFragment extends Fragment {
    private static final String ARG_VEHICLE_DETAILS = "vehicle_details";

    private VehicleDetails mVehicleDetails;

    public static VehicleDetailsFragment newInstance(VehicleDetails vehicleDetails) {
        VehicleDetailsFragment fragment = new VehicleDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_VEHICLE_DETAILS, vehicleDetails);
        fragment.setArguments(args);
        return fragment;
    }

    public VehicleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVehicleDetails = (VehicleDetails)getArguments().getSerializable(ARG_VEHICLE_DETAILS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vehicle_details_fragment, container, false);
    }


}
