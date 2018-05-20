package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "estudio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudio.findAll", query = "SELECT e FROM Estudio e")
    , @NamedQuery(name = "Estudio.findByIdEstudio", query = "SELECT e FROM Estudio e WHERE e.idEstudio = :idEstudio")})
public class Estudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstudio")
    private Integer idEstudio;
    @Lob
    @Size(max = 65535)
    @Column(name = "estudio")
    private String estudio;
    @JoinColumn(name = "idPrestador", referencedColumnName = "idPrestador")
    @ManyToOne
    private Prestadorservicios idPrestador;

    public Estudio() {
    }

    public Estudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }
    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }
    public String getEstudio() {
        return estudio;
    }
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
    public Prestadorservicios getIdPrestador() {
        return idPrestador;
    }
    public void setIdPrestador(Prestadorservicios idPrestador) {
        this.idPrestador = idPrestador;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudio != null ? idEstudio.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudio)) {
            return false;
        }
        Estudio other = (Estudio) object;
        if ((this.idEstudio == null && other.idEstudio != null) || (this.idEstudio != null && !this.idEstudio.equals(other.idEstudio))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Modelo.Estudio[ idEstudio=" + idEstudio + " ]";
    }
}
