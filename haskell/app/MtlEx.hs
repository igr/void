-- MTL is an acronym and stands for Monad Transformer Library
module MtlEx (
    runMtlEx,
    calcMltEx
    ) where
import Control.Monad.Writer

-- MTL is a popular approach to writing applications in Haskell.
-- Monad constraints provide capabilities such as error handling (MonadError),
-- writable state (MonadState), and environmental context (MonadReader).
-- An application typically has one monad stack, implemented as a monad transformer.

addWithLog :: Writer String Int -> Writer String Int -> Writer String Int
addWithLog a b = do
    valA <- a
    valB <- b
    tell "Performed addition. "
    return (valA + valB)

finalResult :: Writer String Int
finalResult = do
    resultA <- addWithLog (tell "Starting computation. " >> return 5) (tell "Got second value. " >> return 6)
    resultB <- addWithLog (tell "Starting second computation. " >> return 7) (tell "Got fourth value. " >> return 8)
    tell "Performed final computation. "
    return (resultA * resultB)

runMtlEx :: IO ()
runMtlEx = do

    let r = finalResult
    -- mapM_: Map each element of a structure to a monadic action, evaluate these actions from left to right, and ignore the result.
    mapM_ putStrLn $ runWriter r

calcMltEx :: IO ()
calcMltEx = do
    let r = finalResult
    print (fst $ runWriter r)
