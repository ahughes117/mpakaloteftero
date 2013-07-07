
package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The abstract GUI class for the frames.
 * 
 * @author Alex Hughes
 */
public abstract class GUI extends JFrame {

    public void setFrameLocationCenter(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = aFrame.getSize();

        int windowX = Math.max(0, (screenSize.width - windowSize.width) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

        aFrame.setLocation(windowX, windowY);
    }
    
 public void popupMenuField(JTextField aTf) {

        JPopupMenu popupMenu = new JPopupMenu();
        ActionListener actionListener = new PopupActionListener(aTf);
        // Cut
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.addActionListener(actionListener);
        popupMenu.add(cutMenuItem);
        // Copy
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.addActionListener(actionListener);
        popupMenu.add(copyMenuItem);
        // Paste
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.addActionListener(actionListener);
        popupMenu.add(pasteMenuItem);

        aTf.setComponentPopupMenu(popupMenu);
        
    }

    public void popupMenuArea(JTextArea aTa) {

        JPopupMenu popupMenu = new JPopupMenu();
        ActionListener actionListener = new PopupActionListener(aTa);
        // Cut
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.addActionListener(actionListener);
        popupMenu.add(cutMenuItem);
        // Copy
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.addActionListener(actionListener);
        popupMenu.add(copyMenuItem);
        // Paste
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.addActionListener(actionListener);
        popupMenu.add(pasteMenuItem);

        aTa.setComponentPopupMenu(popupMenu);
    }
}
class PopupActionListener implements ActionListener {

    private JTextField tf;
    private JTextArea ta;

    public PopupActionListener(JTextField Tf) {
        tf = Tf;
    }

    public PopupActionListener(JTextArea Ta) {
        ta = Ta;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Cut")) {
            if (tf != null) {
                tf.cut();
            } else {
                ta.cut();
            }
        }
        if (actionEvent.getActionCommand().equals("Copy")) {
            if (tf != null) {
                tf.copy();
            } else {
                ta.copy();
            }
        }
        if (actionEvent.getActionCommand().equals("Paste")) {
            if (tf != null) {
                tf.paste();
            } else {
                ta.paste();
            }
        }
    }
}

