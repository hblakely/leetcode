package scenarios

func SearchMatrix(matrix [][]int, target int) bool {
	rows := len(matrix)
	cols := len(matrix[0])
	// binary search, subtract 1 for 0 based
	hi := rows*cols - 1
	lo := 0

	var mid, r, c, val int

	for hi >= lo {
		mid = lo + (hi-lo)/2
		c = mid % cols
		r = mid / cols

		val = matrix[r][c]
		if val == target {
			return true
		}
		if val > target {
			hi = mid - 1
		} else {
			lo = mid + 1
		}
	}
	return false
}
