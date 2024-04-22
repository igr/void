module Lib(
    helloWorld,
    split
    ) where

helloWorld :: IO ()
helloWorld = putStrLn "\nHello Haskell!\n"

split :: Char -> String -> [String]
split _ [] = []
split c xs = start : split c (drop 1 rest)
  where start = takeWhile (/=c) xs
        rest = dropWhile (/=c) xs
