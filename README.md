# Redis

## Description

This project demonstrates various caching strategies using Redis in microservices architecture:

* `api` — the main REST API integrated with Redis
* `ui` — a simple UI client for interacting with the API
* `RedisInsight` — a visual interface for monitoring Redis
* `PostgreSQL` — used as the primary data storage

Implemented caching strategies:

* Cache-Aside
* Read-Through
* Write-Through
* Write-Behind
* Full Page Cache

The project also includes a demonstration of concurrency issues and an example using RedisJSON.

## Architecture

```
+-------+       REST       +------------+
|  UI   +----------------> |   API      |
+-------+                  +------------+
                              |
               +--------------+--------------+
               |                             |
         Redis Stack                 PostgreSQL
 (RedisJSON, TTL, Insight)         (persistent DB)
```

## Endpoints

OpenAPI Documentation: `api/openapi.yml`
[OpenAPI Spec](api/openapi.yaml)  
Example request:

```bash
curl -X POST http://localhost:8087/api/v1/users \
  -H 'Content-Type: application/json' \
  -d '{"name": "Alice", "age": 30}'
```

## Quick Start with Docker Compose

```bash
docker-compose up
```

Services started:

* API (`localhost:8087`)
* Redis (`localhost:6379`)
* RedisInsight (`localhost:5540`)
* PostgreSQL (`localhost:5433`)
* UI (`localhost:3000`)

## Project Structure

```
proselyte-redis-course/
├── docker-compose.yml
├── api/
│   ├── build.gradle
│   ├── Dockerfile
│   └── src/... (Spring Boot REST API)
├── ui/
│   ├── Dockerfile
│   └── src/... (Node.js / React UI)
└── README.md
```

## Technologies

* Java
* Spring Boot (Web, Data Redis, Scheduling)
* Redis / Redis Stack / RedisInsight
* PostgreSQL
* Docker / Docker Compose
* Node.js / React (UI)