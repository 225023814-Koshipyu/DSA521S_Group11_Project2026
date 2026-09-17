# Task A1 — Waiting Line: Queue

A queue is appropriate because students are assisted in order of arrival: first in, first out (FIFO). Students arrive at the rear; the next student is served from the front.

## Queue diagram

After six arrivals:

```text
FRONT                                                        REAR
  |                                                            |
  v                                                            v
[Amara] -> [Brian] -> [Chipo] -> [David] -> [Elna] -> [Farai]
```

After three students are served:

```text
FRONT                              REAR
  |                                  |
  v                                  v
[David] -> [Elna] -> [Farai]
```

## Pseudocode

```text
ENQUEUE(student)
    create node containing student
    IF queue is empty THEN front = node
    ELSE rear.next = node
    rear = node

DEQUEUE()
    IF queue is empty THEN report error
    student = front.data
    front = front.next
    IF front is null THEN rear = null
    RETURN student

PEEK()
    IF queue is empty THEN report error
    RETURN front.data

IS_EMPTY()
    RETURN front is null

DISPLAY_QUEUE()
    print every node from front to rear
```

Run `QueueDemo` to see six arrivals and three students served.
