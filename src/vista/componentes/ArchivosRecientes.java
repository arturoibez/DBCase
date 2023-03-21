package vista.componentes;

import java.io.File;
import java.util.ArrayList;

public class ArchivosRecientes {

	private ArrayList<File> recientes = new ArrayList<File>();
	//Establecemos un maximo de archivos mostrados en recientes para que sean realmente los recientes
	private static final int  max_size = 10;  
	
	public void add(File f) {
		if(recientes.contains(f)) {
			recientes.remove(f);
		}
<<<<<<< HEAD
		recientes.add(0,f); //lo aÃ±ade al principio
		if(recientes.size() > max_size) {
			recientes.remove(recientes.size()-1);
=======
		recientes.add(0,f); //lo añade al principio
		if(recientes.size() > max_size) {
			recientes.remove(recientes.size()-1);//elimina el que hace mas tiempo que no se abre
>>>>>>> fa865d7a4a8e703c24dd1a84992d1136cfa20810
		}
	}
	
	public  ArrayList<File> darRecientes(){
		return recientes;
	}
<<<<<<< HEAD
	
	public void recibeRecientes( ArrayList<File> v) {
=======
	public void recibeRecientes( ArrayList<File>v) {
>>>>>>> fa865d7a4a8e703c24dd1a84992d1136cfa20810
		recientes = v;
	}
	
}
