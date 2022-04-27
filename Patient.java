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
        public void updatePhysName(String physName){
            setPhysName(physName);
        }

        public String getPhysNumber() {
            return physNumber;
        }
        public void setPhysNumber(String physNumber) {
            this.physNumber = physNumber;
        }
        public void updatePhysNumber(String physNumber) {
            setPhysNumber(physNumber);
        }

        public Allergies getAllergies() {
            return allergies;
        }
        public void setAllergies(Allergies allergies) {
            this.allergies = allergies;
        }
        public void updateAllergies(String allergies){
            if(allergies == "Food"){
                this.allergies = Allergies.FOOD;
            } else if (allergies == "Medication"){
                this.allergies = Allergies.MEDICATION;
            } else if (allergies == "None") {
                this.allergies = Allergies.NONE;
            } else if (allergies == "Seasonal"){
                this.allergies = Allergies.SEASONAL;
            } else if (allergies == "Other"){
                this.allergies = Allergies.OTHER;
            } else {
                ErrorMessage("Allergy must be of type: Food, Medication, Seasonal, Other, None");
            }
        }

        public Illnesses getIllnesses() {
            return illnesses;
        }
        public void setIllnesses(Illnesses illnesses){
            this.illnesses = illnesses;
        }
        public void updateIllnesses(String illnesses){
            if(illnesses == "Diabetes"){
                this.illnesses = Illnesses.DIABETES;
            } else if(illnesses == "CHD"){
                this.illnesses = Illnesses.CHD;
            } else if(illnesses == "None"){
                this.illnesses = Illnesses.NONE;
            } else if(illnesses == "Asthma"){
                this.illnesses = Illnesses.ASTHMA;
            } else if(illnesses == "Other") {
                this.illnesses = Illnesses.OTHER;
            } else {
                ErrorMessage("Illness not recognized, illness must be of type:" +
                        " Diabetes, CHD, Asthma, Other, None");
            }
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
    public void updateLastName(String lastName){
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void updateFirstName(String firstName){
        setFirstName(firstName);
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void updateAddress(String address){
        setAddress(address);
    }


    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void updatePhoneNum(String phoneNum){
        setPhoneNum(phoneNum);
    }

    public String getDoB() {
        return DoB;
    }
    public void setDoB(String doB) {
        DoB = doB;
    }
    public void updateDoB(String doB){
        setDoB(doB);
    }

    public InsuranceType getInsuranceType(){
        return insuranceType;
    }
    public void setInsuranceType(InsuranceType insType){
        this.insuranceType = insType;
    }
    public void updateInsuranceType(String insType){
        if (insType.equals("Government")) {
            this.insuranceType = InsuranceType.GOVERNMENT;
        }else if (insType.equals("Private")) {
            this.insuranceType = InsuranceType.PRIVATE;
        } else {
            ErrorMessage("Patient type must be: Government, Private");
        }
    }

    public PatientType getPatientType() {
        return patientType;
    }
    public void setPatientType(PatientType patientType){
        this.patientType = patientType;
    }
    public void updatePatientType(String patientType){
        if (patientType == "Pediatric"){
            this.patientType = PatientType.PEDIATRIC;
        } else if (patientType == "Adult"){
            this.patientType = PatientType.ADULT;
        } else if (patientType == "Geriatric"){
            this.patientType = PatientType.GERIATRIC;
        } else {
            ErrorMessage("Patient type must be: Geriatric, Adult, Pediatric");
        }
    }

    public float getCoPay() {
        return coPay;
    }
    public void setCoPay(float coPay) {
        this.coPay = coPay;
    }
    public void updateCoPay(float coPay){
        setCoPay(coPay);
    }

    public MedicalConditions getMedConditions() {
        return this.medConditions;
    }
    public void setMedConditions(MedicalConditions medConditions) {
        this.medConditions = medConditions;
    }


    // Error Message
    public String ErrorMessage(String Error){
        String ErrorString = "Error: " + Error;
        System.out.println(ErrorString);
        return ErrorString;
    }

    // Print Patient Variables
    public String PrintPatient(){
        System.out.println("Patient Report: \n");
        System.out.println("Patient Name: " + getLastName() + ", " + getFirstName()+"\n");
        System.out.println("Patient DOB: " + getDoB() + " Patient Type: " + getPatientType()+"\n");
        System.out.println("Patient Address: " + getAddress()+" Patient Phone #: "+ getPhoneNum() +"\n");
        System.out.println("Copay:" + getCoPay() + " Insurance Type: "+ getInsuranceType()+"\n");

        return("<html> Patient Report: <br/>" +
                "Patient Name: " + getLastName() + ", " + getFirstName()+"<br/>" +
                "Patient DOB: " + getDoB() + " Patient Type: " + getPatientType()+"<br/>" +
                "Patient Address: " + getAddress()+" Patient Phone #: "+ getPhoneNum() +"<br/>" +
                "Copay:" + getCoPay() + " Insurance Type: "+ getInsuranceType()+"<br/>");
    }

    // Print only name & dob
    public String PrintSummary() {
        System.out.println("Patient Name: " + getLastName() + ", " + getFirstName());
        System.out.println("Patient DOB: " + getDoB() + "\n");
        return("Patient Name: " + getLastName() + ", " + getFirstName() + "<br/>" +
                "Patient DOB: " + getDoB() + "<br/> <br/>");
    }
}
