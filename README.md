
# Price Test Application

SpringBoot application that provides a REST endpoint for querying prices of products for a specific date, product ID, and brand ID. The data is stored in an in-memory H2 database and initialized with the sample data provided below:

| BRAND_ID | START_DATE | END_DATE | FEE_ID | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|------------|----------|--------|------------|----------|-------|------|
| 1 | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1 | 35455 | 0 | 35.50 | EUR |
| 1 | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2 | 35455 | 1 | 25.45 | EUR |
| 1 | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3 | 35455 | 1 | 30.50 | EUR |
| 1 | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4 | 35455 | 1 | 38.95 | EUR |

Where fields mean:
 
- BRAND_ID: company foreign key (1 = ZARA).
- START_DATE , END_DATE: range of dates in which the indicated price rate applies.
- FEE_ID: Applicable price list identifier.
- PRODUCT_ID: Product code identifier.
- PRIORITY: Price application disambiguator. If two rates coincide in a range of dates, the one with the highest priority (highest numerical value) is applied.
- PRICE: final sale price.
- CURR: iso of the currency.

## Getting Started

### Prerequisites

- Java 17 or higher (Spring Boot 3.0.6 / Webflux)
- Maven 3.6 or higher
- Docker (optional)
- Git (optional)
- Jenkins (optional)
- Sonar (optional)

### Application Design Decisions

##### Architecture

Hexagonal architecture: also known as ports and adapters, this architecture seeks maximum decoupling between the layers of the application. It consists of dividing the application into three layers: domain, application, and infrastructure. Each layer has its own responsibility and does not depend on the other layers. Dependencies are established through ports (interfaces) and adapters (implementations of those interfaces).

##### Paradigms, Conventions, Best practices and Technologies

- Clean Code: application development try to follow best practices for writing readable, maintainable, and scalable code. It includes principles such as the SOLID principle, the DRY (Don't Repeat Yourself) principle, the KISS (Keep It Simple and Straightforward) principle, and the YAGNI (You Ain't Gonna Need It) principle.

- Naming convention, applying next rules:
	- Use a clear and descriptive name that reflects the purpose of the application.
	- Use the standard package naming convention, e.g. com.idx.pricetest.
	- Use meaningful names for classes, methods, variables, and other elements in the code.
	- Follow the Java naming conventions for classes, e.g. use PascalCase for class names.

- Automated testing: unit tests, integration tests, and acceptance tests ensure code quality and detect errors early. In this case, application test development focuses on integration and acceptance tests pursuing correct implementation of the hexagonal architecture and achieve the highest possible percentage of coverage.

- Contract First and Documentation: using API First with OpenAPI and Spring Boot, development starts designing the API contract before any coding task. The contract is then used to generate code (src/main/java/com/itx/pricetest/infrastructure/in/api) and documentation automatically, saving time, reducing errors and also promotes consistency across APIs. Finally, it makes easier for developers to implement APIs.

- Reactive: Reactive programming is an approach to software development that emphasizes the use of asynchronous, non-blocking streams of data. It can be used to build highly scalable, responsive, and resilient applications that can handle large amounts of traffic and data. In the context of Spring Boot, reactive programming is supported through the Spring WebFlux module, which provides a reactive stack for building web applications using reactive principles. This allows for better resource utilization, lower latency and more efficient handling of I/O operations.

## Application

### Building the Application

To build the application, run the following command in the root directory:


		mvn clean install -DskipTests


This will compile the code and create an executable JAR file in the target directory.

### Running Tests

To run the tests, run the following command in the root directory:


		mvn test


This will execute application tests.

### Code Analysis <sub>1</sub>

To analyze the code, run the following command in the root directory (don't forget to replace **<sonarToken>** in the command):

        mvn sonar:sonar -Dsonar.login=<sonarToken> -Dsonar.coverage.exclusions=**/domain/model/**/*,**/infrastructure/in/api/**/*,**/infrastructure/in/controller/config/App*.*,**/infrastructure/in/controller/config/Err*.*,**/infrastructure/in/controller/config/Un*.*,**/infrastructure/out/persistence/entity/**/*

The analysis data should create/update the project information. Open `http://localhost:9000/dashboard?id=com.itx:pricetest` in your browser to view it.

<sub>1</sub> [*Sonar must be locally installed and provide a valid sonar token credential replacing <sonarToken> with it in the command.*]

### Running the Application

To run the application,  run the following command in the root directory:


		java -jar target/pricetest-1.0.0.jar


Alternatively, you can use Docker<sub>2</sub> to run the application:


		docker build -t pricetest:1.0.0 .
		docker run -p 8080:8080 --name pricetest pricetest:1.0.0

This will build a Docker image and start a container with the application running on port 8080.

<sub>2</sub> [*Docker must be locally installed.*]

### All in One <sub>3</sub>

To execute the Jenkinsfile implemented in the application:

1. Open Jenkins and create a new pipeline job.
2. In the pipeline configuration, select "Pipeline script from SCM" as the definition.
3. Choose Git as the SCM and enter the repository URL where the application source code is uploaded.
4. Specify the branch or tag containing the Jenkinsfile.
5. Specify the path where Jenkinsfile is located (src/main/resources/out/jenkins/Jenkinsfile).
6. Save the configuration and run the pipeline.

When the pipeline will be running, Jenkins will automatically retrieve the Jenkinsfile from the specified Git repository and execute it according to the pipeline configuration. If there are any errors or issues with the Jenkinsfile, Jenkins will provide feedback on how to correct them.

<sub>3</sub> [*Jenkins and Git must be locally installed and configured. At the same time the environment must be provisioned and well-configured too with all the tools specified in the prerequisites section.*]


### Accessing the H2 Console

Currently by default H2 console is not available for reactive libraries. To run a standalone H2 web console execute the command below using the same H2 DB's jar used in the application:

		java -cp h2.jar org.h2.tools.Server -web -webPort <port/i.e: 9092>

The H2 console is now available at `http://localhost:9092/`. Use the following settings to connect:

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:file:/**<projectPath>**/pricetest/src/main/resources/out/db/pricetestdb
- User Name: sa
- Password: (leave blank)

## API

### API Documentation

API documentation is available in the Swagger UI. Open `http://localhost:8080/openapi-doc/openapi-ui.html` in your browser to view it.
Swagger JSON document is available too. Open `http://localhost:8080/openapi-doc/v3/api-docs` in your browser to view it.

### Accessing the API

Once the application is running, you can access the API using any HTTP client. The API has only one endpoint:


GET /prices/search?applyDate={applyDate}&productId={productId}&brandId={brandId}


The `applyDate` parameter should be in ISO format (`yyyy-MM-dd'T'HH:mm:ss.SSSZ`) and represents the date for which to query prices.

The `productId` parameter is a numeric identifier for the product.

The `brandId` parameter is a numeric identifier for the brand.

The response will be a JSON object with the following fields:

- `productId`: Numeric identifier of the product.
- `brandId`: Numeric identifier of the brand.
- `feeId`: Numeric identifier of the price list applied.
- `startDate`: Start date of the price list in Long/Timestamp.
- `endDate`: End date of the price list in Long/Timestamp.
- `price`: Final price for the product.

### Postman Collection

There is not any Postman collection in the source code. Next steps describe how to create a Postman collection to test the application:

1. Save in a file next commands:

		curl -X 'GET' 'http://localhost:8080/prices/search?applyDate=2020-06-14T10%3A00%3A00.000Z&productId=35455&brandId=1' -H 'accept: application/json'
		curl -X 'GET' 'http://localhost:8080/prices/search?applyDate=2020-06-14T16%3A00%3A00.000Z&productId=35455&brandId=1' -H 'accept: application/json'
		curl -X 'GET' 'http://localhost:8080/prices/search?applyDate=2020-06-14T21%3A00%3A00.000Z&productId=35455&brandId=1' -H 'accept: application/json'
		curl -X 'GET' 'http://localhost:8080/prices/search?applyDate=2020-06-15T10%3A00%3A00.000Z&productId=35455&brandId=1' -H 'accept: application/json'
		curl -X 'GET' 'http://localhost:8080/prices/search?applyDate=2020-06-16T21%3A00%3A00.000Z&productId=35455&brandId=1' -H 'accept: application/json'
		
2. Choose the Import menu option from Postman and select the created file.
3. Postman will automatically create a new collection with requests based on the curl commands.
