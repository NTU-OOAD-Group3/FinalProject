import org.hotelsystem.view.MainFrame;
import org.hotelsystem.control.MainControl;

public class main {
    public static void main(String[] args) {
        MainControl mainControl = new MainControl();
        MainFrame mainFrame = new MainFrame(mainControl);
    }
}