import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Update extends JFrame implements ActionListener {
    private Container c;
    private JLabel jLabel;
    private JTextField jName;
    private JComboBox jType;
    private JSpinner jPrice;
    private JSpinner jStock;
    private JButton jInsert;
    private SpinnerModel modelPrice = new SpinnerNumberModel(0, 0, 200000, 1000);
    private SpinnerModel modelStock = new SpinnerNumberModel(0, 0, 1000, 1);
    private ResultSetMetaData rsm = null;
    private Connection con;

    private static final String SQL_INSERT = "UPDATE product SET(ID, Name, Type, Price, Stock) VALUES (?,?,?,?,?)";



    void Create(){
        setTitle("Update Stock");
        setBounds(300, 90, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        jLabel = new JLabel("Name");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 10);
        c.add(jLabel);

        jName = new JTextField();
        jName.setFont(new Font("Arial", Font.PLAIN, 12));
        jName.setSize(200, 20);
        jName.setLocation(160, 10);
        c.add(jName);

        jLabel = new JLabel("Type");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 40);
        c.add(jLabel);

        String ty[] = {"Drink","Food"};

        jType = new JComboBox(ty);
        jType.setFont(new Font("Arial", Font.PLAIN, 12));
        jType.setSize(200, 20);
        jType.setLocation(160, 40);
        c.add(jType);

        jLabel = new JLabel("Price");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 70);
        c.add(jLabel);

        jPrice = new JSpinner(modelPrice);
        jPrice.setFont(new Font("Arial", Font.PLAIN, 12));
        jPrice.setSize(200, 20);
        jPrice.setLocation(160, 70);
        c.add(jPrice);

        jLabel = new JLabel("Stock");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 100);
        c.add(jLabel);

        jStock = new JSpinner(modelStock);
        jStock.setFont(new Font("Arial", Font.PLAIN, 12));
        jStock.setSize(200, 20);
        jStock.setLocation(160, 100);
        c.add(jStock);


        jInsert = new JButton("Insert");
        jInsert.setFont(new Font("Arial", Font.PLAIN, 12));
        jInsert.setSize(120, 20);
        jInsert.setLocation(200, 200);
        jInsert.addActionListener(this);
        c.add(jInsert);


        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jInsert) {
            if (jName.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Name Harus Diisi!", "Informasi", JOptionPane.ERROR_MESSAGE);
            } else if ((Integer) modelPrice.getValue() < 1 || (Integer) modelPrice.getValue() > 200000) {
                JOptionPane.showMessageDialog(null, "Harga Harus Diatas 1 dan Dibawah 200000!", "Informasi", JOptionPane.ERROR_MESSAGE);
            } else if ((Integer) modelStock.getValue() < 1 || (Integer) modelStock.getValue() > 100) {
                JOptionPane.showMessageDialog(null, "Stock Harus Diatas 1 dan Dibawah 100!", "Informasi", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    con = DriverManager.getConnection("jdbc:ucanaccess://shop.accdb");
                    Statement stmt = con.createStatement();
                    PreparedStatement pstmt = con.prepareStatement(SQL_INSERT);
                    pstmt.setInt(1, 2);
                    pstmt.setString(2, jName.getText());
                    pstmt.setString(3, (String) jType.getSelectedItem());
                    pstmt.setInt(4, (Integer) modelPrice.getValue());
                    pstmt.setInt(5, (Integer) modelStock.getValue());
                    Statement st = null;
                    st = con.createStatement();
                    pstmt.executeUpdate();
                    st.close();
                    dispose();
                    new ProductFrame();
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        }
    }
}
