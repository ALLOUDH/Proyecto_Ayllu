package ay.llu.project_ayllu.chat;

import java.util.Map;

public class MensajeEnviar extends ay.llu.project_ayllu.chat.Mensajes {
    private Map hora;

    public MensajeEnviar() {
    }

    public MensajeEnviar(Map hora) {
        this.hora = hora;
    }

    public MensajeEnviar(String mensajes, String nombre, String fotouser, String mensaje_tipo, Map hora) {
        super(mensajes, nombre, fotouser, mensaje_tipo);
        this.hora = hora;
    }

    public MensajeEnviar(String mensajes, String urlfoto, String nombre, String fotouser, String mensaje_tipo, Map hora) {
        super(mensajes, urlfoto, nombre, fotouser, mensaje_tipo);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
