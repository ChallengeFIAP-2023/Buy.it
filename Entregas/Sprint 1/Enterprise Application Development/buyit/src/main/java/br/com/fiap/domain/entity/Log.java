package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "BUYIT_LOG")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LOG")
    @SequenceGenerator(name = "SQ_LOG", sequenceName = "SQ_LOG", allocationSize = 1)
    @Column(name = "ID_LOG", columnDefinition = "NUMBER(8)")
    private Long id_log;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PEDIDO",
            columnDefinition = "NUMBER(8)",
            referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_LOG"),
            nullable = false
    )
    private Pedido id_pedido;

    @Column(name = "TIMESTAMP_LOG", nullable = false, columnDefinition = "VARCHAR2(20)")
    private String timestamp_log;

    @Column(name = "NM_LOG", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String nm_log;

    @Column(name = "DS_LOG", nullable = false, columnDefinition = "VARCHAR2(255)")
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
        return Objects.equals(id_log, log.id_log) && Objects.equals(id_pedido, log.id_pedido) && Objects.equals(timestamp_log, log.timestamp_log) && Objects.equals(nm_log, log.nm_log) && Objects.equals(ds_log, log.ds_log);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_log, id_pedido, timestamp_log, nm_log, ds_log);
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
