package scenario994

func OrangesRotting(grid [][]int) int {
	queue := [][]int{}
	rows := len(grid)
	cols := len(grid[0])
	fresh := 0
	minutes := -1
	// BFS find rotten locations, count fresh
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {
			if grid[r][c] == 1 {
				fresh++
			} else if grid[r][c] == 2 {
				queue = append(queue, []int{r, c})
			}
		}
	}
	if fresh == 0 && len(queue) > 0 {
		return 0
	} // nothing to rot
	if fresh > 0 && len(queue) == 0 {
		return -1
	}
	queue = append(queue, []int{-1, -1}) // Ticker to count minutes
	directions := [][]int{{0, 1}, {1, 0}, {-1, 0}, {0, -1}}
	// BFS rot oranges, count minutes
	for len(queue) > 0 {
		// poll from queue
		r, c := queue[0][0], queue[0][1]
		queue = queue[1:]
		if r == -1 {
			// Finish round of processing
			minutes++
			if len(queue) > 0 {
				queue = append(queue, []int{-1, -1})
			}
		} else {
			// This is a rotten organge
			// Contaminate:
			for _, d := range directions {
				nr := r + d[0]
				nc := c + d[1]
				if nr >= 0 && nr < rows && nc >= 0 && nc < cols { // in bounds
					if grid[nr][nc] == 1 {
						// fresh orange must be contaminated
						grid[nr][nc] = 2 // contaminated
						fresh--
						queue = append(queue, []int{nr, nc})
					}
				}
			}
		}

	}
	if fresh > 0 {
		return -1
	} // island of no rotten left
	return minutes
}
