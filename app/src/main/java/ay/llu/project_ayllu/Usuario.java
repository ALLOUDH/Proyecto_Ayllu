package ay.llu.project_ayllu;

public class Usuario {
    String id,nombre,apellido,correo,celular,carreraprof;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, String correo, String celular, String carreraprof) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.carreraprof = carreraprof;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCarreraprof() {
        return carreraprof;
    }

    public void setCarreraprof(String carreraprof) {
        this.carreraprof = carreraprof;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", carreraprof='" + carreraprof + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
