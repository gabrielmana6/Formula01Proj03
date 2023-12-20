package appswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Chegada;
import modelo.Prova;
import regras_negocio.Fachada;

public class TelaObservarCorridas {

	private JFrame frmTelaObservarCorridas;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Fachada.inicializar(); //lembrar de apagar e passar para a tela principal
					
					TelaObservarCorridas window = new TelaObservarCorridas();
					window.frmTelaObservarCorridas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaObservarCorridas() {
		initialize();
		
		//lembrar de apagar e passar para a tela principal
//		frmTelaObservarCorridas.addWindowListener(new WindowAdapter() {
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
		frmTelaObservarCorridas = new JFrame();
		frmTelaObservarCorridas.getContentPane().setBackground(new Color(192, 192, 192));
		frmTelaObservarCorridas.setTitle("TELA OBSERVAR CORRIDAS");
		frmTelaObservarCorridas.setBounds(100, 100, 853, 449);
		frmTelaObservarCorridas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTelaObservarCorridas.getContentPane().setLayout(null);
	
//----------------------------------------------------------------------------------------
//scrollpane + tabela mostrando a prova e seus corredores 
//+ label selecione a prova + combobox de provas
		
		//label selecione a prova
		JLabel lblNewLabel = new JLabel("Selecione a Prova:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(38, 43, 171, 31);
		frmTelaObservarCorridas.getContentPane().add(lblNewLabel);
		
		//combobox para selecionar a prova
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(229, 45, 208, 30);
		frmTelaObservarCorridas.getContentPane().add(comboBox);
		
		//obtendo a lista de provas 
		List<Prova> provas = Fachada.listarProvas();

		//limpa o combobox
		comboBox.removeAllItems();

		//adiciona as provas ao JComboBox
		for (Prova prova : provas) {
		    comboBox.addItem(prova.getId()); // Supondo que o método toString de Prova retorne uma representação adequada para exibição no JComboBox
		}
		
		
//----------------------------------------------------------------------------------------
		
		//scrollpane + tabela mostrando a prova e a lista de chegada
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 119, 694, 210);
		frmTelaObservarCorridas.getContentPane().add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		//tableModel.addColumn("Prova");
	    tableModel.addColumn("Linha de Chegada !");
		
		table = new JTable(tableModel);
		table.setForeground(new Color(0, 64, 0));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		table.setRowHeight(22); 
		
		comboBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Limpa a tabela
		        tableModel.setRowCount(0);

		        // Obtenha a prova selecionada
		        Prova selectedProva = provas.get(comboBox.getSelectedIndex());

		        // Adicione linhas para cada ocorrência na lista de chegada
		        List<Chegada> listaChegada = selectedProva.getChegadas();
		        Collections.sort(listaChegada, Comparator.comparingInt(Chegada::getColocacao));
		        
		        for (Chegada chegada : listaChegada) {
		            tableModel.addRow(new Object[]{chegada.getPiloto().getNome() + " - Colocação: " + chegada.getColocacao()});
		        }
		        
	
		    }
		});
		
		// Define as cores para as três primeiras linhas da tabela (dourado, prata e bronze)
		table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		        if (row == 0) {
		            // Primeira linha (dourado)
		            component.setBackground(new Color(255, 215, 0));
		        } else if (row == 1) {
		            // Segunda linha (prata)
		            component.setBackground(new Color(192, 192, 192));
		        } else if (row == 2) {
		            // Terceira linha (bronze)
		            component.setBackground(new Color(205, 127, 50));
		        } else {
		            // Mantém a cor padrão para outras linhas
		            component.setBackground(table.getBackground());
		        }

		        return component;
		    }
		});
		
		
	
		
		
		
		
		frmTelaObservarCorridas.setVisible(true);
	}
}
