package appswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Piloto;
import regras_negocio.Fachada;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaPiloto {

	private JFrame frmTelaPiloto;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
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
					
					TelaPiloto window = new TelaPiloto();
					window.frmTelaPiloto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPiloto() {
		initialize();
		
		//lembrar de apagar e passar para a tela principal
//		frmTelaPiloto.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                Fachada.finalizar(); // Chamando o método de finalização da fachada
//            }
//        });
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTelaPiloto = new JFrame();
		frmTelaPiloto.setTitle("TELA PILOTO");
		frmTelaPiloto.setBounds(100, 100, 762, 660);
		frmTelaPiloto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTelaPiloto.getContentPane().setLayout(null);
		
//----------------------------------------------------------------------------------------
//scrollpane + tabela de pilotos
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 15, 478, 179);
		frmTelaPiloto.getContentPane().add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Nome do Piloto");
	    tableModel.addColumn("Escuderia");
		
		table = new JTable(tableModel);
		table.setForeground(new Color(255, 255, 255));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(255, 104, 104));
		
		//listando os pilotos do banco
		List<Piloto> pilotos = Fachada.listarPilotos();

		//preenchendo a tabela com os objetos dos pilotos
		for (Piloto piloto : pilotos) {
		    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
		}
		
//----------------------------------------------------------------------------------------
//criar piloto
		
		//painel criar piloto
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBounds(20, 297, 196, 304);
		frmTelaPiloto.getContentPane().add(panel);
		panel.setLayout(null);
		
		//label nome + caixa de texto
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(20, 129, 28, 13);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		textField_1 = new JTextField();
		textField_1.setBounds(70, 126, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		//label escuderia + caixa de texto
		JLabel lblNewLabel_1 = new JLabel("Escuderia");
		lblNewLabel_1.setBounds(10, 158, 50, 13);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		textField = new JTextField();
		textField.setBounds(70, 155, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		//botão criar piloto + label tratamento de erro
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setBounds(10, 262, 176, 34);
		panel.add(lblNewLabel_3);
			
		JButton btnNewButton = new JButton("Criar Piloto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textField_1.getText();
					String escuderia = textField.getText();
					
					Fachada.criarPiloto(nome, escuderia);
					lblNewLabel_3.setText("Piloto criado");
					
					//limpa a tabela setando o numero de linhas para 0
					tableModel.setRowCount(0);
					
					//listando os pilotos do banco
					List<Piloto> pilotos = Fachada.listarPilotos();
					
					//preenchendo a tabela com os objetos dos pilotos
					for (Piloto piloto : pilotos) {
					    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
					}
					
				
				}
				catch (Exception ex){
					lblNewLabel_3.setText(ex.getMessage());
				}
				
			}
		});
		btnNewButton.setBounds(35, 211, 120, 41);
		panel.add(btnNewButton);
		
//----------------------------------------------------------------------------------------
//atualizar piloto
		
		
		//painel atualizar piloto
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_1.setBounds(226, 297, 218, 304);
		frmTelaPiloto.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		//label nome do piloto + caixa de texto
		JLabel lblNewLabel_2_2 = new JLabel("Nome do Piloto");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2.setBounds(17, 32, 76, 13);
		panel_1.add(lblNewLabel_2_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(96, 29, 96, 19);
		panel_1.add(textField_5);
		
		//label novo nome + caixa de texto
		JLabel lblNewLabel_2 = new JLabel("Novo Nome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(17, 129, 69, 13);
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(96, 126, 96, 19);
		panel_1.add(textField_2);
		
		//label escuderia + caixa de texto
		JLabel lblNewLabel_1_1 = new JLabel("Escuderia");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1_1.setBounds(27, 158, 50, 13);
		panel_1.add(lblNewLabel_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(96, 155, 96, 19);
		panel_1.add(textField_3);
		
		//botão atualizar piloto + label tratamento de erro
		JLabel lblNewLabel_4_1 = new JLabel("---------------------------");
		lblNewLabel_4_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(17, 71, 175, 34);
		panel_1.add(lblNewLabel_4_1);
		
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(17, 260, 175, 34);
		panel_1.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Atualizar Piloto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nomePiloto = textField_5.getText();
					Fachada.listarPiloto(nomePiloto);
					
					String novoNome = textField_2.getText();
					String escuderia = textField_3.getText();
					
					Fachada.editarPiloto(nomePiloto, novoNome, escuderia);
					lblNewLabel_4.setText("Piloto atualizado");
					
					//limpa a tabela setando o numero de linhas para 0
					tableModel.setRowCount(0);
					
					//listando os pilotos do banco
					List<Piloto> pilotos = Fachada.listarPilotos();

					//preenchendo a tabela com os objetos dos pilotos
					for (Piloto piloto : pilotos) {
					    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
					}
					
				
				}
				catch (Exception ex){
					lblNewLabel_4.setText(ex.getMessage());
				}			
				//catch (Exception ex) {
		            // Lógica para lidar com a exceção de listagem de piloto
		        //    lblNewLabel_4_1.setText("Erro ao listar piloto: " + ex.getMessage());
		        //}
				
			}
		});
		btnNewButton_1.setBounds(53, 209, 120, 41);
		panel_1.add(btnNewButton_1);
		
		
	
//----------------------------------------------------------------------------------------
//deletar piloto
		
		//painel deletar piloto
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_2.setBounds(451, 297, 208, 304);
		frmTelaPiloto.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//label nome + caixa de texto
		JLabel lblNewLabel_2_1 = new JLabel("Nome");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_1.setBounds(21, 129, 28, 13);
		panel_2.add(lblNewLabel_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(59, 126, 96, 19);
		panel_2.add(textField_4);
		
		//botão deletar piloto + label tratamento de erro
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(10, 259, 169, 34);
		panel_2.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Deletar Piloto");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textField_4.getText();
					
					Fachada.deletarPiloto(nome);
					lblNewLabel_5.setText("piloto deletado");
					
					//limpa a tabela setando o numero de linhas para 0
					tableModel.setRowCount(0);
							
					//listando os pilotos do banco
					List<Piloto> pilotos = Fachada.listarPilotos();

					//preenchendo a tabela com os objetos dos pilotos
					for (Piloto piloto : pilotos) {
					    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
					}
					
				
				}
				catch (Exception ex){
					lblNewLabel_5.setText(ex.getMessage());
				}
				
				
			}
		});
		btnNewButton_2.setBounds(35, 208, 120, 41);
		panel_2.add(btnNewButton_2);
		
		
//----------------------------------------------------------------------------------------
//botões auxiliares
		
		//botão localizar piloto
		JButton btnNewButton_5 = new JButton("Localizar Piloto");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!janelaBuscaAberta) {
					
					janelaBuscaAberta = true;
					
			        janelaBusca = new JFrame("Buscar Piloto");
			        janelaBusca.setSize(300, 150);
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
			        JTextField textFieldBusca = new JTextField(20);
			        JButton btnLocalizar = new JButton("Localizar");
	
			        btnLocalizar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			               
			                String nomePiloto = textFieldBusca.getText();
			                
			                try {
				                Piloto piloto = Fachada.listarPiloto(nomePiloto);
				                
				                //limpa a tabela setando o numero de linhas para 0
				                tableModel.setRowCount(0);
				              
				                //preenche a tabela com o objeto do piloto encontrado
				                tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
				                
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
		        janelaBusca.getContentPane().add(new JLabel("Nome do Piloto: "));
		        janelaBusca.getContentPane().add(textFieldBusca);
		        janelaBusca.getContentPane().add(btnLocalizar);

		        // Exiba a janela de busca
		        janelaBusca.setVisible(true);
				}
			}
		});
		btnNewButton_5.setBounds(521, 15, 177, 41);
		frmTelaPiloto.getContentPane().add(btnNewButton_5);
		
		
		//botão listar todos os pilotos
		JButton btnNewButton_4 = new JButton("Listar Pilotos");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os pilotos do banco
				List<Piloto> pilotos = Fachada.listarPilotos();

				//preenchendo a tabela com os objetos dos pilotos
				for (Piloto piloto : pilotos) {
				    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
				}
				
			}
		});
		btnNewButton_4.setBounds(521, 66, 177, 41);
		frmTelaPiloto.getContentPane().add(btnNewButton_4);
		

		//botão deletar todos os pilotos
		JButton btnNewButton_3 = new JButton("Deletar Todos os Pilotos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fachada.deletarTodosPilotos();
				
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os pilotos do banco
				List<Piloto> pilotos = Fachada.listarPilotos();

				//preenchendo a tabela com os objetos dos pilotos
				for (Piloto piloto : pilotos) {
				    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
				}
			}
		});
		btnNewButton_3.setBounds(521, 140, 177, 41);
		frmTelaPiloto.getContentPane().add(btnNewButton_3);
		
		//label consulta personalizada + botão consulta personalizada
		JLabel lblNewLabel_6 = new JLabel("Consulta Personalizada >");
		lblNewLabel_6.setForeground(new Color(0, 128, 0));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_6.setBounds(314, 231, 184, 27);
		frmTelaPiloto.getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_5_1 = new JButton("Localizar Piloto p/ Escuderia");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!janelaBuscaAberta) {
					
					janelaBuscaAberta = true;
					
			        janelaBusca = new JFrame("Buscar Piloto");
			        janelaBusca.setSize(300, 150);
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
			        JTextField textFieldBusca = new JTextField(20);
			        JButton btnLocalizar = new JButton("Localizar");
	
			        btnLocalizar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			               
			                String nomeEscuderia = textFieldBusca.getText();
			                
			                try {
			                	//limpa a tabela setando o numero de linhas para 0
								tableModel.setRowCount(0);
								
								//listando os pilotos do banco
								List<Piloto> pilotos = Fachada.queryListaEscuderias(nomeEscuderia);

								//preenchendo a tabela com os objetos dos pilotos
								for (Piloto piloto : pilotos) {
								    tableModel.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
								}
								
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
		        janelaBusca.getContentPane().add(new JLabel("Nome da Escuderia: "));
		        janelaBusca.getContentPane().add(textFieldBusca);
		        janelaBusca.getContentPane().add(btnLocalizar);

		        // Exiba a janela de busca
		        janelaBusca.setVisible(true);
				}
				
			}
		});
		btnNewButton_5_1.setBounds(508, 225, 218, 41);
		frmTelaPiloto.getContentPane().add(btnNewButton_5_1);
		
		frmTelaPiloto.setVisible(true);
			
	}
	
}
