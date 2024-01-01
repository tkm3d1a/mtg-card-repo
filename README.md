# MTG Card Repo Project

**FUTURE LOGO GOES HERE**

This project is born out of a need for a way to store and track card information for Magic The Gathering.  It will *eventually* consist of a full stack web application where a user can log on and input any card with a Collector number and Set letter.  A user will also be able to view their collection, view the pricing of their collection, and export their collection in various methods for either importing into other various tools (such as Archidekt) or just for personal storage.

## Whats implemented so far

### Features currently completed

- Import of bulk [Scryfall](https://scryfall.com/docs/api) card data either one by one or by file import
  - Tested size limitation of 100MB files maximum
  - Refer to [Scryfall](https://scryfall.com/docs/api) API documentation on structure of data to be imported
- Register of unique users
  - Password storage prepped but not complete (ony stored as clear text currently)
  - No verification or simple form for registering yet
- Addition of cards by user
  - Single card upload or multiple card upload
  - Multiple card upload must follow the format of the file seen in `/raw_data/opened-cards.csv`
  - No tested size limitation currently (Small sample size of ~1k cards currently)
  - As cards are added, if the [Scryfall](https://scryfall.com/docs/api) data does not allready exist, it is fetched directly from [Scryfall](https://scryfall.com/docs/api) and added to the Bulk card database
    - This may cause limitations as the [Scryfall](https://scryfall.com/docs/api) API is *soft* rate limited and can return HTTP 429 response codes
    - Currently no artifical wait is injected but could be to remove this concern at the cost of import time

### Current Limitations

Currently, only the backend is implemented in this version.  All calls and setups have been tested using [Postman](https://www.postman.com/) with local environment variables set.  The DB is created on every session as well using [Spring JPA tools](https://spring.io/projects/spring-data-jpa).  The DB is also currently based in a local Proxmox instance and running on an LXC template of a MySQL DB, that is based off of MariaDB 10.x.  Plans are to switch to a production DB in the future for final deployment.

## Future goals for next steps

- Frontend
  - Build front end using a React framework
  - Allows possible expansion to a Native application as well
  - Nothing is locked down currently on this front besides at a minimum a Web based access point
- Backend
  - Add features to view card data for a specific user
    - Currently only two export methods that are not very cean (`JSON` return and `CSV` file download, of only a single format)
  - Allow a user to search through their existing collection
  - Allow a user to directly update the quantity of cards in their collection
  - ???
- DB
  - Switch to a more cohesive solution, possibly restructre where data is stored and accessed
  - Improve security on users that can/can't write to the DB
  - Build configurations to allow for switching from `Test` to `Production` databases
- Public deployment
  - Plan is to self host a final endpoint
  - Must be investigated on security risks and how to isolate
  - ???
- Code Base imporvements
  - Need to clean up and refactor code that won't be used in final version
  - Lots of restructuring possibly
  - Add testing to allow faster devolpment of future upgrades/changes
