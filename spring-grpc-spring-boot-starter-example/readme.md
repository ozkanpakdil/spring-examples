# GRPC Spring Boot Demo

A Spring Boot application demonstrating GRPC server implementation with user management and bitmap-based indexing.

spring-grpc-spring-boot-starter example

https://docs.spring.io/spring-grpc/reference/getting-started.html

## Overview

This project implements a GRPC server with Spring Boot that provides user management capabilities. It features:
- User creation and retrieval
- Stream-based GRPC responses
- Bitmap indexing for efficient user querying
- Group management for users

## Technical Stack

- Java 17+
- Spring Boot
- GRPC
- RoaringBitmap for efficient indexing
- Maven for build management
- Protocol Buffers for service definitions

## Project Structure

- `GrpcServerService`: Implements GRPC service endpoints
    - `sayHello`: Creates a new user and returns a greeting
    - `streamHello`: Demonstrates streaming capabilities by sending multiple responses

- `UsersService`: Manages user data with bitmap indexing
    - Maintains user records
    - Indexes users by registration date
    - Indexes users by group membership

- `User`: Data model containing user information
    - Basic user details (name, email, phone)
    - Group memberships
    - Creation timestamp

## API Endpoints

### GRPC Services

1. **Single Request-Response**
```protobuf
rpc sayHello (HelloRequest) returns (HelloReply)
```

# Run the application
mvn spring-boot:run

### Testing
To test the GRPC endpoints, you can use any GRPC client. Example request:

{
"name": "testUser"
}
