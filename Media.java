package Phone;

import java.util.ArrayList;

abstract class Media {
    private static ArrayList<MediaFile> medlist = new ArrayList<MediaFile>();
    static class  MediaFile
    {
        boolean type ; // false means videos true is music
        String name ;
        double length; // in minutes

        MediaFile(String type, String name, double length)
        {
            setType(type);
            setLength(length);
            setName(name);
        }
        public String toString()
        {
            if(type) // if type is music
                return "Music "+ name + " ,"+ length + " minutes long";
            else
                return "Video "+ name + " ,"+ length + " minutes long";
        }

        public String getType() {
           if(type)
               return "Music";
           else
               return "Video";
        }

        public String getName() {
            return name;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String input) {
            if(input.toLowerCase() == "music" || input.toLowerCase() == "song")
                this.type = true;
            if(input.toLowerCase() == "video")
                this.type = false;
        }
        void play()
        {
            if(this.type)
                System.out.println("Song " + name + " is playing for " + length +" minutes");
            else
                System.out.println("Video " + name + " is playing for " + length +" minutes");
        }
    }
    static public void addMedia(String type, String name, double length)
    {
        medlist.add(new MediaFile(type, name, length));
    }
    static public void playByName(String name)
    {
        for (MediaFile mediaFile : medlist) {
            if (mediaFile.getName().equals(name)) {
                mediaFile.play();
                return;
            }
        }
        System.out.println("No media by that name has been found");
    }
    static public void playAllMedia()
    {
        for(MediaFile i : medlist) i.play();
    }

}
