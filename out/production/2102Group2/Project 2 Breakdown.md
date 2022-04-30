Project 2 Breakdown

# 2102 Swapna Collaboration Project

## Team Members
- Jared Moore
- Maximillian Mueller
- Benjamin Nowacki

Project 2 Breakdown

# 2102 Swapna Collaboration Project

## Team Members
- Jared Moore
- Maximillian Mueller
- Benjamin Nowacki

## Project Definition
- Patient
	- Teammember
		- Name: Jared Moore
		- Rationale behind split: Even Split
		- Difficulties encountered: Storing Enumerated Types
		- What you would do differently:
	- Time
		- Time Spent on implementation: 8 hours
		- Time Spent on testing: 2 Hours
		- Time Spent on integration: 4 Hours
	- Method's and Attributes
        - [x]   Class Attributes
            - [x]   Last Name
            - [x]   First Name
            - [x]   Address
            - [x]   Phone Number
            - [x]   DOB
            - [x]   Insurance Type
            - [x]   Co-Pay
            - [x]   Patient Type
        - [x]   Medical Conditions
            - [x]   Name of physician
            - [x]   Contact phone # of Physician
            - [x]   Allergies
            - [x]   Illnesses
        - [x]   Constructor
        - [x]   "get" Functions
            - [x]   Get Last Name
            - [x]   Get First Name
            - [x]   Get Address
            - [x]   Get Phone
            - [x]   Get DOB
            - [x]   Get Insurance
            - [x]   Get Co-pay
            - [x]   Get Patient Type
            - [x]   Get Phys Name
            - [x]   Get Contact #
            - [x]   Get Allergies
            - [x]   Get Illnesses
        - [x]   "update" Functions
            - [x]   Update First Name
            - [x]   Update Address
            - [x]   Update Phone
            - [x]   Update DOB
            - [x]   Update Insurance
            - [x]   Update Co-pay
            - [x]   Update Patient Type
            - [x]   Update Phys Name
            - [x]   Update Contact #
            - [x]   Update Allergies
            - [x]   Update Illnesses
        - [x] Print all Vars at once
- Patient Database
	- Teammember
		- Name: Benjamin Nowaki
		- Rationale behind split: even split
		- Difficulties encountered: Interfacing the systems, finding individuals on attributes
		- What you would do differently: Start the project slightly earlier
	- Time
		- Time Spent on implementation: 6 hours
		- Time Spent on testing: 4 hours
		- Time Spent on integration: 3 hours
	- Method's and Attributes
		- [x] Pull From File
		- [x]   Insert Profile
		- [x]   Delete Profile
		- [x]   Update Profile
		- [x]   Find/Display Profile
		- [x]   Summary Reports
- Patient Profile Interface
<<<<<<< HEAD
	- Teammember
		- Name: Maximillain Mueller
		- Rationale behind split: Even Split
		- Difficulties encountered: Buttons meshing up to backend portion
		- What you would do differently: 
	- Time
		- Time Spent on implementation: 8 hours
		- Time Spent on testing: 2 hours
		- Time Spent on integration: 3 hours
	- Method's and Attributes
		- [x]   Enter a New Patient
		- [x]   Delete a patient
		- [x]   Find & Display a patient
		- [x]   Find/Display Profile
		- [x]   Modify a patient Profile
		- [x]   Search the Database
=======
    - Teammember
        - Name
        - Rationale behind split
        - Difficulties encountered
        - What you would do differently
    - Time
        - Time Spent on implementation:
        - Time Spent on testing:
        - Time Spent on integration:
    - Method's and Attributes
        - [x]   Enter a New Patient
            - [x] Menu to enter profile data
            - [x] Menu to enter medical data
            - [x] Button save all data
                - [x] Displays message stating successful addition of patient  
        - [x]   Delete a patient
            - User supplies last name & dob
            - [x] Displays message stating successful deletion of patient
        - [x]   Find & Display a patient
            - User supplies last name & dob
            - [x] Entire patient profile is displayed
        - [x]   Find/Display Profile
        - [x]   Modify a patient Profile
            - Display profile, select attribute to modify, prompt user to enter new value
            - [x] Button to save update, then display new profile
        - [x]   Search the Database
            - [x] Present menu of 5 attributes: doctor, insurance, patient type, allergy, illness
            - [x] Display summary report for selected attribute
                - [x] Summary report displays name & phone num (not whole profile) 
>>>>>>> main

## Test Plan
This is our plan for testing... To be modified.
- Testing of Completeness:
  - [x] Patient Class
    - [x] Stores all Necessary information
    - [x] Uses enumerated types
    - [x] Tested Get Functions
    - [x] Tested Update Functions
    - [x] Tested Init Functions
    - [x] Tested Constructor
  - [x] Patient Database
    - [x] Import Functionality
    - [x] Export Functionality
    - [ ] 
  - [x] GUI Interface
    - [x] Interface Accepts database File (done as a file entered, read the Readme.md)
    - [x] Loop Prompt for action
    - [x] GUI Exit Option (top right x box)
- Testing of Functionality
  - [x] Bulk Load Patients
    - [x] CLI
  - [x] Delete a Patient
    - [x] CLI
    - [x] GUI
  - [x] Find and Display Patient
    - [x] CLI
    - [x] GUI
  - [x] Modify a Patient Profile
    - [x] CLI
    - [x] GUI
  - [x] Search Database (only name and phone dispayed)
    - [x] CLI
    - [x] GUI
- Issues Encountered:
  - [x] The system seems to run through the data and replace the copays with their values in floats
  - [x] Enumerated Type drop down lists in GUI and MIS
  - [ ] 