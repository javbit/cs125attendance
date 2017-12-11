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
      (is (= '()
             (pair nil)))
      (is (= '()
             (pair '()))))
    (testing "an even-length sequence"
      (is (= '((:a :b))
             (pair '(:a :b))))
      (is (= '((:a :b) (:c :d))
             (pair '(:a :b :c :d)))))
    (testing "an odd-length sequence"
      (is (= '((:a))
             (pair '(:a))))
      (is (= '((:a :b :c))
             (pair '(:a :b :c))))
      (is (= '((:a :b) (:c :d :e))
             (pair '(:a :b :c :d :e)))))))