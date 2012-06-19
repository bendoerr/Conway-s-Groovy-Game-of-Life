Conway's Game of Life
====

[Wikipedia][http://en.wikipedia.org/wiki/Conway's_Game_of_Life]

Written in Groovy
----

I was feeling board one day and I always liked the emergent behavior that comes from the simple rules. This is an implementation in groovy with a super basic swing gui on top of it. 

**Rules**

 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by overcrowding.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

**Running**

    groovy GroovyLifeGUI.groovy

 * Place the initial cells, then click cycle and continue to click cycle to see the result of the rules being applied to the current state.

