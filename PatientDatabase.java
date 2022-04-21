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
            String insuranceType = file.nextLine();
            float copay = Float.parseFloat(file.nextLine());
            String patientType = file.nextLine();

            String physName = file.nextLine();
            String phsyPhone = file.nextLine();
            String allergy = file.nextLine();
            String illness = file.nextLine();

            Patient patient = new Patient(lastName, firstName, address, phoneNum, DoB, insuranceType, copay, patientType);
            insertProfile(patient);
        }
        file.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter filename: ");
        String filename = sc.nextLine();

        PatientDatabase db = new PatientDatabase(filename);

        System.out.println("Menu:");
        System.out.println("Enter '1' for ");
        System.exit(0);
    }


    // iterative searches of database
    //    search to compile and print summary reports
    //    assume profile uniquely defined by last name & dob
    //    also want to be able to search via other attributes in order to create summary reports

    public void insertProfile(Patient newPatient) {
        // add profile to array list
        patientDB.add(newPatient);
        numPatients += 1;
        System.out.println("Patient Added: " + newPatient.lastName);
    }

    public void deleteProfile(String lastName, String DoB) {

    }

    public void updateProfile(String lastName, String DoB) {
        // DoB cannot be updated once profile created
    }

    public void displayProfile(String lastName, String DoB) {

    }



}
