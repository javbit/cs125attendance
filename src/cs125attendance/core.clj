(ns cs125attendance.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn pair
  "Make sequential pairs from a sequence."
  [s]
  (if (even? (count s))
    (partition 2 s)
    (concat (list (take 3 s))
            (partition 2 (drop 3 s)))))

(defn edges
  "Split a triple into tuples encoding the edges."
  [t]
  (if (or (nil? t) (empty? t))
    '()
    (if (even? (count t))
         (list t)
         (take 3 (partition 2 (cycle t))))))
(defn pair*
  "Strict sequential pairing with no triples."
  [s]
  (let [raw (pair s)]
    (if (let [c (count s)] (or (= 1 c) (even? c)))
      raw
      (concat (edges (first raw)) (rest raw)))))
