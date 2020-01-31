/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_arbol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tree.SBT;

/**
 *
 * @author dylan
 */
public class PaneOrganizer {
    private SBT<Integer> tree;
    private VBox root;
    private BorderPane panel_superior;
    private Pane pn;
    private TextField add;
    private TextField del;
    private Label titulo;
    private ScrollPane p;
    private VBox superior;

    public PaneOrganizer(){
        root= new VBox();
        panel_superior=new BorderPane();
        tree=new SBT<>(Comparator.comparing((Integer x) -> x));
        p=tree.getPane();
        pn=new Pane();
        titulo=new Label("ARBOL AVL");
        add=new TextField();
        del=new TextField();
        superior= new VBox();
    }
    public Pane getRoot(){
        add.setPrefSize(200, 40);
        del.setPrefSize(200, 40);
        titulo.setTextFill(Color.web("#ff0000", 0.8));
        titulo.setFont(Font.font("Time New Roman", 32));
        superior.getChildren().add(titulo);
        superior.setAlignment(Pos.CENTER);
        
        //Colocar Imagenes en los Botones
        FileInputStream input=null;
        try {
            input = new FileInputStream("src/resources/agregar.jpg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        Button btna = new Button("Agregar",imageView);
        
        String style= 
                "-fx-background-color:  #FFFFFF;" +
                "-fx-border-color: #778899;" + 
                "-fx-border-radius: 5 5 5 5;"+
                "-fx-border-width:1;"+
                "-font-family: Times New Roman;";
        
        //Boton Agregar
        btna.setStyle(style);
        FileInputStream input2=null;
        try {
            input2 = new FileInputStream("src/resources/eliminar.jpg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image imagen = new Image(input2);
        ImageView imageV = new ImageView(imagen);
        imageV.setFitHeight(30);
        imageV.setFitWidth(30);
        
        //Boton Eliminar 
        Button btnd = new Button("Eliminar",imageV);
        btnd.setStyle(style);
        HBox hb=new HBox();
        HBox hb1=new HBox();
        panel_superior.setTop(superior);
        hb.getChildren().addAll(btna,add);
        hb1.getChildren().addAll(btnd,del);
        panel_superior.setLeft(hb);
        panel_superior.setRight(hb1);
        p.setPrefViewportHeight(600);
        panel_superior.setBottom(p);
        root.getChildren().addAll(panel_superior,p);
        
       

      
        
        btna.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if(add.getText().equals("")){
                   Alert alerta = new Alert(Alert.AlertType.ERROR);
                   alerta.setHeaderText("¡Error!");
                   alerta.setContentText("¡No ha ingresado ningun número!");
                   alerta.showAndWait();
                }
                else{    
                    try {
                        int i=Integer.parseInt(add.getText());
                        tree.add(i);
                        Thread t=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        p=tree.getPane();
                                        root.getChildren().remove(1);
                                        p.setPrefViewportHeight(600);
                                        root.getChildren().add(p);
                                        
                                    }
                                });
                            }
                        });
                        t.start();
                        
                    } catch (Exception e) {
                    }finally{
                        add.setText("");
                                }
            }
            }
        });
        btnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if(del.getText().equals("")){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("¡Error!");
                    alerta.setContentText("¡No ha ingresado el número que desea eliminar!\nRevise nuevamente la entrada");
                    alerta.showAndWait();
                }
                else{    
                    try {
                        int i=Integer.parseInt(del.getText());
                        tree.remove(i);
                        Thread t=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        p=tree.getPane();
                                        root.getChildren().remove(1);
                                        p.setPrefViewportHeight(600);
                                        root.getChildren().add(p);
                                 
                                    }
                                });
                            }
                        });
                        t.start();
                        
                    } catch (Exception e) {
                    }finally{
                        del.setText("");
                                }
            }
            }
        });
                
        String escenario = "-fx-background-color: #F5FFFA;";
        root.setStyle(escenario);
        return root;
    }
}
