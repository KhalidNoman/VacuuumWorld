/**************************************************************************
 
 * Name: 			Khalid Noman
 * Professor: 		Dr. Min Kyung An
 * Course Name:		Spc Tpcs in Computer Sci
 * Course Number:	4340 01
 * Semester: 		2021 Summer 10 week
 
 **************************************************************************/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.util.Random;

public class VacuumKhalidNoman extends JFrame {
	
	//AI Handling and Variables
	int size = 8;
	Vacuum myTest = new Vacuum(size);
	
	
	//GUI Handling and Variables
	private JPanel contentPane;
	private JTable tblWorld;
	private JLabel lblCurrAction;
	private JLabel lblCurrScore;
	private JLabel lblCurrPosition;
	private JLabel lblTotalScore;
	private JLabel lblTotalSteps;
	private JLabel lblDirty;
	private JLabel lblLobby;
	private JLabel lblRemaining;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacuumKhalidNoman frame = new VacuumKhalidNoman();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VacuumKhalidNoman() {
		setTitle("Vacuum World");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(580, 10, 400, 260);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrent = new JLabel("Information");
		lblCurrent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrent.setBounds(10, 11, 166, 24);
		panel.add(lblCurrent);
		
		JLabel lblCurrA = new JLabel("Action: ");
		lblCurrA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrA.setBounds(20, 50, 100, 20);
		panel.add(lblCurrA);
		
		JLabel lblCurrS = new JLabel("Score change: ");
		lblCurrS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrS.setBounds(20, 80, 100, 20);
		panel.add(lblCurrS);
		
		lblCurrAction = new JLabel("");
		lblCurrAction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrAction.setBounds(130, 50, 100, 20);
		panel.add(lblCurrAction);
		
		lblCurrScore = new JLabel("");
		lblCurrScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrScore.setBounds(130, 80, 100, 20);
		panel.add(lblCurrScore);
		
		JLabel lblCurrPos = new JLabel("Location (x,y):");
		lblCurrPos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrPos.setBounds(20, 200, 100, 20);
		panel.add(lblCurrPos);
		
		lblCurrPosition = new JLabel("");
		lblCurrPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrPosition.setBounds(130, 200, 100, 20);
		panel.add(lblCurrPosition);
		
		DefaultTableModel model = new DefaultTableModel(size,size);
		tblWorld = new JTable(model){ 
			//Changes color of each individual cell based on true or false
	        @Override
	        public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
	                int columnIndex) {
	            JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
	            if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("")) {
	                component.setBackground(Color.white);
	            } else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("Dirty")){
	                component.setBackground(Color.RED);

	            } else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("vacuum")){
	                component.setBackground(Color.BLUE);
	            } else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("visited")){
	                component.setBackground(Color.darkGray);
	            } 
	            /* Used for initial testing
	              else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("True")){
                	component.setBackground(Color.RED);
            	} else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("False")){
                	component.setBackground(Color.white);*/
	            return component;
	        }
	    };
		tblWorld.setBounds(10, 10, 540, 540);
		tblWorld.setBackground(Color.LIGHT_GRAY);
		tblWorld.setEnabled(false);
		tblWorld.setRowSelectionAllowed(false);
		tblWorld.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(tblWorld);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(580, 277, 400, 260);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblShutOff = new JLabel("After Shut off:");
		lblShutOff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShutOff.setBounds(10, 11, 166, 24);
		panel_1.add(lblShutOff);
		
		JLabel lblTotalScr = new JLabel("Total Score:");
		lblTotalScr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalScr.setBounds(20, 80, 100, 20);
		panel_1.add(lblTotalScr);
		
		JLabel lblTotalStps = new JLabel("Total Steps:");
		lblTotalStps.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalStps.setBounds(20, 50, 100, 20);
		panel_1.add(lblTotalStps);
		
		lblTotalScore = new JLabel("");
		lblTotalScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalScore.setBounds(130, 80, 260, 20);
		panel_1.add(lblTotalScore);
		
		lblTotalSteps = new JLabel("");
		lblTotalSteps.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalSteps.setBounds(130, 50, 260, 20);
		panel_1.add(lblTotalSteps);
		
		JLabel lblDirtyBld = new JLabel("Building Dirty:");
		lblDirtyBld.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDirtyBld.setBounds(20, 110, 100, 20);
		panel_1.add(lblDirtyBld);
		
		lblDirty = new JLabel("");
		lblDirty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDirty.setBounds(130, 110, 260, 20);
		panel_1.add(lblDirty);
		
		lblLobby = new JLabel("");
		lblLobby.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLobby.setBounds(130, 140, 260, 20);
		panel_1.add(lblLobby);
		
		JLabel lblLobbyBld = new JLabel("At Lobby:");
		lblLobbyBld.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLobbyBld.setBounds(20, 140, 100, 20);
		panel_1.add(lblLobbyBld);
		
		JLabel lblRem = new JLabel("Dirty Rooms:");
		lblRem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRem.setBounds(20, 170, 100, 20);
		panel_1.add(lblRem);
		
		lblRemaining = new JLabel("");
		lblRemaining.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemaining.setBounds(130, 170, 260, 20);
		panel_1.add(lblRemaining);
		
		for(int i = 0; i < size; i++) {
			tblWorld.setRowHeight(67);
			for(int j = 0; j < size; j++) {
				/* used for initial testing
				  tblWorld.setValueAt(myTest.grid[i][j], i, j);
				 */
				if(myTest.grid[i][j])
					tblWorld.setValueAt("Dirty", i, j);
				else
					tblWorld.setValueAt("", i, j);
			}
		}
		
		myThread var = new myThread();
		var.start();
	}
	
	public class myThread extends Thread{
		public void run() {
			/*********************************************************************************
			//Pattern for best score
			for(int i = 0; i < size - 1; i++) {
				tblWorld.setValueAt("Visited" , myTest.position[0], myTest.position[1]);
				myTest.right();
				lblCurrAction.setText("Right");
				lblCurrScore.setText("-10");
				if(myTest.grid[myTest.position[0]][myTest.position[1]] == true) {
					myTest.suck();
					lblCurrAction.setText("Suck");
					lblCurrScore.setText("+100");
				}
				lblCurrPosition.setText("(" + myTest.position[1] + ", " + (-myTest.position[0]+7) + ")"); 
				if(myTest.position[0] == 7 && myTest.position[1] == 0)
					lblCurrPosition.setText(lblCurrPosition.getText().concat(" Lobby"));
				lblTotalScore.setText(myTest.score.toString());
				lblTotalSteps.setText(myTest.steps.toString());
				tblWorld.setValueAt("Vacuum" , myTest.position[0], myTest.position[1]);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(myTest.grid[myTest.position[0]][myTest.position[1]] == true) {
				myTest.suck();
				lblCurrAction.setText("Suck");
				lblCurrScore.setText("+100");
			}
			tblWorld.setValueAt("Visited" , myTest.position[0], myTest.position[1]);
			myTest.up();
			lblCurrAction.setText("Up");
			lblCurrScore.setText("-10");
			lblCurrPosition.setText("(" + myTest.position[1] + ", " + (-myTest.position[0]+7) + ")"); 
			if(myTest.position[0] == 7 && myTest.position[1] == 0)
				lblCurrPosition.setText(lblCurrPosition.getText().concat(" Lobby"));
			lblTotalScore.setText(myTest.score.toString());
			lblTotalSteps.setText(myTest.steps.toString());
			tblWorld.setValueAt("Vacuum" , myTest.position[0], myTest.position[1]);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean did = false;
			for(int i = 0; i < size && myTest.on; i++) {
				if(!myTest.isDirty()) {
					myTest.shutOff();
					lblTotalScore.setText(myTest.score.toString());
					lblTotalSteps.setText(myTest.steps.toString());
					lblLobby.setText(myTest.isLobby().toString());
					lblDirty.setText(myTest.isDirty().toString());
					break;
				}
				for(int j = 0; j < size-2 && myTest.on; j++) {
					if(!myTest.isDirty()) {
						myTest.shutOff();
						lblTotalScore.setText(myTest.score.toString());
						lblTotalSteps.setText(myTest.steps.toString());
						lblLobby.setText(myTest.isLobby().toString());
						lblDirty.setText(myTest.isDirty().toString());
						break;
					}
					tblWorld.setValueAt("Visited" , myTest.position[0], myTest.position[1]);
					if(!did) {
						myTest.up();
						lblCurrAction.setText("Up");
					} else {
						myTest.down();
						lblCurrAction.setText("down");
					}
						
					lblCurrScore.setText("-10");
					if(myTest.grid[myTest.position[0]][myTest.position[1]] == true) {
						myTest.suck();
						lblCurrAction.setText("Suck");
						lblCurrScore.setText("+100");
					}
					lblCurrPosition.setText("(" + myTest.position[1] + ", " + (-myTest.position[0]+7) + ")"); 
					if(myTest.position[0] == 7 && myTest.position[1] == 0)
						lblCurrPosition.setText(lblCurrPosition.getText().concat(" Lobby"));
					lblTotalScore.setText(myTest.score.toString());
					lblTotalSteps.setText(myTest.steps.toString());
					tblWorld.setValueAt("Vacuum" , myTest.position[0], myTest.position[1]);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				tblWorld.setValueAt("Visited" , myTest.position[0], myTest.position[1]);
				myTest.left();
				lblCurrAction.setText("Left");
				lblCurrScore.setText("-10");
				if(myTest.grid[myTest.position[0]][myTest.position[1]] == true) {
					myTest.suck();
					lblCurrAction.setText("Suck");
					lblCurrScore.setText("+100");
				}
				lblCurrPosition.setText("(" + myTest.position[1] + ", " + (-myTest.position[0]+7) + ")"); 
				if(myTest.position[0] == 7 && myTest.position[1] == 0)
					lblCurrPosition.setText(lblCurrPosition.getText().concat(" Lobby"));
				lblTotalScore.setText(myTest.score.toString());
				lblTotalSteps.setText(myTest.steps.toString());
				tblWorld.setValueAt("Vacuum" , myTest.position[0], myTest.position[1]);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				did = !did;
			}
				
			if((myTest.steps == 1000 || !myTest.isDirty()) && myTest.on) {
				myTest.shutOff();
				lblTotalScore.setText(myTest.score.toString());
				lblTotalSteps.setText(myTest.steps.toString());
				lblLobby.setText(myTest.isLobby().toString());
				lblDirty.setText(myTest.isDirty().toString());
			}
			/*********************************************************************************/
			
			
			/*********************************************************************************/
			//Random action taken
			while(myTest.on) {
				tblWorld.setValueAt("Visited" , myTest.position[0], myTest.position[1]);
				
				Random rand = new Random();
				int action = rand.nextInt(4);
				switch(action) {
					case 0: 
						myTest.up();
						lblCurrAction.setText("Up");
						break;
					case 1:
						myTest.down();
						lblCurrAction.setText("Down");
						break;
					case 2:
						myTest.left();
						lblCurrAction.setText("Left");
						break;
					case 3:
						myTest.right();
						lblCurrAction.setText("Right");
						break;
				}
				
				lblCurrScore.setText("-10");
				if(myTest.grid[myTest.position[0]][myTest.position[1]] == true) {
					myTest.suck();
					lblCurrAction.setText("Suck");
					lblCurrScore.setText("+100");
				}
				lblCurrPosition.setText("(" + myTest.position[1] + ", " + (-myTest.position[0]+7) + ")"); 
				if(myTest.position[0] == 7 && myTest.position[1] == 0)
					lblCurrPosition.setText(lblCurrPosition.getText().concat(" Lobby"));
				tblWorld.setValueAt("Vacuum" , myTest.position[0], myTest.position[1]);
				lblTotalScore.setText(myTest.score.toString());
				lblTotalSteps.setText(myTest.steps.toString());
				
				
				if(myTest.steps == 1000 || myTest.dirty == 0) {
					myTest.shutOff();
					lblTotalScore.setText(myTest.score.toString());
					lblTotalSteps.setText(myTest.steps.toString());
					lblLobby.setText(myTest.isLobby().toString());
					lblDirty.setText(myTest.isDirty().toString());
					lblRemaining.setText(myTest.dirty + " Left");
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			/*********************************************************************************/
			
		}
	}
}
