package com.xbrain.testfelipe.restapi.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private Date dataVenda;

    private Double valor;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id" ,name = "vendedor_id", nullable=false)
    private Vendedor vendedor;

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Venda() {

    }

    public Venda(int id, Date dataVenda, Double valor, Vendedor vendedor) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valor = valor;
        this.vendedor = vendedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
