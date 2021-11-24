package com.example.inventory2.ui.dependency;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory2.R;
import com.example.inventory2.model.Dependency;

import java.util.ArrayList;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {
    private ArrayList<Dependency> list;

    public DependencyAdapter() {
        this.list = new ArrayList<Dependency>();
        list.add(new Dependency("Aula de 1CFGS", "1CFGS", null, null));
        list.add(new Dependency("Aula de 1CFGM", "1CFGM", null, null));
        list.add(new Dependency("Aula de 2CFGS", "2CFGS", null, null));
        list.add(new Dependency("Aula de 2CFGM", "2CFGM", null, null));
        list.add(new Dependency("Big Data", "BIG", null, null));

    }

    /**
     * Se llamara a este metodo tantas veces como elementos se visualicen en la pantalla
     * del dispositivo movil siempre que getItemCount() > 0
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DependencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
    }

    /**
     * Este metodo devuelve el numero de elementos del Adapter. Es utilizado por el
     * Sistema Operativo cuando se inicializa el componente RecyclerView. Si se deja a
     * 0, NUNCA se muestran los elementos del adapter en el reciclerView ya que no se
     * llama ak metodo OnViewCreateHolder
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
