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

Q4: \
Preorder: \
   E \
  X F \
A M U N \
Postorder: \
   N \
  A U \
E X M F \
Inorder: \
   M \
  X F \
E A U N

Q5: Write the pseudocode for an algorithm which counts the total number of descendants of
a node \
public int countDesc(Node<E\> node){ \
    if(node != null) return 1 + countDesc(node.getLeft()) + countDesc(node.getRight()); \
    else return 0; \
}

### Week 4 (trees II)
Question 5: \
go through all the interal nodes, finding the height of the children, \
adding them and seeing which 2 heights are the largest until you have  \
gone through all the internal nodes

### Week 5 (recursion)
Question 4: What kind of recursive function is this? \
nested recursion (function is called within a function call) 

Question 5: What is the output of Foo(2468)? \
100110100100

Question 6: psuedocode for a recursive function which prints the elements in reverse? \
travel along every node and then print out each value (after the visit (the recursive call))

Question 7: pseudocode for a recursive function which copies the elements in reverse? \
return if the value of the node is null \
create new node \
set the next node to be the recursive call \
return the new node

Question 8: Draw the recursive trace for mystery(2,4,4) \
mystery(2,4,4) calls mystery(1,4,4) \
mystery(1,4,4) returns 4 \
mystery(2,4,4) returns 4+4 (8) \

### Week 6 (Priority queues and Heaps)
Question 1: Illustrate the execution of the heap.insert() method \
2 \
\
2 \
5 \
\
2 \
5 16\
\
2 \
4 5\
16 \
\
2 \
4 5\
10 16\
\
2 \
4 5\
10 16 23\
\
2 \
4 5\
10 16 23 39\
\
2 \
4 5\
10 16 23 39\
18 \
\
2 \
4 5\
10 16 23 39\
18 26 \
\
       2 \
     4      5\
  10   15 23 39\
18 26 16\
\
Question 2: preorder traversal \
2, 4, 10, 18, 26, 15, 16, 5, 23, 39 \
Question 3: postorder traversal \
18, 26, 10, 16, 15, 4, 23, 39, 5, 2 \
Question 4: construction of valid heap \
both yes due to all numbers being given (heapify)

### Week 7 (HashTables)

Question 3: 11 entry hash table 

| _0_ | _1_ | _2_ | _3_ | _4_ | _5_ | _6_ | _7_ | _8_ | _9_ | _10_ |
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|------|
| 13  | 94  |     |     |     | 44  |     |     | 12  | 16  | 20   |
|     | 39  |     |     |     | 88  |     |     | 23  | 5   |
|     |     |     |     |     | 11  |

Question 4: MAD hash function 19-entry hash table

| _0_ | _1_ | _2_ | _3_ | _4_ | _5_ | _6_ | _7_ | _8_ | _9_ | _10_ | _11_ | _12_ | _13_ | _14_ | _15_ | _16_ | _17_ | _18_ |
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|------|------|------|------|------|------|------|------|------|
| 23  |     | 13  |     | 11  | 88  |     |     | 44  | 5   |      | 94   |      |      |      | 16   |      |      | 12   |
|     |     | 39  |     |     | 20  |     |     |     |     |      |      |      |      |      |      |      |      |      |

(code used)
```C
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    srand(time(NULL));
    int p = 127;
    int N = 19;
    int a, b, num;
    
    while(scanf("%d", &num) != EOF){
        a = rand() % p;
        b = rand() % p;
        printf("%d\n", abs((a*num+b)%p)%N);
    }
    return 0;
}
```

