#!/bin/bash
docker-compose up --build -d postgres backend
cd frontend/
npm install
npm start
