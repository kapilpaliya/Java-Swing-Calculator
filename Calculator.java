/***************************************************************
 *     Complete The Calculator Project With Best GUI           *
 ***************************************************************/

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.ComponentOrientation;

//Create Custom Calculator Class
public class Calculator extends JFrame implements ActionListener{
    //Variable Declaration
    Double first = 0.0,second= 0.0,result= 0.0;String operation;
    JButton []btn = new JButton[20]; Container c; int w=70, h=72;
    JTextField res,dis;

    //Create Constructor To Writing Code
    Calculator(){
        //Gate Screen Size
        Toolkit tb = this.getToolkit();
        Dimension d = tb.getScreenSize();
        int width = (int) (d.getWidth()*8/10);
        int height = (int) (d.getHeight()*8/10);

        //Set Frame
        this.setBounds(width/2,height/6,350,550);
        this.setVisible(true);
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set Container
        c = this.getContentPane();
        c.setBackground(Color.BLACK);
        c.setLayout(null);

        //Set TextField Borders
        Border border = BorderFactory.createMatteBorder(2,2,0,2,Color.white);
        Border border1 = BorderFactory.createMatteBorder(0,2,2,2,Color.WHITE);

        //Set Font Size, Color And Face
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,18);
        Font font1 = new Font(Font.SANS_SERIF,Font.BOLD,30);

        //Set Buffer Input And Output Display.
        res  = new JTextField();
        res.setBounds(20,20,c.getWidth()-40,20);
        res.setFont(font);
        res.setBorder(border);
        res.setBackground(Color.white);
        res.setForeground(Color.black);
        res.setEditable(false);
        res.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        c.add(res);

        //Set Result Display.
        dis = new JTextField();
        dis.setBounds(20,40,c.getWidth()-40,50);
        dis.setFont(font1);
        dis.setBorder(border1);
        dis.setBackground(Color.white);
        dis.setForeground(Color.black);
        dis.setEditable(false);
        dis.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        c.add(dis);
        System.out.println(c.getHeight()+"\n"+c.getWidth());
        //Set Position And Name
        for(int i=0;i<btn.length;i++){
            btn[i] = new JButton();
            int[] bond = {20,95,170,245};
            String[] log = {"C","\uF0E7","%","/","7","8","9","*","4","5","6","+","1","2","3","-",".","0","00","="};
            if(i<4){
                btn[i].setBounds(bond[i],110,w,h);
            }
            else if(i<8){
                btn[i].setBounds(bond[i-4],187,w,h);
            }
            else if(i<12){
                btn[i].setBounds(bond[i-8],264,w,h);
            }
            else if(i<16){
                btn[i].setBounds(bond[i-12],341,w,h);
            }
            else if(i<20){
                btn[i].setBounds(bond[i-16],418,w,h);
            }
            btn[i].setText(log[i]);
            btn[i].setFont(font1);
            btn[i].setBorder(null);
            btn[i].addActionListener(this);
            c.add(btn[i]);
        }

        //Set Color Of Button's
        for(int i=0;i<20;i++){
            if (i == 1 || i == 2 || i == 3 || i == 7 || i == 11 || i == 15) {
                btn[i].setBackground(Color.yellow);
                btn[i].setForeground(Color.BLACK);
            } else {
                if (i == 0) {
                    btn[i].setBackground(Color.red);
                    btn[i].setForeground(Color.BLACK);
                } else if (i == 19) {
                    btn[i].setBackground(Color.BLUE);
                    btn[i].setForeground(Color.BLACK);
                } else {
                    btn[i].setBackground(Color.darkGray);
                    btn[i].setForeground(Color.WHITE);
                }
            }
        }
        revalidate();
    }

    //Override The ActionListener Function
    @Override
    public void actionPerformed(ActionEvent e){

        //Text Input Only
        for(int i=4;i<=18;i++){
            if(e.getSource()==btn[i]){
                if(i==7||i==11||i==15){
                    continue;
                }
                String str = dis.getText()+btn[i].getText();
                dis.setText(str);
            }
        }

        //Clear Screen Only
        if(e.getSource()==btn[0]){
                dis.setText("");
                res.setText("");
                first=0.0;
                second=0.0;
                result=0.0;
            }

        //Main Task Of Calculator
        if(!dis.getText().isEmpty()) {
            //Back Button Only
            if (e.getSource() == btn[1]) {
                String str = dis.getText();
                if (str.length() > 0) {
                    StringBuilder val = new StringBuilder(str);
                    val.deleteCharAt(str.length() - 1);
                    System.out.println(val);
                    String tol = val.toString();
                    dis.setText(tol);
                }
            }

            //Modulus Task Button
            if (e.getSource() == btn[2]) {
                if (first > 0) {
                    first = first % (Double.parseDouble(dis.getText()));
                }
                else {
                    first = Double.parseDouble(dis.getText());
                }
                res.setText(String.valueOf(first));
                dis.setText("");
                operation = "%";
            }

            //Division Task Button
            if (e.getSource() == btn[3]) {
                second = Double.parseDouble(dis.getText());
                if (first > 0 && second == 0) {
                    System.out.println("Error Found");
                }
                else if (first > 0) {
                    first = first / (Double.parseDouble(dis.getText()));
                    res.setText(String.valueOf(first));
                    dis.setText("");
                    operation = "/";
                }
                else {
                    first = Double.parseDouble(dis.getText());
                    res.setText(String.valueOf(first));
                    dis.setText("");
                    operation = "/";
                }
            }

            //Multiply Task Button
            if (e.getSource() == btn[7]) {
                if (first <= 0) {
                    first = Double.parseDouble(dis.getText());
                } else {
                    first = first * (Double.parseDouble(dis.getText()));
                }
                res.setText(String.valueOf(first));
                dis.setText("");
                operation = "*";
            }

            //Addition Task Button
            if (e.getSource() == btn[11]) {
                if (first > 0) {
                    first = first + (Double.parseDouble(dis.getText()));
                } else {
                    first = Double.parseDouble(dis.getText());
                }
                res.setText(String.valueOf(first));
                dis.setText("");
                operation = "+";
            }

            //Subtraction Task Button
            if (e.getSource() == btn[15]) {
                if (first > 0) {
                    first = first - (Double.parseDouble(dis.getText()));
                } else {
                    first = Double.parseDouble(dis.getText());
                }
                res.setText(String.valueOf(first));
                dis.setText("");
                operation = "-";
            }

            //Equal Task Button
            if (e.getSource() == btn[19]) {
                switch (operation) {
                    case "%" :
                        result = first % (Double.parseDouble(dis.getText()));
                        dis.setText(String.valueOf(result));
                        first = 0.0;
                        res.setText("");
                        break;
                    case "/" :
                        result = first / (Double.parseDouble(dis.getText()));
                        dis.setText(String.valueOf(result));
                        first = 0.0;
                        res.setText("");
                        break;
                    case "*" :
                        result = first * (Double.parseDouble(dis.getText()));
                        dis.setText(String.valueOf(result));
                        first = 0.0;
                        res.setText("");
                        break;
                    case "+" :
                        result = first + (Double.parseDouble(dis.getText()));
                        dis.setText(String.valueOf(result));
                        first = 0.0;
                        res.setText("");
                        break;
                    case "-" :
                        result = first - (Double.parseDouble(dis.getText()));
                        dis.setText(String.valueOf(result));
                        first = 0.0;
                        res.setText("");
                        break;
                    default : throw new IllegalStateException("Unexpected value: " + operation);
                }
            }
        }
    }
    public static void main(String[] args) {
        new Calculator();
    }
}