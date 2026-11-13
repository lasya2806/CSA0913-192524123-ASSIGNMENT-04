
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FeeService {

    public String processPayment(String regNo, String amountText) {

        if (regNo == null || regNo.trim().isEmpty()) {
            return "Please enter Registration Number!";
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            return "Please enter a valid amount!";
        }

        if (amount <= 0) {
            return "Amount must be greater than zero!";
        }

        return "Payment Successful!";
    }
}

public class FeeWindow extends JFrame {

    JTextField regNo;
    JTextField amount;

    JButton pay, clear, receipt;

    JLabel status;

    FeeService service = new FeeService();

    public FeeWindow() {

        setTitle("Student Fee Payment");
        setSize(450, 250);
        setLayout(new GridLayout(5, 2, 10, 10));

        regNo = new JTextField();
        amount = new JTextField();

        pay = new JButton("Pay");
        clear = new JButton("Clear");
        receipt = new JButton("Receipt");

        status = new JLabel("Enter Payment Details");

        add(new JLabel("Registration Number:"));
        add(regNo);

        add(new JLabel("Payment Amount:"));
        add(amount);

        add(pay);
        add(clear);

        add(receipt);
        add(status);

        // Pay Button
        pay.addActionListener(e -> {

            String result = service.processPayment(
                regNo.getText(),
                amount.getText()
            );

            status.setText(result);

            JOptionPane.showMessageDialog(this, result);
        });

        // Clear Button
        clear.addActionListener(e -> {

            regNo.setText("");
            amount.setText("");

            status.setText("Fields Cleared");
        });

        // Receipt Button
        receipt.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                this,
                "PAYMENT RECEIPT\n\n" +
                "Registration Number: " + regNo.getText() +
                "\nAmount: " + amount.getText()
            );
        });

        // Window Closing
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                    FeeWindow.this,
                    "Are you sure you want to close?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new FeeWindow();
        });
    }
}

