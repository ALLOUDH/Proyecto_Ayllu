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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ay.llu.project_ayllu.InformacionProblema.ProblemaInformacion;
import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.RegistrarProblema.ProblemaClase;

public class ProblemaAdapterReportero extends ArrayAdapter<ProblemaClase> {
    private List<ProblemaClase> listProblema;

    private List<String> textList;
    private Context pcontext;
    private int resourcelayaout;

    public ProblemaAdapterReportero(@NonNull Context context, int resource, List<ProblemaClase> objects){
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
        textList = new ArrayList<String>();
        textList.add("Baja");
        textList.add("Media");
        textList.add("Alta");
        Random random = new Random();
        int index = random.nextInt(textList.size());
        String prioridad = textList.get(index);

        ProblemaClase problema = listProblema.get(position);

        TextView txtTitulo,txtDescripcion,txtFecha,txtHora,txtPrioridad;

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtFecha = view.findViewById(R.id.txtFecha);
        txtHora = view.findViewById(R.id.txtHora);
        txtPrioridad = view.findViewById(R.id.txtPrioridad);

        txtTitulo.setText(problema.getTitulo());
        txtDescripcion.setText(problema.getDescripcion());
        txtFecha.setText(problema.getFecha());
        txtHora.setText(problema.getHora());
        txtPrioridad.setText(prioridad);

        String titulo= problema.getTitulo();
        String descripcion = problema.getDescripcion();
        String latitud = problema.getLatitud();
        String longitud = problema.getLongitud();
        String fecha= problema.getFecha();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ProblemaInformacion.class);
                i.putExtra("titulo",titulo);
                i.putExtra("descripcion",descripcion);
                i.putExtra("latitud",latitud);
                i.putExtra("longitud",longitud);
                i.putExtra("fecha",fecha);
                // i.putExtra("titulo",problema.getFecha());
                //Toast.makeText(ProblemaAdapter.this.getContext(), fecha, Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(i);
            }

        });
        return view;
    }
}
