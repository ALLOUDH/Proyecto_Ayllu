/*ESTE PROYECTO FUE REALIZADO POR:
    Chávez Pérez Héctor
    Muñico Tadeo Layoned
    Soto Montes Jesús
*/
package ay.llu.project_ayllu;

public class ReporteroClase {
    private String DNI;
    private String Nombres;
    private String Contraseña;
    private String Telefono;
    private String Departamento;
    private String Provincia;
    private String Distrito;

    public ReporteroClase(){
    }

    public ReporteroClase(String DNI, String nombres, String contraseña, String telefono, String departamento, String provincia, String distrito) {
        this.DNI = DNI;
        Nombres = nombres;
        Contraseña = contraseña;
        Telefono = telefono;
        Departamento = departamento;
        Provincia = provincia;
        Distrito = distrito;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    @Override
    public String toString() {
        return "ReporteroClase{" +
                "DNI='" + DNI + '\'' +
                ", Nombres='" + Nombres + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Departamento='" + Departamento + '\'' +
                ", Provincia='" + Provincia + '\'' +
                ", Distrito='" + Distrito + '\'' +
                '}';
    }
}

