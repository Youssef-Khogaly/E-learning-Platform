# E-Learning REST API

E-learning platform built with Java and the Spring Boot framework. It provides  RESTful API for managing users, courses, course content, video processing, payments, and enrollments.

---

## Features

- **Role-Based Access Control**: Differentiates between Students, Instructors, and Admins using Spring Security and JWT.
- **Course Management**: Instructors can create, update, and manage courses through their entire lifecycle (Draft, Published, Unpublished).
- **Structured Content**: Courses are organized into Sections and Lessons, allowing for a clear and modular learning path.
- **Hybrid Lessons**: Supports both text-based (`TXT`) and `VIDEO` lesson types.
* **Video Integration**: Integrates with api.video for video hosting, streaming. Supports secure upload token generation, allowing clients to upload videos directly to api.video without routing large media files through the application server, significantly reducing server bandwidth usage and resource consumption.

* **Asynchronous Video Processing**: Receives and processes api.video webhooks to track video encoding and processing status. Video metadata and availability are updated asynchronously once transcoding is completed.

* **Payment Processing**: Secure payment-gated course enrollment handled through Stripe Checkout. Stripe webhooks are processed asynchronously to verify successful transactions and update enrollment status.

* **Asynchronous Webhook Architecture**: Uses concurrent processing for external webhooks (Stripe and api.video), ensuring fast acknowledgment of webhook requests while handling business logic in the background for improved reliability and responsiveness.
- **User Enrollment**: Students can enroll in courses, and their enrollment history is tracked.
- **Containerized Deployment**: Comes with a `docker-compose.yml` file for easy setup and deployment of the application and its database.

### Prerequisites

- JDK 17 or later
- Maven
- Docker and Docker Compose

### Configuration

1.  Clone the repository:
    ```sh
    git clone https://github.com/youssef-khogaly/E-learning-Platform.git
    cd E-learning-Platform
    ```

2.  Create the backend environment file at `E-learning/backend.env` with your credentials:
    ```env
    # Database Configuration
    DB_IP=db-sql-service
    DB_PORT=3306
    DB_SchemaName=E_learning
    DB_USER=your_db_user
    DB_PASS=your_db_password

    # Security
    JWT_SECRET=your_super_secret_jwt_key_with_at_least_256_bits_of_entropy

    # External Service API Keys
    StripeApisec=sk_your_stripe_secret_key
    StripeWhsec=whsec_your_stripe_webhook_secret
    api.video.test.apiKey=your_api_video_key
    api.video.enviroment=SANDBOX # or PRODUCTION
    ```

3.  Create the database environment file at `DB-init/db-sql.env`:
    ```env
    MYSQL_ROOT_PASSWORD=your_root_password
    MYSQL_DATABASE=E_learning
    MYSQL_USER=your_db_user
    MYSQL_PASSWORD=your_db_password

    ### Running the Application

1.  Build the application's JAR file using Maven:
    ```sh
    ./mvnw clean package
    ```

2.  Start the application and the MySQL database using Docker Compose:
    ```sh
    docker-compose up --build -d
    ```

6. Stripe webhook
    1. install Striple CLI https://docs.stripe.com/stripe-cli/install
    2.   login to stripe account and forward webhook
    ```bash
        stripe login

        stripe listen --events checkout.session.completed,checkout.session.expired --forward-to localhost:8080/api/webhooks/stripe

    ```
    3. do not forget to set StripeWhsec enviroment variable to the webhook secret

7. use ngrok to forward api.video webhook to localhost:8080/api/webhooks/video-encoded

# Project Structure

The project is organized into logical packages, promoting modularity and separation of concerns.

-   `E-learning/`: The main Spring Boot application.
    -   `src/main/java/com/elearning/`:
        -   `Courses/`: Logic for course creation, state management, and retrieval.
        -   `Users/` & `Security/`: User management, authentication (JWT), and authorization.
        -   `Sections/` & `Lessons/`: Management of course structure.
        -   `LessonContent/`: Handling the actual content of lessons (text or video).
        -   `Videos/`: Integration with the external video service (`api.video`), including upload token generation and asset management.
        -   `payment/`: Payment session creation and integration with Stripe.
        -   `UserEnroll/`: Manages student enrollments in courses.
        -   `webhook/`: Controllers and services to handle incoming webhooks from Stripe and api.video.
-   `DB-init/`: Contains the `schema.sql` for initial database setup.
-   `docker-compose.yml`: Defines the services, networks, and volumes for containerized deployment.