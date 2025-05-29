
import Interfaces.IIndividuo;
import java.util.List;
import javax.swing.JFrame;

public class PlotUtils {
    public static void plotObjetivos(List<IIndividuo> pop, int geracao) {
        JFrame frame = new JFrame("Espaço dos Objetivos (1º Quadrante) - Geração " + geracao);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().add(new javax.swing.JComponent() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                // Margens para visualização
                int margin = 40;

                // Limites dos eixos
                double minVal = 0.0;
                double maxVal = 3.0;

                // Desenhar grid de fundo (apenas no 1º quadrante)
                g2.setColor(new java.awt.Color(220, 220, 220));
                int gridSpacing = 50;
                int numXTicks = (width - 2 * margin) / gridSpacing;
                int numYTicks = (height - 2 * margin) / gridSpacing;
                for (int i = 0; i <= numXTicks; i++) {
                    int x = margin + i * gridSpacing;
                    g2.drawLine(x, margin, x, height - margin);
                }
                for (int i = 0; i <= numYTicks; i++) {
                    int y = height - margin - i * gridSpacing;
                    g2.drawLine(margin, y, width - margin, y);
                }

                // Desenhar eixos (X e Y)
                g2.setColor(java.awt.Color.BLACK);
                g2.setStroke(new java.awt.BasicStroke(2));
                // Eixo X
                g2.drawLine(margin, height - margin, width - margin, height - margin);
                // Eixo Y
                g2.drawLine(margin, height - margin, margin, margin);

                // Etiquetas dos eixos
                g2.setFont(g2.getFont().deriveFont(java.awt.Font.BOLD, 16f));
                // Eixo X: "Função 1"
                String labelX = "Função 1";
                int labelXWidth = g2.getFontMetrics().stringWidth(labelX);
                g2.drawString(labelX, width / 2 - labelXWidth / 2, height - 10);

                // Eixo Y: "Função 2"
                String labelY = "Função 2";
                int labelYWidth = g2.getFontMetrics().stringWidth(labelY);
                // Rotacionar para desenhar na vertical
                java.awt.geom.AffineTransform orig = g2.getTransform();
                g2.rotate(-Math.PI / 2);
                g2.drawString(labelY, -height / 2 - labelYWidth / 2, 20);
                g2.setTransform(orig);

                // Identificação das distâncias nos eixos (valores de 0 a 3)
                g2.setFont(g2.getFont().deriveFont(java.awt.Font.PLAIN, 12f));
                for (int i = 0; i <= numXTicks; i++) {
                    double value = minVal + (maxVal - minVal) * i / numXTicks;
                    int x = margin + i * gridSpacing;
                    int y = height - margin;
                    String txt = String.format("%.1f", value);
                    int txtWidth = g2.getFontMetrics().stringWidth(txt);
                    g2.drawString(txt, x - txtWidth / 2, y + 18);
                }
                for (int i = 0; i <= numYTicks; i++) {
                    double value = minVal + (maxVal - minVal) * i / numYTicks;
                    int x = margin;
                    int y = height - margin - i * gridSpacing;
                    String txt = String.format("%.1f", value);
                    int txtWidth = g2.getFontMetrics().stringWidth(txt);
                    g2.drawString(txt, x - txtWidth - 8, y + 5);
                }

                // Desenhar os pontos (assumindo valores de 0 a 3)
                for (IIndividuo ind : pop) {
                    double xVal = ind.getFunc(0); // 0 <= x <= 3
                    double yVal = ind.getFunc(1); // 0 <= y <= 3

                    // Normalizar para o espaço do gráfico
                    int px = (int) (margin + ((xVal - minVal) / (maxVal - minVal)) * (width - 2 * margin));
                    int py = (int) (height - margin - ((yVal - minVal) / (maxVal - minVal)) * (height - 2 * margin));

                    // Ponto azul com contorno preto
                    g2.setColor(java.awt.Color.BLUE);
                    g2.fillOval(px - 4, py - 4, 8, 8);
                    g2.setColor(java.awt.Color.BLACK);
                    g2.setStroke(new java.awt.BasicStroke(1.5f));
                    g2.drawOval(px - 4, py - 4, 8, 8);
                }
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }
}
