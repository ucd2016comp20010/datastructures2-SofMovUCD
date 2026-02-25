package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        Integer [] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer [] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16, 17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};

        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<>();
        bt2.construct(inorder, preorder);
        System.out.println(bt2.root());
        System.out.println(bt2.toBinaryTreeString());
        avHiBinTree();
    }


    /*
    *  Added function for getting a node for a specified element
    */
    public Node<E> getNode(E e){
        for(Position<E> pos : positions()){
            if(e == pos.getElement()){
                return (Node<E>) pos;
            }
        }
        throw new IllegalArgumentException();
    }
    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // TODO
        if(isEmpty()){
            root = new Node<E>(e, null, null, null);
            size++;
            return (Position<E>) root;
        }
        else{
            throw new IllegalStateException();
        }
    }

   //public void insert(E e) {
        // TODO

    //}

    // recursively add Nodes to binary tree in proper position
    //private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        //return null;
    //}

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        validate(p);
        Node<E> leftnode = new Node<E>(e, (Node<E>) p, (Node<E>) left(p), (Node<E>) right(p));
        ((Node<E>) p).setLeft(leftnode);
        ((Node<E>) p).setRight(null);
        return leftnode;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        validate(p);
        Node<E> rightnode = new Node<E>(e, (Node<E>) p, (Node<E>) left(p), (Node<E>) right(p));
        ((Node<E>) p).setRight(rightnode);
        ((Node<E>) p).setLeft(null);
        return rightnode;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        validate(p);
        E value = p.getElement();
        p = new Node<E>(e, (Node<E>) parent(p), (Node<E>) left(p), (Node<E>) right(p));

        return value;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
        validate(p);
        if (left(p) != null || right(p) != null) { //p isn't a leaf
            throw new IllegalArgumentException();
        } else {
            ((Node<E>) p).setLeft(((Node<E>) t1.root()));
            ((Node<E>) p).setRight(((Node<E>) t2.root()));
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        // TODO
        validate(p);
        E removed = p.getElement();
        if(left(p) != null && right(p) != null) throw new IllegalArgumentException();
        if(root() == p){
            root = left(p) == null? (Node<E>)right(p): (Node<E>)left(p);
            return removed;
        }
        ((Node<E>)parent(p)).setLeft(((Node<E>)left(p)));
        ((Node<E>)parent(p)).setRight(((Node<E>)right(p)));
        return removed;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        // TODO
        LinkedBinaryTree<E> myTree = new LinkedBinaryTree<E>();
        myTree.addRoot(l.getFirst());
        int depthTree = (int)Math.ceil(Math.log(l.size())/Math.log(2));
        int startIndex;
        Node<E> parentNode;
        for(int i = 1; i < depthTree; i++){ //each level starting from second
            startIndex = (int) Math.pow(2,i) - 1; //where to start within the arrayList
            for(int j = 0; j < Math.pow(2, i); j += 2){ //each node within the level
                if(startIndex+j+1 >= l.size()){
                    return;
                }
                parentNode = myTree.getNode(l.get((int)Math.pow(2,i-1) - 1 +j/2));
                myTree.addLeft(parentNode, l.get(startIndex+j));
                myTree.addRight(parentNode, l.get(startIndex+j+1));
            }
        }
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        // TODO
        return null;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
        if (i < arr.length) {
            Node<E> n = new Node<E>(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(arr, n.right, 2 * i + 2);
            size++;
            return n;
        }
        return p;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }

    /*
    * Function to calculate diameter
    * @return length of diameter
    * @throws IllegalArgumentException if tree is empty
    * */
    public int diameter(){
        if(isEmpty()) throw new IllegalArgumentException();
        int maxDiam = 0;
        int newDiam;
        for(Position<E> startingPoint : positions()){
            //add 3 because height doesn't count starting nodes or mutual parent node
            if(((Node<E>) startingPoint).left != null && ((Node<E>) startingPoint).right != null){
                newDiam = 3 + height_recursive(((Node<E>)startingPoint).getLeft()) + height_recursive(((Node<E>)startingPoint).getRight());
                maxDiam = Math.max(maxDiam, newDiam);
            }
        }
        return maxDiam;
    }

    public int extNodeCount(Node<E> root){
        if (isExternal(root)) {
            return 1;
        } else {
            return extNodeCount(root.getLeft()) + extNodeCount(root.getRight());
        }
    }

    // construct a binary tree from two given traversals
    public void construct(E[] inorder, E[] preorder){
        if(inorder.length != preorder.length || inorder.length == 0){
            return;
        }
        int[] preIndex = {0};
        root = betterConstruct(inorder, preorder, preIndex, 0, preorder.length - 1);
    }

    private Node<E> betterConstruct(E[] inorder, E[] preorder, int[] preIndex, int leftIndex, int rightIndex){
        if(leftIndex > rightIndex){
            return null;
        }

        E newE = preorder[preIndex[0]];
        Node<E> newRoot = new Node<>(newE, null, null, null);
        preIndex[0]++;
        if (leftIndex == rightIndex) return newRoot;

        int foundI = -1;
        for(int i = leftIndex; i <= rightIndex; i++){
            if(inorder[i] == newE){
                foundI = i;
                break;
            }
        }
        //create a left and right subtree
        newRoot.left = betterConstruct(inorder, preorder, preIndex, leftIndex, foundI -1);
        newRoot.right = betterConstruct(inorder, preorder, preIndex, foundI + 1,  rightIndex);

        //add parents and update size
        if(newRoot.left != null){
            newRoot.left.setParent(newRoot);
            size++;
        }
        if(newRoot.right != null){
            newRoot.right.setParent(newRoot);
            size++;
        }
        return newRoot;
    }

    public ArrayList<ArrayList<E>> rootToLeafPaths(){
        ArrayList<ArrayList<E>> allPaths = new ArrayList<>();
        ArrayList<E> path = new ArrayList<>();
        pathFinderRecursive(root, allPaths, path);
        return allPaths;
    }

    private void pathFinderRecursive(Node<E> r, ArrayList<ArrayList<E>> ap, ArrayList<E> p){
        if(r == null) return; //element does not exist

        p.add(r.getElement()); //add item to list

        if(r.left == null && r.right == null){ //leaf so end of path reached
            ap.add(new ArrayList<>(p));
        } else { //search both subtrees if exist
            pathFinderRecursive(r.left, ap, p); //look through left subtree
            pathFinderRecursive(r.right, ap, p); //look through right subtree
        }

        p.removeLast();
    }
    public static void avHiBinTree(){
        int tHeight = 0;
        for(int i = 50; i <= 5000; i += 50){

            //generate 100 random binary trees
            for(int j = 0; j < 100; j++){
                LinkedBinaryTree<Integer> lbt = LinkedBinaryTree.makeRandom(i);
                //get total height
                tHeight += lbt.height();
            }
            System.out.println("number of nodes: "+i+", average height: "+((double)tHeight/100));
            //get average height
            //print it out
        }
    }
}
