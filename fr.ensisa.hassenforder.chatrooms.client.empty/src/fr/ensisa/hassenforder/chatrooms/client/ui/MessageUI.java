package fr.ensisa.hassenforder.chatrooms.client.ui;

import fr.ensisa.hassenforder.chatrooms.client.model.Message;

/**
 *
 * @author Hassenforder
 */
public class MessageUI extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	
    private Message message;
    private GUIListener listener;

    /**
     * Creates new form ChannelDescription
     */
    public MessageUI() {
        initComponents();
    }

    public MessageUI(Message message, GUIListener listener) {
        initComponents();
        setMessage(message);
        setListener(listener);
    }

    public void setMessage (Message message) {
        this.message = message;
        if (message != null) {
            if (message.getChannel() != null) {
                jChannelName.setText(message.getChannel());
            }
            jMessageAuthor.setText(message.getAuthor());
            jMessageText.setText(message.getText());
        }
    }

    public void setListener (GUIListener listener) {
        this.listener = listener;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jChannelName = new javax.swing.JLabel();
        jMessageAuthor = new javax.swing.JLabel();
        jMessageText = new javax.swing.JLabel();

        jChannelName.setText("channel");
        jChannelName.setToolTipText("");
        jChannelName.setName(""); // NOI18N

        jMessageAuthor.setText("author");

        jMessageText.setText("text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jChannelName, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMessageAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMessageText, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jChannelName)
                .addComponent(jMessageAuthor)
                .addComponent(jMessageText))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jChannelName;
    private javax.swing.JLabel jMessageAuthor;
    private javax.swing.JLabel jMessageText;
    // End of variables declaration//GEN-END:variables
}
