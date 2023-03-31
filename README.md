# web-customer-tracker
Customer relationship manager web app

The app was created for educational purposes for Spring framework course. Base app consists of Spting MVC and Hibernate but I added Spring security and second database for user registration and login.

Application has next functions:
 - main application screen requires login and adapts to user roles;
 - registration form with form validation. All new users has EMPOYEE form by default;
 - add new customer with fields validation (for MANAGER and ADMIN);
 - delete a customer (ADMIN only);
 - update customer (for MANAGER and ADMIN);
 - search customer by first name or last name with substrings (EMPLOYEE, MANAGER or ADMIN);
 - sort customer table by name, last name or email (EMPLOYEE, MANAGER or ADMIN).

Application structure:

![structure](https://github.com/strelnikov-e/web-customer-tracker/blob/main/images/App%20structure.png?raw=true)


Application interface:

![interface](https://github.com/strelnikov-e/web-customer-tracker/blob/main/images/app-interface.png?raw=true)
