package objetos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "modulo")
public class Modulo implements Serializable {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Un módulo puede tener muchas notas
    @OneToMany(mappedBy = "modulo",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Nota> notas;

    // Un módulo puede estar impartido por varios profesores
    @OneToMany(mappedBy = "modulo",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Imparte> profesores;

    public Modulo() {
    }

    public Modulo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public List<Imparte> getProfesores() {
        return profesores;
    }

    @Override
    public String toString() {
        return "Modulo [codigo=" + codigo +
               ", nombre=" + nombre + "]";
    }
}
