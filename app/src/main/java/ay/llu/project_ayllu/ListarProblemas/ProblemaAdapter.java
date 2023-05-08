package ay.llu.project_ayllu.ListarProblemas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ay.llu.project_ayllu.Login;
import ay.llu.project_ayllu.R;
import java.util.List;

import ay.llu.project_ayllu.ProblemaClase;
import ay.llu.project_ayllu.problemaClaseP;

public class ProblemaAdapter extends ArrayAdapter<ProblemaClase>{
    private List<ProblemaClase> listProblema;
    private Context pcontext;
    private int resourcelayaout;

    public ProblemaAdapter(@NonNull Context context, int resource, List<ProblemaClase> objects){
        super(context, resource, objects);
        this.listProblema = objects;
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
        ProblemaClase problema = listProblema.get(position);

        TextView txtTitulo,txtDescripcion;

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);


        txtTitulo.setText(problema.getTitulo());
        txtDescripcion.setText(problema.getDescripcion());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Login.class);
                i.putExtra("titulo",txtTitulo.getText().toString());
                i.putExtra("descripcion",txtDescripcion.getText().toString());

                view.getContext().startActivity(i);
            }
        });
        return view;
    }
}
