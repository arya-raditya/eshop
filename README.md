Name    : Arya Raditya Kusuma

NPM     : 2306215816

Class    : B

Deployment link: https://amazing-angelle-arya-raditya-4fabc1bd.koyeb.app/

# Weekly Assignment 3
## Reflection

> 1) Explain what principles you apply to your project!

1. **Single Responsibility Principle (SRP)**: I apply the SRP by ensuring each part of my application has a focused responsibility. Models are for data, repositories for data access, services for business logic, and controllers for request handling. This separation ensures that changes in one area are less likely to impact others.

2. **Open/Closed Principle (OCP)**: My project embraces the OCP by utilizing interfaces for services, repositories, and controllers, and abstract classes for models. This design allows me to extend functionalities by creating new implementations or subtypes without modifying the existing, stable code components.

3. **Liskov Substitution Principle (LSP)**: I adhere to LSP by designing Product and Car as subtypes of Item in such a way that they can be seamlessly substituted for Item without causing unexpected behavior. This ensures that code designed to work with Item will also function correctly with Product and Car.

4. **Interface Segregation Principle (ISP)**: I implement ISP by defining specific interfaces like CarService and ProductService, each tailored to the operations relevant to cars and products respectively. This prevents interfaces from becoming bloated and ensures that classes only need to implement the methods they actually use, promoting cleaner and more focused code.

5. **Dependency Inversion Principle (DIP)**: DIP is central to my architecture, where controllers depend on service interfaces (ProductService, CarService) and not concrete implementations. This decoupling allows for easy swapping of service implementations, enhancing flexibility, testability, and maintainability by reducing dependencies on specific, concrete classes.

> 2) Explain the advantages of applying SOLID principles to your project with examples.

1. Applying SOLID principles significantly increases the maintainability of my project. By separating concerns and reducing dependencies, changes become localized and less likely to introduce unintended side effects, making it easier to update and adapt the application over time. For example, if I need to modify the logic for calculating product discounts, I know I only need to change the ProductService class. Because the controller and repository are separate and only interact through interfaces, I can make this change without fear of breaking the request handling in the ProductController or the data access in the ProductRepository.

2. Testability is greatly enhanced through SOLID principles. Decoupled components with single responsibilities are much simpler to test in isolation, allowing for more effective unit testing and a higher confidence in the correctness of individual modules and the system as a whole. For instance, to test the ProductService, I can easily mock the ProductRepository interface. This allows me to focus solely on testing the business logic within ProductService without needing to set up a real database or worry about the specifics of data storage. I can create controlled scenarios by defining the mock repository's behavior and verify that the ProductService reacts as expected.

3. SOLID principles lead to improved readability and understanding of the codebase. A clear separation of concerns and well-defined component responsibilities result in a more organized and intuitive structure, making it easier for developers to navigate, understand, and contribute to the project. Imagine a new developer joining the team. Because of SRP, they can quickly understand the role of each component. They can look at ProductController and immediately grasp that it's responsible for handling product-related web requests. They can then delve into ProductService to understand the product's business logic, and ProductRepository to see how products are stored and retrieved. This clear structure significantly reduces the learning curve and makes collaboration much smoother.

> 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

1. Without SOLID principles, my project would suffer from decreased maintainability. Code would become tightly coupled and changes in one area could have unpredictable and cascading effects in other parts of the system, making modifications risky and time-consuming.

2. Testability would be significantly reduced if SOLID principles were ignored. Tightly coupled and complex components would be difficult to test in isolation, requiring more complex and less effective integration tests and making it harder to ensure the quality and reliability of the application.

3. Neglecting SOLID principles would result in a codebase that is harder to read and understand. Lack of clear separation of concerns and poorly defined responsibilities would lead to a more convoluted and less intuitive structure, increasing the cognitive load for developers and hindering effective collaboration and development speed.


# Weekly Assignment 2
## Reflection
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them

1. Replacing the for loop with a foreach loop:

The original code utilized a traditional for loop to iterate through the productData list. This approach, while functional, was considered unnecessarily verbose and carried a slightly elevated risk of errors, especially when compared to the more streamlined foreach loop. The primary concern stemmed from the fact that the loop index i was solely employed for accessing elements within the productData list using productData.get(i). This pattern is a strong indicator that a foreach loop would be a more appropriate choice, enhancing both readability and maintainability.

To address this, I replaced the index-based for loop with a foreach loop, specifically for (Product product : productData). This construct directly iterates over the Product objects within the list, eliminating the need for an explicit index. Consequently, the index variable i was completely removed from the code, resulting in a simpler and more focused implementation. The benefits of this change include improved code readability, increased conciseness, and a reduced probability of introducing index-related errors, ultimately contributing to a more robust and maintainable codebase.

2. Changing the import statements (addressed conceptually):

The use of wildcard imports, such as import org.springframework.web.bind.annotation.*;, is generally discouraged in favor of more explicit import declarations. Wildcard imports introduce several potential drawbacks, including namespace pollution, reduced code clarity, and increased risks of compile-time conflicts. By importing all classes from a package, even those not actively used, wildcard imports can obscure the code's actual dependencies and make it harder to understand which classes are essential. Moreover, they can lead to naming collisions if different packages happen to contain classes with the same name.

To mitigate these issues, the best practice is to replace wildcard imports with explicit imports for only the classes that are genuinely used within the code. For instance, instead of import org.springframework.web.bind.annotation.*;, the code would utilize individual import statements such as import org.springframework.web.bind.annotation.RequestMapping;, import org.springframework.web.bind.annotation.GetMapping;, and so on, for each specific annotation class used. This approach offers several advantages, including improved code clarity by making dependencies immediately apparent, reduced risk of naming conflicts due to explicitly defined imports, and enhanced maintainability as unused dependencies are easily identified and removed. By adopting explicit import declarations, the code becomes more self-documenting and less susceptible to unexpected issues related to namespace management.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Looking at the current CI/CD setup, I believe it largely fulfills the definition of Continuous Integration and Continuous Deployment, though reflection reveals opportunities for refinement. For example, the automated testing triggered by code pushes ensures changes are integrated and validated regularly, a core CI principle. The use of tools like Scoreboard, PMD, and Dependabot on each push contributes to ongoing code quality assessment. The automated deployment to Koyeb after successful CI completion demonstrates a functional CD pipeline, reducing manual steps and accelerating feature delivery. However, while I've established a basic CI/CD workflow, I recognize the need to continuously evaluate its efficiency and effectiveness. Perhaps the trigger conditions for certain checks could be optimized to reduce build times. Regarding the deployment process, while it's automated, I need to explore more sophisticated deployment strategies, like blue-green deployments, to minimize downtime during updates. This would further solidify the reliability and robustness of the entire CI/CD pipeline.


# Weekly Assignment 1
## Reflection 1: Coding Standards and Secure Coding
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code

Throughout this assignment, I've consciously tried to implement several clean code principles and secure coding practices, although reviewing the code now reveals areas for improvement. For instance, I made sure to use descriptive variable and method names (e.g., productRepository, findById) to improve readability and maintainability. I also attempted to keep methods relatively short and focused on a single responsibility. However, I notice now that some of the if statement blocks within the repository methods, particularly in update and delete, could be extracted into separate helper functions to enhance code clarity. Regarding security, while this assignment didn't directly involve handling sensitive user data, I've incorporated input validation best practices in other Spring Boot projects, and recognize that we need to extend it here to anticipate all edge cases of product name and quantity, preventing potential exceptions later on. This would prevent common vulnerabilities and improve the overall robustness of the application. 

## Reflection 2: Unit Test and Functional Test
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

After writing the unit tests, I feel more confident in the reliability of the ProductRepository class. It's comforting to know that the core functionalities are behaving as expected. Ideally, the number of unit tests in a class should be sufficient to cover all possible execution paths and edge cases. There's no magic number; it depends on the complexity of the class. While I've tried to test the common scenarios, I recognize that more tests could be added to cover cases like invalid itemId values or potential exceptions. Even with 100% code coverage, bugs and errors can still exist! Code coverage only measures which lines of code have been executed, not whether the tests are actually verifying the correctness of the logic within those lines. For example, a test could execute a line of code without properly asserting the expected outcome, thus missing a bug.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

Creating a new Java class that duplicates setup procedures and instance variables from CreateProductFunctionalTest.java to verify the number of items in the product list would significantly reduce code quality. The most glaring clean code violation is duplication. Having redundant setup code makes the project harder to maintain; if the setup logic needs to change, you have to update it in multiple places, increasing the risk of errors and inconsistencies. Another issue is reduced readability. The new test class would be longer and more complex than necessary, making it harder to understand the core purpose of each test. A better approach would be to create a base class or abstract class containing the common setup procedures and instance variables. The original CreateProductFunctionalTest.java and the new class for verifying item counts could then inherit from this base class, eliminating duplication and promoting code reuse. This also allows for easier extension; future functional tests could also inherit from the same base, maintaining a clean and organized test suite. We should aim for reducing code duplication because when we have 2 different function that does almost the same, it would be harder to maintain and if the code is inconsistent, the system would be harder to trace in case of an exception/error.