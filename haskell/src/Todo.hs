module Todo(
    todo
    ) where

import Control.Exception (Exception, throw)

data TODO = TODO deriving Show
instance Exception TODO

todo :: a
todo = throw TODO
