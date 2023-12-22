(require '[clojure.string :as str])

(defn file-lines [filepath]
  (clojure.string/split (slurp filepath) #"\n"))

(def testline "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

(defn get-game-def [game-str]
  (str/split game-str #": "))

(defn get-game-no [game-no-str]
  (first (rest (str/split game-no-str #" "))))

(defn get-games [games-str]
  (str/split games-str #"(; |, )"))

;; e.g. "3 blue" becomes {"blue" 3}
(defn get-colour-score-map [colour-score-str]                                        
  {
   (first (rest (str/split colour-score-str #" ")))
   (Integer/parseInt (first(str/split colour-score-str #" ")))
   }
  )

(defn score-map [games-string]
  (map #(get-colour-score-map %) games-string))

(defn calc-score [game-scores]
  (reduce #(merge-with + %1 %2) {} game-scores))

(defn get-game-summary [game-str]
  [
   (Integer/parseInt (get-game-no (first (get-game-def game-str))))
   ;;(calc-score
    (score-map
     (get-games(first (rest (get-game-def game-str)))));;)
   ])


(defn valid-game? [game-map]
  (if (> (get game-map "red") 12) false 
      (if (> (get game-map "blue") 14) false
          (if (> (get game-map "green") 13) false true))))
  
(defn get-valid-games [from-file]
  (filter #(valid-game? (first (rest %))) (map #(get-game-summary %) (file-lines from-file))))

(reduce + (map #(first %) (get-valid-games "d2.txt")))
;(map #(get-game-summary %) (file-lines "d2_test.txt"))

