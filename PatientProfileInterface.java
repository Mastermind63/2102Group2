import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField birchTextField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField7;
    private JLabel LastLabel;
    private JLabel FirstLabel;
    private JLabel AddressLabel;
    private JLabel PnumLabel;
    private JLabel DoBLabel;
    private JLabel InsuranceLabel;
    private JLabel CopayLabel;
    private JLabel PtypeLabel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton SubmitNewPatientButton;
    private JTextField textField1;
    private JTextField textField6;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JLabel PhysLabel;
    private JLabel PhysContactLabel;
    private JLabel AllergiesLabel;
    private JLabel IllnessesLabel;
    private JPanel DisplayPanel;
    private JTextPane DisplayPane;
    private JButton ReturnButton;

    private CardLayout c1 = (CardLayout)PanelContainer.getLayout();

    public PatientProfileInterface() {
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
                c1.show(PanelContainer, "DisplayCard");
            }
        });

        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(PanelContainer, "MainCard");
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainframe = new JFrame("MIS");
        mainframe.setContentPane(new PatientProfileInterface().PanelContainer);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setVisible(true);
    }
}
