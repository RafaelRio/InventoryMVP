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
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.inventory2.R;
import com.example.inventory2.data.model.DependencyComparator;
import com.example.inventory2.model.Dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {
    private final ArrayList<Dependency> list;
    private final OnManageDependencyListener listener;


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
        //Colores del tema material design
        ColorGenerator generator = ColorGenerator.MATERIAL;
        //Generar un color aleatorio
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getName().substring(0, 1), color);

        holder.icon.setImageDrawable(drawable);

        //Cuando se actualiza la lista se indica a la clase holder que dependencia es y cual es su listener
        holder.bind(list.get(position), listener);
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

    /**
     * Se debe llamar al metodo notifyDataSetChanged(); para que
     * -Anula la vista
     * -Llama al metodo OnDraw() de todos los elementos de la nueva vista
     *
     * @param list
     */
    public void update(List<Dependency> list) {
        this.list.clear();
        this.list.addAll(list);

        //ERROR DE QUE NO ACTUALIZA
        notifyDataSetChanged();
    }

    public void delete(Dependency deleted) {
        list.remove(deleted);
        notifyDataSetChanged();
    }

    /**
     * Ordena la vista en BASE AL METODO COMPARETO DEFINIDA EN EL POJO
     * (en este caso ordena por nombre)
     */
    public void order() {
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void inverseOrder(){
        Collections.reverse(list);
        notifyDataSetChanged();
    }

    /**
     * Ordena la vista en base a un objeto de una clase que implementa la interfaz
     * comparator
     */
    public void orderByDescription() {
        Collections.sort(list, new DependencyComparator());
        notifyDataSetChanged();
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


    //region Metodos que hay que implementar para actualizar la vista

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            icon = itemView.findViewById(R.id.icon);
        }

        /**
         * Todos los metodos que se crean en la clase ViewHolder tienen acceso al elemento
         * View que contienen en la variable itemView
         *
         * @param dependency
         * @param listener
         */
        public void bind(Dependency dependency, OnManageDependencyListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditDependency(dependency);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteDependency(dependency);
                    //Se indica que se consume el evento y se evita la propagacion del evento en otras vistas
                    return true;
                }
            });
        }
    }

    //endregion
}
