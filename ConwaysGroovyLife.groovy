class ConwaysGroovyLife {
    Grid emptyGrid(Integer width, Integer height) {
        Grid.create(width, height, false)
    }

    Grid cycle(Grid current) {
        final nextGrid = emptyGrid(current.rowCount, current.columnCount)
        current.each {x, y, v ->
            final neighborCount = countNeighbors(current, x, y)
            final alive

            if (neighborCount < 2 || neighborCount > 3) {
                alive = false
            } else if (v || neighborCount == 3) {
                alive = true
            }

            nextGrid.put(x, y, alive)
        }
        return nextGrid
    }

    Integer countNeighbors(Grid grid, Integer ofX, Integer ofY) {
        ((ofX - 1)..(ofX + 1)).inject(0) {ic, i ->
            ((ofY - 1)..(ofY + 1)).inject(ic) {jc, j ->
                if (!(i == ofX && j == ofY) &&
                        (i > 0 || j > 0 || i <= grid.rowCount || j <= grid.columnCount)) {
                    if(grid.get(i, j)) jc++
                }
                return jc
            }
        }
    }
}



