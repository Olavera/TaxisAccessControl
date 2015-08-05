package com.iecisa.taxisaccesscontrol.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iecisa.taxisaccesscontrol.R;
import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author darevalo
 */

public class BlacklistAdapter extends RecyclerView.Adapter<BlacklistAdapter.VehicleHolder> {

    private final BlacklistAdapterCallbacks mCallbacks;
    private final List<Vehicle> mVehicleList = new ArrayList<>();

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
        viewHolder.idListaNegra_tv.setText(Long.toString(rowData.getIdListaNegra()));
        viewHolder.matricula_tv.setText(rowData.getMatricula());
        viewHolder.estado_tv.setText(Integer.toString(rowData.getEstado()));
        viewHolder.fechaInclusion_tv.setText(rowData.getFechaInclusion());

        viewHolder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {
        return mVehicleList.size();
    }

    @Override
    public long getItemId(int position) {
        return mVehicleList.get(position).getIdListaNegra();
    }

    public void removeData (int position) {
        mVehicleList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd, Vehicle vehicle) {
        mVehicleList.add(positionToAdd, vehicle);
        notifyItemInserted(positionToAdd);
    }

    public void appendList(List<Vehicle> vehicleList) {
        int positionStart = mVehicleList.size();
        mVehicleList.addAll(vehicleList);
        notifyItemRangeInserted(positionStart, vehicleList.size());
    }

    public static class VehicleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.idListaNegra_tv)
        public TextView idListaNegra_tv;
        @InjectView(R.id.matricula_tv)
        public TextView matricula_tv;
        @InjectView(R.id.estado_tv)
        public TextView estado_tv;
        @InjectView(R.id.fechaInclusion_tv)
        public TextView fechaInclusion_tv;

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
