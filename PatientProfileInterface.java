import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PatientProfileInterface {
    private JPanel PanelContainer;
    private JPanel MainPanel;
    private JButton ExitButton;
    private JButton NewPatientButton;
    private JPanel NewPatientPanel;
    private JPanel NewPatientPanel2;
    private JButton DeletePatientButton;
    private JButton FindPatientButton;
    private JButton ModifyPatientButton;
    private JButton DatabaseButton;
    private JButton toMedButton;
    private JTextField newLastText;
    private JTextField newFirstText;
    private JTextField newAddressText;
    private JTextField newNumText;
    private JTextField newDobText;
    private JTextField newCopayText;
    private JLabel LastLabel;
    private JLabel FirstLabel;
    private JLabel AddressLabel;
    private JLabel PnumLabel;
    private JLabel DoBLabel;
    private JLabel InsuranceLabel;
    private JLabel CopayLabel;
    private JLabel PtypeLabel;
    private JComboBox newTypeText;
    private JButton SubmitNewPatientButton;
    private JTextField newPhysNameText;
    private JTextField newPhysNumText;
    private JComboBox newAllergiesText;
    private JComboBox newIllnessText;
    private JLabel PhysLabel;
    private JLabel PhysContactLabel;
    private JLabel AllergiesLabel;
    private JLabel IllnessesLabel;
    private JPanel DisplayPanel;
    private JButton ReturnButton;
    private JPanel DeletePatientPanel;
    private JTextField DeleteNameText;
    private JLabel deleteLabel1;
    private JButton DeleteSubmitButton;
    private JLabel deleteLabel2;
    private JTextField DeleteDobText;
    private JLabel DisplayLabel;
    private JComboBox newInsuranceText;
    private JPanel FindPanel;
    private JLabel FindLastLabel;
    private JTextField FindLastText;
    private JButton FindSubmitButton;
    private JTextField FindDobText;
    private JLabel FindDobLable;
    private JPanel SearchPanel;
    private JComboBox SearchAttributeText;
    private JButton SearchSubmitButton;
    private JLabel SearchLabel;
    private JLabel SearchAttributeLabel;
    private JTextField SearchSpecifyText;
    private JPanel UpdatePanel1;
    private JCheckBox UpdateFirstCheck;
    private JCheckBox UpdateLastCheck;
    private JCheckBox UpdateNumberCheck;
    private JCheckBox UpdateInsuranceCheck;
    private JCheckBox UpdateCopayCheck;
    private JCheckBox UpdateAddressCheck;
    private JCheckBox UpdatePTCheck;
    private JButton UpdateButtonOne;
    private JLabel ChangeTitleLabel;
    private JCheckBox UpdatePhysNameCheck;
    private JCheckBox UpdatePhysNumCheck;
    private JCheckBox UpdateAllergyCheck;
    private JCheckBox UpdateIllnessCheck;
    private JPanel UpdatePanel2;
    private JButton UpdateSubmitButton;
    private JTextField UpdateFirstText;
    private JTextField UpdatePhysNameText;
    private JTextField UpdateLastText;
    private JTextField UpdatePhysNumText;
    private JTextField UpdateAddressText;
    private JComboBox UpdateAllergyText;
    private JTextField UpdateNumText;
    private JComboBox UpdateIllnessText;
    private JComboBox UpdateInsuranceText;
    private JTextField UpdateCopayText;
    private JComboBox UpdatePTText;
    private JLabel UT2Label;
    private JLabel UFLabel;
    private JLabel ULLabel;
    private JLabel UALabel;
    private JLabel UNLabel;
    private JLabel UITLabel;
    private JLabel UCPLabel;
    private JLabel UPTLabel;
    private JLabel UPNameLabel;
    private JLabel UPNumLabel;
    private JLabel UAllergyLabel;
    private JLabel UIllnessLabel;
    private JTextField UpdatePTUDobText;
    private JTextField UpdatePTUNameText;
    private JLabel PTULabel;
    private JLabel PTUNameLabel;
    private JLabel PTUDobLabel;

    //create our card layout in order to swap between menus
    private CardLayout c1 = (CardLayout)PanelContainer.getLayout();

    //listen to see if all new patient info is filled
    ArrayList<JTextField> newPatientInfoFields = new ArrayList<JTextField>(
            Arrays.asList(newLastText, newFirstText, newAddressText, newNumText, newDobText, newCopayText)
    );
    //listen to see if all new patient medical info is filled
    ArrayList<JTextField> newPatientMedInfoFields = new ArrayList<JTextField>(
            Arrays.asList(newPhysNameText, newPhysNumText)
    );
    //This is to determine if all the fields of Create new patient are filled correctly
    //before allowing the user to submit the new patient information
    DocumentListener newPatientListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            boolean newPatientEnable = true;
            boolean submitNewPatientEnable = true;

            for (JTextField field : newPatientInfoFields) {
                if (field.getText().isEmpty()) {
                    newPatientEnable = false;
                }
                try {
                    Float.parseFloat(newCopayText.getText());
                } catch (Exception wrongValType) {
                    newPatientEnable = false;
                }
            }
            for (JTextField field : newPatientMedInfoFields) {
                if (field.getText().isEmpty()) {
                    submitNewPatientEnable = false;
                }
            }

            toMedButton.setEnabled(newPatientEnable);
            SubmitNewPatientButton.setEnabled(submitNewPatientEnable);
        }
    };

    //constructor of PPi (all action listeners are defined within this class)
    public PatientProfileInterface(String FileName) {
        //create new patient database with filename given
        PatientDatabase Pdb = new PatientDatabase(FileName);

        // add document listener to all newPatient fields --> to disable next/submit button until all fields filled
        for (JTextField field : newPatientInfoFields) {
            field.getDocument().addDocumentListener(newPatientListener);
        }
        for (JTextField field : newPatientMedInfoFields) {
            field.getDocument().addDocumentListener(newPatientListener);
        }


        NewPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "NewPatientCard");
            }
        });
        toMedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "NewPatientCard2");

            }
        });

        //Button to submit a new patient and display the new patients information
        SubmitNewPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //create temp patient to pass to database
                //fill in all temp variables with form fields
                String tLast = newLastText.getText();
                String tFirst = newFirstText.getText();
                String tAddress = newAddressText.getText();
                String tNumber = newNumText.getText();
                String tDOB = newDobText.getText();
                float tCopay = Float.valueOf(newCopayText.getText());
                String tPhysName = newPhysNameText.getText();
                String tPhysNum = newPhysNumText.getText();
                //HAHA ENUMERATED TYPES ARE FUN!!!!
                //These switch statements work with JDropBox to have user select one of each enumerated types
                Patient.InsuranceType tInsurance;
                int a = newInsuranceText.getSelectedIndex();
                switch(a){
                    case 0:
                        tInsurance = Patient.InsuranceType.PRIVATE;
                        break;
                    case 1:
                        tInsurance = Patient.InsuranceType.GOVERNMENT;
                        break;
                    default:
                        tInsurance = null;
                        break;
                }
                Patient.PatientType tPType;
                int b = newTypeText.getSelectedIndex();
                switch(b){
                    case 0:
                        tPType = Patient.PatientType.PEDIATRIC;
                        break;
                    case 1:
                        tPType = Patient.PatientType.ADULT;
                        break;
                    case 2:
                        tPType = Patient.PatientType.GERIATRIC;
                        break;
                    default:
                        tPType = null;
                        break;
                }
                Patient.MedicalConditions.Allergies tAllergies;
                int c = newAllergiesText.getSelectedIndex();
                switch(c){
                    case 0:
                        tAllergies = Patient.MedicalConditions.Allergies.FOOD;
                        break;
                    case 1:
                        tAllergies = Patient.MedicalConditions.Allergies.MEDICATION;
                        break;
                    case 2:
                        tAllergies = Patient.MedicalConditions.Allergies.SEASONAL;
                        break;
                    case 3:
                        tAllergies = Patient.MedicalConditions.Allergies.NONE;
                        break;
                    case 4:
                        tAllergies = Patient.MedicalConditions.Allergies.OTHER;
                        break;
                    default:
                        tAllergies = null;
                        break;
                }
                Patient.MedicalConditions.Illnesses tIllness;
                int d = newIllnessText.getSelectedIndex();
                switch(d){
                    case 0:
                        tIllness = Patient.MedicalConditions.Illnesses.DIABETES;
                        break;
                    case 1:
                        tIllness = Patient.MedicalConditions.Illnesses.CHD;
                        break;
                    case 2:
                        tIllness = Patient.MedicalConditions.Illnesses.ASTHMA;
                        break;
                    case 3:
                        tIllness = Patient.MedicalConditions.Illnesses.NONE;
                        break;
                    case 4:
                        tIllness = Patient.MedicalConditions.Illnesses.OTHER;
                        break;
                    default:
                        tIllness = null;
                        break;
                }

                //create new temp patient to add to database
                Patient tempPatient = new Patient(tLast, tFirst, tAddress, tNumber, tDOB, tInsurance, tCopay, tPType, tPhysName, tPhysNum, tAllergies, tIllness);
                Pdb.insertProfile(tempPatient);
                //tempPatient = null; --> this is still the reference to the new patient (ie you're overwriting the just added patient with null)

                //display the new patient on the display label and switch to the display menu
                DisplayLabel.setText(Pdb.displayProfile(tLast, tDOB));
                c1.show(PanelContainer, "DisplayCard");

                //clean up text fields
                newLastText.setText(null);
                newFirstText.setText(null);
                newAddressText.setText(null);
                newNumText.setText(null);
                newDobText.setText(null);
                newCopayText.setText(null);
                newPhysNameText.setText(null);
                newPhysNumText.setText(null);

                //clean up variables
                tLast = null;
                tFirst = null;
                tAddress = null;
                tNumber = null;
                tDOB = null;
                tInsurance = null;
                tCopay = 0.0f;
                tPType = null;
                tPhysName = null;
                tPhysNum = null;
                tAllergies = null;
                tIllness = null;
            }
        });
        //button to return from display card to main card
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "MainCard");
                DisplayLabel.setText(null);
            }
        });
        //button to delete a patient profile
        DeleteSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete patient with info from text fields
                try { //try the delete operation
                    Pdb.deleteProfile(DeleteNameText.getText(), DeleteDobText.getText());
                    DisplayLabel.setText("Successfully Deleted Patient");
                } catch (PatientNotFoundException delExc) { //catch exception
                    DisplayLabel.setText("Delete Operation Failed");
                }
                //show display card
                c1.show(PanelContainer, "DisplayCard");
                //clear text fields
                DeleteNameText.setText(null);
                DeleteDobText.setText(null);
            }
        });
        //Button to go MainCard -> DeletePatientCard
        DeletePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "DeletePatientCard");
            }
        });
        //Button to Exit program from the main menu
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //update the database and exit program
                Pdb.updateDatabase();
                System.exit(0);
            }
        });
        //Button to go from MainMenuCard -> FindPatientCard
        FindPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "FindCard");
            }
        });
        //Button to submit find patient query and switch to display
        FindSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pull info from GUI text fields
                String fLast = FindLastText.getText();
                String fDOB = FindDobText.getText();
                //pass info to logical function
                DisplayLabel.setText(Pdb.displayProfile(fLast, fDOB));

                //switch to display card
                c1.show(PanelContainer, "DisplayCard");
                //clean up variables
                fLast = null;
                fDOB = null;
            }
        });
        //button to submit patient search query and go to display
        SearchSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create enum type and get index selected from gui
                PatientDatabase.AttributeTypes searchAttribute;
                int attributeIndex = SearchAttributeText.getSelectedIndex();
                String specificAttribute = SearchSpecifyText.getText();
                //set enum type to selected option
                switch(attributeIndex){
                    case 0:
                        searchAttribute = PatientDatabase.AttributeTypes.DOCTOR;
                        break;
                    case 1:
                        searchAttribute = PatientDatabase.AttributeTypes.INSURANCE;
                        break;
                    case 2:
                        searchAttribute = PatientDatabase.AttributeTypes.PATIENT_TYPE;
                        break;
                    case 3:
                        searchAttribute = PatientDatabase.AttributeTypes.ALLERGY;
                        break;
                    case 4:
                        searchAttribute = PatientDatabase.AttributeTypes.ILLNESS;
                        break;
                    case 5:
                        searchAttribute = PatientDatabase.AttributeTypes.ANY;
                        break;
                    default:
                        searchAttribute = null;
                        break;
                }
                //call search function
                Pdb.getSummary(searchAttribute, specificAttribute, PatientProfileInterface.this);
                //switch to display card
                c1.show(PanelContainer, "DisplayCard");
            }
        });
        //Button to go from MainMenuCard -> SearchDatabaseCard
        DatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "SearchCard");
            }
        });
        //Submit update button one of two
        //(goes from attributes user wants to change to actually being able to change said attributes)
        UpdateButtonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //logic to see what text fields to display
                //if a use selected to change that attribute this code
                //makes sure it shows up on the input form on "UpdateCard2"
                if(UpdateFirstCheck.isSelected()){
                    UpdateFirstText.setVisible(true);
                    UFLabel.setVisible(true);
                }else{
                    UpdateFirstText.setVisible(false);
                    UFLabel.setVisible(false);
                }
                if(UpdateLastCheck.isSelected()){
                    UpdateLastText.setVisible(true);
                    ULLabel.setVisible(true);
                }else{
                    UpdateLastText.setVisible(false);
                    ULLabel.setVisible(false);
                }
                if(UpdateAddressCheck.isSelected()){
                    UpdateAddressText.setVisible(true);
                    UALabel.setVisible(true);
                }else{
                    UpdateAddressText.setVisible(false);
                    UALabel.setVisible(false);
                }
                if(UpdateNumberCheck.isSelected()){
                    UpdateNumText.setVisible(true);
                    UNLabel.setVisible(true);
                }else{
                    UpdateNumText.setVisible(false);
                    UNLabel.setVisible(false);
                }
                if(UpdateInsuranceCheck.isSelected()){
                    UpdateInsuranceText.setVisible(true);
                    UITLabel.setVisible(true);
                }else{
                    UpdateInsuranceText.setVisible(false);
                    UITLabel.setVisible(false);
                }
                if(UpdateCopayCheck.isSelected()){
                    UpdateCopayText.setVisible(true);
                    UCPLabel.setVisible(true);
                }else{
                    UpdateCopayText.setVisible(false);
                    UCPLabel.setVisible(false);
                }
                if(UpdatePTCheck.isSelected()){
                    UpdatePTText.setVisible(true);
                    UPTLabel.setVisible(true);
                }else{
                    UpdatePTText.setVisible(false);
                    UPTLabel.setVisible(false);
                }
                if(UpdatePhysNameCheck.isSelected()){
                    UpdatePhysNameText.setVisible(true);
                    UPNameLabel.setVisible(true);
                }else{
                    UpdatePhysNameText.setVisible(false);
                    UPNameLabel.setVisible(false);
                }
                if(UpdatePhysNumCheck.isSelected()){
                    UpdatePhysNumText.setVisible(true);
                    UPNumLabel.setVisible(true);
                }else{
                    UpdatePhysNumText.setVisible(false);
                    UPNumLabel.setVisible(false);
                }
                if(UpdateAllergyCheck.isSelected()){
                    UpdateAllergyText.setVisible(true);
                    UAllergyLabel.setVisible(true);
                }else{
                    UpdateAllergyText.setVisible(false);
                    UAllergyLabel.setVisible(false);
                }
                if(UpdateIllnessCheck.isSelected()){
                    UpdateIllnessText.setVisible(true);
                    UIllnessLabel.setVisible(true);
                }else{
                    UpdateIllnessText.setVisible(false);
                    UIllnessLabel.setVisible(false);
                }
                //show the next form in the update patient function
                c1.show(PanelContainer, "UpdateCard2");
            }
        });
        //Button to go from MainMenuCard -> UpdatePatientCardOne
        ModifyPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "UpdateCard");
            }
        });
        //Button to submit an update of a patient
        UpdateSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Calls the update function which also sets the display card to show updated paitent info
                DisplayLabel.setText(Pdb.updateProfile(UpdatePTUNameText.getText(), UpdatePTUDobText.getText(), PatientProfileInterface.this));

                //switch to display card
                c1.show(PanelContainer, "DisplayCard");

                //reset the checkboxes
                UpdateFirstCheck.setSelected(false);
                UpdateLastCheck.setSelected(false);
                UpdateAddressCheck.setSelected(false);
                UpdateNumberCheck.setSelected(false);
                UpdateInsuranceCheck.setSelected(false);
                UpdateCopayCheck.setSelected(false);
                UpdatePTCheck.setSelected(false);
                UpdatePhysNameCheck.setSelected(false);
                UpdatePhysNumCheck.setSelected(false);
                UpdateAllergyCheck.setSelected(false);
                UpdateIllnessCheck.setSelected(false);

                //reset textfields
                UpdatePTUDobText.setText(null);
                UpdatePTUNameText.setText(null);
            }
        });
    }

    public static void main(String[] args) {
        // First scan
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter database filename: ");
        String fp = sc.nextLine();
        sc.close();

        JFrame mainframe = new JFrame("MIS");
        mainframe.setContentPane(new PatientProfileInterface(fp).PanelContainer);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setVisible(true);

    }


    //PUBLIC METHODS TO BE CALLED FROM OTHER CLASSES
    //allows other classes to set text in the display label
    public void setDisplayLabel(String text){
        PatientProfileInterface.this.DisplayLabel.setText(text);
    }
    //allows other classes to APPEND text in the display label
    public void appendDisplayLabel(String text){
        PatientProfileInterface.this.DisplayLabel.setText(PatientProfileInterface.this.DisplayLabel.getText() + text);
    }
    //all of these starting with "getUP" allow other classes to pull info from the UpdatePatient card
    //when a patient is in the process of being updated
    public String getUpFirst(){
        return PatientProfileInterface.this.UpdateFirstText.getText();
    }
    public String getUpLast(){
        return PatientProfileInterface.this.UpdateLastText.getText();
    }
    public String getUpAddress(){
        return PatientProfileInterface.this.UpdateAddressText.getText();
    }
    public String getUpNumber(){
        return PatientProfileInterface.this.UpdateNumText.getText();
    }
    public float getUpCopay(){
        return Float.valueOf(PatientProfileInterface.this.UpdateCopayText.getText());
    }
    public Patient.InsuranceType getUpInsurance(){
        Patient.InsuranceType tInsurance;
        int a = PatientProfileInterface.this.UpdateInsuranceText.getSelectedIndex();
        switch(a){
            case 0:
                tInsurance = Patient.InsuranceType.PRIVATE;
                break;
            case 1:
                tInsurance = Patient.InsuranceType.GOVERNMENT;
                break;
            default:
                tInsurance = null;
                break;
        }
        return tInsurance;
    }
    public Patient.PatientType getUpPatientType(){
        Patient.PatientType tPType;
        int b = PatientProfileInterface.this.UpdatePTText.getSelectedIndex();
        switch(b){
            case 0:
                tPType = Patient.PatientType.PEDIATRIC;
                break;
            case 1:
                tPType = Patient.PatientType.ADULT;
                break;
            case 2:
                tPType = Patient.PatientType.GERIATRIC;
                break;
            default:
                tPType = null;
                break;
        }
        return tPType;
    }
    public String getUpPhysName(){
        return PatientProfileInterface.this.UpdatePhysNameText.getText();
    }
    public String getUpPhysNum(){
        return PatientProfileInterface.this.UpdatePhysNumText.getText();
    }
    public Patient.MedicalConditions.Allergies getUpAllergy(){
        Patient.MedicalConditions.Allergies tAllergies;
        int c = UpdateAllergyText.getSelectedIndex();
        switch(c){
            case 0:
                tAllergies = Patient.MedicalConditions.Allergies.FOOD;
                break;
            case 1:
                tAllergies = Patient.MedicalConditions.Allergies.MEDICATION;
                break;
            case 2:
                tAllergies = Patient.MedicalConditions.Allergies.SEASONAL;
                break;
            case 3:
                tAllergies = Patient.MedicalConditions.Allergies.NONE;
                break;
            case 4:
                tAllergies = Patient.MedicalConditions.Allergies.OTHER;
                break;
            default:
                tAllergies = null;
                break;
        }
        return tAllergies;
    }
    public Patient.MedicalConditions.Illnesses getUpIllness(){
        Patient.MedicalConditions.Illnesses tIllness;
        int d = UpdateIllnessText.getSelectedIndex();
        switch(d){
            case 0:
                tIllness = Patient.MedicalConditions.Illnesses.DIABETES;
                break;
            case 1:
                tIllness = Patient.MedicalConditions.Illnesses.CHD;
                break;
            case 2:
                tIllness = Patient.MedicalConditions.Illnesses.ASTHMA;
                break;
            case 3:
                tIllness = Patient.MedicalConditions.Illnesses.NONE;
                break;
            case 4:
                tIllness = Patient.MedicalConditions.Illnesses.OTHER;
                break;
            default:
                tIllness = null;
                break;
        }
        return tIllness;
    }

    //returns a boolean array that allows other classes to see which of the checkboxes are checked
    //this allows them to know what info a user wants to update about a patient
    public boolean[] isChecked(){
        boolean[] r = new boolean[11];

        if(PatientProfileInterface.this.UpdateFirstCheck.isSelected()){
            r[0] = true;
        }else{
            r[0] = false;
        }
        if(PatientProfileInterface.this.UpdateLastCheck.isSelected()){
            r[1] = true;
        }else{
            r[1] = false;
        }
        if(PatientProfileInterface.this.UpdateAddressCheck.isSelected()){
            r[2] = true;
        }else{
            r[2] = false;
        }
        if(PatientProfileInterface.this.UpdateNumberCheck.isSelected()){
            r[3] = true;
        }else{
            r[3] = false;
        }
        if(PatientProfileInterface.this.UpdateInsuranceCheck.isSelected()){
            r[4] = true;
        }else{
            r[4] = false;
        }
        if(PatientProfileInterface.this.UpdateCopayCheck.isSelected()){
            r[5] = true;
        }else{
            r[5] = false;
        }
        if(PatientProfileInterface.this.UpdatePTCheck.isSelected()){
            r[6] = true;
        }else{
            r[6] = false;
        }
        if(PatientProfileInterface.this.UpdatePhysNameCheck.isSelected()){
            r[7] = true;
        }else{
            r[7] = false;
        }
        if(PatientProfileInterface.this.UpdatePhysNumCheck.isSelected()){
            r[8] = true;
        }else{
            r[8] = false;
        }
        if(PatientProfileInterface.this.UpdateAllergyCheck.isSelected()){
            r[9] = true;
        }else{
            r[9] = false;
        }
        if(PatientProfileInterface.this.UpdateIllnessCheck.isSelected()){
            r[10] = true;
        }else{
            r[10] = false;
        }
        return r;
    }

}

