# Project 2 B

## Task 1
### Instructions
Develop between 10 and 12 classes that you feel can be used to best describe MIS (be sure to choose classes in such a way that a meaningful class diagram can be drawn, as you will be asked to do this in the next task). These classes should be different from those included in the Project Assignment #2a. Each class should have between 4 and 6 attributes and between 4 and 6 methods, though more than 6 may be required to accurately model the full functionality of some classes. These classes should be represented as UML class diagrams.

### Task 1 Solution

Classes:
- [ ] Staff - Similar to patient, can have differnt types like doctor, nurse, administrator, and other roles
    - [ ] Attributes
        - [ ] Role
        - [ ] Pay
        - [ ] Scheduled Hours
        - [ ] Security Access / Credentials
    - [ ] Methods
        - [ ] Get Role
        - [ ] Get Pay
        - [ ] Get Scheduled Hours
        - [ ] Schedule Employee
        - [ ] Reset Credentials
- [ ] Staff Database - For storing and reporting data on the staff
    - [ ] Attributes
        - [ ] Arraylist of all staff objects
        - [ ] Number of employees
    - [ ] Methods
        - [ ] Find Employee
        - [ ] Print Schedule
        - [ ] Employee Summary Report
- [ ] Staff Payroll/Hiring/HR - for interfacing between the hospitals 
    - [ ] Attributes
        - [ ] Extends Staff database and Staff
        - [ ] Spots needed to be filled
        - [ ] Stores Arraylist of requested personnel
        - [ ] Stores Hiring materials and legal documents
    - [ ] Methods
        - [ ] Hire Staff
        - [ ] Fire Staff
        - [ ] I9 Report
        - [ ] Change Pay
        - [ ] Make offer
        - [ ] Give Bonus
- [ ] Hospital Department - Stores info related to the type of care in the unit, Prenatal, radiology, cardiology, etc
    - [ ] Attributes
        - [ ] Type of Care (Radiology, Cariology, etc)
        - [ ] Staff Responsible / On duty
        - [ ] Needed Staff
        - [ ] Equiptment inventory
    - [ ] Methods
        - [ ] Get Equiptment
        - [ ] Get Type
        - [ ] Get Staff
        - [ ] Get Schedule / Hours
- [ ] Hospital - Parent of Staff, Staff database, Patient billing, Patients, and other hospital specific requirements
    - [ ] Attributes
        - [ ] Departments
        - [ ] Staff Database
        - [ ] Patient Database
        - [ ] Hospital Location
        - [ ] Assets
        - [ ] Contracts
        - [ ] Banking Info
    - [ ] Methods
        - [ ] List Departments
        - [ ] List Staff
        - [ ] Find Patient in hospital
        - [ ] Pay Lease
- [ ] Hospital Network - For dealing with all of the hospitals in the network, and different types of care centers (ER, Minute clinic, Pharmacy, Trauma Center)
    - [ ] Attributes
        - [ ] Group Name
        - [ ] Number of Hospitals
        - [ ] Shares outstanding
        - [ ] Corperate Structure
    - [ ] Methods
        - [ ] Transfer Patient
        - [ ] Transfer Staff
        - [ ] Aquire Funding
        - [ ] Sell Hospital
        - [ ] Negotiate with Insurance
- [ ] ER Intake - for new patients who are unidentified or may not be able to fill out paperwork before treatment
    - [ ] Attributes
        - [ ] Patient ID number
        - [ ] Patient Illnesses
        - [ ] Patient Age
        - [ ] Assigned Room
        - [ ] Time of Admittance
    - [ ] Methods
        - [ ] Admit ER patient
        - [ ] Find ER patient
        - [ ] Add Patient Info
        - [ ] Edit Patient Info
- [ ] Patient Billing - More detailed Patient insurance information
    - [ ] Attributes
        - [ ] Items in bill
        - [ ] Total
        - [ ] Due Date
        - [ ] Patient Name
        - [ ] Discounts
    - [ ] Methods
        - [ ] Get Bill
        - [ ] Pay Bill
        - [ ] Give Discount
        - [ ] Bill Summary
- [ ] Appointment
  - [ ] Attributes
    - [ ] Open/Filled
    - [ ] Time
    - [ ] Patient
    - [ ] Physician
    - [ ] Type
    - [ ] Location
  - [ ] Methods
    - [ ] Update Appointment
    - [ ] Get Appt Time
    - [ ] Get Appt Patient
    - [ ] Get Appt Physician
    - [ ] Get Appt Location
- [ ] Appointment Database
  - [ ] Attributes
    - [ ] ArrayList of Filled Appointments
    - [ ] Array List of Open Appointments
    - [ ] Number of Appointments
    - [ ] Next Appointment
  - [ ] Methods
    - [ ] Show Open Appointment
    - [ ] Search Patient Appointments
    - [ ] Search Physician Appointments
    - [ ] Add Appointment
    - [ ] Delete Appointment

## Task 2

In MIS, inheritance hierarchies can be developed for many of its different features. For example, an inheritance hierarchy could be useful in the identification of different types of patients, or personnel (medical vs. non-medical), or distinguishing between the various types of medical personnel (nurses, doctors, lab technicians, nurse practitioners, therapists). They could also be valuable in keeping track of the different types of appointments (routine physicals, sick visits, follow ups, immunization visits, and so on). Another possibility is to keep track of the different types of medical facilities. In this assignment, you will build two such inheritance hierarchies (both hierarchies cannot be about the personnel) You must have between 8-10 classes in your final hierarchy, and each should be at least 2 levels deep (3 including the parent class). For each diagram (2 total), you should clearly define what each class in your solution represents by providing a one sentence description. For each inheritance hierarchy, clearly describe the rationale behind organizing the classes in that hierarchy.

### Task 2 Solution
