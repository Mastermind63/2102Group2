public class Patient {
    private String lastName, firstName, address, phoneNum, DoB;
    private enum insuranceType{PRIVATE, GOVERNMENT}
    private enum PatientType{PEDIATRIC, ADULT, GERIATRIC}
    private float coPay;

    public Patient(String tLastName, String tFirstName, String tAddress, String tPhoneNum, String tDoB, String tInsuranceType, float tCoPay, String tPatientType){
        lastName = tLastName;
        firstName = tFirstName;
        address = tAddress;

    }

    class MedicalConditions{
        private String physName, physNumber;
        private enum allergies{FOOD, MEDICATION, SEASONAL, NONE, OTHER}
    }   private enum illnesses{DIABETES, CHD, ASTHMA, NONE, OTHER}
}
