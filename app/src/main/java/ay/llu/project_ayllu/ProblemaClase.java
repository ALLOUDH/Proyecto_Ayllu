package ay.llu.project_ayllu;

public class ProblemaClase {
    private String id;
    private String categoria;
    private String titulo;
    private String descripcion;

    public ProblemaClase(){

    }

    public ProblemaClase(String id, String categoria, String titulo, String descripcion) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "ProblemaClase{" +
                "id='" + id + '\'' +
                ", categoria='" + categoria + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
