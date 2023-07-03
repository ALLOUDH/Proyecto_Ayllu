/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu.ListasAdministrador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ay.llu.project_ayllu.R;

public class ReporteClienteAdapter extends ArrayAdapter<ReportarClass> {
    private List<ReportarClass> listReporte;
    private Context pcontext;
    private int resourcelayaout;

    public ReporteClienteAdapter(@NonNull Context context, int resource, List<ReportarClass> objects){
        super(context, resource, objects);
        this.listReporte = objects;
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


        ReportarClass reporte = listReporte.get(position);

        TextView txtDescripcionListaReporte;

        txtDescripcionListaReporte = view.findViewById(R.id.txtDescripcionListaReporte);

        txtDescripcionListaReporte.setText(reporte.getDescripcion());

        String descripcionReporte = reporte.getDescripcion();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        return view;
    }
}
