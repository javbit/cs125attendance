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
