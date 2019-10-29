package com.mohamed.olatvplayer.toolkit;

import android.util.Log;

import com.mohamed.olatvplayer.Models.M3UItem;

import java.util.ArrayList;
import java.util.List;

public class M3UParser {

    private static final String EXT_M3U = "#EXTM3U";
    private static final String EXT_INF = "#EXTINF:";
    private static final String EXT_PLAYLIST_NAME = "#PLAYLIST";
    private static final String EXT_LOGO = "tvg-logo";
    private static final String EXT_URL = "http://";
    private static final String EXT_GROUP = "group-title";
    private static String FILEURL = "";

    public M3UParser() {

    }


    public static String getFILEURL() {
        return FILEURL;
    }

    public static void setFILEURL(String url) {
        M3UParser.FILEURL = url;
    }

    public List<M3UItem> parseFile(String rawData) {

        List<M3UItem> playlistItems = new ArrayList<>();

        String linesArray[] = rawData.split(EXT_INF);
        for (int i = 0; i < linesArray.length; i++) {
            String currLine = linesArray[i];
            if (currLine.contains(EXT_M3U)) {
                //header of file
                if (currLine.contains(EXT_PLAYLIST_NAME)) {
                    String fileParams = currLine.substring(EXT_M3U.length(), currLine.indexOf(EXT_PLAYLIST_NAME));
                    String playListName = currLine.substring(currLine.indexOf(EXT_PLAYLIST_NAME) + EXT_PLAYLIST_NAME.length()).replace(":", "");
                    //   m3UPlaylist.setPlaylistName(playListName);
                    //   m3UPlaylist.setPlaylistParams(fileParams);
                } else {
                    //   m3UPlaylist.setPlaylistName("Noname Playlist");
                    //   m3UPlaylist.setPlaylistParams("No Params");
                }
            } else {
                M3UItem playlistItem = new M3UItem();
                String[] dataArray = currLine.split(",");
                if (dataArray[0].contains(EXT_LOGO)) {
                    String duration = dataArray[0].substring(0, dataArray[0].indexOf(EXT_LOGO)).replace(":", "").replace("\n", "");
                    String icon = dataArray[0].substring(dataArray[0].indexOf(EXT_LOGO) + EXT_LOGO.length()).replace("=", "").replace("\"", "").replace("\n", "");
                    playlistItem.setItemDuration(duration);
                    playlistItem.setItemIcon(icon);
                } else {
                    String duration = dataArray[0].replace(":", "").replace("\n", "");
                    playlistItem.setItemDuration(duration);
                    playlistItem.setItemIcon("");
                }
                try {
                    String group = dataArray[1].substring(dataArray[1].indexOf(EXT_GROUP) + 11, dataArray[1].indexOf(",")).replaceAll(String.valueOf('"'), "");
                    String url = dataArray[1].substring(dataArray[1].indexOf(EXT_URL)).replace("\n", "").replace("\r", "");
                    String name = dataArray[1].substring(0, dataArray[1].indexOf(EXT_URL)).replace("\n", "");
                    playlistItem.setItemName(name);
                    playlistItem.setItemUrl(url);
                } catch (Exception fdfd) {
                    Log.e("Google", "Error: " + fdfd.fillInStackTrace());
                }
                //playlistItems.add(playlistItem);
                System.out.println(playlistItem);
            }
        }

        return null;
    }


}

