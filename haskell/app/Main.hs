{-# LANGUAGE FlexibleInstances, UndecidableInstances, DuplicateRecordFields #-}

module Main(main) where

--import HelloWorld(mainHW)
import ListReplication(mainLR)


main :: IO()
main = do
    mainLR
