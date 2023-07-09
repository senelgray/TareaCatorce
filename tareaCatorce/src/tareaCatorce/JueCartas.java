package tareaCatorce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JueCartas extends JFrame implements ActionListener {

    private JButton btnJugar;
    private JLabel[] lblCartasJugadores;
    private JLabel lblGanador;
    private JComboBox<Integer> cmbNumJugadores;

    private String[] cartas = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public JueCartas() {
        setTitle("Juego de Cartas");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelJugadores = new JPanel(new GridLayout(1, 3));
        lblCartasJugadores = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            lblCartasJugadores[i] = new JLabel("Cartas del Jugador " + (i + 1) + ": ");
            lblCartasJugadores[i].setHorizontalAlignment(SwingConstants.CENTER);
            panelJugadores.add(lblCartasJugadores[i]);
        }

        JPanel panelConfiguracion = new JPanel();
        lblGanador = new JLabel("Ganador: ");
        cmbNumJugadores = new JComboBox<>(new Integer[]{2, 3});
        btnJugar = new JButton("Jugar");
        btnJugar.addActionListener(this);
        panelConfiguracion.add(lblGanador);
        panelConfiguracion.add(new JLabel("Número de Jugadores: "));
        panelConfiguracion.add(cmbNumJugadores);
        panelConfiguracion.add(btnJugar);

        add(panelJugadores, BorderLayout.CENTER);
        add(panelConfiguracion, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnJugar) {
            int numJugadores = (int) cmbNumJugadores.getSelectedItem();
            List<String> mazoCartas = generarMazoCartas();

            Collections.shuffle(mazoCartas);

            int numCartasPorJugador = mazoCartas.size() / numJugadores;

            for (int i = 0; i < numJugadores; i++) {
                List<String> cartasJugador = mazoCartas.subList(i * numCartasPorJugador, (i + 1) * numCartasPorJugador);
                lblCartasJugadores[i].setText("Cartas del Jugador " + (i + 1) + ": " + cartasJugador);
            }

            String ganador = determinarGanador(numJugadores, mazoCartas);
            lblGanador.setText("Ganador: Jugador " + ganador);
        }
    }

    private List<String> generarMazoCartas() {
        List<String> mazoCartas = new ArrayList<>();

        for (String carta : cartas) {
            mazoCartas.add(carta);
        }

        return mazoCartas;
    }

    private String determinarGanador(int numJugadores, List<String> mazoCartas) {
        int[] puntajes = new int[numJugadores];

        for (int i = 0; i < numJugadores; i++) {
            List<String> cartasJugador = mazoCartas.subList(i * (mazoCartas.size() / numJugadores), (i + 1) * (mazoCartas.size() / numJugadores));
            puntajes[i] = calcularPuntaje(cartasJugador);
        }

        int maxPuntaje = puntajes[0];
        int ganador = 0;
        boolean empate = false;

        for (int i = 1; i < numJugadores; i++) {
            if (puntajes[i] > maxPuntaje) {
                maxPuntaje = puntajes[i];
                ganador = i;
                empate = false;
            } else if (puntajes[i] == maxPuntaje) {
                empate = true;
            }
        }

        if (empate) {
            return "Empate";
        } else {
            return String.valueOf(ganador + 1);
        }
    }

    private int calcularPuntaje(List<String> cartas) {
        int puntaje = 0;

        for (String carta : cartas) {
            switch (carta) {
                case "As":
                    puntaje += 11;
                    break;
                case "J":
                case "Q":
                case "K":
                    puntaje += 10;
                    break;
                default:
                    puntaje += Integer.parseInt(carta);
                    break;
            }
        }

        return puntaje;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JueCartas juego = new JueCartas();
            juego.setVisible(true);
        });
    }
}
