enum IterationResult {
    case Repeat,
    case End,
    case Lower,
    case Higher
}

def imagineSecretNumber(): Int32 \ IO =
    let r = Random.new();
    Random.nextNatWithMax(r, 10)

def enterGuess(): Option[Int32] \ IO =
    match Console.readLine() {
        case Ok(v) => Int32.fromString(v)
        case Err(_) => Option.None
    }

def theEnd(secretNumber: Int32): Unit \ IO =
    println("The End, sorry, it was ${secretNumber}")

def success(): Unit \ IO =
    println("Bingo!")

def playIteration(secretNumber: Int32): IterationResult \ IO =
    match enterGuess() {
        case None => IterationResult.Repeat
        case Some(v) => if (v < secretNumber) IterationResult.Lower
                        else if (v > secretNumber) IterationResult.Higher
                        else IterationResult.End
    }

def game(secretNumber: Int32, iterationCount: Int32): Unit \ IO =
    match iterationCount {
        case 0 => theEnd(secretNumber)
        case c => match playIteration(secretNumber) {
            case IterationResult.Repeat =>
                println("Well... Let's try again"); 
                game(secretNumber, c)
            case IterationResult.Lower =>
                println("The number is higher, ${c - 1} tries left"); 
                game(secretNumber, c - 1)
            case IterationResult.Higher =>
                println("The number is lower, ${c - 1} tries left"); 
                game(secretNumber, c - 1)
            case IterationResult.End =>
                success()
        }
    }

def main(): Unit \ IO =
    println("Guess a number!");
    imagineSecretNumber() `game` 3
