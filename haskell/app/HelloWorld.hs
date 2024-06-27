{-# LANGUAGE FlexibleInstances, UndecidableInstances, DuplicateRecordFields #-}

module HelloWorld(mainHW) where

import Control.Monad

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

mainHW :: IO()
mainHW = do
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
