package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Piloto;
import modelo.Prova;
import regras_negocio.Fachada;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaProva {

	private JFrame frmTelaProva;
	private JTextField textField;
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
					
					TelaProva window = new TelaProva();
					window.frmTelaProva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaProva() {
		initialize();
		
		//lembrar de apagar e passar para a tela principal
//		frmTelaProva.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                Fachada.finalizar(); // Chamando o método de finalização da fachada
//	            }
//	        });
}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTelaProva = new JFrame();
		frmTelaProva.setTitle("TELA PROVA");
		frmTelaProva.setBounds(100, 100, 762, 660);
		frmTelaProva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frmTelaProva.getContentPane().setLayout(null);
		
//----------------------------------------------------------------------------------------
//scrollpane + tabela de provas
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 15, 478, 179);
		frmTelaProva.getContentPane().add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Prova");
	    tableModel.addColumn("Quantidade de Pilotos (Chegadas)");
		
		JTable table = new JTable(tableModel);
		table.setForeground(new Color(255, 255, 255));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(255, 128, 64));
		
		//listando as provas do banco
		List<Prova> provas = Fachada.listarProvas();

		//preenchendo a tabela com os objetos das provas
		for (Prova prova : provas) {
		    tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
		}
		
//----------------------------------------------------------------------------------------
//criar prova

		//painel criar prova
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBounds(20, 297, 196, 304);
		frmTelaProva.getContentPane().add(panel);
		panel.setLayout(null);
		
		//botão criar prova
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 262, 176, 34);
		panel.add(lblNewLabel_1);
			
		JButton btnNewButton = new JButton("Criar Prova");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fachada.criarProva();
				
				lblNewLabel_1.setText("prova criada");
				
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando as provas do banco
				List<Prova> provas = Fachada.listarProvas();

				//preenchendo a tabela com os objetos das provas
				for (Prova prova : provas) {
				    tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
				}
				
			}
		});
		btnNewButton.setBounds(35, 211, 120, 41);
		panel.add(btnNewButton);
		
		//painel deletar prova
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_1.setBounds(226, 297, 218, 304);
		frmTelaProva.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//label ID da prova + caixa de texto
		JLabel lblNewLabel = new JLabel("ID da prova:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(10, 136, 77, 13);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(97, 133, 71, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		//botão deletar prova + label tratamento de erro
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 260, 176, 34);
		panel_1.add(lblNewLabel_1_1);
		
		
		JButton btnNewButton_1 = new JButton("Deletar Prova");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String texto = textField.getText();
					int provaId = Integer.parseInt(texto);
					
					Fachada.deletarProva(provaId);
					lblNewLabel_1_1.setText("prova deletada");
					
					//limpa a tabela setando o numero de linhas para 0
					tableModel.setRowCount(0);
					
					//listando as provas do banco
					List<Prova> provas = Fachada.listarProvas();

					//preenchendo a tabela com os objetos das provas
					for (Prova prova : provas) {
					    tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
					}
				}
				catch (Exception ex){
					lblNewLabel_1_1.setText(ex.getMessage());
				}
				
			}
		});
		btnNewButton_1.setBounds(40, 213, 120, 41);
		panel_1.add(btnNewButton_1);
		
		//painel 3
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_2.setBounds(454, 297, 208, 304);
		frmTelaProva.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
//----------------------------------------------------------------------------------------
//botões auxiliares
		
		//botão localizar prova
		JButton btnNewButton_5 = new JButton("Localizar Prova");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!janelaBuscaAberta) {
					
					janelaBuscaAberta = true;
					
			        janelaBusca = new JFrame("Buscar Prova");
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
			               
			                String texto = textFieldBusca.getText();
			                
			                int provaId = Integer.parseInt(texto);
			                
			                try {
				                Prova prova = Fachada.listarProva(provaId);
				                
				                //limpa a tabela setando o numero de linhas para 0
				                tableModel.setRowCount(0);
				              
				                //preenche a tabela com o objeto da prova encontrado
				                tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
				                
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
		        janelaBusca.getContentPane().add(new JLabel("Id da prova : "));
		        janelaBusca.getContentPane().add(textFieldBusca);
		        janelaBusca.getContentPane().add(btnLocalizar);

		        // Exiba a janela de busca
		        janelaBusca.setVisible(true);
				}		
			}
		});
		btnNewButton_5.setBounds(521, 15, 177, 41);
		frmTelaProva.getContentPane().add(btnNewButton_5);
		
		
		
		
		
		JButton btnNewButton_2 = new JButton("Listar Provas");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os provas do banco
				List<Prova> provas = Fachada.listarProvas();

				//preenchendo a tabela com os objetos das provas
				for (Prova prova : provas) {
				    tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
				}
				
			}
		});
		btnNewButton_2.setBounds(521, 66, 177, 41);
		frmTelaProva.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Deletar Todas as Provas");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fachada.deletarTodasProvas();
				
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os provas do banco
				List<Prova> provas = Fachada.listarProvas();

				//preenchendo a tabela com os objetos das provas
				for (Prova prova : provas) {
				    tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
				}
			}
		});
		btnNewButton_3.setBounds(521, 140, 177, 41);
		frmTelaProva.getContentPane().add(btnNewButton_3);
		
		//label consulta personalizada + botão consulta personalizada
		JLabel lblNewLabel_6 = new JLabel("Consulta Personalizada >");
		lblNewLabel_6.setForeground(new Color(0, 128, 0));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_6.setBounds(275, 231, 184, 27);
		frmTelaProva.getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_5_1 = new JButton("Localizar Prova com mais de Nº de Cheg");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!janelaBuscaAberta) {
					
					janelaBuscaAberta = true;
					
			        janelaBusca = new JFrame("Buscar Prova");
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
			               
			                String texto = textFieldBusca.getText();
			                int numeroChegadas = Integer.parseInt(texto);
			                
			                try {
			                	//limpa a tabela setando o numero de linhas para 0
								tableModel.setRowCount(0);
								
								//listando os pilotos do banco
								List<Prova> provas = Fachada.queryListaProvas(numeroChegadas);

								//preenchendo a tabela com os objetos dos pilotos
								for (Prova prova : provas) {
								    tableModel.addRow(new Object[]{prova.getId(), prova.getListaDeChegada().size()});
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
		        janelaBusca.getContentPane().add(new JLabel("Numero de Chegadas: "));
		        janelaBusca.getContentPane().add(textFieldBusca);
		        janelaBusca.getContentPane().add(btnLocalizar);

		        // Exiba a janela de busca
		        janelaBusca.setVisible(true);
				}
				
			}
		});
		btnNewButton_5_1.setBounds(454, 225, 272, 41);
		frmTelaProva.getContentPane().add(btnNewButton_5_1);
		
		
		
		
		
		
		frmTelaProva.setVisible(true);
	}

}
