package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Chegada;
import modelo.Piloto;
import modelo.Prova;
import regras_negocio.Fachada;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;

public class TelaChecar {

	private JFrame frmTelaChecar;
	private JTable table_1;
	private JFrame janelaBusca;
	private boolean janelaBuscaAberta = false;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Fachada.inicializar(); //lembrar de apagar e passar para a tela principal
								
					TelaChecar window = new TelaChecar();
					window.frmTelaChecar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaChecar() {
		initialize();
		
		//lembrar de apagar e passar para a tela principal
//		frmTelaChecar.addWindowListener(new WindowAdapter() {
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
		frmTelaChecar = new JFrame();
		frmTelaChecar.setTitle("TELA CHECAR");
		frmTelaChecar.setBounds(100, 100, 853, 703);
		frmTelaChecar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTelaChecar.getContentPane().setLayout(null);
		
		
//----------------------------------------------------------------------------------------
//scrollpane + tabela de provas + label provas
		
		JLabel lblNewLabel = new JLabel("Provas:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(33, 10, 88, 34);
		frmTelaChecar.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 54, 600, 120);
		frmTelaChecar.getContentPane().add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Prova");
	    tableModel.addColumn("Quantidade de Pilotos (Chegadas)");
		
		JTable table = new JTable(tableModel);
		table.setForeground(new Color(128, 128, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(255, 255, 255));
		table.setRowHeight(22);
		
		
		//listando as provas do banco
		List<Prova> provas = Fachada.listarProvas();

		//preenchendo a tabela com os objetos das provas
		for (Prova prova : provas) {
		    tableModel.addRow(new Object[]{prova.getId(), prova.getChegadas().size()});
		}
		
	//--------------------------------------------------------------------------------------
	//botões auxiliares à tabela de provas: listar prova, listar todas as provas, 
	//adicionar piloto à prova
		
		//botão localizar prova
		JButton btnNewButton_1 = new JButton("Localizar Prova");
		btnNewButton_1.addActionListener(new ActionListener() {
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
				                tableModel.addRow(new Object[]{prova.getId(), prova.getChegadas().size()});
				                
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
		btnNewButton_1.setBounds(642, 52, 169, 34);
		frmTelaChecar.getContentPane().add(btnNewButton_1);
		
		//botão listar provas
		JButton btnNewButton = new JButton("Listar Provas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//limpa a tabela setando o numero de linhas para 0
				tableModel.setRowCount(0);
				
				//listando os provas do banco
				List<Prova> provas = Fachada.listarProvas();

				//preenchendo a tabela com os objetos das provas
				for (Prova prova : provas) {
				    tableModel.addRow(new Object[]{prova.getId(), prova.getChegadas().size()});
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(642, 96, 169, 34);
		frmTelaChecar.getContentPane().add(btnNewButton);
			
		//botão listar provas	
		JButton btnNewButton_2 = new JButton("Adicionar Pilotos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!janelaBuscaAberta) {
					
					janelaBuscaAberta = true;
					
			        janelaBusca = new JFrame("Buscar Prova");
			        janelaBusca.setSize(300, 240);
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
			        
			        
			        //adicionando um campo de texto e um botão de localizar à janela de busca
			        JTextField textFieldProva = new JTextField(20);
			        JTextField textFieldColocacao = new JTextField(20);
			        JTextField textFieldPiloto = new JTextField(20);
			        JButton btnAdicionar = new JButton("Adicionar Piloto");
	
			        btnAdicionar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			               
			                String texto = textFieldProva.getText();
			                String texto2 = textFieldColocacao.getText();
			                String texto3 = textFieldPiloto.getText();
			                
			                int provaId = Integer.parseInt(texto);
			                int colocacao = Integer.parseInt(texto2);
			                
			                try {
				                Fachada.criarChegada(provaId, colocacao, texto3);
				                
				                //limpa a tabela setando o numero de linhas para 0
								tableModel.setRowCount(0);
								
								//listando os provas do banco
								List<Prova> provas = Fachada.listarProvas();

								//preenchendo a tabela com os objetos das provas
								for (Prova prova : provas) {
								    tableModel.addRow(new Object[]{prova.getId(), prova.getChegadas().size()});
								}
				                
				                //fecha a janela de busca individual de piloto
				                janelaBusca.dispose();	
			                }
			                catch (Exception ex) {
		                        //exibição de uma mensagem de erro com opção de fechar
		                        JOptionPane.showMessageDialog(janelaBusca, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		                    }
			                			                
			            }
			        });

		        janelaBusca.getContentPane().setLayout(new FlowLayout());
		        janelaBusca.getContentPane().add(new JLabel("Informe o Id da Prova : "));
		        janelaBusca.getContentPane().add(textFieldProva);
		        janelaBusca.getContentPane().add(new JLabel("Informe a Colocação do Piloto : "));
		        janelaBusca.getContentPane().add(textFieldColocacao);
		        janelaBusca.getContentPane().add(new JLabel("Informe o Piloto : "));
		        janelaBusca.getContentPane().add(textFieldPiloto);
		        janelaBusca.getContentPane().add(btnAdicionar);

		        // Exiba a janela de busca
		        janelaBusca.setVisible(true);
				}	
				
			}
		});
		btnNewButton_2.setBounds(642, 140, 169, 34);
		frmTelaChecar.getContentPane().add(btnNewButton_2);
		
//----------------------------------------------------------------------------------------
//scrollpane + tabela de pilotos + label pilotos
		
		JLabel lblPilotos = new JLabel("Pilotos:");
		lblPilotos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPilotos.setBounds(33, 202, 88, 34);
		frmTelaChecar.getContentPane().add(lblPilotos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 246, 600, 120);
		frmTelaChecar.getContentPane().add(scrollPane_1);
		
		DefaultTableModel tableModel2 = new DefaultTableModel();
		tableModel2.addColumn("Nome do Piloto");
	    tableModel2.addColumn("Escuderia");
		
		table_1 = new JTable(tableModel2);
		table_1.setFillsViewportHeight(true);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		//listando os pilotos do banco
		List<Piloto> pilotos = Fachada.listarPilotos();

		//preenchendo a tabela com os objetos dos pilotos
		for (Piloto piloto : pilotos) {
		    tableModel2.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
		}
		
	//--------------------------------------------------------------------------------------
	//botões auxiliares à tabela de pilotos: localizar piloto, listar todos os pilotos, 
		
		//botão localizar piloto
		JButton btnNewButton_4 = new JButton("Localizar Piloto");
		btnNewButton_4.addActionListener(new ActionListener() {
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
				                tableModel2.setRowCount(0);
				              
				                //preenche a tabela com o objeto do piloto encontrado
				                tableModel2.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
				                
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
		btnNewButton_4.setBounds(642, 246, 169, 34);
		frmTelaChecar.getContentPane().add(btnNewButton_4);
		
		
		
		//botão listar todos os pilotos
		JButton btnNewButton_3 = new JButton("Listar Todos os Pilotos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//limpa a tabela setando o numero de linhas para 0
				tableModel2.setRowCount(0);
				
				//listando os pilotos do banco
				List<Piloto> pilotos = Fachada.listarPilotos();

				//preenchendo a tabela com os objetos dos pilotos
				for (Piloto piloto : pilotos) {
				    tableModel2.addRow(new Object[]{piloto.getNome(), piloto.getEscuderia()});
				}
			}
		});
		btnNewButton_3.setBounds(642, 290, 169, 34);
		frmTelaChecar.getContentPane().add(btnNewButton_3);
		
		
//----------------------------------------------------------------------------------------
//label consulta personalizada + combobox + scrollpane + tabela de pilotos 
		
		JLabel lblNewLabel_6 = new JLabel("Consulta Personalizada >");
		lblNewLabel_6.setForeground(new Color(0, 128, 0));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_6.setBounds(26, 388, 184, 27);
		frmTelaChecar.getContentPane().add(lblNewLabel_6);
		
		JLabel lblPilotos_1 = new JLabel("Pilotos:");
		lblPilotos_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPilotos_1.setBounds(33, 425, 88, 34);
		frmTelaChecar.getContentPane().add(lblPilotos_1);
		
		//combobox para selecionar a piloto
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(142, 425, 208, 30);
		frmTelaChecar.getContentPane().add(comboBox);
		
		//obtendo a lista de pilotos 
		List<Piloto> pilotos2 = Fachada.listarPilotos();

		//limpa o combobox
		comboBox.removeAllItems();

		//adiciona os pilotos ao JComboBox
		for (Piloto piloto : pilotos2) {
		    comboBox.addItem(piloto.getNome()); // Supondo que o método toString de Prova retorne uma representação adequada para exibição no JComboBox
		}
		
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(20, 473, 600, 120);
		frmTelaChecar.getContentPane().add(scrollPane_1_1);
		
		DefaultTableModel tableModel3 = new DefaultTableModel();
		tableModel3.addColumn("Prova");
	    tableModel3.addColumn("Colocação (Chegada)");
		
		table_2 = new JTable(tableModel3);
		table_2.setFillsViewportHeight(true);
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setBackground(Color.WHITE);
		scrollPane_1_1.setViewportView(table_2);
		
		comboBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Limpa a tabela
		        tableModel3.setRowCount(0);

		        // Obtenha a prova selecionada
		        String selectedPiloto = (String) comboBox.getSelectedItem();
		        
		        
	            // Obtenha as colocações do piloto
	            //ArrayList<Integer> colocacoes;
	            

				try {
					// Obtenha as colocações do piloto
					List<Integer> colocacoes = Fachada.queryListaChegadas(selectedPiloto);
					
					// Obtenha as provas do piloto
		            List<Long> idProvas = Fachada.queryProvaDoPiloto(selectedPiloto);
		            
		            // Certifique-se de que há o mesmo número de provas e colocações
		            int numLinhas = Math.min(idProvas.size(), colocacoes.size());

		            // Crie uma matriz bidimensional para armazenar as informações
		            Object[][] data = new Object[numLinhas][2];
		            for (int i = 0; i < numLinhas; i++) {
		                long idProva = idProvas.get(i);
		                int colocacao = colocacoes.get(i);
		                data[i][0] = "Prova " + idProva;
		                data[i][1] = "Colocação " + colocacao;
		            }

		            // Preencha a tabela com os dados da matriz
		            for (int i = 0; i < numLinhas; i++) {
		                tableModel3.addRow(data[i]);
		            }
					
				} catch (Exception ex) {
					// Exibe uma mensagem de erro com opção de fechar
                    JOptionPane.showMessageDialog(janelaBusca, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

	            
		       
		    }
		});
		
		
		
		
		
		
		
		
		
		
		frmTelaChecar.setVisible(true);
	}
}
