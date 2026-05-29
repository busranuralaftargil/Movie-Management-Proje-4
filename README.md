# Movie Management System - Version 4

## Overview

Movie Management System Version 4 is a distributed four-tier web application developed using Spring Boot, Node.js, Vite and MySQL/PostgreSQL.

This version separates the front-end and back-end into independent applications communicating through REST APIs. The project also supports image upload/download functionality and PDF report generation.

---

## System Architecture

The application follows a four-tier architecture:

Browser (Tier 4)
↓
Node.js + Vite Frontend (Tier 3)
↓
Spring Boot REST API (Tier 2)
↓
MySQL / PostgreSQL Database (Tier 1)

The frontend communicates with the backend through RESTful APIs, while the backend manages business logic and database operations.

---

## Features

### Movie Management

* Create movies
* Update movies
* Delete movies
* View movie details
* Search and filter movie records

### Actor Management

* Create actors
* Update actor information
* Delete actors
* View actor details

### Relationship Management

* Manage movie-actor relationships
* Associate actors with movies
* Retrieve related data through REST APIs

### Image Management

* Upload images
* Download images
* Store and display media resources

### PDF Reporting

* Generate PDF reports from database records
* Export table-based data
* Download generated reports

### REST API

* CRUD endpoints
* DTO-based communication
* Layered architecture
* Service and Repository patterns

---

## Technologies Used

### Backend

* Java 25
* Spring Boot 4.0.1
* Spring Data JPA
* Hibernate
* Maven
* MySQL / PostgreSQL

### Frontend

* Node.js 24.13.0
* Vite 8.2.0
* Vanilla JavaScript
* HTML5
* CSS3

### Reporting

* JasperReports / JasperSoft Community Edition
* PDF Export

---

## Project Structure

### Backend

* Controllers
* Services
* Repositories
* DTOs
* Entities
* PDF Services
* File Management Services

### Frontend

* Views
* API Services
* Components
* Assets
* Image Management UI

---

## API Features

* Movie CRUD APIs
* Actor CRUD APIs
* Relationship APIs
* Image Upload API
* Image Download API
* PDF Report API

---

## Learning Outcomes

This project demonstrates:

* Distributed application development
* RESTful API design
* Frontend-backend separation
* File management systems
* PDF report generation
* Four-tier architecture implementation

---

## Author

**Büşranur Alaftargil**

Software Engineering Student
Sakarya University

---

## Version

Current Version: **4.0**
