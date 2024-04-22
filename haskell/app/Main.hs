module Main (main, split) where
import Lib
import MtlEx
-- import Functions
import Fun

main :: IO ()
main = do
    helloWorld

    print (split 'x' "fooxxbarxquux")
    print (split 'a' "baanaani")

    -- mltx
    print "---------"
    calcMltEx
    runMtlEx

    print "---------"
    funMain


