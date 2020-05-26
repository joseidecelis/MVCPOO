package Controlador;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlProducto implements ActionListener {

    private Producto mod;
    private ConsultasProducto modC;
    private frmProducto frm;

    public CtrlProducto(Producto mod, ConsultasProducto modC, frmProducto frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            mod.setCodigo(frm.txtCodigo.getText());
             mod.setNombre(frm.txtNombre.getText());
              mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
               mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));

            if (modC.registrar(mod)) {
                JOptionPane.showConfirmDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showConfirmDialog(null, "Error al Guardar");
            }
        }

        if (e.getSource() == frm.btnEliminar) {
            mod.setId(Integer.parseInt(frm.txtId.getText()));

            if (modC.eliminar(mod)) {
                JOptionPane.showConfirmDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showConfirmDialog(null, "Error al Eliminar");
            }
        }

        if (e.getSource() == frm.btnModificar) {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));

            if (modC.modificar(mod)) {
                JOptionPane.showConfirmDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showConfirmDialog(null, "Error al Modificar");
            }
        }
        if (e.getSource() == frm.btnBuscar) {
            mod.setCodigo(frm.txtCodigo.getText());

            if (modC.buscar(mod)) {
                
               frm.txtId.setText(String.valueOf(mod.getId()));
               frm.txtCodigo.setText(mod.getCodigo());
               frm.txtNombre.setText(mod.getNombre());
               frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
               frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                

            } else {
                JOptionPane.showConfirmDialog(null, "No se encontró el registro");
            limpiar();
            }
        }
        if (e.getSource() == frm.btnLimpiar){
            limpiar();
            
        }
    }

    public void limpiar() {
        frm.txtId.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtCantidad.setText(null);
    }
}
