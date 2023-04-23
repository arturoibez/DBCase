package modelo.servicios;

import java.util.Iterator;
import java.util.Vector;

import controlador.Controlador;
import controlador.TC;
import modelo.transfers.TransferAgregacion;
import modelo.transfers.TransferEntidad;
import modelo.transfers.TransferRelacion;
import persistencia.DAOAgregaciones;
import persistencia.DAOEntidades;
import persistencia.DAORelaciones;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ServiciosAgregaciones {
	private Controlador controlador;
	
	//Devuelve actualizada la lista de agregaciones
	public Vector <TransferAgregacion> ListaDeAgregaciones(){
		// Creamos el DAO de agregaciones
		DAOAgregaciones dao = new DAOAgregaciones(this.controlador.getPath());
		// Utilizando el DAO obtenemos la lista de agregaciones
		Vector <TransferAgregacion> lista_relaciones = dao.ListaDeAgregaciones();
		// Se lo devolvemos al controlador
		return lista_relaciones;
	}
	
	public void anadirAgregacion(TransferAgregacion ta){
		if (ta.getNombre().isEmpty()){
			controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreVacio, null);
			return;
		}
		DAOAgregaciones daoAgregaciones = new DAOAgregaciones(this.controlador.getPath());
		Vector<TransferAgregacion> lista = daoAgregaciones.ListaDeAgregaciones();
		for (Iterator it = lista.iterator(); it.hasNext(); ){
			TransferAgregacion elem_tr = (TransferAgregacion)it.next();
			if (elem_tr.getNombre().toLowerCase().equals(ta.getNombre().toLowerCase())){
				controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreDeYaExiste,ta);
				return;
			}
		}
		DAOEntidades daoEntidades = new DAOEntidades(this.controlador.getPath());
		Vector<TransferEntidad> listaE = daoEntidades.ListaDeEntidades();
		for (Iterator it = listaE.iterator(); it.hasNext(); ){
			TransferEntidad elem_te = (TransferEntidad)it.next();
			if (elem_te.getNombre().toLowerCase().equals(ta.getNombre().toLowerCase())){
				controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreDeYaExiste,ta);
				return;
			}
		}
		
		DAORelaciones daoRelaciones = new DAORelaciones(this.controlador.getPath());
		Vector<TransferRelacion> listaR = daoRelaciones.ListaDeRelaciones();
		for (Iterator it = listaR.iterator(); it.hasNext(); ){
			TransferRelacion elem_tr = (TransferRelacion)it.next();
			if (elem_tr.getNombre().toLowerCase().equals(ta.getNombre().toLowerCase())){
				controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreDeYaExiste,ta);
				return;
			}
		}
		
		int id = daoAgregaciones.anadirAgregacion(ta);
		if (id==-1)	controlador.mensajeDesde_SR(TC.SAG_InsertarAgregacion_ERROR_DAO,ta);
		else{
			ta.setIdAgregacion(id);
			controlador.mensajeDesde_AG(TC.SR_InsertarRelacion_HECHO, daoAgregaciones.consultarAgregacion(ta));
		}
	}
	//public boolean SePuedeAnadirAgregacion(TransferAgregacion te)???
	
	public void renombrarAgregacion(TransferAgregacion ta, String nuevoNombre){
		Vector<Object> v = new Vector<Object>();
		v.add(ta);
		v.add(nuevoNombre);
		v.add(ta.getNombre());
		
		// Si el nuevo nombre es vacio -> ERROR
		if (nuevoNombre.isEmpty()){
			controlador.mensajeDesde_AG(TC.SAG_RenombrarAgregacion_ERROR_NombreVacio, v);
			return;
		}
		// Si hay una relacion que ya tiene el "nuevoNombre" -> ERROR
		DAORelaciones dao = new DAORelaciones(this.controlador.getPath());
		Vector<TransferRelacion> listaRelaciones = dao.ListaDeRelaciones();
		int i = 0;
		TransferRelacion rel;
		while (i<listaRelaciones.size()){
			rel = listaRelaciones.get(i);
			if (rel.getNombre().toLowerCase().equals(nuevoNombre.toLowerCase())){
				controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreDeYaExiste,ta);
				return;
			}
			i++;
		}
		// Si hay una entidad que ya tiene el "nuevoNombre" -> ERROR
		DAOEntidades daoEntidades = new DAOEntidades(this.controlador.getPath());
		Vector<TransferEntidad> listaE = daoEntidades.ListaDeEntidades();
		/*if (listaE == null){
			controlador.mensajeDesde_SR(TC.SR_RenombrarRelacion_ERROR_DAOEntidades,v);
			return;
		}*/
		for (Iterator it = listaE.iterator(); it.hasNext(); ){
			TransferEntidad elem_te = (TransferEntidad)it.next();
			if (elem_te.getNombre().toLowerCase().equals(nuevoNombre.toLowerCase())){
				controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreDeYaExiste,ta);
				return;
			}
		}
		// Si hay una agregacion distinta que ya tiene el "nuevoNombre" -> ERROR
		DAOAgregaciones daoAgreg = new DAOAgregaciones(this.controlador.getPath());
		Vector<TransferAgregacion> listaAgregaciones = daoAgreg.ListaDeAgregaciones();
		int j = 0;
		TransferAgregacion agreg;
		while (j<listaAgregaciones.size()){
			agreg = listaAgregaciones.get(i);
			if (agreg.getNombre().toLowerCase().equals(nuevoNombre.toLowerCase())&& agreg.getIdAgregacion()!=ta.getIdAgregacion()){
				controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_NombreDeYaExiste,ta);
				return;
			}
			j++;
		}
		// Modificamos el nombre
		ta.setNombre(nuevoNombre);
		if (daoAgreg.modificarAgregacion(ta)==false){
			controlador.mensajeDesde_AG(TC.SAG_InsertarAgregacion_ERROR_DAO, v);	
		}
		else 
			controlador.mensajeDesde_AG(TC.SAG_RenombrarAgregacion_HECHO, v);
		return;
	}
	
	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
