# Use the official Node.js 20 alphine image
FROM node:20.0-alpine3.16 as build

# Set the working directory
WORKDIR /app
# Copy package.json and package-lock.json

COPY package*.json ./
# Install dependencies
RUN npm install
# Copy the rest of the application

COPY . .
# Expose the port the app runs on
EXPOSE 3000
# Command to run the app

CMD ["npm", "run", "start"]
