import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;
import javax.swing.*;


public class Mouser {
    public static int randomRange(int max, int min) {
        return (int) (Math.random() * (max - min));
    }

    public static String getApplicationState(boolean state){
        return state ? "The Application is: Activated!" : "The Application is: Disabled!";
    }

    public static void moveMouse(int screenWidth, int screenHeight, CoordSingleton coordSingleton, JLabel cordLabel) {
        int xCoord, yCoord;
        try {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            xCoord = (int) b.getX();
            yCoord = (int) b.getY();
            if (xCoord == coordSingleton.getxCoord() && yCoord == coordSingleton.getyCoord()) {
                xCoord = randomRange(screenWidth, 0);
                yCoord = randomRange(screenHeight, 0);
            }


            coordSingleton.setxCoord(xCoord);
            coordSingleton.setyCoord(yCoord);
            cordLabel.setText(coordSingleton.getCoords());
            // Move the cursor
            Robot robot = new Robot();
            robot.mouseMove(xCoord, yCoord);
        } catch (AWTException e) {
        }
    }

    public static void main(String args[]) {
        MouseState mouseState = new MouseState(false);
        CoordSingleton coordSingleton = CoordSingleton.getInstance();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Swing props Start
        JFrame frame = new JFrame("My Mouser");
        JLabel stateLabel = new JLabel(getApplicationState(mouseState.getIsMouseRunning()));
        JButton button = new JButton();
        JLabel cordLabel = new JLabel(coordSingleton.getCoords());
        //Swing props End

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(mouseState.getIsMouseRunning()) {
                    moveMouse((int) screenSize.getWidth(), (int) screenSize.getHeight(), coordSingleton, cordLabel);
                }
            }

        }, 0, 5, TimeUnit.SECONDS);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        stateLabel.setBounds(50,50, 200,30);
        button.setText("Activate / Disable");
        button.setBounds(45, 100, 200, 50);//x axis, y axis, width, height
        cordLabel.setBounds(50,150, 200,30);


        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mouseState.setIsMouseRunning(!mouseState.getIsMouseRunning());
                stateLabel.setText(getApplicationState(mouseState.getIsMouseRunning()));
                cordLabel.setText(coordSingleton.getCoords());
            }
        });

        frame.add(stateLabel);
        frame.add(button);
        frame.add(cordLabel);
        button.setVisible(true);
        frame.setVisible(true);
    }
}
