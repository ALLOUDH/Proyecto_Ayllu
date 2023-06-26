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

import ay.llu.project_ayllu.GrupoClase;
import ay.llu.project_ayllu.R;

public class ListarGruposAdapter extends ArrayAdapter<GrupoClase> {
    private List<GrupoClase> listGrupo;
    private Context pcontext;
    private int resourcelayaout;

    public ListarGruposAdapter(@NonNull Context context, int resource, List<GrupoClase> objects){
        super(context, resource, objects);
        this.listGrupo = objects;
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

        GrupoClase grupo = listGrupo.get(position);

        TextView txtTitulo,idgrupo,txtFecha;

        txtTitulo = view.findViewById(R.id.txtTituloGrupos);
        idgrupo = view.findViewById(R.id.txtIDGrupos);
        txtFecha = view.findViewById(R.id.txtFechaGrupos);

        txtTitulo.setText(grupo.getTitulo());
        idgrupo.setText(grupo.getId());
        txtFecha.setText(grupo.getFecha());

        String idgroup = grupo.getId();
        String idproblema = grupo.getIdproblema();
        String titulo= grupo.getTitulo();
        String fecha= grupo.getFecha();
        String idusuario1 = grupo.getIdusuario1();
        String idusuario2 = grupo.getIdusuario2();
        String idusuario3 = grupo.getIdusuario3();
        String idusuario4 = grupo.getIdusuario4();


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), GrupoInformacion.class);
                i.putExtra("idgrupo",idgroup);
                i.putExtra("idproblema",idproblema);
                i.putExtra("titulo",titulo);
                i.putExtra("fecha",fecha);
                i.putExtra("idusuario1",idusuario1);
                i.putExtra("idusuario2",idusuario2);
                i.putExtra("idusuario3",idusuario3);
                i.putExtra("idusuario4",idusuario4);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(i);
            }

        });
        return view;
    }
}
