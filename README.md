#  POS System

## Introduction

A Point of Sale (POS) system is a combination of hardware and software used by businesses to process sales transactions, manage inventory, track customer data, and generate reports. It typically includes components like a cash register, barcode scanner, receipt printer, and a payment terminal, all integrated into a software platform that streamlines retail operations.

### Technologies Used

- **Backend:** Java EE
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL
- **Communication:** AJAX

 ## Installation
### Prerequisites
- Java 17 (OpenJDK 17)
- Maven
- MySQL
- Apache Tomcat 10


### Features

- Sales Management: Quickly process transactions.
- Inventory Tracking: Monitor stock and manage products.
- Customer Management: Keep customer records and history.
- Database Integration: Use MySQL for reliable data storage.
- Real-Time Updates: Use AJAX for instant data communication.

This project demonstrates a cohesive integration of frontend and backend technologies, along with robust database management, to create a functional and efficient POS system tailored for a fruit shop.

## API Documentation

You can find the detailed API documentation [API Documentation](https://documenter.getpostman.com/view/35386291/2sA3s1oBni).

## API Endpoints

Here is a brief description of the available API endpoints:

### 1. item


Customers Endpoints:

- POST /customer: Add a new customer. Requires a JSON object with customer details. Returns the added customer object.

- GET /customer/{id}: Retrieve a specific customer's details by ID. No parameters required. Returns the customer object.

- PUT /customer/{id}: Update a customer by ID. Requires a JSON object with updated customer details. Returns the updated customer object.

- DELETE /customer/{id}: Delete a customer by ID. No parameters required. Returns a success message.


Item Endpoints:

- GET /item:
  Fetch all items or a specific item by code. No parameters required. Returns a JSON array of item objects.

- POST /item:
   Add a new item. Requires a JSON object with item details. Returns the added item object.

- PUT /item/{id}:
  Update an item by ID. Requires a JSON object with updated item details. Returns the updated item object.

- DELETE /item/{id}:
   Delete an item by ID. No parameters required. Returns a success message.

Orders Endpoints:

- GET /orders: Retrieve all orders. Returns a JSON array of order objects.

- POST /orders: Create a new order. Requires a JSON object with order details. Returns the created order object.

- GET /orders/{id}: Retrieve details of a specific order by ID. Returns the order object.

## Setup and Installation
&nbsp;1. Clone the repository.  
&nbsp;2. Set up the back-end dependencies.  
&nbsp;3. Configure the database connection.  
&nbsp;4. Deploy the JavaEE application on the Apache Tomcat server.

## Project Repository

You can find the frontend development project on GitHub [Frontend POS System](https://github.com/nethmiumaya/POS_system-FrontEnd-.git).





## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
