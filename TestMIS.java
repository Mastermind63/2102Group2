import java.nio.DoubleBuffer;

public class TestMIS{

    public static void main(String[] args) {
        // test initialization
        PatientDatabase list = new PatientDatabase("Profiles.txt");
        list.getSummary();


        Patient newPatient = new Patient("LastName", "FirstName",
                "Address", "PhoneNum", "DOB",
                Patient.InsuranceType.PRIVATE, 1000, Patient.PatientType.PEDIATRIC,
                "Dr. Doctor", "Dr.PhoneNum",
                Patient.MedicalConditions.Allergies.FOOD, Patient.MedicalConditions.Illnesses.ASTHMA);


        // test insertProfile()
        System.out.println("\n\n\n*** TESTING INSERT PROFILE ***");
        list.insertProfile(newPatient);
        list.getSummary();

        // test deleteProfile()
        System.out.println("\n\n\n*** TESTING DELETE PROFILE ***");
        try {
            list.deleteProfile("LastName", "DOB");
        } catch (PatientNotFoundException e) {

        }
        list.getSummary();

        // test displayProfile()
        System.out.println("\n\n\n*** TESTING DISPLAY PROFILE ***");
        list.displayProfile("Munira", "1994-6-12");
        list.displayProfile("does not exist", "?");

        // test getSummary()
        System.out.println("\n\n\n*** TESTING GET SUMMARY ***");
        //TODO: Change to match new functionality of getSummary
        //list.getSummary(PatientDatabase.AttributeTypes.DOCTOR, "Alon Mayer");
        //list.getSummary(PatientDatabase.AttributeTypes.ILLNESS, "Asthma");
    }


}
