package cl.carreno.app_ev2.model;

public class Denuncia {
    private String id;
    private String titulo;
    private String direccion;
    private  String estado;

    public Denuncia(){

    }

    public Denuncia(String id, String titulo, String direccion, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
