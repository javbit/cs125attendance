(ns cs125attendance.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [clojure.java.io :as io]))

(defn read-netids
  "Import text file into sequence."
  [f]
  (with-open [r (io/reader f)]
    (doall (line-seq r))))

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

(defn mkreq
  "Make the request data for post."
  [myid partnerid rating & {:keys [understand struggle]
                            :or {understand "" struggle ""}}]
  {:form-params {:yournetid myid
                 :theirnetid partnerid
                 :lecturerating rating
                 :understand understand
                 :struggle struggle}
   :content-type :json})

(defn mkposts
  "Make POSTs to endpoint."
  [endpoint pairs]
  (doseq [[a b] pairs]
    (let [double (list (list a b) (list b a))]
      (doseq [[x y] double]
        (client/post endpoint (mkreq x y (+ 1 (rand-int 10))))))))

(defn -main
  "Runs the whole attendence process."
  [& args]
  (def endpoint "http://cs125class.web.engr.illinois.edu/processfeedback.php")
  (def netids "resources/netids.txt")
  (mkposts endpoint (pair* (shuffle (read-netids netids)))))
