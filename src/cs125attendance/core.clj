(ns cs125attendance.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn pair
  "Make sequential pairs from a sequence."
  [s]
  (if (= 0 (mod (count s) 2))
    (partition 2 s)
    (if (= 1 (count s))
      (list s)
      (concat (list (take 3 s))
              (partition 2 (drop 3 s))))))
