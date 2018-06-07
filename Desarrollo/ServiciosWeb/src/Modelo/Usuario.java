package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByUsuario&Contrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena AND u.nombreUsuario = :nombreUsuario")
})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Size(max = 50)
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Solicitante> solicitanteCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Prestadorservicios> prestadorserviciosCollection;

    public Usuario() {
    }
    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    @XmlTransient
    public Collection<Solicitante> getSolicitanteCollection() {
        return solicitanteCollection;
    }
    public void setSolicitanteCollection(Collection<Solicitante> solicitanteCollection) {
        this.solicitanteCollection = solicitanteCollection;
    }
    @XmlTransient
    public Collection<Prestadorservicios> getPrestadorserviciosCollection() {
        return prestadorserviciosCollection;
    }
    public void setPrestadorserviciosCollection(Collection<Prestadorservicios> prestadorserviciosCollection) {
        this.prestadorserviciosCollection = prestadorserviciosCollection;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Modelo.Usuario[ idUsuario=" + idUsuario + " ]";
    }
}
