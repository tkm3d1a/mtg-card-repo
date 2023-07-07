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

## Plans for possible operations

- Need to make unique users
  - Basic info here for now
  - mainly used to generate a uniqe user ID for storing card listing
- User should be able to input collector number and set to add a card to their list
  - check for foil
  - check for special art/full art
- When a user inputs a card, it should be added to that users card list
  - adds a new entry to the "cards list" table
  - entry contains basic card information used for searching cards
  - will be used with a join on card info table to display info back to user
- User can update count of cards of same type
  - can increase or decrease counts, or can fully delete entry
- User can view price information on their collection of cards
- User can request database information be updated
  - need to rate limit to avoid downloading too much data

## creating a user

- info needed
  - Username
    - unique constraint
  - First name
  - Last name

Once collected, create new entry in `user_info`. Generate UUID for user, assign as PK

> UUID does not need to be an actuall `UUID` and can just be a number in a sequence

## Adding a new card

1. Collect `Collector number`
2. Collect `set id`
3. Boolean selection for `isFoil`
4. Boolean selection for `isFullArt`
5. Confirm if card is in `user_card_list` for the user
   1. if it is, increment `card_count`
   2. if not, add entry to table
6. Save updated `user_card_list` table
7. return success message to user

## Plans for MVP

- Allow user creation
- Allow adding of cards
  - do not worry on display of cards to user
- Load card data into `card_info` as base

- Structure as a basic webform for interaction
- have base DB backing setup
- confirm ability to access remotley
  - idea is to use homelab/server to hold info

## Final needs

- [ ] API for CRUD operations
- [ ] Base webform to access API
- [ ] Base DB and access setup
- [ ] placeholder
