package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitud.findByFechaRealizacion", query = "SELECT s FROM Solicitud s WHERE s.fechaRealizacion = :fechaRealizacion")
    , @NamedQuery(name = "Solicitud.findByFecha", query = "SELECT s FROM Solicitud s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "Solicitud.findByFechaInicial", query = "SELECT s FROM Solicitud s WHERE s.fechaInicial = :fechaInicial")
    , @NamedQuery(name = "Solicitud.findByEstrellas", query = "SELECT s FROM Solicitud s WHERE s.estrellas = :estrellas")
    , @NamedQuery(name = "Solicitud.findByEstado", query = "SELECT s FROM Solicitud s WHERE s.estado = :estado")})
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitud")
    private Integer idSolicitud;
    @Column(name = "fechaRealizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaRealizacion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fechaInicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "estrellas")
    private Integer estrellas;
    @Column(name = "estado")
    private Integer estado;
    @Lob
    @Size(max = 65535)
    @Column(name = "comentario")
    private String comentario;
    @Lob
    @Size(max = 65535)
    @Column(name = "respuesta")
    private String respuesta;
    @JoinColumn(name = "idSolicitante", referencedColumnName = "idSolicitante")
    @ManyToOne
    private Solicitante idSolicitante;
    @JoinColumn(name = "idPrestador", referencedColumnName = "idPrestador")
    @ManyToOne
    private Prestadorservicios idPrestador;

    public Solicitud() {
    }

    public Solicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getFechaInicial() {
        return fechaInicial;
    }
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    public Integer getEstrellas() {
        return estrellas;
    }
    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    public Solicitante getIdSolicitante() {
        return idSolicitante;
    }
    public void setIdSolicitante(Solicitante idSolicitante) {
        this.idSolicitante = idSolicitante;
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
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Modelo.Solicitud[ idSolicitud=" + idSolicitud + " ]";
    }
}
