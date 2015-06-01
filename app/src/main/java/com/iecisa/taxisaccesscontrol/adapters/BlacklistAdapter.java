package com.iecisa.taxisaccesscontrol.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iecisa.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author darevalo
 */

public class BlacklistAdapter extends RecyclerView.Adapter<BlacklistAdapter.VehicleHolder> {

    private final BlacklistAdapterCallbacks mCallbacks;
    private final ArrayList<Vehicle> mVehicleList = new ArrayList<>();

    public BlacklistAdapter(BlacklistAdapterCallbacks callbacks){
        super();
        this.mCallbacks = callbacks;
    }

    @Override
    public VehicleHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.blacklist_row, parentViewGroup, false);

        return new VehicleHolder (rowView, mCallbacks);
    }

    @Override
    public void onBindViewHolder(VehicleHolder viewHolder, final int position) {

        final Vehicle rowData = mVehicleList.get(position);
        viewHolder.plateNumber_tv.setText(rowData.getNumberPlate());
        viewHolder.make_tv.setText(rowData.getMake());
        viewHolder.model_tv.setText(rowData.getModel());
        viewHolder.color_tv.setText(rowData.getColor());

        viewHolder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {
        return mVehicleList.size();
    }

    public String getElemId(int position) {
        return mVehicleList.get(position).getNumberPlate();
    }

    public void removeData (int position) {
        mVehicleList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd, Vehicle vehicle) {
        mVehicleList.add(positionToAdd, vehicle);
        notifyItemInserted(positionToAdd);
    }

    public void appendList(ArrayList<Vehicle> vehicleList) {
        int positionStart = mVehicleList.size();
        mVehicleList.addAll(vehicleList);
        notifyItemRangeInserted(positionStart, vehicleList.size());
    }

    public static class VehicleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.plateNumber_tv)
        public TextView plateNumber_tv;
        @InjectView(R.id.make_tv)
        public TextView make_tv;
        @InjectView(R.id.model_tv)
        public TextView model_tv;
        @InjectView(R.id.color_tv)
        public TextView color_tv;

        private BlacklistAdapterCallbacks mCallbacks;

        public VehicleHolder(View itemView, BlacklistAdapterCallbacks callbacks) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            this.mCallbacks = callbacks;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCallbacks.OnItemClick(getPosition());
        }
    }

}
