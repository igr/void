module Main01 (main) where
import Lib
import MtlEx
import Fun

import HR01 (helloWorld01)

main :: IO ()
main = do
    helloWorld
    helloWorld01

    print (split 'x' "fooxxbarxquux")
    print (split 'a' "baanaani")

    -- mltx
    print "---------"
    calcMltEx
    runMtlEx

    print "---------"
    funMain
