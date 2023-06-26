package ay.llu.project_ayllu.ListasAdministrador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.Usuario;

public class PerfilesUsuariosAdapter extends ArrayAdapter<Usuario> {

    private List<Usuario> listPerfiles;
    private Context pcontext;
    private int resourcelayaout;

    public PerfilesUsuariosAdapter(@NonNull Context context, int resource, List<Usuario> objects){
        super(context, resource, objects);
        this.listPerfiles = objects;
        this.pcontext = context;
        this.resourcelayaout = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(pcontext).inflate(resourcelayaout, null);
        }

        Usuario perfil = listPerfiles.get(position);

        TextView txtNombre,txtCorreo;

        txtNombre = view.findViewById(R.id.txtNombre);
        txtCorreo = view.findViewById(R.id.txtCorreo);

        txtNombre.setText(perfil.getNombre());
        txtCorreo.setText(perfil.getCorreo());

        String iduser = perfil.getIduser();
        String nombre = perfil.getNombre();
        String apellido = perfil.getApellido();
        String celular= perfil.getCelular();
        String correo= perfil.getCorreo();
        String carreraprof = perfil.getCarreraprof();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PerfilInformacion.class);
                i.putExtra("iduser",iduser);
                i.putExtra("nombre",nombre);
                i.putExtra("apellido",apellido);
                i.putExtra("celular",celular);
                i.putExtra("correo",correo);
                i.putExtra("carreraprof",carreraprof);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(i);
            }

        });
        return view;
    }
}
