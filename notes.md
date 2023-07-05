# Overall notes

## Analyizing card json objects

using `sample-card-info.json` file as base for below notes

### Key's to search on

- "set"
  - this appears to be a valid way to search the downloaded cards
  - can be gathered directly from physical paper cards
- "collector_number"
  - appears valid as well
  - can also be gathered directly from card

### Structure and normalization of card data

- Key's needed to store
  - "id"
    - PK for card
  - "name"
  - "uri"
  - "image_uris"
    - this is another object, might need to parse down or store elsewhere?
  - "mana_cost"
  - "cmc"
  - "type_line"
  - "colors"
  - "legalities"
    - this is another object, might need to parse down or store elsewhere?
  - "set"
    - Part of main search/index?
  - "set_name"
  - "collector_number"
    - Part of main search/index?
  - "rarity"
  - "prices"
    - this is another object, might need to parse down or store elsewhere?
