/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumble;

import java.awt.BorderLayout;
import javax.swing.*;


/**
 *
 * @author Son Tran
 */
public class Jumble extends JFrame{

    final int FRAME_WIDTH = 600;
    final int FRAME_HEIGHT = 400;
    public static void main(String[] args) {
        JFrame frame = new Jumble();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public Jumble()
    {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setMenuBar();
        setJMenuBar(menuJMenuBar);
        setNorthPanel();
        add(northPanel, BorderLayout.NORTH);
        setCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        setSouthPanel();
        add(southPanel, BorderLayout.SOUTH);
     
    }
    public void setMenuBar(){
        menuJMenuBar = new JMenuBar();
        loadJMenuItem = new JMenuItem("Load Dictionary");
        fileJMenu = new JMenu("File");
        fileJMenu.add(loadJMenuItem);
        menuJMenuBar.add(fileJMenu);
    }
    public void setNorthPanel(){
        northPanel = new JPanel();
        
    }
    public void setCenterPanel(){
        centerPanel = new JPanel();
    }
    public void setSouthPanel(){
        southPanel = new JPanel();
    }
    JMenuBar menuJMenuBar;
    JMenu fileJMenu;
    JMenuItem loadJMenuItem;
    JPanel northPanel;
    JPanel southPanel;
    JPanel centerPanel;
}