## Running the Project

### 1. Build and start all services

```bash
docker-compose up --build
```

### 2. Access exposed services

- Python service: http://localhost:8080
- Java (aggregator) service: http://localhost:8081

### 3. View logs

```bash
docker-compose logs -f
```

### 4. Stop all containers

```bash
docker-compose down
```

## Database Initialization

Each PostgreSQL container runs an SQL script on startup:

- `server1/database1.sql` initializes `restaurant_db`
- `server2/database2.sql` initializes `menu_db`

You can edit these scripts to define table schemas and insert initial data.
