import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    private CardLayout c1 = (CardLayout)PanelContainer.getLayout();

    public PatientProfileInterface(String FileName) {
        //constructor of PPI
        PatientDatabase Pdb = new PatientDatabase(FileName);
        //Patient tempPatient;


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

                Patient tempPatient = new Patient(tLast, tFirst, tAddress, tNumber, tDOB, tInsurance, tCopay, tPType, tPhysName, tPhysNum, tAllergies, tIllness);
                Pdb.insertProfile(tempPatient);
                //tempPatient = null; --> this is still the reference to the new patient (ie you're overwriting the just added patient with null)

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

        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "MainCard");
                DisplayLabel.setText(null);
            }
        });
        DeleteSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete patient with info from text fields
                //try the delete operation
                try {
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
        DeletePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "DeletePatientCard");
            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pdb.updateDatabase();
                System.exit(0);
            }
        });
        FindPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "FindCard");
            }
        });
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
                    default:
                        searchAttribute = null;
                        break;
                }
                //call search function
                Pdb.getSummary(searchAttribute, specificAttribute, PatientProfileInterface.this);

                c1.show(PanelContainer, "DisplayCard");
            }
        });
        DatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "SearchCard");
            }
        });
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
        ModifyPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "UpdateCard");
            }
        });
        UpdateSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        // Scan again or open
        while (true) {
            try {
                JFrame mainframe = new JFrame("MIS");
                mainframe.setContentPane(new PatientProfileInterface(fp).PanelContainer);
                mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainframe.pack();
                mainframe.setVisible(true);
            } catch (Error e) {
                System.out.println("Error opening the file: " + fp);
                sc = new Scanner(System.in);
                System.out.print("Enter database filename: ");
                fp = sc.nextLine();
                sc.close();
            }
        }
    }

    public void setDisplayLabel(String text){
        PatientProfileInterface.this.DisplayLabel.setText(text);
    }
    public void appendDisplayLabel(String text){
        PatientProfileInterface.this.DisplayLabel.setText(PatientProfileInterface.this.DisplayLabel.getText() + text);
    }
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
    public boolean[] isChecked(){
        boolean[] r = new boolean[7];

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
        }else{
        }
        if(PatientProfileInterface.this.UpdatePhysNumCheck.isSelected()){
        }else{
        }
        if(PatientProfileInterface.this.UpdateAllergyCheck.isSelected()){
        }else{
        }
        if(PatientProfileInterface.this.UpdateIllnessCheck.isSelected()){
        }else{
        }
        return r;
    }

/*    public void exitCode(PatientDatabase finDB, String filename){
        FileWriter fooWriter = null;
        try{
            fooWriter = new FileWriter(filename, false); // true to append
                                                                // false to overwrite.

            fooWriter.write("New Contents\n");
            fooWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Opened");
        }
    }*/
}

