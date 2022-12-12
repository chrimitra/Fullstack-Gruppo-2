package com.gruppo2.fullstack.model;

public class Statistiche {
	
	String domanda;
	Double totale;
	Double Media;
	
	public Statistiche(String voto, Double totale, Double media) {
		super();
		this.domanda = voto;
		this.totale = totale;
		Media = media;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String voto) {
		this.domanda = voto;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public Double getMedia() {
		return Media;
	}

	public void setMedia(Double media) {
		Media = media;
	}
	
	

}
