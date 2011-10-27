import groovy.swing.SwingBuilder
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Label
import javax.swing.JFrame

class GroovyLifeGUI {
    final swing = new SwingBuilder()
    final game = new ConwaysGroovyLife()

    Integer rows = 50
    Integer columns = 50
    GameGrid gridHolder = new GameGrid()

    public static void main(String[] args) {
        new GroovyLifeGUI()

    }

    GroovyLifeGUI() {
        gridHolder.grid = game.emptyGrid(rows, columns)
        gridHolder.grid.put(10, 10, true)
        gridHolder.grid.put(10, 11, true)
        gridHolder.grid.put(10, 12, true)
        swing.edt {
            frame title: "Conway's Groovy Game of Life", show: true, pack: true,
                    defaultCloseOperation: JFrame.EXIT_ON_CLOSE, {
                        panel {
                            generateTable()
                        }
                    }
        }
        loop()
    }

    Closure generateTable = {
        swing.tableLayout {
            (1..rows).each {x ->
                tr {
                    (1..columns).each {y ->
                        td {
//                            label id: "$x,$y", foreground: grid.get(x, y) ? Color.BLACK : Color.WHITE, text: "*",
//                                    preferredSize: new Dimension(10, 10), font: new Font(Font.MONOSPACED, Font.BOLD, 14)
                            widget(new GameGridCell(), gameGrid: gridHolder, x: x, y: y, id: "$x,$y", text: "*",
                                    preferredSize: new Dimension(10, 10), font: new Font(Font.MONOSPACED, Font.BOLD, 14),
                                    mouseClicked: {event-> event.source.gameGrid.grid.put (x, y, true)})
                        }
                    }
                }
            }
        }
    }

    void loop() {
        while(true){
            gridHolder.grid = game.cycle(gridHolder.grid)
            Thread.sleep(100)
        }
    }

    class GameGrid extends Observable {
        Grid grid

        void setGrid(Grid grid) {
            this.grid = grid
            notifyObservers()
        }
    }

    class GameGridCell extends Label implements Observer{
        Integer x
        Integer y
        GameGrid gameGrid

        void setGameGrid(GameGrid gameGrid) {
            this.gameGrid?.removeObserver(this)
            this.gameGrid = gameGrid
            gameGrid.addObserver(this)
        }

        void update(Observable o, Object args) {
            foreground = gameGrid.grid.get(x, y) ? Color.BLACK : Color.WHITE
        }
    }
}