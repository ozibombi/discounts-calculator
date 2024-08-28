# Discount calculator

This is an application written for recruitment purposes. I used Java 22, Spring Boot 3.3.3 and MongoDB as persistence.

The main assumption is that the code I wrote regarding the discount calculation itself is part of a larger service 
that provides a bounded context for pricing in the organization.

I used a hexagonal architecture for this project because I believe 
that the pricing domain is an area that often requires dynamic and straightforward changes in business logic.

I chose MongoDB as the database to avoid being restricted to a single schema for DiscountRule objects. 
This choice allows for the flexibility to add new discount policies in the future, such as assigning discounts based on product categories, 
without the need for complex schema migrations or rigid data models.

Additionally, I designed the system to return the most profitable discount for the consumer. 

# Run and test

To run the application, you only need to have Docker running and start the `DiscountsApplication`. 
Spring Boot will automatically load the `docker-compose.yml` file and use it to set up the necessary container for MongoDB.

All tests are located in the test package. This package contains both unit and integration tests. In a real-world scenario, I would typically create a separate Gradle module for integration tests and set up a dedicated Spring profile for them.

The tests do not cover the entire logic; I focused on the key areas to demonstrate. 
Of course, the application require still more complex tests (units, integrations, components and even archTests as well)

First, add a product using `POST http://localhost:8080/api/products`. 
Then, you can add several discount rules using `POST http://localhost:8080/api/admin/rules`. 
Currently, only the following types are supported: `AMOUNT_BASED_AMOUNT_DISCOUNT` and `AMOUNT_BASED_PERCENTAGE_DISCOUNT`.

You can calculate the discounted price by sending a `GET` request to `http://localhost:8080/api/products/{productId}/prices?amount=1`.


# Additional Assumptions

 - **Separation of DTOs**: It might be beneficial in the future to separate the DTOs used in the database from those used in the endpoints. Currently, they share the same structure, but this could change over time.

 - **Exception Handling**: Exception handling still needs to be comprehensively managed using ControllerAdvice.

 - **Use of Mappers**: It would be better to use mappers instead of "toModel()" methods to have control over mappings in a single place.

 - **Role Management**: I assume that adding roles will be available only for admins, so I foresee implementing a role-based access control mechanism.

Towards the end, I realized that the mechanism requiring the user to specify both `greaterThanEqual` and `lowerThan` might be unintuitive. 
For instance, if they want to set a discount for exactly 5 products, they would need to use the following data: `greaterThanEqual=5` and `lowerThan=6`, which might not be well-received by the admins. 
However, I won't be changing this for now.