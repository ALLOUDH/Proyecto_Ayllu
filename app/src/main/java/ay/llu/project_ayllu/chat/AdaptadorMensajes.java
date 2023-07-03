/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ay.llu.project_ayllu.R;

public class AdaptadorMensajes extends RecyclerView.Adapter<HolderMensajes> {

    private List<MensajeRecibir> listmensaje = new ArrayList<>();
    private Context context;

    public AdaptadorMensajes(Context context) {
        this.context = context;
    }

    public void add_mensaje(MensajeRecibir mensajes){
        listmensaje.add(mensajes);
        notifyItemInserted(listmensaje.size());
    }

    @NonNull
    @Override
    public HolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_mensajes,parent,false);
        return new HolderMensajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensajes holder, int position) {
        holder.getTxtv_nombreuser().setText(listmensaje.get(position).getNombre());
        holder.getTxtv_mensaje().setText(listmensaje.get(position).getMensajes());
        if (listmensaje.get(position).getMensaje_tipo().equals("2")){
            holder.getImgv_imagenmensaje().setVisibility(View.VISIBLE);
            holder.getTxtv_mensaje().setVisibility(View.VISIBLE);
            Glide.with(context).load(listmensaje.get(position).getUrlfoto()).into(holder.getImgv_imagenmensaje());
        } else if (listmensaje.get(position).getMensaje_tipo().equals("1")) {
            holder.getImgv_imagenmensaje().setVisibility(View.GONE);
            holder.getTxtv_mensaje().setVisibility(View.VISIBLE);
        }
        Long codigoHora = listmensaje.get(position).getHora();
        Date date = new Date(codigoHora);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        holder.getTxtv_hora_mensaje().setText(simpleDateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return listmensaje.size();
    }
}
