package com.example.searchtrip.db;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class GeoIpDb {


    File database;
    DatabaseReader dbReader;
    CityResponse response;
    InetAddress ipAddress;

    public GeoIpDb() throws IOException, GeoIp2Exception {
        this.database = new File("GeoLite2-City_20230228/GeoLite2-City.mmdb");
        this.dbReader = new DatabaseReader.Builder(database).build();
        this.response = dbReader.city(ipAddress);
    }


    public void fetchCityData() throws IOException, GeoIp2Exception {
        String ip = "81.227.88.189";
        String dbLocation = "GeoLite2-City_20230228/GeoLite2-City.mmdb";

        File database = new File(dbLocation);
        DatabaseReader dbReader = new DatabaseReader.Builder(database)
                .build();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String countryName = response.getCountry().getName();
        String cityName = response.getCity().getName();
        String postal = response.getPostal().getCode();
        String state = response.getLeastSpecificSubdivision().getName();
    }
}
