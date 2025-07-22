# dad_project_real
Lost-And-Found-University-System
Lost And Found University System for students and staff to track and report the lost and found items in university area.

🧭 Lost & Found Item Management System (University Project)
This is a full-stack web application for reporting, managing, and tracking lost and found items in a university environment.

🔍 Features
📦 Report lost or found items with images
🧠 Admin dashboard for managing reports
🔗 Match lost and found items manually
✅ Claim items and update status
📊 Analytics with pie and bar charts (Matched vs Unmatched, Category, Location)
🔐 JWT authentication for secure login
🎨 Responsive HTML + JavaScript frontend
🚀 Tech Stack
Frontend: HTML, CSS, JavaScript (Vanilla)
Backend: Spring Boot (Java)
Database: MySQL
Image Hosting: ImgBB API
Authentication: JWT (JSON Web Tokens)
📂 Project Structure
├── backend/ │ └── src/main/java/com/utm/lostfound/ │ ├── controller/ │ ├── model/ │ ├── repository/ │ └── config/ ├── frontend/ │ ├── Admin.html │ └── LostFound.html └── README.md

yaml Copy Edit

🛠️ Setup Instructions
1. Clone the repo
git clone https://github.com/your-username/lost-found-project.git
cd lost-found-project
2. Backend Setup (Spring Boot)
Open in Eclipse to work on backend and frontend VS Code

Configure application.properties with your MySQL DB

Run the backend server

3. Frontend
Open login.html (for login before get access to Admin.html or LostFound.html)
LostFound.html (for Lost and Found report submission)
Admin.html (for Admin Dashboard)


Ensure backend runs at http://localhost:8080
