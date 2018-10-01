package com.springproject.vmagri.domain.enums;

public enum StatusPagamento {
	PENDENTE(1, "Pagamento Pendente"),
	EFETUADO(2, "Pagamento Efetuado"),
	CANCELADO(3, "Pagamento Cancelado");
	
	private int cod;
	private String desc;
	
	private StatusPagamento(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;		
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static StatusPagamento getEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (StatusPagamento x : StatusPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID [" + cod + "] for Payment Status!");
	}
}
