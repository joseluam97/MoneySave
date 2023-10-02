package com.example.moneysave.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Models.Gasto;
import com.example.moneysave.R;

import java.util.List;

public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.GastoViewHolder> {

    private Context context;
    private List<Gasto> listaGastos; // Asume que tienes una clase Gasto para representar tus elementos de datos

    public GastosAdapter(Context context, List<Gasto> listaGastos) {
        this.context = context;
        this.listaGastos = listaGastos;
    }

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_item_list, parent, false);
        return new GastoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        Gasto gasto = listaGastos.get(position);

        // Aquí puedes vincular los datos del gasto a las vistas en el ViewHolder
        holder.textViewNombre.setText(gasto.getNombre());
        holder.textViewImporte.setText(String.valueOf(gasto.getImporte()));
    }

    @Override
    public int getItemCount() {
        return listaGastos.size();
    }

    public class GastoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewImporte;

        public GastoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre); // R.id.textViewNombre debe ser el ID de la vista en tu diseño de elemento de gasto
            textViewImporte = itemView.findViewById(R.id.textViewImporte); // R.id.textViewImporte debe ser el ID de la vista en tu diseño de elemento de gasto
        }
    }
}