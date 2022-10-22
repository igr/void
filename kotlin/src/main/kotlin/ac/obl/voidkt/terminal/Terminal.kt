package ac.obl.voidkt.terminal

interface Terminal<C> {
	fun read() : C<String>
	fun write(t: String): C<Unit>
}