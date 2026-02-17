Starting repository for `Data Structures` COMP20280 2025-2026

### Week 1 (linked lists) 
Q6:
What is the difference between a singly linked list and a circularly linked list? \
Singly linked list tail (last element) points to null \
Circularly linked list the tail points to the head (first element)

Q7: In what situations would you prefer to use a linked list to an array?\
Linked List would be used in a situation when you need to store elements in order

Q8: Describe 2 possible use-cases for a circularly linked list (2-3 sentences for each). \
Implementation of Queue: circularly linked list can be used to implement a circular queue\
Task scheduling: allows tasks to receive equal time slices, 
also allows for tasks to be seamlessly added

### Week 2(queues and stacks) 

Q2: Write the pseudocode for an algorithm which implements a Queue using two stacks.
Provide implementations for the enqueue() and dequeue() methods: 

have one stack at the front and one at the back facing opposite directions\
enqueue() //add element to the end \
to stack facing away from front add an element 

dequeue() //remove element from start \
to stack starting from front pop an element \
move the stack source one spot forward

Q3: Reverse the elements on a Stack using two additional Stacks \
pop the elements off of the initial stack onto stack2 \
pop the elements off of stack2 onto the stack3 \
Finally pop the elements off of stack3 and back onto the initial stack
 
Q4: How would you extend this to handle different bases? and bases greater than 9?\
divide by the given base and store remainder in stack \
at the end pop off each element (they are now in the correct order) \
For bases greater than 9, use characters, if remainder is larger than 9

### Week 3(trees)
Q2: Write pseudocode to count the number of external nodes in a binary tree.
extNodeCount(Node<E\> root){ \
if external \
return 1 \
else \
return extNodeCount(leftChild) + extNodeCount(rightChild) 

Q3: Describe an algorithm which counts only the left external
nodes in a binary tree. \
only add 1 if both the leaf is external and left(parent(thisNode)) == thisNode returns true

Q4: