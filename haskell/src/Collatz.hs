module Collatz where

-- The module is interested in the Collatz conjecture, a famous open problem in mathematics. It asks:
-- Does the Collatz sequence eventually reach 1 for all positive integer initial values?

-- The Collatz sequence is defined by taking any number as a starting value,
-- and then repeatedly performing the following operation:
--   if the number is even, divide it by two
--   if the number is odd, triple it and add one

-- As an example, the Collatz sequence for 3 is: 3, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1, 4, 2, 1 …
-- As you can see, once the number reaches 1, it gets caught in a loop.


-- one step of the Collatz sequence

step :: Integer -> Integer
step n
    | even n = div n 2
    | otherwise = 3 * n + 1

-- the Collatz sequence starting at n
collatz :: Integer -> [Integer]
collatz 1 = [1]
collatz n = n : collatz (step n)
