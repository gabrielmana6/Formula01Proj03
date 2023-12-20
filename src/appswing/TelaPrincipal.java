package appswing;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import regras_negocio.Fachada;

public class TelaPrincipal {

	private JFrame frmA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fachada.inicializar();
					
					TelaPrincipal window = new TelaPrincipal();
					window.frmA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
		
		frmA.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.finalizar(); // Chamando o método de finalização da fachada
            }
        });
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmA = new JFrame();
		frmA.setTitle("Formula1");
		frmA.setBounds(100, 100, 717, 461);
		frmA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmA.setJMenuBar(menuBar);
		
//------------------------------------------------------------------------------
//botão menu piloto
		
		JMenu mnNewMenu = new JMenu("Piloto");
		menuBar.add(mnNewMenu);
		
		mnNewMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TelaPiloto telaPiloto = new TelaPiloto();
			}
		});

		
		
//------------------------------------------------------------------------------
//botão menu prova	
		JMenu mnNewMenu_1 = new JMenu("Prova");
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TelaProva telaProva = new TelaProva();
			}
		});
	
//------------------------------------------------------------------------------
//botão menu chegada	
		
		JMenu mnNewMenu_2 = new JMenu("Chegada");
		menuBar.add(mnNewMenu_2);
		
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TelaChegada telaChegada = new TelaChegada();
			}
		});
		
//------------------------------------------------------------------------------
//botão menu checar	
				
		
		JMenu mnNewMenu_3 = new JMenu("Checagem");
		menuBar.add(mnNewMenu_3);
		
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TelaChecar telaChecar = new TelaChecar();
			}
		});
		
//------------------------------------------------------------------------------
//botão menu observar corridas	
		
		
		JMenu mnNewMenu_4 = new JMenu("Observar Corridas");
		menuBar.add(mnNewMenu_4);
		
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TelaObservarCorridas telaObservarCorridas = new TelaObservarCorridas();
			}
		});
		
//------------------------------------------------------------------------------
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		frmA.getContentPane().add(lblNewLabel, BorderLayout.CENTER);

		// Carregue a imagem do arquivo
		ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/formula1.png"));

		lblNewLabel.setIcon(imagem); // Defina o ícone com a imagem

		frmA.setResizable(true);
	}

}
