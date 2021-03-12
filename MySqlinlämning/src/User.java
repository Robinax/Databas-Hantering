import java.sql.Connection;
import java.sql.DriverManager;

public class User {
    private int loginCheck = 0;
    Connection con;
    public User(){    }
    public void getusername(String name, String password, Gui gui){
        String input = "-1";
        if (input.equals("-1")) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/Bibliotek", name, password);
                loginCheck = 1;
                gui.updateGUI(loginCheck);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    }

