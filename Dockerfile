# Use the official Node.js 20 alphine image

FROM openjdk:23-jdk
# Set the working directory
WORKDIR /src
# Copy package.json and package-lock.json

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
# Install dependencies
RUN npm install
# Copy the rest of the application

COPY . .
# Expose the port the app runs on
EXPOSE 3000
# Command to run the app

CMD ["npm", "run", "start"]


