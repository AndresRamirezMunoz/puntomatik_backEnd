package com.soft2.entity;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Infraccion {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String direccion;

	@Column
	private Date fecha;

	@Column
	private String descripcion;

	@Column
	private int valor;

	@Column
	private int paga;

	@Column
	private int puntosPerdidos;

	@Column
	private long cedulaConductor;

	@Column
	private long cedulaAgente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPaga() {
		return paga;
	}

	public void setPaga(int paga) {
		this.paga = paga;
	}

	public int getPuntosPerdidos() {
		return puntosPerdidos;
	}

	public void setPuntosPerdidos(int puntosPerdidos) {
		this.puntosPerdidos = puntosPerdidos;
	}

	public long getCedulaConductor() {
		return cedulaConductor;
	}

	public void setCedulaConductor(long cedulaConductor) {
		this.cedulaConductor = cedulaConductor;
	}

	public long getCedulaAgente() {
		return cedulaAgente;
	}

	public void setCedulaAgente(long cedulaAgente) {
		this.cedulaAgente = cedulaAgente;
	}

	/**
	 * cedulaAgente: "345435" 
	 * cedulaConductor: "35435" 
	 * descripcion: "Exceso de velocidad" 
	 * direccion: "5435435" 
	 * fecha: null 
	 * id: null 
	 * paga: null
	 * puntosPerdidos: null 
	 * valor: null
	 */
	public void calcularDatos(int SMLV, int[] MULTAS, String[] TIPOMULTA ) {

		paga=0;
		fecha=new Date(Calendar.getInstance().getTime().getTime());

		if (descripcion.equals(TIPOMULTA[0])) {
			valor=MULTAS[0]*SMLV;
			puntosPerdidos=MULTAS[0];
		} else if (descripcion.equals(TIPOMULTA[1])) {
			valor=MULTAS[1]*SMLV;
			puntosPerdidos=MULTAS[1];
		} else if (descripcion.equals(TIPOMULTA[2])) {
			valor=MULTAS[2]*SMLV;
			puntosPerdidos=MULTAS[2];
		} else if (descripcion.equals(TIPOMULTA[3])) {
			valor=MULTAS[3]*SMLV;
			puntosPerdidos=MULTAS[3];
		} else if (descripcion.equals(TIPOMULTA[4])) {
			valor=MULTAS[4]*SMLV;
			puntosPerdidos=MULTAS[4];
		} else if (descripcion.equals(TIPOMULTA[5])) {
			valor=MULTAS[5]*SMLV;
			puntosPerdidos=MULTAS[5];
		} else if (descripcion.equals(TIPOMULTA[6])) {
			valor=MULTAS[6]*SMLV;
			puntosPerdidos=MULTAS[6];
		} else if (descripcion.equals(TIPOMULTA[7])) {
			valor=MULTAS[7]*SMLV;
			puntosPerdidos=MULTAS[7];
		}
	}
	
	public void calcularDatosPrueba(int SMLV, int[] MULTAS, String[] TIPOMULTA ) {

		paga=0;


		if (descripcion.equals(TIPOMULTA[0])) {
			valor=MULTAS[0]*SMLV;
			puntosPerdidos=MULTAS[0];
		} else if (descripcion.equals(TIPOMULTA[1])) {
			valor=MULTAS[1]*SMLV;
			puntosPerdidos=MULTAS[1];
		} else if (descripcion.equals(TIPOMULTA[2])) {
			valor=MULTAS[2]*SMLV;
			puntosPerdidos=MULTAS[2];
		} else if (descripcion.equals(TIPOMULTA[3])) {
			valor=MULTAS[3]*SMLV;
			puntosPerdidos=MULTAS[3];
		} else if (descripcion.equals(TIPOMULTA[4])) {
			valor=MULTAS[4]*SMLV;
			puntosPerdidos=MULTAS[4];
		} else if (descripcion.equals(TIPOMULTA[5])) {
			valor=MULTAS[5]*SMLV;
			puntosPerdidos=MULTAS[5];
		} else if (descripcion.equals(TIPOMULTA[6])) {
			valor=MULTAS[6]*SMLV;
			puntosPerdidos=MULTAS[6];
		} else if (descripcion.equals(TIPOMULTA[7])) {
			valor=MULTAS[7]*SMLV;
			puntosPerdidos=MULTAS[7];
		}
	}
//	public static void main(String[] args) {
//		Date fecha = new Date(Calendar.getInstance().getTime().getTime());
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		System.out.println("Fecha: "+dateFormat.format(fecha));
//	}
}
