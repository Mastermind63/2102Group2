public class Patient {
    public enum InsuranceType{PRIVATE, GOVERNMENT}
    public enum PatientType{PEDIATRIC, ADULT, GERIATRIC}
    class MedicalConditions {
        public enum Allergies{FOOD, MEDICATION, SEASONAL, NONE, OTHER}
        public enum Illnesses{DIABETES, CHD, ASTHMA, NONE, OTHER}

        private String physName, physNumber;
        private Allergies allergies;
        private Illnesses illnesses;

        public MedicalConditions(String physName, String physNumber, Allergies allergies, Illnesses illnesses) {
            this.physName = physName;
            this.physNumber = physNumber;
            this.allergies = allergies;
            this.illnesses = illnesses;
        }

        public String getPhysName() {
            return physName;
        }
        public void setPhysName(String physName) {
            this.physName = physName;
        }

        public String getPhysNumber() {
            return physNumber;
        }
        public void setPhysNumber(String physNumber) {
            this.physNumber = physNumber;
        }

        public Allergies getAllergies() {
            return allergies;
        }
        public void setAllergies(Allergies allergies) {
            this.allergies = allergies;
        }

        public Illnesses getIllnesses() {
            return illnesses;
        }
        public void setIllnesses(Illnesses illnesses) {
            this.illnesses = illnesses;
        }
    }


    private String lastName, firstName, address, phoneNum, DoB;
    private MedicalConditions medConditions;
    private InsuranceType insuranceType;
    private PatientType patientType;
    private float coPay;

    // Constructor
    public Patient(String tLastName, String tFirstName, String tAddress, String tPhoneNum, String tDoB, InsuranceType tInsuranceType, float tCoPay, PatientType tPatientType, String tPhysName, String tPhysNumber, MedicalConditions.Allergies tAllergies, MedicalConditions.Illnesses tIllnesses){
        lastName = tLastName;
        firstName = tFirstName;
        address = tAddress;
        phoneNum = tPhoneNum;
        DoB = tDoB;
        insuranceType = tInsuranceType;
        coPay = tCoPay;
        patientType = tPatientType;
        medConditions = new MedicalConditions(tPhysName, tPhysNumber, tAllergies, tIllnesses);
    }

    // Getter & Setter Methods
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDoB() {
        return DoB;
    }
    public void setDoB(String doB) {
        DoB = doB;
    }

    public float getCoPay() {
        return coPay;
    }
    public void setCoPay(float coPay) {
        this.coPay = coPay;
    }


    // Updates
}
