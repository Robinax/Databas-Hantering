import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Gui extends JFrame {
    private JPanel jp;
    private JTextField bookTitle;
    private JLabel login;
    private JLabel password;
    private JLabel output;
    private JTextField userName;
    private JPasswordField passwordInput;
    private String nameonuser;
    private JButton[] buttons = new JButton[10];
    private JLabel[] labels = new JLabel[2];
    private JTextField[] textFields = new JTextField[15];
    private JCheckBox[] checkboxes = new JCheckBox[5];
    int login_true = 0;
    User user = new User();

    public Gui() {
        jp = new JPanel();
        jp.setBackground(Color.cyan);
        jp.setLayout(null);
        this.setTitle("SQL - Front");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //----------------------------------------Sök Bok-Magasin från titel--------------------------------------------------------
        bookTitle = new JTextField("Search for a book title here");
        bookTitle.setVisible(false);
        bookTitle.setBackground(Color.lightGray);
        bookTitle.setBounds(275, 240, 200, 40);
        bookTitle.addActionListener(e -> {
            String ans3 = "";
            try {
                if (checkboxes[1].isSelected()) {
                    PreparedStatement stmt = user.con.prepareStatement("select * from Böcker where Title = ?");
                    stmt.setString(1, bookTitle.getText());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        ans3 = ans3 + "\nID: " + (rs.getInt(1) + ", Title: " + rs.getString(2) + ", Author: " + rs.getString(3) + ", Pages: " + rs.getInt(4) + ", Classifikation: " + rs.getString(5) + ", Utlånad:" + rs.getString(6));
                    }
                    output.setText(ans3);

                }
                if (checkboxes[0].isSelected()) {
                    PreparedStatement stmt = user.con.prepareStatement("select * from tidsskrifter where Titel = ?");
                    stmt.setString(1, bookTitle.getText());
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        ans3 = ans3 + "\nID:" + (rs.getInt(1) + ", Titel:" + rs.getString(2) + ", Date:" + rs.getDate(3) + ", Where:" + rs.getString(4) + ", Utlånad:" + rs.getString(5));
                    }
                    output.setText(ans3);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------
        checkboxes[1] = new JCheckBox("Bok");
        checkboxes[1].setVisible(false);
        checkboxes[1].setBackground(Color.cyan);
        checkboxes[1].setBounds(500, 240, 100, 50);
        checkboxes[1].addActionListener(e -> {
            checkboxes[0].setSelected(false);
        });

        checkboxes[0] = new JCheckBox("Magazine");
        checkboxes[0].setVisible(false);
        checkboxes[0].setBackground(Color.cyan);
        checkboxes[0].setBounds(600, 240, 100, 50);
        checkboxes[0].addActionListener(e -> {
            checkboxes[1].setSelected(false);
        });

        checkboxes[2] = new JCheckBox("Employee");
        checkboxes[2].setBackground(Color.cyan);
        checkboxes[2].setVisible(true);
        checkboxes[2].setBounds(150, 600, 100, 50);
        checkboxes[2].addActionListener(e -> {
            checkboxes[4].setSelected(false);
            checkboxes[3].setSelected(false);
        });

        checkboxes[3] = new JCheckBox("Admin");
        checkboxes[3].setBackground(Color.cyan);
        checkboxes[3].setVisible(true);
        checkboxes[3].setBounds(250, 600, 100, 50);
        checkboxes[3].addActionListener(e -> {
            checkboxes[4].setSelected(false);
            checkboxes[2].setSelected(false);
        });

        checkboxes[4] = new JCheckBox("User");
        checkboxes[4].setVisible(true);
        checkboxes[4].setBackground(Color.cyan);
        checkboxes[4].setBounds(350, 600, 100, 50);
        checkboxes[4].addActionListener(e -> {
            checkboxes[3].setSelected(false);
            checkboxes[2].setSelected(false);
        });

        login = new JLabel("User Name");
        login.setBounds(200, 200, 100, 100);

        password = new JLabel("Password");
        password.setBounds(200, 400, 80, 50);

        output = new JLabel("Output will come here!");
        output.setBackground(Color.ORANGE);
        output.setVisible(false);
        output.setBounds(50, 50, 600, 150);

        userName = new JTextField("Username");
        userName.setBounds(275, 235, 100, 30);

        passwordInput = new JPasswordField("");
        passwordInput.setBounds(275, 410, 100, 30);
        passwordInput.setEchoChar('*');

        //---------------------------------------------------buttons with actionlisteners down here----------------------------------------------------

        //-------------------------------------------------------MAGAZINE-------------------------------------------------------------------------
        //Knappen som tar fram alla magasiner-----------
        buttons[4] = new JButton("look at all Magazines");
        buttons[4].setVisible(false);
        buttons[4].setBounds(185, 350, 175, 45);
        buttons[4].addActionListener(e -> {
            sqlSelect("select * from tidsskrifter");

        });
        //--------------------låter dig låna magasiner-----------
        buttons[5] = new JButton("Loan Magazine");
        buttons[5].setVisible(false);
        buttons[5].setBounds(400, 350, 150, 45);
        buttons[5].addActionListener(e -> {
            String ans = "";
            String update = "";
            try {
                PreparedStatement stmt = user.con.prepareStatement("select * from tidsskrifter");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ans = ans + "\nID:" + (rs.getInt(1) + ", Titel:" + rs.getString(2) + ", Date:" + rs.getDate(3) + ", Where:" + rs.getString(4) + ", Utlånad:" + rs.getString(5));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (ans.contains("Utlånad:Ja")) {
                output.setText("That book is already loaned");

            } else if (ans.contains("Utlånad:Nej")) {
                try {
                    PreparedStatement stmt = user.con.prepareStatement("update tidsskrifter set utlånad=\"Ja\" where Titel = ?");
                    stmt.setString(1, bookTitle.getText());
                    stmt.executeUpdate();
                    System.out.println(bookTitle.getText());

                    update = "You have now loaned: " + bookTitle.getText();
                    output.setText(update);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                output.setText("Invalid Title");
            }
        });
        //-------------------------------------------------BOOKS!!!-----------------------------------------------------------------
        //------------SÖKER UPP ALLA BÖCKER---------------
        buttons[2] = new JButton("Search for books");
        buttons[2].setVisible(false);
        buttons[2].setBounds(200, 300, 150, 45);
        buttons[2].addActionListener(e -> {
            sqlSelect("select * from Böcker");

        });
        //--------------------------LÅTER DIG LÅNA EN BOK-------------------
        buttons[3] = new JButton("Loan the book");
        buttons[3].setVisible(false);
        buttons[3].setBounds(400, 300, 150, 45);
        buttons[3].addActionListener(e -> {
            String ans = "";
            String update = "";
            try {
                PreparedStatement stmt = user.con.prepareStatement("select * from Böcker where Title = ?");
                stmt.setString(1, bookTitle.getText());
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ans = ans + "\nID: " + (rs.getInt(1) + ", Title: " + rs.getString(2) + ", Author: " + rs.getString(3) + ", Pages: " + rs.getInt(4) + ", Classifikation: " + rs.getString(5) + ", Utlånad:" + rs.getString(6));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (ans.contains("Utlånad:Ja")) {
                output.setText("That book is already loaned");

            } else if (ans.contains("Utlånad:Nej")) {
                output.setText("ok");
                int getID = 0;
                try {
                    //-----------------------------------------------------Updaterar så att boken blir utlånad------------------------
                    PreparedStatement stmt = user.con.prepareStatement("update böcker set utlånad=\"Ja\" where Title = ?");
                    stmt.setString(1, bookTitle.getText());
                    stmt.executeUpdate();
                    //-------------------------------------------------LETAR UPP VEM DET ÄR SOM LÅNAR DEN OCH VILKET ID DEN PERSONEN HAR-------------------
                    PreparedStatement SearchforID = user.con.prepareStatement("select LånID from låntagare where Namn =?");
                    SearchforID.setString(1, nameonuser);
                    ResultSet rs = SearchforID.executeQuery();
                    while (rs.next()) {
                        getID = getID + (rs.getInt(1));
                    }
                    //----------------------------------------------UPPDATERAR LÅNADEBÖCKER SÅ ATT MAN SER VEM SOM LÅNAT BÖCKERNA SEN-----------------------
                    PreparedStatement stmtupdate = user.con.prepareStatement("update lånadeböcker set Titel = ? where LånID = ?");
                    stmtupdate.setString(1, bookTitle.getText());
                    stmtupdate.setInt(2, getID);
                    stmtupdate.executeUpdate();
                    update = "You have now loaned: " + bookTitle.getText();
                    output.setText(update);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                output.setText("Invalid Title");
            }
        });
        //-----------TILL FÖR ATT LOGA UT-----------------
        buttons[1] = new JButton("Logout");
        buttons[1].setVisible(false);
        buttons[1].setBounds(400, 700, 100, 50);
        buttons[1].addActionListener(e -> {
            try {
                user.con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            login_true = 0;
            updateGUI(login_true);
        });
        //-----------------------------------LOGIN-----------------------------------
        buttons[0] = new JButton("Login");
        buttons[0].setBounds(400, 500, 100, 50);
        buttons[0].setBackground(Color.PINK);
        buttons[0].addActionListener(e -> {
            user.getusername(userName.getText(), passwordInput.getText(), this);
            nameonuser = userName.getText();
        });

        //-------------------------------Arbetar GUI----------------------------
        labels[0] = new JLabel("Output here");
        labels[0].setBounds(50, 50, 800, 60);
        labels[0].setVisible(false);


        buttons[6] = new JButton("Search for all the books");
        buttons[6].setBounds(200, 300, 200, 45);
        buttons[6].setVisible(false);
        buttons[6].addActionListener(e -> {


            String ans2 = "";
            try {
                PreparedStatement stmt = user.con.prepareStatement("select * from låntagare inner join lånadeböcker on låntagare.LånID = lånadeböcker.LånID;");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ans2 = ans2 + "\nLånID: " + (rs.getInt(1) + ", Namn: " + rs.getString(2) + ", Adress: " + rs.getString(3) + ", Telefonnr: " + rs.getInt(4) + ", Lånekortsnr: " + rs.getInt(5) + ", LånID:" + rs.getInt(6) + ", Titel:" + rs.getString(7) + "<br>");
                }
                labels[0].setText("<html>" + ans2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //----------------------------------------ADMIN GUI--------------------------------------------------
        labels[1] = new JLabel("Output");
        labels[1].setVisible(false);
        labels[1].setBounds(25, 50, 700, 100);

        buttons[7] = new JButton("Search trough all employees");
        buttons[7].setVisible(false);
        buttons[7].setBounds(50, 200, 200, 45);
        buttons[7].addActionListener(e -> {
            sqlSelect("Select * from anställda");
        });


        buttons[8] = new JButton("Change Employee");
        buttons[8].setVisible(false);
        buttons[8].setBounds(400, 450, 175, 45);
        buttons[8].setBackground(Color.orange);
        buttons[8].addActionListener(e -> {
            int id = 0;
            String[] set = new String[7];
            for (int i = 1; i < 7; i++) {
                set[i] = textFields[i].getText();

            }
            id = Integer.parseInt(textFields[7].getText());
            try {
                PreparedStatement stmtName = user.con.prepareStatement("update anställda set " + set[1] + " = ? where AnställningsID = ?");
                stmtName.setString(1, textFields[8].getText());
                stmtName.setInt(2, id);
                stmtName.executeUpdate();

                PreparedStatement stmtAdress = user.con.prepareStatement("update anställda set " + set[2] + " = ? where AnställningsID = ?");
                stmtAdress.setString(1, textFields[9].getText());
                stmtAdress.setInt(2, id);
                stmtAdress.executeUpdate();

                PreparedStatement stmtHemnr = user.con.prepareStatement("update anställda set " + set[3] + " = ? where AnställningsID = ?");
                stmtHemnr.setString(1, textFields[10].getText());
                stmtHemnr.setInt(2, id);
                stmtHemnr.executeUpdate();

                PreparedStatement stmtMobnr = user.con.prepareStatement("update anställda set " + set[4] + " = ? where AnställningsID = ?");
                stmtMobnr.setString(1, textFields[11].getText());
                stmtMobnr.setInt(2, id);
                stmtMobnr.executeUpdate();

                PreparedStatement stmtMånadslön = user.con.prepareStatement("update anställda set " + set[5] + " = ? where AnställningsID = ?");
                stmtMånadslön.setString(1, textFields[12].getText());
                stmtMånadslön.setInt(2, id);
                stmtMånadslön.executeUpdate();

                PreparedStatement stmtSemester = user.con.prepareStatement("update anställda set " + set[6] + " = ? where AnställningsID = ?");
                stmtSemester.setString(1, textFields[13].getText());
                stmtSemester.setInt(2, id);
                stmtSemester.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });
        buttons[9] = new JButton("Search");
        buttons[9].setVisible(false);
        buttons[9].setBounds(375, 240, 100, 35);
        buttons[9].setBackground(Color.PINK);
        buttons[9].addActionListener(e -> {
            sqlcom("select * from anställda where Namn = ?", textFields[14].getText());
        });


        Font fontanställda = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 10);
        textFields[0] = new JTextField("AnställningsID");
        textFields[0].setBackground(Color.BLACK);
        textFields[0].setBounds(50, 300, 100, 30);

        textFields[1] = new JTextField("Namn");
        textFields[1].setBackground(Color.DARK_GRAY);
        textFields[1].setBounds(150, 300, 100, 30);

        textFields[2] = new JTextField("Adress");
        textFields[2].setBackground(Color.BLACK);
        textFields[2].setBounds(250, 300, 100, 30);

        textFields[3] = new JTextField("Hemnummer");
        textFields[3].setBackground(Color.DARK_GRAY);
        textFields[3].setBounds(350, 300, 100, 30);

        textFields[4] = new JTextField("Mobilnummer");
        textFields[4].setBackground(Color.BLACK);
        textFields[4].setBounds(450, 300, 100, 30);

        textFields[5] = new JTextField("Månadslön");
        textFields[5].setBackground(Color.DARK_GRAY);
        textFields[5].setBounds(550, 300, 100, 30);

        textFields[6] = new JTextField("Semesterdagar_kvar");
        textFields[6].setBackground(Color.DARK_GRAY);
        textFields[6].setBounds(650, 300, 100, 30);


        //-----------------------SKA HÄMTA ID----------------------------
        textFields[7] = new JTextField();
        textFields[7].setBounds(50, 360, 100, 30);
        textFields[7].setEditable(false);

        //----------------------SKA HÄMTA NAMN--------------------------
        textFields[8] = new JTextField("");
        textFields[8].setBounds(150, 360, 100, 30);

        //---------------------SKA HÄMTA ADRESS-------------------------
        textFields[9] = new JTextField("");
        textFields[9].setBounds(250, 360, 100, 30);

        //---------------------SKA HÄMTA HEMNR--------------------------
        textFields[10] = new JTextField("");
        textFields[10].setBounds(350, 360, 100, 30);

        //---------------------SKA HÄMTA MOBILNR-------------------------
        textFields[11] = new JTextField("");
        textFields[11].setBounds(450, 360, 100, 30);

        //------------------------SKA HÄMTA MÅNADSLÖN--------------------
        textFields[12] = new JTextField("");
        textFields[12].setBounds(550, 360, 100, 30);

        //------------------------SKA HÄMTA SEMESTERDAGAR------------------
        textFields[13] = new JTextField("");
        textFields[13].setBounds(650, 360, 100, 30);

        textFields[14] = new JTextField("Sök på en specifik användare");
        textFields[14].setBounds(350, 200, 150, 30);
        textFields[14].addActionListener(e -> {
            sqlcom("select * from anställda where Namn = ?", textFields[14].getText());
        });


        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setVisible(false);
            jp.add(textFields[i]);
        }


        for (int i = 0; i < 7; i++) {

            textFields[i].setFont(fontanställda);
            textFields[i].setForeground(Color.WHITE);
            textFields[i].setEditable(false);

        }
        //-------------------------------------ADDS ALL TO THE PANEL!!------------------------------------------
        jp.add(login);
        jp.add(password);
        jp.add(userName);
        jp.add(passwordInput);
        jp.add(output);
        jp.add(bookTitle);
        for (int i = 0; i < checkboxes.length; i++) {jp.add(checkboxes[i]);}
        for (int i = 0; i < textFields.length; i++) {jp.add(textFields[i]);}
        for (int i = 0; i < buttons.length; i++) {jp.add(buttons[i]);}
        for (int i = 0; i < labels.length; i++) {jp.add(labels[i]);}
        this.add(jp);
        this.setVisible(true);
    }
    public void updateGUI(int LoginTrue) {
        //---------------------------LOGIN GUI --------------------------
        if (LoginTrue == 0) {
            login.setVisible(true);
            userName.setVisible(true);
            password.setVisible(true);
            passwordInput.setVisible(true);
            buttons[0].setVisible(true);
            checkboxes[3].setVisible(true);
            checkboxes[2].setVisible(true);
            checkboxes[4].setVisible(true);

            output.setVisible(false);
            bookTitle.setVisible(false);
            checkboxes[0].setVisible(false);
            checkboxes[1].setVisible(false);
            for (int i = 1; i < buttons.length; i++) {buttons[i].setVisible(false);}
            for (int i = 1; i < labels.length; i++) {labels[i].setVisible(false);labels[i].setText("Output");}
            for (int i = 0; i < textFields.length; i++) {textFields[i].setVisible(false);}
        }
        //-------------------------------** USER GUI **-------------------------------------------------
        if (checkboxes[4].isSelected() && LoginTrue == 1) {
            login.setVisible(false);
            passwordInput.setVisible(false);
            password.setVisible(false);
            userName.setVisible(false);
            checkboxes[3].setVisible(false);
            checkboxes[4].setVisible(false);
            buttons[0].setVisible(false);
            checkboxes[2].setVisible(false);

            output.setVisible(true);
            bookTitle.setVisible(true);
            checkboxes[0].setVisible(true);
            checkboxes[1].setVisible(true);
            for (int i = 1; i < 6; i++) {buttons[i].setVisible(true);}
        }
        //------------------------------------ADMIN GUI----------------------------------------------------
        if (checkboxes[2].isSelected() && LoginTrue == 1) {
            login.setVisible(false);
            passwordInput.setVisible(false);
            password.setVisible(false);
            userName.setVisible(false);
            checkboxes[3].setVisible(false);
            checkboxes[4].setVisible(false);
            buttons[0].setVisible(false);
            checkboxes[2].setVisible(false);

            buttons[1].setVisible(true);
            buttons[6].setVisible(true);
            labels[0].setVisible(true);
        }
        if (checkboxes[3].isSelected() && LoginTrue == 1) {
            login.setVisible(false);
            passwordInput.setVisible(false);
            password.setVisible(false);
            userName.setVisible(false);
            checkboxes[3].setVisible(false);
            checkboxes[4].setVisible(false);
            checkboxes[2].setVisible(false);
            buttons[0].setVisible(false);

            buttons[1].setVisible(true);
            buttons[7].setVisible(true);
            buttons[8].setVisible(true);
            buttons[9].setVisible(true);
            labels[1].setVisible(true);
            for (int i = 2; i <= 6; i++) {buttons[i].setVisible(false);}
            for (int i = 0; i < textFields.length; i++) {textFields[i].setVisible(true);}
        }
    }
    public void sqlcom(String command, String query) {
        try {
            PreparedStatement stmt = user.con.prepareStatement(command);
            stmt.setString(1, query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                textFields[7].setText(rs.getString(1));
                textFields[8].setText(rs.getString(2));
                textFields[9].setText(rs.getString(3));
                textFields[10].setText(rs.getString(4));
                textFields[11].setText(rs.getString(5));
                textFields[12].setText(rs.getString(6));
                textFields[13].setText(rs.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void sqlSelect(String command) {
        String ans = "";
        try {
            PreparedStatement stmt = user.con.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();

            if (command.contains("tidsskrifter")) {
                while (rs.next()) {
                    ans = ans + "\nID: " + (rs.getInt(1) + ", Title: " + rs.getString(2) + ", Date: " + rs.getDate(3) + ", Where: " + rs.getString(4) + ", Utlånad:" + rs.getString(5) + "<br>");
                }
                output.setText("<html>" + ans);
            }
            if (command.contains("Böcker")) {
                while (rs.next()) {
                    ans = ans + "\nID: " + (rs.getInt(1) + ", Title: " + rs.getString(2) + ", Author: " + rs.getString(3) + ", Pages: " + rs.getInt(4) + ", Classifikation: " + rs.getString(5) + ", Utlånad:" + rs.getString(6) + "<br>");

                }
                output.setText("<html>" + ans);
            }
            if (command.contains("anställda")) {
                while (rs.next()) {
                    ans = ans + "Namn:" + (rs.getString(2) + ", Adress:" + rs.getString(3) + ", Hemnr:" + rs.getString(4) + ", Mobinr:" + rs.getString(5) + ", Månadslön:" + rs.getString(6) + ", Semesterdagar:" + rs.getString(7) + "<br><br>");
                }
                labels[1].setText("<html>" + ans);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}