package ay.llu.project_ayllu;

public class Problema {
    private String id;
    private String problema;

    public Problema(){
    }

    public Problema(String id, String problema) {
        this.id = id;
        this.problema = problema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    @Override
    public String toString() {
        return "Problema{" +
                "id='" + id + '\'' +
                ", problema='" + problema + '\'' +
                '}';
    }
}
