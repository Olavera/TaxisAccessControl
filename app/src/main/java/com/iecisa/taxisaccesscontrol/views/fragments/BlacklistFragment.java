package com.iecisa.taxisaccesscontrol.views.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.iecisa.taxisaccesscontrol.TaxiAccessControlApp;
import com.iecisa.taxisaccesscontrol.R;
import com.iecisa.taxisaccesscontrol.views.adapters.BlacklistAdapter;
import com.iecisa.taxisaccesscontrol.views.adapters.BlacklistAdapterCallbacks;
import com.iecisa.taxisaccesscontrol.views.adapters.DividerItemDecoration;
import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.mvp.presenters.BlacklistPresenter;
import com.iecisa.taxisaccesscontrol.mvp.views.BlacklistView;
import com.iecisa.taxisaccesscontrol.views.utils.Constants;
import com.iecisa.taxisaccesscontrol.views.utils.Sections;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BlacklistFragment extends Fragment implements BlacklistView, BlacklistAdapterCallbacks {

    private FragmentCallbacks mListener;

    private BlacklistPresenter mBlacklistPresenter = null;

    @InjectView(R.id.blacklist_rlv)
    RecyclerView mBlacklistRecyclerView;

    private BlacklistAdapter mBlacklistAdapter;
    @InjectView(R.id.reconnect_btn)
    Button mReconnectBtn;
    private ProgressDialog mLoadingPd;

    public static BlacklistFragment newInstance() {
        BlacklistFragment fragment = new BlacklistFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public BlacklistFragment() {
        // Required empty public constructor
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

        initializeProgressDialog();
        initializeBlacklistRecyclerView();
        initializePresenter();

        return view;
    }

    private void initializeProgressDialog(){
        mLoadingPd = new ProgressDialog(getActivity());
        mLoadingPd.setCancelable(false);
        mLoadingPd.setMessage(getString(R.string.str_loading));
    }

    private void initializeBlacklistRecyclerView () {
        //recyclerView.setHasFixedSize(true);

        // RecyclerView layout manager
        mBlacklistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBlacklistRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        // RecyclerView adapter
        mBlacklistAdapter = new BlacklistAdapter(this);
        mBlacklistRecyclerView.setAdapter(mBlacklistAdapter);
    }

    private void initializePresenter() {
        TaxiAccessControlApp app = (TaxiAccessControlApp) getActivity().getApplication();
        mBlacklistPresenter = new BlacklistPresenter(app.getRepository());
        mBlacklistPresenter.attachView(this);
        mBlacklistPresenter.attachBundle(getArguments());
        mBlacklistPresenter.initializePresenter();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentCallbacks) activity;
            mListener.onSectionAttached(getString(R.string.str_section_blacklist));
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mBlacklistPresenter.onStart();
    }

    @Override
    public void onStop(){
        mBlacklistPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.VEHICLE_ID, mBlacklistAdapter.getItemId(position));
        mListener.switchFragment(Sections.VEHICLE_DETAILS_FRAGMENT, bundle);
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
    public void showNewList(List<Vehicle> vehicles) {
        mBlacklistAdapter.appendList(vehicles);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), getString(R.string.error_unknown), Toast.LENGTH_LONG).show();
    }
}
