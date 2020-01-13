/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.Comparator;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author dylan
 */
public class SBT<E> {

    private Node<E> root;
    private Comparator<E> f;

    public SBT(Comparator<E> f) {
        this.f = f;
        root = null;
    }

    public boolean add(E data) {
        if (data == null) {
            return false;
        }
        this.root = add(data, root);
        return true;
    }

    private Node<E> add(E element, Node<E> node) {
        if (node == null) {
            node = new Node<>(element);
        } else if (f.compare(element, node.getData()) > 0) {
            node.setRight(add(element, node.getRight()));
        } else if (f.compare(element, node.getData()) < 0) {
            node.setLeft(add(element, node.getLeft()));
        }
        node.updateNode();
        node = node.balance();
        return node;
    }

    public E min() {
        return min(root);
    }

    private E min(Node<E> n) {
        if (n == null) {
            return null;
        } else if (n.getLeft() == null) {
            return n.getData();
        } else {
            return min(n.getLeft());
        }

    }

    public E max() {
        return max(root);
    }

    public E max(Node<E> n) {
        if (n == null) {
            return null;
        } else if (n.getRight() == null) {
            return n.getData();
        } else {
            return min(n.getRight());
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean remove(E element) {
        if (element == null || isEmpty()) {
            return false;
        }
        this.root = remove(element, root);
        return true;
    }

    private Node<E> remove(E element, Node<E> n) {
        if (n == null) {
            return n;
        } else if (f.compare(element, n.getData()) > 0) {
            n.setRight(remove(element, n.getRight()));
        } else if (f.compare(element, n.getData()) < 0) {
            n.setLeft(remove(element, n.getLeft()));
        } else if (n.getLeft() != null && n.getRight() != null) {
            n.setData(max(n.getLeft()));
            n.setLeft(remove(n.getData(), n.getLeft()));
        } else {
            System.out.println("deleting");
            n = (n.getLeft() == null) ? n.getLeft() : n.getRight();
        }
        if(n!=null){
        n.updateNode();
        n = n.balance();
        }
        return n;
    }

    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        return contains(element, root);
    }

    private boolean contains(E element, Node<E> node) {
        if (root == null) {
            return false;
        } else if (f.compare(element, node.getData()) > 0) {
            contains(element, node.getRight());
        } else if (f.compare(element, node.getData()) < 0) {
            contains(element, node.getLeft());
        } else {
            return node.getData().equals(element);
        }
        return true;
    }

    public boolean equals(SBT<E> tree) {
        return equals(tree.root, root);
    }

    private boolean equals(Node<E> node1, Node<E> node2) {
        boolean right = false;
        boolean left = false;
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 != null && node2 != null) {
            if (node1.getData().equals(node2.getData())) {
                right = equals(node1.getRight(), node2.getRight());
                left = equals(node1.getLeft(), node2.getLeft());
                return right && left;
            }
        }
        return false;
    }

    public int nivel(E data) {
        return nivel(root, data);
    }

    private int nivel(Node<E> root, E data) {
        int var = 0;
        if (root == null) {
            return 0;
        } else if (root.getData().equals(data)) {
            return 1;
        } else {
            if (f.compare(data, root.getData()) > 0) {
                var = nivel(root.getRight(), data);
                if (var > 0) {
                    return 1 + var;
                }
            } else {
                var = nivel(root.getRight(), data);
                if (var > 0) {
                    return 1 + var;
                }
            }
            return 0;
        }
    }

    public SBT<E> mirror() {
        SBT<E> arbol = new SBT<E>(f);
        mirror(root, arbol.root);
        return arbol;
    }

    private void mirror(Node<E> node, Node<E> node1) {
        if (node != null) {
            node1 = new Node<>(node.getData());
            mirror(node.getLeft(), node1.getRight());
            mirror(node.getRight(), node1.getLeft());
        }
    }

    public ScrollPane getPane() {
        Pane p = new Pane();
        getPane(p, root, 200, 20);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(p);
        return scrollPane;
    }

    private void getPane(Pane p, Node<E> nod, int x, int y) {
        if (nod != null) {
            int h=Node.heigh(nod);
            Line l1;
            Line l2;
            StackPane pa = new StackPane();
            int ra=30;
            Circle circle = new Circle(ra, Color.ALICEBLUE);
            if(nod.getLeft()!=null){
                l1=new Line(x+30, y+30, x-2*ra*h+30, y+3*ra+30);
                p.getChildren().add(l1);
            }
            if(nod.getRight()!=null){
                l2=new Line(x+30,y+30, x+ra*(2)*h+30, y+3*ra+30);
                p.getChildren().add(l2);
            }
            Label label=new Label(nod.getData().toString());
            pa.getChildren().addAll(circle,label);
            pa.setLayoutX(x);
            pa.setLayoutY(y);
            p.getChildren().add(pa);
            getPane(p, nod.getLeft(), x-2*ra*h, y+3*ra);
            getPane(p, nod.getRight(), x+ra*(2)*h, y+3*ra);
        }
    }//comm
}
