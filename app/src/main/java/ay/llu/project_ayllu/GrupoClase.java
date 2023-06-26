package ay.llu.project_ayllu;

public class GrupoClase {
    private String id;
    private String idproblema;
    private String titulo;
    private String fecha;
    private String idusuario1;
    private String idusuario2;
    private String idusuario3;
    private String idusuario4;


    public GrupoClase(){
    }

    public GrupoClase(String id, String idproblema, String titulo, String fecha, String idusuario1, String idusuario2, String idusuario3, String idusuario4) {
        this.id = id;
        this.idproblema = idproblema;
        this.titulo = titulo;
        this.fecha = fecha;
        this.idusuario1 = idusuario1;
        this.idusuario2 = idusuario2;
        this.idusuario3 = idusuario3;
        this.idusuario4 = idusuario4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdproblema() {
        return idproblema;
    }

    public void setIdproblema(String idproblema) {
        this.idproblema = idproblema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdusuario1() {
        return idusuario1;
    }

    public void setIdusuario1(String idusuario1) {
        this.idusuario1 = idusuario1;
    }

    public String getIdusuario2() {
        return idusuario2;
    }

    public void setIdusuario2(String idusuario2) {
        this.idusuario2 = idusuario2;
    }

    public String getIdusuario3() {
        return idusuario3;
    }

    public void setIdusuario3(String idusuario3) {
        this.idusuario3 = idusuario3;
    }

    public String getIdusuario4() {
        return idusuario4;
    }

    public void setIdusuario4(String idusuario4) {
        this.idusuario4 = idusuario4;
    }

    @Override
    public String toString() {
        return "GrupoClase{" +
                "id='" + id + '\'' +
                ", idproblema='" + idproblema + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", idusuario1='" + idusuario1 + '\'' +
                ", idusuario2='" + idusuario2 + '\'' +
                ", idusuario3='" + idusuario3 + '\'' +
                ", idusuario4='" + idusuario4 + '\'' +
                '}';
    }
}
