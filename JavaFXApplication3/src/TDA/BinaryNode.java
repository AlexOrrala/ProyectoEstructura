package TDA;

import java.util.LinkedList;

public class BinaryNode<T> {
    
    private T content;
    private  LinkedList<BinaryTree<T>> childrens;
    
    public BinaryNode() {
        this(null, null);
    }

    public BinaryNode(T content) {
        this(content, null);
    }

    public BinaryNode(T content, BinaryTree<T> children) {
        this.content = content;
        this.childrens = new LinkedList<BinaryTree<T>>();
        this.childrens.add(children);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


    public LinkedList<BinaryTree<T>> getChildrens() {
        return childrens;
    }

    public void addChildren(BinaryTree<T> children) {
        childrens.add(children);
    }
    
}
