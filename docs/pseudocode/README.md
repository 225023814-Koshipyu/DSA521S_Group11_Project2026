ISEMPTY()
    IF queue contains no students
        Return TRUE
    ELSE
        Return FALSE
    END IF
END ISEMPTY

ENQUEUE(student)
    Add student to the rear of the queue
END ENQUEUE


DEQUEUE()
    IF queue is empty
        Display "Queue is empty"
    ELSE
        Remove student from the front of the queue
    END IF
END DEQUEUE


PEEK()
    IF queue is empty
        Display "Queue is empty"
    ELSE
        Return the student at the front
    END IF
END PEEK


DISPLAYQUEUE()
    IF queue is empty
        Display "Queue is empty"
    ELSE
        Display all students from front to rear
    END IF
END DISPLAYQUEUE
