package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Chegada;
import modelo.Piloto;
import modelo.Prova;
import regras_negocio.Fachada;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaChegada {

	private JFrame frmTelaChegada;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JFrame janelaBusca;
	private boolean janelaBuscaAberta = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Fachada.inicializar(); //lembrar de apagar e passar para a tela principal
					
					
					TelaChegada window = new TelaChegada();
					window.frmTelaChegada.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaChegada() {
		initialize();
		
		//lembrar de apagar e passar para a tela principal
//		frmTelaChegada.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                Fachada.finalizar(); // Chamando o método de finalização da fachada
//            }
//        });
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTelaChegada = new JFrame();
		frmTelaChegada.setTitle("TELA CHEGADA");
		frmTelaChegada.setBounds(100, 100, 740, 572);
		frmTelaChegada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTelaChegada.getContentPane().setLayout(null);
		
//----------------------------------------------------------------------------------------
//scrollpane + tabela de chegadas
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 15, 478, 179);
		frmTelaChegada.getContentPane().add(scrollPane);
		//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Prova");
	    tableModel.addColumn("Colocacao");
	    tableModel.addColumn("Piloto");
	    
				
		JTable table = new JTable(tableModel);
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(200, 200, 0));
		//table.getColumnModel().getColumn(1).setPreferredWidth(5);
		
		//listando os pilotos do banco
		List<Chegada> chegadas = Fachada.listarChegadas();

		//preenchendo a tabela com os objetos dos pilotos
		for (Chegada chegada : chegadas) {
		    tableModel.addRow(new Object[]{chegada.getProva(), chegada.getColocacao(), chegada.getPiloto()});
		}

		
//----------------------------------------------------------------------------------------
//criar chegada	
		
		//painel criar chegada
		JPanel panel = new JPanel();
		panel.setBounds(20, 204, 210, 304);
		frmTelaChegada.getContentPane().add(panel);
		panel.setLayout(null);
		
		//label ID da prova + caixa de texto
		JLabel lblNewLabel = new JLabel("ID da prova:");
		lblNewLabel.setBounds(10, 116, 81, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(101, 113, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		//label colocacao + caixa de texto
		JLabel lblNewLabel_1 = new JLabel("Colocação:");
		lblNewLabel_1.setBounds(10, 145, 81, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(101, 142, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		//label nome do piloto + caixa de texto
		JLabel lblNewLabel_2 = new JLabel("Nome do Piloto:");
		lblNewLabel_2.setBounds(10, 174, 89, 13);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(101, 171, 96, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		//botão criar chegada + label tratamento de erro
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setToolTipText("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(10, 262, 176, 34);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Criar Chegada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//inserindo prova
				String texto = textField.getText();
				int provaId = Integer.parseInt(texto);
						
				//inserindo colocacao
				String texto1 = textField_1.getText();
				int colocacao = Integer.parseInt(texto1);
				
				//inserindo piloto
				String texto2 = textField_2.getText();
				
				try {					
					
					//criando objeto chegada
					Fachada.criarChegada(provaId, colocacao, texto2);
					lblNewLabel_4.setText("Chegada criada");
					
					//limpa a tabela setando o numero de linhas para 0
					tableModel.setRowCount(0);
					
					//listando os pilotos do banco
					List<Chegada> chegadas = Fachada.listarChegadas();

					//preenchendo a tabela com os objetos dos pilotos
					for (Chegada chegada : chegadas) {
					    tableModel.addRow(new Object[]{chegada.getProva(), chegada.getColocacao(), chegada.getPiloto()});
					}

				}
				catch (Exception ex){
					lblNewLabel_4.setText(ex.getMessage());
				}
			}
		});
		btnNewButton.setBounds(35, 211, 120, 41);
		panel.add(btnNewButton);
		
		
//----------------------------------------------------------------------------------------
//atualizar chegada
		
		//painel atualizar chegada
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(240, 204, 218, 304);
		frmTelaChegada.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//label ID da prova + caixa de texto
		JLabel lblNewLabel_3 = new JLabel("ID da prova:");
		lblNewLabel_3.setBounds(10, 114, 81, 13);
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(103, 111, 96, 19);
		panel_1.add(textField_3);
		
		//label colocacao + caixa de texto
		JLabel lblNewLabel_1_1 = new JLabel("Colocação:");
		lblNewLabel_1_1.setBounds(10, 143, 81, 13);
		panel_1.add(lblNewLabel_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(103, 140, 96, 19);
		panel_1.add(textField_4);
		
		//label nome do piloto + caixa de texto
		JLabel lblNewLabel_2_1 = new JLabel("Nome do Piloto:");
		lblNewLabel_2_1.setBounds(10, 172, 97, 13);
		panel_1.add(lblNewLabel_2_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(103, 169, 96, 19);
		panel_1.add(textField_5);
		
		//botão atualizar chegada + label tratamento de erro
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setToolTipText("");
		lblNewLabel_4_1.setForeground(Color.RED);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(23, 260, 176, 34);
		panel_1.add(lblNewLabel_4_1);
		
		JButton btnNewButton_1 = new JButton("Atualizar Chegada");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//copiar template do criar chegada
				
			}
		});
		btnNewButton_1.setBounds(23, 209, 168, 41);
		panel_1.add(btnNewButton_1);
		
		
//----------------------------------------------------------------------------------------
//deletar chegada	
		
		
		//painel deletar chegada
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(468, 204, 218, 304);
		frmTelaChegada.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//label ID da prova + caixa de texto
		JLabel lblNewLabel_3_1 = new JLabel("ID da prova:");
		lblNewLabel_3_1.setBounds(10, 139, 81, 13);
		panel_2.add(lblNewLabel_3_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(98, 136, 96, 19);
		panel_2.add(textField_6);
		
		//label nome do piloto + caixa de texto
		JLabel lblNewLabel_2_1_1 = new JLabel("Nome do Piloto:");
		lblNewLabel_2_1_1.setBounds(10, 168, 81, 13);
		panel_2.add(lblNewLabel_2_1_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(98, 165, 96, 19);
		panel_2.add(textField_7);
		
		//botão deletar chegada + label tratamento de erro
		JLabel lblNewLabel_4_1_1 = new JLabel("");
		lblNewLabel_4_1_1.setToolTipText("");
		lblNewLabel_4_1_1.setForeground(Color.RED);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(10, 260, 176, 34);
		panel_2.add(lblNewLabel_4_1_1);
		
		JButton btnNewButton_2 = new JButton("Deletar Chegada");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//recebendo parametro prova
				String texto = textField_6.getText();
				int provaId = Integer.parseInt(texto);
				
				//recebendo parametro piloto
				String texto2 = textField_7.getText();
				
				try {
					//deletando objeto chegada
					Chegada chegada = Fachada.listarChegada(provaId);
					Fachada.deletarChegada(provaId, texto2);
					lblNewLabel_4_1_1.setText("Chegada deletada");
					
					//limpa a tabela setando o numero de linhas para 0
					tableModel.setRowCount(0);
					
					//listando os pilotos do banco
					List<Chegada> chegadas = Fachada.listarChegadas();

					//preenchendo a tabela com os objetos dos pilotos
					for (Chegada chegada : chegadas) {
					    tableModel.addRow(new Object[]{chegada.getProva(), chegada.getColocacao(), chegada.getPiloto()});
					}
					
				} 
				
				catch (Exception ex) {
					lblNewLabel_4_1_1.setText(ex.getMessage());
				}
				
			}
		});
		btnNewButton_2.setBounds(21, 208, 172, 41);
		panel_2.add(btnNewButton_2);
		
		
//----------------------------------------------------------------------------------------
//botões auxiliares	
		
		//botão localizar chegada
		JButton btnNewButton_5 = new JButton("Localizar Chegada");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!janelaBuscaAberta) {
					
					janelaBuscaAberta = true;
					
			        janelaBusca = new JFrame("Buscar Chegada");
			        janelaBusca.setSize(300, 190);
			        janelaBusca.setLocationRelativeTo(null); // Centralize a janela na tela
			        janelaBusca.setResizable(false);
			        janelaBusca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Feche apenas a janela de busca ao clicar em fechar
	
			        janelaBusca.addWindowListener(new WindowAdapter() {
		                @Override
		                public void windowClosed(WindowEvent e) {
		                    // Defina o estado da janela de busca como fechada ao fechá-la
		                    janelaBuscaAberta = false;
		                }
		            });
			        
			        
			        // Adicione um campo de texto e um botão de localizar à janela de busca
			        JTextField textFieldProva = new JTextField(20);
			        JTextField textFieldPiloto = new JTextField(20);
			        JButton btnLocalizar = new JButton("Localizar");
	
			        btnLocalizar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			               
			            	String texto = textFieldProva.getText();
			            	int provaId = Integer.parseInt(texto);
			                String nomePiloto = textFieldPiloto.getText();
			                
			                try {
				                Chegada chegada = Fachada.listarChegada(provaId, nomePiloto);
				                
				                //limpa a tabela setando o numero de linhas para 0
				                tableModel.setRowCount(0);
				              
				                //preenche a tabela com o objeto do piloto encontrado
				                tableModel.addRow(new Object[]{chegada.getProva(), chegada.getColocacao(), chegada.getPiloto()});
				                
				                //fecha a janela de busca individual de piloto
				                janelaBusca.dispose();	
			                }
			                catch (Exception ex) {
		                        // Exibe uma mensagem de erro com opção de fechar
		                        JOptionPane.showMessageDialog(janelaBusca, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		                    }
			                
			                
			                			                
			            }
			        });

		        janelaBusca.getContentPane().setLayout(new FlowLayout());
		        janelaBusca.getContentPane().add(new JLabel("ID da prova : "));
		        janelaBusca.getContentPane().add(textFieldProva);
		        janelaBusca.getContentPane().add(new JLabel("Nome do Piloto: "));
		        janelaBusca.getContentPane().add(textFieldPiloto);
		        janelaBusca.getContentPane().add(btnLocalizar);

		        // Exiba a janela de busca
		        janelaBusca.setVisible(true);
				}
				
			}
		});
		btnNewButton_5.setBounds(515, 15, 190, 41);
		frmTelaChegada.getContentPane().add(btnNewButton_5);
		
		//botão listar todos os pilotos
		JButton btnNewButton_3 = new JButton("Listar Chegadas");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os pilotos do banco
				List<Chegada> chegadas = Fachada.listarChegadas();

				//preenchendo a tabela com os objetos dos pilotos
				for (Chegada chegada : chegadas) {
				    tableModel.addRow(new Object[]{chegada.getProva(), chegada.getColocacao(), chegada.getPiloto()});
				}
			}
		});
		btnNewButton_3.setBounds(515, 67, 190, 41);
		frmTelaChegada.getContentPane().add(btnNewButton_3);
		
		//botão deletar todos os pilotos
		JButton btnNewButton_4 = new JButton("Deletar Todas as Chegadas");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fachada.deletarTodasChegadas();
				
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os pilotos do banco
				List<Chegada> chegadas = Fachada.listarChegadas();

				//preenchendo a tabela com os objetos dos pilotos
				for (Chegada chegada : chegadas) {
				    tableModel.addRow(new Object[]{chegada.getProva(), chegada.getColocacao(), chegada.getPiloto()});
				}
			}
		});
		btnNewButton_4.setBounds(515, 140, 190, 41);
		frmTelaChegada.getContentPane().add(btnNewButton_4);
		
		
		
		
		frmTelaChegada.setVisible(true);
	}
}
