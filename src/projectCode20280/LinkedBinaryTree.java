//This is my implementation of LinkedBinaryTree, which uses a node-based, linked structure. It implements the AbstractBinaryTree ADT and all functions perform well.

package projectCode20280;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E>
{

  //LinkedBinaryTree instance variables
  //The root of the binary tree
  protected Node<E> root = null;     // root of the tree

  //The number of nodes in the binary tree
  private int size = 0;              // number of nodes in the tree

  //Nested static class for a binary tree node.
  protected static class Node<E> implements Position<E>
  {
    private E element;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    protected Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild)
    {
      this.element = e;
      this.parent = above;
      this.left = leftChild;
      this.right = rightChild;
    }

    //Accessors
    public E getElement()      { return element; }
    public Node<E> getParent() { return parent; }
    public Node<E> getLeft()   { return left; }
    public Node<E> getRight()  { return right; }

    //Mutators
    public void setElement(E e) { this.element = e; }
    public void setParent(Node<E> parentNode) { this.parent = parentNode; }
    public void setLeft(Node<E> leftChild) { this.left = leftChild; }
    public void setRight(Node<E> rightChild) { this.right = rightChild; }

    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(element);
      return sb.toString();
    }
  }

  //Factory function to create a new node storing element e.
  protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right)
  {
    return new Node<E>(e, parent, left, right);
  }

  // constructor
  //Constructs an empty binary tree.
  public LinkedBinaryTree() { }      // constructs an empty binary tree

  // nonpublic utility
/*
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this tree)
   * @return    the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
*/

  protected Node<E> validate(Position<E> p) throws IllegalArgumentException
  {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)
/*
  * Returns the number of nodes in the tree.
  * @return number of nodes in the tree
*/

  @Override
  public int size() {
    return size;
  }

/*
  * Returns the root Position of the tree (or null if tree is empty).
  * @return root Position of the tree (or null if tree is empty)
*/

  @Override
  public Position<E> root() {
    return root;
  }

/*
  * Returns the Position of p's parent (or null if p is root).
  *
  * @param p    A valid Position within the tree
  * @return Position of p's parent (or null if p is root)
  * @throws IllegalArgumentException if p is not a valid Position for this tree.
*/

  @Override
  public Position<E> parent(Position<E> p)
  {
    Node<E> node = validate(p);
    return node.parent;
  }

/*
  * Returns the Position of p's left child (or null if no child exists).
  *
  * @param p A valid Position within the tree
  * @return the Position of the left child (or null if no child exists)
*/

  @Override
  public Position<E> left(Position<E> p) {
    Node<E> node = validate(p);
    return node.getLeft();
  }

/*
  * Returns the Position of p's right child (or null if no child exists).
  *
  * @param p A valid Position within the tree
  * @return the Position of the right child (or null if no child exists)
*/

  @Override
  public Position<E> right(Position<E> p) {
    Node<E> node = validate(p);
    return node.getRight();
  }

  // update methods supported by this class
/*
  * Places element e at the root of an empty tree and returns its new Position.
  *
  * @param e   the new element
  * @return the Position of the new element
  * @throws IllegalStateException if the tree is not empty
*/

  public Position<E> addRoot(E e) throws IllegalStateException {
    if(!isEmpty()) throw new IllegalStateException("Tree is not empty! Cannot add a root.");
    size = 1;
    return root = createNode(e,null, null, null);
  }

  public void insert(E e) {
    //recursively add from root
    root = addRecursive(root, e);
    ++size;
  }

  //recursively add Nodes to binary tree in proper position
  private Node<E> addRecursive(Node<E> p, E e) {
    if(isEmpty()) {
      root= createNode(e, null, null, null);
      return root;
    }

    if(e.compareTo(p.element) < 0) {
      if(p.left!=null) {
        addRecursive(p.left, e);
      } else {
        p.left = createNode(e, p, null, null);
      }
    }else {
      if (p.right != null) {
        addRecursive(p.right, e);
      } else {
        p.right = createNode(e, p, null, null);
      }
    }

    return p;
  }

/*
  * Creates a new left child of Position p storing element e and returns its Position.
  *
  * @param p   the Position to the left of which the new element is inserted
  * @param e   the new element
  * @return the Position of the new element
  * @throws IllegalArgumentException if p is not a valid Position for this tree
  * @throws IllegalArgumentException if p already has a left child
*/

  public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if(parent.getLeft() != null) throw new IllegalArgumentException("p already has a left child!");
    Node<E> child = createNode(e, parent, null, null);
    parent.setLeft(child);
    size ++;
    return child;
  }

/*
  * Creates a new right child of Position p storing element e and returns its Position.
  *
  * @param p   the Position to the right of which the new element is inserted
  * @param e   the new element
  * @return the Position of the new element
  * @throws IllegalArgumentException if p is not a valid Position for this tree.
  * @throws IllegalArgumentException if p already has a right child
*/

  public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if(parent.getRight() != null) throw new IllegalArgumentException("p already has a right child!");
    Node<E> child = createNode(e, parent, null, null);
    parent.setRight(child);
    size ++;
    return child;
  }

/*
  * Replaces the element at Position p with element e and returns the replaced element.
  *
  * @param p   the relevant Position
  * @param e   the new element
  * @return the replaced element
  * @throws IllegalArgumentException if p is not a valid Position for this tree.
*/

  public E set(Position<E> p, E e) throws IllegalArgumentException {
    E returnElement = p.getElement();
    Node<E> node = validate(p);

    node.setElement(e);

    return returnElement;
  }

/*
  * Attaches trees t1 and t2, respectively, as the left and right subtree of the
  * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
  *
  * @param p   a leaf of the tree
  * @param t1  an independent tree whose structure becomes the left child of p
  * @param t2  an independent tree whose structure becomes the right child of p
  * @throws IllegalArgumentException if p is not a valid Position for this tree
  * @throws IllegalArgumentException if p is not a leaf
*/

  public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    Node<E> node = validate(p);

    if (isInternal(p)) {
      throw new IllegalArgumentException("This function requires a leaf as input.");
    }

    size += t1.size + t2.size;

    if (!t1.isEmpty()) {
      t1.root.setParent(node);
      node.setLeft(t1.root);
      t1.root = null;
      t1.size = 0;
    }
    if (!t2.isEmpty()) {
      t2.root.setParent(node);
      node.setRight(t2.root);
      t2.root = null;
      t2.size = 0;
    }
  }

/*
  * Removes the node at Position p and replaces it with its child, if any.
  *
  * @param p   the relevant Position
  * @return element that was removed
  * @throws IllegalArgumentException if p is not a valid Position for this tree.
  * @throws IllegalArgumentException if p has two children.
*/

  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node;
    try {
      node = validate(p);
    }
    catch(IllegalArgumentException e) {
      throw new IllegalArgumentException("Not valid position type");
    }

    E temp = node.getElement();
    Node<E> child;

    if(node.getLeft()!=null && node.getRight()!=null) {
      throw new IllegalArgumentException("Cannot remove a node with two children!");
    }
    else if(node.getLeft()!=null) {
      child = node.getLeft();
    } else if(node.getRight()!=null) {
      child = node.getRight();
    } else {
      child = null;
    }

    if(child!=null) {
      child.setParent(node.getParent());
    }

    if(node==root) {
      root = child;
    } else {
      Node<E> parent = node.getParent();
      if(node==parent.getLeft()) {
        parent.setLeft(child);
      } else {
        parent.setRight(child);
      }
    }

    size--;
    return temp;
  }

  public void createLevelOrder(E[] array) {
    root = createLevelOrderRecursiveHelper(array, root, 0);
  }

  private Node<E> createLevelOrderRecursiveHelper(E[] array, Node<E> node, int level) {

    if(level <array.length) {
      Node<E> result = createNode(array[level], node, null, null);
      result.left = createLevelOrderRecursiveHelper(array, result.left, 2*level +1);
      result.right = createLevelOrderRecursiveHelper(array, result.right, 2*level +2);

      ++size;
      return result;
    }
    return node;
  }

  public String toString() {
    return inorder().toString();
  }

  public static void main(String [] args) {
    LinkedBinaryTree<Integer> bt1 = new LinkedBinaryTree<Integer>();

    Integer [] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
    bt1.createLevelOrder(arr);
    //System.out.println(bt.toString());
    System.out.println(bt1.toString());

    LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

    Position<Integer> root = bt.addRoot(12);
    Position<Integer> l1 = bt.addLeft(root, 25);
    Position<Integer> r1 = bt.addRight(root, 31);

    Position<Integer> l1l = bt.addLeft(l1, 58);
    bt.addRight(l1, 36);
    bt.addLeft(l1l, 62);
    bt.addRight(l1l, 75);

    bt.addLeft(r1, 42);
    bt.addRight(r1, 90);

    System.out.println("bt: (inorder) " + bt.inorder() );
    System.out.println("bt: (preorder) " + bt.preorder() );
  }
}