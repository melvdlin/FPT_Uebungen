# Requirements / Wishes

---

## Functional

Library Management System:

- Inventory Management
  - Inventory contains different types of Media:
    - Books
    - Articles
    - org.somevand.fpt.teaching.libraryproject.entities.Video
    - ...
  - Media is available by different means:
    - physically (book, paper, DVD, ...)
    - digitally (formatted text hosted by publisher, video stream, ...)
  - All Media has
    - a UID
    - a Title
    - a set of Authors
    - (a Thumbnail Image)
  - Physical Media has:
    - a set of Instances
    - an Instance has:
      - a UID
      - a location
      - a checkout status - it is either
        - checked in
        - checked out since a specific point in time
  - Media can be:
    - *Queried*
      - Physical Media has its Instances listed
        - Instance checkout status is indicated in the query result
    - *Added*
    - *Removed*
  - Physical Media Instances can be:
    - *Checked Out* by a Customer
    - *Checked In*
  - A Customer can get a list of all Physical Media Instances currently checked out by them

---

## Structural

Use of:

- Enums
- Lambda Expressions
- `equals()` and `hashCode()`
- Copying
- `try`-with-resources
- Serialisation
- Patterns:
  - Structural
    - Composite
  - Creational
    - Singleton
    - Static Factory
    - Factory Method
    - Abstract Factory
  - Behavioural:
    - Observer / Listener
- Concurrency
  - Threads
  - Synchronization between Threads
  - Deadlocks
  - Async Programming
- Databases
- Network Programming
  - Stream Sockets
- JavaFX
  - MVC

---