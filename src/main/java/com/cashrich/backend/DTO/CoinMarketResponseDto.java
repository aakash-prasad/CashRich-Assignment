package com.cashrich.backend.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CoinMarketResponseDto {
    public Status status;
    public Map<String, CoinDetail> data;


    public static class Status {
        public Date timestamp;
        public int error_code;
        public Object error_message;
        public int elapsed;
        public int credit_count;
        public Object notice;
    }

    public static class CoinDetail {
        public int id;
        public String name;
        public String symbol;
        public String slug;
        public int num_market_pairs;
        public Date date_added;
        public ArrayList<String> tags;
        public int max_supply;
        public double circulating_supply;
        public int total_supply;
        public int is_active;
        public boolean infinite_supply;
        public Object platform;
        public int cmc_rank;
        public int is_fiat;
        public Object self_reported_circulating_supply;
        public Object self_reported_market_cap;
        public Object tvl_ratio;
        public Date last_updated;
        public Quote quote;
    }

    public static class USD {
        public double price;
        public double volume_24h;
        public double volume_change_24h;
        public double percent_change_1h;
        public double percent_change_24h;
        public double percent_change_7d;
        public double percent_change_30d;
        public double percent_change_60d;
        public double percent_change_90d;
        public double market_cap;
        public double market_cap_dominance;
        public double fully_diluted_market_cap;
        public Object tvl;
        public Date last_updated;
    }

    public static class Quote {
        public USD USD;
    }

}





