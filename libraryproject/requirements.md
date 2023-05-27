# Requirements / Wishes

---

## Functional

Library Management System:

- Inventory Management
  - Inventory contains different types of Media:
    - Books
    - Articles
    - Video
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
        - checked out by a Customer since a specific point in time
  - Media can be:
    - *Queried* by anyone
      - Physical Media has its Instances listed
        - Anyone can see whether an Instance is checked out
    - *Added* by Staff
    - *Removed* by Staff
  - Physical Media can be:
    - *Checked Out* by Customers
    - *Checked In* by Staff
  - Customers can list their checked-out media
  - Staff can query media instances to see if and by whom they are checked out
- Authentication
  - Several User Groups exist:
    - Staff
    - Customers
  - Credentials / user accounts have:
    - a UID (could be an alphanumeric string)
    - a Passkey (byte string of arbitrary length)
  - Credentials can be:
    - Added by Staff
    - Removed by Staff

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