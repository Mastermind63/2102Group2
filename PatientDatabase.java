import org.w3c.dom.Attr;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class PatientDatabase {
    public enum AttributeTypes {
        DOCTOR, INSURANCE, PATIENT_TYPE, ALLERGY, ILLNESS
    };

    ArrayList<Patient> patientDB = new ArrayList<Patient>();

    // accepts name of file from which patient profiles will be read
    // filename provided to system at startup via interface
    // store profiles in same file when exiting
    public PatientDatabase (String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening the file: " + filename);
            System.exit(0);
        }

        while (file.hasNextLine()) {
            // assume every patient has same num lines (12)?
            String firstName = file.nextLine();
            String lastName = file.nextLine();
            String address = file.nextLine();
            String phoneNum = file.nextLine();
            String DoB = file.nextLine();

            Patient.InsuranceType insuranceType = null;
            switch (file.nextLine()) {
                case "Private":
                    insuranceType = Patient.InsuranceType.PRIVATE;
                    break;
                case "Government":
                    insuranceType = Patient.InsuranceType.GOVERNMENT;
                    break;
                default:
                    System.out.println("error reading insurance type");
                    break;
            }

            float copay = Float.parseFloat(file.nextLine());

            Patient.PatientType patientType = null;
            switch (file.nextLine()) {
                case "Pediatric":
                    patientType = Patient.PatientType.PEDIATRIC;
                    break;
                case "Adult":
                    patientType = Patient.PatientType.ADULT;
                    break;
                case "Geriatric":
                    patientType = Patient.PatientType.GERIATRIC;
                    break;
                default:
                    System.out.println("error reading patient type");
                    break;
            }

            String physName = file.nextLine();
            String physPhone = file.nextLine();

            Patient.MedicalConditions.Allergies allergy = null;
            switch (file.nextLine()) {
                case "Food":
                    allergy =  Patient.MedicalConditions.Allergies.FOOD;
                    break;
                case "Medication":
                    allergy =  Patient.MedicalConditions.Allergies.MEDICATION;
                    break;
                case "Seasonal":
                    allergy =  Patient.MedicalConditions.Allergies.SEASONAL;
                    break;
                case "None":
                    allergy =  Patient.MedicalConditions.Allergies.NONE;
                    break;
                case "Other":
                    allergy =  Patient.MedicalConditions.Allergies.OTHER;
                    break;
                default:
                    System.out.println("error reading allergy");
                    break;
            }

            Patient.MedicalConditions.Illnesses illness = null;
            switch (file.nextLine()) {
                case "Diabetes":
                    illness = Patient.MedicalConditions.Illnesses.DIABETES;
                    break;
                case "CHD":
                    illness = Patient.MedicalConditions.Illnesses.CHD;
                    break;
                case "Asthma":
                    illness = Patient.MedicalConditions.Illnesses.ASTHMA;
                    break;
                case "None":
                    illness = Patient.MedicalConditions.Illnesses.NONE;
                    break;
                case "Other":
                    illness = Patient.MedicalConditions.Illnesses.OTHER;
                    break;
                default:
                    System.out.println("error reading illness");
                    break;
            }


            Patient patient = new Patient(lastName, firstName, address, phoneNum, DoB, insuranceType, copay, patientType, physName, physPhone, allergy, illness);
            insertProfile(patient);
        }
        file.close();
    }

    public void insertProfile(Patient newPatient) {
        // GUI -- prompt user for profile info & med conditions info
        //   create new patient via Patient() constructor
        //   then pass to this method

        patientDB.add(newPatient);
        updateDatabase();
    }

    public void deleteProfile(String lastName, String DoB) {
        int patientIndex = getPatientIndex(lastName, DoB);
        if (patientIndex == -1) {
            ErrorMessage("Patient does not exist.");
        } else {
            patientDB.remove(patientIndex);
            updateDatabase();
        }
    }

    // TODO: need way of passing textInput from GUI to method
    public String updateProfile(String lastName, String DoB, PatientProfileInterface gui) {
        Patient patient = patientDB.get(getPatientIndex(lastName, DoB));

        // GUI will need to display profile with option for user to select which attribute to modify
        // when attributes are all modified, have button (or other) to pass new values
        boolean[] boolArray = gui.isChecked();

        if (boolArray[0])patient.setFirstName(gui.getUpFirst());
        if (boolArray[1])patient.setLastName(gui.getUpLast());
        if (boolArray[2])patient.setAddress(gui.getUpAddress());
        if (boolArray[3])patient.setPhoneNum(gui.getUpNumber());
        if (boolArray[4])patient.setInsuranceType(gui.getUpInsurance());
        if (boolArray[5])patient.setCoPay(gui.getUpCopay());
        if (boolArray[6])patient.setPatientType(gui.getUpPatientType());

        //TODO: this doesn't work with how I implemented this
        //(boolArray[0])patient.setMedConditions(new Patient.MedicalConditions(guiInput, guiInput, guiInput, guiInput));

        updateDatabase();   // update database with new patient data
        if (boolArray[1]){
            return displayProfile(gui.getUpLast(), DoB);
        }else{
            return displayProfile(lastName, DoB);
        }
    }

    public String displayProfile(String lastName, String DoB) {
        if (getPatientIndex(lastName, DoB) != -1) {
        Patient patient = patientDB.get(getPatientIndex(lastName, DoB));
        return patient.PrintPatient();   // this will need to call so GUI function and/or pass the patient reference
        } else {
            return ErrorMessage("Patient does not exist");
        }
    }

    public ArrayList<Patient> getPatientByAttribute(AttributeTypes attribute, String attrValue) {
        // pass an attribute and a value of that attribute
        //    ex: getPatientByAttribute(DOCTOR, "Jon Doe") will return list of patients who have Jon Doe as their doctor
        ArrayList<Patient> patientsWithAttribute = new ArrayList<Patient>();

        for (int i = 0; i < patientDB.size(); i ++) {
            Patient curPatient = patientDB.get(i);

            switch (attribute) {
                case DOCTOR:
                    if (curPatient.getMedConditions().getPhysName().equals(attrValue)) {
                        patientsWithAttribute.add(curPatient);
                    }
                    break;
                case INSURANCE:
                    if (attrValue.equals("Private")) {
                        if (curPatient.getInsuranceType() == Patient.InsuranceType.PRIVATE) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Government")) {
                        if (curPatient.getInsuranceType() == Patient.InsuranceType.GOVERNMENT) {
                            patientsWithAttribute.add(curPatient);
                        }
                    }
                    break;
                case PATIENT_TYPE:
                    if (attrValue.equals("Adult")) {
                        if (curPatient.getPatientType() == Patient.PatientType.ADULT) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Geriatric")) {
                        if (curPatient.getPatientType() == Patient.PatientType.GERIATRIC) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Pediatric")) {
                        if (curPatient.getPatientType() == Patient.PatientType.PEDIATRIC) {
                            patientsWithAttribute.add(curPatient);
                        }
                    }
                    break;
                case ALLERGY:
                    if (attrValue.equals("Food")) {
                        if (curPatient.getMedConditions().getAllergies() == Patient.MedicalConditions.Allergies.FOOD) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Medication")) {
                        if (curPatient.getMedConditions().getAllergies() == Patient.MedicalConditions.Allergies.MEDICATION) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Seasonal")) {
                        if (curPatient.getMedConditions().getAllergies() == Patient.MedicalConditions.Allergies.SEASONAL) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("None")) {
                        if (curPatient.getMedConditions().getAllergies() == Patient.MedicalConditions.Allergies.NONE) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Other")) {
                        if (curPatient.getMedConditions().getAllergies() == Patient.MedicalConditions.Allergies.OTHER) {
                            patientsWithAttribute.add(curPatient);
                        }
                    }
                    break;
                case ILLNESS:
                    if (attrValue.equals("CHD")) {
                        if (curPatient.getMedConditions().getIllnesses() == Patient.MedicalConditions.Illnesses.CHD) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Asthma")) {
                        if (curPatient.getMedConditions().getIllnesses() == Patient.MedicalConditions.Illnesses.ASTHMA) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Diabetes")) {
                        if (curPatient.getMedConditions().getIllnesses() == Patient.MedicalConditions.Illnesses.DIABETES) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("None")) {
                        if (curPatient.getMedConditions().getIllnesses() == Patient.MedicalConditions.Illnesses.NONE) {
                            patientsWithAttribute.add(curPatient);
                        }
                    } else if (attrValue.equals("Other")) {
                        if (curPatient.getMedConditions().getIllnesses() == Patient.MedicalConditions.Illnesses.OTHER) {
                            patientsWithAttribute.add(curPatient);
                        }
                    }
                    break;
                default:
                    System.out.println("Not a valid attribute");
                    break;
            }
        }
        return patientsWithAttribute;
    }

    // TODO: need to pass/display patient list in GUI
    public void getSummary(AttributeTypes attribute, String attrValue, PatientProfileInterface gui) {
        ArrayList<Patient> patientsWithAttribute = getPatientByAttribute(attribute, attrValue);
        gui.setDisplayLabel("<html> ");
        // need to display all patients in above arraylist (only name and phone number)
        System.out.println("Patients with AttributeType: " + attribute.toString() + " with value: " + attrValue);
        for (int i = 0; i < patientsWithAttribute.size(); i ++) {
            Patient patient = patientsWithAttribute.get(i);
            gui.appendDisplayLabel(patient.PrintSummary());
        }
        gui.appendDisplayLabel(" <html>");
    }

    public void getSummary() {
        for (int i = 0; i < patientDB.size(); i ++) {
            Patient patient = patientDB.get(i);
            patient.PrintSummary();
        }
    }



    public Patient getPatient (String lastName, String DoB) {
        for (int i = 0; i < patientDB.size(); i ++) {
            if (patientDB.get(i).getLastName().equals(lastName) && patientDB.get(i).getDoB().equals(DoB)) {
                return patientDB.get(i);
            }
        }
        return null; // patient not found
    }
    private int getPatientIndex (String lastName, String DoB) {
        for (int i = 0; i < patientDB.size(); i ++) {
            if (patientDB.get(i).getLastName().equals(lastName) && patientDB.get(i).getDoB().equals(DoB)) {
                return i;
            }
        }
        return -1; // patient not found
    }

    private void updateDatabase() {
        String fileName = "ProfilesF.txt";    // just for testing ... will need to overwrite "Profiles.txt" (save backup copies?)
        PrintWriter outputFile = null;
        try {
            outputFile = new PrintWriter(fileName);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening the file: " + fileName);
            System.exit(0);
        }

        // go through each Patient in patientDB
        for (int i =  0; i < patientDB.size(); i ++) {
            Patient curPatient = patientDB.get(i);

            outputFile.println(curPatient.getFirstName());
            outputFile.println(curPatient.getLastName());
            outputFile.println(curPatient.getAddress());
            outputFile.println(curPatient.getPhoneNum());
            outputFile.println(curPatient.getDoB());
            outputFile.println(curPatient.getCoPay());
            switch(curPatient.getPatientType())  {
                case ADULT:
                    outputFile.println("Adult");
                    break;
                case GERIATRIC:
                    outputFile.println("Geriatric");
                    break;
                case PEDIATRIC:
                    outputFile.println("Pediatric");
                    break;
                default:
                    System.out.println("error writing to file (patient type)");
                    break;
            }
            outputFile.println(curPatient.getMedConditions().getPhysName());
            outputFile.println(curPatient.getMedConditions().getPhysNumber());
            switch(curPatient.getMedConditions().getAllergies()) {
                case FOOD:
                    outputFile.println("Food");
                    break;
                case MEDICATION:
                    outputFile.println("Medication");
                    break;
                case SEASONAL:
                    outputFile.println("Seasonal");
                    break;
                case NONE:
                    outputFile.println("None");
                    break;
                case OTHER:
                    outputFile.println("Other");
                    break;
                default:
                    System.out.println("error writing to file (allergies)");
                    break;
            }
            switch(curPatient.getMedConditions().getIllnesses()) {
                case DIABETES:
                    outputFile.println("Diabetes");
                    break;
                case CHD:
                    outputFile.println("CHD");
                    break;
                case ASTHMA:
                    outputFile.println("Asthma");
                    break;
                case NONE:
                    outputFile.println("None");
                    break;
                case OTHER:
                    outputFile.println("Other");
                    break;
                default:
                    System.out.println("error writing to file (illnesses)");
                    break;
            }
        }
        outputFile.close();

    }



    // Error Message
    public String ErrorMessage(String Error){
        String ErrorString = "Error: PatientDatabase: " + Error;
        System.out.println(ErrorString);
        return ErrorString;
    }


    // for testing, will delete later
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter filename: ");
        String filename = sc.nextLine();

        PatientDatabase db = new PatientDatabase(filename);

        int curState = 0;
        while (curState != -1) {

            if (curState == 0)  {
                System.out.println("\n*** Menu ***");
                System.out.println("(1) Insert Profile");
                System.out.println("(2) Delete Profile");
                System.out.println("(3) Update Profile");
                System.out.println("(4) Display Profile");
                System.out.print("Enter number selection (enter \"EXIT\" to exit)");

                switch (sc.nextLine()) {
                    case "1":
                        curState = 1;
                        break;
                    case "2":
                        curState = 2;
                        break;
                    case "3":
                        curState = 3;
                        break;
                    case "4":
                        curState = 4;
                        break;
                    case "EXIT":
                        curState = -1;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Not an option. Please try again.");
                        break;
                }
            }

            else if (curState == 1) {
                System.out.println("\n*** Insert Profile (\"EXIT\" to exit) ***");
                curState = 0;
            }
            else if (curState == 2)  {
                System.out.println("\n*** Delete Profile (\"EXIT\" to exit) ***");
                curState = 0;
            }
            else if (curState == 3) {
                System.out.println("\n*** Update Profile (\"EXIT\" to exit) ***");

                System.out.print("Enter Last Name of Patient to Update: ");
                String lastName = sc.nextLine();
                if (lastName.equals("EXIT")) {
                    curState = -1;
                    System.exit(0);
                }
                System.out.print("Enter Date of Birth of Patient to Update: ");
                String dob = sc.nextLine();
                if (dob.equals("EXIT")) {
                    curState = -1;
                    System.exit(0);
                }

                Patient patient = db.getPatient(lastName, dob);
                System.out.print("Field to Update (lastName, firstName, address, phoneNumber): ");
                String field = sc.nextLine();
                switch (field) {
                    case "lastName":
                        System.out.print("Enter new lastName: ");
                        patient.setLastName(sc.nextLine());
                        patient.PrintPatient();
                        db.updateDatabase();
                        break;
                    case "firstName":
                        System.out.print("Enter new firstName: ");
                        patient.setFirstName(sc.nextLine());
                        System.out.println("New first name is: " +  patient.getFirstName());
                        break;
                    case "address":
                        System.out.print("Enter new address: ");
                        patient.setAddress(sc.nextLine());
                        System.out.println("New address is: " +  patient.getAddress());
                        break;
                    case "phoneNumber":
                        System.out.print("Enter new phoneNumber: ");
                        patient.setPhoneNum(sc.nextLine());
                        System.out.println("New phoneNumber is: " +  patient.getPhoneNum());
                        break;
                    case "EXIT":
                        db.updateDatabase();
                        curState = -1;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Not a valid field");
                        break;
                }
            }



        }
        System.exit(0);
    }

}
