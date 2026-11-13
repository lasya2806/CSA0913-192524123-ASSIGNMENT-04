
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FeeService {

    public String processPayment(String regNo, String amountText) {

        if (regNo == null || regNo.trim().isEmpty()) {
            return "Enter Registration Number";
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            return "Enter a valid amount";
        }

        if (amount <= 0) {
            return "Amount must be greater than zero";
        }

        // Payment processing logic
        return "Payment Successful!";
    }
}

public class FeeWindow extends JFrame {

    JTextField regNo = new JTextField(15);
    JTextField amount = new JTextField(15);

    JButton pay = new JButton("Pay");
    JButton clear = new JButton("Clear");
    JButton receipt = new JButton("Receipt");

    JLabel status = new JLabel("Enter payment details");

    FeeService service = new FeeService();

    public FeeWindow() {

        setTitle("Student Fee Payment");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Registration Number:"));
        add(regNo);

        add(new JLabel("Amount:"));
        add(amount);

        add(pay);
        add(clear);

        add(receipt);
        add(status);

        pay.addActionListener(e -> {
            String result = service.processPayment(
                regNo.getText(),
                amount.getText()
            );

            status.setText(result);
            JOptionPane.showMessageDialog(this, result);
        });

        clear.addActionListener(e -> {
            regNo.setText("");
            amount.setText("");
            status.setText("Fields Cleared");
        });

        receipt.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Registration Number: " + regNo.getText()
                + "\nAmount: " + amount.getText()
                + "\nPlease verify payment status."
            );
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeeWindow());
    }
}

