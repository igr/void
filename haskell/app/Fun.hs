module Fun(
    funMain
) where

data Grade = A | B | C | D | F deriving Show

-- a simple tuple of function and element
newtype Range b a = Range (a -> b, a)

-- Grade is a Semigroup: it provides an associative binary operation <>
-- F is never the result of this binary operation
instance Semigroup Grade where
    F <> x = x
    x <> _ = x

-- Grade is a Monoid: it provides an identity element mempty, which is F
instance Monoid Grade where
    mempty = F

-- (Range b) is Functor, so we need to define `fmap`
instance (Monoid b) => Functor (Range b) where
    fmap f (Range (g, a)) = Range (\_ -> g a, f a)

-- (Range b) is Applicative.
-- 1) defines a `pure` method to lift
-- 2) binary opeartion <*>
instance (Monoid b) => Applicative (Range b) where
    pure a = Range (const mempty, a)
    Range (u, v) <*> Range (x, y) = Range (\_ -> u v <> x y, v y)

-- (Range b) is Monoid
instance (Monoid b) => Monad (Range b) where
    return a = Range (const mempty, a)
    Range (u, v) >>= f = Range (\k -> u v <> x k, y) where Range (x, y) = f v


-- grade is a function that given a condition, a potential result, and a value,
-- will generate a Range with the result if the condition holds true. If the condition is not met, F will be the result.
grade :: (t -> Bool) -> Grade -> t -> Range Grade t
grade cond res val = Range (\x -> if cond x then res else F, val)

checkGrade :: (Ord a, Num a) => a -> Range Grade [a]
checkGrade val = sequence $
    [ grade (>=90) A
    , grade (>=80) B
    , grade (>=70) C
    , grade (>=60) D
    ] <*> [val]

-- The funMain function then uses this functionality to take a numeric value and return the graded result.
-- The where Range (a, b) = checkGrade 85 clause destructures the Range object into a and b elements.
funMain :: IO()
funMain = print $ a b where Range (a, b) = checkGrade 85
--funMain = print $ C <> F) <> A
