module MonadTransformer where

import Control.Monad.State

type Result a = StateT Int IO a

increment :: Result ()
increment = modify (+ 1)

greet :: String -> Result String
greet name = do
  increment
  return $ "Hello, " ++ name ++ "!"

main :: IO ()
main = do
  (message, count) <- runStateT (greet "World") 0
  putStrLn $ message ++ " (operations: " ++ show count ++ ")"
