(ns statute.facts
  "General-law compliance catalog for South Korea (KOR) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra/-can/-aus's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites the Korea Legislation Research Institute's official
  English-translation portal (elaw.klri.re.kr) -- never fabricated. KLRI
  is a Korean government-funded research institute and its translations
  are the only ENGLISH source with any official standing; KLRI itself
  disclaims the English text as reference-only (the authoritative
  Korean text lives on law.go.kr). This distinction is recorded via
  `:statute/url-provenance :official-klri-reference-translation` rather
  than implying the English text itself is legally authoritative. A law
  not in this table has NO spec-basis, full stop; extend `catalog`, do
  not invent an id/url. Title for every entry below was directly
  WebFetch-verified against the live elaw.klri.re.kr page on 2026-07-15.")

(def catalog
  "iso3 -> vector of statute entries."
  {"KOR"
   [{:statute/id "kor.commercial-act"
     :statute/title "Commercial Act"
     :statute/jurisdiction "KOR"
     :statute/kind :law
     :statute/url "https://elaw.klri.re.kr/eng_service/lawView.do?hseq=54525&lang=ENG"
     :statute/url-provenance :official-klri-reference-translation
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "kor.personal-information-protection-act"
     :statute/title "Personal Information Protection Act"
     :statute/jurisdiction "KOR"
     :statute/kind :law
     :statute/url "https://elaw.klri.re.kr/eng_service/lawView.do?hseq=53044&lang=ENG"
     :statute/url-provenance :official-klri-reference-translation
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "kor.labor-standards-act"
     :statute/title "Labor Standards Act"
     :statute/jurisdiction "KOR"
     :statute/kind :law
     :statute/url "https://elaw.klri.re.kr/eng_service/lawView.do?hseq=25437&lang=ENG"
     :statute/url-provenance :official-klri-reference-translation
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-kor statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "KOR")) " KOR statutes seeded with an "
                 "official KLRI (elaw.klri.re.kr) reference-translation citation. "
                 "Extend `statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
