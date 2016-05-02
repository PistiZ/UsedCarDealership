package model;

import java.time.Year;

import org.joda.money.Money;

public class Vehicle {

	private String marka;
	private String modell;
	private String tipus;
	private Year evjarat;
	private Money vetelar;
	private Money eladasiAr;
	private long kmOraAllas;
	private Uzemanyag uzemanyag;
	private Allapot allapot;
	private String leiras;

	public Vehicle() {}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public Year getEvjarat() {
		return evjarat;
	}

	public void setEvjarat(Year evjarat) {
		this.evjarat = evjarat;
	}

	public Money getVetelar() {
		return vetelar;
	}

	public void setVetelar(Money vetelar) {
		this.vetelar = vetelar;
	}

	public Money getEladasiAr() {
		return eladasiAr;
	}

	public void setEladasiAr(Money eladasiAr) {
		this.eladasiAr = eladasiAr;
	}

	public long getKmOraAllas() {
		return kmOraAllas;
	}

	public void setKmOraAllas(long kmOraAllas) {
		this.kmOraAllas = kmOraAllas;
	}

	public Uzemanyag getUzemanyag() {
		return uzemanyag;
	}

	public void setUzemanyag(Uzemanyag uzemanyag) {
		this.uzemanyag = uzemanyag;
	}

	public Allapot getAllapot() {
		return allapot;
	}

	public void setAllapot(Allapot allapot) {
		this.allapot = allapot;
	}

	public String getLeiras() {
		return leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}
}