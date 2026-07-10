(ns marketentry.facts "Korea market-entry catalog.")
(def catalog
  {"KOR" {:name "Republic of Korea"
          :owner-authority "Public Procurement Service (PPS) / KONEPS (나라장터)"
          :legal-basis "Act on Contracts to Which the State Is a Party"
          :national-spec "KONEPS supplier registration + Business Registration Number"
          :provenance "https://www.g2b.go.kr/"
          :required-evidence ["Business Registration Number (BRN) record"
                              "KONEPS registration record"
                              "Corporate registry extract"
                              "Authorized-representative record"]
          :rep-owner-authority "PPS / contracting authorities"
          :rep-legal-basis "Korean presence or authorized local representative for many public tenders"
          :rep-provenance "https://www.g2b.go.kr/"
          :corporate-number-owner-authority "National Tax Service"
          :corporate-number-legal-basis "Business Registration Number (BRN)"
          :corporate-number-provenance "https://www.nts.go.kr/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "JPN" {:name "Japan" :owner-authority "GEPS" :legal-basis "unified qualification"
          :national-spec "GEPS" :provenance "https://www.chotatujoho.go.jp/va/com/ShikakuTop.html"
          :required-evidence ["法人番号確認記録" "全省庁統一資格申請記録" "GEPS 事業者登録記録" "日本居住代理人確認記録"]}
   "SGP" {:name "Singapore" :owner-authority "GeBIZ" :legal-basis "GFR"
          :national-spec "GeBIZ" :provenance "https://www.gebiz.gov.sg/"
          :required-evidence ["UEN record" "GeBIZ registration record" "GST record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
