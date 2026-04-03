1.Finance Dashboard Backend:

A production-ready backend system for managing financial records with secure authentication, role-based access control, and containerized deployment.

2.Features:

- JWT Authentication & Authorization  
- Role-Based Access Control (ADMIN, ANALYST, VIEWER)  
- Financial Records Management (CRUD)  
- Dashboard Summary (Income, Expense, Balance)  
- Filtering & Category-wise Analytics  
- Pagination Support  
- Global Exception Handling  
- Clean API Response Structure  
- Dockerized Application  

2.Tech Stack:

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
4.Api Endpoints:
| Role    | Email             | Password   |
|---------|------------------|------------|
| ADMIN   | admin@test.com   | admin123   |
| ANALYST | analyst@test.com | analyst123 |
| VIEWER  | viewer@test.com  | viewer123  |

5.Default Users:
   1) ADMIN:  
             email: admin@test.com
             password: admin123
   2) ANALYST: 
             email: analyst@test.com
             password: analyst123
   3) VIEWER: 
             email: viewer@test.com
             password: viewer123


6.Environment Variables:  JWT_SECRETE=your_super_secret_key_here


## From GitHub
7.To run locally: (follow the instructions).
1. Clone Repository: 
2. Provide Jwt Environment Variable:  export JWT_SECRETE=your_super_secret_key_here
3. Build & Run Application: mvn clean install
                            mvn spring-boot:run
4. Acess Application: http://localhost:8080

## Quick Start
   Clone → Set JWT_SECRETE → Run → Done


## this project or image has been uploaded to Docker hub
## Run from Docker Hub
docker pull karthi2005/finance-dashboard:latest
docker run -p 8080:8080 karthi2005/finance-dashboard:latest

8.Run with Docker:


Build Image: docker build -t finance-dashboard .
Run Container: docker run -p 8080:8080 -e JWT_SECRETE=your_secret_key finance-dashboard
 Run from Docker Hub: docker run -p 8080:8080 \
-e JWT_SECRETE=your_secret_key \
karthi2005/finance-dashboard:latest

9.Deployment (AWS EC2):
	1.	Launch EC2 instance
	2.	Install Docker
	3.	Pull image from Docker Hub
	4.	Run container

docker pull karthi2005/finance-dashboard:latest
docker run -d -p 80:8080 -e JWT_SECRETE=your_secret_key karthi2005/finance-dashboard:latest

10.Testing with Postman:

    1). Login → Get JWT Token  
    2). Add Header: Authorization: Bearer <token>  
    3). Call APIs  

## Notes	
  JWT expires in 1 hour (add refresh token, token rotation in development)
  H2 database resets on restart
  Ensure correct port configuration in deployment

11.Author:  Karthik Narravula

## final thoughts
- Use postgresaql in production
- Use swagger.
 

