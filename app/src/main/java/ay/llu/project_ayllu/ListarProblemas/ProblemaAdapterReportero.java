/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu.ListarProblemas;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import java.util.stream.Collectors;

import ay.llu.project_ayllu.InformacionProblema.ProblemaInformacion;
import ay.llu.project_ayllu.R;
import ay.llu.project_ayllu.RegistrarProblema.ProblemaClase;

public class ProblemaAdapterReportero extends ArrayAdapter<ProblemaClase> {
    private List<ProblemaClase> listProblema;
    private List<ProblemaClase> listOriginal;
    private List<String> textList;
    private List<String> textListEstado;
    private Context pcontext;
    private int resourcelayaout;

    public ProblemaAdapterReportero(@NonNull Context context, int resource, List<ProblemaClase> objects){
        super(context, resource, objects);
        this.listProblema = objects;
        this.pcontext = context;
        this.resourcelayaout = resource;

        listOriginal = new ArrayList<>();
        listOriginal.addAll(listProblema);
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

        textListEstado = new ArrayList<String>();
        textListEstado.add("Pendiente");
        textListEstado.add("En progreso..");
        Random randomE = new Random();
        int i = randomE.nextInt(textListEstado.size());
        String estado = textListEstado.get(i);

        ProblemaClase problema = listProblema.get(position);

        TextView txtTitulo,txtDescripcion,txtFecha,txtHora,txtPrioridad,txtEstado;

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtFecha = view.findViewById(R.id.txtFecha);
        txtHora = view.findViewById(R.id.txtHora);
        txtPrioridad = view.findViewById(R.id.txtPrioridad);
        txtEstado = view.findViewById(R.id.txtEstado);

        txtTitulo.setText(problema.getTitulo());
        txtDescripcion.setText(problema.getDescripcion());
        txtFecha.setText(problema.getFecha());
        txtHora.setText(problema.getHora());
        txtPrioridad.setText(prioridad);
        txtEstado.setText(estado);

        String idreportero = problema.getIdReportero();
        String idproblema = problema.getId();
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
                i.putExtra("dnireportero",idreportero);
                i.putExtra("idproblema",idproblema);
                // i.putExtra("titulo",problema.getFecha());
                //Toast.makeText(ProblemaAdapter.this.getContext(), fecha, Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(i);
            }

        });
        return view;
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud==0){
            listProblema.clear();
            listProblema.addAll(listOriginal);
        }else{
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.N){
                List<ProblemaClase>coleccion = listProblema.stream().filter(i -> i.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listProblema.clear();
                listProblema.addAll(coleccion);
            }else{
                for(ProblemaClase d: listOriginal){
                    if(d.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listProblema.add(d);
                    }
                }//for
            }//else
        }//else
        notifyDataSetChanged();
    }//filtrado
}
