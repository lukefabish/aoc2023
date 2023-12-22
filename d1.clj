(defn re-str-content []
  "1|2|3|4|5|6|7|8|9")

(defn re-str [day]
  (if (== day 2)
    (str "(" (re-str-content) ")")
    (str "(" (re-str-content) ")")))

(defn get-match [calib-str]
  (first (re-find (re-pattern (re-str 2)) calib-str)))

(defn calib-nos [calib-str]
  (Integer/parseInt
   (str
    (get-match calib-str)
    (get-match (apply str (reverse calib-str))))))

(defn file-lines [filepath]
  (clojure.string/split (slurp filepath) #"\n"))

(reduce + (map #(calib-nos %) (file-lines "d1_test.txt")))
;;(map #(calib-nos %) (file-lines "d1_test.txt"))
