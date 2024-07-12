package com.mycompany.camcapture;

import javax.swing.ImageIcon;
import com.github.sarxos.webcam.Webcam;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class StudentCapture extends javax.swing.JFrame {

    private boolean isRunning = false;
    private Webcam webcam;
    private Timer captureTimer;
    private int countdownSeconds = 5;

    public StudentCapture() {
        initComponents();
        initializeWebcam();
        setupTimer();
    }

    private void setupTimer() {
        captureTimer = new Timer(1000, new ActionListener() {
            private int count = countdownSeconds;

            @Override
            public void actionPerformed(ActionEvent e) {
                timerLabel.setText("Capture in " + count + " seconds");
                count--;
                if (count < 0) {
                    captureTimer.stop();
                    timerLabel.setText(""); // Clear timer label
                }
            }
        });
    }

    private void initializeWebcam() {
        setLocationRelativeTo(null);
        webcam = Webcam.getDefault();
        webcam.setViewSize(new java.awt.Dimension(320, 240));
        webcam.open();

        // Start the webcam feed automatically
        isRunning = true;
        new VideoFeedTaker().start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webcamContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        imageHolder = new javax.swing.JLabel();
        timerLabel = new javax.swing.JLabel();
        captureButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        timerLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout webcamContainerLayout = new javax.swing.GroupLayout(webcamContainer);
        webcamContainer.setLayout(webcamContainerLayout);
        webcamContainerLayout.setHorizontalGroup(
            webcamContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webcamContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(webcamContainerLayout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(timerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(320, Short.MAX_VALUE))
        );
        webcamContainerLayout.setVerticalGroup(
            webcamContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webcamContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(webcamContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timerLabel))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        captureButton.setText("Capture");
        captureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(webcamContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(captureButton)))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webcamContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(captureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void captureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureButtonActionPerformed

        if (webcam != null && webcam.isOpen()) {
            captureTimer.restart(); // Start or restart the timer
            try {
                // Simulate shutter effect (optional)
                Image blankImage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
                imageHolder.setIcon(new ImageIcon(blankImage));
                Thread.sleep(200); // Adjust delay as needed for shutter effect

                // Capture the actual image from webcam
                Image image = webcam.getImage();
                imageHolder.setIcon(new ImageIcon(image));

                // Save the captured image to file
                String filename = "capture_" + System.currentTimeMillis() + ".png";
                File file = new File(filename);
                ImageIO.write((java.awt.image.RenderedImage) image, "PNG", file);
                System.out.println("Photo captured and saved as " + filename);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(StudentCapture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_captureButtonActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentCapture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentCapture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentCapture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentCapture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentCapture().setVisible(true);
            }
        });
    }

    class VideoFeedTaker extends Thread {

        @Override
        public void run() {
            while (true && isRunning) {
                try {
                    Image image = webcam.getImage();
                    imageHolder.setIcon(new ImageIcon(image));
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StudentCapture.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton captureButton;
    private javax.swing.JLabel imageHolder;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel timerLabel;
    private javax.swing.JPanel webcamContainer;
    // End of variables declaration//GEN-END:variables
}
