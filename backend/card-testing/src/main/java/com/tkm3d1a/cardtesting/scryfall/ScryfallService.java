package com.tkm3d1a.cardtesting.scryfall;

/**
 * This {@code Service} class will be used for fetching and handling data from the Scryfall API
 */
public class ScryfallService {
    //TODO: Send GET request to https://api.scryfall.com/bulk-data/default_cards
    //  This will be fore getting the bulk_data object
    //  This object will contain the link to download the latest default_cards JSON list
    //  https://commons.apache.org/proper/commons-io/ <- may help with downloading of file

    //TODO: store the downloaded gzip file from scryfall

    //TODO: extract the file to either a stream or just another raw .json file?
    //  https://www.geeksforgeeks.org/compressing-decompressing-files-using-gzip-format-java/#
    //  Above link for example of decompressing a GZIP file

    //TODO: batch send the file through the CardsController bulk upload endpoint?
    //  Probably best to predetermine a batch size and send? maybe 1000 cards at a time?
    //  If the file is stored and we read it bit by bit, might be harder
    //  Might be easier here to store the file in a stream and read that way?
}
