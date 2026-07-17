(ns culture.facts
  "Country-level regional-culture catalog for South Korea (KOR) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"KOR"
   [{:culture/id "kor.dish.kimchi"
     :culture/name "Kimchi"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Traditional Korean side dish of salted and fermented vegetables, a staple eaten with almost every Korean meal; South Korea's kimjang (making and sharing kimchi) was inscribed by UNESCO in 2013."
     :culture/url "https://en.wikipedia.org/wiki/Kimchi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.dish.bibimbap"
     :culture/name "Bibimbap"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Korean rice dish of warm white rice topped with namul (seasoned vegetables) and gochujang; South Korean cities such as Jeonju, Jinju and Tongyeong are known for their versions."
     :culture/url "https://en.wikipedia.org/wiki/Bibimbap"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.dish.bulgogi"
     :culture/name "Bulgogi"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Korean dish of thin, marinated slices of beef grilled or pan-cooked, very popular in South Korea from upscale restaurants to supermarket pan-ready kits."
     :culture/url "https://en.wikipedia.org/wiki/Bulgogi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.dish.tteokbokki"
     :culture/name "Tteokbokki"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Korean food made from small garae-tteok rice cakes, well known as Korea's representative street food."
     :culture/url "https://en.wikipedia.org/wiki/Tteokbokki"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.beverage.soju"
     :culture/name "Soju"
     :culture/country "KOR"
     :culture/kind :beverage
     :culture/summary "Clear, colorless distilled alcoholic beverage from Korea, traditionally made from rice; it originated in 13th-century Goryeo and remains deeply embedded in South Korean culture."
     :culture/url "https://en.wikipedia.org/wiki/Soju"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.beverage.makgeolli"
     :culture/name "Makgeolli"
     :culture/country "KOR"
     :culture/kind :beverage
     :culture/summary "Milky, lightly sparkling Korean rice wine; it was the most-consumed alcoholic drink in South Korea in the 1960s and 1970s and has seen a recent resurgence."
     :culture/url "https://en.wikipedia.org/wiki/Makgeolli"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.craft.hanji"
     :culture/name "Hanji"
     :culture/name-local "한지"
     :culture/country "KOR"
     :culture/kind :craft
     :culture/summary "Traditional handmade paper from Korea, made from the inner bark of the paper mulberry, a tree native to Korea."
     :culture/url "https://en.wikipedia.org/wiki/Korean_paper"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.festival.chuseok"
     :culture/name "Chuseok"
     :culture/country "KOR"
     :culture/kind :festival
     :culture/summary "Major Korean mid-autumn harvest festival on the 15th day of the 8th lunisolar month; a three-day national holiday in South Korea when people travel to ancestral hometowns."
     :culture/url "https://en.wikipedia.org/wiki/Chuseok"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kor.heritage.changdeokgung"
     :culture/name "Changdeokgung"
     :culture/country "KOR"
     :culture/kind :heritage
     :culture/summary "Former royal palace in Seoul, South Korea, made a UNESCO World Heritage Site in 1997 as an outstanding example of Far Eastern palace architecture."
     :culture/url "https://en.wikipedia.org/wiki/Changdeokgung"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-kor culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "KOR"))
                 " KOR entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
