package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "LOG")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LOG")
    @SequenceGenerator(name = "SQ_LOG", sequenceName = "SQ_LOG", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_LOG")
    private Long id_log;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PEDIDO",
            referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_LOG"),
            nullable = false
    )
    private Pedido id_pedido;

    @Column(name = "TIMESTAMP_LOG", nullable = false)
    private String timestamp_log;

    @Column(name = "NM_LOG", nullable = false)
    private String nm_log;

    @Column(name = "DS_LOG", nullable = false)
    private String ds_log;

    public Log() {
    }

    public Log(Long id_log, Pedido id_pedido, String timestamp_log, String nm_log, String ds_log) {
        this.id_log = id_log;
        this.id_pedido = id_pedido;
        this.timestamp_log = timestamp_log;
        this.nm_log = nm_log;
        this.ds_log = ds_log;
    }

    public Long getId_log() {
        return id_log;
    }

    public Log setId_log(Long id_log) {
        this.id_log = id_log;
        return this;
    }

    public Pedido getId_pedido() {
        return id_pedido;
    }

    public Log setId_pedido(Pedido id_pedido) {
        this.id_pedido = id_pedido;
        return this;
    }

    public String getTimestamp_log() {
        return timestamp_log;
    }

    public Log setTimestamp_log(String timestamp_log) {
        this.timestamp_log = timestamp_log;
        return this;
    }

    public String getNm_log() {
        return nm_log;
    }

    public Log setNm_log(String nm_log) {
        this.nm_log = nm_log;
        return this;
    }

    public String getDs_log() {
        return ds_log;
    }

    public Log setDs_log(String ds_log) {
        this.ds_log = ds_log;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log log)) return false;

        if (!id_log.equals(log.id_log)) return false;
        if (!id_pedido.equals(log.id_pedido)) return false;
        if (!timestamp_log.equals(log.timestamp_log)) return false;
        if (!nm_log.equals(log.nm_log)) return false;
        return ds_log.equals(log.ds_log);
    }

    @Override
    public int hashCode() {
        int result = id_log.hashCode();
        result = 31 * result + id_pedido.hashCode();
        result = 31 * result + timestamp_log.hashCode();
        result = 31 * result + nm_log.hashCode();
        result = 31 * result + ds_log.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id_log=" + id_log +
                ", id_pedido=" + id_pedido +
                ", timestamp_log='" + timestamp_log + '\'' +
                ", nm_log='" + nm_log + '\'' +
                ", ds_log='" + ds_log + '\'' +
                '}';
    }
}
