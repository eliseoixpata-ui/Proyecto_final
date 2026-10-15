/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import Controlador.ArtistaController;
import modelo.Artistas;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Pattern;

/**
 *
 * @author josue
 */
public class jpArtista extends javax.swing.JPanel {
    
    private ArtistaController artistaController;
private TableRowSorter<DefaultTableModel> sorter;

public jpArtista() {

    initComponents();

    artistaController = new ArtistaController();

    txtID.setEditable(false);
    txtID.setText("");

    cargarTabla();

    configurarBusqueda();

    btnNuevo.addActionListener(e -> nuevoArtista());
    btnGuardar.addActionListener(e -> guardarArtista());
    btnActualizar.addActionListener(e -> actualizarArtista());
    btnEliminar.addActionListener(e -> eliminarArtista());

    tblArtistas.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            seleccionarArtista();
        }
    });
}


private void limpiarCampos() {

    jLabel2.setText("");
    lblNombreArtistico.setText("");
    lblGeneroMusical.setText("");
    lblPaisOrigen.setText("");
}


private void cargarTabla() {

    DefaultTableModel modelo =
            (DefaultTableModel) tblArtistas.getModel();

    modelo.setRowCount(0);

    List<Artistas> lista =
            artistaController.listar();

    for (Artistas artista : lista) {

        Object[] fila = {
            artista.getIdArtista(),
            artista.getNombreArtistico(),
            artista.getGeneroMusical(),
            artista.getPaisOrigen()
        };

        modelo.addRow(fila);
    }
}


private void configurarBusqueda() {

    DefaultTableModel modelo =
            (DefaultTableModel) tblArtistas.getModel();

    sorter = new TableRowSorter<>(modelo);

    tblArtistas.setRowSorter(sorter);

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
            || texto.equalsIgnoreCase("Buscar Artista...")) {

        sorter.setRowFilter(null);

    } else {

        sorter.setRowFilter(
                RowFilter.regexFilter(
                        "(?i)" + Pattern.quote(texto)
                )
        );
    }
}


private void nuevoArtista() {

    limpiarCampos();

    lblNombreArtistico.requestFocus();
}


private void guardarArtista() {

    if (lblNombreArtistico.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Ingrese el nombre artístico"
        );

        return;
    }

    if (lblGeneroMusical.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Ingrese el género musical"
        );

        return;
    }

    Artistas artista = new Artistas();

    artista.setNombreArtistico(
            lblNombreArtistico.getText().trim()
    );

    artista.setGeneroMusical(
            lblGeneroMusical.getText().trim()
    );

    artista.setPaisOrigen(
            lblPaisOrigen.getText().trim()
    );

    boolean resultado =
            artistaController.guardar(artista);

    if (resultado) {

        JOptionPane.showMessageDialog(
                this,
                "Artista guardado correctamente"
        );

        limpiarCampos();
        cargarTabla();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Error al guardar el artista"
        );
    }
}


private void actualizarArtista() {

    if (jLabel2.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Seleccione un artista"
        );

        return;
    }

    if (lblNombreArtistico.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Ingrese el nombre artístico"
        );

        return;
    }

    Artistas artista = new Artistas();

    artista.setIdArtista(
            Integer.parseInt(jLabel2.getText())
    );

    artista.setNombreArtistico(
            lblNombreArtistico.getText().trim()
    );

    artista.setGeneroMusical(
            lblGeneroMusical.getText().trim()
    );

    artista.setPaisOrigen(
            lblPaisOrigen.getText().trim()
    );

    boolean resultado =
            artistaController.actualizar(artista);

    if (resultado) {

        JOptionPane.showMessageDialog(
                this,
                "Artista actualizado correctamente"
        );

        limpiarCampos();
        cargarTabla();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Error al actualizar el artista"
        );
    }
}


private void eliminarArtista() {

    if (jLabel2.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Seleccione un artista"
        );

        return;
    }

    int respuesta =
            JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar este artista?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

    if (respuesta == JOptionPane.YES_OPTION) {

        int id =
                Integer.parseInt(jLabel2.getText());

        boolean resultado =
                artistaController.eliminar(id);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Artista eliminado correctamente"
            );

            limpiarCampos();
            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el artista"
            );
        }
    }
}


private void seleccionarArtista() {

    int filaVista =
            tblArtistas.getSelectedRow();

    if (filaVista == -1) {
        return;
    }

    int fila =
            tblArtistas.convertRowIndexToModel(filaVista);

    DefaultTableModel modelo =
            (DefaultTableModel) tblArtistas.getModel();

    jLabel2.setText(
            modelo.getValueAt(fila, 0).toString()
    );

    lblNombreArtistico.setText(
            modelo.getValueAt(fila, 1).toString()
    );

    lblGeneroMusical.setText(
            modelo.getValueAt(fila, 2).toString()
    );

    Object pais =
            modelo.getValueAt(fila, 3);

    lblPaisOrigen.setText(
            pais == null ? "" : pais.toString()
    );
}

    /**
     * Creates new form jpArtista
     */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblNombreArtistico = new javax.swing.JLabel();
        lblGeneroMusical = new javax.swing.JLabel();
        lblPaisOrigen = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtNombreArtistico = new javax.swing.JTextField();
        txtGeneroMusical = new javax.swing.JTextField();
        txtPaisOrigen = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblArtistas = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("ID:");

        lblNombreArtistico.setText("Nombre Artistico:");

        lblGeneroMusical.setText("Genero Musical:");

        lblPaisOrigen.setText("Pais de Origen:");

        btnNuevo.setText("Nuevo");

        btnGuardar.setText("Guardar");

        btnActualizar.setText("Actualizar");

        btnEliminar.setText("Eliminar");

        tblArtistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Artistico", "Genero", "Pais"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArtistas.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(tblArtistas);
        tblArtistas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        txtBuscar.setText("Buscar Artista...");

        jLabel1.setText("ARTISTAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombreArtistico)
                                    .addComponent(lblGeneroMusical)
                                    .addComponent(lblPaisOrigen)
                                    .addComponent(jLabel1))
                                .addGap(96, 96, 96)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                                    .addComponent(txtNombreArtistico)
                                    .addComponent(txtGeneroMusical)
                                    .addComponent(txtPaisOrigen)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar)
                                .addGap(106, 106, 106)
                                .addComponent(btnActualizar)
                                .addGap(116, 116, 116)
                                .addComponent(btnEliminar)
                                .addGap(8, 8, 8)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPaisOrigen)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreArtistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreArtistico))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGeneroMusical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGeneroMusical))
                        .addGap(18, 18, 18)
                        .addComponent(txtPaisOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblGeneroMusical;
    private javax.swing.JLabel lblNombreArtistico;
    private javax.swing.JLabel lblPaisOrigen;
    private javax.swing.JTable tblArtistas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtGeneroMusical;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombreArtistico;
    private javax.swing.JTextField txtPaisOrigen;
    // End of variables declaration//GEN-END:variables
}
