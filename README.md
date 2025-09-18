# personal-finance-tracker

A Spring Boot + PostgreSQL based RESTful backend for managing personal finances.  
This project helps users track **income, expenses, categories, and transactions** with advanced reporting.

## 🔹 1. Project Folder Structure (example)

```
personal-finance-tracker/
 ├── src/
 │    └── main/
 │         ├── java/com/yourname/finance/...
 │         └── resources/
 │              └── application.properties
 ├── pom.xml
 ├── .gitignore
 ├── README.md
```


````markdown


---

## 🚀 Features
- 👤 **User Management** – Register and manage users
- 📂 **Categories** – Organize expenses and income
- 💵 **Transactions** – Record income/expense with relationships:
  - One-to-Many: User → Transactions
  - Many-to-One: Transactions → Categories
- 📊 **Reports**
  - Filter transactions by **date range**
  - Group transactions by **category**
  - **Monthly summaries** (income vs expenses)
  - **Category breakdown** for spending

---

## 🛠 Tech Stack
- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Testing:** Postman
- **Architecture:** REST API + Layered (Controller, Service, Repository)

---

## 📦 API Endpoints (Sample)

### Users
- `POST /users` – Create new user
- `GET /users/{id}` – Get user by ID
- `PUT /users/{id}` – Update user
- `DELETE /users/{id}` – Delete user

### Categories
- `POST /categories` – Add category
- `GET /categories` – List all categories

### Transactions
- `POST /transactions` – Add new transaction
- `GET /transactions` – List all transactions
- `GET /transactions?from=2025-01-01&to=2025-01-31` – Filter by date
- `GET /transactions/category/{id}` – Filter by category
- `GET /transactions/summary/monthly` – Monthly income vs expenses
- `GET /transactions/summary/category` – Category-wise breakdown

---

## ⚙️ Setup & Run

1. Clone the repository
   ```bash
   git clone https://github.com/your-username/personal-finance-tracker.git
   cd personal-finance-tracker
````

2. Configure PostgreSQL in `src/main/resources/application.properties`

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/finance_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build & run

   ```bash
   mvn spring-boot:run
   ```

4. Test APIs using Postman (import `FinanceTracker.postman_collection.json` if provided)

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss.



