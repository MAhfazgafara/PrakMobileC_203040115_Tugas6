package com.example.clothingregistration.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothingregistration.R;
import com.example.clothingregistration.database.entitas.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewAdapter> {
    private List<User> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new viewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewAdapter holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.brand.setText(list.get(position).brand);
        holder.year.setText(list.get(position).year);
        holder.price.setText(list.get(position).price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewAdapter extends RecyclerView.ViewHolder {
        TextView name, brand, year, price;

        public viewAdapter(@NonNull View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.name);
            brand = itemView.findViewById(R.id.brand);
            year = itemView.findViewById(R.id.year);
            price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
