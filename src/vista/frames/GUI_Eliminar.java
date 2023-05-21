package vista.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import controlador.Controlador;
import controlador.TC;
import modelo.transfers.Transfer;
import modelo.transfers.TransferAgregacion;
import modelo.transfers.TransferAtributo;
import modelo.transfers.TransferEntidad;
import modelo.transfers.TransferRelacion;
import vista.componentes.MyComboBoxRenderer;
import vista.imagenes.ImagePath;
import vista.lenguaje.Lenguaje;

@SuppressWarnings("serial")
public class GUI_Eliminar extends Parent_GUI{
	private Controlador controlador;
	@SuppressWarnings("rawtypes")
	private JComboBox comboTransfers;
	@SuppressWarnings("unused")
	private JButton botonConfirmar;
	private Vector<Transfer> listaTransfers;
	
	public GUI_Eliminar() {
		this.initComponents();
	}
	
	private void initComponents() {
		setTitle(Lenguaje.text(Lenguaje.DELETE));
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ImagePath.LOGODBDT)).getImage());
		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		//getContentPane().add(getEligeTransfer());
		getContentPane().add(getComboTransfers());
		getContentPane().add(getBotonConfirmar());
		this.setSize(300, 420);
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	

	private JButton getBotonConfirmar() {
		if(botonConfirmar == null) {
			botonConfirmar = this.botonConfirmar(160, 330);
			botonConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					botonEliminarActionPerformed(evt);
				}
			});
			botonConfirmar.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==10) botonEliminarActionPerformed(null);
				}
				public void keyReleased(KeyEvent e) {}
				public void keyTyped(KeyEvent e) {}
			});
		}
		return botonConfirmar;
	}
	
	@SuppressWarnings("null")
	private void botonEliminarActionPerformed(ActionEvent evt) {
		Vector<Object> v = new Vector<Object>();
		v.add(comboTransfers.getSelectedItem());
		v.add(true);
		v.add(0);
		if(comboTransfers.getSelectedItem() instanceof TransferRelacion) 
			controlador.mensajeDesde_PanelDiseno(TC.PanelDiseno_Click_EliminarRelacionNormal, v);
		
		else if (comboTransfers.getSelectedItem() instanceof TransferEntidad) 
			controlador.mensajeDesde_PanelDiseno(TC.PanelDiseno_Click_EliminarEntidad,v);
		
			
		else if(comboTransfers.getSelectedItem() instanceof TransferAgregacion) 
			controlador.mensajeDesde_PanelDiseno(TC.PanelDiseno_Click_EliminarAgregacion,comboTransfers.getSelectedItem());
		
		else if(comboTransfers.getSelectedItem() instanceof TransferAtributo)
			controlador.mensajeDesde_PanelDiseno(TC.PanelDiseno_Click_EliminarAtributo,v);
		
		this.setInactiva();
	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private JComboBox getComboTransfers() {
		if(comboTransfers == null) {
			comboTransfers = new JComboBox();
			comboTransfers.setRenderer(new MyComboBoxRenderer());
			comboTransfers.setFont(theme.font());
			comboTransfers.setBackground(theme.background());
			comboTransfers.setBounds(25, 40, 231, 21);
		}
		return comboTransfers;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setActiva() {
		this.centraEnPantalla();
		this.comboTransfers.setEnabled(true);
		
		//Genera Transfers
		this.comboTransfers.setModel(new javax.swing.DefaultComboBoxModel(listaTransfers));
		
		this.setVisible(true);
	}
	
	public void setInactiva() {
		this.setVisible(false);
	}
	
	public void setListaTransfers(Vector<Transfer> lista) {
		for(int i = 0; i < lista.size(); ++i) { //no eliminaremos con esta GUI atributos para no sobrecargar el combo
			if(lista.get(i) instanceof TransferAtributo) {
				lista.remove(i);
				--i;
			}
		}
		this.listaTransfers=lista;
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}
	
}
