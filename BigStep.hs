
-- Definição das árvore sintática para representação dos programas:

data E = Num Int
    |Var String  
    |Soma E E
    |Sub E E
    |Mult E E
    |Div E E
    deriving(Eq,Show)

data B = TRUE
    | FALSE
    | Not B
    | And B B
    | Or  B B
    | Leq E E    -- menor ou igual
    | Igual E E  -- verifica se duas expressões aritméticas são iguais
    deriving(Eq,Show)

data C = While B C
    | If B C C
    | Seq C C
    | Atrib E E
    | Skip
    | DoWhile C B      ---- Do C While B: executa C enquanto B avalie para verdadeiro
    | Unless B C C   ---- Unless B C1 C2: se B avalia para falso, então executa C1, caso contrário, executa C2
    | Loop E C    --- Loop E C: Executa E vezes o comando C
    | Swap E E --- recebe duas variáveis e troca o conteúdo delas
    | DAtrrib E E E E -- Dupla atribuição: recebe duas variáveis "e1" e "e2" e duas expressões "e3" e "e4". Faz e1:=e3 e e2:=e4.
    deriving(Eq,Show)                


-----------------------------------------------------
-----
----- As próximas funções, servem para manipular a memória (sigma)
-----
------------------------------------------------


--- A próxima linha de código diz que o tipo memória é equivalente a uma lista de tuplas, onde o
--- primeiro elemento da tupla é uma String (nome da variável) e o segundo um Inteiro
--- (conteúdo da variável):


type Memoria = [(String,Int)]

exSigma :: Memoria
exSigma = [ ("x", 10), ("temp",0), ("y",0)]


--- A função procuraVar recebe uma memória, o nome de uma variável e retorna o conteúdo
--- dessa variável na memória. Exemplo:
---
--- *Main> procuraVar exSigma "x"
--- 10


procuraVar :: Memoria -> String -> Int
procuraVar [] s = error ("Variavel " ++ s ++ " nao definida no estado")
procuraVar ((s,i):xs) v
    | s == v     = i
    | otherwise  = procuraVar xs v


--- A função mudaVar, recebe uma memória, o nome de uma variável e um novo conteúdo para essa
--- variável e devolve uma nova memória modificada com a varíável contendo o novo conteúdo. A
--- chamada
---
--- *Main> mudaVar exSigma "temp" 20
--- [("x",10),("temp",20),("y",0)]
---
---
--- essa chamada é equivalente a operação exSigma[temp->20]

mudaVar :: Memoria -> String -> Int -> Memoria
mudaVar [] v n = error ("Variavel " ++ v ++ " nao definida no estado")
mudaVar ((s,i):xs) v n
    | s == v     = ((s,n):xs)
    | otherwise  = (s,i): mudaVar xs v n


-------------------------------------
---
--- Completar os casos comentados das seguintes funções:
---
---------------------------------




ebigStep :: (E,Memoria) -> Int
ebigStep (Var x,s) = procuraVar s x
ebigStep (Num n,s) = n
ebigStep (Soma e1 e2,s)  = ebigStep (e1,s) + ebigStep (e2,s)
ebigStep (Sub e1 e2,s) = ebigStep (e1,s) - ebigStep (e2,s)
ebigStep (Mult e1 e2,s)  = ebigStep (e1,s) * ebigStep (e2,s)
ebigStep(Div e1 e2,s) = div (ebigStep (e1,s)) (ebigStep (e2, s)) 


bbigStep :: (B,Memoria) -> Bool
bbigStep (TRUE,s)  = True
bbigStep (FALSE,s) = False
bbigStep (Not b,s) 
    | bbigStep (b,s) == True     = False
    | otherwise                  = True 
bbigStep (And b1 b2,s)
    | bbigStep (b1, s) == False = False
    | bbigStep (b2, s) == False = False
    | otherwise = True
bbigStep (Or b1 b2,s )
    | bbigStep (b1, s) == True = True
    | bbigStep (b2, s) == True = True
    | otherwise = False
bbigStep (Leq e1 e2,s) = ebigStep (e1, s) <= ebigStep (e2, s)
bbigStep (Igual e1 e2,s) = ebigStep (e1, s) == ebigStep (e2, s)

cbigStep :: (C,Memoria) -> (C,Memoria)
cbigStep (Skip,s) = (Skip,s)
cbigStep (If b c1 c2,s)
    | bbigStep (b, s) == True = cbigStep (c1, s)
    | otherwise = cbigStep (c2, s)
cbigStep (Seq c1 c2,s) = let (c1', s') = cbigStep (c1, s) in cbigStep (c2, s')
cbigStep (Atrib (Var x) e,s) =  let val = ebigStep (e, s)
                                    newMem = mudaVar s x val
                                in (Skip, newMem)
cbigStep (While b c,s)
    | bbigStep (b, s) == True = cbigStep (Seq c (While b c), s)
    | otherwise = (Skip, s)
cbigStep (DoWhile c b, s) = cbigStep (Seq c (While b c), s)
cbigStep (Loop e c, s)  
    | ebigStep (e, s) > 0 = cbigStep (Seq c (Loop (Sub e (Num 1)) c), s)
    | otherwise = (Skip, s)
cbigStep (Swap (Var x) (Var y),s) = 
    let novoX = procuraVar s x
        novoY = procuraVar s y
        s' = mudaVar s x novoY
        s'' = mudaVar s' y novoX
    in (Skip, s'')
cbigStep (DAtrrib (Var x) (Var y) e1 e2,s) =
    let s' = mudaVar s x (ebigStep (e1,s))
        s'' = mudaVar s' y (ebigStep (e2,s'))
    in (Skip, s'')

--------------------------------------
---
--- Exemplos de programas para teste
---
--- O ALUNO DEVE IMPLEMENTAR EXEMPLOS DE PROGRAMAS QUE USEM:
--- * Loop 
--- * Dupla Atribuição
--- * Do While
-------------------------------------

exSigma2 :: Memoria
exSigma2 = [("x",3), ("y",0), ("z",0)]


---
--- O progExp1 é um programa que usa apenas a semântica das expressões aritméticas. Esse
--- programa já é possível rodar com a implementação inicial  fornecida:

progExp1 :: E
progExp1 = Soma (Num 3) (Soma (Var "x") (Var "y"))

---
--- para rodar:
-- *Main> ebigStep (progExp1, exSigma)
-- 13
-- *Main> ebigStep (progExp1, exSigma2)
-- 6

--- Para rodar os próximos programas é necessário primeiro implementar as regras da semântica
---


---
--- Exemplos de expressões booleanas:


teste1 :: B
teste1 = (Leq (Soma (Num 3) (Num 3))  (Mult (Num 2) (Num 3)))

teste2 :: B
teste2 = (Leq (Soma (Var "x") (Num 3))  (Mult (Num 2) (Num 3)))


---
-- Exemplos de Programas Imperativos:

testec1 :: C
testec1 = (Seq (Seq (Atrib (Var "z") (Var "x")) (Atrib (Var "x") (Var "y"))) 
            (Atrib (Var "y") (Var "z")))

fatorial :: C
fatorial = (Seq (Atrib (Var "y") (Num 1))
                (While (Not (Igual (Var "x") (Num 1)))
                    (Seq (Atrib (Var "y") (Mult (Var "y") (Var "x")))
                            (Atrib (Var "x") (Sub (Var "x") (Num 1))))))

fibSigma :: Memoria
fibSigma = [("x", 9), ("prev", 0), ("atual", 1), ("res", 0)]

fibonacci :: C -- Loop e DAttrib, começa do 0
fibonacci = Seq 
                (DAtrrib (Var "prev") (Var "atual") (Num 0) (Num 1))
                (Loop (Var "x")
                    (
                        Seq
                            (Atrib (Var "res") (Soma (Var "atual") (Var "prev")))
                            (DAtrrib (Var "prev") (Var "atual") (Var "atual") (Var "res"))       
                    )
                )

sqrtMem :: Memoria
sqrtMem = [("n", 49), ("guess", 1), ("prevGuess", 0)] --n = raiz a ser calculada

-- Calcula o valor aproximado de uma raiz
sqrtGuess :: C -- Uso: cbigStep (sqrtGuess, sqrtMem)
sqrtGuess = 
    Seq 
        (DAtrrib (Var "guess") (Var "n") (Num 1) (Var "n"))
        (DoWhile
            (Seq
                (Atrib (Var "prevGuess") (Var "guess"))
                (Atrib (Var "guess") (Div (Soma (Var "guess") (Div (Var "n") (Var "guess"))) (Num 2)))
            )
            (Not (Igual (Var "guess") (Var "prevGuess")))
        )

log2Mem :: Memoria
log2Mem = [("n", 16), ("k", 0), ("p", 1)] -- fórmula: 2^k = n

-- Calcula o logaritmo base 2
log2 :: C -- Uso: cbigStep (log2, log2Mem)
log2 =
    Seq
        (Seq
            (Atrib (Var "k") (Num 0))
            (Atrib (Var "p") (Num 1))
        )
        (Seq
            (DoWhile
                (Seq
                    (Atrib (Var "k") (Soma (Var "k") (Num 1)))
                    (Atrib (Var "p") (Mult (Var "p") (Num 2)))
                )
                (Leq (Var "p") (Var "n"))
            )
            -- faz k-1 porque tava dando um valor acima (n sei porque)
            (Atrib (Var "k") (Sub (Var "k") (Num 1)))
        )



