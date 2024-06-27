{-# LANGUAGE FlexibleInstances, UndecidableInstances, DuplicateRecordFields #-}

module Main(main) where

import Control.Monad
import Data.Array
import Data.Bits
import Data.List
import Data.List.Split
import Data.Set
import Debug.Trace
import System.Environment
import System.IO
import System.IO.Unsafe


helloWorlds :: (Num n, Eq n) => n -> IO() -> IO()
helloWorlds 0 _ = return ()
helloWorlds n action = do
    action
    helloWorlds (n - 1) action

helloWorlds2 :: Int -> IO() -> IO()
helloWorlds2 n action = do
    forM_ [1..n] $ \_ -> action

helloWorlds3 :: Int -> IO() -> IO()
helloWorlds3 n action = mapM_ (const action) [1..n]

main :: IO()
main = do
    n <- readLn :: IO Int
    helloWorlds n $ putStrLn "Hello World"
    ---also
    sequence_ $ replicate n $ putStrLn "Hello World"
    -- also
    replicateM_ n (putStrLn "Hello World")
    -- also
    helloWorlds2 n $ putStrLn "Hello World"
    -- also
    helloWorlds3 n $ putStrLn "Hello World"
