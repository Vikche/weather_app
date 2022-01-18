package com.vikche.weatherforecast.forecastlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vikche.weatherforecast.R;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private CardDSInterface dataSource;
    private OnItemClickListener onItemClickListener;

    public CardAdapter(CardDSInterface dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        if (onItemClickListener != null) {
            viewHolder.setOnCLickListener(onItemClickListener);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        Card card = dataSource.getCard(position);
        holder.setData(card.getDay());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void SetOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day_tv);
        }

        public void setOnCLickListener(final OnItemClickListener listener) {
            day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    listener.onItemClick(v,adapterPosition);
                }
            });
        }

        public void setData(String day) {
            getDay().setText(day);
        }

        public TextView getDay() {
            return day;
        }

    }
}
