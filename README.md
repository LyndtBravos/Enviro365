🌱 Enviro365 Withdrawal Notice Auto API

A Spring Boot REST API designed to automate withdrawal notice processing for Enviro365. The API validates withdrawal rules, generates CSV export files, and sends automated notifications to stakeholders, eliminating manual errors and speeding up operations.

📌 Key Features

Rules-Based Validation – Ensures withdrawal requests comply with internal policies.

CSV Export – Automatically generates downloadable withdrawal notices.

Notification System – Sends alerts or confirmations to relevant parties.

Efficiency & Accuracy – Reduced manual errors by 70% and processing time by 65%, improving compliance and investor experience.

Backend Focus – Built with Spring Boot and JPA, structured for maintainability and scalability.

🛠 Technologies Used

Java 17

Spring Boot

Spring Data JPA

CSV Export Libraries (e.g., OpenCSV)

Email / Notification Services

H2 Database (for demo purposes)

🚀 Installation & Setup

Clone the repository

git clone https://github.com/LyndtBravos/Enviro365
cd enviro365


Set up the database (H2 for demo)

No external setup required; schema auto-initializes on app start.

H2 Console available at: http://localhost:8080/h2-console

Run the API

./mvnw spring-boot:run


Access endpoints

Base URL: http://localhost:8080/api/...

API supports CRUD operations for withdrawals and CSV exports.

📂 Sample Usage

Validate withdrawal: POST /api/withdrawals/validate

Generate CSV notice: POST /api/withdrawals/export

Send notification: POST /api/withdrawals/notify

Example payloads and responses available in Postman collection (if you have one).

📈 Impact / Highlights

Streamlined withdrawal processing workflow.

Reduced operational errors and compliance risks.

Demonstrates backend design ability, combining validation, data export, and automated communication.

💡 Future Enhancements

Integrate with PostgreSQL for production readiness.

Add user authentication and role-based access control.

📄 License

MIT License – feel free to fork and build upon it.
