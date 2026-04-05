## Front end: 
```
http://13.127.223.25:3000/
```
## Overview
1. Finance Dashboard Backend:

A production-ready backend system for managing financial records with secure authentication, role-based access control, and containerized deployment.

2. Features:

- JWT Authentication & Authorization  
- Role-Based Access Control (ADMIN, ANALYST, VIEWER)  
- Financial Records Management (CRUD)  
- Dashboard Summary (Income, Expense, Balance)  
- Filtering & Category-wise Analytics  
- Pagination Support  
- Global Exception Handling  
- Clean API Response Structure  
- Dockerized Application  

3. Tech Stack:

- Backend: Spring Boot, Spring Security  
- Database: H2 (In-Memory)  
- ORM: Spring Data JPA, Hibernate  
- Authentication: JWT  
- Mapping: MapStruct  
- Build Tool: Maven  
- Containerization: Docker  

## Project Structure
3. Project Structure
```
com.karthik.dashboard.dashboard
│
├── config          # Security & Data Initialization
├── controller      # REST APIs
├── dto             # Request & Response DTOs
├── exception       # Global Exception Handling
├── model           # Entities & Enums
├── repo            # JPA Repositories
├── security        # JWT, Filters, Security Utils
└── service         # Business Logic
```
5. Api Endpoints:

Authentication:
```
	•	Register → POST /api/auth/register
	•	Login → POST /api/auth/login
```	
Financial Records:
```
	•	Create Record → POST /api/records (ADMIN only)
	•	Get All Records → GET /api/records?page=0&size=10
	•	Get Record by ID → GET /api/records/{id}
	•	Update Record → PUT /api/records/{id} (ADMIN only)
	•	Delete Record → DELETE /api/records/{id} (ADMIN only)
```
Analytics:
```
	•	Dashboard Summary → GET /api/records/summary
	•	Filter Records → GET /api/records/filter?type=INCOME&category=Salary
	•	Category Summary → GET /api/records/category-summary
```
6. Default Users:

| Role    | Email             | Password   |
|---------|------------------|------------|
| ADMIN   | admin@test.com   | admin123   |
| ANALYST | analyst@test.com | analyst123 |
| VIEWER  | viewer@test.com  | viewer123  |

7. Environment Variables:  JWT_SECRETE=your_super_secret_key_here


## From GitHub
8. To run locally: (follow the instructions).
- Clone Repository:
```
git clone https://github.com/karthi2005/finance-dashboard.git
cd finance-dashboard

export JWT_SECRETE=your_super_secret_key_here

mvn clean install
mvn spring-boot:run
```
- Provide Jwt Environment Variable:  export JWT_SECRETE=your_super_secret_key_here
- Build & Run Application: ```mvn clean install```
                            ```mvn spring-boot:run```
- Acess Application: http://localhost:8080

## Quick Start
   Clone → Set JWT_SECRETE → Run → Done


## this project or image has been uploaded to Docker hub
## Run from Docker Hub
```
docker pull karthi2005/finance-dashboard:latest
docker run -p 8080:8080 karthi2005/finance-dashboard:latest
```


## Run with Docker (Local Build)

```bash
docker build -t finance-dashboard .

docker run -p 8080:8080 \
-e JWT_SECRETE=your_secret_key \
finance-dashboard
```

---

## Deployment (AWS EC2)

1. Launch EC2 instance  
2. Install Docker  
3. Pull image from Docker Hub  
4. Run container  

```bash
docker pull karthi2005/finance-dashboard:latest

docker run -d -p 80:8080 \
-e JWT_SECRETE=your_secret_key \
karthi2005/finance-dashboard:latest
```

9.Testing with Postman:

    1). Login → Get JWT Token  
    2). Add Header: Authorization: Bearer <token>  
    3). Call APIs  

## Notes	
  JWT expires in 1 hour (add refresh token, token rotation in development)
  H2 database resets on restart
  Ensure correct port configuration in deployment
  In default VIEWER role is assgined during new registration. 
  No one can change data except with ROLE_ADMIN.
  Analyst can view records but cannot access or change or add them.

10.Author:  Karthik Narravula

## final thoughts
- Use postgresql in production
- Use swagger.
- use spring actuator in production for monitoring.
- implementation of password evaluation in production.
- implementation of good ui.
- implementation of jwt token refresh, Oauth2 to improve security.
- Another separate front end application to create Analyst accounts by admin.
  ## APIs
  check APIs using postman to check their format for frontend implementation
  
 

