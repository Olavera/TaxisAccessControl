package com.iecisa.taxisaccesscontrol.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.iecisa.model.entities.Vehicle;
import com.iecisa.model.entities.VehicleDetails;
import com.iecisa.taxisaccesscontrol.App;
import com.iecisa.taxisaccesscontrol.R;
import com.iecisa.taxisaccesscontrol.adapters.BlacklistAdapter;
import com.iecisa.taxisaccesscontrol.adapters.BlacklistAdapterCallbacks;
import com.iecisa.taxisaccesscontrol.adapters.DividerItemDecoration;
import com.iecisa.taxisaccesscontrol.mvp.presenters.BlacklistPresenter;
import com.iecisa.taxisaccesscontrol.mvp.views.BlacklistView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BlacklistFragment extends Fragment implements BlacklistView, BlacklistAdapterCallbacks{

    private BlacklistPresenter mBlacklistPresenter = null;

    @InjectView(R.id.blacklist_rlv)
    RecyclerView mBlacklistRlv;
    private BlacklistAdapter mBlacklistAdapter;
    @InjectView(R.id.reconnect_btn)
    Button mReconnectBtn;
    private ProgressDialog mLoadingPd;

    public static BlacklistFragment newInstance() {
        BlacklistFragment fragment = new BlacklistFragment();
        return fragment;
    }

    public BlacklistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blacklist_fragment, container, false);
        ButterKnife.inject(this, view);

        //recyclerView.setHasFixedSize(true);

        // RecyclerView layout manager
        mBlacklistRlv.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBlacklistRlv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        // RecyclerView adapter
        mBlacklistAdapter = new BlacklistAdapter(this);
        mBlacklistRlv.setAdapter(mBlacklistAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        App app = (App) getActivity().getApplication();
        mBlacklistPresenter = new BlacklistPresenter(this, app.getRestDataSource());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mLoadingPd = new ProgressDialog(activity);
        mLoadingPd.setCancelable(false);
        mLoadingPd.setMessage(getString(R.string.str_loading));
        try {
            ((MainActivity) activity).onSectionAttached(getString(R.string.section_blacklist));
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mBlacklistPresenter.start();
    }

    @Override
    public void onStop(){
        mBlacklistPresenter.stop();
        super.onStop();
    }

    @Override
    public void showLoading() {
        mLoadingPd.show();
    }

    @Override
    public void hideLoading() {
        mLoadingPd.dismiss();
    }

    @Override
    public void showConexionError() {
        Toast.makeText(getActivity(), getString(R.string.error_conexion), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNewList(ArrayList<Vehicle> list) {
        mBlacklistAdapter.appendList(list);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), getString(R.string.error_unknown), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showVehicleDetails(VehicleDetails vehicleDetails) {
        ((MainActivity)getActivity()).changeToVehicleDetails(vehicleDetails);
    }

    @Override
    public void OnItemClick(int position) {
        String numberPlate = mBlacklistAdapter.getElemId(position);
        mBlacklistPresenter.getVehicleDetails(numberPlate);
    }
}
