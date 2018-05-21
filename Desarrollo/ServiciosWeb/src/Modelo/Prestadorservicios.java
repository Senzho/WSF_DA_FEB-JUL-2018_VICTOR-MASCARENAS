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
@Table(name = "prestadorservicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestadorservicios.findAll", query = "SELECT p FROM Prestadorservicios p")
    , @NamedQuery(name = "Prestadorservicios.findByIdPrestador", query = "SELECT p FROM Prestadorservicios p WHERE p.idPrestador = :idPrestador")
    , @NamedQuery(name = "Prestadorservicios.findByNombrePrestador", query = "SELECT p FROM Prestadorservicios p WHERE p.nombrePrestador = :nombrePrestador")
    , @NamedQuery(name = "Prestadorservicios.findByTelefonoPrestador", query = "SELECT p FROM Prestadorservicios p WHERE p.telefonoPrestador = :telefonoPrestador")
    , @NamedQuery(name = "Prestadorservicios.findByCorreoPrestador", query = "SELECT p FROM Prestadorservicios p WHERE p.correoPrestador = :correoPrestador")
    , @NamedQuery(name = "Prestadorservicios.findByCategoria", query = "SELECT p FROM Prestadorservicios p WHERE p.categoria = :categoria")
    , @NamedQuery(name = "Prestadorservicios.findByFechaNacimiento", query = "SELECT p FROM Prestadorservicios p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Prestadorservicios.findByGeneroPrestador", query = "SELECT p FROM Prestadorservicios p WHERE p.generoPrestador = :generoPrestador")})
public class Prestadorservicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrestador")
    private Integer idPrestador;
    @Size(max = 100)
    @Column(name = "nombrePrestador")
    private String nombrePrestador;
    @Size(max = 10)
    @Column(name = "telefonoPrestador")
    private String telefonoPrestador;
    @Size(max = 100)
    @Column(name = "correoPrestador")
    private String correoPrestador;
    @Column(name = "categoria")
    private Integer categoria;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcionPrestador")
    private String descripcionPrestador;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Lob
    @Size(max = 65535)
    @Column(name = "direccionPrestador")
    private String direccionPrestador;
    @Column(name = "generoPrestador")
    private Integer generoPrestador;
    @OneToMany(mappedBy = "idPrestador")
    private Collection<Solicitud> solicitudCollection;
    @OneToMany(mappedBy = "idPrestador")
    private Collection<Estudio> estudioCollection;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Prestadorservicios() {
    }

    public Prestadorservicios(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getNombrePrestador() {
        return nombrePrestador;
    }

    public void setNombrePrestador(String nombrePrestador) {
        this.nombrePrestador = nombrePrestador;
    }

    public String getTelefonoPrestador() {
        return telefonoPrestador;
    }

    public void setTelefonoPrestador(String telefonoPrestador) {
        this.telefonoPrestador = telefonoPrestador;
    }

    public String getCorreoPrestador() {
        return correoPrestador;
    }

    public void setCorreoPrestador(String correoPrestador) {
        this.correoPrestador = correoPrestador;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getDescripcionPrestador() {
        return descripcionPrestador;
    }

    public void setDescripcionPrestador(String descripcionPrestador) {
        this.descripcionPrestador = descripcionPrestador;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionPrestador() {
        return direccionPrestador;
    }

    public void setDireccionPrestador(String direccionPrestador) {
        this.direccionPrestador = direccionPrestador;
    }

    public Integer getGeneroPrestador() {
        return generoPrestador;
    }

    public void setGeneroPrestador(Integer generoPrestador) {
        this.generoPrestador = generoPrestador;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @XmlTransient
    public Collection<Estudio> getEstudioCollection() {
        return estudioCollection;
    }

    public void setEstudioCollection(Collection<Estudio> estudioCollection) {
        this.estudioCollection = estudioCollection;
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
        hash += (idPrestador != null ? idPrestador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestadorservicios)) {
            return false;
        }
        Prestadorservicios other = (Prestadorservicios) object;
        if ((this.idPrestador == null && other.idPrestador != null) || (this.idPrestador != null && !this.idPrestador.equals(other.idPrestador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Prestadorservicios[ idPrestador=" + idPrestador + " ]";
    }
    
}
