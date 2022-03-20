/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package virtualpiano;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.JButton;

/**
 *
 * @author 84946
 */
public class Piano extends javax.swing.JDialog implements KeyListener {

    /**
     * Creates new form Piano
     */
    Synthesizer synth;
    MidiChannel[] mc;
    int pitch;
    private String keys = "ZXCVBNM,./ASDFGHJKL;QWERTYUIOP1234567890-";

    private boolean[] keyOn = new boolean[keys.length()];
//    private boolean C,D,Dm,E,F,G,A,B,Am,Em; 
    Map<Integer, Integer> map;
    Map<Integer, JButton> mapButton;

    public Piano(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Piano");
        addKeyListener(this);
        map = new HashMap<Integer, Integer>();
        mapButton = new HashMap<Integer, JButton>();
        initMap();

        pitch = 0;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            mc = synth.getChannels();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println(e.toString());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int noteIndex = keys.indexOf((char) e.getKeyCode());
//        System.out.println((char) e.getKeyCode() == '1');
        if (noteIndex < 0 || keyOn[noteIndex]) {
            return;
        }
        keyOn[noteIndex] = true;
        if (noteIndex >= 30) {
            switch (noteIndex) {
                case 30://C
                    mc[5].noteOn(48 + pitch, 50);
                    mc[5].noteOn(52 + pitch, 50);
                    mc[5].noteOn(55 + pitch, 50);
                    Cc.setBackground(Color.green);
                    C4.setBackground(Color.green);
                    E4.setBackground(Color.green);
                    G4.setBackground(Color.green);
                    break;
                case 31://D
                    mc[5].noteOn(50 + pitch, 50);
                    mc[5].noteOn(54 + pitch, 50);
                    mc[5].noteOn(57 + pitch, 50);
                    Dc.setBackground(Color.green);
                    D4.setBackground(Color.green);
                    F4t.setBackground(Color.green);
                    A4.setBackground(Color.green);
                    break;
                case 32: //dm
                    mc[5].noteOn(50 + pitch, 50);
                    mc[5].noteOn(53 + pitch, 50);
                    mc[5].noteOn(57 + pitch, 50);
                    Dmc.setBackground(Color.green);
                    D4.setBackground(Color.green);
                    F4.setBackground(Color.green);
                    A4.setBackground(Color.green);
                    break;
                case 33: //E
                    mc[5].noteOn(52 + pitch, 50);
                    mc[5].noteOn(56 + pitch, 50);
                    mc[5].noteOn(59 + pitch, 50);
                    Ec.setBackground(Color.green);
                    E4.setBackground(Color.green);
                    G4t.setBackground(Color.green);
                    B4.setBackground(Color.green);
                    break;
                case 34: //Em
                    mc[5].noteOn(52 + pitch, 50);
                    mc[5].noteOn(55 + pitch, 50);
                    mc[5].noteOn(59 + pitch, 50);
                    Emc.setBackground(Color.green);
                    E4.setBackground(Color.green);
                    G4.setBackground(Color.green);
                    B4.setBackground(Color.green);
                    break;
                case 35: //F
                    mc[5].noteOn(53 + pitch, 50);
                    mc[5].noteOn(57 + pitch, 50);
                    mc[5].noteOn(60 + pitch, 50);
                    Fc.setBackground(Color.green);
                    F4.setBackground(Color.green);
                    A4.setBackground(Color.green);
                    C4.setBackground(Color.green);
                    break;
                case 36: //G
                    mc[5].noteOn(55 + pitch, 50);
                    mc[5].noteOn(59 + pitch, 50);
                    mc[5].noteOn(62 + pitch, 50);
                    Gc.setBackground(Color.green);
                    G4.setBackground(Color.green);
                    B4.setBackground(Color.green);
                    D4.setBackground(Color.green);
                    break;
                case 37: //A
                    mc[5].noteOn(57 + pitch, 50);
                    mc[5].noteOn(61 + pitch, 50);
                    mc[5].noteOn(64 + pitch, 50);
                    Ac.setBackground(Color.green);
                    A4.setBackground(Color.green);
                    C4t.setBackground(Color.green);
                    E4.setBackground(Color.green);
                    break;
                case 38: //Am
                    mc[5].noteOn(57 + pitch, 50);
                    mc[5].noteOn(60 + pitch, 50);
                    mc[5].noteOn(64 + pitch, 50);
                    Amc.setBackground(Color.green);

                    A4.setBackground(Color.green);
                    C4.setBackground(Color.green);
                    E4.setBackground(Color.green);
                    break;
                case 39: //B
                    mc[5].noteOn(59 + pitch, 50);
                    mc[5].noteOn(63 + pitch, 50);
                    mc[5].noteOn(66 + pitch, 50);
                    Bc.setBackground(Color.green);
                    B4.setBackground(Color.green);
                    D4t.setBackground(Color.green);
                    F4t.setBackground(Color.green);
                    break;
                case 40: // Bm
                    mc[5].noteOn(59 + pitch, 50);
                    mc[5].noteOn(62 + pitch, 50);
                    mc[5].noteOn(66 + pitch, 50);
                    Bmc.setBackground(Color.green);
                    B4.setBackground(Color.green);
                    D4.setBackground(Color.green);
                    F4t.setBackground(Color.green);
                    break;

            }
        } else {
            keyOn[noteIndex] = true;
            mc[5].noteOn(map.get(noteIndex) + pitch, 100);
            mapButton.get(noteIndex).setBackground(Color.green);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int noteIndex = keys.indexOf((char) e.getKeyCode());
        if (noteIndex < 0) {
            return;
        }
        if (noteIndex >= 30) {
            keyOn[noteIndex] = false;
            switch (noteIndex) {
                case 30://C
                    mc[5].noteOff(48 + pitch, 100);
                    mc[5].noteOff(52 + pitch, 100);
                    mc[5].noteOff(55 + pitch, 100);
                    Cc.setBackground(null);
                    C4.setBackground(null);
                    E4.setBackground(null);
                    G4.setBackground(null);
                    break;
                case 31://D
                    mc[5].noteOff(50 + pitch, 100);
                    mc[5].noteOff(54 + pitch, 100);
                    mc[5].noteOff(57 + pitch, 100);
                    Dc.setBackground(null);
                    D4.setBackground(null);
                    F4t.setBackground(C3t.getBackground());
                    A4.setBackground(null);
                    break;
                case 32: //dm
                    mc[5].noteOff(50 + pitch, 100);
                    mc[5].noteOff(53 + pitch, 100);
                    mc[5].noteOff(57 + pitch, 100);
                    Dmc.setBackground(null);
                    D4.setBackground(null);
                    F4.setBackground(null);
                    A4.setBackground(null);
                    break;
                case 33: //E
                    mc[5].noteOff(52 + pitch, 100);
                    mc[5].noteOff(56 + pitch, 100);
                    mc[5].noteOff(59 + pitch, 100);
                    Ec.setBackground(null);
                    E4.setBackground(null);
                    G4t.setBackground(C3t.getBackground());
                    B4.setBackground(null);
                    break;
                case 34: //Em
                    mc[5].noteOff(52 + pitch, 100);
                    mc[5].noteOff(55 + pitch, 100);
                    mc[5].noteOff(59 + pitch, 100);
                    Emc.setBackground(null);
                    E4.setBackground(null);
                    G4.setBackground(null);
                    B4.setBackground(null);
                    break;
                case 35: //F
                    mc[5].noteOff(53 + pitch, 100);
                    mc[5].noteOff(57 + pitch, 100);
                    mc[5].noteOff(60 + pitch, 100);
                    Fc.setBackground(null);
                    F4.setBackground(null);
                    A4.setBackground(null);
                    C4.setBackground(null);
                    break;
                case 36: //G
                    mc[5].noteOff(55 + pitch, 100);
                    mc[5].noteOff(59 + pitch, 100);
                    mc[5].noteOff(62 + pitch, 100);
                    Gc.setBackground(null);
                    G4.setBackground(null);
                    B4.setBackground(null);
                    D4.setBackground(null);
                    break;
                case 37: //A
                    mc[5].noteOff(57 + pitch, 100);
                    mc[5].noteOff(61 + pitch, 100);
                    mc[5].noteOff(64 + pitch, 100);
                    Ac.setBackground(null);
                    A4.setBackground(null);
                    C4t.setBackground(C3t.getBackground());
                    E4.setBackground(null);
                    break;
                case 38: //Am
                    mc[5].noteOff(57 + pitch, 100);
                    mc[5].noteOff(60 + pitch, 100);
                    mc[5].noteOff(64 + pitch, 100);
                    Amc.setBackground(null);

                    A4.setBackground(null);
                    C4.setBackground(null);
                    E4.setBackground(null);
                    break;
                case 39: //B
                    mc[5].noteOff(59 + pitch, 100);
                    mc[5].noteOff(63 + pitch, 100);
                    mc[5].noteOff(66 + pitch, 100);
                    Bc.setBackground(null);
                    B4.setBackground(null);
                    D4t.setBackground(C3t.getBackground());
                    F4t.setBackground(C3t.getBackground());
                    break;
                case 40: // Bm
                    mc[5].noteOff(59 + pitch, 100);
                    mc[5].noteOff(62 + pitch, 100);
                    mc[5].noteOff(66 + pitch, 100);
                    Bmc.setBackground(null);
                    B4.setBackground(null);
                    D4.setBackground(null);
                    F4t.setBackground(C3t.getBackground());
                    break;

            }
        } else {
            keyOn[noteIndex] = false;
            mc[5].noteOff(map.get(noteIndex) + pitch, 100);
            mapButton.get(noteIndex).setBackground(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        F3t = new javax.swing.JButton();
        C3t = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        D3t = new javax.swing.JButton();
        E3 = new javax.swing.JButton();
        D3 = new javax.swing.JButton();
        G3t = new javax.swing.JButton();
        G3 = new javax.swing.JButton();
        F3 = new javax.swing.JButton();
        A3t = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        C4t = new javax.swing.JButton();
        G4t = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        D4t = new javax.swing.JButton();
        E4 = new javax.swing.JButton();
        D4 = new javax.swing.JButton();
        F4t = new javax.swing.JButton();
        F4 = new javax.swing.JButton();
        G4 = new javax.swing.JButton();
        A4t = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        C5t = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        D5t = new javax.swing.JButton();
        E5 = new javax.swing.JButton();
        D5 = new javax.swing.JButton();
        G5t = new javax.swing.JButton();
        F5t = new javax.swing.JButton();
        G5 = new javax.swing.JButton();
        F5 = new javax.swing.JButton();
        A5t = new javax.swing.JButton();
        A5 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        C6t = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        D6t = new javax.swing.JButton();
        E6 = new javax.swing.JButton();
        D6 = new javax.swing.JButton();
        G6t = new javax.swing.JButton();
        F6t = new javax.swing.JButton();
        G6 = new javax.swing.JButton();
        F6 = new javax.swing.JButton();
        A6t = new javax.swing.JButton();
        A6 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        C7t = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        D7t = new javax.swing.JButton();
        E7 = new javax.swing.JButton();
        D7 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        Emc = new javax.swing.JButton();
        Bmc = new javax.swing.JButton();
        Dc = new javax.swing.JButton();
        Dmc = new javax.swing.JButton();
        Ec = new javax.swing.JButton();
        Cc = new javax.swing.JButton();
        Fc = new javax.swing.JButton();
        Gc = new javax.swing.JButton();
        Ac = new javax.swing.JButton();
        Amc = new javax.swing.JButton();
        Bc = new javax.swing.JButton();
        SheetView = new javax.swing.JPanel();
        c = new javax.swing.JButton();
        x = new javax.swing.JButton();
        v = new javax.swing.JButton();
        b = new javax.swing.JButton();
        n = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        dot = new javax.swing.JButton();
        comma = new javax.swing.JButton();
        m = new javax.swing.JButton();
        d = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        s = new javax.swing.JButton();
        f = new javax.swing.JButton();
        g = new javax.swing.JButton();
        h = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        l = new javax.swing.JButton();
        k = new javax.swing.JButton();
        j = new javax.swing.JButton();
        e = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        w = new javax.swing.JButton();
        r = new javax.swing.JButton();
        t = new javax.swing.JButton();
        y = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        o = new javax.swing.JButton();
        i = new javax.swing.JButton();
        u = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        two = new javax.swing.JButton();
        three = new javax.swing.JButton();
        four = new javax.swing.JButton();
        five = new javax.swing.JButton();
        six = new javax.swing.JButton();
        seven = new javax.swing.JButton();
        eight = new javax.swing.JButton();
        nine = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        zero = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        z = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        one = new javax.swing.JButton();
        a = new javax.swing.JButton();
        q = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        p = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        semicolon = new javax.swing.JButton();
        slash = new javax.swing.JButton();
        apostrophe = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setName("Piano"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        F3t.setBackground(new java.awt.Color(0, 0, 0));
        F3t.setFocusPainted(false);
        F3t.setFocusable(false);
        F3t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F3t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F3tActionPerformed(evt);
            }
        });
        jPanel1.add(F3t, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 446, 36, 100));

        C3t.setBackground(new java.awt.Color(0, 0, 0));
        C3t.setFocusPainted(false);
        C3t.setFocusable(false);
        C3t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C3t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3tActionPerformed(evt);
            }
        });
        jPanel1.add(C3t, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 446, 36, 100));

        C3.setText("C");
        C3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C3.setDefaultCapable(false);
        C3.setDoubleBuffered(true);
        C3.setFocusPainted(false);
        C3.setFocusable(false);
        C3.setInheritsPopupMenu(true);
        C3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });
        jPanel1.add(C3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 446, 40, 160));

        D3t.setBackground(new java.awt.Color(0, 0, 0));
        D3t.setFocusPainted(false);
        D3t.setFocusable(false);
        D3t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D3t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D3tActionPerformed(evt);
            }
        });
        jPanel1.add(D3t, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 446, 36, 100));

        E3.setFocusPainted(false);
        E3.setFocusable(false);
        E3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E3ActionPerformed(evt);
            }
        });
        jPanel1.add(E3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 446, 40, 160));

        D3.setFocusPainted(false);
        D3.setFocusable(false);
        D3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        D3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D3ActionPerformed(evt);
            }
        });
        jPanel1.add(D3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 446, 40, 160));

        G3t.setBackground(new java.awt.Color(0, 0, 0));
        G3t.setFocusPainted(false);
        G3t.setFocusable(false);
        G3t.setInheritsPopupMenu(true);
        G3t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G3t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G3tActionPerformed(evt);
            }
        });
        jPanel1.add(G3t, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 446, 36, 100));

        G3.setFocusPainted(false);
        G3.setFocusable(false);
        G3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G3ActionPerformed(evt);
            }
        });
        jPanel1.add(G3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 446, 40, 160));

        F3.setFocusPainted(false);
        F3.setFocusable(false);
        F3.setRequestFocusEnabled(false);
        F3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F3ActionPerformed(evt);
            }
        });
        jPanel1.add(F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 446, 40, 160));

        A3t.setBackground(new java.awt.Color(0, 0, 0));
        A3t.setFocusable(false);
        A3t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3tActionPerformed(evt);
            }
        });
        jPanel1.add(A3t, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 446, 36, 100));

        A3.setFocusable(false);
        A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3ActionPerformed(evt);
            }
        });
        jPanel1.add(A3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 446, 40, 160));

        B3.setFocusable(false);
        B3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });
        jPanel1.add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 446, 40, 160));

        C4t.setBackground(new java.awt.Color(0, 0, 0));
        C4t.setFocusPainted(false);
        C4t.setFocusable(false);
        C4t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C4t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4tActionPerformed(evt);
            }
        });
        jPanel1.add(C4t, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 446, 36, 100));

        G4t.setBackground(new java.awt.Color(0, 0, 0));
        G4t.setFocusPainted(false);
        G4t.setFocusable(false);
        G4t.setInheritsPopupMenu(true);
        G4t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G4t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G4tActionPerformed(evt);
            }
        });
        jPanel1.add(G4t, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 446, 36, 100));

        C4.setText("C");
        C4.setDefaultCapable(false);
        C4.setFocusPainted(false);
        C4.setFocusable(false);
        C4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                C4MouseReleased(evt);
            }
        });
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });
        jPanel1.add(C4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 446, 40, 160));

        D4t.setBackground(new java.awt.Color(0, 0, 0));
        D4t.setFocusPainted(false);
        D4t.setFocusable(false);
        D4t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D4t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D4tActionPerformed(evt);
            }
        });
        jPanel1.add(D4t, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 446, 36, 100));

        E4.setFocusPainted(false);
        E4.setFocusable(false);
        E4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E4ActionPerformed(evt);
            }
        });
        jPanel1.add(E4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 446, 40, 160));

        D4.setFocusPainted(false);
        D4.setFocusable(false);
        D4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        D4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D4ActionPerformed(evt);
            }
        });
        jPanel1.add(D4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 446, 40, 160));

        F4t.setBackground(new java.awt.Color(0, 0, 0));
        F4t.setFocusPainted(false);
        F4t.setFocusable(false);
        F4t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F4t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F4tActionPerformed(evt);
            }
        });
        jPanel1.add(F4t, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 446, 36, 100));

        F4.setFocusPainted(false);
        F4.setFocusable(false);
        F4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F4ActionPerformed(evt);
            }
        });
        jPanel1.add(F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 446, 40, 160));

        G4.setFocusPainted(false);
        G4.setFocusable(false);
        G4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G4ActionPerformed(evt);
            }
        });
        jPanel1.add(G4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 446, 40, 160));

        A4t.setBackground(new java.awt.Color(0, 0, 0));
        A4t.setFocusable(false);
        A4t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4tActionPerformed(evt);
            }
        });
        jPanel1.add(A4t, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 446, 36, 100));

        B4.setFocusable(false);
        B4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });
        jPanel1.add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 446, 40, 160));

        C5t.setBackground(new java.awt.Color(0, 0, 0));
        C5t.setFocusPainted(false);
        C5t.setFocusable(false);
        C5t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C5t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5tActionPerformed(evt);
            }
        });
        jPanel1.add(C5t, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 446, 36, 100));

        A4.setFocusable(false);
        A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4ActionPerformed(evt);
            }
        });
        jPanel1.add(A4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 446, 40, 160));

        C5.setText("C");
        C5.setDefaultCapable(false);
        C5.setFocusPainted(false);
        C5.setFocusable(false);
        C5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });
        jPanel1.add(C5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 446, 40, 160));

        D5t.setBackground(new java.awt.Color(0, 0, 0));
        D5t.setFocusPainted(false);
        D5t.setFocusable(false);
        D5t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D5t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D5tActionPerformed(evt);
            }
        });
        jPanel1.add(D5t, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 446, 36, 100));

        E5.setFocusPainted(false);
        E5.setFocusable(false);
        E5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E5ActionPerformed(evt);
            }
        });
        jPanel1.add(E5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 446, 40, 160));

        D5.setFocusPainted(false);
        D5.setFocusable(false);
        D5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        D5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D5ActionPerformed(evt);
            }
        });
        jPanel1.add(D5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 446, 40, 160));

        G5t.setBackground(new java.awt.Color(0, 0, 0));
        G5t.setFocusPainted(false);
        G5t.setFocusable(false);
        G5t.setInheritsPopupMenu(true);
        G5t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G5t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G5tActionPerformed(evt);
            }
        });
        jPanel1.add(G5t, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 446, 36, 100));

        F5t.setBackground(new java.awt.Color(0, 0, 0));
        F5t.setFocusPainted(false);
        F5t.setFocusable(false);
        F5t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F5t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F5tActionPerformed(evt);
            }
        });
        jPanel1.add(F5t, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 446, 36, 100));

        G5.setFocusPainted(false);
        G5.setFocusable(false);
        G5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G5ActionPerformed(evt);
            }
        });
        jPanel1.add(G5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 446, 40, 160));

        F5.setFocusPainted(false);
        F5.setFocusable(false);
        F5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F5ActionPerformed(evt);
            }
        });
        jPanel1.add(F5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 446, 40, 160));

        A5t.setBackground(new java.awt.Color(0, 0, 0));
        A5t.setFocusable(false);
        A5t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A5tActionPerformed(evt);
            }
        });
        jPanel1.add(A5t, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 446, 36, 100));

        A5.setFocusable(false);
        A5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A5ActionPerformed(evt);
            }
        });
        jPanel1.add(A5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 446, 40, 160));

        B5.setFocusable(false);
        B5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });
        jPanel1.add(B5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 446, 40, 160));

        C6t.setBackground(new java.awt.Color(0, 0, 0));
        C6t.setFocusPainted(false);
        C6t.setFocusable(false);
        C6t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C6t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6tActionPerformed(evt);
            }
        });
        jPanel1.add(C6t, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 446, 36, 100));

        C6.setText("C");
        C6.setDefaultCapable(false);
        C6.setFocusPainted(false);
        C6.setFocusable(false);
        C6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });
        jPanel1.add(C6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 446, 40, 160));

        D6t.setBackground(new java.awt.Color(0, 0, 0));
        D6t.setFocusPainted(false);
        D6t.setFocusable(false);
        D6t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D6t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D6tActionPerformed(evt);
            }
        });
        jPanel1.add(D6t, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 446, 36, 100));

        E6.setFocusPainted(false);
        E6.setFocusable(false);
        E6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E6ActionPerformed(evt);
            }
        });
        jPanel1.add(E6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 446, 40, 160));

        D6.setFocusPainted(false);
        D6.setFocusable(false);
        D6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        D6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D6ActionPerformed(evt);
            }
        });
        jPanel1.add(D6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 446, 40, 160));

        G6t.setBackground(new java.awt.Color(0, 0, 0));
        G6t.setFocusPainted(false);
        G6t.setFocusable(false);
        G6t.setInheritsPopupMenu(true);
        G6t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G6t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G6tActionPerformed(evt);
            }
        });
        jPanel1.add(G6t, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 446, 36, 100));

        F6t.setBackground(new java.awt.Color(0, 0, 0));
        F6t.setFocusPainted(false);
        F6t.setFocusable(false);
        F6t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F6t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F6tActionPerformed(evt);
            }
        });
        jPanel1.add(F6t, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 446, 36, 100));

        G6.setFocusPainted(false);
        G6.setFocusable(false);
        G6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G6ActionPerformed(evt);
            }
        });
        jPanel1.add(G6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 446, 40, 160));

        F6.setFocusPainted(false);
        F6.setFocusable(false);
        F6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        F6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F6ActionPerformed(evt);
            }
        });
        jPanel1.add(F6, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 446, 40, 160));

        A6t.setBackground(new java.awt.Color(0, 0, 0));
        A6t.setFocusable(false);
        A6t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A6tActionPerformed(evt);
            }
        });
        jPanel1.add(A6t, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 446, 36, 100));

        A6.setFocusable(false);
        A6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A6ActionPerformed(evt);
            }
        });
        jPanel1.add(A6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 446, 40, 160));

        B6.setFocusable(false);
        B6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });
        jPanel1.add(B6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 446, 40, 160));

        C7t.setBackground(new java.awt.Color(0, 0, 0));
        C7t.setFocusPainted(false);
        C7t.setFocusable(false);
        C7t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C7t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7tActionPerformed(evt);
            }
        });
        jPanel1.add(C7t, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 446, 36, 100));

        C7.setText("C");
        C7.setDefaultCapable(false);
        C7.setFocusPainted(false);
        C7.setFocusable(false);
        C7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });
        jPanel1.add(C7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 446, 40, 160));

        D7t.setBackground(new java.awt.Color(0, 0, 0));
        D7t.setFocusPainted(false);
        D7t.setFocusable(false);
        D7t.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D7t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D7tActionPerformed(evt);
            }
        });
        jPanel1.add(D7t, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 446, 36, 100));

        E7.setFocusPainted(false);
        E7.setFocusable(false);
        E7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E7ActionPerformed(evt);
            }
        });
        jPanel1.add(E7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 446, 40, 160));

        D7.setFocusPainted(false);
        D7.setFocusable(false);
        D7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        D7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D7ActionPerformed(evt);
            }
        });
        jPanel1.add(D7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 446, 40, 160));

        jButton19.setText("+");
        jButton19.setFocusable(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 70, 30));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pitch : "+ pitch);
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setInheritsPopupMenu(false);
        jLabel1.setRequestFocusEnabled(false);
        jLabel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 70, 30));

        jButton18.setText("-");
        jButton18.setFocusable(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 70, 30));

        Emc.setText("Em (5)");
        Emc.setFocusable(false);
        Emc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmcActionPerformed(evt);
            }
        });
        jPanel1.add(Emc, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 80, 80));

        Bmc.setText("Bm(-)");
        Bmc.setFocusable(false);
        Bmc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmcActionPerformed(evt);
            }
        });
        jPanel1.add(Bmc, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 80, 80));

        Dc.setText("D(2)");
        Dc.setFocusable(false);
        Dc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DcActionPerformed(evt);
            }
        });
        jPanel1.add(Dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 80, 80));

        Dmc.setText("Dm(3)");
        Dmc.setFocusable(false);
        Dmc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DmcActionPerformed(evt);
            }
        });
        jPanel1.add(Dmc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 80, 80));

        Ec.setText("E(4)");
        Ec.setFocusable(false);
        Ec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EcActionPerformed(evt);
            }
        });
        jPanel1.add(Ec, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 80, 80));

        Cc.setText("C (1)");
        Cc.setFocusable(false);
        Cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CcActionPerformed(evt);
            }
        });
        jPanel1.add(Cc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 80, 80));

        Fc.setText("F(6)");
        Fc.setFocusable(false);
        Fc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FcActionPerformed(evt);
            }
        });
        jPanel1.add(Fc, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 80, 80));

        Gc.setText("G(7)");
        Gc.setFocusable(false);
        Gc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GcActionPerformed(evt);
            }
        });
        jPanel1.add(Gc, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 80, 80));

        Ac.setText("A(8)");
        Ac.setFocusable(false);
        Ac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcActionPerformed(evt);
            }
        });
        jPanel1.add(Ac, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 80, 80));

        Amc.setText("Am(9)");
        Amc.setFocusable(false);
        Amc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmcActionPerformed(evt);
            }
        });
        jPanel1.add(Amc, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 80, 80));

        Bc.setText("B(0)");
        Bc.setFocusable(false);
        Bc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcActionPerformed(evt);
            }
        });
        jPanel1.add(Bc, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 80, 80));

        SheetView.setFocusable(false);
        SheetView.setLayout(null);

        c.setText("C");
        c.setFocusable(false);
        c.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cMouseReleased(evt);
            }
        });
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });
        SheetView.add(c);
        c.setBounds(180, 170, 40, 40);

        x.setText("X");
        x.setFocusable(false);
        x.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                xMouseReleased(evt);
            }
        });
        x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xActionPerformed(evt);
            }
        });
        SheetView.add(x);
        x.setBounds(140, 170, 40, 40);

        v.setText("V");
        v.setFocusable(false);
        v.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vMouseReleased(evt);
            }
        });
        SheetView.add(v);
        v.setBounds(220, 170, 40, 40);

        b.setText("B");
        b.setFocusable(false);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bMouseReleased(evt);
            }
        });
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });
        SheetView.add(b);
        b.setBounds(260, 170, 40, 40);

        n.setText("N");
        n.setFocusable(false);
        n.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nMouseReleased(evt);
            }
        });
        n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nActionPerformed(evt);
            }
        });
        SheetView.add(n);
        n.setBounds(300, 170, 40, 40);

        jButton12.setBackground(new java.awt.Color(153, 153, 153));
        jButton12.setText("Shift");
        jButton12.setEnabled(false);
        jButton12.setFocusable(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        SheetView.add(jButton12);
        jButton12.setBounds(500, 170, 120, 40);

        dot.setText(".");
        dot.setFocusable(false);
        dot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dotMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dotMouseReleased(evt);
            }
        });
        dot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotActionPerformed(evt);
            }
        });
        SheetView.add(dot);
        dot.setBounds(420, 170, 40, 40);

        comma.setText(",");
        comma.setFocusable(false);
        comma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                commaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                commaMouseReleased(evt);
            }
        });
        SheetView.add(comma);
        comma.setBounds(380, 170, 40, 40);

        m.setText("M");
        m.setFocusable(false);
        m.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mMouseReleased(evt);
            }
        });
        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });
        SheetView.add(m);
        m.setBounds(340, 170, 40, 40);

        d.setText("D");
        d.setFocusable(false);
        d.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dMouseReleased(evt);
            }
        });
        d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dActionPerformed(evt);
            }
        });
        SheetView.add(d);
        d.setBounds(160, 130, 40, 40);

        jButton9.setBackground(new java.awt.Color(153, 153, 153));
        jButton9.setText("CpLk");
        jButton9.setEnabled(false);
        jButton9.setFocusable(false);
        SheetView.add(jButton9);
        jButton9.setBounds(10, 130, 70, 40);

        s.setText("S");
        s.setFocusable(false);
        s.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sMouseReleased(evt);
            }
        });
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });
        SheetView.add(s);
        s.setBounds(120, 130, 40, 40);

        f.setText("F");
        f.setFocusable(false);
        f.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fMouseReleased(evt);
            }
        });
        f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fActionPerformed(evt);
            }
        });
        SheetView.add(f);
        f.setBounds(200, 130, 40, 40);

        g.setText("G");
        g.setFocusable(false);
        g.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gMouseReleased(evt);
            }
        });
        g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gActionPerformed(evt);
            }
        });
        SheetView.add(g);
        g.setBounds(240, 130, 40, 40);

        h.setText("H");
        h.setFocusable(false);
        h.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hMouseReleased(evt);
            }
        });
        h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hActionPerformed(evt);
            }
        });
        SheetView.add(h);
        h.setBounds(280, 130, 40, 40);

        jButton20.setBackground(new java.awt.Color(153, 153, 153));
        jButton20.setText("Enter");
        jButton20.setEnabled(false);
        jButton20.setFocusable(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        SheetView.add(jButton20);
        jButton20.setBounds(520, 130, 100, 40);

        l.setText("L");
        l.setFocusable(false);
        l.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lMouseReleased(evt);
            }
        });
        l.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lActionPerformed(evt);
            }
        });
        SheetView.add(l);
        l.setBounds(400, 130, 40, 40);

        k.setText("K");
        k.setFocusable(false);
        k.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                kMouseReleased(evt);
            }
        });
        k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kActionPerformed(evt);
            }
        });
        SheetView.add(k);
        k.setBounds(360, 130, 40, 40);

        j.setText("J");
        j.setFocusable(false);
        j.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMouseReleased(evt);
            }
        });
        j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActionPerformed(evt);
            }
        });
        SheetView.add(j);
        j.setBounds(320, 130, 40, 40);

        e.setText("E");
        e.setFocusable(false);
        e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                eMouseReleased(evt);
            }
        });
        e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eActionPerformed(evt);
            }
        });
        SheetView.add(e);
        e.setBounds(150, 90, 40, 40);

        jButton25.setBackground(new java.awt.Color(153, 153, 153));
        jButton25.setText("tab");
        jButton25.setEnabled(false);
        jButton25.setFocusable(false);
        SheetView.add(jButton25);
        jButton25.setBounds(10, 90, 60, 40);

        w.setText("W");
        w.setFocusable(false);
        w.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                wMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                wMouseReleased(evt);
            }
        });
        w.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wActionPerformed(evt);
            }
        });
        SheetView.add(w);
        w.setBounds(110, 90, 40, 40);

        r.setText("R");
        r.setFocusable(false);
        r.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rMouseReleased(evt);
            }
        });
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });
        SheetView.add(r);
        r.setBounds(190, 90, 40, 40);

        t.setText("T");
        t.setFocusable(false);
        t.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tMouseReleased(evt);
            }
        });
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });
        SheetView.add(t);
        t.setBounds(230, 90, 40, 40);

        y.setText("Y");
        y.setFocusable(false);
        y.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                yMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                yMouseReleased(evt);
            }
        });
        y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yActionPerformed(evt);
            }
        });
        SheetView.add(y);
        y.setBounds(270, 90, 40, 40);

        jButton30.setBackground(new java.awt.Color(153, 153, 153));
        jButton30.setText("\\");
            jButton30.setEnabled(false);
            jButton30.setFocusable(false);
            jButton30.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton30ActionPerformed(evt);
                }
            });
            SheetView.add(jButton30);
            jButton30.setBounds(550, 90, 70, 40);

            o.setText("O");
            o.setFocusable(false);
            o.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    oMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    oMouseReleased(evt);
                }
            });
            o.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    oActionPerformed(evt);
                }
            });
            SheetView.add(o);
            o.setBounds(390, 90, 40, 40);

            i.setText("I");
            i.setFocusable(false);
            i.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    iMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    iMouseReleased(evt);
                }
            });
            i.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    iActionPerformed(evt);
                }
            });
            SheetView.add(i);
            i.setBounds(350, 90, 40, 40);

            u.setText("U");
            u.setFocusable(false);
            u.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    uMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    uMouseReleased(evt);
                }
            });
            u.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    uActionPerformed(evt);
                }
            });
            SheetView.add(u);
            u.setBounds(310, 90, 40, 40);

            jButton34.setBackground(new java.awt.Color(153, 153, 153));
            jButton34.setText("`");
            jButton34.setEnabled(false);
            jButton34.setFocusable(false);
            SheetView.add(jButton34);
            jButton34.setBounds(10, 50, 40, 40);

            two.setText("2");
            two.setFocusable(false);
            two.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    twoActionPerformed(evt);
                }
            });
            SheetView.add(two);
            two.setBounds(90, 50, 40, 40);

            three.setText("3");
            three.setFocusable(false);
            three.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    threeActionPerformed(evt);
                }
            });
            SheetView.add(three);
            three.setBounds(130, 50, 40, 40);

            four.setText("4");
            four.setFocusable(false);
            four.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    fourActionPerformed(evt);
                }
            });
            SheetView.add(four);
            four.setBounds(170, 50, 40, 40);

            five.setText("5");
            five.setFocusable(false);
            five.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    fiveActionPerformed(evt);
                }
            });
            SheetView.add(five);
            five.setBounds(210, 50, 40, 40);

            six.setText("6");
            six.setFocusable(false);
            six.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sixActionPerformed(evt);
                }
            });
            SheetView.add(six);
            six.setBounds(250, 50, 40, 40);

            seven.setText("7");
            seven.setFocusable(false);
            seven.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sevenActionPerformed(evt);
                }
            });
            SheetView.add(seven);
            seven.setBounds(290, 50, 40, 40);

            eight.setText("8");
            eight.setFocusable(false);
            eight.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    eightActionPerformed(evt);
                }
            });
            SheetView.add(eight);
            eight.setBounds(330, 50, 40, 40);

            nine.setText("9");
            nine.setFocusable(false);
            nine.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    nineActionPerformed(evt);
                }
            });
            SheetView.add(nine);
            nine.setBounds(370, 50, 40, 40);

            jButton43.setBackground(new java.awt.Color(153, 153, 153));
            jButton43.setText("Backspace");
            jButton43.setEnabled(false);
            jButton43.setFocusable(false);
            jButton43.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton43ActionPerformed(evt);
                }
            });
            SheetView.add(jButton43);
            jButton43.setBounds(530, 50, 90, 40);

            zero.setText("0");
            zero.setFocusable(false);
            zero.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    zeroActionPerformed(evt);
                }
            });
            SheetView.add(zero);
            zero.setBounds(410, 50, 40, 40);

            jButton45.setBackground(new java.awt.Color(153, 153, 153));
            jButton45.setText("Shift");
            jButton45.setEnabled(false);
            jButton45.setFocusable(false);
            SheetView.add(jButton45);
            jButton45.setBounds(10, 170, 90, 40);

            z.setText("Z");
            z.setFocusable(false);
            z.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    zMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    zMouseReleased(evt);
                }
            });
            z.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    zActionPerformed(evt);
                }
            });
            SheetView.add(z);
            z.setBounds(100, 170, 40, 40);

            jButton47.setText("Z");
            jButton47.setFocusable(false);
            SheetView.add(jButton47);
            jButton47.setBounds(100, 170, 40, 40);

            one.setText("1");
            one.setFocusable(false);
            SheetView.add(one);
            one.setBounds(50, 50, 40, 40);

            a.setText("A");
            a.setFocusable(false);
            a.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    aMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    aMouseReleased(evt);
                }
            });
            SheetView.add(a);
            a.setBounds(80, 130, 40, 40);

            q.setText("Q");
            q.setFocusable(false);
            q.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    qMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    qMouseReleased(evt);
                }
            });
            SheetView.add(q);
            q.setBounds(70, 90, 40, 40);

            minus.setText("-");
            minus.setFocusable(false);
            minus.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    minusActionPerformed(evt);
                }
            });
            SheetView.add(minus);
            minus.setBounds(450, 50, 40, 40);

            p.setText("P");
            p.setFocusable(false);
            p.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    pMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    pMouseReleased(evt);
                }
            });
            p.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    pActionPerformed(evt);
                }
            });
            SheetView.add(p);
            p.setBounds(430, 90, 40, 40);

            jButton53.setBackground(new java.awt.Color(153, 153, 153));
            jButton53.setText("[");
            jButton53.setEnabled(false);
            jButton53.setFocusable(false);
            jButton53.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton53ActionPerformed(evt);
                }
            });
            SheetView.add(jButton53);
            jButton53.setBounds(470, 90, 40, 40);

            semicolon.setText(";");
            semicolon.setFocusable(false);
            semicolon.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    semicolonMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    semicolonMouseReleased(evt);
                }
            });
            semicolon.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    semicolonActionPerformed(evt);
                }
            });
            SheetView.add(semicolon);
            semicolon.setBounds(440, 130, 40, 40);

            slash.setText("/");
            slash.setFocusable(false);
            slash.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    slashMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    slashMouseReleased(evt);
                }
            });
            slash.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    slashActionPerformed(evt);
                }
            });
            SheetView.add(slash);
            slash.setBounds(460, 170, 40, 40);

            apostrophe.setBackground(new java.awt.Color(204, 204, 204));
            apostrophe.setText("'");
            apostrophe.setEnabled(false);
            apostrophe.setFocusable(false);
            apostrophe.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    apostropheActionPerformed(evt);
                }
            });
            SheetView.add(apostrophe);
            apostrophe.setBounds(480, 130, 40, 40);

            jButton57.setBackground(new java.awt.Color(153, 153, 153));
            jButton57.setText("]");
            jButton57.setEnabled(false);
            jButton57.setFocusable(false);
            jButton57.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton57ActionPerformed(evt);
                }
            });
            SheetView.add(jButton57);
            jButton57.setBounds(510, 90, 40, 40);

            jButton58.setBackground(new java.awt.Color(153, 153, 153));
            jButton58.setText("=");
            jButton58.setEnabled(false);
            jButton58.setFocusable(false);
            jButton58.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton58ActionPerformed(evt);
                }
            });
            SheetView.add(jButton58);
            jButton58.setBounds(490, 50, 40, 40);

            jPanel1.add(SheetView, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 1030, 220));

            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn bài ", "Happy birthday", "Super Idol" }));
            jComboBox1.setFocusable(false);
            jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBox1ActionPerformed(evt);
                }
            });
            jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 100, -1));

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void C3tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3tActionPerformed
        mc[15].noteOn(37 + pitch, 100);
    }//GEN-LAST:event_C3tActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
        mc[15].noteOn(36 + pitch, 100);
    }//GEN-LAST:event_C3ActionPerformed

    private void D3tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D3tActionPerformed
        mc[15].noteOn(39 + pitch, 100);
    }//GEN-LAST:event_D3tActionPerformed

    private void E3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E3ActionPerformed
        // TODO add your handling code here:
        mc[15].noteOn(40 + pitch, 100);
    }//GEN-LAST:event_E3ActionPerformed

    private void D3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D3ActionPerformed
        mc[15].noteOn(38 + pitch, 100);
    }//GEN-LAST:event_D3ActionPerformed

    private void G3tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G3tActionPerformed
        mc[15].noteOn(44 + pitch, 100);
    }//GEN-LAST:event_G3tActionPerformed

    private void F3tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F3tActionPerformed
        mc[15].noteOn(42 + pitch, 100);
    }//GEN-LAST:event_F3tActionPerformed

    private void G3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G3ActionPerformed
        mc[15].noteOn(43 + pitch, 100);
    }//GEN-LAST:event_G3ActionPerformed

    private void F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F3ActionPerformed
        // TODO add your handling code here:
        mc[15].noteOn(41 + pitch, 100);
    }//GEN-LAST:event_F3ActionPerformed

    private void A3tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A3tActionPerformed
        mc[15].noteOn(46 + pitch, 100);
    }//GEN-LAST:event_A3tActionPerformed

    private void A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A3ActionPerformed
        mc[15].noteOn(45 + pitch, 100);
    }//GEN-LAST:event_A3ActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        mc[15].noteOn(47 + pitch, 100);
    }//GEN-LAST:event_B3ActionPerformed

    private void C4tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4tActionPerformed
        mc[15].noteOn(49 + pitch, 100);
    }//GEN-LAST:event_C4tActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        mc[15].noteOn(48 + pitch, 100);
    }//GEN-LAST:event_C4ActionPerformed

    private void D4tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D4tActionPerformed
        mc[15].noteOn(51 + pitch, 100);
    }//GEN-LAST:event_D4tActionPerformed

    private void E4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E4ActionPerformed
        mc[15].noteOn(52 + pitch, 100);
    }//GEN-LAST:event_E4ActionPerformed

    private void D4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D4ActionPerformed
        mc[15].noteOn(50 + pitch, 100);
    }//GEN-LAST:event_D4ActionPerformed

    private void G4tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G4tActionPerformed
        mc[15].noteOn(56 + pitch, 100);
    }//GEN-LAST:event_G4tActionPerformed

    private void F4tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F4tActionPerformed
        mc[15].noteOn(54 + pitch, 100);
    }//GEN-LAST:event_F4tActionPerformed

    private void G4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G4ActionPerformed
        mc[15].noteOn(55 + pitch, 100);
    }//GEN-LAST:event_G4ActionPerformed

    private void F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F4ActionPerformed
        mc[15].noteOn(53 + pitch, 100);
    }//GEN-LAST:event_F4ActionPerformed

    private void A4tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A4tActionPerformed
        mc[15].noteOn(58 + pitch, 100);
    }//GEN-LAST:event_A4tActionPerformed

    private void A4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A4ActionPerformed
        mc[15].noteOn(57 + pitch, 100);
    }//GEN-LAST:event_A4ActionPerformed

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        mc[15].noteOn(59 + pitch, 100);
    }//GEN-LAST:event_B4ActionPerformed

    private void C5tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5tActionPerformed
        mc[15].noteOn(61 + pitch, 100);
    }//GEN-LAST:event_C5tActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        mc[15].noteOn(60 + pitch, 100);
    }//GEN-LAST:event_C5ActionPerformed

    private void D5tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D5tActionPerformed
        mc[15].noteOn(63 + pitch, 100);
    }//GEN-LAST:event_D5tActionPerformed

    private void E5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E5ActionPerformed
        mc[15].noteOn(64 + pitch, 100);
    }//GEN-LAST:event_E5ActionPerformed

    private void D5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D5ActionPerformed
        mc[15].noteOn(62 + pitch, 100);
    }//GEN-LAST:event_D5ActionPerformed

    private void G5tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G5tActionPerformed
        mc[15].noteOn(68 + pitch, 100);
    }//GEN-LAST:event_G5tActionPerformed

    private void F5tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F5tActionPerformed
        mc[15].noteOn(66 + pitch, 100);
    }//GEN-LAST:event_F5tActionPerformed

    private void G5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G5ActionPerformed
        mc[15].noteOn(67 + pitch, 100);
    }//GEN-LAST:event_G5ActionPerformed

    private void F5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F5ActionPerformed
        mc[15].noteOn(65 + pitch, 100);
    }//GEN-LAST:event_F5ActionPerformed

    private void A5tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A5tActionPerformed
        mc[15].noteOn(70 + pitch, 100);
    }//GEN-LAST:event_A5tActionPerformed

    private void A5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A5ActionPerformed
        mc[15].noteOn(69 + pitch, 100);
    }//GEN-LAST:event_A5ActionPerformed

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        mc[15].noteOn(71 + pitch, 100);
    }//GEN-LAST:event_B5ActionPerformed

    private void C6tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6tActionPerformed
        mc[15].noteOn(73 + pitch, 100);
    }//GEN-LAST:event_C6tActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        mc[15].noteOn(72 + pitch, 100);
    }//GEN-LAST:event_C6ActionPerformed

    private void D6tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D6tActionPerformed
        mc[15].noteOn(75 + pitch, 100);
    }//GEN-LAST:event_D6tActionPerformed

    private void E6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E6ActionPerformed
        mc[15].noteOn(76 + pitch, 100);
    }//GEN-LAST:event_E6ActionPerformed

    private void D6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D6ActionPerformed
        mc[15].noteOn(74 + pitch, 100);
    }//GEN-LAST:event_D6ActionPerformed

    private void G6tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G6tActionPerformed
        mc[15].noteOn(80 + pitch, 100);
    }//GEN-LAST:event_G6tActionPerformed

    private void F6tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F6tActionPerformed
        mc[15].noteOn(78 + pitch, 100);
    }//GEN-LAST:event_F6tActionPerformed

    private void G6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G6ActionPerformed
        mc[15].noteOn(79 + pitch, 100);
    }//GEN-LAST:event_G6ActionPerformed

    private void F6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F6ActionPerformed
        mc[15].noteOn(77 + pitch, 100);
    }//GEN-LAST:event_F6ActionPerformed

    private void A6tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A6tActionPerformed
        mc[15].noteOn(82 + pitch, 100);
    }//GEN-LAST:event_A6tActionPerformed

    private void A6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A6ActionPerformed
        mc[15].noteOn(81 + pitch, 100);
    }//GEN-LAST:event_A6ActionPerformed

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        mc[15].noteOn(83 + pitch, 100);
    }//GEN-LAST:event_B6ActionPerformed

    private void C7tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7tActionPerformed
        mc[15].noteOn(85 + pitch, 100);
    }//GEN-LAST:event_C7tActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        mc[15].noteOn(84 + pitch, 100);
    }//GEN-LAST:event_C7ActionPerformed

    private void D7tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D7tActionPerformed
        mc[15].noteOn(87 + pitch, 100);
    }//GEN-LAST:event_D7tActionPerformed

    private void E7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E7ActionPerformed
        mc[15].noteOn(88 + pitch, 100);
    }//GEN-LAST:event_E7ActionPerformed

    private void D7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D7ActionPerformed
        mc[15].noteOn(86 + pitch, 100);
    }//GEN-LAST:event_D7ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if (pitch <= 11) {
            pitch += 1;
        }
        jLabel1.setText("Pitch : " + pitch);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if (pitch >= -11) {
            pitch -= 1;
        }
        jLabel1.setText("Pitch : " + pitch);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void EmcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmcActionPerformed
        mc[5].noteOn(52 + pitch, 100);
        mc[5].noteOn(55 + pitch, 100);
        mc[5].noteOn(59 + pitch, 100);
    }//GEN-LAST:event_EmcActionPerformed

    private void EcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EcActionPerformed
        mc[5].noteOn(52 + pitch, 100);
        mc[5].noteOn(56 + pitch, 100);
        mc[5].noteOn(59 + pitch, 100);
    }//GEN-LAST:event_EcActionPerformed

    private void BmcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BmcActionPerformed
        mc[5].noteOn(61 + pitch, 100);
        mc[5].noteOn(65 + pitch, 100);
        mc[5].noteOn(68 + pitch, 100);
    }//GEN-LAST:event_BmcActionPerformed

    private void AmcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmcActionPerformed
        mc[5].noteOn(57 + pitch, 100);
        mc[5].noteOn(60 + pitch, 100);
        mc[5].noteOn(64 + pitch, 100);
    }//GEN-LAST:event_AmcActionPerformed

    private void CcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CcActionPerformed
        mc[5].noteOn(48 + pitch, 100);
        mc[5].noteOn(52 + pitch, 100);
        mc[5].noteOn(55 + pitch, 100);
    }//GEN-LAST:event_CcActionPerformed

    private void C4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C4MousePressed
        C4.setBackground(Color.green);
    }//GEN-LAST:event_C4MousePressed

    private void C4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C4MouseReleased
        C4.setBackground(null);
    }//GEN-LAST:event_C4MouseReleased

    private void BcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BcActionPerformed
        mc[5].noteOn(59 + pitch, 100);
        mc[5].noteOn(63 + pitch, 100);
        mc[5].noteOn(66 + pitch, 100);
    }//GEN-LAST:event_BcActionPerformed

    private void DcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DcActionPerformed
        mc[5].noteOn(50 + pitch, 100);
        mc[5].noteOn(54 + pitch, 100);
        mc[5].noteOn(57 + pitch, 100);
    }//GEN-LAST:event_DcActionPerformed

    private void DmcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DmcActionPerformed
        mc[5].noteOn(50 + pitch, 100);
        mc[5].noteOn(53 + pitch, 100);
        mc[5].noteOn(57 + pitch, 100);
    }//GEN-LAST:event_DmcActionPerformed

    private void FcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FcActionPerformed
        mc[5].noteOn(53 + pitch, 100);
        mc[5].noteOn(57 + pitch, 100);
        mc[5].noteOn(60 + pitch, 100);
    }//GEN-LAST:event_FcActionPerformed

    private void GcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GcActionPerformed
        mc[5].noteOn(55 + pitch, 100);
        mc[5].noteOn(59 + pitch, 100);
        mc[5].noteOn(62 + pitch, 100);
    }//GEN-LAST:event_GcActionPerformed

    private void AcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcActionPerformed
        mc[5].noteOn(57 + pitch, 100);
        mc[5].noteOn(61 + pitch, 100);
        mc[5].noteOn(64 + pitch, 100);
    }//GEN-LAST:event_AcActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int index=jComboBox1.getSelectedIndex();
        if(index ==1 )
        {
            SheetView.setVisible(false);
        }
        else SheetView.setVisible(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton57ActionPerformed

    private void apostropheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apostropheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apostropheActionPerformed

    private void slashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slashActionPerformed

    private void slashMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slashMouseReleased
        // TODO add your handling code here:
        E5.setBackground(null);
        slash.setBackground(null);
    }//GEN-LAST:event_slashMouseReleased

    private void slashMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slashMousePressed
        // TODO add your handling code here:
        E5.setBackground(Color.GREEN);
        slash.setBackground(Color.GREEN);
        mc[5].noteOn(64 + pitch, 50);
    }//GEN-LAST:event_slashMousePressed

    private void semicolonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semicolonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semicolonActionPerformed

    private void semicolonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_semicolonMouseReleased
        // TODO add your handling code here:
        E6.setBackground(null);
        semicolon.setBackground(null);
    }//GEN-LAST:event_semicolonMouseReleased

    private void semicolonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_semicolonMousePressed
        // TODO add your handling code here:
        E6.setBackground(Color.GREEN);
        semicolon.setBackground(Color.GREEN);
        mc[5].noteOn(76 + pitch, 50);
    }//GEN-LAST:event_semicolonMousePressed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pActionPerformed

    private void pMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMouseReleased
        // TODO add your handling code here:
        E7.setBackground(null);
        p.setBackground(null);
    }//GEN-LAST:event_pMouseReleased

    private void pMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMousePressed
        E7.setBackground(Color.GREEN);
        p.setBackground(Color.GREEN);
        mc[5].noteOn(88 + pitch, 50);
    }//GEN-LAST:event_pMousePressed

    private void minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusActionPerformed

    private void qMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qMouseReleased
        // TODO add your handling code here:
        C6.setBackground(null);
        q.setBackground(null);
    }//GEN-LAST:event_qMouseReleased

    private void qMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qMousePressed
        C6.setBackground(Color.GREEN);
        q.setBackground(Color.GREEN);
        mc[5].noteOn(72 + pitch, 50);
    }//GEN-LAST:event_qMousePressed

    private void aMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aMouseReleased

        C5.setBackground(null);
        a.setBackground(null);
    }//GEN-LAST:event_aMouseReleased

    private void aMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aMousePressed
        C5.setBackground(Color.GREEN);
        a.setBackground(Color.GREEN);
        mc[5].noteOn(60 + pitch, 50);
    }//GEN-LAST:event_aMousePressed

    private void zActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zActionPerformed

    private void zMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zMouseReleased
        C4.setBackground(null);
        z.setBackground(null);
    }//GEN-LAST:event_zMouseReleased

    private void zMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zMousePressed
        // TODO add your handling code here:
        C4.setBackground(Color.GREEN);
        z.setBackground(Color.GREEN);
        mc[5].noteOn(48 + pitch, 50);
    }//GEN-LAST:event_zMousePressed

    private void zeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zeroActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void nineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nineActionPerformed

    private void eightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eightActionPerformed

    private void sevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sevenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sevenActionPerformed

    private void sixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sixActionPerformed

    private void fiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fiveActionPerformed

    private void fourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fourActionPerformed

    private void threeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_threeActionPerformed

    private void twoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twoActionPerformed

    private void uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uActionPerformed

    private void uMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uMouseReleased
        // TODO add your handling code here:
        B6.setBackground(null);
        u.setBackground(null);
    }//GEN-LAST:event_uMouseReleased

    private void uMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uMousePressed
        B6.setBackground(Color.GREEN);
        u.setBackground(Color.GREEN);
        mc[5].noteOn(83 + pitch, 50);
    }//GEN-LAST:event_uMousePressed

    private void iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iActionPerformed

    private void iMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iMouseReleased
        C7.setBackground(null);
        i.setBackground(null);
    }//GEN-LAST:event_iMouseReleased

    private void iMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iMousePressed
        C7.setBackground(Color.GREEN);
        i.setBackground(Color.GREEN);
        mc[5].noteOn(84 + pitch, 50);
    }//GEN-LAST:event_iMousePressed

    private void oActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oActionPerformed

    private void oMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oMouseReleased
        // TODO add your handling code here:
        D7.setBackground(null);
        o.setBackground(null);
    }//GEN-LAST:event_oMouseReleased

    private void oMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oMousePressed
        D7.setBackground(Color.GREEN);
        o.setBackground(Color.GREEN);
        mc[5].noteOn(86 + pitch, 50);
    }//GEN-LAST:event_oMousePressed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void yActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yActionPerformed

    private void yMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yMouseReleased
        // TODO add your handling code here:
        A6.setBackground(null);
        y.setBackground(null);
    }//GEN-LAST:event_yMouseReleased

    private void yMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yMousePressed
        A6.setBackground(Color.GREEN);
        y.setBackground(Color.GREEN);
        mc[5].noteOn(81 + pitch, 50);
    }//GEN-LAST:event_yMousePressed

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tActionPerformed

    private void tMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMouseReleased
        // TODO add your handling code here:
        G6.setBackground(null);
        t.setBackground(null);
    }//GEN-LAST:event_tMouseReleased

    private void tMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMousePressed
        G6.setBackground(Color.GREEN);
        t.setBackground(Color.GREEN);
        mc[5].noteOn(79 + pitch, 50);
    }//GEN-LAST:event_tMousePressed

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rActionPerformed

    private void rMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rMouseReleased
        F6.setBackground(null);
        r.setBackground(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_rMouseReleased

    private void rMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rMousePressed
        F6.setBackground(Color.GREEN);
        r.setBackground(Color.GREEN);
        mc[5].noteOn(77 + pitch, 50);
    }//GEN-LAST:event_rMousePressed

    private void wActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wActionPerformed

    private void wMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wMouseReleased
        // TODO add your handling code here:
        D6.setBackground(null);
        w.setBackground(null);
    }//GEN-LAST:event_wMouseReleased

    private void wMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wMousePressed
        D6.setBackground(Color.GREEN);
        w.setBackground(Color.GREEN);
        mc[5].noteOn(74 + pitch, 50);
    }//GEN-LAST:event_wMousePressed

    private void eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eActionPerformed

    private void eMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eMouseReleased
        // TODO add your handling code here:
        E6.setBackground(null);
        e.setBackground(null);
    }//GEN-LAST:event_eMouseReleased

    private void eMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eMousePressed
        E6.setBackground(Color.GREEN);
        e.setBackground(Color.GREEN);
        mc[5].noteOn(76 + pitch, 50);
    }//GEN-LAST:event_eMousePressed

    private void jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jActionPerformed

    private void jMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMouseReleased

        B5.setBackground(null);
        j.setBackground(null);
    }//GEN-LAST:event_jMouseReleased

    private void jMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMousePressed
        B5.setBackground(Color.GREEN);
        j.setBackground(Color.GREEN);
        mc[5].noteOn(71 + pitch, 50);
    }//GEN-LAST:event_jMousePressed

    private void kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kActionPerformed

    private void kMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kMouseReleased
        // TODO add your handling code here:
        C6.setBackground(null);
        k.setBackground(null);
    }//GEN-LAST:event_kMouseReleased

    private void kMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kMousePressed
        C6.setBackground(Color.GREEN);
        k.setBackground(Color.GREEN);
        mc[5].noteOn(72 + pitch, 50);
    }//GEN-LAST:event_kMousePressed

    private void lActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lActionPerformed

    private void lMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lMouseReleased

        D6.setBackground(null);
        l.setBackground(null);
    }//GEN-LAST:event_lMouseReleased

    private void lMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lMousePressed
        // TODO add your handling code here:
        D6.setBackground(Color.GREEN);
        l.setBackground(Color.GREEN);
        mc[5].noteOn(74 + pitch, 50);
    }//GEN-LAST:event_lMousePressed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hActionPerformed

    private void hMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hMouseReleased
        // TODO add your handling code here:
        A5.setBackground(null);
        h.setBackground(null);
    }//GEN-LAST:event_hMouseReleased

    private void hMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hMousePressed
        A5.setBackground(Color.GREEN);
        h.setBackground(Color.GREEN);
        mc[5].noteOn(69 + pitch, 50);
    }//GEN-LAST:event_hMousePressed

    private void gActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gActionPerformed

    private void gMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gMouseReleased
        // TODO add your handling code here:
        G5.setBackground(null);
        g.setBackground(null);
    }//GEN-LAST:event_gMouseReleased

    private void gMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gMousePressed
        G5.setBackground(Color.GREEN);
        g.setBackground(Color.GREEN);
        mc[5].noteOn(67 + pitch, 50);
    }//GEN-LAST:event_gMousePressed

    private void fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fActionPerformed

    private void fMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fMouseReleased
        // TODO add your handling code here:
        F5.setBackground(null);
        f.setBackground(null);
    }//GEN-LAST:event_fMouseReleased

    private void fMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fMousePressed
        F5.setBackground(Color.GREEN);
        f.setBackground(Color.GREEN);
        mc[5].noteOn(65 + pitch, 50);
    }//GEN-LAST:event_fMousePressed

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sActionPerformed

    private void sMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMouseReleased
        // TODO add your handling code here:
        D5.setBackground(null);
        s.setBackground(null);
    }//GEN-LAST:event_sMouseReleased

    private void sMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sMousePressed
        D5.setBackground(Color.GREEN);
        s.setBackground(Color.GREEN);
        mc[5].noteOn(62 + pitch, 50);
    }//GEN-LAST:event_sMousePressed

    private void dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dActionPerformed

    private void dMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dMouseReleased
        // TODO add your handling code here:
        E5.setBackground(null);
        d.setBackground(null);
    }//GEN-LAST:event_dMouseReleased

    private void dMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dMousePressed
        E5.setBackground(Color.GREEN);
        d.setBackground(Color.GREEN);
        mc[5].noteOn(64 + pitch, 50);
    }//GEN-LAST:event_dMousePressed

    private void mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mActionPerformed

    private void mMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mMouseReleased
        // TODO add your handling code here:
        B4.setBackground(null);
        m.setBackground(null);
    }//GEN-LAST:event_mMouseReleased

    private void mMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mMousePressed
        // TODO add your handling code here:
        B4.setBackground(Color.GREEN);
        m.setBackground(Color.GREEN);
        mc[5].noteOn(59 + pitch, 50);
    }//GEN-LAST:event_mMousePressed

    private void commaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_commaMouseReleased
        // TODO add your handling code here:
        C5.setBackground(null);
        comma.setBackground(null);
    }//GEN-LAST:event_commaMouseReleased

    private void commaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_commaMousePressed

        C5.setBackground(Color.GREEN);
        comma.setBackground(Color.GREEN);
        mc[5].noteOn(60 + pitch, 50);
    }//GEN-LAST:event_commaMousePressed

    private void dotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dotActionPerformed

    private void dotMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dotMouseReleased
        // TODO add your handling code here:
        D5.setBackground(null);
        dot.setBackground(null);
    }//GEN-LAST:event_dotMouseReleased

    private void dotMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dotMousePressed
        // TODO add your handling code here:
        D5.setBackground(Color.GREEN);
        dot.setBackground(Color.GREEN);
        mc[5].noteOn(62 + pitch, 50);
    }//GEN-LAST:event_dotMousePressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nActionPerformed

    private void nMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nMouseReleased
        // TODO add your handling code here:
        A4.setBackground(null);
        n.setBackground(null);
    }//GEN-LAST:event_nMouseReleased

    private void nMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nMousePressed
        // TODO add your handling code here:
        A4.setBackground(Color.GREEN);
        n.setBackground(Color.GREEN);
        mc[5].noteOn(57 + pitch, 50);
    }//GEN-LAST:event_nMousePressed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bActionPerformed

    private void bMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMouseReleased
        // TODO add your handling code here:
        G4.setBackground(null);
        b.setBackground(null);
    }//GEN-LAST:event_bMouseReleased

    private void bMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMousePressed

        G4.setBackground(Color.GREEN);
        b.setBackground(Color.GREEN);
        mc[5].noteOn(55 + pitch, 50);
    }//GEN-LAST:event_bMousePressed

    private void vMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMouseReleased
        // TODO add your handling code here:
        F4.setBackground(null);
        v.setBackground(null);
    }//GEN-LAST:event_vMouseReleased

    private void vMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMousePressed
        // TODO add your handling code here:
        F4.setBackground(Color.GREEN);
        v.setBackground(Color.GREEN);
        mc[5].noteOn(53 + pitch, 50);
    }//GEN-LAST:event_vMousePressed

    private void xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xActionPerformed

    private void xMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xMouseReleased
        // TODO add your handling code here:
        D4.setBackground(null);
        x.setBackground(null);
    }//GEN-LAST:event_xMouseReleased

    private void xMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xMousePressed
        // TODO add your handling code here:
        D4.setBackground(Color.GREEN);
        x.setBackground(Color.GREEN);
        mc[5].noteOn(50 + pitch, 50);
    }//GEN-LAST:event_xMousePressed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed

    }//GEN-LAST:event_cActionPerformed

    private void cMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cMouseReleased
        // TODO add your handling code here:
        E4.setBackground(null);
        c.setBackground(null);
    }//GEN-LAST:event_cMouseReleased

    private void cMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cMousePressed
        // TODO add your handling code here:
        E4.setBackground(Color.GREEN);
        c.setBackground(Color.GREEN);
        mc[5].noteOn(52 + pitch, 50);
    }//GEN-LAST:event_cMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Piano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Piano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Piano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Piano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Piano dialog = new Piano(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A3;
    private javax.swing.JButton A3t;
    private javax.swing.JButton A4;
    private javax.swing.JButton A4t;
    private javax.swing.JButton A5;
    private javax.swing.JButton A5t;
    private javax.swing.JButton A6;
    private javax.swing.JButton A6t;
    private javax.swing.JButton Ac;
    private javax.swing.JButton Amc;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton Bc;
    private javax.swing.JButton Bmc;
    private javax.swing.JButton C3;
    private javax.swing.JButton C3t;
    private javax.swing.JButton C4;
    private javax.swing.JButton C4t;
    private javax.swing.JButton C5;
    private javax.swing.JButton C5t;
    private javax.swing.JButton C6;
    private javax.swing.JButton C6t;
    private javax.swing.JButton C7;
    private javax.swing.JButton C7t;
    private javax.swing.JButton Cc;
    private javax.swing.JButton D3;
    private javax.swing.JButton D3t;
    private javax.swing.JButton D4;
    private javax.swing.JButton D4t;
    private javax.swing.JButton D5;
    private javax.swing.JButton D5t;
    private javax.swing.JButton D6;
    private javax.swing.JButton D6t;
    private javax.swing.JButton D7;
    private javax.swing.JButton D7t;
    private javax.swing.JButton Dc;
    private javax.swing.JButton Dmc;
    private javax.swing.JButton E3;
    private javax.swing.JButton E4;
    private javax.swing.JButton E5;
    private javax.swing.JButton E6;
    private javax.swing.JButton E7;
    private javax.swing.JButton Ec;
    private javax.swing.JButton Emc;
    private javax.swing.JButton F3;
    private javax.swing.JButton F3t;
    private javax.swing.JButton F4;
    private javax.swing.JButton F4t;
    private javax.swing.JButton F5;
    private javax.swing.JButton F5t;
    private javax.swing.JButton F6;
    private javax.swing.JButton F6t;
    private javax.swing.JButton Fc;
    private javax.swing.JButton G3;
    private javax.swing.JButton G3t;
    private javax.swing.JButton G4;
    private javax.swing.JButton G4t;
    private javax.swing.JButton G5;
    private javax.swing.JButton G5t;
    private javax.swing.JButton G6;
    private javax.swing.JButton G6t;
    private javax.swing.JButton Gc;
    private javax.swing.JPanel SheetView;
    private javax.swing.JButton a;
    private javax.swing.JButton apostrophe;
    private javax.swing.JButton b;
    private javax.swing.JButton c;
    private javax.swing.JButton comma;
    private javax.swing.JButton d;
    private javax.swing.JButton dot;
    private javax.swing.JButton e;
    private javax.swing.JButton eight;
    private javax.swing.JButton f;
    private javax.swing.JButton five;
    private javax.swing.JButton four;
    private javax.swing.JButton g;
    private javax.swing.JButton h;
    private javax.swing.JButton i;
    private javax.swing.JButton j;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton k;
    private javax.swing.JButton l;
    private javax.swing.JButton m;
    private javax.swing.JButton minus;
    private javax.swing.JButton n;
    private javax.swing.JButton nine;
    private javax.swing.JButton o;
    private javax.swing.JButton one;
    private javax.swing.JButton p;
    private javax.swing.JButton q;
    private javax.swing.JButton r;
    private javax.swing.JButton s;
    private javax.swing.JButton semicolon;
    private javax.swing.JButton seven;
    private javax.swing.JButton six;
    private javax.swing.JButton slash;
    private javax.swing.JButton t;
    private javax.swing.JButton three;
    private javax.swing.JButton two;
    private javax.swing.JButton u;
    private javax.swing.JButton v;
    private javax.swing.JButton w;
    private javax.swing.JButton x;
    private javax.swing.JButton y;
    private javax.swing.JButton z;
    private javax.swing.JButton zero;
    // End of variables declaration//GEN-END:variables

    private void initMap() {
        map.put(0, 48);
        map.put(1, 50);
        map.put(2, 52);
        map.put(3, 53);
        map.put(4, 55);
        map.put(5, 57);
        map.put(6, 59);
        map.put(7, 60);
        map.put(8, 62);
        map.put(9, 64);

        map.put(10, 60);
        map.put(11, 62);
        map.put(12, 64);
        map.put(13, 65);
        map.put(14, 67);
        map.put(15, 69);
        map.put(16, 71);
        map.put(17, 72);
        map.put(18, 74);
        map.put(19, 76);

        map.put(20, 72);
        map.put(21, 74);
        map.put(22, 76);
        map.put(23, 77);
        map.put(24, 79);
        map.put(25, 81);
        map.put(26, 83);
        map.put(27, 84);
        map.put(28, 86);
        map.put(29, 88);

//    private String keys = "ZXCVBNM,./ASDFGHJKL;QWERTYUIOP";
//        ZXCVBNMASDFGHJKLQWERTYUIOP
        mapButton.put(0, C4);
        mapButton.put(1, D4);
        mapButton.put(2, E4);
        mapButton.put(3, F4);
        mapButton.put(4, G4);
        mapButton.put(5, A4);
        mapButton.put(6, B4);
        mapButton.put(7, C5);
        mapButton.put(8, D5);
        mapButton.put(9, E5);
        mapButton.put(10, C5);
        mapButton.put(11, D5);
        mapButton.put(12, E5);
        mapButton.put(13, F5);
        mapButton.put(14, G5);
        mapButton.put(15, A5);
        mapButton.put(16, B5);
        mapButton.put(17, C6);
        mapButton.put(18, D6);
        mapButton.put(19, E6);
        mapButton.put(20, C6);
        mapButton.put(21, D6);
        mapButton.put(22, E6);
        mapButton.put(23, F6);
        mapButton.put(24, G6);
        mapButton.put(25, A6);
        mapButton.put(26, B6);
        mapButton.put(27, C7);
        mapButton.put(28, D7);
        mapButton.put(29, E7);

    }
}
