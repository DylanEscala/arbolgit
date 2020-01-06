/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.Comparator;

/**
 *
 * @author Melina
 * @param <E>
 */
public class SBT<E> {
    private Node<E> root;
    private Comparator<E> f;
    
    public SBT(Comparator<E> f) {
        this.f = f;
        this.root = null;
    }
    
    public int height(){
        return height(root);
    }
    
    //funcion auxiliar
    public int height(Node<E> n){
        if(n==null) return 0;
        return 1+ Math.max(height(n.getLeft()),height(n.getRight()));
        
    }
    
    private int equilibrio(Node<E> nodo){
        if(nodo==null)
            return -1;
        return height(nodo.getRight())-height(nodo.getLeft());
    }
    
    //TO DO: ROTACIONES
    
    
    
    
    
    public boolean add(E element){
        if(element==null)
            return false;
        this.root=add(element,root);
        return true;
    }
    
    private Node<E> add(E element,Node<E> n){
        if(n==null)
            n=new Node<>(element);
        else if(f.compare(element,n.getData())>0)
            n.setRight(add(element,n.getRight()));
        else if(f.compare(element,n.getData())<0)
            n.setLeft(add(element,n.getLeft()));
        
        n.setEquilibrio(equilibrio(n));
        if(n.getEquilibrio()==2||n.getEquilibrio()==-2)
            return equilibrarArbol(element,n,n.getEquilibrio());
        
        
        
        return n;
    }
    
    public E min(){
        return min(root);
       
    }
    public E max(){
        return min(root);
       
    }
    
    public E max(Node<E> n){
        if(n==null)
            return (E) n;
        else if(n.getRight()==null)
            return n.getData();
        else
            return min(n.getRight());
        
    }
    
    public E min(Node<E> n){
        if(n==null)
            return (E) n;
        else if(n.getLeft()==null)
            return n.getData();
        else
           return min(n.getLeft());
       
    }
    
    public boolean isEmpty(){
        return this.root==null;
    }
    
    private Node<E> equilibrarArbol(E data,Node<E> nodo,int valor){
        if(valor==2){
            
            
            
            
        }
        
        else if(valor==-2){
             
            
            
        }
        return nodo;
    }
    
    public boolean remove(E element){
        if(element==null || isEmpty())
            return false;
        this.root=remove(element,root);
        return true;
    }
    
    
    private Node<E> remove(E element, Node<E> n){
        if(n==null)
            return n;
        else if(f.compare(element,n.getData())>0)
            n.setRight(remove(element,n.getRight()));
        else if(f.compare(element,n.getData())<0)
            n.setLeft(remove(element,n.getLeft()));
        else if(n.getLeft()!=null&&n.getRight()!=null){
           n.setData(max(n.getLeft()));
           n.setLeft(remove(n.getData(),n.getLeft()));
        }  
        //en caso de que solo tenga un hijo
        else{
            n=(n.getLeft()!=null)?n.getLeft():n.getRight();
        }
        
        //Equilibrar arbol luego de eliminar
        n.setEquilibrio(equilibrio(n));
        if(n.getEquilibrio()==2||n.getEquilibrio()==-2)
            return equilibrarArbol(element,n,n.getEquilibrio());
        
        return n;    
    }
    
    public boolean contains(E data){
        if (data == null || isEmpty()) return false;
        //if (root == null) return false;
        return contains(root,data);
    }
    
    private boolean contains(Node<E> n,E element){
        if(n==null)
            return false;
        else if(f.compare(element,n.getData())==0)
            return true;
        else if(f.compare(element,n.getData())>0)
            return contains(n.getRight(),element);
        else if(f.compare(element,n.getData())<0)
            return contains(n.getLeft(),element);
        return false;
    }
    
    
    //Recorridos de Arboles
    
    public void posOrden(){
        posOrden(root);
    }
    
    private void posOrden(Node<E> nodo){
        if (nodo!=null){
            posOrden(nodo.getLeft());
            posOrden(nodo.getRight());
            System.out.print(nodo.getData()+" ");
        }
    }
    
    public void preOrden(){
        preOrden(root);
    }
    
    private void preOrden(Node<E> nodo){
        if (nodo!=null){
            //raiz
            System.out.print(nodo.getData()+" ");
            // subarbol izquierdo
            preOrden(nodo.getLeft());
            // subarbol derecho 
            preOrden(nodo.getRight());
            
        }
    }
    
    public void EnOrden(){
        EnOrden(root);
    }
    
    private void EnOrden(Node<E> nodo){
        if (nodo!=null){
             // subarbol izquierdo
            EnOrden(nodo.getLeft());
            //raiz
            System.out.print(nodo.getData()+" ");
            // subarbol derecho 
            EnOrden(nodo.getRight());
            
        }
    }
}
