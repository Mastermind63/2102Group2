public class Patient {
    private String lastName, firstName, address, phoneNum, DoB;
    private enum insuranceType{PRIVATE, GOVERNMENT}
    private enum PatientType{PEDIATRIC, ADULT, GERIATRIC}
    private float coPay;

    // Constructor
    public Patient(String tLastName, String tFirstName, String tAddress, String tPhoneNum, String tDoB, String tInsuranceType, float tCoPay, String tPatientType, String tphysName, String tphysNumber, String tallergies, String tillnesses){
        lastName = tLastName;
        firstName = tFirstName;
        address = tAddress;
        phoneNum = tPhoneNum;
        DoB = tDoB;
        insuranceType insType;
        if (tInsuranceType == "Private"){
            insType = insuranceType.PRIVATE;
        } else if (tInsuranceType == "Government") {
            insType = insuranceType.GOVERNMENT;
        }

        coPay = tCoPay;
        PatientType = tPatientType;

    }
    // Gets
    // Updates

    class MedicalConditions{
        private String physName, physNumber;
        private enum allergies{FOOD, MEDICATION, SEASONAL, NONE, OTHER}
        private enum illnesses{DIABETES, CHD, ASTHMA, NONE, OTHER}
        }
}
