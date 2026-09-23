# Part E - Pseudocode and Algorithm Representation

The pseudocode below follows the submitted Java implementation.

## Queue

```text
ALGORITHM enqueue(student)
    IF size = capacity THEN REPORT "queue full"
    items[rear] <- student
    rear <- (rear + 1) MOD capacity
    size <- size + 1
END ALGORITHM

ALGORITHM dequeue()
    IF size = 0 THEN REPORT "queue empty"
    student <- items[front]
    items[front] <- null
    front <- (front + 1) MOD capacity
    size <- size - 1
    RETURN student
END ALGORITHM
```

## Stack

```text
ALGORITHM push(value)
    IF top = capacity - 1 THEN REPORT "stack overflow"
    top <- top + 1
    items[top] <- value
END ALGORITHM

ALGORITHM pop()
    IF top < 0 THEN REPORT "stack underflow"
    value <- items[top]
    top <- top - 1
    RETURN value
END ALGORITHM

ALGORITHM evaluatePostfix(expression)
    CREATE empty stack
    FOR EACH token IN expression
        IF token is a number THEN
            push(token)
        ELSE
            right <- pop()
            left <- pop()
            result <- left token right
            push(result)
        END IF
    END FOR
    IF stack size is not 1 THEN REPORT "malformed expression"
    RETURN peek()
END ALGORITHM
```

## Singly linked list

```text
ALGORITHM insertNode(student, position)
    CREATE newNode containing student
    IF position = 1 THEN
        newNode.next <- head
        head <- newNode
        RETURN
    END IF
    previous <- head
    REPEAT position - 2 times
        previous <- previous.next
    END REPEAT
    newNode.next <- previous.next
    previous.next <- newNode
END ALGORITHM

ALGORITHM deleteNode(studentNumber)
    IF head = null THEN RETURN false
    IF head.studentNumber = studentNumber THEN
        head <- head.next
        RETURN true
    END IF
    current <- head
    WHILE current.next is not null
        IF current.next.studentNumber = studentNumber THEN
            current.next <- current.next.next
            RETURN true
        END IF
        current <- current.next
    END WHILE
    RETURN false
END ALGORITHM

ALGORITHM searchNode(studentNumber)
    current <- head
    WHILE current is not null
        IF current.studentNumber = studentNumber THEN RETURN current.data
        current <- current.next
    END WHILE
    RETURN null
END ALGORITHM

ALGORITHM traverseList()
    current <- head
    WHILE current is not null
        DISPLAY current.data
        current <- current.next
    END WHILE
END ALGORITHM
```

## Sorting

```text
ALGORITHM selectionSort(values)
    FOR pass <- 0 TO length(values) - 2
        minimumIndex <- pass
        FOR index <- pass + 1 TO length(values) - 1
            comparisons <- comparisons + 1
            IF values[index] < values[minimumIndex] THEN minimumIndex <- index
        END FOR
        IF minimumIndex != pass THEN SWAP values[pass], values[minimumIndex]
    END FOR
END ALGORITHM

ALGORITHM insertionSort(values)
    FOR pass <- 1 TO length(values) - 1
        key <- values[pass]
        index <- pass - 1
        WHILE index >= 0
            comparisons <- comparisons + 1
            IF values[index] <= key THEN BREAK
            values[index + 1] <- values[index]
            shifts <- shifts + 1
            index <- index - 1
        END WHILE
        values[index + 1] <- key
    END FOR
END ALGORITHM

ALGORITHM mergeSort(values, left, right)
    IF left >= right THEN RETURN             // base case: one value
    middle <- left + (right - left) DIV 2
    mergeSort(values, left, middle)
    mergeSort(values, middle + 1, right)
    MERGE the two sorted ranges, counting each data-value comparison
END ALGORITHM

ALGORITHM quickSort(values, low, high)
    IF low >= high THEN RETURN
    pivot <- values[high]                    // final-element pivot rule
    boundary <- low - 1
    FOR index <- low TO high - 1
        comparisons <- comparisons + 1
        IF values[index] <= pivot THEN
            boundary <- boundary + 1
            SWAP values[boundary], values[index]
        END IF
    END FOR
    SWAP values[boundary + 1], values[high]
    pivotIndex <- boundary + 1
    quickSort(values, low, pivotIndex - 1)
    quickSort(values, pivotIndex + 1, high)
END ALGORITHM
```

