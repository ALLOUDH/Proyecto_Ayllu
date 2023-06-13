package ay.llu.project_ayllu.RegistrarProblema;

public class ProblemaClase {
    private String id;
    private String categoria;
    private String titulo;
    private String descripcion;
    private String latitud;
    private String longitud;
    private String fecha;
    private String hora;

    private String idReportero;

    private String idFotoProblema;

    private String idVideoProblema;




    public ProblemaClase(){
    }

    public ProblemaClase(String id, String categoria, String titulo, String descripcion, String latitud, String longitud, String fecha, String hora, String idReportero, String idFotoProblema, String idVideoProblema) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.hora = hora;
        this.idReportero = idReportero;
        this.idFotoProblema = idFotoProblema;
        this.idVideoProblema = idVideoProblema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdReportero() {
        return idReportero;
    }

    public void setIdReportero(String idReportero) {
        this.idReportero = idReportero;
    }

    public String getIdFotoProblema() {
        return idFotoProblema;
    }

    public void setIdFotoProblema(String idFotoProblema) {
        this.idFotoProblema = idFotoProblema;
    }

    public String getIdVideoProblema() {
        return idVideoProblema;
    }

    public void setIdVideoProblema(String idVideoProblema) {
        this.idVideoProblema = idVideoProblema;
    }

    @Override
    public String toString() {
        return "ProblemaClase{" +
                "id='" + id + '\'' +
                ", categoria='" + categoria + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", idReportero='" + idReportero + '\'' +
                ", idFotoProblema='" + idFotoProblema + '\'' +
                ", idVideoProblema='" + idVideoProblema + '\'' +
                '}';
    }
}
