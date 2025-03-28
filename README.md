# Multi-Service Dockerized Backend System

This project implements a backend-focused microservice system using Docker and Docker Compose. It consists of three individual services developed in C#, Python, and Java (Gradle). These services communicate via REST APIs and interact with dedicated PostgreSQL databases. The services are containerized and orchestrated through a single `docker-compose.yml` file.

## System Overview

- **web-csharp (Server1)**: Written in C#. Manages restaurant-related data stored in a PostgreSQL database (`restaurant_db`). Accessible internally only.
- **web-python (Server2)**: Written in Python. Manages menu data stored in a PostgreSQL database (`menu_db`). Exposed to the outside world on port `8080`.
- **web-java (Server3)**: Written in Java with Gradle (multi-stage build). Aggregates data from the other two services and exposes it via a unified REST API on port `8081`.

## Architecture and Networking

- Two PostgreSQL databases:
  - `db-restaurant` (used by `web-csharp`)
  - `db-menu` (used by `web-python`)
- `web-csharp` is **not exposed** to external users.
- `web-python` is accessible on `localhost:8080`.
- `web-java` is accessible on `localhost:8081`.

## Technologies

- Backend: C#, Python (Flask/FastAPI), Java (Spring + Gradle)
- Databases: PostgreSQL (2 instances)
- Containerization: Docker
- Orchestration: Docker Compose

## REST API Overview

### web-csharp (internal only)

- `GET /entity/:id` – Retrieve an item by ID
- `GET /entities` – List all items

### web-python (accessible externally on port 8080)

- `GET /entity/:id` – Retrieve an item by ID
- `GET /entities` – List all items

### web-java (aggregator, accessible on port 8081)

- `GET /combined` – Return merged data from both other services as JSON

## Notes

- `web-csharp` is intentionally isolated from external access to simulate an internal microservice.
- All inter-service communication happens over the default Docker network.
- Environment variables are used to configure each service’s database connection and dependencies.
