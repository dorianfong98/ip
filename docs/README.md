# User Guide
Welcome to Duke!

Accessed conveniently via your PC's **Command Line Interface (CLI)**, Duke is your very own personal dashboard featuring to-dos, deadlines, events, and more.

Get focused, be productive, and do your best work today with Duke.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing help: `help`](#viewing-help-help)
  * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
  * [Adding an event task: `event`](#adding-an-event-task-event)
  * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
  * [Viewing all tasks: `list`](#viewing-all-tasks-list)
    * [Viewing all todo tasks: `todos`](#viewing-all-todo-tasks-todos)
    * [Viewing all deadlines: `deadlines`](#viewing-all-deadlines-deadlines)
    * [Viewing all events: `events`](#viewing-all-events-events)
  * [Marking a task as done: `done`](#marking-a-task-as-done-done)
  * [Looking for a task: `find`](#looking-for-a-task-find)
  * [Looking for all tasks on a date: `schedule`](#looking-for-all-tasks-on-a-date-schedule)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Clearing all tasks: `clear`](#clearing-all-tasks-clear)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
  * [Saving your data](#saving-your-data)
* [FAQs](#faqs)
* [Command Summary](#command-summary)

---
  
## Quick Start
1. Ensure that you have installed Java `11`.
2. Download the latest `ip.jar` [here](https://github.com/dorianfong98/ip/releases/tag/v1.0).
3. Copy the jar file to any location/folder on your computer.\
(Note that when you first run `ip.jar`, a file to save your data, `Duke.txt`, will be created in the same directory
as `ip.jar`)
4. Open a new *terminal* window and navigate to the same directory where your `ip.jar` is located.
5. Enter the following command into the *terminal* window to launch the application:
    ````
    java -jar ip.jar
    ````
    You will be greeted with a welcome screen from Duke on successful launch.

    ![Start up screen image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/startscreen.png)
    *(Note that a new save file is created if no previous save file exists in the same directory as `ip.jar`)*
    
6. Type a command into the *terminal* window and press Enter to execute it. e.g. typing `help` and pressing Enter will display a list of available commands

    Some examples of commands that you can try:

    * `help`: Displays a list of all available commands.
    * `todo Print notes`: Adds a todo task with its description, `Print notes`, to your current task list.
    * `event Coffee Date /at Starbucks 2020-11-09`: Adds an event with description `Coffee Date (At: Starbucks Nov 9 2020)` 
    to the task list.
    * `list`: Lists all available tasks.
    * `delete 1`: Deletes 1st task shown in list.
    * `bye`: Exits the program.
  
7. Refer to the [Features](#features) section below for further details of each command.

---

## Features 
**Notes about the command format:**
* **Words in `UPPER_CASE` are the parameters to be supplied by the user.**\
e.g in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo Prepare for test`.
* **Items bounded by square brackets are optional.**\
e.g. `/by ADDITIONAL_INFORMATION [DATE]` can be used as `/by next Mon 2020-12-25` or `/by next Mon`.
* **Dates can be inputted in any order.**\
But ensure that you use the **required `YYYY-MM-DD` format** when inputting dates, and that they are placed after `/by` or `/at` tags.

---
### Viewing help: `help`
Displays all available commands on the terminal.\
Format: `help`

![Help command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/help.png)

---

### Adding a todo task: `todo`
Adds a todo task to the task list without any additional information.\
Format: `todo TASK_DESCRIPTION`

**Examples:**
* `todo Email Thomas` Adds a todo task `Email Thomas` to the task list.
* `todo Attempt CG2027 Assignment` Adds another todo task `Attempt CG2027 Assignment` to the task list.

---

### Adding an event task: `event`
Adds an event task to the task list with additional timing information.\
Format: `event TASK_DESCRIPTION /at ADDITIONAL_INFORMATION [DATE]`

* Both the `TASK_DESCRIPTION` and `ADDITIONAL_INFORMATION` *(or optional `DATE`)* fields must be present.
* If `DATE` is provided, it must be in the format of `YYYY-MM-DD` for the field to be detected as a date. Else,
it will be saved as part of the description.

**Examples:**
* `event CS2113T Team meeting /at 8pm tonight` 

Adds event `CS2113T Team meeting (At: 8pm tognight)` to the task list.

* `event ACC1701X tutorial  /at 10.30-11.30am, 2020-11-05` 

Adds another event `ACC1701X tutorial (At: 10.30-11.30am, Nov 5 2020)` to the task list.

![Event command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/event.png)

---

### Adding a deadline task: `deadline`
Adds a deadline task to the task list with additional timing information.\
Format: `deadline TASK_DESCRIPTION /by ADDITIONAL_INFORMATION [DATE]`

* Similar to [event](#adding-an-event-task-event).
* Both the `TASK_DESCRIPTION` and `ADDITIONAL_INFORMATION` *(or optional `DATE`)* fields must be present.
* If `DATE` is provided, it must be in the format of `YYYY-MM-DD` for the field to be detected as a date. Else,
it will be saved as part of the description.

**Examples:**
* `deadline Complete assignment /by This weekend` 

Adds deadline `Complete assignment (By: This weekend)` to the task list.

* `deadline Pay NUS school fees /by 2020-10-31` 

Adds deadline `Pay NUS school fees (By: Oct 31 2020)`.

![Deadline command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/deadline.png)

---

### Viewing all tasks: `list`
Displays all available tasks on the task list.\
Format: `list`
#### Viewing all Todo tasks: `todos`
Displays all available todo tasks on the task list.\
Format: `todos`
#### Viewing all Deadlines: `deadlines`
Displays all deadlines on the task list.\
Format: `deadlines`
#### Viewing all Events: `events`
Displays all available events on the task list.\
Format: `events`

![list command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/list.png)

---

### Marking a task as done: `done`
Marks a specified task as done.\
Format: `done INDEX`

* Sets the task as done at the specified `INDEX`.
* The index refers to the index number shown in the displayed task [list](#viewing-all-tasks-list).
* The index **must be a positive integer** e.g 1, 2, 3, ...

**Example:**
* `done 3` marks the third task on the `list` as done. Good job! :)

![Done command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/done.png)

---

### Looking for a task: `find`
Finds all tasks in the task list that contain the given keywords.\
Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is **case-sensitive**: `books` will **not** match `Books`
* The search will **follow the order of the search terms**: `email prof` will **not** match `prof email`
* Only tasks containing **all keywords** in the **correct order** will be returned.
* Only **full words** will be matched: `Song` will not match `Songs`
* Only the description is searched.

**Examples:**
* `find read notes` 

returns `read notes (By: Tomorrow morning)` and `read notes for ACC1701X`

![Find command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/find.png)

---

### Looking for all tasks on a date: `schedule`
Finds all tasks in the task list that fall on a particular date.\
Format: `schedule DATE`

* The given date must be in the format of `YYYY-MM-DD`.
* Only tasks created with a valid date will be returned: Tasks with the dates found only in their description
will not be returned.
* Only the dates saved and linked to the task will be searched.

**Examples:**
* `schedule 2020-11-01` 

returns `Bro's ORD date (At: Nov 1 2020)` and `Prepare for presentation (By: Nov 1 2020)`

![Schedule command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/schedule.png)

---

### Deleting a task: `delete`
Deletes the specified task from the task list.\
Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task [list](#viewing-all-tasks-list).
* The index **must be a positive integer** e.g 1, 2, 3, ...

**Examples:**
* `delete 1` 

deletes the first task from `list`
![Delete1 command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/delete1.png)

* `delete 3` 

deletes the third task from `list`
![Delete3 command image](https://raw.githubusercontent.com/dorianfong98/ip/master/docs/images/delete3.png)

---

### Clearing all tasks: `clear`
Clears all entries from the task list.\
Format: `clear`

---

### Exiting the program: `bye`
Exits Duke.\
Format: `bye`

---

### Saving your data
All data entered into Duke is saved onto the hard disk automatically after any command that changes the data. Hence, there is
no need to manually save the data. When no save file is detected on startup, the program will automatically create 
a new save file.

---

## FAQs
**Q:** Why does the `schedule` command not work? Why doesn't it return the task that falls on the date I'm searching for?

**A:** Check that for the task in question, you had inputted the date in the required format of `YYYY-MM-DD`, after 
the `/at` identifier for `event` command and `/by` for `deadline` command. If successful, the input date in the 
`YYYY-MM-DD` format will be automatically reformatted into a `MMM-DD-YYYY` format.\
*e.g `2020-10-09` will be reformatted into `Oct 09 2020` in the task description.*

\
**Q:** Why am I receiving an ArrayOutOfBounds Error when Duke ran fine the last time I ran it?

**A:** This is most likely due to an improper exit of the program the previous session (not using the `bye` command). 
Try deleting the existing Duke.txt file in the directory of your `ip.jar` or overwriting the existing Duke.txt file with a new blank Duke.txt file and executing `java -jar ip.jar` in your terminal again. 

If you require any further technical assistance with Duke, or have any queries regarding Duke, feel free to contact the developer at [dorianfong@u.nus.edu](mailto:dorianfong@u.nus.edu). 

---

## Command Summary

Command | Formatting, Examples
--------|-----------------
**Help**|`help`
**Todo**|`todo TASK_DESCRIPTION` e.g.,`todo Read lecture notes`
**Event**|`event TASK_DESCRIPTION /at ADDITIONAL_INFORMATION [DATE]` e.g.,`event Team meeting /at 8pm tonight`
**Deadline**|`deadline TASK_DESCRIPTION /by ADDITIONAL_INFORMATION [DATE]` e.g.,`deadline Pay school fees /by 2020-10-31`
**List**|`list`
**Done**|`done INDEX` e.g., `done 2`
**Find**|`find KEYWORD [MORE_KEYWORDS]` e.g.,`find notes`
**Schedule**|`schedule DATE` e.g.,`schedule 2020-12-04`
**Delete**|`delete INDEX` e.g.,`delete 2`
**Clear**|`clear`
**Bye**|`bye`