package challenge.tvsm


fun main() {
	MazeLoader()
		.loadMazes()
		.stream()
		.forEach {
			println("---------------------------")
			it.solve()
		}

}
