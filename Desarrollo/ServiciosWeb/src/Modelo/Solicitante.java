/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "solicitante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitante.findAll", query = "SELECT s FROM Solicitante s")
    , @NamedQuery(name = "Solicitante.findByIdSolicitante", query = "SELECT s FROM Solicitante s WHERE s.idSolicitante = :idSolicitante")
    , @NamedQuery(name = "Solicitante.findByNombreSolicitante", query = "SELECT s FROM Solicitante s WHERE s.nombreSolicitante = :nombreSolicitante")
    , @NamedQuery(name = "Solicitante.findByCorreoSolicitante", query = "SELECT s FROM Solicitante s WHERE s.correoSolicitante = :correoSolicitante")
    , @NamedQuery(name = "Solicitante.findByTelefonoSolicitante", query = "SELECT s FROM Solicitante s WHERE s.telefonoSolicitante = :telefonoSolicitante")
    , @NamedQuery(name = "Solicitante.findByFechaNacimiento", query = "SELECT s FROM Solicitante s WHERE s.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Solicitante.findByTipo", query = "SELECT s FROM Solicitante s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "Solicitante.findByGeneroSolicitante", query = "SELECT s FROM Solicitante s WHERE s.generoSolicitante = :generoSolicitante")
    , @NamedQuery(name = "Solicitante.findByIdUsuario", query = "SELECT s FROM Solicitante s WHERE s.idUsuario.idUsuario = :idUsuario")
})
public class Solicitante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitante")
    private Integer idSolicitante;
    @Size(max = 100)
    @Column(name = "nombreSolicitante")
    private String nombreSolicitante;
    @Size(max = 100)
    @Column(name = "correoSolicitante")
    private String correoSolicitante;
    @Size(max = 10)
    @Column(name = "telefonoSolicitante")
    private String telefonoSolicitante;
    @Lob
    @Size(max = 65535)
    @Column(name = "direccionSolicitante")
    private String direccionSolicitante;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "generoSolicitante")
    private Integer generoSolicitante;
    @OneToMany(mappedBy = "idSolicitante")
    private Collection<Solicitud> solicitudCollection;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Solicitante() {
    }

    public Solicitante(Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public String getTelefonoSolicitante() {
        return telefonoSolicitante;
    }

    public void setTelefonoSolicitante(String telefonoSolicitante) {
        this.telefonoSolicitante = telefonoSolicitante;
    }

    public String getDireccionSolicitante() {
        return direccionSolicitante;
    }

    public void setDireccionSolicitante(String direccionSolicitante) {
        this.direccionSolicitante = direccionSolicitante;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getGeneroSolicitante() {
        return generoSolicitante;
    }

    public void setGeneroSolicitante(Integer generoSolicitante) {
        this.generoSolicitante = generoSolicitante;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitante != null ? idSolicitante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitante)) {
            return false;
        }
        Solicitante other = (Solicitante) object;
        if ((this.idSolicitante == null && other.idSolicitante != null) || (this.idSolicitante != null && !this.idSolicitante.equals(other.idSolicitante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Solicitante[ idSolicitante=" + idSolicitante + " ]";
    }
    
}
