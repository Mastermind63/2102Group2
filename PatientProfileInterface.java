import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                        System.out.println("SHIT DIDNT WORK");
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
                tempPatient = null;

                DisplayLabel.setText(Pdb.displayProfile(tLast, tNumber));
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
                }catch(Exception delExc){ //catch exception
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
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fp = sc.nextLine();
        sc.close();

        JFrame mainframe = new JFrame("MIS");
        mainframe.setContentPane(new PatientProfileInterface(fp).PanelContainer);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setVisible(true);
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

