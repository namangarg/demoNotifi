package com.notifi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ws.rs.WebApplicationException;

public class util {

    private SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );
    private java.sql.Timestamp timestamp;

    public util( String timestampStr ) throws WebApplicationException {
        try {
            timestamp = new java.sql.Timestamp( df.parse( timestampStr ).getTime() );
        } catch ( final ParseException ex ) {
            throw new WebApplicationException( ex );
        }
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        if ( timestamp != null ) {
            return timestamp.toString();
        } else {
            return "";
        }
    }
}