package TDA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author alex_
 */
public class BinaryTree<T> {
    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = new BinaryNode<>();
    }
    
    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int countlevels(){
        int cont=0;
        int cont1=0;
        int cont2=0;
        if(this.isEmpty()){
            cont+=0;
        }else if(this.isLeaf()){
            cont+=1;
        }else{
            if (this.root.getLeft() != null ) {
                    cont1 = cont1+1+this.root.getLeft().countlevels();
                }
            if (this.root.getRight() != null) {
                    cont2 = cont2+1+this.root.getRight().countlevels();
                }
            if(cont1>cont2){
                cont+=cont1;
            }else{
                cont+=cont2;
            }
        }
        return cont;    
    }
    
    public boolean isLefty(){
        if(this.isEmpty()){
            return true;
        }else if(this.isLeaf()){
            return true;       
        }else return this.root.getLeft().isLefty() && 
                this.root.getRight().isLefty() &&
                this.root.getLeft().recursivecountDescendants()>(this.recursivecountDescendants() / 2);
    }
    
    public boolean isIdentical(BinaryTree<T> arbol2){
        if(this.isEmpty() && arbol2.isEmpty()){
            return true;        
        }else if((this!= null && arbol2!= null)
                && (this.getRoot().equals(arbol2.getRoot()))
                ){
            return this.getRoot().getLeft().isIdentical(arbol2.getLeft()) 
                    && this.getRoot().getRight().isIdentical(arbol2.getRight()) ;
        }
        return false;
    }
    
    public int countNodesWithOnlyChild(){
        int cont=0;
        if (this.isEmpty()) {
            cont+=0;
        } else if (this.isLeaf()) {
            cont+=0;
        } else {
            if ((this.root.getLeft() != null) && (this.root.getRight() == null) ) {
                    cont = cont+1+this.root.getLeft().countNodesWithOnlyChild();
                }
            if ((this.root.getRight() != null) && (this.root.getLeft() == null) ) {
                    cont = cont+1+this.root.getRight().countNodesWithOnlyChild();
                }
        }
        return cont;
    }
    
    public boolean isHeightBalanced(){
        if (this.isEmpty()) {
            return true;
        } else if (this.root.getLeft() != null &&
                   this.root.getRight() != null
                ) {
            return  this.root.getLeft().isHeightBalanced() && 
                    this.root.getRight().isHeightBalanced() &&
                    (this.root.getLeft().countlevels()-this.root.getRight().countlevels()<1);
        } return false;
    }
    
    public void largestValueOfEachLevel(){
        Stack<BinaryTree<T>> stack = new Stack();
        ArrayList n1 = new ArrayList();
        int contador = 1;
        if (this.isEmpty()) {
            System.out.println("Vacío");
        } else {
            stack.push(this);
            while (!stack.empty()) {
                
                BinaryTree<T> subtree = stack.pop();
                boolean izquierda = subtree.root.getLeft() != null;
                boolean derecha = subtree.root.getRight() != null;
                if (izquierda) {
                    
                    stack.push(subtree.root.getLeft());
                }
                if (derecha) {
                    stack.push(subtree.root.getRight());
                }
                if(subtree.root.getLeft().isLeaf()){
                    //n1.add(subtree.root.getLeft().get)
                }
            }
        }
    }
    public BinaryNode<T> findParent(BinaryTree<T> nodo){
        
        if (nodo!= null) {
            findParent(nodo.getLeft());
            if(!nodo.isLeaf()){
                //System.out.println(nodo.getRoot().getContent());
                return nodo.getRoot();
            }
            findParent(nodo.getRight());
            
        }
        
    
        return null;
    }
    
    
    public BinaryNode<T> recursiveSearch(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content, cmp);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content, cmp);
                    }
                }
                return tmp;
            }
        }
    }

    private int recursivecountDescendants() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
}
