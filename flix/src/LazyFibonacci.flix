/// An infinite sequence of Fibonacci numbers
def fibs(): DelayList[Int32] =
    DelayList.LCons(0,
        lazy DelayList.LCons(1,
            lazy DelayList.zipWith(
                (x, y) -> x + y, fibs(), DelayList.tail(fibs()))))

/// Prints the first 20 Fibonacci numbers
// def main(): Unit \ IO =
//     DelayList.take(20, fibs()) |> DelayList.toList |> println