# SIGMA User Guide

Sigma is a **command-line task management chatbot** that helps you keep track of your to-dos, deadlines, and events. It saves your tasks automatically so they persist between sessions.

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest `.jar` file.
3. Open a terminal and run: `java -jar sigma.jar`
4. Type commands and press Enter to interact with Sigma.

## Features

> **Notes about command format:**
> - Words in `UPPER_CASE` are parameters to be supplied by the user.
> - Commands are case-sensitive and should be typed in lowercase.

---

### Adding a to-do: `todo`

Adds a to-do task (no date/time).

Format: `todo DESCRIPTION`

Example: `todo read book`

```
____________________________________________________________
Got it. I've added this task:
 [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```

---

### Adding a deadline: `deadline`

Adds a task with a deadline.

Format: `deadline DESCRIPTION /by DEADLINE`

Example: `deadline return book /by Sunday 2359`

```
____________________________________________________________
Got it. I've added this task:
 [D][ ] return book (by: Sunday 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Adding an event: `event`

Adds a task that spans a time period.

Format: `event DESCRIPTION /from START /to END`

Example: `event project meeting /from Mon 2pm /to 4pm`

```
____________________________________________________________
Got it. I've added this task:
 [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

---

### Listing all tasks: `list`

Shows all tasks in your list.

Format: `list`

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sunday 2359)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```

---

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark INDEX`

Example: `mark 1`

```
____________________________________________________________
Nice! I've marked this task as done:
 [T][X] read book
____________________________________________________________
```

---

### Unmarking a task: `unmark`

Marks the specified task as not done.

Format: `unmark INDEX`

Example: `unmark 1`

```
____________________________________________________________
OK, I've marked this task as not done yet:
 [T][ ] read book
____________________________________________________________
```

---

### Deleting a task: `delete`

Removes the specified task from the list.

Format: `delete INDEX`

Example: `delete 2`

```
____________________________________________________________
Noted. I've removed this task:
 [D][ ] return book (by: Sunday 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Finding tasks by keyword: `find`

Finds tasks whose descriptions contain the given keyword. The search is case-insensitive.

Format: `find KEYWORD`

Example: `find book`

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] read book
____________________________________________________________
```

---

### Exiting the program: `bye`

Exits Sigma.

Format: `bye`

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

---

### Saving the data

Task data is saved automatically to `data/sigma.txt` after every command that modifies the task list. There is no need to save manually.

## Command Summary

| Action       | Format                                       |
|--------------|----------------------------------------------|
| **Todo**     | `todo DESCRIPTION`                           |
| **Deadline** | `deadline DESCRIPTION /by DEADLINE`          |
| **Event**    | `event DESCRIPTION /from START /to END`      |
| **List**     | `list`                                       |
| **Mark**     | `mark INDEX`                                 |
| **Unmark**   | `unmark INDEX`                               |
| **Delete**   | `delete INDEX`                               |
| **Find**     | `find KEYWORD`                               |
| **Exit**     | `bye`                                        |