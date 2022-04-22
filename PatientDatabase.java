import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class PatientDatabase {

    int numPatients;
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
            String lastName = file.nextLine();
            String firstName = file.nextLine();
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
            String phsyPhone = file.nextLine();

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


            Patient patient = new Patient(lastName, firstName, address, phoneNum, DoB, insuranceType, copay, patientType, physName, physName, allergy, illness);
            insertProfile(patient);
        }
        file.close();

        System.out.println("Database Loaded");
        System.out.println("There are " + numPatients + " patient profiles saved.\n");
    }




    // iterative searches of database
    //    search to compile and print summary reports
    //    assume profile uniquely defined by last name & dob
    //    also want to be able to search via other attributes in order to create summary reports

    public void insertProfile(Patient newPatient) {
        // add profile to array list
        patientDB.add(newPatient);
        numPatients += 1;
        System.out.println("Patient Added: " + newPatient.getLastName());
    }

    public void deleteProfile(String lastName, String DoB) {
        int patientIndex = getPatientIndex(lastName, DoB);
        if (patientIndex == -1) {
            System.out.println("Patient does not exist.");
        } else {
            patientDB.remove(patientIndex);
            numPatients -= 1;
        }
    }

    public void updateProfile(String lastName, String DoB) {
        Patient patient = patientDB.get(getPatientIndex(lastName, DoB));

        // how to  determine which properties to set? (can't update DoB)
        /*
        patient.setLastName();
        ...
         */
    }

    public void displayProfile(String lastName, String DoB) {
        Patient patient = patientDB.get(getPatientIndex(lastName, DoB));

        System.out.println("Last Name of Patient: " + patient.getLastName());
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
                        System.out.println("New last name is: " +  patient.getLastName());
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
