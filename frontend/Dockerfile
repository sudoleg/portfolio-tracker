# Stage 1: Build the Angular application
FROM node:20-alpine AS build
WORKDIR /app

# Copy package.json and package-lock.json
COPY . /app/

# Install dependencies
RUN npm ci

# Copy the rest of the application files
COPY . .

# Build the Angular application
RUN npm run build

# Stage 2: Serve the application with Nginx
FROM nginx:1.29-alpine-slim
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]