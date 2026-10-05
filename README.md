# Vehicle Rental Management System

A Java Swing-based desktop application for managing vehicle rentals, customers, rental transactions, vehicle returns, invoices, and revenue analytics.

The system is designed as an academic software project demonstrating Java GUI development, object-oriented programming, event-driven programming, file handling, data management, and desktop application design.

---

## Features

### Administrator Login

* Secure administrator login interface
* Demo authentication system
* Simple desktop-based access control

### Dashboard

* Total vehicles
* Total customers
* Active rentals
* Earned revenue
* Rental activity overview

### Vehicle Management

* Add vehicles
* View vehicle details
* Track vehicle availability
* Store registration number, brand, model, category, fuel type, and daily rental rate

### Customer Management

* Add customer details
* Store name, phone number, email, driving license, and address
* Search and manage registered customers

### Vehicle Rental

* Select customer and available vehicle
* Specify rental duration
* Calculate rental amount
* Record security deposit
* Automatically update vehicle availability

### Vehicle Return

* Process returned vehicles
* Automatically calculate overdue days
* Calculate late charges
* Record vehicle condition
* Add applicable damage/repair charges
* Calculate deposit usage and refund
* Update vehicle availability

### Rental History

* View previous rental transactions
* Track rental and return information
* Monitor completed and active rentals

### Reports & Analytics

* Total revenue
* Today's revenue
* Monthly revenue
* Pending rental revenue
* Security deposits currently held
* Rental statistics

### Invoice Management

* Generate rental invoices
* Display customer and vehicle information
* Display rental charges
* Display late and damage charges
* Display deposit settlement
* Save invoice as a text file
* Print invoice

### Email Invoice

The application can open the computer's default email client with:

* Customer email address
* Invoice subject
* Invoice details

The administrator can review the email and manually send it.

---

## Technologies Used

* **Java**
* **Java Swing**
* **AWT**
* **Object-Oriented Programming**
* **ArrayList**
* **File Handling**
* **JTable**
* **JFileChooser**
* **Java Desktop API**
* **Java Printing API**
* **Eclipse IDE**

---

## Application Architecture

The current version uses an in-memory data model.

```text
                    Vehicle Rental Management System
                                  |
                 +----------------+----------------+
                 |                |                |
             Dashboard        Management       Transactions
                                |                |
                       +--------+--------+       |
                       |        |        |       |
                    Vehicle  Customer  Rental   Return
                                             |
                                             |
                                      Invoice / Revenue
```

---

## Project Structure

```text
Vehicle-Rental-Management-System/
│
├── src/
│   └── vehicleRentalSystem/
│       └── VehicleRentalSystem.java
│
├── screenshots/
│   ├── login.png
│   ├── dashboard.png
│   ├── vehicle-management.png
│   ├── rental.png
│   ├── return.png
│   ├── invoice.png
│   └── reports.png
│
├── README.md
├── .gitignore
└── LICENSE
```

---

## Demo Login

The current application contains demonstration credentials:

```text
Username: admin
Password: admin123
```

These credentials are intended only for academic demonstration purposes and should not be used for production authentication.

---

## How to Run

### Requirements

Install:

* Java JDK 8 or later
* Eclipse IDE or another Java IDE

Check Java installation:

```bash
java -version
```

Check the Java compiler:

```bash
javac -version
```

---

## Running Using Eclipse

### Step 1: Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/Vehicle-Rental-Management-System.git
```

### Step 2: Open Eclipse

Select:

```text
File → Import
```

Then:

```text
General → Existing Projects into Workspace
```

Select the cloned project directory.

### Step 3: Verify source folder

The Java source should be located at:

```text
src/vehicleRentalSystem/VehicleRentalSystem.java
```

### Step 4: Run the application

Right-click:

```text
VehicleRentalSystem.java
```

Select:

```text
Run As → Java Application
```

### Step 5: Login

Use:

```text
Username: admin
Password: admin123
```

---

## Running From Command Prompt

Navigate to the project directory:

```bash
cd Vehicle-Rental-Management-System
```

Compile:

```bash
javac -d bin src\vehicleRentalSystem\VehicleRentalSystem.java
```

Run:

```bash
java -cp bin vehicleRentalSystem.VehicleRentalSystem
```

If your project contains additional `.java` files in the future, compile all source files instead of only this one.

---

## Invoice Workflow

The invoice module supports three delivery methods:

```text
Generate Invoice
       |
       +---- Save as TXT
       |
       +---- Print
       |
       +---- Open Default Email Client
```

The email feature uses the Java Desktop API and does not require SMTP credentials.

---

## Revenue Calculation

Revenue is calculated from completed rental transactions.

The system distinguishes between:

* Rental charges
* Late charges
* Damage charges
* Security deposits

Security deposits are treated separately because they represent customer funds held temporarily and are not automatically considered business revenue.

---

## Current Data Storage

The current version stores application data in memory using Java `ArrayList` collections.

Therefore:

> Data added during an application session is not permanently stored after the application is closed.

The sample vehicle and customer records are loaded when the application starts.

---

## Limitations

* No database integration
* Data is stored in memory
* Authentication is demonstration-level
* Email functionality opens the system email client rather than sending mail through an SMTP server
* No online payment gateway
* No cloud synchronization
* No multi-user access

---

## Future Enhancements

Possible improvements include:

* MySQL or SQLite database integration
* Persistent customer and vehicle records
* Password hashing and improved authentication
* Role-based access control
* PDF invoice generation
* Automated email delivery
* SMS/WhatsApp notification integration
* Online payment integration
* Vehicle maintenance tracking
* Advanced revenue analytics
* Cloud-based deployment
* Backup and restore functionality

---

## Learning Outcomes

This project demonstrates practical implementation of:

* Java programming
* Object-oriented programming
* GUI development using Swing
* Event-driven programming
* Collections framework
* Exception handling
* File handling
* Date and time handling
* Table-based data presentation
* Desktop integration
* Software project organization

---

## Screenshots

### Login

Add your screenshot here:

```text
screenshots/mockup_login.png
```

### Dashboard

```text
screenshots/mockup_dashboard.png
```

### Vehicle Management

```text
screenshots/mockup_vehicle_management.png
```

### Vehicle Rental

```text
screenshots/mockup_new_rental.png
```

### Vehicle Return

```text
screenshots/mockup_return_settlement.png
```

### Invoice

```text
screenshots/mockup_invoice.png
```

### Reports

```text
screenshots/mockup_reports.png
```

---

## Author

**Sanskar K.**

B.Tech Electronics & Telecommunication Engineering

Pillai College of Engineering, New Panvel

---

## License

This project is intended primarily for academic and educational purposes.
