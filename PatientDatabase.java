import org.w3c.dom.Attr;

import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class PatientDatabase {
    public enum AttributeTypes {
        DOCTOR, INSURANCE, PATIENT_TYPE, ALLERGY, ILLNESS, ANY
    };

    ArrayList<Patient> patientDB = new ArrayList<Patient>();
    String filename = null;

    // accepts name of file from which patient profiles will be read
    // filename provided to system at startup via interface
    // store profiles in same file when exiting
    public PatientDatabase (String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
            this.filename = filename;
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
        patientDB.add(newPatient);
        updateDatabase();
    }

    public void deleteProfile(String lastName, String DoB) throws PatientNotFoundException {
        int patientIndex = getPatientIndex(lastName, DoB);
        if (patientIndex == -1) {
            ErrorMessage("Patient does not exist.");
            throw new PatientNotFoundException("Patient does not exist.");
        } else {
            patientDB.remove(patientIndex);
            updateDatabase();
        }
    }

    public String updateProfile(String lastName, String DoB, PatientProfileInterface gui) {
        try {
            Patient patient = patientDB.get(getPatientIndex(lastName, DoB));

            // GUI will need to display profile with option for user to select which attribute to modify
            // when attributes are all modified, have button (or other) to pass new values
            boolean[] boolArray = gui.isChecked();

            //if box was checked, update that value
            if (boolArray[0])patient.setFirstName(gui.getUpFirst());
            if (boolArray[1])patient.setLastName(gui.getUpLast());
            if (boolArray[2])patient.setAddress(gui.getUpAddress());
            if (boolArray[3])patient.setPhoneNum(gui.getUpNumber());
            if (boolArray[4])patient.setInsuranceType(gui.getUpInsurance());
            if (boolArray[5])patient.setCoPay(gui.getUpCopay());
            if (boolArray[6])patient.setPatientType(gui.getUpPatientType());
            if (boolArray[7])patient.getMedConditions().setPhysName(gui.getUpPhysName());
            if (boolArray[8])patient.getMedConditions().setPhysNumber(gui.getUpPhysNum());
            if (boolArray[9])patient.getMedConditions().setAllergies(gui.getUpAllergy());
            if (boolArray[10])patient.getMedConditions().setIllnesses(gui.getUpIllness());

            updateDatabase();   // update database with new patient data
            if (boolArray[1]){
                return displayProfile(gui.getUpLast(), DoB);
            } else{
                return displayProfile(lastName, DoB);
            }

        } catch (PatientNotFoundException e) {
            return ErrorMessage("Patient not found.");
        }



    }

    public String displayProfile(String lastName, String DoB){
        try {
            Patient patient = getPatient(lastName, DoB);
            return patient.PrintPatient();
        } catch (PatientNotFoundException e) {
            return ErrorMessage("Patient not found.");
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
                case ANY:
                    patientsWithAttribute.add(curPatient);
                    break;
                default:
                    System.out.println("Not a valid attribute");
                    break;
            }
        }
        return patientsWithAttribute;
    }

    public void getSummary(AttributeTypes attribute, String attrValue, PatientProfileInterface gui) {
        ArrayList<Patient> patientsWithAttribute = getPatientByAttribute(attribute, attrValue);
        gui.setDisplayLabel("<html> ");
        // need to display all patients in above arraylist (only name and phone number)
        System.out.println("Patients with AttributeType: " + attribute.toString() + " with value: " + attrValue);
        gui.appendDisplayLabel("Patients with AttributeType: " + attribute.toString() + " with value: " + attrValue + "<br/>");
        for (int i = 0; i < patientsWithAttribute.size(); i ++) {
            Patient patient = patientsWithAttribute.get(i);
            gui.appendDisplayLabel(patient.PrintSummary());
        }
        if (patientsWithAttribute.size() == 0){
            gui.appendDisplayLabel("No Patients Match Criteria <br/> Make sure value is spelled correctly (i.e. 'Government' and not 'GOVERNMENT')");
        }
        gui.appendDisplayLabel(" <html>");
    }

    public void getSummary() {
        for (int i = 0; i < patientDB.size(); i ++) {
            Patient patient = patientDB.get(i);
            patient.PrintSummary();
        }
    }



    public Patient getPatient (String lastName, String DoB) throws PatientNotFoundException {
        try {
            return patientDB.get(getPatientIndex(lastName, DoB));
        } catch (PatientNotFoundException e) {
            ErrorMessage("Patient not found.");
        }
        throw new PatientNotFoundException("Patient not found.");
    }
    private int getPatientIndex (String lastName, String DoB) throws PatientNotFoundException{
        for (int i = 0; i < patientDB.size(); i ++) {
            if (patientDB.get(i).getLastName().equals(lastName) && patientDB.get(i).getDoB().equals(DoB)) {
                return i;
            }
        }
        throw new PatientNotFoundException("Patient not found.");
    }

    public void updateDatabase() {
        PrintWriter outputFile = null;
        try {
            outputFile = new PrintWriter(filename);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening the file: " + filename);
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
            switch (curPatient.getInsuranceType()) {
                case PRIVATE:
                    outputFile.println("Private");
                    break;
                case GOVERNMENT:
                    outputFile.println("Government");
                    break;
                default:
                    System.out.println("error writing to file (insurance type)");
                    break;
            }
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
        String ErrorString = "Error: " + Error;
        System.out.println(ErrorString);
        return ErrorString;
    }
}
