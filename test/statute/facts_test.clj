(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest kor-has-spec-basis
  (let [sb (facts/spec-basis "KOR")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://elaw.klri.re.kr/") sb))
    (is (every? #(= :official-klri-reference-translation (:statute/url-provenance %)) sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["KOR" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["kor.labor-standards-act"]
         (mapv :statute/id (facts/by-topic "KOR" :labor))))
  (is (empty? (facts/by-topic "KOR" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
