package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "posicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posicion.findAll", query = "SELECT p FROM Posicion p")
    , @NamedQuery(name = "Posicion.findByIdPosicion", query = "SELECT p FROM Posicion p WHERE p.idPosicion = :idPosicion")
    , @NamedQuery(name = "Posicion.findByTipo", query = "SELECT p FROM Posicion p WHERE p.tipo = :tipo")
    , @NamedQuery(name = "Posicion.findByIdTipo", query = "SELECT p FROM Posicion p WHERE p.idTipo = :idTipo")
    , @NamedQuery(name = "Posicion.findByLongitud", query = "SELECT p FROM Posicion p WHERE p.longitud = :longitud")
    , @NamedQuery(name = "Posicion.findByLatitud", query = "SELECT p FROM Posicion p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Posicion.findBySolicitante", query = "SELECT p FROM Posicion p WHERE p.tipo = 0 AND p.idTipo = :idSolicitante")
    , @NamedQuery(name = "Posicion.findByPrestador", query = "SELECT p FROM Posicion p WHERE p.tipo = 1 AND p.idTipo = :idPrestador")
})
public class Posicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPosicion")
    private Integer idPosicion;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "idTipo")
    private Integer idTipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "latitud")
    private Double latitud;

    public Posicion() {
    }
    public Posicion(Integer idPosicion) {
        this.idPosicion = idPosicion;
    }

    public Integer getIdPosicion() {
        return idPosicion;
    }
    public void setIdPosicion(Integer idPosicion) {
        this.idPosicion = idPosicion;
    }
    public Integer getTipo() {
        return tipo;
    }
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    public Integer getIdTipo() {
        return idTipo;
    }
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPosicion != null ? idPosicion.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posicion)) {
            return false;
        }
        Posicion other = (Posicion) object;
        if ((this.idPosicion == null && other.idPosicion != null) || (this.idPosicion != null && !this.idPosicion.equals(other.idPosicion))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Modelo.Posicion[ idPosicion=" + idPosicion + " ]";
    }
}
