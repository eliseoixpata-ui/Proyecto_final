package vista;

import Controlador.UsuarioController;
import modelo.usuarios;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Pattern;

public class jpUsuario extends javax.swing.JPanel {

    private UsuarioController usuarioController;
    private TableRowSorter<DefaultTableModel> sorter;

    public jpUsuario() {

        initComponents();

        usuarioController = new UsuarioController();

        txtID.setEditable(false);
        txtID.setText("");

        cargarTabla();
        configurarBusqueda();

        btnNuevo.addActionListener(e -> nuevoUsuario());
        btnGuardar.addActionListener(e -> guardarUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());

        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarUsuario();
            }
        });
    }

    private void limpiarCampos() {

        txtID.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtNombreCompleto.setText("");

        if (cmbxRol.getItemCount() > 0) {
            cmbxRol.setSelectedIndex(0);
        }
    }

    private void cargarTabla() {

        DefaultTableModel modelo =
                (DefaultTableModel) tblUsuarios.getModel();

        modelo.setRowCount(0);

        List<usuarios> lista =
                usuarioController.listar();

        for (usuarios usuario : lista) {

            Object[] fila = {
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getNombreCompleto(),
                usuario.getRol()
            };

            modelo.addRow(fila);
        }
    }

    private void configurarBusqueda() {

        DefaultTableModel modelo =
                (DefaultTableModel) tblUsuarios.getModel();

        sorter = new TableRowSorter<>(modelo);

        tblUsuarios.setRowSorter(sorter);

        txtBuscar.getDocument().addDocumentListener(
                new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }
        });
    }

    private void filtrar() {

        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()
                || texto.equalsIgnoreCase("Buscar usuario...")) {

            sorter.setRowFilter(null);

        } else {

            sorter.setRowFilter(
                    RowFilter.regexFilter(
                            "(?i)" + Pattern.quote(texto)
                    )
            );
        }
    }

    private void nuevoUsuario() {

        limpiarCampos();
        txtUsuario.requestFocus();
    }

    private void guardarUsuario() {

        if (txtUsuario.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el usuario"
            );

            return;
        }

        if (txtContraseña.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese la contraseña"
            );

            return;
        }

        if (txtNombreCompleto.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el nombre completo"
            );

            return;
        }

        usuarios usuario = new usuarios();

        usuario.setNombreUsuario(
                txtUsuario.getText().trim()
        );

        usuario.setContrasenaHash(
                txtContraseña.getText().trim()
        );

        usuario.setNombreCompleto(
                txtNombreCompleto.getText().trim()
        );

        usuario.setRol(
                cmbxRol.getSelectedItem().toString().toLowerCase()
        );

        boolean resultado =
                usuarioController.guardar(usuario);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario guardado correctamente"
            );

            limpiarCampos();
            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar el usuario"
            );
        }
    }

    private void actualizarUsuario() {

        if (txtID.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un usuario"
            );

            return;
        }

        usuarios usuario = new usuarios();

        usuario.setIdUsuario(
                Integer.parseInt(txtID.getText())
        );

        usuario.setNombreUsuario(
                txtUsuario.getText().trim()
        );

        usuario.setContrasenaHash(
                txtContraseña.getText().trim()
        );

        usuario.setNombreCompleto(
                txtNombreCompleto.getText().trim()
        );

        usuario.setRol(
                cmbxRol.getSelectedItem().toString().toLowerCase()
        );

        boolean resultado =
                usuarioController.actualizar(usuario);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario actualizado correctamente"
            );

            limpiarCampos();
            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar el usuario"
            );
        }
    }

    private void eliminarUsuario() {

        if (txtID.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un usuario"
            );

            return;
        }

        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar este usuario?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if (respuesta == JOptionPane.YES_OPTION) {

            int id =
                    Integer.parseInt(txtID.getText());

            boolean resultado =
                    usuarioController.eliminar(id);

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario eliminado correctamente"
                );

                limpiarCampos();
                cargarTabla();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar el usuario"
                );
            }
        }
    }

    private void seleccionarUsuario() {

        int filaVista =
                tblUsuarios.getSelectedRow();

        if (filaVista == -1) {
            return;
        }

        int fila =
                tblUsuarios.convertRowIndexToModel(filaVista);

        DefaultTableModel modelo =
                (DefaultTableModel) tblUsuarios.getModel();

        int id =
                Integer.parseInt(
                        modelo.getValueAt(fila, 0).toString()
                );

        usuarios usuario =
                usuarioController.consultar(id);

        if (usuario != null) {

            txtID.setText(
                    String.valueOf(usuario.getIdUsuario())
            );

            txtUsuario.setText(
                    usuario.getNombreUsuario()
            );

            txtContraseña.setText(
                    usuario.getContrasenaHash()
            );

            txtNombreCompleto.setText(
                    usuario.getNombreCompleto()
            );

            if (usuario.getRol() != null) {

                if (usuario.getRol().equalsIgnoreCase("administrador")) {
                    cmbxRol.setSelectedItem("Administrador");
                } else {
                    cmbxRol.setSelectedItem("Vendedor");
                }
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        txtNombreCompleto = new javax.swing.JTextField();
        cmbxRol = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();

        jLabel1.setText("ID:");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jLabel4.setText("Nombre Completo:");

        jLabel5.setText("Rol:");

        cmbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor" }));

        btnNuevo.setText("Nuevo");

        btnGuardar.setText("Guardar");

        btnActualizar.setText("Actualizar");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtBuscar.setText("Buscar usuario...");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Nombre Completo", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblUsuarios);
        tblUsuarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2)))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID)
                            .addComponent(txtUsuario)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreCompleto)
                            .addComponent(cmbxRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnGuardar)
                            .addComponent(btnActualizar)
                            .addComponent(btnEliminar))
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
