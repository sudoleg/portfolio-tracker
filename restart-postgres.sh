#!/bin/bash

# Stop and remove the Docker container named 'postgres'
docker stop postgres
docker rm postgres

# Prune Docker volumes
docker volume rm portfolio-tracker_postgres -f

# Start the postgres service in detached mode using docker-compose
docker-compose up -d postgres

# Output message indicating the script has completed its tasks
echo "Postgres container stopped, removed, postgres volume deleted, and postgres service started in detached mode."
