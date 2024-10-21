/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main1;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import util.Constantes;
import main.java.ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import ch.swingfx.twinkle.NotificationBuilder;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.Magasin;
import dao.entity.MouvementStockGlobal;
import dao.entity.StockMP;
import dao.entity.Utilisateur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author SDRIDI(16/05/2014)
 * 
 */
@SuppressWarnings("serial")
public class AuthentificationView extends javax.swing.JFrame {
	
	public static String login;
	
	public static Utilisateur utilisateur;
	public UtilisateurDAO utiliseurDAO;
	public static MatierePremiereDAO dao= new MatierePremierDAOImpl();
	

	public String getPasswordControl() {
		return passwordControl.getText();
	}

	public void setPasswordControl(String passwordControl) {
		this.passwordControl.setText(passwordControl);
	}

	public String getUserNameControl() {
		return this.userNameControl.getText();
	}

	public void setUserNameControl(String userNameControl) {
		this.userNameControl.setText(userNameControl);
	}

	public String getErrorLabel() {
		return errorLabel.getText();
	}

	public void setErrorLabel(String errorLabel) {
		this.errorLabel.setText(errorLabel);
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	public void setPassword(String password) {
		this.password.setText(password);
		;
	}

	public String getUserName() {
		return this.userName.getText();
	}

	public void setUserName(String userName) {
		this.userName.setText(userName);
	}

	
	

	
	
	
	
	public AuthentificationView() {
		try {
				Properties props = new Properties();
			  props.put("logoString", "------");
			  McWinLookAndFeel.setCurrentTheme(props);
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			
			/*
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		*/
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AuthentificationView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		initComponents();
		// setSize(getToolkit().getScreenSize());
		setLocationRelativeTo(null);
		connect.setEnabled(true);
		nbrAttempt = 0;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		
		
		
		
		utiliseurDAO=new UtilisateurDAOImpl();
		panelAuthentification = new javax.swing.JPanel();
		welcomeLabel = new javax.swing.JLabel();
		panelConnexion = new javax.swing.JPanel();
		connexionLabel = new javax.swing.JLabel();
		orLabel = new javax.swing.JLabel();
		createAccount = new javax.swing.JLabel();
		userLabel = new javax.swing.JLabel();
		userName = new javax.swing.JTextField();
		util.Utils.copycoller(userName);
		passwordLabel = new javax.swing.JLabel();
		password = new javax.swing.JPasswordField();
		util.Utils.copycoller(password);
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				
				if(evt.getKeyCode()==evt.VK_ENTER)
				{
					
					try {
						connectkeypressed(evt);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
					
					
				}
				
			}
		});
		savePasswordForNextEntry = new javax.swing.JCheckBox();
		savePasswordForNextEntry.setVisible(false);
		connect = new javax.swing.JButton();
		errorLabel = new javax.swing.JLabel();
		passwordRecover = new javax.swing.JLabel();
		panelLoader = new javax.swing.JPanel();
		userNameControl = new javax.swing.JLabel();
		passwordControl = new javax.swing.JLabel();
		sloganOne = new javax.swing.JLabel();
		imgWrite = new javax.swing.JLabel();
		sloganTwo = new javax.swing.JLabel();
		imgWriteTwo = new javax.swing.JLabel();
		sloganThree = new javax.swing.JLabel();
		imgWriteThree = new javax.swing.JLabel();
		sloganFour = new javax.swing.JLabel();
		imgWriteThree1 = new javax.swing.JLabel();
		sloganFour1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bienvenue dans Application de Gestion Prod Compta V1.0"); // NOI18N
		setResizable(false);

		panelAuthentification.setBackground(new java.awt.Color(255, 255, 255));
		panelAuthentification.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
		panelAuthentification.setMaximumSize(getToolkit().getScreenSize());
		panelAuthentification.setName(""); // NOI18N

		welcomeLabel.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/img/best.png"))); // NOI18N

		panelConnexion.setBackground(new java.awt.Color(204, 204, 204));
		panelConnexion.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		panelConnexion.setOpaque(false);

		connexionLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		connexionLabel.setText("Connexion");

		//orLabel.setText("ou");

		createAccount.setForeground(new java.awt.Color(0, 153, 204));
		/*createAccount.setText(" Créer un compte");
		createAccount.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				createAccountMouseClicked(evt);
			}
		});*/

		userLabel.setText("Nom d'utilisateur");

		userName.setToolTipText("saisissez le nom d'utilisateur avec lequel vous vous êtes enregistrés");
		userName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				userNameFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				userNameFocusLost(evt);
			}
		});
		userName.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				userNamePropertyChange(evt);
			}
		});

		passwordLabel.setText("Mot de passe");
		password.setToolTipText("saisisser un mot de passe valide");

		password.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				passwordActionPerformed(evt);
			}
		});

		/*savePasswordForNextEntry.setBackground(new java.awt.Color(255, 255, 255));
		savePasswordForNextEntry.setText(" Mémoriser pendant une semaine");*/

		connect.setBackground(new java.awt.Color(204, 51, 0));
		connect.setForeground(new java.awt.Color(255, 255, 255));
		connect.setText("Connexion");
		connect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					connectActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		errorLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		errorLabel.setForeground(new java.awt.Color(255, 0, 0));

		passwordRecover.setForeground(new java.awt.Color(0, 153, 204));
		//passwordRecover.setText("Mot de passe oublié ?");
		passwordRecover.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				passwordRecoverMouseClicked(evt);
			}
		});

		panelLoader.setBackground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout panelLoaderLayout = new javax.swing.GroupLayout(panelLoader);
		panelLoader.setLayout(panelLoaderLayout);
		panelLoaderLayout.setHorizontalGroup(panelLoaderLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 83, Short.MAX_VALUE));
		panelLoaderLayout.setVerticalGroup(panelLoaderLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		userNameControl.setBackground(new java.awt.Color(255, 255, 255));
		userNameControl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		userNameControl.setForeground(new java.awt.Color(255, 0, 0));

		passwordControl.setBackground(new java.awt.Color(255, 255, 255));
		passwordControl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		passwordControl.setForeground(new java.awt.Color(255, 0, 0));

		javax.swing.GroupLayout panelConnexionLayout = new javax.swing.GroupLayout(panelConnexion);
		panelConnexion.setLayout(panelConnexionLayout);
		panelConnexionLayout
				.setHorizontalGroup(panelConnexionLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelConnexionLayout
										.createSequentialGroup()
										.addGap(37, 37, 37)
										.addGroup(
												panelConnexionLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																panelConnexionLayout
																		.createSequentialGroup()
																		.addComponent(connexionLabel,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				150,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(orLabel)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(createAccount,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				104,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(21, 21, 21))
														.addGroup(
																panelConnexionLayout
																		.createSequentialGroup()
																		.addGroup(
																				panelConnexionLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								panelConnexionLayout
																										.createSequentialGroup()
																										.addComponent(
																												connect,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												157,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												panelLoader,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								savePasswordForNextEntry,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								325,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								passwordRecover,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								235,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(0, 79, Short.MAX_VALUE))
														.addGroup(
																panelConnexionLayout
																		.createSequentialGroup()
																		.addGroup(
																				panelConnexionLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								panelConnexionLayout
																										.createSequentialGroup()
																										.addGap(12, 12,
																												12)
																										.addComponent(
																												passwordLabel,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												215,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												passwordControl,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))
																						.addGroup(
																								panelConnexionLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING,
																												false)
																										.addComponent(
																												errorLabel,
																												javax.swing.GroupLayout.Alignment.LEADING,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												password,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												361,
																												Short.MAX_VALUE)
																										.addGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												panelConnexionLayout
																														.createSequentialGroup()
																														.addComponent(
																																userLabel,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																206,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																														.addComponent(
																																userNameControl,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addComponent(
																												userName)))
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))));
		panelConnexionLayout.setVerticalGroup(panelConnexionLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelConnexionLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								panelConnexionLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(connexionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(orLabel)
										.addComponent(createAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(32, 32, 32)
						.addGroup(
								panelConnexionLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(userNameControl, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30)
						.addGroup(
								panelConnexionLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(passwordControl, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26)
						.addComponent(savePasswordForNextEntry)
						.addGap(18, 18, 18)
						.addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								panelConnexionLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(connect, javax.swing.GroupLayout.DEFAULT_SIZE, 48,
												Short.MAX_VALUE)
										.addComponent(panelLoader, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
						.addComponent(passwordRecover, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

		sloganOne.setFont(new java.awt.Font("Vani", 0, 12)); // NOI18N
		sloganOne.setForeground(new java.awt.Color(102, 0, 0));
		sloganOne
				.setText("<html><b>L'application de gestion de production</b> permet de gérer la production, les matières premières, et <br>\r\n les fournisseurs, avec tous les détails possibles afin d'oufrir aux managers <br> \r\n une vision globale sur le cout de chaque production.\r\n</htmlL>");

		imgWrite.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/img/write.png")));

		sloganTwo.setFont(new java.awt.Font("Vani", 0, 12)); // NOI18N
		sloganTwo
				.setText("<html><b>Capturez tout</b><br>Enregistrez vos Ordres de fabrication<br>\n (cout, délai, qualité, …)</html>");

		imgWriteTwo.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/img/reservation.png")));

		sloganThree.setFont(new java.awt.Font("Vani", 0, 12)); // NOI18N
		sloganThree
				.setText("<html><b>Accès partout</b><br> </html>");

		imgWriteThree.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/img/search.png")));

		sloganFour.setFont(new java.awt.Font("Vani", 0, 12)); // NOI18N
		sloganFour
				.setText("<html><b>Retrouvez tout rapidement</b><br> Recherche instantanée des entités qui rentrent dans le processus de fabrication</html>");

		imgWriteThree1.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/img/simulate.png")));

		sloganFour1.setFont(new java.awt.Font("Vani", 0, 12)); // NOI18N
		sloganFour1
				.setText("<html><b>Simulez avant d'aller</b><br> </html>");

		javax.swing.GroupLayout panelAuthentificationLayout = new javax.swing.GroupLayout(panelAuthentification);
		panelAuthentification.setLayout(panelAuthentificationLayout);
		panelAuthentificationLayout
				.setHorizontalGroup(panelAuthentificationLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelAuthentificationLayout
										.createSequentialGroup()
										.addGroup(
												panelAuthentificationLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																panelAuthentificationLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																		.addComponent(welcomeLabel,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				465,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				panelAuthentificationLayout
																						.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(sloganOne)))
														.addGroup(
																panelAuthentificationLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				panelAuthentificationLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								panelAuthentificationLayout
																										.createSequentialGroup()
																										.addComponent(
																												imgWrite,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												75,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(18, 18,
																												18)
																										.addComponent(
																												sloganTwo,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												300,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								panelAuthentificationLayout
																										.createSequentialGroup()
																										.addComponent(
																												imgWriteTwo,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												75,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(18, 18,
																												18)
																										.addComponent(
																												sloganThree,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												300,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								panelAuthentificationLayout
																										.createSequentialGroup()
																										.addComponent(
																												imgWriteThree,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												75,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(18, 18,
																												18)
																										.addComponent(
																												sloganFour,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												300,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								panelAuthentificationLayout
																										.createSequentialGroup()
																										.addComponent(
																												imgWriteThree1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												75,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(18, 18,
																												18)
																										.addComponent(
																												sloganFour1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												300,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(panelConnexion, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));
		panelAuthentificationLayout.setVerticalGroup(panelAuthentificationLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						panelAuthentificationLayout
								.createSequentialGroup()
								.addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(sloganOne, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(
										panelAuthentificationLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														panelAuthentificationLayout
																.createSequentialGroup()
																.addGap(18, 18, 18)
																.addComponent(imgWrite,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 75,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														panelAuthentificationLayout
																.createSequentialGroup()
																.addGap(20, 20, 20)
																.addComponent(sloganTwo,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(18, 18, 18)
								.addGroup(
										panelAuthentificationLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(imgWriteTwo, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(sloganThree))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										panelAuthentificationLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(imgWriteThree, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(sloganFour, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										panelAuthentificationLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(imgWriteThree1, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(sloganFour1, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 40, Short.MAX_VALUE))
				.addGroup(
						panelAuthentificationLayout
								.createSequentialGroup()
								.addGap(177, 177, 177)
								.addComponent(panelConnexion, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelAuthentification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addComponent(panelAuthentification, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		
	
		pack();
		
	}// </editor-fold>

	private void createAccountMouseClicked(java.awt.event.MouseEvent evt) {

		//CreateAccount newAccount = new CreateAccount();
		// this.setVisible(false);
	//	newAccount.setVisible(true);

	}

	private void passwordActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void connectActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		
			

			 login =userName.getText();
			String pw =password.getText();
		  //  query="SELECT * from UTILISATEUR  where Login='"+login+"' and password='"+pw+"'" ;
		  // rset=stx.executeQuery(query);
		   
		   utilisateur = utiliseurDAO.findUtilisateurByLoginMotPasse(login, pw);
		  
		   ImageIcon okIcon = new ImageIcon(this.getClass().getResource("/img/strive.png"));
			if(utilisateur !=null && utilisateur.getId()>0) {
				try{
				Main window = new Main();
				window.setVisible(true);
				}catch (Exception e1) {
			 		  JOptionPane.showMessageDialog(null, "Exception 1  "+e1);
				 		}
				
				//MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
				this.setVisible(false);
				//entityFlatView.setVisible(true);
				try{
				new NotificationBuilder()
				.withStyle(new DarkDefaultNotification())
				.withTitle("Bienvenue " + userName.getText())
				.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
				.withDisplayTime(4000).withIcon(okIcon).showNotification();
				this.dispose();
				}catch (Exception e1) {
			 		  JOptionPane.showMessageDialog(null, "Exception   2"+e1);
				 		}
			} else {
				ImageIcon errorIcon = new ImageIcon(this.getClass().getResource("/img/faint.png"));
				
				setErrorLabel("L'e-mail/nom utilisateur ou le mot de passe saisi est incorrect.");
				new NotificationBuilder().withStyle(new DarkDefaultNotification())
				.withTitle("Dinning Room Application")
				.withMessage("Nom utilisateur ou mot de passe invalide").withDisplayTime(3000)
				.withIcon(errorIcon).showNotification();


			}

		/*//AuthentificationController controller = new AuthentificationController();
		boolean response = controller.evaluateFields(this);
		if (response) {
			//SecurityImplUtil authentificationUtil = new SecurityImplUtil();

		//	current_User = authentificationUtil.checkUser(getUserName(), getPassword());

			
			if (current_User == null) {
				Utils.setCURRENT_NBR_ATTEMPT(nbrAttempt++);
				if (Utils.getCURRENT_NBR_ATTEMPT() < Utils.getMAX_NBR_ATTEMPT()) {
					setErrorLabel("L'e-mail/nom utilisateur ou le mot de passe saisi est incorrect.");
									} else {
					connect.setEnabled(false);
					setErrorLabel("vous avez dépassé le nombre de tentative autorisé");
				}
			} else {

				new NotificationBuilder()
						.withStyle(new DarkDefaultNotification())
						.withTitle("Bienvenue " + current_User.getUserName())
						.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
						.withDisplayTime(4000).withIcon(okIcon).showNotification();

				MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
				this.setVisible(false);
				entityFlatView.setVisible(true);
			}

		}*/

	}
	
	
	// connection key pressed
	
	private void connectkeypressed(java.awt.event.KeyEvent evt) throws SQLException {
		
		

		 login =userName.getText();
		String pw =password.getText();
	  //  query="SELECT * from UTILISATEUR  where Login='"+login+"' and password='"+pw+"'" ;
	  // rset=stx.executeQuery(query);
	   
	   utilisateur = utiliseurDAO.findUtilisateurByLoginMotPasse(login, pw);
	  
	   ImageIcon okIcon = new ImageIcon(this.getClass().getResource("/img/strive.png"));
		if(utilisateur !=null && utilisateur.getId()>0) {
			try{
				
			Main window = new Main();
			window.setVisible(true);
		
			}catch (Exception e1) {
		 		  JOptionPane.showMessageDialog(null, "Exception 1  "+e1);
			 		}
			
			
			//MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
			this.setVisible(false);
			//entityFlatView.setVisible(true);
			try{
			new NotificationBuilder()
			.withStyle(new DarkDefaultNotification())
			.withTitle("Bienvenue " + userName.getText())
			.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
			.withDisplayTime(4000).withIcon(okIcon).showNotification();
			this.dispose();
			}catch (Exception e1) {
		 		  JOptionPane.showMessageDialog(null, "Exception   2"+e1);
			 		}
		} else {
			ImageIcon errorIcon = new ImageIcon(this.getClass().getResource("/img/faint.png"));
			
			setErrorLabel("L'e-mail/nom utilisateur ou le mot de passe saisi est incorrect.");
			new NotificationBuilder().withStyle(new DarkDefaultNotification())
			.withTitle("Dinning Room Application")
			.withMessage("Nom utilisateur ou mot de passe invalide").withDisplayTime(3000)
			.withIcon(errorIcon).showNotification();


		}

	/*//AuthentificationController controller = new AuthentificationController();
	boolean response = controller.evaluateFields(this);
	if (response) {
		//SecurityImplUtil authentificationUtil = new SecurityImplUtil();

	//	current_User = authentificationUtil.checkUser(getUserName(), getPassword());

		
		if (current_User == null) {
			Utils.setCURRENT_NBR_ATTEMPT(nbrAttempt++);
			if (Utils.getCURRENT_NBR_ATTEMPT() < Utils.getMAX_NBR_ATTEMPT()) {
				setErrorLabel("L'e-mail/nom utilisateur ou le mot de passe saisi est incorrect.");
								} else {
				connect.setEnabled(false);
				setErrorLabel("vous avez dépassé le nombre de tentative autorisé");
			}
		} else {

			new NotificationBuilder()
					.withStyle(new DarkDefaultNotification())
					.withTitle("Bienvenue " + current_User.getUserName())
					.withMessage("connexion effectuée avec succès\n" + "votre session a été chargée convenablement")
					.withDisplayTime(4000).withIcon(okIcon).showNotification();

			MainEntityFlatMenu entityFlatView = new MainEntityFlatMenu();
			this.setVisible(false);
			entityFlatView.setVisible(true);
		}

	}*/

}
	
	
	
	
	

	private void passwordRecoverMouseClicked(java.awt.event.MouseEvent evt) {/*
		PasswordForgottenView forgottenView = new PasswordForgottenView();
		forgottenView.setVisible(true);
		connect.setEnabled(true);
	*/}

	private void userNameFocusGained(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
	}

	private void userNameFocusLost(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
	}

	private void userNamePropertyChange(java.beans.PropertyChangeEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AuthentificationView().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	
	private javax.swing.JButton connect;
	private javax.swing.JLabel connexionLabel;
	private javax.swing.JLabel createAccount;
	private javax.swing.JLabel errorLabel;
	private javax.swing.JLabel imgWrite;
	private javax.swing.JLabel imgWriteThree;
	private javax.swing.JLabel imgWriteThree1;
	private javax.swing.JLabel imgWriteTwo;
	private javax.swing.JLabel orLabel;
	private javax.swing.JPanel panelAuthentification;
	private javax.swing.JPanel panelConnexion;
	private javax.swing.JPanel panelLoader;
	private javax.swing.JPasswordField password;
	private javax.swing.JLabel passwordControl;
	private javax.swing.JLabel passwordLabel;
	private javax.swing.JLabel passwordRecover;
	private javax.swing.JCheckBox savePasswordForNextEntry;
	private javax.swing.JLabel sloganFour;
	private javax.swing.JLabel sloganFour1;
	private javax.swing.JLabel sloganOne;
	private javax.swing.JLabel sloganThree;
	private javax.swing.JLabel sloganTwo;
	private javax.swing.JLabel userLabel;
	private javax.swing.JTextField userName;
	private javax.swing.JLabel userNameControl;
	private javax.swing.JLabel welcomeLabel;
//	public static User current_User;
	private int nbrAttempt;
	
	
	
	
	
	
	
	
	// End of variables declaration
}
