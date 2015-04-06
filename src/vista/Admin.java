package vista;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.rootpane.WebFrame;
import control.AdminC;
import java.util.Date;
import util.Dates;

/**
 *
 * @author Agarimo
 */
public class Admin extends WebFrame {

    /**
     * Creates new form Client
     */
    public Admin() {
               
        boolean decorated = WebLookAndFeel.isDecorateFrames();
        WebLookAndFeel.setDecorateFrames(true);
        setIconImages(WebLookAndFeel.getImages());
        initComponents();
        init();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        WebLookAndFeel.setDecorateFrames(decorated);
    }
    
    private void init(){
        this.FechaI.setDate(Dates.curdate());
        this.FechaF.setDate(Dates.curdate());
        this.boletines.setText("...");
        this.multas.setText("...");
    }
    
    public void setDatos(String bol, String mul){
        this.boletines.setText(bol);
        this.multas.setText(mul);
    }
    
    private void cargaStats(){
        Date ini=FechaI.getDate();
        Date fin=FechaF.getDate();
        AdminC.cargaStats(ini, fin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Layer = new javax.swing.JLayeredPane();
        webPanel1 = new com.alee.laf.panel.WebPanel();
        webPanel2 = new com.alee.laf.panel.WebPanel();
        webPanel3 = new com.alee.laf.panel.WebPanel();
        webButton1 = new com.alee.laf.button.WebButton();
        webButton2 = new com.alee.laf.button.WebButton();
        webPanel4 = new com.alee.laf.panel.WebPanel();
        webPanel5 = new com.alee.laf.panel.WebPanel();
        webButton3 = new com.alee.laf.button.WebButton();
        FechaF = new com.toedter.calendar.JDateChooser();
        FechaI = new com.toedter.calendar.JDateChooser();
        webPanel6 = new com.alee.laf.panel.WebPanel();
        webLabel1 = new com.alee.laf.label.WebLabel();
        webLabel2 = new com.alee.laf.label.WebLabel();
        boletines = new com.alee.laf.label.WebLabel();
        multas = new com.alee.laf.label.WebLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IDBL");
        setResizable(false);

        webPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        webButton1.setText("Insercion");

        webButton2.setText("Salir");

        javax.swing.GroupLayout webPanel3Layout = new javax.swing.GroupLayout(webPanel3);
        webPanel3.setLayout(webPanel3Layout);
        webPanel3Layout.setHorizontalGroup(
            webPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel3Layout.createSequentialGroup()
                .addGroup(webPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(webButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addContainerGap())
        );
        webPanel3Layout.setVerticalGroup(
            webPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(webButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        webPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        webPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        webButton3.setText("Cargar");
        webButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton3ActionPerformed(evt);
            }
        });
        webPanel5.add(webButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 80, 30));
        webPanel5.add(FechaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 120, 30));
        webPanel5.add(FechaI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, 30));

        webLabel1.setText("Boletines");

        webLabel2.setText("Multas");

        boletines.setText("...");

        multas.setText("...");

        javax.swing.GroupLayout webPanel6Layout = new javax.swing.GroupLayout(webPanel6);
        webPanel6.setLayout(webPanel6Layout);
        webPanel6Layout.setHorizontalGroup(
            webPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(webPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(webPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(multas, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boletines, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        webPanel6Layout.setVerticalGroup(
            webPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(webPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boletines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(webLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(webPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(multas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(webLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout webPanel4Layout = new javax.swing.GroupLayout(webPanel4);
        webPanel4.setLayout(webPanel4Layout);
        webPanel4Layout.setHorizontalGroup(
            webPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(webPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
                .addContainerGap())
        );
        webPanel4Layout.setVerticalGroup(
            webPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(webPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout webPanel2Layout = new javax.swing.GroupLayout(webPanel2);
        webPanel2.setLayout(webPanel2Layout);
        webPanel2Layout.setHorizontalGroup(
            webPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(webPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        webPanel2Layout.setVerticalGroup(
            webPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(webPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout webPanel1Layout = new javax.swing.GroupLayout(webPanel1);
        webPanel1.setLayout(webPanel1Layout);
        webPanel1Layout.setHorizontalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        webPanel1Layout.setVerticalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout LayerLayout = new javax.swing.GroupLayout(Layer);
        Layer.setLayout(LayerLayout);
        LayerLayout.setHorizontalGroup(
            LayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LayerLayout.setVerticalGroup(
            LayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Layer.setLayer(webPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Layer)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Layer)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton3ActionPerformed

        this.boletines.setText("...");
        this.multas.setText("...");
        cargaStats();
        
    }//GEN-LAST:event_webButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FechaF;
    private com.toedter.calendar.JDateChooser FechaI;
    private javax.swing.JLayeredPane Layer;
    private com.alee.laf.label.WebLabel boletines;
    private com.alee.laf.label.WebLabel multas;
    private com.alee.laf.button.WebButton webButton1;
    private com.alee.laf.button.WebButton webButton2;
    private com.alee.laf.button.WebButton webButton3;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.label.WebLabel webLabel2;
    private com.alee.laf.panel.WebPanel webPanel1;
    private com.alee.laf.panel.WebPanel webPanel2;
    private com.alee.laf.panel.WebPanel webPanel3;
    private com.alee.laf.panel.WebPanel webPanel4;
    private com.alee.laf.panel.WebPanel webPanel5;
    private com.alee.laf.panel.WebPanel webPanel6;
    // End of variables declaration//GEN-END:variables
}
