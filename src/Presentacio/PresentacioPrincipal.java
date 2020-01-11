package Presentacio;

import Persistencia.ControladorPersistencia;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static Presentacio.ControladorPresentacio.getFolderSize;

/**
 * @class Clase Presentacio Principal
 * @author Miquel Perelló
 *
 */
public class PresentacioPrincipal {

    private ControladorPresentacio p;

    public PresentacioPrincipal() {
        p = new ControladorPresentacio();
    }


    public static void main(String args[]) {
        ClasePrincipal clp = new ClasePrincipal();
    }

    /**
     * @brief Pantalla Inicial
     */
    static class ClasePrincipal {
        Frame miFrame;
        JLabel textinicial = new JLabel("Hola, bienvenido a Compressor! Elige que quieres hacer:");
        Button Comprimir = new Button("Comprimir");
        Button Descomprimir = new Button("Descomprimir");
        Button Visualize = new Button("Visualizar Archivo");
        /**
         * @brief Clase que implementa los tres botones principales
         */
        public ClasePrincipal() {

            miFrame = new Frame("Compressor");
            miFrame.setLayout(new BoxLayout(miFrame, BoxLayout.Y_AXIS));

            miFrame.add(textinicial);
            miFrame.add(Comprimir);
            miFrame.add(Box.createGlue());
            miFrame.add(Descomprimir);
            miFrame.add(Box.createGlue());
            miFrame.add(Visualize);


            // Fixem el tamany del Frame
            miFrame.setSize(500, 500);
            miFrame.setVisible(true);


            Comprimir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ClaseComprimir();
                }
            });

            Descomprimir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ClaseDescomprimir();
                }
            });


           Button botoback = new Button("Back!");
            Visualize.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    File f = null;
                    JFileChooser chooser;
                    chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "Images", "ppm", "txt");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        f = new File(chooser.getSelectedFile().getPath());
                    }
                    String fi = f.getPath();
                    String extension = fi.substring(fi.lastIndexOf("."), fi.length());


                    if (extension.equals(".ppm")) {
                        try {
                            miFrame.removeAll();
                            byte [] b = ControladorPresentacio.LlegeixEscriuArxiu(f.getPath());
                            InputStream in = new ByteArrayInputStream(b);
                            BufferedImage bImageFromConvert = ImageIO.read(in);
                            JLabel picLabel = new JLabel(new ImageIcon(bImageFromConvert));
                            JScrollPane scrollPanel = new JScrollPane(picLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                            miFrame.add(scrollPanel);
                            JPanel myPanel2 = new JPanel();
                            myPanel2.add(Visualize);
                            myPanel2.add(botoback);
                            miFrame.add(myPanel2);
                            miFrame.setVisible(true);

                        } catch (Exception er) {
                            System.out.println(er.getMessage());
                        }
                    }

                    if (extension.equals(".txt")) {
                        miFrame.removeAll();
                        miFrame.setVisible(false);
                        JPanel myPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JPanel myPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        myPanel1.setPreferredSize(new Dimension(1000, 1000));

                        JTextArea j = new JTextArea();
                        JScrollPane sp = new JScrollPane();


                        FileReader reader = null;
                        try {
                            reader = new FileReader(f);
                            BufferedReader br = new BufferedReader(reader);
                            j.read(br, null);
                            br.close();
                            j.requestFocus();
                            sp.setViewportView(myPanel1);
                            myPanel1.add(j);
                            miFrame.add(sp);


                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        myPanel2.add(Visualize);
                        myPanel2.add(botoback);
                        miFrame.add(myPanel2);
                        miFrame.setVisible(true);
                    }

                }

            });

            botoback.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    miFrame.removeAll();
                    miFrame.setVisible(false);
                    miFrame.add(textinicial);
                    miFrame.add(Comprimir);
                    miFrame.add(Box.createGlue());
                    miFrame.add(Descomprimir);
                    miFrame.add(Box.createGlue());
                    miFrame.add(Visualize);
                    miFrame.setVisible(true);
                }
            });

            miFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            });
        }
        public File fichero;
        public File ficherodesti;
        /**
         * @brief Funcion que llamamos cuando presionamos el botón comprimir
         */
        public void ClaseComprimir() {
            miFrame.removeAll();
            Label headerLabel = new Label();
            headerLabel.setText("Selecciona que archivo quieres:");
            Button botoseleccioarxiu = new Button("Arxiu ");
            Button botoselecciodesti = new Button("Desti");
            Button botookarxiu = new Button("Ok!");
            Button botoback = new Button("Back!");
            JPanel primerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Label seleccioarxiu = new Label("Selecciona que archivo quieres comprimir:");
            primerpanel.add(seleccioarxiu);
            primerpanel.add(botoseleccioarxiu);

            JPanel segonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Label selecciodesti = new Label("Selecciona el destino donde quieres que se comprima el archivo:");
            segonpanel.add(selecciodesti);
            segonpanel.add(botoselecciodesti);
            miFrame.add(primerpanel);
            miFrame.add(Box.createGlue());
            miFrame.add(segonpanel);
            miFrame.add(Box.createGlue());
            miFrame.add(botookarxiu);
            miFrame.add(Box.createGlue());
            miFrame.add(botoback);
            miFrame.setVisible(true);

            botoseleccioarxiu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    int seleccion = fileChooser.showOpenDialog(botoseleccioarxiu);
                    if (seleccion == JFileChooser.APPROVE_OPTION)  fichero = fileChooser.getSelectedFile();
                }
            });
            botoselecciodesti.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int seleccion = fileChooser.showOpenDialog(botoseleccioarxiu);
                    if (seleccion == JFileChooser.APPROVE_OPTION) ficherodesti = fileChooser.getSelectedFile();
                }
            });

            botoback.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    miFrame.removeAll();
                    miFrame.add(textinicial);
                    miFrame.add(Comprimir);
                    miFrame.add(Box.createGlue());
                    miFrame.add(Descomprimir);
                    miFrame.add(Box.createGlue());
                    miFrame.add(Visualize);
                }
            });

            Button calitatOk = new Button("Ok!");
            JSlider selector;
            selector = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

            botookarxiu.addActionListener(new ActionListener() {
                long size_input;
                long size_output;
                long start_time;
                long end_time;
                public void actionPerformed(ActionEvent e) {
                    String fi = fichero.getPath();
                    String extension = "";
                    try {
                        extension = fi.substring(fi.lastIndexOf("."), fi.length());
                    }
                    catch(Exception ce){

                    }
                    if(extension.equals(".ppm")){
                        miFrame.removeAll();
                        Label Txt = new Label();
                        Txt.setText("De acuerdo, escoge la calidad:");
                        miFrame.add(Txt);
                        JPanel controlPanel = new JPanel();
                        controlPanel.add(selector);
                        controlPanel.add(calitatOk);
                        miFrame.add(controlPanel);
                        miFrame.setVisible(true);
                    }
                    else if ( extension.equals(".txt") || ControladorPresentacio.staticisDirectory(fi)){
                        miFrame.removeAll();
                        Button bob = new Button("Loading...");
                        miFrame.add(bob);
                        miFrame.setVisible(true);
                        String path = fichero.getPath();
                        String path_out = ficherodesti.getPath();
                        if (extension.equals(".txt")) path_out= ficherodesti.getPath() +"//"+ ControladorPresentacio.getNameFile(fichero.getPath());
                        start_time = System.currentTimeMillis();
                        ControladorPresentacio.comprimir(path, path_out, 100);
                        end_time = System.currentTimeMillis();
                        size_input = ControladorPresentacio.getsize(fichero.getPath()); // retorna en bytes tamany final
                        size_output = ControladorPresentacio.getsize(ficherodesti.getPath());
                        Label tempsLabel = new Label();
                        tempsLabel.setText("El temps ha sigut de : " + (end_time - start_time) + " milisegons\n");
                        Label sizeLabel = new Label();
                        Label ratioLabel = new Label();
                        if(extension.equals(".txt")) {
                            sizeLabel.setText(" Size of compression is " + size_output);
                            ratioLabel.setText(" Compress ratio is " + (double) (size_input / size_output));
                        }
                        else{
                            sizeLabel.setText(" Size of compression is " + ControladorPresentacio.getFolderSize(path_out + "//" + ControladorPresentacio.getNameFile(path) +".prop"));
                            ratioLabel.setText(" Compress ratio is " + (double) (ControladorPresentacio.getFolderSize(path) / ControladorPresentacio.getFolderSize(path_out + "//" + ControladorPresentacio.getNameFile(path) +".prop")));
                        }
                        miFrame.removeAll();
                        miFrame.add(tempsLabel);
                        miFrame.add(sizeLabel);
                        miFrame.add(ratioLabel);
                        miFrame.add(botoback);
                        miFrame.setVisible(true);
                    }

                }
            });
            /**
             * Pulsando sobre el boton OK dentro de calidad efectuamos esta llamada.
              */
            calitatOk.addActionListener(new ActionListener() {
                long size_input;
                long size_output;
                long start_time;
                long end_time;

                public void actionPerformed(ActionEvent e) {
                    Integer calitat = selector.getValue();
                    miFrame.removeAll();
                    Button bob = new Button("Loading...");
                    miFrame.add(bob);
                    miFrame.setVisible(false);

                    String path_out= ficherodesti.getPath() +"//"+ ControladorPresentacio.getNameFile(fichero.getPath());
                    start_time = System.currentTimeMillis();
                    ControladorPresentacio.comprimir(fichero.getPath(), ficherodesti.getPath(), calitat);
                    end_time = System.currentTimeMillis();
                    size_input = ControladorPresentacio.getsize(fichero.getPath());
                    size_output = ControladorPresentacio.getsize(path_out);
                    System.out.println(path_out);
                    Label tempsLabel = new Label();
                    tempsLabel.setText("El temps ha sigut de : " + (end_time - start_time) + " milisegons\n");
                    Label sizeLabel = new Label();
                    sizeLabel.setText(" Size of compression is " + size_output);
                    Label ratioLabel = new Label();
                    ratioLabel.setText(" Compress ratio is " + (double) (size_input / size_output));
                    miFrame.removeAll();
                    miFrame.add(tempsLabel);
                    miFrame.add(sizeLabel);
                    miFrame.add(ratioLabel);
                    miFrame.add(botoback);
                    miFrame.setVisible(true);
                }
            });
        }
        /**
         * @brief Funcion que llamamos cuando presionamos el botón descomprimir
         */
        public void ClaseDescomprimir() {
                    miFrame.removeAll();
                    Label headerLabel = new Label();
                    headerLabel.setText("Selecciona que archivo quieres descomprimir:");
                    Button botoseleccioarxiu = new Button("Arxiu comprimit ");
                    Button botoselecciodesti = new Button("Arxiu descomprimit");
                    Button botookarxiu = new Button("Ok!");
                    Button botoback = new Button("Back");
                    JPanel primerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    primerpanel.add(headerLabel);
                    primerpanel.add(botoseleccioarxiu);
                    JPanel segonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    Label selecciodesti = new Label("Selecciona el destino donde quieres que se descomprima el archivo:");
                    segonpanel.add(selecciodesti);
                    segonpanel.add(botoselecciodesti);
                    miFrame.add(primerpanel);
                    miFrame.add(Box.createGlue());
                    miFrame.add(segonpanel);
                    miFrame.add(Box.createGlue());
                    miFrame.add(botookarxiu);
                    miFrame.add(Box.createGlue());
                    miFrame.add(botoback);
                    miFrame.add(Box.createGlue());
                    miFrame.setVisible(true);

                    botoseleccioarxiu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                            int seleccion = fileChooser.showOpenDialog(botoseleccioarxiu);
                            if (seleccion == JFileChooser.APPROVE_OPTION) fichero = fileChooser.getSelectedFile();
                        }
                    });

                    botoselecciodesti.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            int seleccion = fileChooser.showOpenDialog(botoseleccioarxiu);
                            if (seleccion == JFileChooser.APPROVE_OPTION)  ficherodesti = fileChooser.getSelectedFile();
                        }
                    });

                    botookarxiu.addActionListener(new ActionListener() {
                        long size_input;
                        long size_output;
                        long start_time;
                        long end_time;
                        public void actionPerformed(ActionEvent e) {
                            String fi = fichero.getPath();
                            String extension = fi.substring(fi.lastIndexOf("."), fi.length());
                            if (extension.equals( ".ppm") || extension.equals(".txt") || ControladorPresentacio.staticisDirectory(fi)) {
                                miFrame.removeAll();
                                Button bob = new Button("Loading...");
                                miFrame.add(bob);
                                start_time = System.currentTimeMillis();
                                try {
                                    ControladorPresentacio.descomprimir(fichero.getPath(), ficherodesti.getPath());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                end_time = System.currentTimeMillis();
                                size_input = ControladorPresentacio.getsize(fichero.getPath());
                                size_output = ControladorPresentacio.getsize(ficherodesti.getPath());
                                Label tempsLabel = new Label();
                                tempsLabel.setText("El temps ha sigut de : " + (end_time - start_time) + " milisegons\n");
                                Label sizeLabel = new Label();
                                sizeLabel.setText(" Size of compression is " + size_output);
                                Label ratioLabel = new Label();
                                ratioLabel.setText(" Compress ratio is " + (double) (size_input / size_output));
                                miFrame.removeAll();
                                miFrame.add(tempsLabel);
                                miFrame.add(sizeLabel);
                                miFrame.add(ratioLabel);
                                miFrame.add(botoback);
                                miFrame.setVisible(true);
                            }

                        }
                    });
            botoback.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    miFrame.removeAll();
                    miFrame.add(textinicial);
                    miFrame.add(Comprimir);
                    miFrame.add(Box.createGlue());
                    miFrame.add(Descomprimir);
                    miFrame.add(Box.createGlue());
                    miFrame.add(Visualize);
                    miFrame.setVisible(true);
                }
            });
        }
    }
}




