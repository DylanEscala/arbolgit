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
//        SBT<Integer> tree=new SBT<>(Comparator.comparing((Integer x) -> x));
//        SBT<Integer> tree2=new SBT<>(Comparator.comparing((Integer x) -> x));
//        for (int i=1;i<5;i++){
//            tree.add(i);
//            tree2.add(i);
//            tree2.add(i*2);
//            tree.add(i*2);
//            
//        }
//        System.out.println("Los arboles son iguales? "+tree.equals(tree2));
//        SBT<Integer> mirror=tree.mirror();
//        System.out.println("Nivel del 4: "+tree.nivel(4));
//        System.out.println("El arbol contiene al 8? "+tree.contains(8));
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
