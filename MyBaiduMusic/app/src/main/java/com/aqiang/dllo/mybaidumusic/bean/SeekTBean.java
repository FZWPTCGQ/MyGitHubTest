package com.aqiang.dllo.mybaidumusic.bean;

import java.util.List;

/**
 * Created by qianggedemac on 16/12/15.
 * 搜索类似关键字
 */

public class SeekTBean {

    /**
     * song : [{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"732250","songname":"演员","control":"0000000000","has_mv":"0","yyr_artist":"0","artistname":"薛之谦","encrypted_songid":"7307e6dd2e5095790928cL","songid":"242078437"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"457940","songname":"寂寞的人伤心的歌","control":"0000000000","has_mv":"0","yyr_artist":"0","artistname":"龙梅子,杨海彪","encrypted_songid":"1107fcc4bb9095728740cL","songid":"265046969"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"233580","songname":"无所谓","control":"0000000000","has_mv":"1","yyr_artist":"0","artistname":"杨坤","encrypted_songid":"6805477680957d549bbL","songid":"292712"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"204000","songname":"微微一笑很倾城","control":"0000000000","has_mv":"0","yyr_artist":"0","artistname":"杨洋","encrypted_songid":"5008100260680957eb93c6L","songid":"268591208"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"90760","songname":"摇摆哥","control":"0000000000","has_mv":"1","yyr_artist":"0","artistname":"王绎龙","encrypted_songid":"2606530d830956d3f1f2L","songid":"5442947"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"57150","songname":"阳光总在风雨后","control":"0000000000","has_mv":"1","yyr_artist":"0","artistname":"许美静","encrypted_songid":"6405342040956fcd4c4L","songid":"213508"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"53600","songname":"没有你陪伴我真的好孤单","control":"0000000000","has_mv":"0","yyr_artist":"0","artistname":"杨泉","encrypted_songid":"4007e91177d0956210571L","songid":"244389757"},{"bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","weight":"50720","songname":"烟花易冷","control":"0000000000","has_mv":"1","yyr_artist":"0","artistname":"周杰伦","encrypted_songid":"000537c290958414d0fL","songid":"228393"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"39040","songname":"你是我今生的依靠","control":"0000000000","has_mv":"0","yyr_artist":"0","artistname":"冷漠,杨小曼","encrypted_songid":"780775a01160956e927ceL","songid":"123339030"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"36280","songname":"瑶琴","control":"0000000000","has_mv":"0","yyr_artist":"1","artistname":"少司命","encrypted_songid":"","songid":"74179241"}]
     * album : [{"albumname":"无所谓","weight":"244840","artistname":"杨坤","artistpic":"http://qukufile2.qianqian.com/data2/pic/692ee5eb0e82824654a09847fd12c421/88360034/88360034.jpg","albumid":"63112"},{"albumname":"微微一笑很倾城","weight":"204000","artistname":"杨洋","artistpic":"http://qukufile2.qianqian.com/data2/pic/60b163f8b51c4c33bb4f6db4dc8c62e3/268591173/268591173.jpg","albumid":"268591211"},{"albumname":"摇","weight":"96380","artistname":"庞龙","artistpic":"http://qukufile2.qianqian.com/data2/pic/115553075/115553075.jpg","albumid":"12324154"},{"albumname":"初.爱","weight":"72190","artistname":"杨宗纬","artistpic":"http://qukufile2.qianqian.com/data2/pic/117393084/117393084.jpg","albumid":"35366709"},{"albumname":"心痛的感觉","weight":"55870","artistname":"杨泉","artistpic":"http://qukufile2.qianqian.com/data2/pic/244382997/244382997.jpg","albumid":"244389749"},{"albumname":"你是我今生的依靠","weight":"39040","artistname":"冷漠,杨小曼","artistpic":"http://qukufile2.qianqian.com/data2/pic/123339018/123339018.jpg","albumid":"123339029"},{"albumname":"原色","weight":"38280","artistname":"杨宗纬","artistpic":"http://qukufile2.qianqian.com/data2/pic/115485710/115485710.jpg","albumid":"9070321"},{"albumname":"离心力","weight":"35670","artistname":"杨乃文","artistpic":"http://qukufile2.qianqian.com/data2/pic/56ceeab06c3ff225efdafa1eed951c53/277195760/277195760.jpg","albumid":"277195763"},{"albumname":"那一天","weight":"34420","artistname":"杨坤","artistpic":"http://qukufile2.qianqian.com/data2/pic/88373218/88373218.jpg","albumid":"65290"},{"albumname":"痒","weight":"33490","artistname":"黄龄","artistpic":"http://qukufile2.qianqian.com/data2/pic/88349945/88349945.jpg","albumid":"61379"}]
     * order : artist,song,album
     * error_code : 22000
     * artist : [{"yyr_artist":"0","artistname":"杨坤","artistid":"1380","artistpic":"http://qukufile2.qianqian.com/data2/pic/971361bda96349fb59fa501bc96c1690/246586404/246586404.jpg","weight":"154940"},{"yyr_artist":"0","artistname":"杨洋","artistid":"2914","artistpic":"http://qukufile2.qianqian.com/data2/pic/247056578/247056578.jpg","weight":"89600"},{"yyr_artist":"0","artistname":"杨宗纬","artistid":"8477","artistpic":"http://qukufile2.qianqian.com/data2/pic/33554842af9715f2d9ea6c77ad8c18f9/246669520/246669520.jpg","weight":"55070"},{"yyr_artist":"0","artistname":"杨钰莹","artistid":"1451","artistpic":"http://qukufile2.qianqian.com/data2/pic/035aa70653eb1a04cf317eb27061b939/246709885/246709885.jpg","weight":"52050"},{"yyr_artist":"0","artistname":"阎维文","artistid":"1281","artistpic":"http://qukufile2.qianqian.com/data2/pic/240322699/240322699.jpg","weight":"40240"},{"yyr_artist":"0","artistname":"杨千嬅","artistid":"1085","artistpic":"http://qukufile2.qianqian.com/data2/pic/8f01aaee544b74b75d675f92d51b4c5e/246586425/246586425.jpg","weight":"26540"},{"yyr_artist":"0","artistname":"杨丞琳","artistid":"82366","artistpic":"http://qukufile2.qianqian.com/data2/pic/f961d2307fbf3459a24bddc4d0f2ae36/276620752/276620752.jpg","weight":"23750"},{"yyr_artist":"0","artistname":"杨乃文","artistid":"1170","artistpic":"http://qukufile2.qianqian.com/data2/pic/be1c5a8d2ff94e80674a971dcddc2cb2/275254979/275254979.jpg","weight":"20350"},{"yyr_artist":"0","artistname":"Yanni","artistid":"4018","artistpic":"http://qukufile2.qianqian.com/data2/pic/f72f4c13bb770c858a73e4f09d798212/252291567/252291567.jpg","weight":"17340"},{"yyr_artist":"0","artistname":"杨青","artistid":"1620203","artistpic":"http://a.hiphotos.baidu.com/ting/abpic/item/c2fdfc039245d6888832f7a1a6c27d1ed21b2486.jpg","weight":"11030"}]
     */

    private String order;
    private int error_code;
    private List<SongBean> song;
    private List<AlbumBean> album;
    private List<ArtistBean> artist;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongBean> getSong() {
        return song;
    }

    public void setSong(List<SongBean> song) {
        this.song = song;
    }

    public List<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumBean> album) {
        this.album = album;
    }

    public List<ArtistBean> getArtist() {
        return artist;
    }

    public void setArtist(List<ArtistBean> artist) {
        this.artist = artist;
    }

    public static class SongBean {
        /**
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * weight : 732250
         * songname : 演员
         * control : 0000000000
         * has_mv : 0
         * yyr_artist : 0
         * artistname : 薛之谦
         * encrypted_songid : 7307e6dd2e5095790928cL
         * songid : 242078437
         */

        private String bitrate_fee;
        private String weight;
        private String songname;
        private String control;
        private String has_mv;
        private String yyr_artist;
        private String artistname;
        private String encrypted_songid;
        private String songid;

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(String has_mv) {
            this.has_mv = has_mv;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getEncrypted_songid() {
            return encrypted_songid;
        }

        public void setEncrypted_songid(String encrypted_songid) {
            this.encrypted_songid = encrypted_songid;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }
    }

    public static class AlbumBean {
        /**
         * albumname : 无所谓
         * weight : 244840
         * artistname : 杨坤
         * artistpic : http://qukufile2.qianqian.com/data2/pic/692ee5eb0e82824654a09847fd12c421/88360034/88360034.jpg
         * albumid : 63112
         */

        private String albumname;
        private String weight;
        private String artistname;
        private String artistpic;
        private String albumid;

        public String getAlbumname() {
            return albumname;
        }

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }
    }

    public static class ArtistBean {
        /**
         * yyr_artist : 0
         * artistname : 杨坤
         * artistid : 1380
         * artistpic : http://qukufile2.qianqian.com/data2/pic/971361bda96349fb59fa501bc96c1690/246586404/246586404.jpg
         * weight : 154940
         */

        private String yyr_artist;
        private String artistname;
        private String artistid;
        private String artistpic;
        private String weight;

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getArtistid() {
            return artistid;
        }

        public void setArtistid(String artistid) {
            this.artistid = artistid;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
