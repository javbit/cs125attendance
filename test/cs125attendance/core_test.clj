(ns cs125attendance.core-test
  (:require [clojure.test :refer :all]
            [cs125attendance.core :refer :all]))

;; Pairing should take a sequence and make as many sequential tuples as
;; possible. Let S be a sequence of length n. If n is even, then (pair S) should
;; return a sequence of length n/2 containing sequential tuples. If n is 1, then
;; return the single element. For any odd n greater than 1, there should be one
;; triple and the rest tuples.
(deftest test-pair
  (testing "Making pairs from"
    (testing "an empty sequence"
      (are [e a] (= e (pair a))
        '() nil
        '() '()))
    (testing "an even-length sequence"
      (are [e a] (= e (pair a))
        '((:a :b)) '(:a :b)
        '((:a :b) (:c :d)) '(:a :b :c :d)))
    (testing "an odd-length sequence"
      (are [e a] (= e (pair a))
        '((:a)) '(:a)
        '((:a :b :c)) '(:a :b :c)
        '((:a :b :c) (:d :e)) '(:a :b :c :d :e)))))

;; Splitting a triple into tuples. This one does all possible pairs.
(deftest test-edges
  (testing "Splitting"
    (testing "an empty sequence"
      (are [e a] (= e (edges a))
        '() nil
        '() '()))
    (testing "a tuple"
      (is (= '((:a :b))
             (edges '(:a :b)))))
    (testing "a triple"
      (is (= #{'(:a :b) '(:b :c) '(:c :a)}
             (set (edges '(:a :b :c))))))))

;; Strict pairing that has no triples
(deftest test-pair*
  (testing "Strict pairings from"
    (testing "an empty sequence"
      (are [e a] (= e (pair* a))
        '() nil
        '() '()))
    (testing "an even-length sequence"
      (are [e a] (= e (pair* a))
        '((:a :b)) '(:a :b)
        '((:a :b) (:c :d)) '(:a :b :c :d)))
    (testing "an odd-length sequence"
      (is (= '((:a)) (pair* '(:a))))
      (are [e a] (= e (set (pair* a)))
        #{'(:a :b) '(:b :c) '(:c :a)} '(:a :b :c)
        #{'(:a :b) '(:b :c) '(:c :a) '(:d :e)} '(:a :b :c :d :e)))))
