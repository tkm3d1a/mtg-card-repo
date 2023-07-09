package com.tkm3d1a.cardtesting.scryfall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleCard {
    private String object;
    private String id;
    private String oracle_id;
    private ArrayList<Integer> multiverse_ids;
    private int mtgo_id;
    private int mtgo_foil_id;
    private int tcgplayer_id;
    private int cardmarket_id;
    private String name;
    private String lang;
    private String released_at;
    private String uri;
    private String scryfall_uri;
    private String layout;
    private boolean highres_image;
    private String image_status;
    private ImageURIs image_uris;
    private String mana_cost;
    private double cmc;
    private String type_line;
    private String oracle_text;
    private String power;
    private String toughness;
    private ArrayList<String> colors;
    private ArrayList<String> color_identity;
    private ArrayList<String> keywords;
    //TODO: Need to add handling of "card faces"
    //TODO: Need to create "card_faces" POJO
    private Legalities legalities;
    private ArrayList<String> games;
    private boolean reserved;
    private boolean foil;
    private boolean nonfoil;
    private ArrayList<String> finishes;
    private boolean oversized;
    private boolean promo;
    private boolean reprint;
    private boolean variation;
    private String set_id;
    private String set;
    private String set_name;
    private String set_type;
    private String set_uri;
    private String set_search_uri;
    private String scryfall_set_uri;
    private String rulings_uri;
    private String prints_search_uri;
    private String collector_number;
    private boolean digital;
    private String rarity;
    private String flavor_text;
    private String card_back_id;
    private String artist;
    private ArrayList<String> artist_ids;
    private String illustration_id;
    private String border_color;
    private String frame;
    private boolean full_art;
    private boolean textless;
    private boolean booster;
    private boolean story_spotlight;
    private int edhrec_rank;
    private int penny_rank;
    private Prices prices;
    private RelatedURIs related_uris;
}
