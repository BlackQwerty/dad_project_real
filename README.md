## **Team Members**

> **Team Members:**
> 
> • Ahmad Shukri Bin Bakri - B032310856  
> • Che Khuaimanfikri Bin Che Om - B032310698  
> • Muhammad Haziq Bin Norizan - B032310357  
> • Muhammad Ammarul Arrif Bin Mohd Arrifin - B032310874  
> • Muhammad Fikri Bin Abdullah - B032310813

 🧭 Lost & Found Management System (University Project)

A full-stack distributed web application for reporting and managing lost and found items in a university setting. Built using **Spring Boot**, **MySQL**, and **Vanilla JavaScript + HTML**, it offers both public-facing and admin-facing interfaces with real-time analytics, secure login, and image handling via ImgBB API.



 📌 Project Overview

Students and staff often lose or find personal belongings on campus. This project provides a structured system to report, match, claim, and monitor lost and found items with transparency and efficiency.



💼 Commercial Value / Third-Party Integration

In real-world campuses, this system reduces administrative workload and increases the return rate of lost items.  

🔗 Integrated Third-Party Services:
- **ImgBB API** – Used to host uploaded item images.
- **JWT (JSON Web Tokens)** – Provides secure login and user role access control.


 🏗️ System Architecture

graph TD
    A[LostFound.html (Public User)] -->|HTTP| C[Spring Boot Backend]
    B[Admin.html (Admin Panel)] -->|HTTP| C
    C --> D[(MySQL Database)]
    C --> E[ImgBB API]

🖥️ Backend Application
🧪 Technology Stack
Language: Java 17

Framework: Spring Boot 3.4.7

Database: MySQL

Security: JWT

Libraries: Spring Security, Spring Data JPA, Lombok, Jackson

📡 API Documentation
✅ Main Endpoints
Endpoint	Method	Description
/api/report-lost	POST	Submit lost item
/api/report-found	POST	Submit found item
/api/reports	GET	Retrieve all reports
/api/match	POST	Match lost & found items
/api/claim/{id}	PUT	Mark item as claimed
/api/auth/login	POST	Authenticate and receive JWT
/api/stats/category	GET	Lost items by category
/api/stats/location	GET	Lost items by location
/api/stats/match-status	GET	Matched vs unmatched stats

📥 Request Example
json
Copy
Edit
POST /api/report-lost
Content-Type: application/json

{
  "name": "Blue Wallet",
  "category": "Accessories",
  "description": "Left in cafeteria",
  "location": "Cafeteria",
  "dateReported": "2025-07-20",
  "imageUrl": "https://i.ibb.co/example.jpg",
  "type": "lost",
  "status": "pending",
  "reportedBy": 1
}
📤 Response (Success)
json
Copy
Edit
{
  "id": 12,
  "status": "pending",
  "type": "lost"
}
🔐 Security
All sensitive endpoints (/api/match, /api/claim, /api/reports) are protected using JWT Authentication.

JWT is included in the Authorization header using the Bearer schema.

http
Copy
Edit
Authorization: Bearer <your-token>
This ensures only authorized admins can perform matches, claims, and view statistics.

💻 Frontend Applications
🎯 1. LostFound.html (Public User Interface)
Purpose: Allows any university user to submit and view reports.

Stack: HTML, CSS, Vanilla JavaScript

API Integration: Uses fetch() to call endpoints like:

/api/report-lost

/api/report-found

/api/reports (GET with filters)

🛠️ 2. Admin.html (Admin Panel)
Purpose: Allows admin to view reports, manually match items, claim items, and view analytics.

Stack: HTML, CSS, Vanilla JavaScript, Chart.js

API Integration: Authenticated fetch with JWT to endpoints like:

/api/reports

/api/claim/{id}

/api/match

/api/stats/*

🗃️ Database Design
🧩 Entity-Relationship Diagram (ERD)
mermaid
Copy
Edit
erDiagram
    USER ||--o{ REPORT : has
    REPORT {
        Long id PK
        String name
        String category
        String description
        String location
        Date dateReported
        String type
        String status
        String imageUrl
        Long matchedWith FK (nullable)
    }
    USER {
        Long id PK
        String email
        String password
        String role
    }
💬 Schema Justification
User stores login credentials and role (admin/user).

Report table contains all item reports (both lost/found), with a foreign key matchedWith for linking matched items.

🔄 Business Logic & Validation
🔁 Use Case Flows
Report Lost/Found → Fill form → Upload image to ImgBB → Submit JSON to backend → Store in DB

Admin Login → Fetch JWT → Access reports & analytics

Match Items → Select two items (lost + found) → POST to /api/match

Claim Item → PUT /api/claim/{id} → Mark as claimed

✅ Data Validation
Frontend:

Required fields (no blank item names, category, date, etc.)

File upload presence

Backend:

JSON validation using @Valid

@NotNull and format checks (e.g., valid date)

Prevent duplicate matching and invalid claims


