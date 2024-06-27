module ListReplication (mainLR) where

-- Given a list, repeat each element in the list  amount of times.
f :: Int -> [Int] -> [Int]
f n arr = concatMap (replicate n) arr

mainLR:: IO()
mainLR = getContents >>=
       mapM_ print. (\(n:arr) -> f n arr). map read. words
