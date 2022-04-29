# 2102Group2

### How to Start System:
* In terminal:
  * Run "PatientProfileInterface.java"
  * User will be prompted to enter the text file where the patient info is stored
    * To use the test cases provided to the class, enter "Profiles.txt" as the filename (without quotes)
* A new window for the Medical Info System (MIS) should launch.

### How to Use MIS
* On startup, a window will appear with six buttons:
  1. "New Patient"
  2. "Delete Patient"
  3. "Find Patient"
  4. "Modify Patient"
  5. "Search Database"
  6. "Exit"
1. "New Patient"
   * Clicking will switch to a new window where the patient info can be entered.
   * All fields must be entered before you can proceed to the next window. The value for "Co-Pay" must be a number.
   * Clicking "Next" will open a new window to enter the medical info of the patient.
   * All fields must be entered before being able to click "Submit"
   * Clicking "Submit" will once again show a new window. This window shows a full overview of the newly entered patient's info including the medical conditions.
   * "Return to Main Menu"  will return the user to the initial menu page.
2. "Delete Patient"
   * Clicking will switch to a new window prompting the user to enter the lastname and date of birth of the user to be deleted.
   * Once the info is entered, "Delete Patient" can be clicked.
     * If the patient does exist in the database, the patient is removed from the database and a message will be displayed stating, "Successfully Deleted Patient".
     * If the patient does not exist, a message is shown stating "Delete Operation Failed".
   * The user can then return to the main menu.
3. "Find Patient"
   * Clicking will switch to a new window prompting the user to enter the lastname and date of birth of the user to find.
   * Once the info is entered, "Find Patient" can be clicked.
     * If the patient does exist, a new window will be shown with a full overview of the patient's info including the medical conditions.
     * If the patient does not exist, a message is shown stating "Error: Patient not found."
   * The user can then return to the main menu.
4. "Modify Patient"
   * Clicking will switch to a new window.
   * The user is prompted to enter the lastname and date of birth of the user to modify.
   * The user is also asked to check all the attributes they wish to modify.
   * Clicking "Next" opens a new window providing text fields for the user to enter new values for the previously selected attributes.
   * "Update Patient" can then be clicked.
     * If the patient does exist, a new window will be shown with a full overview of the patient's new info including the medical conditions.
     * If the patient does not exist, a message is shown stating "Error: Patient not found."
   * The user can then return to the main menu.
5. "Search Database"
   * The user is prompted to select an attribute to search by (Doctor, Insurance, Patient Type, Illness, Allergy, Any Attribute)
   * The user is then asked to enter a value for the chosen attribute.
   * "Search Database" will generate a scrollable list of all patients matching the chosen attribute.
     * Only the patient's name and date of birth is shown for each matching patient.
   * Note for "Any Attribute", the search will return a list of all patients in the database regardless of teh specific attribute value entered.
   * The user can then return to the main menu
6. "Exit"
   * This will close the system.
   * All patient data is saved upon closing and will be reloaded providing that the same filename is entered on the next startup (see the section "How to Start System").
 
