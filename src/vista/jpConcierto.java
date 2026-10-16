/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;
import dao.ArtistaDao;
import Controlador.conciertoController;
import modelo.Artistas;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Concierto;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Pattern;
/**
 *
 * @author ixpat
 */
public class jpConcierto extends javax.swing.JPanel {
    
    private conciertoController conciertoController;
    private ArtistaDao artistaDao;
    private List<Artistas> listaArtistas;
    private TableRowSorter<DefaultTableModel> sorter;
   
    public jpConcierto() {
        initComponents();
        configurarHora();
        

            txtID.setEditable(false);
            txtID.setText("Automático");

            cmbxEstado.removeAllItems();
            cmbxEstado.addItem("programado");
            cmbxEstado.addItem("activo");
            cmbxEstado.addItem("finalizado");
            cmbxEstado.addItem("cancelado");

            conciertoController = new conciertoController();
            artistaDao = new ArtistaDao();

            cargarArtistas();
            cargarTabla();
            configurarBusqueda();
    }
    
    private void cargarArtistas() {

         listaArtistas = artistaDao.listar();

         cmbxArtista.removeAllItems();

        for (Artistas artista : listaArtistas) {
            cmbxArtista.addItem(artista.getNombreArtistico());
        }
    }


    private void limpiarCampos() {

        txtID.setText("Automático");
        txtTituloEvento.setText("");
        txtRecinto.setText("");

        jDateChooser2.setDate(null);

        cbxHora.setSelectedItem("18");
        cmbxMinutos.setSelectedItem("00");

        if (cmbxArtista.getItemCount() > 0) {
            cmbxArtista.setSelectedIndex(0);
        }

        cmbxEstado.setSelectedItem("programado");
    }
    private void cargarTabla() {

        DefaultTableModel modelo =
            (DefaultTableModel) tblConciertos.getModel();

        modelo.setColumnIdentifiers(new Object[]{
            "ID", "Artista", "Título", "Fecha", "Recinto", "Estado"
        });

        modelo.setRowCount(0);

        List<Concierto> lista = conciertoController.listar();

        for (Concierto concierto : lista) {

            String nombreArtista = "";

            for (Artistas artista : listaArtistas) {

                if (artista.getIdArtista() == concierto.getIdArtista()) {
                    nombreArtista = artista.getNombreArtistico();
                    break;
                }
            }

            modelo.addRow(new Object[]{
                concierto.getIdConcierto(),
                nombreArtista,
                concierto.getTituloEvento(),
                concierto.getFechaConcierto(),
                concierto.getRecinto(),
                concierto.getEstado()
            });
            tblConciertos.setModel(modelo);
            configurarBusqueda();
        }
    }
    private void configurarBusqueda() {

    DefaultTableModel modelo =
            (DefaultTableModel) tblConciertos.getModel();

        sorter = new TableRowSorter<>(modelo);

        tblConciertos.setRowSorter(sorter);

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

        if (texto.isEmpty()) {

            sorter.setRowFilter(null);

        } else {

            sorter.setRowFilter(
                RowFilter.regexFilter(
                    "(?i)" + Pattern.quote(texto)
                )
            );
        }
    }
    private void configurarHora() {

        cbxHora.removeAllItems();
        cmbxMinutos.removeAllItems();

        for (int i = 0; i < 24; i++) {
            cbxHora.addItem(String.format("%02d", i));
        }

        for (int i = 0; i < 60; i++) {
            cmbxMinutos.addItem(String.format("%02d", i));
        }

        cbxHora.setSelectedItem("18 H");
        cmbxMinutos.setSelectedItem("00 Minuts");
    }
    
    private String obtenerFechaConcierto ( ){
        java.util.Date fecha = jDateChooser2.getDate(); 
        
        if (fecha == null ){
            JOptionPane.showMessageDialog(
            this, "seleccione una fecha para el concierto"
            );
            return null;
        }
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        String fechaTexto  = formato.format(fecha);
        
        String hora= cbxHora.getSelectedItem ().toString();
        String minutos = cmbxMinutos.getSelectedItem().toString();
        
        return fechaTexto + " "+ hora + ":" + minutos + ":00";
    }

    private void mostrarFechaConcierto(String fechaTexto) {

        try {
            java.text.SimpleDateFormat formato =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            java.util.Date fecha = formato.parse(fechaTexto);

            jDateChooser2.setDate(fecha);

            cbxHora.setSelectedItem(
                new java.text.SimpleDateFormat("HH").format(fecha)
            );

            cmbxMinutos.setSelectedItem(
                new java.text.SimpleDateFormat("mm").format(fecha)
            );

        } catch (java.text.ParseException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Error al cargar la fecha: " + e.getMessage()
            );
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

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtTituloEvento = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmbxMinutos = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtRecinto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cmbxArtista = new javax.swing.JComboBox<>();
        cmbxEstado = new javax.swing.JComboBox<>();
        cbxHora = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblConciertos = new javax.swing.JTable();
        txtID = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setBackground(new java.awt.Color(102, 255, 255));

        jLabel7.setText("Estado");

        jLabel8.setText("Artista:");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        jLabel9.setText("ID:");

        cmbxMinutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        jLabel10.setText("Recinto");

        txtBuscar.setText("Bucar concierto...                                                   ");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        jLabel11.setText("Fecha de concierto");

        cmbxArtista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblConciertos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblConciertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConciertosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblConciertos);

        txtID.setText("Automatico");
        txtID.addActionListener(this::txtIDActionPerformed);

        jLabel12.setText("Titulo del Evento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRecinto, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                .addComponent(btnGuardar)
                                .addGap(104, 104, 104)
                                .addComponent(btnActualizar)
                                .addGap(85, 85, 85)
                                .addComponent(btnEliminar))
                            .addComponent(jScrollPane2)
                            .addComponent(txtBuscar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(163, 163, 163)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbxArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTituloEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbxMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxHora, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel12)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbxArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTituloEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cmbxMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtRecinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addComponent(btnNuevo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizar)
                            .addComponent(btnGuardar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {

            if (cmbxArtista.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un artista");
                return;
            }

            if (txtTituloEvento.getText().trim().isEmpty()
                    || txtRecinto.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Complete todos los campos");
                return;
            }

            String fecha = obtenerFechaConcierto();

            if (fecha == null) {
                return;
            }

            int indice = cmbxArtista.getSelectedIndex();
            int idArtista = listaArtistas.get(indice).getIdArtista();

            Concierto concierto = new Concierto();

            concierto.setIdArtista(idArtista);
            concierto.setTituloEvento(txtTituloEvento.getText().trim());
            concierto.setFechaConcierto(fecha);
            concierto.setRecinto(txtRecinto.getText().trim());
            concierto.setEstado(cmbxEstado.getSelectedItem().toString());

            if (conciertoController.guardar(concierto)) {

                JOptionPane.showMessageDialog(this, "Concierto guardado correctamente");

                cargarTabla();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el concierto");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {

            if (txtID.getText().equals("Automático")
                    || txtID.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Seleccione un concierto");
                return;
            }

            if (cmbxArtista.getSelectedIndex() == -1
                    || txtTituloEvento.getText().trim().isEmpty()
                    || txtRecinto.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Complete todos los campos");
                return;
            }

            String fecha = obtenerFechaConcierto();

            if (fecha == null) {
                return;
            }

            int id = Integer.parseInt(txtID.getText());

            int indice = cmbxArtista.getSelectedIndex();
            int idArtista = listaArtistas.get(indice).getIdArtista();

            Concierto concierto = new Concierto();

            concierto.setIdConcierto(id);
            concierto.setIdArtista(idArtista);
            concierto.setTituloEvento(txtTituloEvento.getText().trim());
            concierto.setFechaConcierto(fecha);
            concierto.setRecinto(txtRecinto.getText().trim());
            concierto.setEstado(cmbxEstado.getSelectedItem().toString());

            if (conciertoController.actualizar(concierto)) {

                JOptionPane.showMessageDialog(this, "Concierto actualizado correctamente");

                cargarTabla();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         try {

            if (txtID.getText().equals("Automático")
                    || txtID.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Seleccione un concierto");
                return;
            }

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este concierto?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta != JOptionPane.YES_OPTION) {
                return;
            }

            int id = Integer.parseInt(txtID.getText());

            if (conciertoController.eliminar(id)) {

                JOptionPane.showMessageDialog(this, "Concierto eliminado correctamente");

                cargarTabla();
                limpiarCampos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar el concierto. Verifique si tiene registros relacionados."
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblConciertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConciertosMouseClicked
        
        int fila = tblConciertos.getSelectedRow();

        if (fila == -1) {
            return;
        }

        int filaModelo = tblConciertos.convertRowIndexToModel(fila);

        javax.swing.table.TableModel modelo = tblConciertos.getModel();

        txtID.setText(modelo.getValueAt(filaModelo, 0).toString());
        txtTituloEvento.setText(modelo.getValueAt(filaModelo, 2).toString());
        txtRecinto.setText(modelo.getValueAt(filaModelo, 4).toString());

        cmbxEstado.setSelectedItem(
                modelo.getValueAt(filaModelo, 5).toString()
        );

        String nombreArtista = modelo.getValueAt(filaModelo, 1).toString();
        cmbxArtista.setSelectedItem(nombreArtista);

        mostrarFechaConcierto(
                modelo.getValueAt(filaModelo, 3).toString()
        );
    }//GEN-LAST:event_tblConciertosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxHora;
    private javax.swing.JComboBox<String> cmbxArtista;
    private javax.swing.JComboBox<String> cmbxEstado;
    private javax.swing.JComboBox<String> cmbxMinutos;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblConciertos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtRecinto;
    private javax.swing.JTextField txtTituloEvento;
    // End of variables declaration//GEN-END:variables
}
