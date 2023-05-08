package ay.llu.project_ayllu.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ay.llu.project_ayllu.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMensajes extends RecyclerView.ViewHolder {
    private TextView txtv_nombreuser;
    private TextView txtv_mensaje;
    private TextView txtv_hora_mensaje;
    private CircleImageView civ_fotouser;
    private ImageView imgv_imagenmensaje;

    public HolderMensajes(@NonNull View itemView) {
        super(itemView);
        txtv_nombreuser = (TextView) itemView.findViewById(R.id.CV_nombreusuario);
        txtv_mensaje = (TextView) itemView.findViewById(R.id.CV_mensaje);
        txtv_hora_mensaje = (TextView) itemView.findViewById(R.id.CV_hora);
        civ_fotouser = (CircleImageView) itemView.findViewById(R.id.CV_fotouser);
        imgv_imagenmensaje = (ImageView) itemView.findViewById(R.id.CV_imagen);
    }

    public TextView getTxtv_nombreuser() {
        return txtv_nombreuser;
    }

    public void setTxtv_nombreuser(TextView txtv_nombreuser) {
        this.txtv_nombreuser = txtv_nombreuser;
    }

    public TextView getTxtv_mensaje() {
        return txtv_mensaje;
    }

    public void setTxtv_mensaje(TextView txtv_mensaje) {
        this.txtv_mensaje = txtv_mensaje;
    }

    public TextView getTxtv_hora_mensaje() {
        return txtv_hora_mensaje;
    }

    public void setTxtv_hora_mensaje(TextView txtv_hora_mensaje) {
        this.txtv_hora_mensaje = txtv_hora_mensaje;
    }

    public CircleImageView getCiv_fotouser() {
        return civ_fotouser;
    }

    public void setCiv_fotouser(CircleImageView civ_fotouser) {
        this.civ_fotouser = civ_fotouser;
    }

    public ImageView getImgv_imagenmensaje() {
        return imgv_imagenmensaje;
    }

    public void setImgv_imagenmensaje(ImageView imgv_imagenmensaje) {
        this.imgv_imagenmensaje = imgv_imagenmensaje;
    }
}
