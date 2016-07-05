package br.com.thiengo.tcmaterialdesign.domain;

import java.util.Date;

public class Comanda {

	private int codComanda;
	private String nome;
	private String data;
	private String status;

	public Comanda(){

	}
	public Comanda(String nome, String data, String status){
		this.nome = nome;
		this.data = data;
		this.status = status;
	}

	public Comanda(int codComanda, String nome){
		this.codComanda = codComanda;
		this.nome = nome;
	}

	public String getCodComanda() {
		return String.valueOf(codComanda);
	}
	
	public void setCodComanda(int codComanda) {
		this.codComanda = codComanda;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}



}
