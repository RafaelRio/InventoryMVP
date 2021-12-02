package com.example.inventory2.ui.dependency;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.inventory2.R;
import com.example.inventory2.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {
    private ArrayList<Dependency> list;
    private OnManageDependencyListener listener;


    /**
     * En el constructor por parte de la vista se debe indicar
     * -Los datos
     * -Quien es el listener
     */
    public DependencyAdapter(ArrayList<Dependency> list, OnManageDependencyListener listener) {
        this.list = list;
        this.listener = listener;
    }

    /**
     * Se llamara a este metodo tantas veces como elementos se visualicen en la pantalla
     * del dispositivo movil siempre que getItemCount() > 0
     *
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
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(list.get(position).getName().substring(0,1), Color.BLUE);


        holder.icon.setImageDrawable(drawable);
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



    /**
     * Esta interfaz debe implementarla aquellas clases que quieran escuchar los
     * eventos que ocurren en la lista: editar y eliminar
     */
    public interface OnManageDependencyListener {
        //Si se hace click en una dependencia se edita (onClickListener)
        void onEditDependency(Dependency dependency);

        //Si se hace una pulsacion larga en la dependencia se elimina (onLongClickListener)
        void onDeleteDependency(Dependency dependency);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            icon = itemView.findViewById(R.id.icon);
        }
    }


    //region Metodos que hay que implementar para actualizar la vista

    /**
     * Se debe llamar al metodo notifyDataSetChanged(); para que
     *  -Anula la vista
     *  -Llama al metodo OnDraw() de todos los elementos de la nueva vista
     * @param list
     */
    public void update(List<Dependency> list) {
        this.list.clear();
        this.list.addAll(list);

        //ERROR DE QUE NO ACTUALIZA
        notifyDataSetChanged();
    }

    //endregion
}
