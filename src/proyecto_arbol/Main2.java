package proyecto_arbol;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Comparator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dylan
 */
public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
//      
//    
    }

    @Override
    public void start(Stage stage) throws Exception {
        PaneOrganizer po=new PaneOrganizer();
        Scene scene = new Scene(po.getRoot(),1000,900);
        stage.setTitle("ARBOL AVL");
        stage.setScene(scene);
        stage.show();
    }
}
