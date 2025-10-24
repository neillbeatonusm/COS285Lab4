package lab4;

import java.util.*;

/**Creates a MyBinaryTree object that stores unsorted nodes in a binary tree.
 * @param <E> the data type of the elements in the tree
 * @author Abby Pitcairn
 * @version October 18, 2025
 */
public class MyBinaryTree<E extends Comparable<E>> {
    
    /**Root Node of the tree*/
    protected Node<E> root;
    
    /**Nested class for a Node object
     * @param <E> the data type of the element in the node
     */
    protected static class Node<E>{
        E data;
        Node<E> left;
        Node<E> right;
        
        Node(E data){
            this.data = data;
        }
    }
    
    /**Recursively searches for the next available insertion spot
     * and inserts a new node with the given value to that space
     * in the tree.
     * @param value - the value to insert into the tree.
     */
    public void insert(E value) {
        root = insertRecursively(root, value);
    }

    /** Recursive helper for insertion.
     * @param current - the current Node being evaluated.
     * @param value - the value to insert into the tree.
     */
    private Node<E> insertRecursively(Node<E> current, E value) {
        if (current == null) {
            return new Node<>(value);
        }
        if (value.compareTo(current.data) < 0) {
            current.left = insertRecursively(current.left, value);
        } else if (value.compareTo(current.data) > 0) {
            current.right = insertRecursively(current.right, value);
        }
        return current;
    }
    /** 
     * Adds values from a list into the tree one by one.
     * Calls insert() for every element in the list.
     * @param elements the list of items to add to the tree
     */
    public void buildTree(List<E> elements) {
        for (E element : elements) {
            insert(element);
        }
    }

    /**
     * Prints out all the nodes in the tree using Breadth First Search.
     * Starts from the root and goes level by level from left to right.
     */
    public void bfsPrintTree() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node<E> node = q.remove();
            System.out.print(node.data + " ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
        System.out.println();
    }

    /**
     * Searches the tree for a value using recursion.
     * Returns true if the value is found, false if it’s not.
     * @param target the value to search for in the tree
     * @return true if the value exists in the tree else false
     */
    public boolean recursiveSearch(E target) {
        if (root == null) {
            return false;
        }
        Node<E> node = root;

        if (node.data.equals(target)) {
            return true;
        }

        if (target.compareTo(node.data) < 0) {
            if (node.left == null) {
                return false;
            }
            MyBinaryTree<E> leftTree = new MyBinaryTree<>();
            leftTree.root = node.left;
            return leftTree.recursiveSearch(target);
        } else {
            if (node.right == null) {
                return false;
            }
            MyBinaryTree<E> rightTree = new MyBinaryTree<>();
            rightTree.root = node.right;
            return rightTree.recursiveSearch(target);
        }
    }

}
