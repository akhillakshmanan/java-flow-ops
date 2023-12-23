# java-flow-ops
Continuous Running Java Project - CRUD Operations with In-Memory Data Storage

# Overview
This Java application is a simple CRUD (Create, Read, Update, Delete) system that operates on in-memory data storage. 

The application is designed to manage data related to departments, teachers, and students, establishing relationships based on department values.

# Prerequisites
Java 8 or above
Gson library (version 2.10.1)
Getting Started



# Clone the Repository:
git clone <repository-url>
Download Gson Library:

Download the Gson library (version 2.10.1) and add it to the classpath as an external JAR.

# Run the Application:
cd <project-directory>
javac Main.java
java Main
Usage
Input Format
Input to the application should be provided as a JSON string for department, student, or teacher entities.


# Sample Inputs
Department{"id":dep1,"name":"commerce","NoOfTeachers":0,"NoOfStudents":0}
Student{"rollNo":12,"name":"John","age":21,"address":"House No:12","department":"commerce","cource":"BBA"}
Teacher{"staffId":12,"name":"John","age":21,"address":"House No:14","department":"CS","specialization":"Java"}

# Dependencies
Gson Library 2.10.1
