package ay.llu.project_ayllu.chat;

public class Mensajes {
    private String Mensajes;
    private String urlfoto;
    private String Nombre;
    private String fotouser;
    private String mensaje_tipo;

    public Mensajes() {
    }

    public Mensajes(String mensajes, String nombre, String fotouser, String mensaje_tipo) {
        Mensajes = mensajes;
        Nombre = nombre;
        this.fotouser = fotouser;
        this.mensaje_tipo = mensaje_tipo;
    }

    public Mensajes(String mensajes, String urlfoto, String nombre, String fotouser, String mensaje_tipo) {
        Mensajes = mensajes;
        this.urlfoto = urlfoto;
        Nombre = nombre;
        this.fotouser = fotouser;
        this.mensaje_tipo = mensaje_tipo;
    }

    public String getMensajes() {
        return Mensajes;
    }

    public void setMensajes(String mensajes) {
        Mensajes = mensajes;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFotouser() {
        return fotouser;
    }

    public void setFotouser(String fotouser) {
        this.fotouser = fotouser;
    }

    public String getMensaje_tipo() {
        return mensaje_tipo;
    }

    public void setMensaje_tipo(String mensaje_tipo) {
        this.mensaje_tipo = mensaje_tipo;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}
