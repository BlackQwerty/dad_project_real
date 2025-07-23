# 🧭 Lost & Found Management System (University Project)

A full-stack distributed web application for reporting and managing lost and found items in a university setting. Built using **Spring Boot**, **MySQL**, and **Vanilla JavaScript + HTML**, it offers both public-facing and admin-facing interfaces with real-time analytics, secure login, and image handling via ImgBB API.

---

## 📌 Project Overview

Students and staff often lose or find personal belongings on campus. This project provides a structured system to report, match, claim, and monitor lost and found items with transparency and efficiency.

---

## 💼 Commercial Value / Third-Party Integration

In real-world campuses, this system reduces administrative workload and increases the return rate of lost items.  

### 🔗 Integrated Third-Party Services:
- **ImgBB API** – Used to host uploaded item images.
- **JWT (JSON Web Tokens)** – Provides secure login and user role access control.

---

## 🏗️ System Architecture

### High-Level Diagram

```mermaid
graph TD
    A[LostFound.html (Public User)] -->|HTTP| C[Spring Boot Backend]
    B[Admin.html (Admin Panel)] -->|HTTP| C
    C --> D[(MySQL Database)]
    C --> E[ImgBB API]
