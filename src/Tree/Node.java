package tree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dylan
 */
public class Node<E> {

    private E data;
    private Node<E> left;
    private Node<E> right;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public Node(E data) {
        right = left = null;
        this.data = data;
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

    public boolean hasChilds() {
        return this.getLeft() != null || this.getRight() != null;
    }

    public static int heigh(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(heigh(node.getLeft()), heigh(node.getRight()));
        }
    }

    public void updateNode() {
        balance=heigh(this.right)-heigh(this.left);
    }

    public Node<E> balance() {
        if (this.getBalance() > 1) {
            if (right!=null&&this.getRight().balance >= 0) {
                Node<E> temp = this.right;
                this.setRight(temp.getLeft());
                temp.setLeft(this);
                return temp;
            } else {
                if(this.right!=null){
                   Node<E> temp2 = this.right.left;
                    this.right.left = temp2.right;
                    temp2.right = this.right;
                    this.right = temp2.left;
                    temp2.left = this;
                    return temp2;
                }
                
            }
        } else if (this.getBalance() < -1) {
            if (left!=null&&this.getLeft().balance <= 0) {
                Node<E> temp = this.left;
                this.setLeft(temp.getRight());
                temp.setRight(this);
                return temp;
            } else {
                if(this.left!=null){
                    Node<E> temp2 = this.left.right;
                    this.left.right = temp2.left;
                    temp2.left = this.left;
                    this.left = temp2.right;
                    temp2.right = this;
                    return temp2;
                }
                
            }
        }
        return this;
    }
    
}
