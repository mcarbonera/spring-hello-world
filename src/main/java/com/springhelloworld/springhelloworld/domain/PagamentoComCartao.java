package com.springhelloworld.springhelloworld.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.springhelloworld.springhelloworld.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@JsonTypeName("pagamentoComCartao")
@Table(name = "SPRING_PAGAMENTO_COM_CARTAO", schema = "APL_SBJ")
public class PagamentoComCartao extends Pagamento {
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {

    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
