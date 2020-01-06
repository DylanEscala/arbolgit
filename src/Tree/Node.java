/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author Melina
 */

public class Node<E> {
    
    private E data;
    private Node<E> left;
    private Node<E> right;
    private int equilibrio;

   
        
    public Node(E data){
        right=left=null;
        this.data=data;
        equilibrio=0;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
    
    public int getEquilibrio() {
        return equilibrio;
    }

    public void setEquilibrio(int equilibrio) {
        this.equilibrio = equilibrio;
    }
    
   
    

}
