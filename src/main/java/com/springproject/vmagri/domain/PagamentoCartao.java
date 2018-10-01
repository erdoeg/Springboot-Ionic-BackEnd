package com.springproject.vmagri.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.springproject.vmagri.domain.enums.StatusPagamento;

@Entity
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;
	private Date dataPagamento;

	public PagamentoCartao() {

	}

	public PagamentoCartao(Integer id, StatusPagamento statusPagamento, Pedido pedido, Integer numeroParcelas,
			Date dataPagamento) {
		super(id, statusPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
		this.dataPagamento = dataPagamento;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
