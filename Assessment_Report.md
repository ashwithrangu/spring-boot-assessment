# Spring Boot Library Management Project Assessment

## 1. Entity Relationship Design

For this application, two entities were chosen: **Author** and **Book**.
- **Author**: Represents a book author. It has `id`, `name`, and `email` properties.
- **Book**: Represents a book written by an author. It has `id`, `title`, and `isbn` properties.

**Relationship**:
There is a **One-to-Many** relationship between `Author` and `Book`. One author can have multiple books, but a book is associated with exactly one author.
- In JPA, this is mapped using `@OneToMany(mappedBy = "author")` in the `Author` entity.
- In the `Book` entity, it is mapped using `@ManyToOne` and `@JoinColumn(name = "author_id")`.

## 2. Implementation Details

### a) Populate Database
- The `application.properties` was configured to use an in-memory **H2 database**.
- A `data.sql` file was created in `src/main/resources` to automatically populate 10 authors and 10 books at application startup using standard `INSERT INTO` statements.

### b) Create Operation
- **Form**: Created JSP pages `add-author.jsp` and `add-book.jsp` using Spring's `form:form` tags to bind data securely.
- **Controller**: Implemented `@PostMapping("/addAuthor")` and `@PostMapping("/addBook")`.
- **Integrity Validation**: Added `@Valid` annotation to ensure empty fields are not submitted. Wrapped save calls in `try-catch` blocks to catch `DataIntegrityViolationException` and display user-friendly error messages via `RedirectAttributes`.

### c) Read Operation
- **View**: Implemented `list.jsp` to display two tables: one for Authors and one for Books.
- **Controller**: Implemented `@GetMapping("/")` to fetch all data via `LibraryService` and bind it to the model.
- **Custom Query**: In `BookRepository.java`, added a custom inner join query:
  ```java
  @Query("SELECT b FROM Book b JOIN FETCH b.author")
  List<Book> findAllBooksWithAuthors();
  ```
  This effectively performs an inner join and avoids the N+1 select problem when rendering the book's author name in the view.

### d) Update Operation
- **View**: Created `edit-author.jsp` and `edit-book.jsp` pre-filled with the entity's current data.
- **Controller**: Mapped `@GetMapping("/edit{Entity}/{id}")` to show the form and `@PostMapping("/update{Entity}/{id}")` to handle the update. Existing entities are updated seamlessly through `JpaRepository.save()`.

### e) Testing
- Implemented `LibraryServiceTest` using **JUnit 5** and **Mockito** to test business logic in isolation.
- Implemented `BookRepositoryTest` using `@DataJpaTest` to verify the custom inner join query works correctly against the database.

## 3. Challenges Faced and Solutions

1.  **Lazy Initialization Exception**:
    - *Challenge*: When fetching books and displaying them in the JSP, accessing `book.author.name` caused a `LazyInitializationException` because the session was closed before the view rendered the lazy-loaded author.
    - *Solution*: Utilized a `JOIN FETCH` in the custom query (`@Query("SELECT b FROM Book b JOIN FETCH b.author")`) to eagerly load the associated `Author` during the initial query.

2.  **Form Validation**:
    - *Challenge*: Ensuring data integrity without causing ugly application crashes.
    - *Solution*: Added `spring-boot-starter-validation` dependency. Used `@NotBlank` on entity fields and `@Valid` alongside `BindingResult` in the controller to return the user to the form with specific error messages if validation fails.

## 4. Github Repository URL
*Note: Due to `git` missing on the current environment's PATH, the repository could not be pushed automatically. Please refer to the conversation for instructions on pushing.*
