package root.view;

public enum Color {
//    Color(double red, double green, double blue, double opacity)
//    Color c = Color.web("0x0000FF",1.0);
    BLACK("#000000"),
    WHITE("#FFFFFF"),
    WHEAT("#F5DEB3"),
    RED("#FF0000"),
    GREY("#808080"),
    CYAN("#00FFFF"),
    BROWN("#A52A2A"),
    GREEN("#00FF00");
    
    private String webValue;
    Color(String str){
        webValue = str;
    }
    public String getWeb() {return webValue;}
}
