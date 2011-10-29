@groovy.lang.Grab(group = 'com.google.guava', module = 'guava', version = '10.0.1')
import com.google.common.collect.Table
import com.google.common.collect.ArrayTable

class Grid {
    private Table table

    static Grid create(Integer maxX, Integer maxY, Boolean defaultValue) {
        IntRange rows = 1..maxX
        IntRange columns = 1..maxY
        Table table = ArrayTable.create(rows, columns)
        rows.each {x ->
            columns.each {y ->
                table.put(x, y, defaultValue)
            }
        }
        return new Grid(table)
    }

    private Grid(Table t) {
        table = t
    }

    Integer getColumnCount() {
        table.columnKeyList().size()
    }

    Integer getRowCount() {
        table.rowKeyList().size()
    }

    Object each(Closure c) {
        table.columnKeyList().each {y ->
            table.rowKeyList().each {x ->
                c(x, y, table.get(x, y))
            }
        }
    }

    Boolean put(x, y, v) {
        table.put(x, y, v)
    }

    Boolean get(x, y) {
        table.get(x, y)
    }

}