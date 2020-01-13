/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_arbol;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.util.Comparator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tree.SBT;

/**
 *
 * @author dylan
 */
public class PaneOrganizer {

    SBT<Integer> tree;
    Pane pn;
    TextField add;
    TextField del;
    ScrollPane p;

    public PaneOrganizer() {
        tree = new SBT<>(Comparator.comparing((Integer x) -> x));
        p = new ScrollPane();
        p = tree.getPane((int) p.getWidth());
        p.setMaxHeight(1000);
        p.setMaxWidth(1000);
        pn = new Pane();
    }

    public Pane getRoot() {
        Button btna = new Button("Agregar");
        Button btnd = new Button("Eliminar");
        add = new TextField();
        del = new TextField();
        HBox hb = new HBox();
        hb.getChildren().addAll(btna, add, btnd, del);
        VBox vb = new VBox();
        vb.getChildren().addAll(hb, p);

        btna.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (add.getText().equals("")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("¡Error!");
                    alerta.setContentText("¡No ha ingresado ningun número!");
                    alerta.showAndWait();
                } else {
                    try {
                        int i = Integer.parseInt(add.getText());
                        tree.add(i);
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        p = tree.getPane((int) p.getWidth());
                                        p.setMaxHeight(1000);
                                        p.setMaxWidth(1000);
                                        vb.getChildren().remove(1);
                                        vb.getChildren().add(p);
                                    }
                                });
                            }
                        });
                        t.start();

                    } catch (Exception e) {
                    } finally {
                        add.setText("");
                    }
                }
            }
        });
        btnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (del.getText().equals("")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("¡Error!");
                    alerta.setContentText("¡No ha ingresado el número que desea eliminar!\nRevise nuevamente la entrada");
                    alerta.showAndWait();
                } else {
                    try {
                        int i = Integer.parseInt(del.getText());
                        tree.remove(i);
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        p = tree.getPane((int) p.getWidth());
                                        p.setMaxHeight(1000);
                                        p.setMaxWidth(1000);
                                        vb.getChildren().remove(1);
                                        vb.getChildren().add(p);
                                    }
                                });
                            }
                        });
                        t.start();

                    } catch (Exception e) {
                    } finally {
                        del.setText("");
                    }
                }
            }
        });

        pn.getChildren().add(vb);

        return pn;
    }
}
