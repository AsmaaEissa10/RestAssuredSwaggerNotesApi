# ✨ Rest Assured Practice ✨

*  **[ Swagger Notes API Documentation ]**
* 🌐 **( Base URL: https://practice.expandtesting.com/notes/api/api-docs/ )**

### 🗃️ Documentation:
* **[Rest-Assured Docs] ( https://rest-assured.io/ )**

### The main Frameworks included in the project:
* Rest Assured
* TestNG

### Project Design:
* Page Object Model (POM) design pattern
* Data Driven framework using JSON

### This Project included:

### 📦️2 packages in src/main/java
#### 1- api 
* used in it HTTP methods to make requests
#### 2- pojoBody 
*  used to represent data. and contain private data members and public setter and getter methods for every data member.
***************************************
### 📦️2 packages in src/test/java
#### 1* data => included 1 class 
###### 1- ReadJsonData => to read data
#### 2* tests => included 3 Classes
###### 1- baseTest => covered BeforeMethod 
###### 2- UsersApisTests => covered the following testcases
  - Register
  - Login
  - UserProfile
  - UpdateUserProfile
  - ForgetPassword
  - NewPassword
  - Logout
  - LoginAgainWithNewPassword
  - DeleteAccount
###### 3- NotesApisTests => covered the following testcases
  - Register
  - login
  - CreateNote
  - UpdateNoteById
  - CompleteUpdateById
  - DeleteNote
***************************************
### 🧱 2 Directory in src/test/resources/TestData
#### 1- users => included 3 Json file
* ( registerNoteData.json - updateUserData.json - newPasswordData.json )
#### 2- notes => included 3 Json file
 * ( createNoteData.json - patchNoteData.json - putNoteData.json )
***************************************
### ⚗️ 2 xml files
#### 1- pom.xml 
* ( XML file that contains information about the project and configuration details used by Maven to build the project)
#### 2- testng.xml 
* ( is the configuration for TestNG testing framework ( defining test suite )
 