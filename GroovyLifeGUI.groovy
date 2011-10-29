import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JFrame
import javax.swing.JPanel

class GroovyLifeGUI {
    final swing = new SwingBuilder()
    final game = new ConwaysGroovyLife()
    final grid = game.emptyGrid(50, 50)
    final gameArea = new GameArea()

    public static void main(String[] args) {
        new GroovyLifeGUI()
    }

    GroovyLifeGUI() {
        grid.put(10, 10, true)
        grid.put(11, 10, true)
        grid.put(12, 10, true)
        gameArea.update(grid)
        swing.edt {
            frame title: "Conway's Groovy Game of Life", show: true, pack: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE, size: [500, 500], {
                borderLayout()

                widget gameArea, constraints: BorderLayout.CENTER, size: [400, 400]
                button text: "Cycle", constraints: BorderLayout.SOUTH, actionPerformed: { loop() }
            }
        }
    }

    void loop() {
        gameArea.update(game.cycle(gameArea.grid))
    }
}

class GameArea extends JPanel implements MouseListener {

    static final Integer CELL_SIZE = 5

    Grid grid

    GameArea() {
        this.addMouseListener(this)
    }

    void update(Grid grid) {
        this.grid = grid
        repaint()
    }

    protected void paintComponent(Graphics g) {
        g.clearRect(CELL_SIZE, CELL_SIZE, grid.rowCount * CELL_SIZE, grid.columnCount * CELL_SIZE)
        g.drawRect(CELL_SIZE, CELL_SIZE, grid.rowCount * CELL_SIZE, grid.columnCount * CELL_SIZE)
        grid.each {x, y, alive ->
            if (alive) {
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE)
            }
        }
    }

    void mouseClicked(MouseEvent e) {
        Integer gridX = e.x / CELL_SIZE
        Integer gridY = e.y / CELL_SIZE

        Boolean alive = grid.get(gridX, gridY)
        grid.put(gridX, gridY, !alive)
        repaint()
    }

    void mousePressed(MouseEvent e) { }

    void mouseReleased(MouseEvent e) { }

    void mouseEntered(MouseEvent e) { }

    void mouseExited(MouseEvent e) { }

}