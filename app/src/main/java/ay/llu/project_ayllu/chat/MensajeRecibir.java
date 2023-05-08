package ay.llu.project_ayllu.chat;

public class MensajeRecibir extends ay.llu.project_ayllu.chat.Mensajes {
    private Long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensajes, String urlfoto, String nombre, String fotouser, String mensaje_tipo, Long hora) {
        super(mensajes, urlfoto, nombre, fotouser, mensaje_tipo);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
