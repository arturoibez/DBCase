package modelo.transfers;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;


@SuppressWarnings("rawtypes")
public class TransferAgregacion extends Transfer{

	private int idAgregacion;
	private String nombre;
	private Vector listaRelaciones;
	private Vector listaAtributos;
	private Point2D posicion;
	private int volumen;//?
	private int frecuencia;//?
	private int offsetAttr=0;//?
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdAgregacion() {
		return idAgregacion;
	}
	public void setIdAgregacion(int idAgregacion) {
		this.idAgregacion = idAgregacion;
	}
	public Vector getListaRelaciones() {
		return listaRelaciones;
	}
	public void setListaRelaciones(Vector listaRelaciones) {
		this.listaRelaciones = listaRelaciones;
	}
	public Point2D getPosicion() {
		return posicion;
	}
	public void setPosicion(Point2D posicion) {
		this.posicion = posicion;
	}
	public Vector getListaAtributos() {
		return listaAtributos;
	}
	public void setListaAtributos(Vector listaAtributos) {
		this.listaAtributos = listaAtributos;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Shape toShape() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getVolumen() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getFrecuencia() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setVolumen(int v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setFrecuencia(int f) {
		// TODO Auto-generated method stub
		
	}
	public void CopiarAgregacion(TransferAgregacion agreg) {
		// TODO Auto-generated method stub
		
	}
	public TransferAgregacion clonar() {
		// TODO Auto-generated method stub
		return null;
	}
	
}