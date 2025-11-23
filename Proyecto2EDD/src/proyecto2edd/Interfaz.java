/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto2edd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
/**
 * Interfaz del programa. 
 * @author 
 */
public class Interfaz extends javax.swing.JFrame {

    HashTable info = new HashTable(301);
    HashAlt pClave = new HashAlt(301);
    HashAlt auth = new HashAlt(301);
    Arbol arbolPalabras = new Arbol();
    Arbol arbolAutores= new Arbol();
    Arbol arbolTitulo= new Arbol();
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        cargarGuardado(info, auth, pClave, arbolPalabras, arbolAutores, arbolTitulo);
        String salida = "";
        String[] x = arbolTitulo.inorden(arbolTitulo.getroot(), salida).split(", ");
        String[] y = arbolAutores.inorden(arbolAutores.getroot(), salida).split(", ");
        String[] z = arbolPalabras.inorden(arbolPalabras.getroot(), salida).split(", ");
        jComboBox1.setModel(new DefaultComboBoxModel(x));
        jComboBox2.setModel(new DefaultComboBoxModel(y));
        jComboBox3.setModel(new DefaultComboBoxModel(z));
        jComboBox4.setModel(new DefaultComboBoxModel(z));
    }
    
    /**
     * Deja al usuario elegir un archivo, a partir del cual se extraerá la información.
     * @param entrada
     * @param entradaAut
     * @param entradaClav
     * @return 
     */
    public String agregar_Resumen(HashTable entrada, HashAlt entradaAut, HashAlt entradaClave, Arbol wordTree, Arbol authTree, Arbol titleTree){
            String txt;
            String auxTitulo = "";    
            String auxAutor = "";
            String auxResumen = "";
            String auxPClave = "";
            try{
                JFileChooser elegir_archivo = new JFileChooser();
                elegir_archivo.showOpenDialog(this);
                File archivo_elegido = elegir_archivo.getSelectedFile();
                if (archivo_elegido==null){
                    return "Ningún archivo fue elegido";
                }
                else{
                    FileReader archivo=new FileReader(archivo_elegido);
                    BufferedReader leer=new BufferedReader(archivo);
                    while(!(txt=leer.readLine()).equals("Autores")){
                        auxTitulo += txt + " ";
                    }                   
                    while(!(txt=leer.readLine()).equals("Resumen")){
                        auxAutor += txt + ", ";
                    }
                    while(!(txt=leer.readLine()).equals("")){
                        auxResumen += txt + " ";
                    }
                    while((txt=leer.readLine())!=null){
                        auxPClave += txt + ", ";
                    }
                    String[] x = auxAutor.split(", ");
                    String[] y = auxPClave.split(", ");
                    y[0] = y[0].replace("Palabras Claves: ", "").replace("Palabras claves: ", "");
                    y[y.length-1] = y[y.length-1].replace(".", "");

                    if(entrada.buscar(auxTitulo)==null){
                    Elementos_Hash elem = new Elementos_Hash(auxTitulo, x, auxResumen, y);
                  
                    for(int i= 0; i<x.length; i++){
                        ElementosHalt autores = new ElementosHalt(x[i], auxTitulo);
                            entradaAut.insertar(autores);
                            authTree.insertarnodo(authTree.getroot(), x[i]);
                    }
                    for(int i= 0; i<y.length; i++){
                        ElementosHalt palabras_clave = new ElementosHalt(y[i], auxTitulo);
                            entradaClave.insertar(palabras_clave);
                            wordTree.insertarnodo(wordTree.getroot(), y[i]);
                    }
                    
                    entrada.insertar(elem);
                    
                    actualizarfrecuencia(elem,entradaClave);
                    
                    titleTree.insertarnodo(titleTree.getroot(), auxTitulo);
                    
                    }
                    else{
                        return "El resumen ya existe, por favor seleccione otro resumen para añadir";
                    }
                }     
        }catch(Exception e){
        return "Ha ocurrido un error";
        }
        return "Se ha leído el archivo";
    }
    

    public String simplificar (String string){
        String resultado = string.replace("\r", " ").replace("\n", " ");
        resultado = resultado.replaceAll("[^\\p{L}\\p{Nd}\\s]+", " ");
        resultado = resultado.toLowerCase().replaceAll("\\s+", " ").trim();
        return resultado;
    }
    
    public int contar(String entradaResumen, String Clave){
        int index=0;
        String analizar = simplificar(entradaResumen);
        String simplificarClave = simplificar(Clave);
        int desde = 0;
        int contador = 0;
        while((index = analizar.indexOf(simplificarClave, desde))!=-1){
            contador ++;
            desde = index + simplificarClave.length();
        }
        return contador;
    }
    
    public void actualizarfrecuencia(Elementos_Hash entrada,HashAlt entradaClave){
        String resumen = entrada.getResumen();
        String [] PClave = entrada.getP_clave();
        for (int i = 0; i < PClave.length; i++) {
            String clave = PClave[i];
            int cuenta = contar(resumen,clave);
            if (cuenta>0){
                ElementosHalt nodo = entradaClave.buscar(clave);
                ElementosHalt aux = nodo;
                String clavesimple = simplificar(clave);
                //revisar si hace falta simplificar la clave que entra
                while(aux!=null){
                    if(simplificar(aux.getKey()).equals(clavesimple) && aux.getTitulo().equals(entrada.getTitulo())){
                        aux.setFrecuencia(aux.getFrecuencia() + cuenta);
                        break;
                    }
                    aux =aux.getNext();
                }
            }
        }
    }
    
    /**
     * Método que permite al programa guardar la información de todos los títulos en un archivo con un formato específico.
     * Permite guardar cambios entre sesiones.
     */
    public void guardarYsalir(){
        Elementos_Hash elem = new Elementos_Hash(null, null, null, null);
        String salida = "";
        String rutaArchivo = "sav.txt";
        String[] title = arbolTitulo.inorden(arbolTitulo.getroot(), salida).split(", ");
        try{
            FileWriter writer = new FileWriter(rutaArchivo);
            for(int i=0; i<title.length; i++){
                elem= info.buscar(title[i]);
                writer.write(elem.getTitulo()+"\n"+arrayAString(elem.getAutores())+"\n"+elem.getResumen()+"\n"+arrayAString(elem.getP_clave())+"\n\n");
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "No se ha podido guardar el archivo");
        }
    }
    
    public void cargarGuardado(HashTable entrada, HashAlt entradaAut, HashAlt entradaClav, Arbol wordTree, Arbol authTree, Arbol titleTree){
        try{
            String titulo = "";
            String[] autores;
            String resumen = "";
            String[] palabrasClaves;
    
            File save = new File("sav.txt");
            FileReader archivo=new FileReader(save);
            BufferedReader leer=new BufferedReader(archivo);
            while((titulo=leer.readLine())!=null){
                autores = leer.readLine().split(", ");
                autores[autores.length-1] = autores[autores.length-1].replace(".", "");
                resumen = leer.readLine();
                palabrasClaves = leer.readLine().split(", ");
                palabrasClaves[palabrasClaves.length-1] = palabrasClaves[palabrasClaves.length-1].replace(".", "");
                leer.readLine();
                
                Elementos_Hash elemHash = new Elementos_Hash(titulo, autores, resumen, autores);
                entrada.insertar(elemHash);
                titleTree.insertarnodo(titleTree.getroot(), titulo);
                
                for(int i=0; i<autores.length; i++){
                    ElementosHalt auxAut = new ElementosHalt(autores[i], titulo);
                    entradaAut.insertar(auxAut);
                    authTree.insertarnodo(authTree.getroot(), autores[i]);
                }
                for(int i= 0; i<palabrasClaves.length; i++){
                    ElementosHalt palabras_clave = new ElementosHalt(palabrasClaves[i], titulo);
                    entradaClav.insertar(palabras_clave);
                    wordTree.insertarnodo(wordTree.getroot(), palabrasClaves[i]);
                }
            }
            JOptionPane.showMessageDialog(rootPane, "Se han cargado los resumenes correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "No se ha podido precargar un resumen");
        }
    }
    
    /**
     * Función por conveniencia.
     * Convierte un array de Strings en un string separado por comas
     * @param array - El array que se quiere convertir
     * @return - El string resultado de convertir el array
     */
    public String arrayAString(String[] array){
        String x = "";
        for (int i = 0; i < array.length-1; i++) {
               x += array[i]+", ";
           }
           x+= array[array.length-1]+".";
           return x;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Agregar Resumen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 170, 40));

        jButton2.setText("Analizar Resumen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 170, 50));

        jScrollPane1.setFocusable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 770, 150));

        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 390, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Resumenes cargados");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 170, -1));

        jPanel2.setBackground(new java.awt.Color(255, 140, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Buscar por palabra clave");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 254, 240, 80));

        jButton4.setText("Buscar por autor");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 76, 240, 81));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Búsqueda de resumenes");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 42, 240, -1));

        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 169, 240, -1));

        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 346, 240, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 420, 410));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Resumenes relacionados:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 262, 170, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, -1, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 53, -1, 160));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, 20, 440));

        jButton5.setBackground(new java.awt.Color(185, 0, 0));
        jButton5.setForeground(new java.awt.Color(255, 204, 120));
        jButton5.setText("Guardar y salir del sistema");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 610, 320, 40));

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 340, 105));

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 441, 340, 229));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Información del resumen");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 413, 148, -1));

        jButton6.setText("Mostrar detalles");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 260, 120, 60));

        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, 220, -1));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 340, 350, 220));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 840, 15));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 580, 390, 15));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, 10, 350));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, 390, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, agregar_Resumen(info, auth, pClave, arbolPalabras, arbolAutores, arbolTitulo));
        String salida = "";
        String[] x = arbolTitulo.inorden(arbolTitulo.getroot(), salida).split(", ");
        String[] y = arbolAutores.inorden(arbolAutores.getroot(), salida).split(", ");
        String[] z = arbolPalabras.inorden(arbolPalabras.getroot(), salida).split(", ");
        jComboBox1.setModel(new DefaultComboBoxModel(x));
        jComboBox2.setModel(new DefaultComboBoxModel(y));
        jComboBox3.setModel(new DefaultComboBoxModel(z));
        jComboBox4.setModel(new DefaultComboBoxModel(z));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if (!evt.getValueIsAdjusting()) { 
            String selectedValue = jList1.getSelectedValue();
            Elementos_Hash x = info.buscar(selectedValue);
            String stringautores = arrayAString(x.getAutores());
            String stringpclaves = arrayAString(x.getP_clave());
            jTextArea2.setText("Título: "+x.getTitulo()+"\n\nAutores: "+stringautores+"\n\n"+x.getResumen()+"\n\npalabras Clave: "+stringpclaves);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{
           Elementos_Hash elem = info.buscar((String)jComboBox1.getSelectedItem());
           String x = arrayAString(elem.getAutores());
           jTextArea1.setText("Nombre del trabajo: " + elem.getTitulo() +"\nAutor(es): " + x + "\nPalabas Clave:");
           for (int i = 0; i < elem.getP_clave().length ; i++) {
               ElementosHalt y = pClave.buscar(elem.getP_clave()[i]);
               if( y.getTitulo().equals(elem.getTitulo())){
                x = y.getFrecuencia() +"";
                jTextArea1.append("\n"+ elem.getP_clave()[i]+ ", Frecuencia:" + x + " ");
               }
               else{
                   while(!y.getTitulo().equals(elem.getTitulo())){
                       y=y.getNext();
                   }
                    x = y.getFrecuencia() +"";
                    jTextArea1.append("\n"+ elem.getP_clave()[i]+ ", Frecuencia:" + x + " ");
               }
           }
       }catch(Exception a){
        
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
        ElementosHalt elem = auth.buscar((String)jComboBox2.getSelectedItem());
        DefaultListModel model = new DefaultListModel();
        while(elem!=null){
            model.addElement(elem.getTitulo());
            elem = elem.getNext();
        }
        jList1.setModel(model);
        }catch(Exception e){
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
        ElementosHalt elem = pClave.buscar((String)jComboBox3.getSelectedItem());
        DefaultListModel model = new DefaultListModel();
        while(elem!=null){
            model.addElement(elem.getTitulo());
            elem = elem.getNext();
        }
        jList1.setModel(model);
        }catch(Exception e){
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try{
        this.guardarYsalir();
        JOptionPane.showMessageDialog(rootPane, "Los cambios se han guardado exitosamente, el programa terminará");
        System.exit(0);
        }catch(Exception e){
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            ElementosHalt elem = pClave.buscar((String)jComboBox4.getSelectedItem());
            jTextArea3.setText("Artículos en donde aparecece:\n");
            while(elem!=null){
                jTextArea3.append("Título:" + elem.getTitulo()+ "\n--->Frecuencia en este título:" + elem.getFrecuencia()+"\n");
                elem=elem.getNext();
            }
        }catch (Exception e){
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}
