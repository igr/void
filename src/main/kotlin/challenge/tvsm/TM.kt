package challenge.tvsm


fun main() {
	MazeLoader()
		.loadMazes()
		.stream()
		.forEach {
			println("---------------------------")
			MazeSolver(it).solve()
		}

}
